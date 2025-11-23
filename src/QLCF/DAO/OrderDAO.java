/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.DAO;

import java.sql.Statement;
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
public class OrderDAO {
    
    // lay id order hien tai theo ma ban
    public Integer getCurrentOrderIdByBan(int maBan){
        String sql = "SELECT maOrder FROM `order` WHERE maBan = ? and trang_thai = 'Đang phục vụ'";
        
        try(Connection conn = connectDB.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql)){
                
            ps.setInt(1, maBan);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt("maOrder");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public List<DetailOrder> getOrderDetailByOrderId(int orderId){
        List<DetailOrder> list = new ArrayList<>();
        String sql = "SELECT od.maOrder, od.maSP, od.so_luong, od.thanh_tien, "
                   + " sp.maSP, sp.tenSP, sp.gia, sp.loai, sp.hinh_anh " 
                   + " FROM detailorder od JOIN sanpham sp ON od.maSP = sp.maSP "
                   + " WHERE od.maOrder = ? AND sp.trang_thai = ?";
        
        try(Connection conn = connectDB.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql)){
                
            ps.setInt(1, orderId);
            ps.setString(2,"Đang bán");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("maSP"));            
                sp.setTenSP(rs.getString("tenSP"));     
                sp.setGia(rs.getDouble("gia"));       
                sp.setHinhanh(rs.getBytes("hinh_anh")); 

                DetailOrder detail = new DetailOrder(
                    rs.getInt("maOrder"),
                    sp,
                    rs.getInt("so_luong"),
                    rs.getDouble("thanh_tien")
                );
                list.add(detail);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public Integer createOrder(int maBan){
        String sql = "INSERT INTO `order` (maBan, trang_thai) VALUES (?, ?)";
        
        try(Connection conn = connectDB.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                
            ps.setInt(1, maBan);
            ps.setString(2, "Đang phục vụ");
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) return rs.getInt(1);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void InsertData(int maOrder, String maSP, String tenSP, int so_luong, double gia, double thanh_tien) {
        String sql = "INSERT INTO detailorder (maOrder, maSP, tenSP, so_luong, gia, thanh_tien) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connectDB.getInstance(); 
                PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, maOrder);
                    ps.setString(2, maSP);
                    ps.setString(3, tenSP);
                    ps.setInt(4, so_luong);
                    ps.setDouble(5, gia);
                    ps.setDouble(6, so_luong * gia);
                    ps.executeUpdate();
        }    catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateBanTrangThai(int maBan, String trang_thai){
        Connection conn = connectDB.getInstance();
        String sql = "UPDATE ban SET trang_thai = ? WHERE maBan = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, trang_thai);
            ps.setInt(2, maBan);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean updateOrderTrangThai(int maOrder, String trang_thai){
        String sql = "UPDATE `order` SET trang_thai = ? WHERE maOrder = ?";
        
        try(Connection conn = connectDB.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, trang_thai);
            ps.setInt(2, maOrder);
            
            int affectRow = ps.executeUpdate();
            
            return affectRow > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}