package com.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.bean.Cart;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.util.Projectutil;

public class CartDao {

	public static void AddtoCart(Cart c)
	{
		try {
			Connection conn=Projectutil.createConnection();
			String sql="insert into cart(id,pid,product_price,product_qty,total_price)values(?,?,?,?,?)";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, c.getId());
			pst.setInt(2, c.getPid());
			pst.setInt(3, c.getProduct_price());
			pst.setInt(4, c.getProduct_qty());
			pst.setInt(5, c.getTotal_price());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static List<Cart> getCartByUser(int id)
	{
		List<Cart> list=new ArrayList<>();
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="select * from cart where id=?";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=(ResultSet) pst.executeQuery();
			while(rs.next())
			{
				Cart c=new Cart();
				c.setPid(rs.getInt("pid"));
				c.setId(rs.getInt("id"));
				c.setCid(rs.getInt("cid"));
				c.setProduct_price(rs.getInt("product_price"));
				c.setProduct_qty(rs.getInt("product_qty"));
				c.setTotal_price(rs.getInt("total_price"));
				c.setPayment_status(rs.getBoolean("payment_status"));
			
				list.add(c);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
		
	}
	public static boolean checkCart(int id,int pid)
	{
		boolean flag=false;
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="select * from cart where id=? and pid=?";
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
	public static void removeCart(Cart c)
	{
		try {
			Connection conn=Projectutil.createConnection();
			String sql="delete from cart where id=? and pid=?";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, c.getId());
			pst.setInt(2, c.getPid());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static Cart getCart(int cid)
	{
		Cart c=null;
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="select * from cart where cid=?";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, cid);
			ResultSet rs=(ResultSet) pst.executeQuery();
			while(rs.next())
			{
				c=new Cart();
				c.setPid(rs.getInt("pid"));
				c.setId(rs.getInt("id"));
				c.setCid(rs.getInt("cid"));
				c.setProduct_price(rs.getInt("product_price"));
				c.setProduct_qty(rs.getInt("product_qty"));
				c.setTotal_price(rs.getInt("total_price"));
				c.setPayment_status(rs.getBoolean("payment_status"));
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return c;
		
	}
	public static void updateCart(Cart c)
	{
		try {
			Connection conn=Projectutil.createConnection();
			String sql="update cart set product_qty=?,total_price=? where cid=?";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, c.getProduct_qty());
			pst.setInt(2, c.getTotal_price());
			pst.setInt(3, c.getCid());
			   
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
