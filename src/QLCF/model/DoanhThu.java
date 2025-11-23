/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class DoanhThu {
    private int id;
    private int maOrder;
    private Date thoi_gian;
    private double tongTien;
    private int maNV;
    private int maCa;

    public DoanhThu(){}
    
    public DoanhThu(int id, int maOrder, Date thoi_gian, double tongTien, int maNV, int maCa) {
        this.id = id;
        this.maOrder = maOrder;
        this.thoi_gian = thoi_gian;
        this.tongTien = tongTien;
        this.maNV = maNV;
        this.maCa = maCa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaOrder() {
        return maOrder;
    }

    public void setMaOrder(int maOrder) {
        this.maOrder = maOrder;
    }

    public Date getThoi_gian() {
        return thoi_gian;
    }

    public void setThoi_gian(Date thoi_gian) {
        this.thoi_gian = thoi_gian;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaCa() {
        return maCa;
    }

    public void setMaCa(int maCa) {
        this.maCa = maCa;
    }
    
    
}
