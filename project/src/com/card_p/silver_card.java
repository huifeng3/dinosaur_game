package com.card_p;

public class silver_card extends card{

    public silver_card(String car_number, String car_host, String phone_number, double card_rest) {
        super(car_number, car_host, phone_number, card_rest);
    }

    @Override
    public void consume(double money) {
        if (money * 0.9 <= getCard_rest() && money > 0) {
            System.out.println("当前消费:"+money);
            System.out.println("优惠后价格:"+money*0.9);
            setCard_rest(getCard_rest()-money*0.9);
        }else if (money * 0.9> getCard_rest()){
            System.out.println("消费额过大，超过卡片余额:" + getCard_rest());
        }else {
            System.out.println("消费额必须大于0");
        }
    }
}
