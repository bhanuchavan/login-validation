package loginvalidation.org.login;

import java.sql.*;
import java.util.Scanner;

public class LoginValidation 
{
    public static void main(String[] args)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM pentagon.login WHERE username = ? AND password = ?";
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
           
            pstmt = conn.prepareStatement(sql);
            
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Enter username:");
            String name = sc.nextLine();
            
            System.out.println("Enter password:");
            String password = sc.nextLine();
            
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            if (rs.next()) 
            {
            	String username=rs.getString(1);
                System.out.println(" Hello "+username+" Login successful!");
            } else {
                System.out.println("Invalid username or password!");
            }
            
        }
        	catch (ClassNotFoundException | SQLException e) 
        {
            e.printStackTrace();
        } finally
        {
            try 
            {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
