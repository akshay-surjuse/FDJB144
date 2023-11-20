package com.braindatawire.collegemanagement.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.braindatawire.collegemanagement.config.JdbcConnection;
import com.braindatawire.collegemanagement.model.Batch;
import com.braindatawire.collegemanagement.model.Course;
import com.braindatawire.collegemanagement.model.Faculty;
import com.braindatawire.collegemanagement.model.Student;
import com.braindatawire.collegemanagement.servicei.Cjc;

public class Karvenagar implements Cjc
{

	@Override
	public void addCourse() throws ClassNotFoundException, SQLException 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course Id");
		int cid = sc.nextInt();
		
		System.out.println("Enter Course Name");
		String cname = sc.nextLine();
		
		Course c = new Course();
		c.setcId(cid);
		c.setcName(cname);
		
		Connection con = JdbcConnection.mkConnection();
		PreparedStatement ps = con.prepareStatement("insert into Course values(?,?)");
		ps.setInt(1, c.getcId());
		ps.setString(2, c.getcName());
		
		ps.executeUpdate();
		ps.close();
		
		con.close();
		
		System.out.println("Course added Successfully");
	}

	@Override
	public List<Course> viewCourse() throws ClassNotFoundException, SQLException 
	{
		List<Course> cli = new ArrayList<>();
		
		Connection con = JdbcConnection.mkConnection();
		PreparedStatement ps = con.prepareStatement("select * from Course");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			System.out.println("Course Id  : " + rs.getInt(1));
			System.out.println("Course Name: " + rs.getString(2));
			System.out.println("********************");
			
			Course c = new Course();
			c.setcId(rs.getInt(1));
			c.setcName(rs.getString(2));
			
			cli.add(c);
		}
		con.close();
		return cli;
	}

	@Override
	public void addFaculty() throws ClassNotFoundException, SQLException 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Faculty Id");
		int fid = sc.nextInt();
		
		sc.nextLine();
		
		System.out.println("Enter Faculty Name");
		String fname = sc.nextLine();
	
		Faculty f = new Faculty();
		
		f.setfId(fid);
		f.setfName(fname);
		
		
		List<Course> cli = viewCourse();
		
		System.out.println("Enter Faculty Course Id");
		int fcid = sc.nextInt();
		
		for(Course c : cli)
		{
			if(c.getcId()==fcid)
			{
				f.setCourse(c);
			}
		}
		
		Connection con = JdbcConnection.mkConnection();
		PreparedStatement ps = con.prepareStatement("insert into Faculty values(?,?,?,?)");
		ps.setInt(1, f.getfId());
		ps.setString(2, f.getfName());
		ps.setInt(3, f.getCourse().getcId());
		ps.setString(4, f.getCourse().getcName());
		
		ps.executeUpdate();
		ps.close();
		
		con.close();
	}

	@Override
	public List<Faculty> viewFaculty() throws ClassNotFoundException, SQLException 
	{
		List<Faculty> fli = new ArrayList<>();
		
		Connection con = JdbcConnection.mkConnection();
		PreparedStatement ps = con.prepareStatement("select * from Faculty");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			System.out.println("Faculty Id         : " + rs.getInt(1));
			System.out.println("Faculty Name       : " + rs.getString(2));
			System.out.println("Faculty Course Id  : " + rs.getInt(3));
			System.out.println("Faculty Course Name: " + rs.getString(4));
			System.out.println("**********************************");
			
			Course c = new Course();
			c.setcId(rs.getInt(3));
			c.setcName(rs.getString(4));
			
			Faculty f = new Faculty();
			f.setfId(rs.getInt(1));
			f.setfName(rs.getString(2));
			f.setCourse(c);
			
			fli.add(f);
		}
		con.close();
		return fli;
	}

	@Override
	public void addBatch() throws ClassNotFoundException, SQLException 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Batch Id");
		int bid = sc.nextInt();
		
		sc.nextLine();
		
		System.out.println("Enter Batch Name");
		String bname = sc.nextLine();
		
		Batch b = new Batch();
		b.setbId(bid);
		b.setbName(bname);
		
		List<Faculty> fli = viewFaculty();
		
		System.out.println("Enter Batch Faculty Id");
		int bfid = sc.nextInt();
		
		for(Faculty f : fli)
		{
			if(f.getfId()==bfid)
			{
				b.setFaculty(f);
			}
		}
		
		Connection con = JdbcConnection.mkConnection();
		PreparedStatement ps = con.prepareStatement("insert into Batch values(?,?,?,?,?,?)");
		ps.setInt(1, b.getbId());
		ps.setString(2, b.getbName());
		ps.setInt(3, b.getFaculty().getfId());
		ps.setString(4, b.getFaculty().getfName());
		ps.setInt(5, b.getFaculty().getCourse().getcId());
		ps.setString(6, b.getFaculty().getCourse().getcName());
		
		ps.executeUpdate();
		ps.close();
		
		con.close();
		
		System.out.println("Batch Added Successfully");
	}

	@Override
	public List<Batch> viewBatch() throws ClassNotFoundException, SQLException 
	{
		List<Batch> bli = new ArrayList<>();
		
		Connection con = JdbcConnection.mkConnection();
		PreparedStatement ps = con.prepareStatement("select * from Batch");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			System.out.println("Batch Id: " + rs.getInt(1));
			System.out.println("Batch Name: " + rs.getString(2));
			System.out.println("Batch Faculty Id: " + rs.getInt(3));
			System.out.println("Batch Faculty Name: " + rs.getString(4));
			System.out.println("Batch Faculty Course Id: " + rs.getInt(5));
			System.out.println("Batch Faculty Course Name: " + rs.getString(6));
			System.out.println("************************************");
			
			Course c = new Course();
			c.setcId(rs.getInt(5));
			c.setcName(rs.getString(6));
			
			Faculty f = new Faculty();
			f.setfId(rs.getInt(3));
			f.setfName(rs.getString(4));
			f.setCourse(c);
			
			Batch b= new Batch();
			b.setbId(rs.getInt(1));
			b.setbName(rs.getString(2));
			b.setFaculty(f);
			
			bli.add(b);
		}
		con.close();
		
		return bli;
	}

	@Override
	public void addStudent() throws ClassNotFoundException, SQLException 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Student Id");
		int sid = sc.nextInt();
		
		sc.nextLine();
		
		System.out.println("Enter Student Name");
		String sname = sc.nextLine();
		
		Student s = new Student();
		
		s.setsId(sid);
		s.setsName(sname);
		
		List<Batch> bli = viewBatch();
		
		System.out.println("Enter Student Batch Id");
		int sbid = sc.nextInt();
		
		for(Batch b : bli)
		{
			if(b.getbId()==sbid)
			{
				s.setBatch(b);
			}
		}
		
		Connection con = JdbcConnection.mkConnection();
		PreparedStatement ps = con.prepareStatement("insert into Student values(?,?,?,?,?,?,?,?)");
		ps.setInt(1, s.getsId());
		ps.setString(2, s.getsName());
		ps.setInt(3, s.getBatch().getbId());
		ps.setString(4, s.getBatch().getbName());
		ps.setInt(5, s.getBatch().getFaculty().getfId());
		ps.setString(6, s.getBatch().getFaculty().getfName());
		ps.setInt(7, s.getBatch().getFaculty().getCourse().getcId());
		ps.setString(8, s.getBatch().getFaculty().getCourse().getcName());
		
		ps.executeUpdate();
		ps.close();
		con.close();
		
		System.out.println("Student Added Successfully");
	}

	@Override
	public void viewStudent() throws ClassNotFoundException, SQLException 
	{
		Connection con = JdbcConnection.mkConnection();
		PreparedStatement ps = con.prepareStatement("select * from Student");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			System.out.println("Student Id                       : " + rs.getInt(1));
			System.out.println("Student Name                     : " + rs.getString(2));
			System.out.println("Student Batch Id                 : " + rs.getInt(3));
			System.out.println("Student Batch Name               : " + rs.getString(4));
			System.out.println("Student Batch Faculty Id         : " + rs.getInt(5));
			System.out.println("Student Batch Faculty Name       : " + rs.getString(6));
			System.out.println("Student Batch Faculty Course Id  : " + rs.getInt(7));
			System.out.println("Student Batch Faculty Course Name: " + rs.getString(8));
			System.out.println("**************************************************");
		}
	}

}
