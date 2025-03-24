package com.dinosaur;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;

public class dinosaur_game extends JPanel implements ActionListener, KeyListener{
    int boardWidth=800;
    int boardHeight=400;
    Image dinosaurImg;
    Image dinosaurDeadImg;
    Image dinosaurJumpImg;
    Image dinosaurDuckImg;

    Image cactus1Img;
    Image cactus2Img;
    Image cactus3Img;
    Image cactus4Img;
    Image cactus5Img;
    Image cactus6Img;

    Image birdImg;
    Image birdDeadImg;

    Image cloudImg;

    Image trackImg;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode()==KeyEvent.VK_UP){
            if(jumpCount == 2){
                dinosaurVelocityY=-25;
                dinosaur.img=dinosaurJumpImg;
                jumpCount--;
            }
            else if(jumpCount == 1){
                if (dinosaurVelocityY <= 0)
                    dinosaurVelocityY+=-15;
                else
                    dinosaurVelocityY=-20;
                dinosaur.img=dinosaurJumpImg;
                jumpCount--;
            }

            if(gameOver){
                dinosaur.y=dinosaurY;
                dinosaur.img=dinosaurImg;
                dinosaurVelocityY=0;
                cactusArray.clear();
                birdArray.clear();
                cloudArray.clear();
                score=0;
                gameOver=false;
                gameLoop.start();
                placeTimer.start();
            }
        }
        if(!isDuck && dinosaur.y == dinosaurY && e.getKeyCode()==KeyEvent.VK_DOWN){
            dinosaur.y += dinosaurHeight / 2;
            dinosaurY += dinosaurHeight / 2;
            dinosaur.height = dinosaurHeight / 2;
            dinosaur.img=dinosaurDuckImg;
            repaint();
            isDuck = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (isDuck && e.getKeyCode()==KeyEvent.VK_DOWN){
            dinosaur.y -= dinosaurHeight / 2;
            dinosaurY -= dinosaurHeight / 2;
            dinosaur.height = dinosaurHeight;
            dinosaur.img = dinosaurImg;
            repaint();
            isDuck = false;
        }
    }

    int jumpCount = 2;

    int dinosaurWidth =70;
    int dinosaurHeight=70;
    int dinosaurX=50;
    int dinosaurY=boardHeight-dinosaurHeight;
//    Block normalDinosaur;
//    Block duckDinosaur;
    Block dinosaur;

    //cactus
    int smallCactus1Width=34;
    int smallCactus2Width=69;
    int smallCactus3Width=102;

    int bigCactus1Width= 45;
    int bigCactus2Width= 80;
    int bigCactus3Width= 135;

    int smallCactusHeight=60;
    int bigCactusHeight=80;
    int cactusX=boardWidth;
    int smallCactusY=boardHeight-smallCactusHeight;
    int bigCactusY=boardHeight-bigCactusHeight;
    ArrayList<Block> cactusArray;

    //bird
    int birdX = boardWidth;
    int birdHeight=25;
    int birdWidth=40;
    ArrayList<Block> birdArray;

    //cloud
    int cloudX = boardWidth;
    int cloudHeight=100;
    int cloudWidth=150;
    ArrayList<Block> cloudArray;

    //physics
    int dinosaurVelocityX=-12;
    int dinosaurVelocityY=0;//dinosaur jump speed
    int gravity=2;

    boolean gameOver=false;
    int score=0;

    //status
    boolean isDuck;

    Timer gameLoop;
    Timer placeTimer;

    public dinosaur_game(){
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);

        dinosaurImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/dino-run.gif"))).getImage();
        dinosaurDeadImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/dino-dead.png"))).getImage();
        dinosaurJumpImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/dino-run1.png"))).getImage();
        dinosaurDuckImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/dino-duck.gif"))).getImage();

        cactus1Img=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/cactus1.png"))).getImage();
        cactus2Img=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/cactus2.png"))).getImage();
        cactus3Img=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/cactus3.png"))).getImage();
        cactus4Img=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/big-cactus1.png"))).getImage();
        cactus5Img=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/big-cactus2.png"))).getImage();
        cactus6Img=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/big-cactus3.png"))).getImage();

        birdImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/bird.gif"))).getImage();
        birdDeadImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/bird1.png"))).getImage();

        cloudImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/cloud.png"))).getImage();

        trackImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/dinosaur/img/track.png"))).getImage();

