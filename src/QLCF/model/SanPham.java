/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.model;

/**
 *
 * @author Admin
 */
public class SanPham {
    private String maSP;
    private String tenSP;
    private double gia;
    private String loai;
    private byte[] hinhanh;
    
    public SanPham(){}

    public SanPham(String maSP, String tenSP, double gia, String loai, byte[] hinhanh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.loai = loai;
        this.hinhanh = hinhanh;
    }

    public SanPham(String tenSP, double gia, String loai, byte[] hinhanh) {
        this.tenSP = tenSP;
        this.gia = gia;
        this.loai = loai;
        this.hinhanh = hinhanh;
    }
    
    

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }
    
    
    
}
