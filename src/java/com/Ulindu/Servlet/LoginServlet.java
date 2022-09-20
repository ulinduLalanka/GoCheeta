/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Ulindu.Servlet;

import com.Ulindu.Dao.LoginDao;
import com.Ulindu.bean.LoginBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet("/LoginView")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uName = request.getParameter("email");
        String pass = request.getParameter("password");

        LoginBean loginBean = new LoginBean();

        loginBean.setEmail(uName);
        loginBean.setPassword(pass);

        LoginDao loginDao = new LoginDao();

        HttpSession session = request.getSession();

        RequestDispatcher dispatcher = null;

        try {
            String userValidate = loginDao.authernticatedUser(loginBean);

            switch (userValidate) {
                case "Admin_Role":
                    session.setAttribute("name", loginBean.getUsername());
                    dispatcher = request.getRequestDispatcher("AdminPortal.jsp");
                    break;
                case "User_Role":
                    session.setAttribute("name", loginBean.getUsername());
                    dispatcher = request.getRequestDispatcher("CustomerPortal.jsp");
                    break;
                default:
                    dispatcher = request.getRequestDispatcher("index.html");
                    break;
            }
            dispatcher.forward(request, response);

        } catch (IOException | ServletException ex) {
        }
    }

}
