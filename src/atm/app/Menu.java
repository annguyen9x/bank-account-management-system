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
public abstract class Menu {

    private Vector<String> luaChon = new Vector<String>(10, 5);

    public Menu() {

    }

    public Menu(String[] inLuaChon) {
        for (String lc : inLuaChon) {
            luaChon.add(lc);
        }
    }

    public void display() {
        System.out.println("\n\n\t\t\t" + luaChon.elementAt(0) + "\n");
        System.out.println("-------- *** --------");
        for (int i = 1; i < luaChon.size(); i++) {
            System.out.println(i + "." + luaChon.get(i));
        }
        System.out.println("-------- *** --------");
    }

    public int getSelected() {
        display();
        Scanner sc = new Scanner(System.in);
        String lc;
        int numberChoose = 0;
        System.out.println("Mời chọn mục: ");
        lc = sc.nextLine();
        try {
            numberChoose = Integer.parseInt(lc);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Phải nhập vào kiểu số nguyên !");
        }
        return numberChoose;
    }

    public abstract void execute(int n);

    public void run() {
        int lc = -1;
        do {
            lc = getSelected();
            if (lc >= luaChon.size() || lc <= 0) {
                System.out.println("Lựa chọn không đúng !\n\t\tVietcombank hẹn gặp lại quý khách !");
                System.exit(0);
            }
            execute(lc);
        } while (lc < luaChon.size() && lc > 0);
    }
}
