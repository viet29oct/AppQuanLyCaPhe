package QLCF.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import QLCF.connection.connectDB;
import QLCF.model.SanPham;

public class SanPhamDAO {

    public List<SanPham> getAllSanPham() {
        List<SanPham> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB.getInstance();
            String sql = "SELECT maSP, tenSP, gia, loai, hinh_anh FROM sanpham";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setGia(rs.getDouble("gia"));
                sp.setLoai(rs.getString("loai"));
                sp.setHinhanh(rs.getBytes("hinh_anh"));
                list.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return list;
    }

    public void addSanPham(SanPham sp) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB.getInstance();
            String sql = "INSERT INTO sanpham (maSP, tenSP, gia, loai, hinh_anh) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());
            ps.setDouble(3, sp.getGia());
            ps.setString(4, sp.getLoai());
            ps.setBytes(5, sp.getHinhanh());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteSanPham(String maSP) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB.getInstance();
            String sql = "DELETE FROM sanpham WHERE maSP = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, maSP);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public SanPham getSanPhamByMaSP(String maSP) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connectDB.getInstance();
            String sql = "SELECT maSP, tenSP, gia, loai, hinh_anh FROM sanpham WHERE maSP = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, maSP);
            rs = ps.executeQuery();
            if (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setGia(rs.getDouble("gia"));
                sp.setLoai(rs.getString("loai"));
                sp.setHinhanh(rs.getBytes("hinh_anh"));
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public boolean updateSanPham(SanPham sp) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = connectDB.getInstance();
            String sql = "UPDATE sanpham SET tenSP = ?, gia = ?, loai = ?, hinh_anh = ? WHERE maSP = ?"; 
            ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getTenSP());
            ps.setDouble(2, sp.getGia());
            ps.setString(3, sp.getLoai());
            if (sp.getHinhanh() != null) {
                ps.setBytes(4, sp.getHinhanh());
            } else {
                ps.setNull(4, java.sql.Types.BLOB);
            }
            ps.setString(5, sp.getMaSP());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getMaSPByTen(String tenSP) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connectDB.getInstance();
            String sql = "SELECT maSP FROM sanpham WHERE tenSP = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenSP);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("maSP");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }
    public String generateNewMaSP(String loaiSP) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String prefix = "";

        String loaiLower = loaiSP.toLowerCase();
        if (loaiLower.contains("cà phê")) {
            prefix = "CF";
        } else if (loaiLower.contains("bánh")) {
            prefix = "BA";
        } else if (loaiLower.contains("trà")) {
            prefix = "TS";
        } else if (loaiLower.contains("nước ép")) {
            prefix = "NE";
        } else {
            prefix = "SP"; // mdinh
        }

        try {
            conn = connectDB.getInstance();
            String sql = "SELECT maSP FROM sanpham WHERE maSP LIKE ? ORDER BY maSP DESC LIMIT 1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, prefix + "%"); 
            rs = ps.executeQuery();
            
            int nextNumber = 1;
            
            if (rs.next()) {
                String lastMaSP = rs.getString("maSP"); 
                String numberPart = lastMaSP.substring(prefix.length()); 
                
                try {
                    int lastNumber = Integer.parseInt(numberPart);
                    nextNumber = lastNumber + 1;
                } catch (NumberFormatException e) {
                }
            }

            String newNumber = String.format("%03d", nextNumber); 
            return prefix + newNumber;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi tạo mã sản phẩm: " + e.getMessage());
            return prefix + "001"; 
        } finally {
            connectDB.closeConnect(conn);
        }
    }
}
