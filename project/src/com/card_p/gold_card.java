package com.card_p;

public class gold_card extends card{

    public gold_card(String car_number, String car_host, String phone_number, double card_rest) {
        super(car_number, car_host, phone_number, card_rest);
    }
    @Override
    public void consume(double money){
        if (money * 0.8 <= getCard_rest() && money > 0) {
            System.out.println("当前消费:"+money);
            System.out.println("优惠后价格:"+money*0.8);
            setCard_rest(getCard_rest()-money*0.8);
            if (money * 0.8 > 200)
                ticket();
        }else if (money * 0.8 > getCard_rest()){
            System.out.println("消费额过大，超过卡片余额:" + getCard_rest());
        }else {
            System.out.println("消费额必须大于0");
        }
    }
    public void ticket(){
        System.out.println("恭喜您获得一张 ticket");
    }

}
