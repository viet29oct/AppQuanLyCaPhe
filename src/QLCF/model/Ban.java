/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.model;

/**
 *
 * @author Admin
 */
public class Ban {
    private int maBan;
    private String tenBan;
    private String trang_thai;
    
    public Ban(){}
    
    public Ban(int maBan, String tenBan, String trang_thai){
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.trang_thai = trang_thai;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }
    
}
