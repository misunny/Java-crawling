CREATE SEQUENCE categoryNum_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE reviewNum_seq
START WITH 1
INCREMENT BY 1;


CREATE TABLE category(
	categoryNum 		NUMBER 		PRIMARY KEY,--categoryNum_seq		
	categoryName 		VARCHAR2(100) 	NOT NULL
);

CREATE TABLE product(
	productName VARCHAR2(100) PRIMARY KEY,--�⺻Ű	
	brandName VARCHAR2(100) NOT NULL,		
	categoryNum NUMBER,
	FOREIGN KEY(categoryNum) REFERENCES category(categoryNum)
);

CREATE TABLE reviews(
	reviewNum NUMBER PRIMARY KEY,
	id VARCHAR2(50),		
	ageNum NUMBER,
	skinNum NUMBER,			
	text VARCHAR(4000) NOT NULL ,
	Score NUMBER DEFAULT 0,
	personDate DATE DEFAULT SYSDATE,
	productName VARCHAR2(100),
	FOREIGN KEY(productName) REFERENCES product(productName),
	FOREIGN KEY(ageNum) REFERENCES age(ageNum),
	FOREIGN KEY(skinNum) REFERENCES skin(skinNum)
); 

CREATE TABLE age(
	ageNum 	NUMBER			 PRIMARY KEY,--personAgeNum_seq	
	ageType 		VARCHAR2(20) 	 NOT NULL
);

CREATE TABLE skin(
	skinNum 	NUMBER			 PRIMARY KEY,--personSkinNum_seq
	skinType 		VARCHAR2(20) 	NOT NULL
);

