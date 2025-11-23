///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */

package QLCF.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import QLCF.connection.connectDB;
import QLCF.model.MatHang;
import QLCF.model.NhaCungCap;

/**
 * DAO cho Nhà Cung Cấp
 * @author NoutSpace
 */
public class NhaCungCapDAO {
    
    public List<NhaCungCap> getAll() {
        List<NhaCungCap> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = connectDB.getInstance();
            String sql = "SELECT * FROM nhacungcap ORDER BY maNCC ASC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getInt("maNCC"));
                ncc.setTenNCC(rs.getString("tenNCC"));
                ncc.setLoaiHang(rs.getString("LoaiHang"));
                ncc.setLienHe(rs.getString("LienHe"));
                ncc.setSoDienThoai(rs.getString("SoDienThoai"));
                ncc.setEmail(rs.getString("Email"));
                ncc.setDiaChi(rs.getString("DiaChi"));
                list.add(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public boolean add(NhaCungCap ncc) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = connectDB.getInstance();
            String sql = "INSERT INTO nhacungcap(tenNCC, LoaiHang, LienHe, SoDienThoai, Email, DiaChi) VALUES (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getLoaiHang());
            ps.setString(3, ncc.getLienHe());
            ps.setString(4, ncc.getSoDienThoai());
            ps.setString(5, ncc.getEmail());
            ps.setString(6, ncc.getDiaChi());
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean update(NhaCungCap ncc) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = connectDB.getInstance();
            String sql = "UPDATE nhacungcap SET tenNCC = ?, LoaiHang = ?, LienHe = ?, SoDienThoai = ?, Email = ?, DiaChi = ? WHERE maNCC = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getLoaiHang());
            ps.setString(3, ncc.getLienHe());
            ps.setString(4, ncc.getSoDienThoai());
            ps.setString(5, ncc.getEmail());
            ps.setString(6, ncc.getDiaChi());
            ps.setInt(7, ncc.getMaNCC());
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean delete(int maNCC) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = connectDB.getInstance();
            String sql = "DELETE FROM nhacungcap WHERE maNCC = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maNCC);
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<NhaCungCap> findByName(String keyword) {
        List<NhaCungCap> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = connectDB.getInstance();
            String sql = "SELECT * FROM nhacungcap WHERE tenNCC LIKE ? OR LoaiHang LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getInt("maNCC"));
                ncc.setTenNCC(rs.getString("tenNCC"));
                ncc.setLoaiHang(rs.getString("LoaiHang"));
                ncc.setLienHe(rs.getString("LienHe"));
                ncc.setSoDienThoai(rs.getString("SoDienThoai"));
                ncc.setEmail(rs.getString("Email"));
                ncc.setDiaChi(rs.getString("DiaChi"));
                list.add(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public  List<MatHang> getMatHangByNCC(int maNCC){
            List<MatHang> list = new ArrayList<>();
            String sql = "SELECT * FROM mathangncc WHERE maNCC = ?";

            try(Connection conn = connectDB.getInstance();
                    PreparedStatement ps = conn.prepareStatement(sql)){

                    ps.setInt(1, maNCC);
                    ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    MatHang matHang = new MatHang(
                            rs.getInt("MaHang"),
                            rs.getString("TenHang"),
                            rs.getString("DonVi"),
                            rs.getString("GiaNhap"),
                            rs.getInt("SoLuongTon")
                    );
                    list.add(matHang);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
    
    public boolean updateSoLuongTon(int maHang, int soLuongMoi){
        String sql = "UPDATE mathangncc SET SoLuongTon = ? WHERE maHang = ?";
        try (Connection conn = connectDB.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, soLuongMoi);
            ps.setInt(2, maHang);
        
            int row = ps.executeUpdate();
            
            return row > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    public NhaCungCap getNCCByID(int maNCC){
        String sql = "SELECT * FROM nhacungcap WHERE maNCC = ?";
        try(Connection conn = connectDB.getInstance();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, maNCC);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return new NhaCungCap(
                        rs.getInt("maNCC"),
                        rs.getString("tenNCC"),
                        rs.getString("LoaiHang"),
                        rs.getString("LienHe"),
                        rs.getString("SoDienThoai"),
                        rs.getString("Email"),
                        rs.getString("Diachi")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
  
}