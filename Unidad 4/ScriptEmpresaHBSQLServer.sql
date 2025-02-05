IF DB_ID ('EmpresaHB') IS NOT NULL
	DROP DATABASE EmpresaHB;
ELSE 
	CREATE DATABASE EmpresaHB;

use	EmpresaHB;

IF OBJECT_ID('EMPREGADO') IS NOT NULL
	DROP TABLE EMPREGADO

CREATE table EMPREGADO(
	Nome varchar(25) not null,
    Apelido_1 varchar(25) not null,
	Apelido_2 varchar(25),
    NSS varchar(15) not null,
    Salario float,
	Data_nacemento date,
    Sexo char(1),
    CONSTRAINT PK_NSS primary key(NSS)	
);

GO
IF OBJECT_ID('DEPARTAMENTO') IS NOT NULL
	DROP TABLE DEPARTAMENTO

CREATE table DEPARTAMENTO(
	Num_departamento int not null,
    Nome_departamento varchar(25) not null,
    CONSTRAINT PK_NumDepartamento PRIMARY KEY(Num_departamento),
	CONSTRAINT U_NomeDepartamento UNIQUE (Nome_departamento)
);

GO
IF OBJECT_ID('PROXECTO') IS NOT NULL
	DROP TABLE PROXECTO

CREATE table PROXECTO(
	Num_proxecto int not null,
    Nome_proxecto varchar(25) not null,
    Lugar varchar(25) not null,
    CONSTRAINT PK_Numproxecto PRIMARY KEY(Num_proxecto),
	CONSTRAINT U_NomeProxeto UNIQUE (Nome_proxecto)
);


CREATE TABLE TELEFONO (
	NSS varchar(15) not null,
	Numero char(9) not null,
	CONSTRAINT PK_NSS_TELEFONO PRIMARY KEY (NSS, Numero),
	CONSTRAINT FK_NSS_TELEFONO FOREIGN KEY (NSS) REFERENCES EMPREGADO(NSS)
);


ALTER TABLE TELEFONO
	ADD Informacion varchar(15) null;

CREATE TABLE FAMILIAR (
	NSS_Empregado varchar(15) not null,
	Numero int not null,
	NSS varchar(15) not null,
	nome varchar(25) not null,
	apelido1 varchar(25) not null,
	apelido2 varchar(25),
	datanacemento date,
	parentesco varchar(20) not null,
	sexo char(1) not null,

	CONSTRAINT PK_FAMILIAR PRIMARY KEY (NSS_Empregado, Numero),
	CONSTRAINT FK_FAMILIAR_EMPLEADO FOREIGN KEY (NSS_Empregado) REFERENCES EMPREGADO(NSS)
)

CREATE TABLE AFICION (
    Nss_Empregado varchar(15) not null,
    Aficion varchar(50) not null,

    CONSTRAINT PK_AFICION PRIMARY KEY (Nss_Empregado, Aficion),
    CONSTRAINT FK_NSS_AFICION FOREIGN KEY (Nss_Empregado) REFERENCES EMPREGADO(NSS)
)

CREATE TABLE LUGAR (
    id int not null,
    Num_Departamento int not null,
    lugar varchar(15) not null,

    CONSTRAINT PK_LUGAR PRIMARY KEY (id),
    CONSTRAINT UQ_LUGAR UNIQUE (Num_Departamento, lugar),
    CONSTRAINT FK_LUGAR_DEPARTAMENTO FOREIGN KEY (Num_Departamento) REFERENCES DEPARTAMENTO(Num_Departamento)
)