package updatedatm;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
public class CreateAccount {
	private Scanner sc;
	public void method() throws Exception
	{
		try {	
		sc=new Scanner(System.in);
Random account_no=new Random();
	int  acc = 0;
	for(int counter=1;counter <1000;counter++)
	acc=account_no.nextInt(10000000);
   System.out.println("Your account number is  :"+acc);
	System.out.println("Enter the first Name:");
	String fn=sc.next();
	System.out.println("Enter the last name:");
	String ln=sc.next();
	System.out.println("Enpter the address:");
	String add=sc.next();
	System.out.println("Enter the marital status:");
	String mari=sc.next();
	System.out.println("Enter the profession:");
	String prof=sc.next();
	System.out.println("Enter citizenship");
	String citizen=sc.next();
	System.out.println("Enter contact No:");
	long contact=sc.nextLong();
	Random pin=new Random();
	int pin_no=0;
	for(int i=0;i<1000;i++)
	pin_no=pin.nextInt(10000);
	String query="insert into account(account_no,first_name,last_name,address,marital_status,profession,citizenship,contact_no,pin)values("+acc+",'"+fn+"','"+ln+"','"+add+"','"+mari+"','"+prof+"','"+citizen+"',"+contact+","+pin_no+")";
	Connection con=ConnectionManager.jdbcConnection();
	Statement st=con.createStatement();
	st.executeUpdate(query);
	st.close();
	con.close();
	System.out.println("*****	\t Your Account Has Been Created.\t      ****");
	System.out.println("\n ***Note :\t Please visit the bank to get the ATM pin number.***");
		}
		catch(Exception e)
		{
			System.out.println("\n\t  **  Seems  you haven't inputted your information .\n\tPlease Again Create your account. **");
		}
	}
}