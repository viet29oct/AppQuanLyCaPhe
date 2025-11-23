/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import QLCF.connection.connectDB;
import QLCF.model.TaiKhoan;

/**
 *
 * @author NoutSpace
 */
public class TaiKhoanDao {

    public void addTaiKhoan(TaiKhoan tk) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = connectDB.getInstance();
            String sql = "INSERT INTO taikhoan (username, password, role) VALUES (?, ?, ?)";
            pre = conn.prepareStatement(sql);

            pre.setString(1, tk.getUsername());
            pre.setString(2, tk.getPassword());
            pre.setString(3, tk.getRole());

            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pre != null) pre.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateTaiKhoan(TaiKhoan tk) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = connectDB.getInstance();
            // cap nhat username, password, role lay tu id
            String sql = "UPDATE taikhoan SET username = ?, password = ? WHERE id = ?"; 
            pre = conn.prepareStatement(sql);
            
            pre.setString(1, tk.getUsername());
            pre.setString(2, tk.getPassword());
            pre.setInt(3, tk.getId()); 

            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pre != null) pre.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteTaiKhoan(int id) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = connectDB.getInstance();
            String sql = "DELETE FROM taikhoan WHERE id = ?"; 
            pre = conn.prepareStatement(sql);

            pre.setInt(1, id);

            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pre != null) pre.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<TaiKhoan> getAllTaiKhoans() {
        List<TaiKhoan> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = connectDB.getInstance();
            String sql = "SELECT * FROM taikhoan";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery(); 

            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setId(rs.getInt("id"));
                tk.setUsername(rs.getString("username"));
                tk.setPassword(rs.getString("password"));
                tk.setRole(rs.getString("role"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pre != null) pre.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public TaiKhoan checkLogin(String username, String password) {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        TaiKhoan tk = null;
        try {
            conn = connectDB.getInstance();
            // truy van username + pass
            String sql = "SELECT id, username, role FROM taikhoan WHERE username = ? AND password = ?"; 
            pre = conn.prepareStatement(sql);
            
            pre.setString(1, username);
            pre.setString(2, password);
            
            rs = pre.executeQuery();

            if (rs.next()) {
                tk = new TaiKhoan();
                tk.setId(rs.getInt("id"));
                tk.setUsername(rs.getString("username"));
                tk.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pre != null) pre.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return tk;
    }
    
    public boolean checkRegister(String username){
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        boolean checkExist = false;
        
        try {
            conn = connectDB.getInstance();
            String sql = "SELECT id, username, role FROM taikhoan WHERE username = ?"; 
            pre = conn.prepareStatement(sql);
            
            pre.setString(1, username);
            rs = pre.executeQuery();
            
            if (rs.next()) {
                checkExist = true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (rs != null) rs.close();
                if (pre != null) pre.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception e) {
            }
        }
        return checkExist;
    }
}