//        normalDinosaur = new Block(dinosaurX,dinosaurY,dinosaurWidth,dinosaurHeight,dinosaurImg,0);
//        duckDinosaur = new Block(dinosaurX,dinosaurY + dinosaurHeight / 2,dinosaurWidth,dinosaurHeight / 2,dinosaurDuckImg,0);

        dinosaur= new Block(dinosaurX,dinosaurY,dinosaurWidth,dinosaurHeight,dinosaurImg,0);
        isDuck = false;

        //cactus
        cactusArray=new ArrayList<Block>();
        birdArray= new ArrayList<Block>();
        cloudArray= new ArrayList<Block>();
        gameLoop =new Timer(1000/60,this);
        gameLoop.start();

        placeTimer=new Timer(800,new ActionListener(){
            public void actionPerformed(ActionEvent e){
                double placeChance = Math.random();
                if (placeChance > .15)
                    placeCactus();
                if (placeChance > .50)
                    placeBird();
                if (placeChance > .20)
                    placeCloud();
                //score
                score++;
                repaint();
            }
        });
        placeTimer.start();
    }

    void placeCactus(){
        if(gameOver){
            return;
        }
        double placeCactusChance=Math.random();
        if(placeCactusChance>.90){
            Block cactus=new Block(cactusX,bigCactusY,bigCactus3Width,bigCactusHeight,cactus6Img, dinosaurVelocityX);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance>.70){
            Block cactus=new Block(cactusX,smallCactusY,smallCactus3Width,smallCactusHeight,cactus3Img, dinosaurVelocityX);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance>.50){
            Block cactus=new Block(cactusX,bigCactusY,bigCactus1Width,bigCactusHeight,cactus4Img, dinosaurVelocityX);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance>.35){
            Block cactus=new Block(cactusX,smallCactusY,smallCactus1Width,smallCactusHeight,cactus1Img, dinosaurVelocityX);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance>.20){
            Block cactus=new Block(cactusX,bigCactusY,bigCactus2Width,bigCactusHeight,cactus5Img, dinosaurVelocityX);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance>.10){
            Block cactus=new Block(cactusX,smallCactusY,smallCactus2Width,smallCactusHeight,cactus2Img, dinosaurVelocityX);
            cactusArray.add(cactus);
        }
        if(cactusArray.size()>5){
            cactusArray.remove(0);
        }
    }

    void placeBird(){
        if(gameOver){
            return;
        }
        double placeBirdHeight=Math.random();
        double BirdVelocity=Math.random();
        int birdY = (int) (boardHeight / 2. - placeBirdHeight * (boardHeight / 4.));
        int BirdVelocityX = - (int) (- dinosaurVelocityX + 4 + 6 * BirdVelocity);
        Block bird=new Block(birdX,birdY,birdWidth,birdHeight,birdImg, BirdVelocityX);
        birdArray.add(bird);

        if(birdArray.size()>5){
            birdArray.remove(0);
        }
    }


    void placeCloud(){
        if (gameOver)
            return;
        double cloudSizeChance=Math.random();
        double placeCloudHeight=Math.random();
        int cloudY = (int) (boardHeight * (2. / 3.) - placeCloudHeight * (boardHeight / 3.));
        Block cloud=new Block(cloudX,cloudY,cloudWidth,cloudHeight,cloudImg, dinosaurVelocityX);
        cloudArray.add(cloud);

        if(cloudArray.size()>5){
            cloudArray.remove(0);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(dinosaur.img,dinosaur.x,dinosaur.y,dinosaur.width,dinosaur.height,null);
        for (Block cactus : cactusArray) {
            g.drawImage(cactus.img, cactus.x, cactus.y, cactus.width, cactus.height, null);
        }
        for (Block bird : birdArray) {
            g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        }
        for (Block cloud : cloudArray) {
            g.drawImage(cloud.img, cloud.x, cloud.y, cloud.width, cloud.height, null);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Courier",Font.PLAIN,32));
        if(gameOver){
            g.drawString("Game Over: "+String.valueOf(score),10,35);
        }
        else{
            g.drawString(String.valueOf(score),10,35);
        }
    }

    boolean collision(Block a,Block b){
        return !(a.y + a.height - 3 < b.y + 3 || a.y + 3 > b.y + b.height - 3) &&
                !(a.x + a.width - 3 < b.x + 3 || a.x + 3 > b.x + b.width - 3);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        move();
        repaint();
        if(gameOver){
            placeTimer.stop();
            gameLoop.stop();
        }
    }
    public void move(){
        dinosaurVelocityY+=gravity;
        dinosaur.y+=dinosaurVelocityY;
        if(dinosaur.y>dinosaurY){//stop dinosaur from falling below ground
            dinosaur.y=dinosaurY;
            dinosaurVelocityY=0;
            if (isDuck) {
                dinosaur.img = dinosaurDuckImg;
            } else {
                dinosaur.img = dinosaurImg;
            }
        }
        if (dinosaur.y == dinosaurY && jumpCount < 2) {
            jumpCount = 2;
        }
        //cactus
        for (Block cactus : cactusArray) {
            cactus.x += cactus.velocityX;
            if (collision(dinosaur, cactus)) {
                gameOver = true;
                dinosaur.img = dinosaurDeadImg;
            }
        }
        for (Block bird : birdArray) {
            bird.x += bird.velocityX;
            if (collision(dinosaur, bird)) {
                gameOver = true;
                dinosaur.img = dinosaurDeadImg;
                bird.img = birdDeadImg;
            }
        }
        boolean cloudFlag = false;
        for (Block cloud : cloudArray) {
            cloud.x += cloud.velocityX;
            if (dinosaur.y + dinosaur.height <= cloud.y + cloud.height / 3  && !(dinosaur.x + dinosaur.width < cloud.x || dinosaur.x > cloud.x + cloud.width )){
                if (dinosaurY + dinosaur.height > cloud.y + cloud.height / 3)
                    dinosaurY = cloud.y + cloud.height / 3 - dinosaur.height;
                cloudFlag = true;
            }
        }
        if (!cloudFlag){
            dinosaurY = boardHeight - dinosaur.height;
        }
    }
}
