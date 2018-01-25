1. mysql 安装
mysql 5.6 安装完成后, 会生成一个随机密码, 密码可以在  '/root/.mysql_secret'. 中找到. 第一次连接必须变更密码 且只能使用 'SET PASSWORD' 命令
1.1 macos 设置偏好启动myqlserver
1.2 设置mysql bin环境变量
1.3 mysql -u root -p 空密码登录
1.4 select password('123456');
1.5 set password = '123456';


