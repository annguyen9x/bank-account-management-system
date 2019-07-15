/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author An Nguyen
 */
public class Bank extends Menu {

    private static Vector<Account> aList = new Vector<Account>(20, 10);
    private static Vector<Customer> cList = new Vector<Customer>(20, 10);
    static String[] mc = {"Vietcombank banking counters welcome !", "Đăng ký thành viên", "Đăng nhập thành viên", "Nộp tiền vào tài khoản", "Xem danh sách thành viên", "Kết thúc"};
    Customer curenrCustomer;
    Account currentAccount;

    public Bank() {
        super(mc);
        loadData("account.txt");
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                customerRegistration();
                break;
            case 2:
                try {
                    curenrCustomer = customerLogIn();
                    System.out.println("Đăng nhập thành công, xin chào " + curenrCustomer.getTen() + " !");
                    curenrCustomer.run();
                } catch (Exception e) {
                    System.out.println("Lỗi: " + e.getMessage());
                }
                break;
            case 3:
                doDepositCash();
                break;
            case 4:
                viewCustomerList();
                break;
            case 5:
                saveData("account.txt");
                System.out.println("\t\tVietcombank hẹn gặp lại quý khách !");
                System.exit(0);
        }
    }

    public void customerRegistration() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n------- Đăng ký thành viên -------");

        String ten;
        int cmnd = 0;
        String ngaySinh = null;
        try {
            System.out.println("Nhập tên");
            ten = sc.nextLine();
            System.out.println("Nhập cmnd");
            cmnd = Integer.parseInt(sc.nextLine());
            System.out.println("Nhập ngày sinh");
            ngaySinh = sc.nextLine();
            Customer c = new Customer(ten, cmnd, ngaySinh);
            cList.add(c);

            //Random rand = new Random();
            //int randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
            Random rd = new Random();
            int pin = 100000 + rd.nextInt((900000 - 100000) + 1);//pin = [100000, 900000]
            double soDu = 100;
            Account a = new Account(pin, soDu);
            aList.add(a);
            System.out.println("\n\tĐăng ký tài khoản thành công, xin chào " + ten + "\nMã KH của bạn: " + c.getMaKH() + "\nMật khẩu mặc định: " + c.getMatKhau());
            System.out.println("Bạn được cấp số tài khoản: " + a.getSoTK() + "\nPin mặc định: " + a.getPin() + "\nSố dư Mặc định: " + a.getSoDu());
        } catch (Exception e) {
            System.out.println("Lỗi nhập sai dữ liệu !");
        }
    }

    public Customer customerLogIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n------- Đăng nhập thành viên -------");
        System.out.println("Mã khách hàng: ");
        String maKH = sc.nextLine();
        System.out.println("Mật khẩu: ");
        String matKhau = sc.nextLine();
        for (Customer c : cList) {
            if (maKH.equals(c.maKH) && matKhau.equals(c.matKhau)) {
                return c;
            }
        }
        throw new RuntimeException("Tài khoản và mật khẩu không đúng !");
    }

    public void doDepositCash() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n------- Nộp tiền vào tài khoản -------");
        System.out.println("Nhập số tài khoản: ");
        int stk = sc.nextInt();
        
        Account a = getAccount(stk);
        try {
            double tienNop = depositCash(a);
            double soDuCu = a.getSoDu();
            a.setSoDu(tienNop + soDuCu);
        } catch (Exception e) {
            System.out.println("Lỗi nộp tiền vào tài khoản !");
        }
    }

    public double depositCash(Account a) {
        if (a == null ) {
            throw new RuntimeException("Số tài khoản không tồn tại !");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Số tiền gửi vào tài khoản: ");
        double tienNop = sc.nextDouble();
        String mota = "Nộp tiền vào Tài khoản";
        if (tienNop <= 0) {
            throw new RuntimeException("Tiền nộp vào tài khoản phải > 0");
        }
        a.transactionDiary.add(new Transaction(a,tienNop,"Nộp Tiền",mota));
        return tienNop;
    }

    public void viewCustomerList() {
        System.out.println("\n------- Danh sách thành viên -------");
        Collections.sort(cList);
        for (Customer c : cList) {
            System.out.println(c);
        }
        System.out.println("   ------- *** -------");
    }

    public void saveData(String path) {
        File fileName = new File(path);
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Account a : aList) {
                bw.write(a.getMaKH() + "::" + a.getTen() + "::" + a.getMatKhau() + "::" + a.getCmnd() + "::" + a.getNgaySinh()
                        + "::" + a.getSoTK() + "::" + a.getPin() + "::" + a.getSoDu() + "\n");
            }
            fw.close();
            bw.close();

        } catch (IOException ex) {
            System.out.println("Lỗi lưu file !");
        }
    }

    public void loadData(String path) {
        try {
            FileReader fr = new FileReader(path);

            BufferedReader br = new BufferedReader(fr);
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] array = line.trim().split("::");
                Customer c = createCustomer(array);
                cList.add(c);
                Account a = createAccount(array);
                aList.add(a);
            }

            br.close();
            fr.close();
        } catch (Exception ex) {
            System.out.println("Lỗi load file: " + ex.getMessage());
        }
    }

    public Customer createCustomer(String[] array) {
        String maKH = array[0];
        String ht = array[1];
        String matKhau = array[2];
        int cmnd = Integer.parseInt(array[3]);
        String ngaySinh = array[4];

        int count = Integer.parseInt(array[0].trim().substring(1, 5));
        Customer.setCount(count);
        return new Customer(maKH, ht, matKhau, cmnd, ngaySinh);
    }

    public Account createAccount(String[] array) {
        int soTK = Integer.parseInt(array[5]);
        int pin = Integer.parseInt(array[6]);
        double soDu = Double.parseDouble(array[7]);
        Account.setCountSoTK(soTK);
        return new Account(soTK, pin, soDu);
    }

    public static Account getAccount(int soTK) {
        for (Account a : aList) {
            if (soTK == a.getSoTK()) {
                return a;
            }
        }
        return null;
    }

}
