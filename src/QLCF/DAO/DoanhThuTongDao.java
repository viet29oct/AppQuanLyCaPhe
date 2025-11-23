/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import QLCF.connection.connectDB;
import QLCF.model.DoanhThu;

/**
 *
 * @author NoutSpace
 */
public class DoanhThuTongDao {
    
    public List<DoanhThu> getDoanhThuTong(Date start, Date end) {
    List<DoanhThu> list = new ArrayList<>();   
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conn = connectDB.getInstance();
        String sql = "SELECT * FROM doanhthu WHERE thoi_gian BETWEEN ? AND ?";
        ps = conn.prepareStatement(sql);
        java.sql.Date BatDau = new java.sql.Date(start.getTime());
        java.sql.Date KetThuc = new java.sql.Date(end.getTime());
        ps.setDate(1, BatDau);
        ps.setDate(2, KetThuc);
        rs = ps.executeQuery();
        while (rs.next()) {
            DoanhThu dt = new DoanhThu();
            dt.setId(rs.getInt("id"));
            dt.setMaOrder(rs.getInt("maOrder"));
            dt.setThoi_gian(rs.getTimestamp("thoi_gian"));
            dt.setTongTien(rs.getDouble("TongTien"));
            dt.setMaNV(rs.getInt("MaNV"));
            dt.setMaCa(rs.getInt("maCa"));
            
            list.add(dt);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

}
