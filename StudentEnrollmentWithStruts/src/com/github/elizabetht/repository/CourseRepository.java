package com.github.elizabetht.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.github.elizabetht.util.DbUtil;

public class CourseRepository 
{

	private Connection dbConnection;

	public CourseRepository() {
		dbConnection = DbUtil.getConnection();
	}

	public void save(String courseName, int courseID,int duration, String discription) {
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("insert into course(courseID,courseName,duration,discription) values (?, ?, ?, ?)");
				prepStatement.setInt(1, courseID);
				prepStatement.setString(2, courseName);
				prepStatement.setInt(3, duration);
				prepStatement.setString(4, discription);				
				

				prepStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}

	public boolean findByCourseName(String courseName) {
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("select count(*) from course where courseName = ?");
				prepStatement.setString(1, courseName);

				ResultSet result = prepStatement.executeQuery();
				if (result != null) {
					while (result.next()) {
						if (result.getInt(1) == 1) {
							return true;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean findByName(String courseName) {
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("select password from course where courseName = ?");
				prepStatement.setString(1, courseName);

				ResultSet result = prepStatement.executeQuery();
				if (result != null) {
					while (result.next()) {
						if (result.getString(1).equals(courseName)) {
							return true;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
}
