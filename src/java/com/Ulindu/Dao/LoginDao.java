/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.Ulindu.Dao;

import com.Ulindu.bean.LoginBean;
import com.Ulindu.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class LoginDao {
   public String authernticatedUser(LoginBean loginBean) {
        String username = loginBean.getEmail();
        String password = loginBean.getPassword();
        Connection con = null;
        PreparedStatement pst = null;

        ResultSet rs = null;

        String roleDB = "";
        String fullName = "";
        try {
            con = DBConnection.createConnection();
            pst = con.prepareStatement("select * from customer where Email = ? and Password =? ");
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            while (rs.next()) {
                roleDB = rs.getString("Role");
                fullName = rs.getString("Username");
                loginBean.setUsername(fullName);

                if (roleDB.equals("Admin")) {
                    return ("Admin_Role");
                } else if (roleDB.equals("Customer")) {
                    return "User_Role";

                }
            }
        } catch (SQLException e) {
        }
        return "Invalid user credentials";
    }
}
