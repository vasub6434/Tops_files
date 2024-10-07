package com.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.bean.Wishlist;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.util.Projectutil;

public class WishlistDao {

	public static void AddtoWishlist(Wishlist w)
	{
		try {
			Connection conn=Projectutil.createConnection();
			String sql="insert into wishlist(id,pid)values(?,?)";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, w.getId());
			pst.setInt(2, w.getPid());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static List<Wishlist> getWishlistByUser(int id)
	{
		List<Wishlist> list=new ArrayList<>();
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="select * from wishlist where id=?";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=(ResultSet) pst.executeQuery();
			while(rs.next())
			{
				Wishlist w=new Wishlist();
				w.setPid(rs.getInt("pid"));
				w.setId(rs.getInt("id"));
				w.setWid(rs.getInt("wid"));
				list.add(w);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
		
	}
	public static boolean checkwishlist(int id,int pid)
	{
		boolean flag=false;
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="select * from wishlist where id=? and pid=?";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, pid);
			ResultSet rs=(ResultSet) pst.executeQuery();
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
	public static void removeWishlist(Wishlist w)
	{
		try {
			Connection conn=Projectutil.createConnection();
			String sql="delete from wishlist where id=? and pid=?";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, w.getId());
			pst.setInt(2, w.getPid());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
