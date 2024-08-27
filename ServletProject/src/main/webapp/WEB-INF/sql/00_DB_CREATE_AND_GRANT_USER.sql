-- 0) 관리자 계정으로 접속
-- 1) 디폴트 데이터베이스인 mysql로 이동
use mysql;

-- 2) 데이터베이스 생성 (employeedb)
CREATE DATABASE employeedb;
SHOW DATABASES;

-- 3) 유저 생성 (himedia/himedia)
CREATE USER 'himedia'@'%' IDENTIFIED BY  'himedia';
SELECT * FROM user;

-- 4) 유저에게 권한 부여
GRANT ALL PRIVILEGES ON employeedb.* TO 'himedia'@'%';
SHOW GRANTS FOR 'himedia'@'%';

-- 5) 데이터베이스(employeedb)로 이동
use employeedb;