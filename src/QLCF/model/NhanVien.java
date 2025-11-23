/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.model;

import java.util.Date;

/**
 *
 * @author NoutSpace
 */
public class NhanVien {
    private int id;
    private String ten;
    private String sdt;
    private Double luongCB;
    private String gioiTinh;
    private Date ngaySinh;
    private int taikhoanId;
    private String username;
    private String role;
    
    public  NhanVien() {}

    public NhanVien(int id, String ten, String sdt, Double luongCB, String gioiTinh, Date ngaySinh, int taikhoanId, String username, String role) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.luongCB = luongCB;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.taikhoanId = taikhoanId;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Double getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(Double luongCB) {
        this.luongCB = luongCB;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTaikhoanId() {
        return taikhoanId;
    }

    public void setTaikhoanId(int taikhoanId) {
        this.taikhoanId = taikhoanId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}

