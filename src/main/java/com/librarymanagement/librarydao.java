package com.librarymanagement;
import java.sql.*;



public class librarydao {
	
	Connection con=null;
	public void dbconnection()throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryprob","root","270819");
	} 
	

	
	
	
	
	
	public int registercustomer(customer c1) throws Exception {
		//registering the customer in database
		
		String query="insert into customer values(?,?,?,?,?,?)";
		PreparedStatement bs=con.prepareStatement(query);
		bs.setInt(1,c1.cusid);
		bs.setString(2, c1.cusname);
		bs.setInt(3, c1.cuscontact);
		bs.setInt(4, c1.cuspin);
		bs.setInt(5,c1.cusbook);
		bs.setInt(6,c1.cusbk);
		int res=bs.executeUpdate();
		return res;
	}
	
	public int login(String uname,int pwd) throws Exception {
		String query="select * from customer where cusname='"+uname+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		if(rs.next()) {
			
			int password=rs.getInt(4);
			if(password==pwd) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}
		}
			else {
				return -1;
			}
		}
	public int changepwd(int currentpwd,int newpwd, int cusid)throws Exception{
		//fetching user details based on customer id
		String 	query2="select  * from customer where cusid="+cusid;
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(query2);
		rs.next();
		//Giving condition for confirming the existing password
		if(currentpwd==rs.getInt(4)) {
			//Here we are Updating the new password in database
			String query="update customer set cuspin="+newpwd+" where cusid="+cusid;
			
				PreparedStatement pst=con.prepareStatement(query);
				pst.executeUpdate();
				return 1;
		}
		else {
			return 0;
		}
	}
	
	public int deleteAccount(int pwd, int cusid)throws Exception{
		//fetching user details based on customer id
		String 	query2="select  * from customer where cusid="+cusid;
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(query2);
		rs.next();
		//confirming password
		if(pwd==rs.getInt(4)) {
		
			//To delete the account of the customer
			String query="delete from customer where cusid="+cusid;
			
			PreparedStatement pst=con.prepareStatement(query);
				pst.executeUpdate();
			return 1;
		}
		else {
			return 0;
		}
	}
	public String bookadd(int nob,int customerid) throws Exception {
		//fetching user details based on customer id
		String query2="select * from customer where cusid="+customerid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query2);
        rs.next();
        int amount;
    	
        //Updating the amount with balance
        amount=50*nob;
     
       
        //We are storing the updated amount
		String query="update customer set cusbk ="+amount+" , cusbook ="+nob+" where cusid="+customerid;
		PreparedStatement pst=con.prepareStatement(query);
		//we are returing the updated amount
		int up=pst.executeUpdate();
		
		if(up==1) {
		String res="Num of books are "+nob+"and its price is"+amount;
		return res;
		}
		else {
			return "Something went wrong";
		}
	}
	
	public String bookdelete(int book,int pwd,int cusid)throws Exception {
	
		String 	query2="select  * from customer where cusid="+cusid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query2);
		rs.next();
		
		//we are extracting available amount
		int availbook=rs.getInt(5);
		int availprice=rs.getInt(6);
		if(pwd==rs.getInt(4))
		{
			//if available amount is greater than  required amount then only withdraw happens
			if(book<=availbook) {
				
				availbook-=book;
				availprice-=book*50;
				String query="update customer set cusbook="+availbook+", cusbk ="+availprice+" where cusid="+cusid;
				
				PreparedStatement pst=con.prepareStatement(query);
				int u=pst.executeUpdate();
				if(u==1) {
					String res="Num of books remaining are "+availbook+"\n and its price is:"+availprice;
					return res;
					}
					else {
						return "Something went wrong";
					}
				
			
		}
		
		
	}
		return "awwwww........deposit some books to delete";
	}
}

