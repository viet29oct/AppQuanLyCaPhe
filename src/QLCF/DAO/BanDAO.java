/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import QLCF.connection.connectDB;
import QLCF.model.Ban;

/**
 *
 * @author Admin
 */
public class BanDAO {
    public List<Ban> getAllBan(){
        List<Ban> list = new ArrayList<>();
        String sql = "SELECT * FROM ban";
        try(Connection conn = connectDB.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                Ban ban = new Ban(
                        rs.getInt("maBan"),
                        rs.getString("tenBan"),
                        rs.getString("trang_thai")
                );
                list.add(ban);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    } 
    
    public void updateTrangThai(int maBan, String trangThai){
        String sql = "UPDATE ban SET trang_thai = ? WHERE maBan = ?";
        
        try(Connection conn = connectDB.getInstance();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, trangThai);
            ps.setInt(2, maBan);
            
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
