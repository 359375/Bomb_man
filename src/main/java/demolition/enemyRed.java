package demolition;

import java.util.List;

import processing.core.*;
import java.util.Random;

public class enemyRed extends gameSetenemy{


    private PImage[] images;
    private int imageCount;

    private Random rand;
    private int i;
    public enemyRed(int x, int y, PImage pi, List<wall> wallarr, List<brokenwall> broarr, player play,PImage[] a, List<enemyYellow> enemyYellowsarr, List<enemyRed> enemyRedsarr) {
        super(x, y, pi, wallarr, broarr, play, a,enemyYellowsarr,enemyRedsarr);
        imageCount = 0;
        this.images=a;
        this.broarr= broarr;
        this.wallarr = wallarr;
        this. alive = true;
        this.dir = "DOWN";
        this.rand =new Random(4);
        this.i= 0;
    }

    public void tick(int count){

        i=rand.nextInt(4);
        if(alive){
            if(count %15 == 0) {
                imageCount++;
                }
                if (imageCount>(images.length-1)) {
                    imageCount  = 0;
                }
        }
        this.checkCollision();

    }
    public void draw(PApplet p,PImage[] a){
        this.i = rand.nextInt(4);
        if(alive){
         
            images=a;
            p.image(images[imageCount], this.x, this.y);
        }
        else if (alive == false){
           
            images=a;
            p.image(images[imageCount], locationBeforeX, locationBeforeY);
            this.x= locationBeforeX;
            this.y = locationBeforeY;
            alive = true;
        } 


        checkCollision();
    }

    public void moving(){
         
        this.yVel = 32;
        this.xVel = 32;
   }

    public void moving(int count){
        Boolean footClide = false;
        Boolean leftClide = false;
        Boolean headClide = false;
        Boolean rightClide = false;
        for(wall wall : this.wallarr){
            int wallleft = wall.getx();
            int wallBottom = wall.gety() + wall.getHeight();
            int playerBottom = this.y + this.pi.height;
            if (   (x== wallleft && playerBottom+32 == wallBottom) ) {
                footClide = true;
                
            }            
            if (   (x-32== wallleft && playerBottom == wallBottom) ) {
                leftClide = true;
            }
            if (   (x+32== wallleft && playerBottom == wallBottom) ) {
                rightClide = true;
            }
            if (   (x== wallleft && playerBottom-32 == wallBottom) ) {
                headClide = true;
            }
        }
        for(brokenwall wall : this.broarr){
            int wallleft = wall.getx();
            int wallBottom = wall.gety() + wall.getHeight();
            int playerBottom = this.y + this.pi.height;
            if (   (x== wallleft && playerBottom+32 == wallBottom) ) {
                footClide = true;
                
            }            
            if (   (x-32== wallleft && playerBottom == wallBottom) ) {
                leftClide = true;
            }
            if (   (x+32== wallleft && playerBottom == wallBottom) ) {
                rightClide = true;
            }
            if (   (x== wallleft && playerBottom-32 == wallBottom) ) {
                headClide = true;
            }
        }
        if(count % 60 ==0 && alive == true){

            if (( footClide == true && dir == "DOWN") ){
                searchDir();
            } else if (leftClide == true && dir == "LEFT"){
                searchDir();
            }else if (headClide == true && dir == "UP"){
                searchDir();
            }else if (rightClide == true && dir == "RIGHT"){
                searchDir();
            }else if(leftClide == false && dir == "LEFT" ){
                movingleft();
            }else if(footClide == false && dir == "DOWN"){
                movingdown();
            }else if(rightClide == false && dir == "RIGHT" ){
                movingright();
            }else if(headClide == false && dir == "UP" ){
                movingup();
            }
            }
        }
       
            
    public String searchDir(){
        String a = "";
        switch(this.i){
            case 0: dir=movingdown(); break;
            case 1:dir=movingup(); break;
            case 2:dir=movingleft(); break;
            case 3:dir=movingright(); break;
        }
       return a;
    }      

    


}