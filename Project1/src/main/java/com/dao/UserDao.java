package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.User;
import com.util.Projectutil;

public class UserDao {
	public static void SignupUser(User u)
	{
		try 
		{
			Connection conn=Projectutil.createConnection();
			String sql="insert into user (usertype,fname,lname,email,mobile,password,profile_picture)values(?,?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, u.getUsertype());
			pst.setString(2, u.getFname());
			pst.setString(3, u.getLname());
			pst.setString(4, u.getEmail());
			pst.setLong(5, u.getMobile());
			pst.setString(6, u.getPassword());
			pst.setString(7, u.getProfile_picture());
			
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static boolean checkemail(String email)
	{
		boolean flag=false;
		try 
		{
			Connection conn=Projectutil.createConnection();
			String sql="select * from user where email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				flag=true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
		
	}
	public static User loginUser(String email)
	{
		User u=null; 
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="select * from user where email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				u=new User();
				u.setId(rs.getInt("id"));
				u.setUsertype(rs.getString("usertype"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setEmail(rs.getString("email"));
				u.setMobile(rs.getLong("mobile"));
				u.setPassword(rs.getString("password"));
				u.setProfile_picture(rs.getString("profile_picture"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return u;	
	}
	public static void changepassword(String email,String password)
	{
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="update user set password=? where email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2,email);
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void updateprofile(User u)
	{
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="update user set usertype=?,fname=?,lname=?,mobile=? where email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, u.getUsertype());
			pst.setString(2, u.getFname());
			pst.setString(3, u.getLname());
			pst.setLong(4, u.getMobile());
			pst.setString(5, u.getEmail());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
