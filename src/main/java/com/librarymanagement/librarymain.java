package com.librarymanagement;
import java.util.*;


public class librarymain {

	public static void main(String[] args) throws Exception{
		
		Scanner bs=new Scanner(System.in);
		//For taking the input from the user
		librarydao dao=new librarydao();
		//creating an object for customer class
		customer c1=new customer();
		//creating the object of customer class
		System.out.println("\t\t\t\t$$$$$$$$$$$$Welcome to Library$$$$$$$$$$$$$$$");
		System.out.println("Press 1 for resgistration \n press2 for Login");
		int op=bs.nextInt();
		
		switch(op) {
		
		case 1->{
			
			System.out.println("enter customer id");
	        int cid=bs.nextInt();
	        bs.nextLine();
			System.out.println("enter customer name");
			String cname=bs.nextLine();
			System.out.println("enter your phone number");
	        int ccontact=bs.nextInt();
			System.out.println("enter customer pin");
	        int cpin=bs.nextInt();
	        

	        
	        c1.cusid=cid;
	        c1.cusname=cname;
	        c1.cuscontact=ccontact;
	        c1.cuspin=cpin;



	        
	        //calling db connection method
	        
	        dao.dbconnection();
	        int res=dao.registercustomer(c1);
	        //we are giving details into database
	        if(res==1) {
	        	//if res is 1 then it is successful else it prints as something went wrong
	        	
	        	System.out.println("acc creation successful");
	        }
	        else {
	        	
	        	System.out.println("something went wrong");

	        }
		}
		case 2->{
			//taking inputs
			System.out.println("\t\t\t\tWelcome to login page");
			System.out.println("Enter username");
			bs.nextLine();
			String uname=bs.nextLine();
			System.out.println("Enter password");
			int pwd=bs.nextInt();
			//we are creating a connection to database
			dao.dbconnection();
	        int res=dao.login(uname,pwd);
	        //login methods starts here based on below condition we get the output
	        if(res==0) {
	        	
	        	System.out.println("username/password is incorrect");
	             }
	        else if(res==-1) {
	        	
	        	System.out.println("unable to find the details");

	            }
            else{
	        	
	        	System.out.println("Login successful");
	        	System.out.println("press 1 for adding books\n press 2 for change password \n press 3 for delete account \n press 4 for delete books");
	        	int ops=bs.nextInt();
		switch(ops) {
    	case 1->{
    	//input of deposit
    	System.out.println("Number of books you are depositing");
    	int nob=bs.nextInt();
    String reso=dao.bookadd(nob,res);
    	System.out.println("Deposit successful\n"+reso);
    	//now try it 
    	}
    	case 2->{
			System.out.println("Enter current password");
			int currentpwd=bs.nextInt();
			System.out.println("Enter new password");
			int newpwd=bs.nextInt();
			
			int status=dao.changepwd(currentpwd, newpwd, res);
			if(status==1) {
				System.out.println("Password changed...");
			}
			else {
				System.out.println("Something went wrong");
			}
			
	}
    	case 3->{
			System.out.println("Enter password to delete");
			int pass=bs.nextInt();
			int status=dao.deleteAccount(pass, res);
			if(status==1) {
				System.out.println("Your account is deleted\n byeeeeeeeeeeeeeee");
			}
			else {
				System.out.println("Something went wrong");
			}		
	}
    	case 4->{
    		System.out.println("Enter no of books to delete");
    		int book=bs.nextInt();
			System.out.println("Confirm your password");
			int confmpwd=bs.nextInt();
    		String reso=dao.bookdelete(book, confmpwd,res);
    		
    		System.out.println("Deletion completed!!!\n "+reso);
    		
    	}
    		
    	}


bs.close();
            }
		}
}
	}

}


