package updatedatm;

import java.sql.*;
import java.util.Scanner;

public class AccountInformation {
	private Scanner s;

	public void generalInformation() throws Exception
	{
		s = new Scanner(System.in);
		System.out.println("Enter the account number:");
		int account_no=s.nextInt();
		String query="select * from account where account_no="+account_no+"";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=ConnectionManager.jdbcConnection();
		Statement st =con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		System.out.println("\t  !!   Account Information  !!\nAccount Number: ");
		int accno=rs.getInt("account_no");
		System.out.print(accno);
		String accinfo="\nFirst Name :  "+rs.getString("first_name")+"  "+rs.getString("last_name")+"\nAddress:   "+rs.getString("address");
		System.out.print(accinfo);
		String otherinfo="\nMarital Status:  "+rs.getString("marital_status")+"\nProfession:  "+rs.getString("profession")+"\nCitizenship:  "+rs.getString("citizenship")+"\nContact No:  "+rs.getLong("contact_no")+"\n";
		System.out.print(otherinfo);
	}

}
