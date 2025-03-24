package com.dinosaur;
import javax.swing.*;

public class game {
    public static void main(String[] args) throws Exception {
        int boardWidth=750;
        int boardHeight=250;
        JFrame frame=new JFrame("Chrome dinosaur");
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        dinosaur_game dinosaur_game =new dinosaur_game();
        frame.add(dinosaur_game);
        dinosaur_game.requestFocus();
        frame.pack();

        frame.setVisible(true);

    }
}
