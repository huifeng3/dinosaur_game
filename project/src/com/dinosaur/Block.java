package com.dinosaur;

import java.awt.*;

public class Block{
    int x;
    int y;
    int width;
    int height;
    Image img;
    int velocityX;
    public Block(int x, int y, int width, int height, Image img, int velocityX) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.velocityX = velocityX;
    }
}
