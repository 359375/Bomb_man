package demolition;

import java.util.List;


import processing.core.*;


public class player extends gameSetplayer implements touch{
    private PImage[] images ;
    private int imageCount;
    private boolean alive;
    private PImage pi;
    private boolean hit;
    private int originX;
    private int originY;
    private List<enemyYellow> yellowArr;
    private List<enemyRed> redArr;
    private List<goal> goalArr;
    private boolean isWin;
    public player(int x, int y,PImage pi,List<wall> wallarr,List<brokenwall> broarr,List<enemyYellow> yellowArr,List<enemyRed> redArr,PImage[] a,List<goal> goalArr ) {
        super(x, y, pi,wallarr,broarr);
        originX= x;
        originY=y;
        this.pi = pi;
        imageCount = 0;
        this.images=a;
        this.alive = true;
        this.hit = true;
        this.yellowArr= yellowArr;
        this.redArr =redArr;
        this.goalArr = goalArr;
        isWin = false;
    }

    public void  movingdown(){
        this.moving();
        locationBeforeX=x;
        locationBeforeY=y;
        y+=yVel;
        this.yVel = 0;
    
    }

    public void movingup(){
        this.moving();
        locationBeforeX=x;
        locationBeforeY=y;
        y-=yVel;
        this.yVel = 0;
 
    }

    public void movingleft(){
        this.moving();
        locationBeforeX=x;
        locationBeforeY=y;
        x-=xVel;
        this.xVel = 0;
 
    }

    public void  movingright(){
        this.moving();
        locationBeforeX=x;
        locationBeforeY=y;
        x+=xVel;
        this.xVel = 0;
  
    }

    //logic
    public void tick(int count){
        if(alive){
            if(count %15 == 0) {
                imageCount++;
                }
                if (imageCount>(images.length-1)) {
                    imageCount  = 0;
                }
        }else if(hit){
            if(count %30 == 0) {
                imageCount++;
                }
                if (imageCount>(images.length-1)) {
                    imageCount  = 0;
                }
                hit=true;
        }
        this.checkwin();
        this.checkCollision();
        this.hitEnemy();
    }

    public void draw(PApplet p,PImage[] a){
        
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
        if (hit == false){
            
            images=a;
            p.image(images[imageCount], this.originX, this.originY);
            this.x=originX;
            this.y = originY;
            hit = true;
            imageCount=0;
        }
        this.checkwin();
        hitEnemy();
        checkCollision();
    }

    //abstract write from gameset
    public void collide(){
        this.alive = false; 
    }
     //abstract write from gameset

    public void checkCollision(){
        for(wall wall : this.wallarr){
            int wallleft = wall.getx();
            int wallBottom = wall.gety() + wall.getHeight();
            int playerBottom = this.y + this.pi.height;
            if (   (x== wallleft && playerBottom == wallBottom) ) {
                this.collide();
                
                break;
            }
        }
        for(brokenwall wall : this.broarr){
            int wallleft = wall.getx();
            int wallBottom = wall.gety() + wall.getHeight();
            int playerBottom = this.y + this.pi.height;
            if (   (x== wallleft && playerBottom == wallBottom) ) {
                this.collide();
                break;
            }
        }
    }

    public void checkwin(){
        for(goal wall : this.goalArr){
            int wallleft = wall.getx();
            int wallBottom = wall.gety() + wall.getHeight();
            int playerBottom = this.y + this.pi.height;
            if (   (x== wallleft && playerBottom == wallBottom) ) {
                this.isWin = true;
            }
        }

    }
    public void hit(){
        this.hit = false;
    }

    public  void hitEnemy(){ 
        for(enemyYellow wall : this.yellowArr){
            int wallleft = wall.getx();
            int wallBottom = wall.gety() + wall.getHeight();
            int playerBottom = this.y + this.pi.height;
            if (   (x== wallleft && playerBottom == wallBottom) ) {
                this.hit();
                System.out.println(1);
                for(enemyRed a : this.redArr){
                    a.setx(a.originX);
                    a.sety(a.originY);
                    a.setDir("DOWN");
                
                }
                for(enemyYellow a : this.yellowArr){
                    a.setx(a.originX);
                    a.sety(a.originY);
                    a.setDir("DOWN");
                }
                break;
            }
        }
        for(enemyRed wall : this.redArr){
            int wallleft = wall.getx();
            int wallBottom = wall.gety() + wall.getHeight();
            int playerBottom = this.y + this.pi.height;
            if ((x== wallleft && playerBottom == wallBottom) ) {
                this.hit();
                for(enemyYellow a : this.yellowArr){
                    a.setx(a.originX);
                    a.sety(a.originY);
                    a.setDir("DOWN");
                }
                wall.setx(wall.originX);
                wall.sety(wall.originY);
                wall.setDir("DOWN");
                break;
            }
        }
    }


    public boolean isHit() {
        return hit;
    }

    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean isWin) {
        this.isWin = isWin;
    }





    

}