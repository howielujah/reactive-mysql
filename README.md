# reactive-mysql
> this is an Restful API example using Jasync MySQL and R2DBC.

## Getting Started

You'll need to have a MySQL instance up and running in order to run this code. I got it working using Docker. Here's an example.

`docker run --name=some-mysql -d -p 3306:3306 -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=sa_user -e MYSQL_PASSWORD=usersa -e MYSQL_DATABASE=company mysql/mysql-server:5.7.24`