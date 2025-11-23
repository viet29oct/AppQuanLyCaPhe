/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NoutSpace
 */
package QLCF.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import QLCF.connection.connectDB;
import QLCF.model.NhanVien;

public class NhanVienDao {

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB.getInstance();
            String sql = """
                SELECT n.id, n.ten, n.sdt, n.gioitinh, n.ngaysinh, n.taikhoan_id, t.username, t.role
                FROM nhanvien n
                JOIN taikhoan t ON n.taikhoan_id = t.id
            """;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt("id"));
                nv.setTen(rs.getString("ten"));
                nv.setSdt(rs.getString("sdt"));
                nv.setGioiTinh(rs.getString("gioitinh"));
                nv.setNgaySinh(rs.getDate("ngaysinh"));
                nv.setTaikhoanId(rs.getInt("taikhoan_id"));
                nv.setUsername(rs.getString("username"));
                nv.setRole(rs.getString("role"));
                list.add(nv);
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

    public void addNhanVien(NhanVien nv) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = connectDB.getInstance();
            String sql = "INSERT INTO nhanvien(ten, sdt, gioitinh, ngaysinh, taikhoan_id) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nv.getTen());
            ps.setString(2, nv.getSdt());
            ps.setString(3, nv.getGioiTinh());
            ps.setDate(4, (Date) nv.getNgaySinh());
            ps.setInt(5, nv.getTaikhoanId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); if (conn != null) connectDB.closeConnect(conn); } catch (Exception ignored) {}
        }
    }

    public void deleteNhanVien(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = connectDB.getInstance();
            String sql = "DELETE FROM nhanvien WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); if (conn != null) connectDB.closeConnect(conn); } catch (Exception ignored) {}
        }
    }
    
    public void updateNhanVien(NhanVien nv) {
        String sql = "UPDATE nhanvien SET ten = ?, gioitinh = ?, sdt = ?, ngaysinh = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB.getInstance();
            ps = conn.prepareStatement(sql);

            ps.setString(1, nv.getTen());
            ps.setString(2, nv.getGioiTinh());
            ps.setString(3, nv.getSdt());
            ps.setDate(4,(Date) nv.getNgaySinh());
            ps.setInt(5, nv.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
            } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên để cập nhật!");
        }


        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Loi " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) connectDB.closeConnect(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<NhanVien> findByName(String name) {
        List<NhanVien> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;

        try {
            conn = connectDB.getInstance();
            String sql = """
                SELECT n.id, n.ten, n.sdt, n.gioitinh, n.ngaysinh, n.taikhoan_id, t.username, t.role
                FROM nhanvien n
                JOIN taikhoan t ON n.taikhoan_id = t.id
                WHERE n.ten LIKE ?
            """;
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + name + "%");
            rs = pre.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt("id"));
                nv.setTen(rs.getString("ten"));
                nv.setGioiTinh(rs.getString("gioitinh"));
                nv.setSdt(rs.getString("sdt"));
                nv.setNgaySinh(rs.getDate("ngaysinh"));
                nv.setTaikhoanId(rs.getInt("taikhoan_id"));
                nv.setUsername(rs.getString("username"));
                nv.setRole(rs.getString("role"));
                list.add(nv);
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
}

