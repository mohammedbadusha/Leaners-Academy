package com.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Classes;
import com.entity.Subject;
import com.util.HibernateUtil;

//@WebServlet("/addClass")
public class addClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			// Step 1: Get details , user has entered
			String name = request.getParameter("class");
					
			// Step2: Create session
			SessionFactory sf  = HibernateUtil.buildSessionFactory();
			Session session = sf.openSession();
			
			// Step 3: Begin Transaction
			Transaction tx = session.beginTransaction();
				
			//Step4; Create persistent object and add Teacher
			
			Classes classes = new Classes();
			classes.setName(name);
			
			
			session.save(classes);
			
			// STep5: Commit transaction and close session
			tx.commit();
			session.close();
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewClass.jsp");
	        dispatcher.forward(request, response); 
	}

}
