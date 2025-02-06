package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Entities.Model;
import dao.ModelInterface;
import jdbcConnection.dbconnection;

public class daoimplModel implements ModelInterface{
//DAO = Database Access Object
	
	static Scanner sc = new Scanner(System.in);
	
	static Connection con = null;
	Statement st;	//For one statement
	ResultSet rs;	//For group of statement
	PreparedStatement pst;
	
	public daoimplModel() {
		try {
			con = dbconnection.getConnect();
			System.out.println("Connection Done...");
			st= con.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void addmodel(Model m) {
		// TODO Auto-generated method stub
		
		/*Prepared Statement : 
		 * 			when we want to execute query once and enter values at the run time
		 * 			then we use prepared statement
		 * 			It is used when we use dynamic query */
		
		try {
			pst = con.prepareStatement("insert into model values(?,?,?,?)");
			
			pst.setInt(1, m.getMid());
			pst.setString(2, m.getMname());
			pst.setFloat(3, m.getCost());
			pst.setInt(4, 0);	
			
			int noRAdd = pst.executeUpdate();
			
			if(noRAdd>0) {
				System.out.println("Model Added Succesfully..");
			}
			else 
			{
				System.out.println("Error occurs ");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deletemodel(int mid) {
		// TODO Auto-generated method stub
		/*If we want delete using model_name
		executeUpdate
		delete from model where model_name='Bajaj'
		st.executeUpdate("delete from model where model_name='"+name+"'");
		' " " ' if we want concat string */
		
		
		try {
			
			int noOfdelMo = st.executeUpdate("delete from model where model_id = "+mid);
			
			if(noOfdelMo>0) {
				System.out.println("Model "+mid+" is deleted");
			}
			else {
				System.out.println("Model id does not exists");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updatemodelcost(int mid) {
		// TODO Auto-generated method stub
		try {
			Model m = new Model();
			m = searchModelById(mid);
			
			if(m!=null) {
				System.out.println(m);
				
				System.out.println("Enter new cost : ");
				float new_cost = sc.nextFloat();
				
				pst = con.prepareStatement("update model set cost = ? where model_id = ?");
				pst.setFloat(1, new_cost);
				pst.setInt(2, mid);
				
				int noRaffected = pst.executeUpdate();
				
				if(noRaffected>0) {
					System.out.println("Model cost updated");
					System.out.println(searchModelById(mid));
				}
				else {
					System.out.println("Error in updating cost");
				}
			}
			else {
				System.out.println("Model not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void displayAllmodels() {
		// TODO Auto-generated method stub
		
		try {
			rs = st.executeQuery("select * from model");
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Model searchModelById(int mid) {
		// TODO Auto-generated method stub
		
		try {
			pst = con.prepareStatement("select * from model where model_id = ?");
			pst.setInt(1, mid);
			boolean status = false;
			rs = pst.executeQuery();
			
			while(rs.next()) {
				status = true;
//				System.out.println("Model Id : "+rs.getInt(1));
//				System.out.println("Model Name : "+rs.getString(2));
//				System.out.println("Model Cost : "+rs.getFloat(3));
//				System.out.println("Model COunt : "+rs.getInt(4));
				
				//If we want to return the model
				Model m = new Model();
				
				m.setMid(rs.getInt(1));
				m.setMname(rs.getString(2));
				m.setCost(rs.getFloat(3));
				m.setCount(rs.getInt(4));
				
				return m;
			}
			
			if(!status) {
				System.out.println("Model not found;....");
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
