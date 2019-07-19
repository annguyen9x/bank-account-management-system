/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm.app;

import java.util.Date;

/**
 *
 * @author An Nguyen
 */
public class Transaction{

    private int logID;
    private Account acc;
    private double soTien;
    private Date thoiGian;
    private String loaiGD, moTa;
    private static int count = 1;

    public Transaction() {

    }

    public Transaction(Account a, double soTien, String loaiGD, String mota) {
        this.logID = count++;
        this.acc = a;
        this.soTien = soTien;
        this.thoiGian = new Date();
        this.loaiGD = loaiGD;
        this.moTa = mota;
    }

    @Override
    public String toString() {
        return "LogID= " + logID + ", thời gian= " + thoiGian + ", tài khoản=" + acc.getSoTK() + ", số tiền= " + soTien + ", loại GD= " + loaiGD + ", mô tả= " + moTa;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getLoaiGD() {
        return loaiGD;
    }

    public void setLoaiGD(String loaiGD) {
        this.loaiGD = loaiGD;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Transaction.count = count;
    }
    
}
