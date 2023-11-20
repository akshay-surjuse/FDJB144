package com.braindatawire.collegemanagement.client;

import java.sql.SQLException;
import java.util.Scanner;

import com.braindatawire.collegemanagement.serviceimpl.Karvenagar;

public class Test 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Karvenagar k = new Karvenagar();
	
		while(true)
		{
			System.out.println("***College Management Project*** \n" +
			                   "1.Add Course \n" +
			                   "2.View Course \n" +
			                   "3.Add Faculty \n" +
			                   "4.View Faculty \n" +
			                   "5.Add Batch \n" +
			                   "6.View Batch \n" +
			                   "7.Add Student \n" +
			                   "8.View Student \n" +
			                   "9.Exit \n");
			Scanner sc = new Scanner(System.in);
			int key = sc.nextInt();
			switch (key) 
			{
				case 1:
					k.addCourse();
					break;
					
				case 2:
					k.viewCourse();
					break;
					
				case 3:
					k.addFaculty();
					break;
					
				case 4:
					k.viewFaculty();
					break;
					
				case 5:
					k.addBatch();
					break;
					
				case 6:
					k.viewBatch();
					break;
					
				case 7:
					k.addStudent();
					break;
					
				case 8:
					k.viewStudent();
					break;
					
				case 9:
					System.exit(1);
					break;
			
				default:
					System.out.println("Incorrect Selection" +
				                       "Select From Given Options");
					break;
			}
		}
	}
}
