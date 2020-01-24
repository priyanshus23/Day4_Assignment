import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Day4assignment {
	Connection con;
	PreparedStatement pre;
	Statement stat;
	ResultSet res;
	int idCustomer;
	String CustomerFirstName;
	String CustomerLastName;
	String CustomerAddress;
	String CustomeeEAddress;
	static Scanner sc=new Scanner(System.in);
	
	public Day4assignment() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DatabaseForCustomer","priyanshu","Pashu@1998");
			System.out.println("Connected!!");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void AddDetails() {
		
		try {
		
		
		
		System.out.println("Enter the Customer id");
		idCustomer=sc.nextInt();
		System.out.println("Enter the Customer First Name");
		CustomerFirstName=sc.next();
		System.out.println("Enter the Customer Last Name");
		CustomerLastName=sc.next();
		System.out.println("Enter the Customer Address");
		CustomerAddress=sc.next();
		System.out.println("Enter the Customer Email-Address");
		CustomeeEAddress=sc.next();
		
		pre=con.prepareStatement("insert into Customer values(?,?,?,?,?)");
		pre.setInt(1, idCustomer);
		pre.setString(2, CustomerFirstName);
		pre.setString(3, CustomerLastName);
		pre.setString(4, CustomerAddress);
		pre.setString(5, CustomeeEAddress);
		
		int ch=pre.executeUpdate();
		if(ch>0) {
			System.out.println("Customer Details have been added");
		}
		else {
			System.out.println("Customer Details could not be added");
		}
		
		pre.close();
		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}	
		
		public void updatedetails(){
			try
			
			{	
				System.out.println("Enter name to update the name\n");
				
				String name=sc.next();
				System.out.println("Enter the customer id for which the name to be updated\n");
				
				int id=sc.nextInt();
				
				pre=con.prepareStatement("update Customer set CustomerFirstName=? where idCustomer=?");
				pre.setString(1, name);
				pre.setInt(2,id );
				int re=pre.executeUpdate();
				if(re>0) {
					System.out.println("Name updated for customerid");
				}
				else {
					System.out.println("Name is not updated..");
				}
				pre.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		public void deleteCustomer() {
			try {
				
				System.out.println("Enter the customer_id to delete the record");
				int c_id=sc.nextInt();
				pre=con.prepareStatement("delete from Customer where idCustomer=?");
				pre.setInt(1,c_id);
				int re=pre.executeUpdate();
				if(re>0) {
					System.out.println("Record deleted");
					
				}
				else {
					System.out.println("Record not deleted");		
					}
				pre.close();
				}
		
			
			catch(Exception e) {
				e.printStackTrace();
			}
		
}
		
	public void printdetails() {
			
			try {
				stat=con.createStatement();
				res=stat.executeQuery("select * from Customer");
				
				while(res.next()) {
					System.out.println("Customer_id: "+res.getInt("idCustomer"));
					System.out.println("Customer_fname: "+res.getString("CustomerFirstName"));
					System.out.println("Customer_lname: "+res.getString("CustomerLastName"));
					System.out.println("Customer_address: "+res.getString("CustomerAddress"));
					System.out.println("Customer_email: "+res.getString("CustomeeEAddress"));
					System.out.println("");
	
				}
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		


	public static void main(String[] args) {
		
		
		
		Day4assignment dbms=new Day4assignment();
		dbms.printdetails();
		System.out.println("enter choiceY/N");
		
		String ch=sc.next();
		
		while(ch.equals("Y")) {
			System.out.println(" Insert 1: To ADD customer details.\nInsert 2: To Edit customer Name based on id.\nInsert 3: To Remove the customer.\nInsert 4: TO Print the Customer Details.\nInsert 5: To exit from the app."
					);
			
			int inp=sc.nextInt();
			if(inp==1) {
				dbms.AddDetails();
			}
			else if(inp==2) {
				dbms.updatedetails();
			}
			else if(inp==3) {
				dbms.deleteCustomer();
			}
			else if(inp==4) {
				dbms.printdetails();
			}
			if(inp==5) {
				System.exit(0);
			}

			
			
			System.out.println("Do you want to continueY/N");
			ch=sc.next();
			
			
		}
		
	}

}