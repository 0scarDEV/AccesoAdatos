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


    
















