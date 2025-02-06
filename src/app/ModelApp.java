package app;
import java.util.*;

import Entities.Model;
import daoimpl.daoimplModel;

public class ModelApp {

	static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		System.out.println("1. Add Model");
		System.out.println("2. Delete Model");
		System.out.println("3. Update Model cost");
		System.out.println("4. Search Model");
		System.out.println("5. Display All Model");
		System.out.println("6. Exit Model App ");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int ch;
		char choice;
		Model m;		
		do
		{
			System.out.println("Which operation you want to perform : ");
			menu();
			ch = sc.nextInt();
			
			daoimplModel mint = new daoimplModel();
			
			switch(ch) {
			
			case 1:
				m = new Model();
		
				System.out.println("Enter model id : ");
				m.setMid(sc.nextInt());
				System.out.println("Enter model name : ");
				m.setMname(sc.next());
				System.out.println("Enter model cost : ");
				m.setCost(sc.nextFloat());	
				
				mint.addmodel(m);
				break;
				
			case 2:
				System.out.println("Enter model id : ");
				int mid = sc.nextInt();
				mint.deletemodel(mid);
				break;
				
			case 3:
				System.out.println("Enter model id : ");
				mid = sc.nextInt();
				mint.updatemodelcost(mid);
				break;
				
			case 4:
				System.out.println("Enter model id : ");
				mid = sc.nextInt();
				mint.searchModelById(mid);
				break;
				
			case 5:
				mint.displayAllmodels();
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
			
		}while(choice=='Y'|| choice=='y');
		
		System.out.println("------- You have exit Model app -------");
		
		sc.close();	
	}

}
