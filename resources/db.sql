CREATE DATABASE IF NOT EXISTS bialekygd DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

CREATE USER 'bialekygd'@'localhost' IDENTIFIED BY 'bialekygd';
GRANT ALL PRIVILEGES ON bialekygd.* TO 'bialekygd'@'localhost';
FLUSH PRIVILEGES;