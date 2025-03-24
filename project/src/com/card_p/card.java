package com.card_p;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class card {
    private String car_number;
    private String car_host;
    private String phone_number;
    private double card_rest;

    public void depoist(double money){
        card_rest+=money;
    }

    public void consume(double money){
        card_rest-=money;
    }
}
