package com.braindatawire.collegemanagement.servicei;

import java.sql.SQLException;
import java.util.List;

import com.braindatawire.collegemanagement.model.Batch;
import com.braindatawire.collegemanagement.model.Course;
import com.braindatawire.collegemanagement.model.Faculty;

public interface Cjc 
{
	public void addCourse() throws ClassNotFoundException, SQLException;
	public List<Course> viewCourse() throws ClassNotFoundException, SQLException;
	
	public void addFaculty() throws ClassNotFoundException, SQLException;
	public List<Faculty> viewFaculty() throws ClassNotFoundException, SQLException;
	
	public void addBatch() throws ClassNotFoundException, SQLException;
	public List<Batch> viewBatch() throws ClassNotFoundException, SQLException;
	
	public void addStudent() throws ClassNotFoundException, SQLException;
	public void viewStudent() throws ClassNotFoundException, SQLException;
}
