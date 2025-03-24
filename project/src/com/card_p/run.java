package com.card_p;
import java.util.Scanner;  // 导入 Scanner 类

public class run {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            line();
            System.out.println("1.办理金卡.");
            System.out.println("2.办理银卡.");
            System.out.println("3.退出系统.");
            System.out.println("欢迎来到加油站支付系统，请根据您的选择输入相应的数字:");

            switch (sc.nextInt()) {
                case 1:
                    make_card(1);
                    break;
                case 2:
                    make_card(2);
                    break;
                case 3:
                    sc.close();
                    return;
                default:
                    System.out.println("输入错误，请重新输入.");
                    break;
            }
        }
    }

    public static void line() {
        System.out.print("\n");
        System.out.println("============================================================");
        System.out.print("\n");
    }

    public static void make_card(int type){
        line();
        System.out.println("请输入你的车牌号码:");
        String car_number = sc.next();
        System.out.println("请输入你的姓名:");
        String name = sc.next();
        System.out.println("请输入你的电话号码:");
        String phone = sc.next();
        System.out.println("请输入你的预存款:");
        double money = sc.nextDouble();
        if (type == 1) {
            if (money < 5000) {
                System.out.print("\n");
                System.out.println("预存款低于5000，无法办理金卡.");
                return;
            }
        } else {
            if (money < 2000) {
                System.out.print("\n");
                System.out.println("预存款低于2000，无法办理银卡.");
                return;
            }
        }
        line();
        card card = null;
        if (type == 1) {
            card = new gold_card(car_number, name, phone, money);
            System.out.println("金卡创办成功");
        }else{
            card = new silver_card(car_number, name, phone, money);
            System.out.println("银卡创办成功");
        }
        while (true) {
            System.out.println("1.充值金额:");
            System.out.println("2.消费金额:");
            System.out.println("3.查询卡片信息:");
            System.out.println("4.退出当前界面:");
            System.out.println("请输入您的选择:");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("请输入你充值的金额:");
                    card.depoist(sc.nextDouble());
                    break;
                case 2:
                    System.out.println("请输入你消费的金额:");
                    card.consume(sc.nextDouble());
                    break;
                case 3:
                    line();
                    if (type == 1){
                        System.out.println("卡片类别-----金卡");
                    } else {
                        System.out.println("卡片类别-----银卡");
                    }
                    System.out.println("车牌号:" + card.getCar_number());
                    System.out.println("车主姓名:" + card.getCar_host());
                    System.out.println("电话号码:" + card.getPhone_number());
                    System.out.println("卡片余额:" + card.getCard_rest());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("输入错误，请重新输入.");
                    break;
            }
        }
    }
}
