package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.bean.Cart;
import com.bean.User;
import com.bean.Wishlist;
import com.dao.CartDao;
import com.dao.UserDao;
import com.dao.WishlistDao;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512, maxRequestSize = 1024 * 1024 * 512)//512MB 


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String extractfilename(Part file) {
		String cd = file.getHeader("content-disposition");
		System.out.println(cd);
		String[] items = cd.split(";");
		for(String string : items) {
			if(string.trim().startsWith("filename")) {
				return string.substring(string.indexOf("=") + 2, string.length() - 1);
			}
		}
		return "";
		
	}
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("insert"))
		{
			boolean flag=UserDao.checkemail(request.getParameter("email"));
			if(flag==false)
			{
				if(request.getParameter("password").equals(request.getParameter("cpassword")))
				{
					User u=new User();
					u.setUsertype(request.getParameter("usertype"));
					u.setFname(request.getParameter("fname"));
					u.setLname(request.getParameter("lname"));
					u.setEmail(request.getParameter("email"));
					u.setMobile(Long.parseLong(request.getParameter("mobile")));
					u.setPassword(request.getParameter("password"));
					String savePath = "C:\\Users\\LENOVO\\eclipse-workspace\\Project1\\src\\main\\webapp\\profile_picture";
					File fileSaveDir = new File(savePath);
					if(!fileSaveDir.exists()) {
						fileSaveDir.mkdir();
					}
					Part file1 = request.getPart("profile_picture");
					String fileName = extractfilename(file1);
					file1.write(savePath + File.separator + fileName);
					String savePath2 = "C:\\Users\\LENOVO\\eclipse-workspace\\Project1\\src\\main\\webapp\\profile_picture";
					File imgSaveDir = new File(savePath2);
					if(!imgSaveDir.exists()) {
						imgSaveDir.mkdir();
					}
					u.setProfile_picture(fileName);
					UserDao.SignupUser(u);
					request.setAttribute("msg", "user signup successfully");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("msg", "password and confirm password not matched");
					request.getRequestDispatcher("signup.jsp").forward(request, response);
				}		
			}
			else
			{
				request.setAttribute("msg", "email already registered");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
				
		}
		else if(action.equalsIgnoreCase("login"))
		{
			User u=UserDao.loginUser(request.getParameter("email")); 
			if(u!=null)
			{ 
				if(u.getPassword().equals(request.getParameter("password"))) 
				{
					HttpSession session=request.getSession();
					session.setAttribute("u", u);
					if(u.getUsertype().equals("Buyer"))
					{
						List<Wishlist> list=WishlistDao.getWishlistByUser(u.getId());
						session.setAttribute("wishlist_count", list.size());
						
						List<Cart> list1=CartDao.getCartByUser(u.getId());
						session.setAttribute("cart_count", list1.size());
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					else
					{
						request.getRequestDispatcher("seller-index.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("msg", "incorrect password");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} 
			}
			else
			{
				request.setAttribute("msg", "email not registered");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("change password"))
		{
			HttpSession session=request.getSession();
			User u=(User) session.getAttribute("u");
			if(u.getPassword().equals(request.getParameter("o_password")))
			{
				if(request.getParameter("n_password").equals(request.getParameter("cn_password")))
				{
					if(!u.getPassword().equals(request.getParameter("n_password")))
					{
						UserDao.changepassword(u.getEmail(), request.getParameter("n_password"));
						request.setAttribute("msg", "password changed successfully");
						session.removeAttribute("u");
						session.invalidate();	
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
					else
					{
						request.setAttribute("msg", "your new password and old password are same");
						if(u.getUsertype().equals("Buyer"))
						{
							request.getRequestDispatcher("changepassword.jsp").forward(request, response);
						}
						else
						{
							request.getRequestDispatcher("seller-changepassword.jsp").forward(request, response);
						}
					}
				}
				else
				{
					request.setAttribute("msg", "new password and conform password are not matched");
					if(u.getUsertype().equals("Buyer"))
					{
						request.getRequestDispatcher("changepassword.jsp").forward(request, response);
					}
					else
					{
						request.getRequestDispatcher("seller-changepassword.jsp").forward(request, response);
					}
				}
			}
			request.setAttribute("msg", "old password are not matched");
			if(u.getUsertype().equals("Buyer"))
			{
				request.getRequestDispatcher("changepassword.jsp").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("seller-changepassword.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("Update Profile"))
		{
			HttpSession session=request.getSession();
			User u=(User) session.getAttribute("u");
			u.setUsertype(request.getParameter("usertype"));
			u.setFname(request.getParameter("fname"));
			u.setLname(request.getParameter("lname"));
			u.setMobile(Long.parseLong(request.getParameter("mobile")));
			UserDao.updateprofile(u);
			session.setAttribute("u", u);
			request.setAttribute("msg", "profile updated successfully");
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		}
	}
}
