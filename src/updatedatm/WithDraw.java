package updatedatm;

import java.sql.*;
import java.util.Scanner;

public class WithDraw {
	private Scanner s;

	public void withDraw() throws Exception
{
		try {
		s = new Scanner(System.in);
		System.out.println("Enter your Account number:");
		int acc_no=s.nextInt();
		
	
		String query1="select * from account where account_no="+acc_no+"";
	    Connection con=ConnectionManager.jdbcConnection();
	    Statement st4=con.createStatement();
	    ResultSet rs4=st4.executeQuery(query1);
	    rs4.next();
	    int pin_number=rs4.getInt("pin");	

		System.out.println("Enter your pin number :");
		int pin_no1=s.nextInt();
		if(pin_number==pin_no1)
		{System.out.println("Enter the amount to withdraw:");
		int withdraw=s.nextInt();
			String query11="select * from total_bal where account_no="+acc_no+"";
			Connection con1=ConnectionManager.jdbcConnection();
			Statement st1=con1.createStatement();
			ResultSet rs1=st1.executeQuery(query11);
			rs1.next();
			int total=rs1.getInt("total");
	         if(total>withdraw)
	 		{
	        	 String query1111="insert into  credit_bal(account_no,credit,pin) values ("+acc_no+","+withdraw+","+pin_number+")";
		     		Connection con1111=ConnectionManager.jdbcConnection();
		     		Statement st1111=con1111.createStatement();
		     		st1111.executeUpdate(query1111);
		     		Timestamp t = rs1.getTimestamp("time");
		    		System.out.println("\n\t**Receipt** :"+withdraw+"   has been deducted from your Account ."+t);
	        
	 		}
	         else {
	        	 System.out.println("***  Your requirement exceeds your bank balance. ***");
	    		
	         		}
		
		}

			else 
			{
				System.out.println("\n\t **Alert** !! You have entered incorrect please get  code from bank  **");
			}
		}
		catch(Exception e)
		{
			System.out.println("Check your balance before you try to withdraw");
		}

		
}
	
}
