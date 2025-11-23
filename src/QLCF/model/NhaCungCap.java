/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.model;

/**
 *
 * @author Admin
 */
public class NhaCungCap {
    private int MaNCC;
    private String TenNCC;
    private String LoaiHang;
    private String LienHe;
    private String SoDienThoai;
    private String Email;
    private String DiaChi;
    
    public NhaCungCap(){}
    
    public NhaCungCap(int MaNCC, String TenNCC, String LoaiHang, String LienHe, String SoDienThoai, String Email, String DiaChi){
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.LoaiHang = LoaiHang;
        this.LienHe = LienHe;
        this.SoDienThoai = SoDienThoai;
        this.Email = Email;
        this.DiaChi = DiaChi;
    }

    public int getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(int MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getLoaiHang() {
        return LoaiHang;
    }

    public void setLoaiHang(String LoaiHang) {
        this.LoaiHang = LoaiHang;
    }

    public String getLienHe() {
        return LienHe;
    }

    public void setLienHe(String LienHe) {
        this.LienHe = LienHe;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
    
}
