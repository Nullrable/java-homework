mysql8 in mac 配置主从复置

1. mac 安装的msyql 默认无 my.cnf 文件，需要自己配置 /etc/my.cnf 配置文件见 my.cnf
2. docker 安装的mysql 可以通过 docker.for.mac.host.internal 访问宿主机
3. mysql8 默认对密码进行了加密 'caching_sha2_password' cannot be loaded  https://www.cnblogs.com/zhurong/p/9898675.html
```
 ALTER USER 'repl'@'localhost' IDENTIFIED BY 'password' PASSWORD EXPIRE NEVER;   #修改加密规则 
 ALTER USER 'repl'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';   #更新一下用户的密码
```
4. 查看主机 bin logs
```
//查看big logs日志列表
show binary logs;


//具体查看binlog内容
sudo mysqlbinlog --no-defaults mysql-bin.000009;

sudo mysqlbinlog --no-defaults --short-form --base64-output=never mysql-bin.000009; //输出内容会更清楚些
```

5. 从机上 slave 相关命令

```

//slave 连接到 master
CHANGE MASTER TO
    MASTER_HOST='docker.for.mac.host.internal',  
    MASTER_PORT = 3306,
    MASTER_USER='repl',      
    MASTER_PASSWORD='123456',   
    MASTER_LOG_FILE='mysql-bin.000005',
    MASTER_LOG_POS=1745;


//停止
stop slave;

//开启
start slave;

//重置
reset slave;

```


  



