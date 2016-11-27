package database;

import java.sql.Connection;
import java.sql.SQLException;

import database.connection.connect;
import database.methods.CreateDomainEx;
import database.methods.CreateTableEx;
import database.methods.database_methods;



public class buildDbMain
{
   public static void main(String args[]) throws ClassNotFoundException, SQLException
   {
	  Connection con = null;
	  con = connect.PostgreSQLJDBC("VikingHousesJava", "jens");
	  
	  CreateDomainEx exD = new CreateDomainEx(con); 
	  CreateTableEx exT = new CreateTableEx(con); 
	  
	  //JavaInputMethod.addEmployee(con); // create new employee by entering data from keyboard
	  boolean ignore = true;
	  
	  //create domains
	  if (!ignore)createDomains(exD);
	  
	  //create tables
	  if (!ignore)createTables(exT);
	  
	  //add branch
	  if (!ignore)addSomeBranch(con);
	  
	  //add houses
	  if (!ignore)addSomeHouse(con);
	  
	  //add customer
	  if (!ignore)addSomeCustomer(con);
	  
	  //add booking
	  if (!ignore)addSomeBooking(con);
	  
	  //add someEmployee
	  if (!ignore)addSomeEmployee(con);
	  
	  //add update some tables and atributes
	  if (!ignore)makeSomeUpdate(con);
	  
	  //database_methods.dropTable(con, "parttime");
	  //database_methods.deleteFromTable(con, "house", "houseName", "Solgaarden");
	  //database_methods.deleteFromTable(con, "customer","fname", "bob");
	  //database_methods.updateBookingPrice(con, 0);
	  database_methods.updateAllBookingPrices(con);
   }
   
   public static void createDomains(CreateDomainEx exD ) throws SQLException
   {

	   exD.addNewDomain("sextype", "CHAR", "F", new String[]{"M","F","X"});
	   exD.addNewDomain("choice", "CHAR", "N", new String[]{"Y","N"});
	   exD.addNewDomain("empType", "CHAR(2)", "PT", new String[]{"FT","PT"});
	   exD.addNewDomain("roles", "CHAR(4)", "CLNG", new String[]{"MNGR","RECP","CLNG"});
	   exD.addNewDomain("payment_types", "CHAR(4)", null, new String[]{"CASH","CARD","TRAN"});
	   exD.addNewDomain("danishPost", "CHAR(4)", 4);
	   exD.addNewDomain("ssn", "CHAR(10)", 10);
	   exD.addNewDomain("phone", "CHAR(8)", 8);
	   exD.addNewDomain("type_cardNo", "VARCHAR(16)", 16);
	   
   }
   
