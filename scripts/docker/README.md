# Docker部署

```shell
mkdir -p /home/nginx/kuretru.com/public
mkdir -p /home/nginx/kuretru.com/log

mkdir -p /home/docker/aries-navigation
cd /home/docker/aries-navigation
wget -O compose.yaml https://github.com/kuretru/Aries-Navigation/raw/main/scripts/docker/compose.yaml
wget -O environment https://github.com/kuretru/Aries-Navigation/raw/main/scripts/docker/environment

docker compose up -d
```
