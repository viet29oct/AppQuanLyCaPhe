/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.model;

/**
 *
 * @author Admin
 */
public class MatHang {
    private int MaHang;
    private String TenHang;
    private String DonVi;
    private String GiaNhap;
    private int SoLuongTon;

    public MatHang(){}
    
    public MatHang(int MaHang, String TenHang, String DonVi, String GiaNhap, int SoLuongTon) {
        this.MaHang = MaHang;
        this.TenHang = TenHang;
        this.DonVi = DonVi;
        this.GiaNhap = GiaNhap;
        this.SoLuongTon = SoLuongTon;
    }

    public int getMaHang() {
        return MaHang;
    }

    public void setMaHang(int MaHang) {
        this.MaHang = MaHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String DonVi) {
        this.DonVi = DonVi;
    }

    public String getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(String GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }
    
    
}
