/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NoutSpace
 */
public class connectDB {
    public static String url = "jdbc:mySQL://localhost:3306/qlcf_new";
    public static String username = "root";
    private static String password = "12345678";
    public static Connection getInstance(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,username , password );
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
            
        } catch (SQLException e){
            System.out.println("Lỗi kết nối cơ sở dữ liệu MySQL: " + e.getMessage());
            e.printStackTrace();
    }
    
    return conn;
    }
public static void closeConnect(Connection conn){
    try{
        if(conn != null) conn.close();
    }catch (SQLException e){
        e.printStackTrace();
    }
}
}
