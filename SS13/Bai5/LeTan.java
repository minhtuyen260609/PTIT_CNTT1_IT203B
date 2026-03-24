package Bai5;

import java.util.Scanner;

public class LeTan {
    private Scanner sc = new Scanner(System.in);
    private BenhNhan controller = new BenhNhan();

    public void runMenu() {
        while (true) {
            System.out.println("===== MENU =====");
            System.out.println("1. Xem giuong trong");
            System.out.println("2. Tiep nhan benh nhan");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            int chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    controller.xemGiuongTrong();
                    break;
                case 2:
                    tiepNhan();
                    break;
                case 0:
                    System.out.println("Tam biet!");
                    return;
                default:
                    System.out.println("Chon sai!");
            }
        }
    }

    private void tiepNhan() {
        try {
            System.out.print("Ten BN: ");
            String ten = sc.nextLine();

            System.out.print("Tuoi: ");
            int tuoi = Integer.parseInt(sc.nextLine());

            System.out.print("Ma giuong: ");
            int maGiuong = Integer.parseInt(sc.nextLine());

            System.out.print("Tien tam ung: ");
            double tien = Double.parseDouble(sc.nextLine());

            controller.tiepNhanBenhNhan(ten, tuoi, maGiuong, tien);

        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang!");
        }
    }
}
