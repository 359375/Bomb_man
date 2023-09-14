package demolition;

import java.util.List;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class gameSetenemy extends gamePeople implements touch{

    public String dir;
    protected Boolean hit; 

    private int imageCount;
    private PImage[] images ;
    protected int originX;
    protected int originY;
    private Random rand;
    protected int i;
    protected List<enemyYellow> enemyYellowsarr;
    protected List<enemyRed> enemyRedsarr;
    public gameSetenemy(int x, int y,PImage pi,List<wall> wallarr,List<brokenwall> broarr,player playarr ,PImage[] a, List<enemyYellow> enemyYellowsarr, List<enemyRed> enemyRedsarr) {
        super(x, y, wallarr, broarr, pi);
        this.dir = "DOWN";
        this.hit = true;

        this.images = a;
        this.originX= x;
        this.originY = y;
        this.rand =new Random(4);
        i=0;
        this.enemyRedsarr =enemyRedsarr;
        this.enemyYellowsarr = enemyYellowsarr;
    }

    public void tick(int count){
        this.i=rand.nextInt(4);
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
          

    public String  movingdown(){
        this.moving();
        locationBeforeX=x;
        locationBeforeY=y;
        y+=yVel;
        this.yVel = 0;
        return "DOWN";
    }

    public String movingup(){
        this.moving();
        locationBeforeX=x;
        locationBeforeY=y;
        y-=yVel;
        this.yVel = 0;
        return "UP";
    }

    public String movingleft(){
        this.moving();
        locationBeforeX=x;
        locationBeforeY=y;
        x-=xVel;
        this.xVel = 0;
        return "LEFT";
    }

    public String  movingright(){
        this.moving();
        locationBeforeX=x;
        locationBeforeY=y;
        x+=xVel;
        this.xVel = 0;
        return "RIGHT";
    }
    
    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }


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

    public abstract void moving(int count);
}