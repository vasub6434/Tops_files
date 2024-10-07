package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.bean.Product;
import com.mysql.jdbc.ResultSet;
import com.util.Projectutil;

public class ProductDao {

	public static void addProduct(Product p)
	{
		try {
			Connection conn=Projectutil.createConnection();
			String sql="insert into product(product_category,product_name,product_price,product_desc,product_image,id)values(?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, p.getProduct_category());
			pst.setString(2, p.getProduct_name());
			pst.setInt(3, p.getProduct_price());
			pst.setString(4, p.getProduct_desc());
			pst.setString(5, p.getProduct_image());
			pst.setInt(6, p.getId());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static List<Product> getProductBySeller(int id)
	{
		List<Product> list=new ArrayList<Product>();
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="select * from product where id=?";
			PreparedStatement  pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=(ResultSet) pst.executeQuery();
			while(rs.next())
			{
				Product p=new Product();
				p.setPid(rs.getInt("pid"));
				p.setId(rs.getInt("id"));
				p.setProduct_price(rs.getInt("product_price"));
				p.setProduct_category(rs.getString("product_category"));
				p.setProduct_desc(rs.getString("product_name"));
				p.setProduct_image(rs.getString("product_image"));
				p.setProduct_desc(rs.getString("product_desc"));
				list.add(p);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
		
	}
	public static Product getProduct(int pid)
	{
		Product p=null;
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="select * from product where pid=?";
			PreparedStatement  pst=conn.prepareStatement(sql);
			pst.setInt(1, pid);
			ResultSet rs= (ResultSet) pst.executeQuery();
			while(rs.next())
			{
				p=new Product();
				p.setPid(rs.getInt("pid"));
				p.setId(rs.getInt("id"));
				p.setProduct_price(rs.getInt("product_price"));
				p.setProduct_category(rs.getString("product_category"));
				p.setProduct_desc(rs.getString("product_name"));
				p.setProduct_image(rs.getString("product_image"));
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return p;	
	}
	public static void updateProduct(Product p)
	{
		try {
			Connection conn=Projectutil.createConnection();
			String sql="update product set product_category=?,product_name=?,product_price=?,product_desc=? where pid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, p.getProduct_category());
			pst.setString(2, p.getProduct_name());
			pst.setInt(3, p.getProduct_price());
			pst.setString(4, p.getProduct_desc());
			pst.setInt(5, p.getPid());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void deleteProduct(int pid)
	{
		try {
			Connection conn=Projectutil.createConnection();
			String sql="delete from product where pid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pid);
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static List<Product> getAllproduct()
	{
		List<Product> list=new ArrayList<Product>();
		try
		{
			Connection conn=Projectutil.createConnection();
			String sql="select * from product";
			PreparedStatement  pst=conn.prepareStatement(sql);
			ResultSet rs=(ResultSet) pst.executeQuery();
			while(rs.next())
			{
				Product p=new Product();
				p.setPid(rs.getInt("pid"));
				p.setId(rs.getInt("id"));
				p.setProduct_price(rs.getInt("product_price"));
				p.setProduct_category(rs.getString("product_category"));
				p.setProduct_desc(rs.getString("product_name"));
				p.setProduct_image(rs.getString("product_image"));
				p.setProduct_desc(rs.getString("product_desc"));
				list.add(p);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
		
	}
}