   public static void createTables(CreateTableEx exT ) throws SQLException
   {
	   exT.createTable("BRANCH", new String[][]{{"BRANCH_NR","VARCHAR(4) NOT NULL"},{"ADDRESS_STREET","VARCHAR(30)"},{"ADDRESS_PSTCOD","danishPost"},{"ADDRESS_CITY","VARCHAR(30)"},{"NO_OF_EMPLOYEES","INTEGER"},{"MANAGED_BY","VARCHAR(30)DEFAULT '1000'"}},new String[]{"pk:(BRANCH_NR)","fk:(MANAGED_BY):rf:BRANCH(BRANCH_NR)"},true,true);
	   exT.createTable("EMPLOYEE", new String[][]{{"EMPID","INTEGER NOT NULL UNIQUE"},{"FNAME","VARCHAR (30) NOT NULL"},{"LNAME","VARCHAR (30) NOT NULL"},{"SSN","SSN"},{"BDATE","DATE"},{"STARTDATE","DATE"},{"BRNNUM","VARCHAR(4) DEFAULT 1000"},{"ADDRESS_STREET","VARCHAR (60)"},{"ADDRESS_CITY","VARCHAR (30)"},{"ADDRESS_PSTCOD","DANISHPOST"},{"EMAIL","VARCHAR (40)UNIQUE"},{"Gender","sextype NOT NULL"},{"salary","integer Default 0/*CHECK (salary > 0)*/"},{"type","empType not null"},{"POSITION","roles not null"}},new String[]{"pk:(EMPID)","uq:(EMPID,type)","uq:(EMPID,POSITION)","fk:(BRNNUM):rf:BRANCH(BRANCH_NR)"},true,true);
	   exT.createTable("PartTime", new String[][]{{"EMPID","INTEGER NOT NULL"},{"type","char(2) not null DEFAULT 'PT' check(type='PT')"},{"hourlyRate","INTEGER NOT NULL default 120"},{"weeklyhours","INTEGER NOT NULL default 0"}},new String[]{"pk:(EMPID)","fk:(EMPID,type):rf:employee(EMPID,type)"},true,true);
	   exT.createTable("FullTime", new String[][]{{"EMPID ","INTEGER NOT NULL"},{"type","char(2) not null DEFAULT 'FT' check(type='FT')"},{"monthlyrate","INTEGER NOT NULL default 28000"},{"vacationDays","INTEGER NOT NULL default 40"}},new String[]{"pk:(EMPID)","fk:(EMPID, type):rf:employee(EMPID,type)"},true,true);
	   exT.createTable("emp_telephone", new String[][]{{"telNO","PHONE"},{"EMPID","INTEGER NOT NULL"}},new String[]{"pk:(telNo)","fk:(EMPID):rf:employee(empid)"},true,true);
	   exT.createTable("Manager", new String[][]{{"EMPID","INTEGER NOT NULL"},{"officeNumber","INTEGER DEFAULT '345'"},{"Bonus","INTEGER"},{"educationLevel","VARCHAR(30)"},{"phoneExtention","INTEGER DEFAULT '9988'"},{"POSITION","char(4) check(POSITION='MNGR')"}},new String[]{"pk:(EMPID)","fk:(EMPID,POSITION):rf:employee(EMPID,POSITION)"},true,true);
	   exT.createTable("Cleaning", new String[][]{{"EMPID","INTEGER NOT NULL"},{"cleaningSpeed","VARCHAR NOT NULL"},{"numberOfRoomsAssigned","INTEGER NOT NULL"},{"POSITION","char(4) check(POSITION='CLNG')"}},new String[]{"pk:(EMPID)","fk:(EMPID,POSITION):rf:employee(EMPID,POSITION)"},true,true);
	   exT.createTable("Receptionist", new String[][]{{"EMPID","INTEGER NOT NULL"},{"midnightAvailability","BOOLEAN NOT NULL"},{"Languages","VARCHAR"},{"POSITION","char(4) check(POSITION='RECP')"}},new String[]{"pk:(EMPID)","fk:(EMPID,POSITION):rf:employee(EMPID,POSITION)"},true,true);
	   exT.createTable("EMPLOYEE_HISTORY", new String[][]{{"EMP_ID","INTEGER"},{"BDAY_IDEAS","VARCHAR(60)"},{"COMPLAINTS","VARCHAR(200)"},{"OBSERVATIONS","VARCHAR(100)"}},new String[]{"pk:(EMP_ID)","fk:(EMP_ID):rf:EMPLOYEE(EMPID)"},true,true);
	   exT.createTable("HOUSE", new String[][]{{"houseName","VARCHAR(30)"},{"capacity","INTEGER"},{"pricePerDay","DECIMAL"},{"BRANCH_NO","VARCHAR(4) default 1000"}},new String[]{"pk:(houseName)","fk:(BRANCH_NO):rf:BRANCH(BRANCH_NR)"},true,true);
	   exT.createTable("CUSTOMER", new String[][]{{"cusSSN","ssn"},{"fName","VARCHAR(30)"},{"lName","VARCHAR(30)"},{"eMail","VARCHAR(30)"}},new String[]{"pk:(cusSSN)"},false,false);
	   exT.createTable("CUS_TELNO", new String[][]{{"telNO","phone"},{"cusSSN","ssn NOT NULL"}},new String[]{"pk:(telNo)","fk:(cusSSN):rf:CUSTOMER(cusSSN)"}, true, true);
	   exT.createTable("Booking", new String[][]{{"booking_id","INTEGER UNIQUE NOT NULL"},{"house_name","VARCHAR(20) NOT NULL"},{"cusSSN","ssn NOT NULL"},{"start_date","Date"},{"end_date","Date"},{"num_of_ppl","INTEGER"},{"rented_days","INTEGER CHECK(rented_days>0)"},{"amount_to_pay","DECIMAL NOT NULL default 0"},{"cardNo","type_CardNo"},{"was_paid","boolean"},{"payment_type","payment_types"}},new String[]{"pk:(start_date, end_date, house_name)","fk:(house_name):rf:HOUSE(houseName)","fk:(cusSSN):rf:CUSTOMER(cusSSN)"},false,true);
 
   }
   
