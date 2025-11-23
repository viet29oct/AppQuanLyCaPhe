/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.model;

/**
 *
 * @author Admin
 */
public class DetailOrder {
    private int maOrder;
    private SanPham sanPham;
    private int so_luong;
    private double thanh_tien;
    
    public DetailOrder(){}
    public DetailOrder(int maOrder, SanPham sanPham, int so_luong, double thanh_tien) {
        this.maOrder = maOrder;
        this.sanPham = sanPham;
        this.so_luong = so_luong;
        this.thanh_tien = thanh_tien;
    }
    public int getMaOrder() {
        return maOrder;
    }

    public void setMaOrder(int maOrder) {
        this.maOrder = maOrder;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
        this.thanh_tien = sanPham.getGia() * this.so_luong;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public double getThanh_tien() {
        return thanh_tien;
    }

    public void setThanh_tien(double thanh_tien) {
        this.thanh_tien = thanh_tien;
    }
    

    
    
}
