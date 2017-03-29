--DROP DATABASE Bank
CREATE DATABASE Bank
Go
USE Bank

CREATE TABLE Users(
id int primary key identity,
username nvarchar(200),
password nvarchar(200)
)

CREATE TABLE UsersRole(
id int primary key identity,
userId int references Users(id),
userType nvarchar(200),
)

CREATE TABLE Client(
idCardNumber int primary key identity,
name nvarchar(200),
cnp int,
address nvarchar(200)
)

CREATE TABLE Account(
idNumber int primary key identity,
idCardNumber int references Client(idCardNumber),
type nvarchar(200),
amount int,
creationDate nvarchar(200)
)