   public static void addSomeBooking(Connection con) throws SQLException
   {

	   database_methods.addBooking(con, new String[]{"Solgaarden", "0101990101", "1-jan-2016", "8-jan-2016", "5", "8", "0","1236456985692365" ,"false", "CARD"});   
	   database_methods.addBooking(con, new String[]{"Bjoernsgaard", "1203430333", "3-jan-2016", "9-jan-2016", "3", "7", "0","1596326589652354" ,"false", "CARD"});   
	   database_methods.addBooking(con, new String[]{"Nordgaarden", "1907884444", "1-jan-2016", "8-jan-2016", "3", "8", "0","1226986645736526" ,"false", "CARD"});   
	   database_methods.addBooking(con, new String[]{"Bjoernsgaard", "1203430333", "22-feb-2016", "30-jan-2016", "5", "9", "0","6526456985854632" ,"false", "CARD"});   
	   database_methods.addBooking(con, new String[]{"Ulvegaarden", "0101990101", "30-jan-2016", "3-feb-2016", "5", "4", "0","6456986985692365" ,"false", "CARD"});   
	   database_methods.addBooking(con, new String[]{"Ulvegaarden", "1203430333", "1-jan-2016", "8-jan-2016", "5", "8", "0","7789656985692365" ,"false", "CARD"});   
	   database_methods.addBooking(con, new String[]{"Haraldsgaard", "1234567890", "19-jan-2016", "21-jan-2016", "4", "3", "0","9633698585692389" ,"false", "CARD"});   
	   database_methods.addBooking(con, new String[]{"Solgaarden", "1907884444", "10-jan-2016", "18-jan-2016", "5", "9", "0","6332114985692378" ,"false", "CARD"});   
	   database_methods.addBooking(con, new String[]{"Solgaarden", "1234567890", "20-jan-2016", "23-jan-2016", "4", "4", "0","9663556985692396" ,"false", "CARD"});  
	   
   }
   
   public static void addSomeEmployee(Connection con) throws SQLException
   {
	   database_methods.addEmployee(con, new String[]{"106", "Twilight", "Sparkle", "1234567890", "12-dec-1992", "12-dec-2016", "1000", "Fredesgade 11", "Horsens", "8700", "metalTS@gmail.com", "F","0", "PT", "RECP"});
	   database_methods.addEmployee(con, new String[]{"107", "Sunset", "Shimmer", "1599633215", "11-dec-1990", "02-nov-2016", "1000", "Filly Street 11", "Fillydephlia", "9620", "SunnySun62@viking.dk", "F","0", "PT", "MNGR"});
	   
   }
   
   public static void addSomeBranch(Connection con) throws SQLException
   {
	   database_methods.addToTable(con, "BRANCH", new String[]{"1000", "Herningvej 22", "7500", "Holstebro", "1"});
	   database_methods.addToTable(con, "BRANCH", new String[]{"1001", "Horsensvej 88", "6500", "Horsens", "1"});
   }
   
   public static void addSomeHouse(Connection con) throws SQLException
   {
	   database_methods.addToTable(con, "HOUSE", new String[]{"Solgaarden", "6", "249.99", "1000"});
	   database_methods.addToTable(con, "HOUSE", new String[]{"Bjoernsgaard", "8", "249.99", "1000"});
	   database_methods.addToTable(con, "HOUSE", new String[]{"Ulvegaarden", "10", "349.99", "1001"});
	   database_methods.addToTable(con, "HOUSE", new String[]{"Nordgaarden", "5", "229.99", "1000"});
	   database_methods.addToTable(con, "HOUSE", new String[]{"Haraldsgaard", "5", "229.99", "1000"});
   }
   
   public static void addSomeCustomer(Connection con) throws SQLException
   {
	   database_methods.addToTable(con, "CUSTOMER", new String[]{"0101990101","Bob","Johnson","bob@gmail.com"});
	   database_methods.addToTable(con, "CUSTOMER", new String[]{"1203430333","Steve","Smith","steve@gmail.com"});
	   database_methods.addToTable(con, "CUSTOMER", new String[]{"1907884444","Bill","Ziegler","bill@gmail.com"});
	   database_methods.addToTable(con, "CUSTOMER", new String[]{"1234567890","Twilight","Sparkle","metalTS@gmail.com"});
   }
   
   public static void makeSomeUpdate(Connection con) throws SQLException
   {
	   database_methods.updateAtribute(con, "employee", "fname", "Twilight", "Jessica");
	   database_methods.updateAtribute(con, "booking", "booking_id", "amount_to_pay", "0", "3000");
	   
   }
   
}
