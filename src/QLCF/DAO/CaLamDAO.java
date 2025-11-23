/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.DAO;
import java.sql.ResultSet;
import java.sql.Connection;
import QLCF.connection.connectDB;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import QLCF.model.CaLam;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import QLCF.model.LichCaLam;
/**
 *
 * @author Admin
 */
public class CaLamDAO {
    public int getCurrentCa() throws Exception{
        Connection conn = connectDB.getInstance();
        
        String sql = "SELECT maCa FROM calam WHERE trang_thai = 'Active' LIMIT 1";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        int maCa = -1;
        if(rs.next()){
            maCa = rs.getInt("maCa");
        }
        
        rs.close();
        ps.close();
        conn.close();
        
        return maCa;
    }
    public List<CaLam> getCaLamByDate(Date date){
        List<CaLam> list = new ArrayList<>();
        String sql = "SELECT c.maCa,c.Date, c.thoi_gian_bat_dau,"
                    +"nv.id, nv.ten AS name, c.trang_thai"+
                " FROM calam c JOIN nhanvien nv ON c.maNV = nv.id" +
                " WHERE c.Date = ?";
        try(Connection conn = connectDB.getInstance();
                PreparedStatement ps = conn.prepareStatement(sql)){
            Date sqlDate = new Date(date.getTime());
            ps.setDate(1, sqlDate);
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    CaLam cl = new CaLam(
                            rs.getInt("maCa"),
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("trang_thai")
                    );
                    list.add(cl);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public int getMaNVBymaCa(int maCa) throws Exception{
        Connection conn = connectDB.getInstance();
        String sql = "SELECT maNV FROM calam WHERE maCa = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maCa);
        ResultSet rs = ps.executeQuery();
        
        int maNV = -1;
        if(rs.next()){
            maNV =  rs.getInt("maNV");
        }
        rs.close();
        ps.close();
        conn.close();
        return maNV;
    }
    
    public boolean insertCaLam(int maCa, Date date, Time thoiGianBatDau, Time thoiGianKetThuc, int maNV, String trangThai) throws SQLException{
        Connection conn = connectDB.getInstance();
        String sql = "INSERT INTO calam (maCa, Date, thoi_gian_bat_dau, thoi_gian_ket_thuc, maNV, trang_thai) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, maCa);
            pst.setDate(2, date);
            pst.setTime(3, thoiGianBatDau);
            pst.setTime(4, thoiGianKetThuc);
            pst.setInt(5, maNV);
            pst.setString(6, trangThai);
            
            int rows = pst.executeUpdate();
            pst.close();
            return rows > 0;
        }
    }
    public List<LichCaLam> getLichCaLamByDate(Date date) {
    List<LichCaLam> list = new ArrayList<>();
    Connection conn = connectDB.getInstance();
    String sql = "SELECT * FROM lichcalam WHERE Date = ?";
    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setDate(1, date);
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                LichCaLam lc = new LichCaLam(
                    rs.getInt("maCa"),
                    rs.getTime("thoi_gian_bat_dau"),      
                    rs.getTime("thoi_gian_ket_thuc")      
                );
                list.add(lc);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return list;
    }
    
    public List<LichCaLam> getAllLichCaLam() {
        List<LichCaLam> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB.getInstance();
            String sql = "select * from lichcalam";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LichCaLam lcl = new LichCaLam();
                lcl.setMaCa(rs.getInt("maCa"));
                lcl.setBatdau(rs.getTime("thoi_gian_bat_dau"));
                lcl.setKetthuc(rs.getTime("thoi_gian_ket_thuc"));
                list.add(lcl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close();
                  if (ps != null) ps.close();
                  if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception ignored) {}
        }
        return list;
    }
}
