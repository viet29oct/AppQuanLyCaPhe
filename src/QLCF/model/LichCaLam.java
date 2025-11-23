/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCF.model;

import java.sql.Time;

/**
 *
 * @author Admin
 */
public class LichCaLam {
    private int maCa;
    private Time batdau;
    private Time ketthuc;

    public LichCaLam(){}
    public LichCaLam(int maCa, Time batdau, Time ketthuc) {
        this.maCa = maCa;
        this.batdau = batdau;
        this.ketthuc = ketthuc;
    }

    public int getMaCa() {
        return maCa;
    }

    public void setMaCa(int maCa) {
        this.maCa = maCa;
    }

    public Time getBatdau() {
        return batdau;
    }

    public void setBatdau(Time batdau) {
        this.batdau = batdau;
    }

    public Time getKetthuc() {
        return ketthuc;
    }

    public void setKetthuc(Time ketthuc) {
        this.ketthuc = ketthuc;
    }
    
    
}
