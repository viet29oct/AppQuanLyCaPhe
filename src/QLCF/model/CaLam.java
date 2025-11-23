/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.model;

/**
 *
 * @author Admin
 */
public class CaLam {
    private int maCa;
    private int maNV;
    private String tenNV;
    private String trang_thai;

    public CaLam(){}

    public int getMaCa() {
        return maCa;
    }

    public void setMaCa(int maCa) {
        this.maCa = maCa;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public CaLam(int maCa, int maNV, String tenNV, String trang_thai) {
        this.maCa = maCa;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.trang_thai = trang_thai;
    }
    
}
