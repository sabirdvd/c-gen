远程控制的脚本，实现中，未完...

1.生成密钥
   ssh-keygen -t rsa
2.将公密加到目标主机
   ssh-copy-id -i ~/.ssh/id_rsa.pub 远程主机ip
   
3.ssh登陆测试一下
   ssh user@ip
   