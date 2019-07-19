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
    static String[] mc = {"Menu 2", "Vietcombank Internet Banking welcome !", "Đăng ký thêm tài khoản ngân hàng", "Xem danh sách tài khoản đang sở hữu", "Đăng nhập vào tài khoản ngân hàng", "Đổi mật khẩu đăng nhập thành viên", "Quay lại Menu 1", "Thoát"};

    public Customer() {
        super();
    }

    public Customer(String ht, int cmnd, String ngS) {
        if (ht == null || cmnd < 0 || ngS == null) {
            throw new RuntimeException("Lỗi dữ liệu");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        dateFormat.setLenient(false);//validate of date
        try {
            ngaySinh = dateFormat.parse(ngS);
            ten = ht;
            maKH = "C" + count++;
            matKhau = Integer.toString(cmnd);
            this.cmnd = cmnd;
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);//validate of date
        try {
            ngaySinh = dateFormat.parse(ngS);
            this.maKH = maKH;
            this.ten = ht;
            this.matKhau = matKhau;
            this.cmnd = cmnd;
        } catch (ParseException e) {
            throw new RuntimeException("Ngày sinh không hợp lệ !");
        }
    }

    public void setMenu() {
        super.setMenu(mc);
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                System.out.println("Đăng ký thêm tài khoản ngân hàng");
                break;
            case 2:
                showAccountOfCustomer(this);
                break;
            case 3:
                System.out.println("Đăng nhập vào tài khoản ngân hàng");
                break;
            case 4:
                System.out.println("Đổi mật khẩu đăng nhập thành viên");
                break;
            case 5:
                Bank b = new Bank(Bank.mc);
                b.run();
                break;
            case 6:
                System.out.println("Thoát");
                break;
        }
    }
    
    public void showAccountOfCustomer(Customer c){
        boolean check = false;
        System.out.println("\n------- Các số tài khoản của \""+ c.ten + "\" -------");
        System.out.printf("%-7s| %-16s| %-15s", "Mã KH", "Họ tên", "Số tài khoản");
        for(Account a: Bank.getaList()){
            if( a.maKH.equals(c.maKH) ){
                 System.out.printf("\n%-7s| %-16s| %-15s",a.maKH,a.ten,a.getSoTK());
                 check = true;
            }
        }
        if( check == false ){
            System.out.println("Danh sách rỗng !");
        }
        System.out.println("\n\t --------- *** ---------");
    }

    @Override
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
