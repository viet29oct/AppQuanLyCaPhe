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
import QLCF.model.DetailOrder;
import QLCF.model.SanPham;

/**
 *
 * @author Admin
 */
public class DetailOrderDAO {
    public List<DetailOrder> getDetailByOrderId(int maOrder){
        List<DetailOrder> list = new ArrayList<>();
        String sql = "SELECT od.maOrder, od.maSP, sp.tenSP, od.so_luong, sp.loai, sp.gia, (od.so_luong * sp.gia) AS thanh_tien"
                + " FROM detailorder od JOIN sanpham sp ON od.maSP = sp.maSP"
                + " WHERE od.maOrder = ?";
        try(Connection conn = connectDB.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, maOrder);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    SanPham sanPham = new SanPham(
                            rs.getString("maSP"),
                            rs.getString("tenSP"),
                            rs.getDouble("gia"),
                            rs.getString("loai"),
                            rs.getBytes("hinh_anh")
                    );
                    DetailOrder od = new DetailOrder(
                             rs.getInt("maOrder"),
                             sanPham,
                             rs.getInt("so_luong"),
                             rs.getDouble("thanh_tien")
                    );
                    list.add(od);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return list;
        }
    
    //then chi tiet order moi
    public boolean insertDetailOrder(int maOrder, String maSP,String tenSP, int so_luong, double gia, double thanhTien){
        String sql = "INSERT INTO detailorder (maOrder, maSP, tenSP, so_luong, gia, thanh_tien) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection conn = connectDB.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql)){
            
           ps.setInt(1, maOrder);
           ps.setString(2, maSP);
           ps.setString(3, tenSP);
           ps.setInt(4, so_luong);
           ps.setDouble(5, gia);
           ps.setDouble(6, thanhTien);
           
           return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    //xoa chi tiet order theo ma order va ma san pham
    public boolean deleteDetailOrder(int maOrder, String maSP){
        String sql = "DELETE FROM detailorder WHERE maOrder = ? AND maSP = ?";
        try(Connection conn = connectDB.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql)){
           ps.setInt(1, maOrder);
           ps.setString(2, maSP);
           return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    //cap nhat so luong va thanh tien
    public boolean updateDetailOrder(DetailOrder od){
        String sql = "UPDATE detailorder SET so_luong = ?, gia = ?, thanh_tien = ? WHERE maOrder = ? AND maSP = ?";
        try(Connection conn = connectDB.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql)){
           ps.setInt(1, od.getSo_luong()); 
           ps.setDouble(3, od.getThanh_tien());
           ps.setInt(4, od.getMaOrder());
           ps.setString(5, od.getSanPham().getMaSP());
           return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public void deleteDetailByOrderId(int orderId){
        String sql = "DELETE FROM detailorder WHERE maOrder = ?";
        try(Connection conn = connectDB.getInstance();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
