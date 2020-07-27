package updatedatm;
import java.sql.*;
import java.util.Scanner;

public class Deposit {
	private Scanner sc1;
	void depositAccount()  throws Exception
	{
		try {
		sc1=new Scanner(System.in);
		System.out.println("Enter your account number:");
		int accno=sc1.nextInt();
		System.out.print("Enter the amount to deposit in bank  : ");
		int dep=sc1.nextInt();
		String query1="select * from account where account_no="+accno+"";
		Connection con1=ConnectionManager.jdbcConnection();
		Statement st1=con1.createStatement();
		ResultSet rs1=st1.executeQuery(query1);
		rs1.next();
		int pin_no=rs1.getInt("pin");
		

	Timestamp t = rs1.getTimestamp("time");
		System.out.print(" \n \t**  Receipt ** ::  "     +dep+      " has been deposited into your Account.     "+t);
		
		String query111="insert into  bankstat(account_no,debit,pin) values ("+accno+","+dep+","+pin_no+")";
		Connection con111=ConnectionManager.jdbcConnection();
		Statement st111=con111.createStatement();
		st111.executeUpdate(query111);
		
		String query1111="insert into  debit_bal(account_no,debit,pin) values ("+accno+","+dep+","+pin_no+")";
		Connection con1111=ConnectionManager.jdbcConnection();
		Statement st1111=con1111.createStatement();
		st1111.executeUpdate(query1111);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

}
