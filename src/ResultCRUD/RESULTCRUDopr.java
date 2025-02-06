package ResultCRUD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import jdbcConnection.dbconnection;

public class RESULTCRUDopr {
	
	static Scanner sc = new Scanner(System.in);
	static Connection con;
	Statement st;
	ResultSet rs;
	
	public RESULTCRUDopr(){		
		try {
			con = dbconnection.getConnect();
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			//Direct result set madhun query constructor madhun pass keli
			
			rs = st.executeQuery("select * from Model");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void menu() {
		System.out.println("1. Add Model");
		System.out.println("2. Delete Model");
		System.out.println("3. Update Model cost");
		System.out.println("4. Display All Model");
		System.out.println("5. Display All Model in reverse order");
		System.out.println("6. Exit Model App ");
	}
	
	public void displayModel() {
		try {
			rs.beforeFirst();

			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayInReverse() {
		try {
			rs.afterLast();
			while(rs.previous()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addModels() {
		System.out.println("Enter model Information");
		System.out.println("ID : ");
		int mid = sc.nextInt();
		System.out.println("Name : ");
		String name = sc.next();
		System.out.println("Cost : ");
		float cost = sc.nextFloat();
		
		try {
			
			rs.moveToInsertRow();
			rs.updateInt(1, mid);
			rs.updateString(2, name);
			rs.updateFloat(3, cost);
			rs.updateInt(4, 0);
			
			rs.insertRow();
			
			System.out.println("Model added Succesfully..");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteModel() {
		System.out.println("Enter Model ID : ");
		int mid = sc.nextInt();
		
		Boolean isfound = false;
		
		try {
			
			rs.beforeFirst();
			
			while(rs.next()) {
				if(rs.getInt(1)==mid) {
					isfound = true;
					
					rs.deleteRow();
					System.out.println("Model deleted Succesfully ");
					break;
				}
			}			
			
			if(!isfound)	System.out.println("Model id does not exists");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateModel() {
		System.out.println("Enter Model ID : ");
		int mid = sc.nextInt();
		Boolean isFound = false;
		
		try {
			rs.beforeFirst();
			
			while(rs.next()) {
			
				if(rs.getInt(1)==mid) {
				isFound = true;
				
				System.out.println("Old Cost : "+rs.getFloat(3));
				
				System.out.println("Enter new Cost : ");
				float cost = sc.nextFloat();
				
				rs.updateFloat(3, cost);
				rs.updateRow();
				
				System.out.println("Updated model is : ");
				
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
				}
			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(!isFound)	System.out.println("Model ID does not exists ");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RESULTCRUDopr obj = new RESULTCRUDopr();
		
		int ch;
		char choice;
		
		do {
			menu();
			System.out.println("Which operation you want to perform : ");
			ch = sc.nextInt();
			
			switch(ch) {
				//	Add,delete, update, view , view in reverse, exit
				
				case 1:
						obj.addModels();
						break;
			
				case 2:
						obj.deleteModel();
						break;
				
				case 3:
						obj.updateModel();
						break;
			
				case 4:
						obj.displayModel();
						break;
						
				case 5:
						obj.displayInReverse();
						break;
						
				case 6:
					System.out.println("-- You have exit app --");
					System.exit(0);
					break;
					
				default:
					System.out.println("Entered Option is not available");
			}
			
			System.out.println("Do you want to continue [Y/N] : ");
			choice = sc.next().charAt(0);
			
		}while(choice=='Y'||choice=='y');
		
		System.out.println("------- You have exit Model app -------");
		
		sc.close();
	}

}
