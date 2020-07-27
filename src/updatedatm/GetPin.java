package updatedatm;
import java.sql.*;
import java.util.Scanner;

public class GetPin {
	private Scanner ac;

	void getPinNumber() throws Exception
	{
		ac = new Scanner(System.in);
		System.out.println("Enter your Account Number:");
		int account=ac.nextInt();
		String query="select pin from account where account_no="+account+"";
		Connection con=ConnectionManager.jdbcConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int pin_no=rs.getInt("pin");
		System.out.println("Your pin Number is :"+pin_no);
		
	}
}
