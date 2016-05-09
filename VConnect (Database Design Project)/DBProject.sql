CREATE TABLE Person (
S_ID  NUMBER(10) NOT NULL,
Fname VARCHAR(255) NOT NULL,
Lname VARCHAR(255),
Address VARCHAR(255)NOT NULL,
Phone NUMBER(10) NOT NULL,
Gender CHAR,
DOB DATE,
Email_ID VARCHAR(255) NOT NULL,
PRIMARY KEY (S_ID),
constraint ckGender check (Gender in ('M', 'F'))
);

CREATE TABLE Mobile_Plans (
Plan_Id NUMBER(10) NOT NULL,
Plan_Name VARCHAR(255) NOT NULL,
Type NUMBER(1) NOT NULL,
Talktime INTEGER NOT NULL,
Data INTEGER NOT NULL,
Duration NUMBER(1) NOT NULL,
Charge INTEGER NOT NULL,
Plan_Type VARCHAR(8) NOT NULL,
PRIMARY KEY (Plan_Id),
constraint ckType check (Type in (0, 1, 2)),
constraint ckDuration check (Duration in (0, 1, 2)),
constraint ckPlanType check (Plan_Type in ('Prepaid', 'Postpaid'))
);

CREATE TABLE Account (
Account_Id NUMBER(10) NOT NULL,
Password NUMBER(32) NOT NULL,
Activated_Date DATE NOT NULL,
Security_Question_1 NVARCHAR2(1000) NOT NULL,
Security_Answer_1 NVARCHAR2(1000) NOT NULL,
Security_Question_2 NVARCHAR2(1000),
Security_Answer_2 NVARCHAR2(1000),
Billing_Type VARCHAR(9) NOT NULL,
Payment_Type VARCHAR(6) NOT NULL,
Billing_Address NVARCHAR2(1000) NOT NULL,
Outstanding_Balance INTEGER,
Plan_Id NUMBER(10) NOT NULL,
Account_Status Boolean,
PRIMARY KEY (Account_Id),
FOREIGN KEY (Plan_Id) REFERENCES Mobile_Plans,
constraint ckBillingType check (Billing_Type in ('Paper', 'Paperless')),
constraint ckPaymentType check (Payment_Type in ('EZpay', 'Normal'))
);

CREATE TABLE Customer (
S_ID NUMBER(10) NOT NULL,
ID  NUMBER(10) NOT NULL,
ID_Type NUMBER(1) NOT NULL,
Account_Id NUMBER(10) NOT NULL,
PRIMARY KEY (S_ID),
FOREIGN KEY (S_ID) REFERENCES Person,
FOREIGN KEY (Account_Id) REFERENCES Account,
constraint ckIDTYPE check (ID_Type in (0, 1, 2))
);

CREATE TABLE Customer_Credit (
S_ID NOT NULL,
Credit_Card NOT NULL,
PRIMARY KEY (S_ID),
FOREIGN KEY (S_ID) REFERENCES Customer
);

CREATE TABLE Employee (
S_ID NUMBER(10) NOT NULL,
SSN NUMBER(9) NOT NULL,
Work_Email VARCHAR(255),
Salary INTEGER,
Type CHAR,
MgrSSN NUMBER(9) NOT NULL,
PRIMARY KEY (S_ID),
FOREIGN KEY (S_ID) REFERENCES Person,
UNIQUE (SSN),
CONSTRAINT ckType check (Type in ('T','S'))
);

CREATE TABLE Technician (
Supervision_Area_Code ,
S_ID NOT NULL,
Availability VARCHAR(3) DEFAULT 'yes' NOT NULL ,
PRIMARY KEY (S_ID),
FOREIGN KEY (S_ID) REFERENCES Employee,
CONSTRAINT ckAvailablity check (Availability in ('yes','no'))
);

CREATE TABLE Staff (
Primary_Language NVARCHAR2(25),
S_ID NOT NULL,
PRIMARY KEY (S_ID),
FOREIGN KEY (S_ID) REFERENCES Employee
);

CREATE TABLE Bills (
Bill_Id NUMBER(10) NOT NULL,
Cycle_Start_Date DATE NOT NULL,
Cycle_End_Date DATE NOT NULL,
Total_Charge INTEGER NOT NULL,
Payment_Due_Date DATE NOT NULL,
Payment_Status Boolean NOT NULL,
Account_Id NUMBER(10) NOT NULL,
PRIMARY KEY (Bill_Id),
FOREIGN KEY (Account_Id) REFERENCES Account
);

CREATE TABLE Customer_Service (
Service_Id NUMBER(10) NOT NULL,
Account_Id NUMBER(10),
Service_Complaint_Description NVARCHAR(2000) NOT NULL,
Service_Requested_Date DATE,
Service_Completion_Date DATE,
Service_Report NVARCHAR2(2000),
Service_Status INTEGER,
PRIMARY KEY (Service_Id),
FOREIGN KEY (Account_Id) REFERENCES Account
);

CREATE TABLE Staff_Service (
Service_Id NUMBER(10) NOT NULL,
S_ID NUMBER(10) NOT NULL,
Call_Date DATE,
Call_Report NVARCHAR(2000),
PRIMARY KEY (Service_Id,S_ID),
FOREIGN KEY (Service_Id) REFERENCES Customer_Service,
FOREIGN KEY (S_ID) REFERENCES Staff
);

