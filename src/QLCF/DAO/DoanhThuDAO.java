/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.DAO;

import java.sql.Connection;
import QLCF.connection.connectDB;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import QLCF.model.DoanhThu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
/**
 *
 * @author Admin
 */
public class DoanhThuDAO {
    public void insertDoanhThu(int maOrder, double tongTien, int maNV, int maCa) throws Exception{
        Connection conn = connectDB.getInstance();
        
        String sql = "INSERT INTO doanhthu (maOrder, thoi_gian, tongTien, maNV, maCa) "
                + "VALUES (?, NOW(), ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, maOrder);
        ps.setDouble(2, tongTien);
        ps.setInt(3, maNV);
        ps.setInt(4, maCa);
        
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    public List<DoanhThu> getAllDoanhThu(){
        List<DoanhThu> list = new ArrayList<>();
        String sql = "SELECT * FROM doanhthu";
        try(Connection conn = connectDB.getInstance();
                PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()){
        
            while(rs.next()){
                DoanhThu dt = new DoanhThu(
                        rs.getInt("id"),
                        rs.getInt("maOrder"),
                        rs.getTime("thoi_gian"),
                        rs.getDouble("tongTien"),
                        rs.getInt("maNV"),
                        rs.getInt("maCa")
                );
                list.add(dt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<DoanhThu> getDoanhThuByDate(Date date){
        List<DoanhThu> list = new ArrayList<>();
        String sql = "SELECT * FROM doanhthu WHERE DATE(thoi_gian) = ? ORDER BY thoi_gian DESC";
        try(Connection conn = connectDB.getInstance();
                PreparedStatement ps = conn.prepareStatement(sql)){
            java.sql.Date sqlDate = new Date(date.getTime());
            ps.setDate(1, sqlDate);
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    DoanhThu dt = new DoanhThu(
                            rs.getInt("id"),
                            rs.getInt("maOrder"),
                            rs.getTime("thoi_gian"),
                            rs.getDouble("tongTien"),
                            rs.getInt("maNV"),
                            rs.getInt("maCa")
                    );
                    list.add(dt);
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
