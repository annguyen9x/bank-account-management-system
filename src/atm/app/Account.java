/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm.app;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author An Nguyen
 */
public class Account extends Customer {

    private int soTK, pin;
    private Double soDu = 100D;
    private static int countSoTK = 10000;
    static Vector<Transaction> transactionDiary = new Vector<Transaction>(10, 5);
    String menu[] = {"Giao dịch tại ATM Vietcombank", "Rút tiền", "Chuyển tiền", "Đổi pin", "Xem số dư", "Xem nhật ký giao dịch"};

    public Account() {
        super();
    }

    public Account(String ht, int cmnd, String ngS, double sd, int pin) {
        super(ht, cmnd, ngS);
        this.pin = pin;
        this.soDu = sd;
        this.soTK = countSoTK++;
    }

    public Account(Customer c, double sd, int pin) {
        super(c);
        this.soDu = sd;
        this.pin = pin;
        this.soTK = countSoTK++;
    }

    public Account(Customer c, int soTK, int pin, double sd) {
        super(c);
        this.soTK = soTK;
        this.soDu = sd;
        this.pin = pin;
    }

    public Account accountLogIn(int stk, int pin) {
        if (this.soTK == stk && this.pin == pin) {
            return this;
        }
        return null;
    }

    public void doWithdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t\t------- Rút tiền -------");
        System.out.println("Số tiền cần rút: ");
        Double st = sc.nextDouble();
        String mt = "Rút tiền mặt tại ATM";
        try {
            withdraw(st, mt);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public double withdraw(double sotien, String mota) {
        if (sotien > this.soDu) {
            throw new RuntimeException("Số dư không đủ để rút tiền");
        }
        if (sotien <= soDu) {
            transactionDiary.add(new Transaction(this, sotien, "Rút Tiền", mota));
            return this.soDu -= sotien;
        }
        return soDu;
    }

    public void doTransferMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t\t------- Chuyển tiền -------");
        System.out.println("Số tài khoản nhận tiền: ");
        int stk = sc.nextInt();
        Account a = Bank.getAccount(stk);
        System.out.println("Số tiền cần chuyển: ");
        Double st = sc.nextDouble();
        System.out.println("Nội dung chuyển tiền: ");
        String mt = sc.nextLine();
        try {
            transferMoney(a, st, this.getTen() + " chuyển tiền cho " + a.getTen() + ", nội dung: " + mt);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void transferMoney(Account a, double sotien, String mota) {
        if (a == null) {
            throw new RuntimeException("Không tìm thấy tài khoản nhận tiền !");
        }
        if (a == this) {
            throw new RuntimeException("Tài khoản nhận tiền phải khác tài khoản chuyển tiền !");
        }
        if (sotien > soDu) {
            throw new RuntimeException("Số dư không đủ để chuyển tiền !");
        }

        transactionDiary.add(new Transaction(this, sotien, "Chuyển Khoản", mota));
        transactionDiary.add(new Transaction(a, sotien, "Nhận Chuyển Khoản", mota));
        this.soDu -= sotien;
        a.soDu += sotien;
    }

    public void doChangePin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t\t------- Đổi Pin -------");
        System.out.println("Nhập pin mới: ");
        int pin = sc.nextInt();
        System.out.println("Nhập lại pin mới: ");
        int pin2 = sc.nextInt();
        String mota = "Đổi pin tại ATM";
        if (pin < 100000) {
            System.out.println("Pin phải có 6 chữ số !");
            return;
        }
        if (pin != pin2) {
            System.out.println("Pin nhập lại không chính xác !");
            return;
        }
        try {
            changePin(pin, mota);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

    }

    public void changePin(int newpin, String mota) {
        if (newpin == this.pin) {
            throw new RuntimeException("Pin mới phải khác Pin cũ !");
        }
        transactionDiary.add(new Transaction(this, 0, "Đổi Pin", mota));
        this.pin = newpin;
    }

    public void checkBalance() {
        System.out.println("\n\n\t\t------- Kiểm tra số dư -------");
        this.getSoDu();
        transactionDiary.add(new Transaction(this, 0, "KTSD", "Kiểm tra số dư tại ATM"));
    }

    public void viewTransactionDiary() {
        System.out.println("\n\n\t\t------- Nhật ký GD -------");
        for (Transaction t : transactionDiary) {
            System.out.println(t.toString());
        }
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                doWithdraw();
                break;
            case 2:
                doTransferMoney();
                break;
            case 3:
                doChangePin();
                break;
            case 4:
                checkBalance();
                break;
            case 5:
                viewTransactionDiary();
                break;
        }
    }

    public int getSoTK() {
        return soTK;
    }

    public void setSoTK(int soTK) {
        this.soTK = soTK;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Double getSoDu() {
        return soDu;
    }

    public void setSoDu(Double soDu) {
        this.soDu = soDu;
    }

    public static int getCountSoTK() {
        return countSoTK;
    }

    public static void setCountSoTK(int countSoTK) {
        Account.countSoTK = countSoTK;
    }

    public static Vector<Transaction> getTransactionDiary() {
        return transactionDiary;
    }

    public static void setTransactionDiary(Vector<Transaction> transactionDiary) {
        Account.transactionDiary = transactionDiary;
    }

    @Override
    public String toString() {
        return "Account{" + "soTK=" + soTK + ", pin=" + pin + ", soDu=" + soDu + '}';
    }

}
