# K8s installation on ubuntu
# Master Node

```bash
    sudo apt update && sudo apt upgrade -y
    sudo apt-get update && sudo apt-get upgrade -y
    
```
# Control Plane
```bash
sudo swapoff -a
sudo sed -i '/ swap / s/^/#/' /etc/fstab
sudo su -
sudo hostnamectl set-hostname "master-node"
exec bash
host=$(hostname -I)
echo "$host budget-controller" >> /etc/hosts
sudo su 

cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
overlay
br_netfilter
EOF

sudo modprobe overlay
sudo modprobe br_netfilter
```

## sysctl params required by setup, params persist across reboots
```bash
cat <<EOF | sudo tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-iptables  = 1
net.bridge.bridge-nf-call-ip6tables = 1
net.ipv4.ip_forward                 = 1
EOF

```

## Apply sysctl params without reboot
```bash
sudo sysctl --system

sudo apt-get install -y apt-transport-https ca-certificates curl
sudo mkdir /etc/apt/keyrings

echo "deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] \
  https://pkgs.k8s.io/core:/stable:/v1.28/deb/ /" | \
  sudo tee /etc/apt/sources.list.d/kubernetes.list


curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.28/deb/Release.key | \
  sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg

sudo apt-get update
sudo apt-get install -y kubelet kubeadm kubectl
sudo apt-mark hold kubelet kubeadm kubectl

sudo apt install docker.io
sudo mkdir /etc/containerd
sudo sh -c "containerd config default > /etc/containerd/config.toml"

sudo sed -i 's/ SystemdCgroup = false/ SystemdCgroup = true/' /etc/containerd/config.toml
sudo systemctl restart containerd.service
sudo systemctl restart kubelet.service
sudo systemctl enable kubelet.service

sudo kubeadm config images pull
sudo kubeadm init --pod-network-cidr 192.168.0.0/16 --ignore-preflight-errors=all
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

kubectl create -f https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/tigera-operator.yaml

curl https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/custom-resources.yaml -O

kubectl create -f custom-resources.yaml

```

```bash
  kubeadm token create --print-join-command  
```
``` bash
export KUBECONFIG=~<username>/.kube/config
```

```bash
  sudo hostnamectl set-hostname master-node

```

# Worker Node

```bash
    sudo apt update && sudo apt upgrade -y
    sudo apt-get update && sudo apt-get upgrade -y
    
```

```bash
sudo swapoff -a
sudo sed -i '/ swap / s/^/#/' /etc/fstab


cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
overlay
br_netfilter
EOF

sudo modprobe overlay
sudo modprobe br_netfilter
```

## sysctl params required by setup, params persist across reboots
```bash
cat <<EOF | sudo tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-iptables  = 1
net.bridge.bridge-nf-call-ip6tables = 1
net.ipv4.ip_forward                 = 1
EOF
```

## Apply sysctl params without reboot
```bash
sudo sysctl --system

sudo apt-get install -y apt-transport-https ca-certificates curl

sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.28/deb/Release.key | \
  sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg

echo "deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] \
https://pkgs.k8s.io/core:/stable:/v1.28/deb/ /" | \
sudo tee /etc/apt/sources.list.d/kubernetes.list



sudo apt-get update
sudo apt-get install -y kubelet kubeadm kubectl
sudo apt-mark hold kubelet kubeadm kubectl


sudo apt install docker.io -y
sudo mkdir /etc/containerd

sudo sh -c "containerd config default > /etc/containerd/config.toml"

sudo sed -i 's/ SystemdCgroup = false/ SystemdCgroup = true/' /etc/containerd/config.toml

sudo systemctl restart containerd.service
sudo systemctl restart kubelet.service
sudo systemctl enable kubelet.service
```
``` bash
    echo "Run as sudo su - --> installation done, execute join with - kubeadm token create --print-join-command if its worker node, else proceed with other steps on Control node "
```
```bash
sudo kubeadm join 10.240.0.31:6443 --token <token from master node> 
```