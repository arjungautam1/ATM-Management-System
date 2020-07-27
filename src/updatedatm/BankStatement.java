package updatedatm;

import java.io.FileOutputStream;
import java.sql.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.util.Scanner;

public class BankStatement {
	private Scanner s;

	public void bankStatement()throws Exception
	{
		s = new Scanner(System.in);
		System.out.println("Enter your Account number:");
		int acc_no=s.nextInt();
	
	
		String query2="select * from account where account_no="+acc_no+"";
	    Connection con=ConnectionManager.jdbcConnection();
	    Statement st4=con.createStatement();
	    ResultSet rs4=st4.executeQuery(query2);
	    rs4.next();
	    int pin_number=rs4.getInt("pin");	

		System.out.println("Enter your pin number :");
		int pin_no1=s.nextInt();
		
		if(pin_number==pin_no1)
		{
		String query1="select * from debit_bal where account_no="+acc_no+"";
		Connection con1=ConnectionManager.jdbcConnection();
		Statement st1=con1.createStatement();
		ResultSet rs1=st1.executeQuery(query1);
		int total=0;
		System.out.println("\t\t \t!!!          Bank  Statement         !!!\t\t\n");
		System.out.println("\n\t**    Deposit Amounts History :      **\n");
		while(rs1.next())
		{
			 Timestamp t = rs1.getTimestamp("time");
			int debit=rs1.getInt("debit");
			System.out.println("\t"+debit+"  "+t);
			total=rs1.getInt("debit")+total;
		}
		System.out.println("\n\t Total Deposited Balance:    =   "+total);
		
		String query11="select * from credit_bal where account_no="+acc_no+"";
		Connection con11=ConnectionManager.jdbcConnection();
		Statement st11=con11.createStatement();
		ResultSet rs11=st11.executeQuery(query11);
		int total_credit=0;
		System.out.println("\n\t **    Withdrawn Amounts History:       **\n");
		while(rs11.next())
		{
			 Timestamp t1 = rs11.getTimestamp("time");
			int credit=rs11.getInt("credit");
			System.out.println("\t"+credit+"  "+t1);
			total_credit=rs11.getInt("credit")+total_credit;
		}
		System.out.println("\n\t Total Withdrawn Balance=     "+total_credit);
		
		int available_balance=total-total_credit;
		System.out.println("\nAvailable Bank Balance  :    "+available_balance);
		
		String query21="select * from account where account_no="+acc_no+"";
		Connection con2=ConnectionManager.jdbcConnection();
		Statement st2 =con2.createStatement();
		ResultSet rs2=st2.executeQuery(query21);
		rs2.next();
		int pin=rs2.getInt("pin");
		String query="insert into total_bal(account_no,total,pin,debit_total,credit_total) values ("+acc_no+","+available_balance+","+pin+","+total+","+total_credit+")";
		Connection con111=ConnectionManager.jdbcConnection();
		Statement st=con111.createStatement();
		st.executeUpdate(query);
		//pdf
		
		String file_name="/home/laserarjun/statement.pdf";
		Document document=new Document();
		
		PdfWriter.getInstance(document, new FileOutputStream(file_name));
		document.open();
		
		
		document.add(Image.getInstance("/home/laserarjun/Documents/Logo.png"));
		document.add(new Paragraph("                                                                                                                 Date: "+rs4.getString("time")));
		Paragraph para=new Paragraph("Account Name  :  "+rs4.getString("first_name")+"   "+rs4.getString("last_name"));
    	document.add(para);
    	 para=new Paragraph("Account Number  :  "+rs4.getString("account_no"));

    	 
    	 document.add(para);
    	 
		
		Connection conn1=ConnectionManager.jdbcConnection();
		PreparedStatement ps1=null;
		
		ps1=conn1.prepareStatement(query1);
		rs1=ps1.executeQuery();
		document.add(new Paragraph(" "));
   	 document .add(new Paragraph("Currency   :   NPR"));
   	 document.add(new Paragraph("Accrued Interest : 0.45 "));
		document.add(new Paragraph("  "));	//for space 
		document.add(new Paragraph("                Deposited Balance  : "));
		document.add(new Paragraph(" "));
		PdfPTable table1=new PdfPTable(3);
		PdfPCell c11 =new PdfPCell(new Phrase("S.N."));
		table1.addCell(c11);
		
		c11=new PdfPCell(new Phrase("Debit Balance  "));
		table1.addCell(c11);
		
		c11=new PdfPCell(new Phrase("Date and Time of Transaction"));
		table1.addCell(c11);
		
		table1.setHeaderRows(1);
		while(rs1.next())
		{
			table1.addCell(rs1.getString("sno"));
			table1.addCell(rs1.getString("debit"));
			table1.addCell(rs1.getString("time"));
			
		}
		document.add(table1);
		
		
		document.add(new Paragraph(" "));
		Connection conn2=ConnectionManager.jdbcConnection();
		PreparedStatement ps2=null;
		
		ps2=conn2.prepareStatement(query11);
		rs11=ps2.executeQuery();
		document.add(new Paragraph("               Withdrawn Balance :"));
		document.add(new Paragraph(" "));
		PdfPTable table2=new PdfPTable(3);
		PdfPCell  c2=new PdfPCell(new Phrase("S.N."));
		table2.addCell(c2);
		
		c2=new PdfPCell(new Phrase(" Credit Balance "));
		table2.addCell(c2);
		
		c2=new PdfPCell(new Phrase("Date and Time of Transaction"));
		table2.addCell(c2);
		
		table2.setHeaderRows(1);
		while(rs11.next())
		{
			table2.addCell(rs11.getString("sno"));
			table2.addCell(rs11.getString("credit"));
			table2.addCell(rs11.getString("time"));
			
		}
		document.add(table2);
		
		document.add(new Paragraph(" "));
		document.add(new Paragraph("Total Deposited balance : "+total));
		document.add(new Paragraph("\tTotal Withdrawn Balance : "+total_credit));
		document.add(new Paragraph("                                                                                         Available Bank Balance : "+available_balance));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(new Paragraph("                                           !!    Thanks for using our banking Services      !!"));
		document.close();
		}
		
			else
			{
				System.out.println("\n\t **Alert** !! You have entered incorrect password.\n\t\t**  Please visit the bank to get new code **");
				
			}
		}


}
