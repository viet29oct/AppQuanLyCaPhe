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
public class Order {
    private int maOrder;
    private int maBan;
    private Date thoi_gian;
    private String trang_thai;
    
    public Order(){}
    
    public Order(int maOrder, int maBan, Date thoi_gian, String trang_thai){
        this.maOrder = maOrder;
        this.maBan = maBan;
        this.thoi_gian = thoi_gian;
        this.trang_thai = trang_thai;
    }

    public int getMaOrder() {
        return maOrder;
    }

    public void setMaOrder(int maOrder) {
        this.maOrder = maOrder;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    @SuppressWarnings("ReturnOfDateField")
    public Date getThoi_gian() {
        return thoi_gian;
    }

    @SuppressWarnings("AssignmentToDateFieldFromParameter")
    public void setThoi_gian(Date thoi_gian) {
        this.thoi_gian = thoi_gian;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }
    
    
}