CREATE TABLE Technician_Service (
Service_Id NUMBER(10) NOT NULL,
S_ID NUMBER(10) NOT NULL,
Call_Date DATE,
Call_Report NVARCHAR2(2000),
R_S_ID NUMBER(10) NOT NULL,
PRIMARY KEY (Service_Id,S_ID),
FOREIGN KEY (Service_Id) REFERENCES Customer_Service,
FOREIGN KEY (S_ID) REFERENCES Technician,
FOREIGN KEY (R_S_ID) REFERENCES Staff_Service
);



CREATE TABLE Mobile_Nos (
ICCID NUMBER(22) NOT NULL,
Mobile_No NUMBER(10) NOT NULL,
IMSI NUMBER(15) NOT NULL,
PUK NUMBER(8) NOT NULL,
Type VARCHAR(8) NOT NULL,
Activate_By_Date DATE NOT NULL,
PRIMARY KEY (ICCID),
constraint ckType check (Type in ('Prepaid', 'Postpaid'))
);

CREATE TABLE Moblie (
Mobile_No NUMBER(10) NOT NULL,
Account_Id NUMBER(10) NOT NULL,
PRIMARY KEY (Mobile_No),
FOREIGN KEY (Account_Id) REFERENCES Account
);

CREATE TABLE M_Service_Plans (
M_Service_Id NUMBER(10) NOT NULL,
Service_Name VARCHAR(255) NOT NULL,
Charge INTEGER NOT NULL,
PRIMARY KEY (M_Service_Id),
constraint ckServiceName check (Service_Name in ('International Calls/Messages', 'International Roaming', 'Radio Service', 'Data Stash' , 'Caller Tune')));

CREATE TABLE Mobile_Services (
Mobile_No NUMBER(10) NOT NULL,
M_Service_Id NUMBER(10) NOT NULL,
PRIMARY KEY (Mobile_No,M_Service_Id),
FOREIGN KEY (Mobile_No) REFERENCES Mobile,
FOREIGN KEY (M_Service_Id) REFERENCES M_Service_Plans
);

CREATE TABLE Mobile_Transactions (
Transaction_ID NUMBER(100) NOT NULL,
Mobile_No NUMBER(10) NOT NULL,
Date DATE NOT NULL,
Time TIMESTAMP(0) NOT NULL,
Type VARCHAR(4) NOT NULL,
PRIMARY KEY (Transaction_ID),
FOREIGN KEY (Mobile_No) REFERENCES Mobile,
CONSTRAINT ckType check (Type in ('Call','Text','Data'))
);

CREATE TABLE MT_Call	(
Transaction_ID NUMBER(100) NOT NULL,
Destination CHAR(255) DEFAULT 'Incoming',
"Number" NUMBER(10),
Minutes INTEGER,
PRIMARY KEY (Transaction_ID),
FOREIGN KEY (Transaction_ID) REFERENCES Mobile_Transactions
);
	
CREATE TABLE MT_Text	( 
Transaction_ID NOT NULL,
Destination CHAR(255) DEFAULT 'Incoming',
"Number" NUMBER(10),
Direction CHAR(8),
PRIMARY KEY (Transaction_ID),
FOREIGN KEY (Transaction_ID) REFERENCES Mobile_Transactions,  
CONSTRAINT ckDirection check (Direction in ('Outgoing','Incoming'))
	);
  
CREATE TABLE MT_Data	(
Transaction_ID NOT NULL,
	"Time" TIMESTAMP(0) NOT NULL,
	"Usage" DECIMAL(7,3),
  PRIMARY KEY (Transaction_ID,"Time"),
  FOREIGN KEY (Transaction_ID) REFERENCES Mobile_Transactions
  );
	
CREATE TABLE Services	(
Service_Id NUMBER(10) NOT NULL,
	Plan_Id NUMBER(10) NOT NULL,
	Service_Name NVARCHAR(255) NOT NULL,
	Plan_Name NVARCHAR(255),
	Monthy_Charge INTEGER NOT NULL,
  PRIMARY KEY (Service_Id,Plan_Id)
  CONSTRAINT ckServiceName check (Service_Name in ('Wifi', 'Cloud', 'Cable TV', 'Wifi-Dongle'))
  );
	
CREATE TABLE Account_Services	(
Account_Id NUMBER(10),
	Service_Id NUMBER(10),
	Plan_Id NUMBER(10),
  PRIMARY KEY (Account_Id,Service_Id,Plan_Id),
  FOREIGN KEY (Account_Id) REFERENCES Account
  FOREIGN KEY (Service_Id,Transaction_ID) REFERENCES Services
  );
	
CREATE TABLE Service_Transactions (
Transaction_Id NUMBER(10) NOT NULL,
	"Date" DATE NOT NULL,
	"Time" TIMESTAMP(0) NOT NULL,
	Account_Id NUMBER(10),
	Service_Id NUMBER(10),
	Plan_Id NUMBER(10),
	"Duration" INTEGER NOT NULL,
  PRIMARY KEY (Transaction_Id),
  FOREIGN KEY (Account_Id,Service_Id,Plan_Id) REFERENCES Account_Services

  );

