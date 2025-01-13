-- Óscar Fernández Pastoriza --

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'BDEmpresa')
	DROP DATABASE BDEmpresa;
ELSE
	CREATE DATABASE BDEmpresa;
GO

CREATE TABLE DEPARTAMENTO (
	Num_departamento int NOT NULL,
	Nome_departamento varchar(25) NOT NULL,
	NSS_dirige varchar(15) NULL,
	Data_direccion date NULL,

	CONSTRAINT PK_NumDepartamento PRIMARY KEY (Num_departamento)
);

CREATE TABLE EMPREGADO (
	Nome varchar(25) NOT NULL,
	Apelido_1 varchar(25) NOT NULL,
	Apelido_2 varchar(25) NOT NULL,
	NSS varchar(15) NOT NULL,
	Rua varchar(30) NULL,
	Numero_rua int NULL,
	Piso varchar(4) NULL,
	CP char(5) NULL,
	Localidade varchar(25) NULL,
	Data_Nacemento date NULL,
	Salario float NULL,
	Sexo char(1) NULL,
	NSS_Supervisa varchar(15) NULL,
	Num_departamento_pertenece int NULL,

	CONSTRAINT PK_NSS_Empregado PRIMARY KEY (NSS),
	CONSTRAINT FK_NSS_NSSupervisa FOREIGN KEY (NSS_Supervisa) REFERENCES EMPREGADO(NSS),
	CONSTRAINT FK_Empregado_Num_Departamento FOREIGN KEY (Num_departamento_pertenece) REFERENCES DEPARTAMENTO(Num_departamento)
);

CREATE TABLE PROXECTO (
	Num_proxecto int NOT NULL,
	Nome_proxecto varchar(25) NOT NULL,
	Lugar varchar(25) NOT NULL,
	Num_departamento_pertenece int NOT NULL,

	CONSTRAINT PK_Proxecto PRIMARY KEY (Num_proxecto),
	CONSTRAINT FK_Proxecto_Num_Departamento FOREIGN KEY (Num_departamento_pertenece) REFERENCES DEPARTAMENTO(Num_departamento)
);

CREATE TABLE EMPREGADO_PROXECTO (
	NSS_Empregado varchar(15) NOT NULL,
	Num_proxecto int NOT NULL,
	Horas_semanais int NULL,

	CONSTRAINT PK_EmpregadoProxecto PRIMARY KEY (NSS_Empregado, Num_proxecto),
	CONSTRAINT FK_EP_Empregado FOREIGN KEY (NSS_Empregado) REFERENCES EMPREGADO(NSS),
	CONSTRAINT FK_EP_Proxecto FOREIGN KEY (Num_proxecto) REFERENCES PROXECTO(Num_proxecto)
);

ALTER TABLE DEPARTAMENTO (
	ADD CONSTRAINT FK_DirectorDepartamento FOREIGN KEY (NSS_dirige) REFERENCES EMPREGADO(NSS)
);