package FunctionCall;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

import jdbcConnection.dbconnection;

public class Function1 {
	
	static Scanner sc = new Scanner(System.in);
	static Connection con;
	Statement st;
	ResultSet rs;
	static PreparedStatement pst;
	static CallableStatement cst;
	
	public Function1(){
		con = dbconnection.getConnect();
	}
	
	public void cstOfM() {
		String str;
		
		System.out.println("Enter Model Name : ");	
		str = sc.next();
		
		try {
			cst = con.prepareCall("{? = call costOfModel(?)}");	
			cst.registerOutParameter(1, Types.FLOAT);
			cst.setString(2, str);
			
			cst.execute();
			
			System.out.println("Cost of Model : "+str+" is "+cst.getFloat(1));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void StoreProcedure() {
		System.out.println("Enter Budget : ");
		int budget = sc.nextInt();
		
		try {
			cst = con.prepareCall("{call ModelCost(?)}");
			cst.setInt(1, budget);
			
			Boolean isFound = false;
			Boolean status = cst.execute();
			//model_id, model_name, cost
			
			if(status) {
				rs = cst.getResultSet();
				while(rs.next()) {
					isFound = true;
					
					System.out.println(" "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3));
				}
			}
			
			if(!isFound) {
				System.out.println("Model not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Function1 fn = new Function1();
		//fn.cstOfM();
		fn.StoreProcedure();
	}
}
