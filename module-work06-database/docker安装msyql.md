docker安装msyql后，宿主访问docker mysql

1. docker pull mysql:5.7.28 --- 这里版本可以是最新的
2. docker run -di --name=misiai_mysql -p 33306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:5.7.28
 -p 代表端口映射，格式为  宿主机映射端口:容器运行端口
 -e 代表添加环境变量  MYSQL_ROOT_PASSWORD  是root用户的登陆密码
 （3）远程登录mysql
 连接宿主机的IP  ,指定端口为33306 

3. docker exec -it misiai_mysql bash
4. 登陆 mysql -uroot -p
5. grant all on *.* to 'root'@'%';
6. flush privileges; 
7. 宿主机登陆到docker mysql -h 127.0.0.1 -P33306 -uroot -p123456