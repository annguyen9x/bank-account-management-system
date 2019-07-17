/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author An Nguyen
 */
public class Customer extends Menu implements Comparable<Customer> {

    protected String maKH, ten, matKhau;
    protected int cmnd;
    protected Date ngaySinh;
    protected static int count = 1000;
    static String[] mc = {"Internet Banking welcome !", "Đăng ký thêm tài khoản", "Xem danh sách tài khoản đã có", "Đăng nhập vào tài khoản", "Đổi mật khẩu đăng nhập thành viên"};

    public Customer() {
        super(mc);
    }

    public Customer(String ht, int cmnd, String ngS) {
        if (ht == null || cmnd < 0 || ngS == null) {
            throw new RuntimeException("Lỗi dữ liệu");
        }
        ten = ht;
        maKH = "C" + count++;
        matKhau = Integer.toString(cmnd);
        this.cmnd = cmnd;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        dateFormat.setLenient(false);//validate of date
        try {
            ngaySinh = dateFormat.parse(ngS);
        } catch (ParseException e) {
            throw new RuntimeException("Ngày sinh không hợp lệ !");
        }
    }

    public Customer(Customer c) {
        if (c == null) {
            throw new RuntimeException("Lỗi dữ liệu !");
        }
        this.maKH = c.maKH;
        this.cmnd = c.cmnd;
        this.matKhau = c.matKhau;
        this.ten = c.ten;
        this.ngaySinh = c.ngaySinh;
    }

    public Customer(String maKH, String ht, String matKhau, int cmnd, String ngS) {
        if (maKH == null || ht == null || matKhau == null || cmnd < 0 || ngS == null) {
            throw new RuntimeException("Lỗi dữ liệu");
        }
        this.maKH = maKH;
        this.ten = ht;
        this.matKhau = matKhau;
        this.cmnd = cmnd;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);//validate of date
        try {
            ngaySinh = dateFormat.parse(ngS);
        } catch (ParseException e) {
            throw new RuntimeException("Ngày sinh không hợp lệ !");
        }
    }

    @Override
    public void execute(int n) {

    }

    public String toString() {
        return String.format("\n%-7s| %-16s| %-15s", getMaKH(), getTen(), getMatKhau());
    }

    @Override
    public int compareTo(Customer o) {
        return this.ten.compareTo(o.ten);
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getCmnd() {
        return cmnd;
    }

    public void setCmnd(int cmnd) {
        this.cmnd = cmnd;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Customer.count = count;
    }

}
