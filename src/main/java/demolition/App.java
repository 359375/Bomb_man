package demolition;

import java.time.chrono.Era;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;

    public String mapString;
    private ArrayList<wall> wallarray;
    private ArrayList<empty> emptyarray;
    private ArrayList<goal>  goalarray;
    private ArrayList<brokenwall> brokenwallarray;
    private player player;
    private ArrayList<enemyRed> enemyRedarray;
    private ArrayList<enemyYellow> enemyYellowarray;
    private ArrayList<player> playerarray;
    private ArrayList<icon> icon;
    private PImage[] img;
    private PImage[] imgY;
    private PImage[] imgR;
    private PImage[] imgB;
    private PImage[] imgE;
    private ArrayList<bombefore> bombefore;
    
    private int lives;
    PFont font;
    private int timeCount;
    private ArrayList< time> timea;
    public App() {
        mapString = "";
        lives = 0;
        this.goalarray= new ArrayList<goal>();
        this.bombefore = new ArrayList<bombefore>();
        this.playerarray=new ArrayList<player>();
        this.wallarray=new ArrayList<wall>();
        this.emptyarray=new ArrayList<empty>();
        this.brokenwallarray=new ArrayList<brokenwall>();
        this.enemyRedarray = new ArrayList<enemyRed>();
        this.enemyYellowarray = new ArrayList<enemyYellow>();
        this.img = new PImage[4];
        this.imgY = new PImage[4];
        this.imgR = new PImage[4];
        this.imgB= new PImage[8];
        this.imgE= new PImage[7];
        this.icon = new ArrayList<icon>();
        this.timea = new ArrayList<time>();
        timeCount = 180;
    }


    public void settings() {
        size(WIDTH, HEIGHT);
    }


    public void setup() {
        
        try {

            frameRate(FPS);
            
            //load icon
            icon.add(new icon(4*32,16,this.loadImage("src/main/resources/icons/player.png")));

            font = this.createFont("PressStart2P-Regular.ttf",16);

            textFont(font);
            textAlign(CENTER, CENTER);
            textSize(18);
            fill(0);
            //load token

            timea.add(new time(9*32,16,this.loadImage("src/main/resources/icons/clock.png"))) ;

            mapString=getJson.getString(getJson.getJsonA("config.json")[0].getString("path"));
            String[] splitString = mapString.split("\n");
            lives = getJson.getJsontime("config.json");
            
            

            for(int i = 0 ; i < 13 ; i++){
                for(int a= 0 ; a <15; a++){
                    switch(splitString[i].charAt(a)){
                        case 'W': wallarray.add(new wall(a*32,(i+2)*32,this.loadImage("src/main/resources/wall/solid.png"))); break;
                        case 'B': brokenwallarray.add(new brokenwall(a*32,(i+2)*32,this.loadImage("src/main/resources/broken/broken.png")));break;
                        case ' ': emptyarray.add(new empty(a*32,(i+2)*32,this.loadImage("src/main/resources/empty/empty.png")));break;
                        case 'G': goalarray.add(new goal(a*32,(i+2)*32,this.loadImage("src/main/resources/goal/goal.png"))); break;
                        case 'P': 
                        this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                        this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                        this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                        this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                        this.imgB[0] = this.loadImage("src/main/resources/bomb/bomb1.png");
                        this.imgB[1] = this.loadImage("src/main/resources/bomb/bomb2.png");
                        this.imgB[2] = this.loadImage("src/main/resources/bomb/bomb3.png");
                        this.imgB[3] = this.loadImage("src/main/resources/bomb/bomb4.png");
                        this.imgB[4] = this.loadImage("src/main/resources/bomb/bomb5.png");
                        this.imgB[5] = this.loadImage("src/main/resources/bomb/bomb6.png");
                        this.imgB[6] = this.loadImage("src/main/resources/bomb/bomb7.png");
                        this.imgB[7] = this.loadImage("src/main/resources/bomb/bomb8.png");

                        this.imgE[0]= this.loadImage("src/main/resources/explosion/centre.png");
                        this.imgE[1]= this.loadImage("src/main/resources/explosion/end_bottom.png");
                        this.imgE[2]= this.loadImage("src/main/resources/explosion/end_left.png");
                        this.imgE[3]= this.loadImage("src/main/resources/explosion/end_right.png");
                        this.imgE[4]= this.loadImage("src/main/resources/explosion/end_top.png");
                        this.imgE[5]= this.loadImage("src/main/resources/explosion/horizontal.png");
                        this.imgE[6]= this.loadImage("src/main/resources/explosion/vertical.png");

                                    this.player=new player(a*32,48+i*32,this.loadImage("src/main/resources/player/player3.png"),wallarray,brokenwallarray,enemyYellowarray,enemyRedarray,img,goalarray);
                                    playerarray.add(this.player);
                                    emptyarray.add(new empty(a*32,(i+2)*32,this.loadImage("src/main/resources/empty/empty.png")));
                                    this.bombefore.add(new bombefore(a*32,48+i*32,imgB,null));
                                    break;
                        case 'R': enemyRedarray.add(new enemyRed(a*32,i*32+48,this.loadImage("src/main/resources/red_enemy/red_down1.png"),wallarray,brokenwallarray,player,imgR,enemyYellowarray,enemyRedarray));
                        emptyarray.add(new empty(a*32,(i+2)*32,this.loadImage("src/main/resources/empty/empty.png"))); break;
                        case 'Y':  
                        this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                        this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                        this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                        this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                        enemyYellowarray.add(new enemyYellow(a*32,i*32+48,this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png"),wallarray,brokenwallarray,imgY,player,enemyYellowarray,enemyRedarray));
                        emptyarray.add(new empty(a*32,(i+2)*32,this.loadImage("src/main/resources/empty/empty.png"))); break;
                        
                    }
                }

            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        // Load images during setup
    }

    int x= 0;
    int y=0;
    boolean press= false;
    String playerFacingDirection = "";
    boolean wheatherPress= false;

    int time= 0 ;
    int wait = 2000;
    boolean tick;
    int time1 = 0; 
    int wait1 = 500;
    boolean tick1;

    public void draw() {

        // this.rect(-1, -1, WIDTH+2, HEIGHT+2);

        //backgroud color
        background(192,112, 000);    
        if((timeCount != 0 && lives !=0 && !this.player.isWin())){
            text(lives, 175, 32);
            text(timeCount, 350, 32);
        }

        if(timeCount == 0 || lives ==0){
            emptyarray.clear();
            enemyRedarray.clear();
            wallarray.clear();
            brokenwallarray.clear();
            goalarray.clear();;
            enemyYellowarray.clear();
            icon.clear();
            timea.clear();
            playerarray.clear();
            textSize(26);
            text("GAME", HEIGHT/2-100, WIDTH/2 );
            text("OVER", HEIGHT/2+100, WIDTH/2 );
            noLoop();
        }
        
        if(this.player.isWin()){
            emptyarray.clear();
            enemyRedarray.clear();
            wallarray.clear();
            brokenwallarray.clear();
            goalarray.clear();;
            enemyYellowarray.clear();
            icon.clear();
            timea.clear();
            playerarray.clear();
            textSize(26);
            text("YOU", HEIGHT/2-100, WIDTH/2 );
            text("WIN", HEIGHT/2+100, WIDTH/2 );
            noLoop();
        }

        if(frameCount%60 == 0){
            timeCount--;
        }

        for(icon i :this.icon){
            i.draw(this);
        }

    
        for(time i :this.timea){
            i.draw(this);
        }
        
 
        for(goal i :this.goalarray){
            i.draw(this);
        }
        for(wall i :this.wallarray){
            i.draw(this);
        }
        for(brokenwall i :this.brokenwallarray){
            i.draw(this);
        }
        for(empty i :this.emptyarray){
            i.draw(this);
        }      

        for(enemyRed i :this.enemyRedarray){
            i.moving(frameCount);
            i.tick(frameCount);
            if(i.hit == true){
                switch(i.getDir()){
                    case "UP": 
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_up1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_up2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_up3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_up4.png");
                    break;
                    case "DOWN": 
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                    break;
                    case "LEFT":
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_left1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_left2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_left3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_left4.png");
                    break;
                    case "RIGHT": 
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_right1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_right2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_right3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_right4.png");
                    break;
                    
            } 
            i.draw(this,imgR);
            }else{
                this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                i.setDir("DOWN");
                i.draw(this,imgR);   
            }

        }  



        for(enemyYellow i :this.enemyYellowarray){
            i.tick(frameCount);
            i.moving(frameCount);
            if(i.hit == true){
                switch(i.getDir()){
                        case "UP":
                        this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_up1.png");
                        this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_up2.png");
                        this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_up3.png");
                        this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_up4.png");
                        break;
                        case "DOWN": 
                        this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                        this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                        this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                        this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                        break;
                        case "LEFT":
                                    
                        this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_left1.png");
                        this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_left2.png");
                        this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_left3.png");
                        this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_left4.png");
                        break;
                        case "RIGHT": 
                        this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_right1.png");
                        this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_right2.png");
                        this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_right3.png");
                        this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_right4.png");
                        break;
                }
                i.draw(this,imgY); 
            }else{
                this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                i.setDir("DOWN");
                i.draw(this,imgY); 
                
            }

        }    

        

    for(bombefore bombefore : bombefore){
        bombefore.tick(frameCount);

        if (key== 32 && wheatherPress == false && bombefore.isFinished() == true && bombefore.getIsIgnite()== false) {
            wheatherPress = true;
            time = millis();
            tick = true;
            bombefore.setImageCount(0);
            bombefore.setx(this.player.x);
            bombefore.sety(this.player.y);
          }

        if(tick == true){
            bombefore.draw(this,imgB,bombefore.getx(),bombefore.gety());
        }

        if (millis() - time >=2000 && tick == true) {
            tick1 = true;
            tick = false;
        }
        if(tick1 == true){
            //bomb center 
            explosion bomb_center = new explosion(bombefore.getx(),bombefore.gety(), imgE[0], wallarray,brokenwallarray,enemyYellowarray,enemyRedarray,playerarray);
            bomb_center.draw(this);
            enemyRed eRaq= bomb_center.checkCollisionRedEnemy();
            enemyYellow eYaq = bomb_center.checkCollisionYellowEnemy();
            player ePaq = bomb_center.checkCollisionPlayer();
            if(eRaq != null){
                enemyRedarray.remove(eRaq);
            }
            if(eYaq != null){
                enemyYellowarray.remove(eYaq);
            }
            if(ePaq != null){
                lives--;
                this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                for(enemyYellow i :enemyYellowarray){
                i.setDir("DOWN");
                i.draw(this,imgY);
                i.setx(i.originX);
                i.sety(i.originY);
                }
                this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                for(enemyRed i :enemyRedarray){
                i.setDir("DOWN");
                i.draw(this,imgR);
                i.setx(i.originX);
                i.sety(i.originY);
                }
                this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                player.setx(player.getOriginX());
                player.sety(player.getOriginY());
                player.draw(this,img);  
            }
            //bomb upper 
            explosion bomb_up1 = new explosion(bombefore.getx(),bombefore.gety()-32, imgE[6],wallarray, brokenwallarray,enemyYellowarray,enemyRedarray,playerarray);
            bomb_up1.checkCollision();
            brokenwall a= bomb_up1.checkCollisionA();
            //no wall or broken wall
            if(bomb_up1.isAlive() && a == null){
                bomb_up1.draw(this);
                // bomb top
                explosion bomb_up2 = new explosion(bombefore.getx(),bombefore.gety()-64, imgE[4],wallarray, brokenwallarray,enemyYellowarray,enemyRedarray,playerarray);
                bomb_up2.checkCollision();
                enemyRed eRa= bomb_up1.checkCollisionRedEnemy();
                enemyYellow eYa = bomb_up1.checkCollisionYellowEnemy();
                player ePa = bomb_up1.checkCollisionPlayer();
                if(eRa != null){
                    enemyRedarray.remove(eRa);
                }
                if(eYa != null){
                    enemyYellowarray.remove(eYa);
                }
                if(ePa != null){
                    lives--;
                    this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                    this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                    this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                    this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                    for(enemyYellow i :enemyYellowarray){
                    i.setDir("DOWN");
                    i.draw(this,imgY);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                    for(enemyRed i :enemyRedarray){
                    i.setDir("DOWN");
                    i.draw(this,imgR);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                    this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                    this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                    this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                    player.setx(player.getOriginX());
                    player.sety(player.getOriginY());
                    player.draw(this,img);  
                }
                brokenwall b= bomb_up2.checkCollisionA();
                if(bomb_up2.isAlive() && b == null){
                    bomb_up2.draw(this);
                    enemyRed eRa1= bomb_up2.checkCollisionRedEnemy();
                    enemyYellow eYa1 = bomb_up2.checkCollisionYellowEnemy();
                    player ePa1 = bomb_up2.checkCollisionPlayer();
                    if(eRa1 != null){
                        enemyRedarray.remove(eRa1);
                    }
                    if(eYa1 != null){
                        enemyYellowarray.remove(eYa1);
                    }
                    if(ePa1 != null){
                        lives--;
                        this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                        this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                        this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                        this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                        for(enemyYellow i :enemyYellowarray){
                        i.setDir("DOWN");
                        i.draw(this,imgY);
                        i.setx(i.originX);
                        i.sety(i.originY);
                        }
                        this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                        this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                        this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                        this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                        for(enemyRed i :enemyRedarray){
                        i.setDir("DOWN");
                        i.draw(this,imgR);
                        i.setx(i.originX);
                        i.sety(i.originY);
                        }
                        this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                        this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                        this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                        this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                        player.setx(player.getOriginX());
                        player.sety(player.getOriginY());
                        player.draw(this,img);  
                    }
                }else if(bomb_up2.isAlive() && b!= null){   // // no wall but broken wall
                    bomb_up2.draw(this);
                    brokenwallarray.remove(b);

                    emptyarray.add(new empty(bomb_up2.getX(),bomb_up2.getY(),this.loadImage("src/main/resources/empty/empty.png")));
                }
            }else if(bomb_up1.isAlive() && a != null){   // // no wall but broken wall
                bomb_up1.draw(this);
                brokenwallarray.remove(a);
                enemyRed eRa= bomb_up1.checkCollisionRedEnemy();
                enemyYellow eYa = bomb_up1.checkCollisionYellowEnemy();
                player ePa = bomb_up1.checkCollisionPlayer();
                if(eRa != null){
                    enemyRedarray.remove(eRa);
                }
                if(eYa != null){
                    enemyYellowarray.remove(eYa);
                }                
                if(ePa != null){
                    lives--;
                    this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                    this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                    this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                    this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                    for(enemyYellow i :enemyYellowarray){
                    i.setDir("DOWN");
                    i.draw(this,imgY);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                    for(enemyRed i :enemyRedarray){
                    i.setDir("DOWN");
                    i.draw(this,imgR);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                    this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                    this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                    this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                    player.setx(player.getOriginX());
                    player.sety(player.getOriginY());
                    player.draw(this,img);  
                }
                emptyarray.add(new empty(bomb_up1.getX(),bomb_up1.getY(),this.loadImage("src/main/resources/empty/empty.png")));
                
            }
        
            //bomb left 
            explosion bomb_left1 = new explosion(bombefore.getx()-32,bombefore.gety(), imgE[5],wallarray, brokenwallarray,enemyYellowarray,enemyRedarray,playerarray);
            bomb_left1.checkCollision();
            brokenwall d= bomb_left1.checkCollisionA();
            //no wall or broken wall
            if(bomb_left1.isAlive() && d == null){
                bomb_left1.draw(this);

                // bomb left side
                explosion bomb_left2 = new explosion(bombefore.getx()-64,bombefore.gety(), imgE[2],wallarray, brokenwallarray,enemyYellowarray,enemyRedarray,playerarray);
                bomb_left2.checkCollision();
                enemyRed eRa= bomb_left1.checkCollisionRedEnemy();
                enemyYellow eYa = bomb_left1.checkCollisionYellowEnemy();
                player ePa = bomb_left1.checkCollisionPlayer();
                if(eRa != null){
                    enemyRedarray.remove(eRa);
                }
                if(eYa != null){
                    enemyYellowarray.remove(eYa);
                }
                if(eRa != null){
                    enemyRedarray.remove(eRa);
                }
                if(eYa != null){
                    enemyYellowarray.remove(eYa);
                }                
                if(ePa != null){
                    lives--;
                    this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                    this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                    this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                    this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                    for(enemyYellow i :enemyYellowarray){
                    i.setDir("DOWN");
                    i.draw(this,imgY);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                    for(enemyRed i :enemyRedarray){
                    i.setDir("DOWN");
                    i.draw(this,imgR);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                    this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                    this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                    this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                    player.setx(player.getOriginX());
                    player.sety(player.getOriginY());
                    player.draw(this,img);  
                }
                brokenwall s= bomb_left2.checkCollisionA();
                if(bomb_left2.isAlive() && s == null){
                    bomb_left2.draw(this);
                    enemyRed eRa1= bomb_left2.checkCollisionRedEnemy();
                    enemyYellow eYa1 = bomb_left2.checkCollisionYellowEnemy();
                    player ePa1 = bomb_left2.checkCollisionPlayer();
                    if(eRa1 != null){
                        enemyRedarray.remove(eRa1);
                    }
                    if(eYa != null){
                        enemyYellowarray.remove(eYa1);
                    }
                    if(ePa1 != null){
                        lives--;
                        this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                        this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                        this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                        this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                        for(enemyYellow i :enemyYellowarray){
                        i.setDir("DOWN");
                        i.draw(this,imgY);
                        i.setx(i.originX);
                        i.sety(i.originY);
                        }
                        this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                        this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                        this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                        this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                        for(enemyRed i :enemyRedarray){
                        i.setDir("DOWN");
                        i.draw(this,imgR);
                        i.setx(i.originX);
                        i.sety(i.originY);
                        }
                        this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                        this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                        this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                        this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                        player.setx(player.getOriginX());
                        player.sety(player.getOriginY());
                        player.draw(this,img);  
                    }
    
                }else if(bomb_left2.isAlive() && s!= null){   // // no wall but broken wall
                   bomb_left2.draw(this);
                    brokenwallarray.remove(s);
                    emptyarray.add(new empty(bomb_left2.getX(),bomb_left2.getY(),this.loadImage("src/main/resources/empty/empty.png")));
                }

            }else if(bomb_left1.isAlive() && d != null){   // // no wall but broken wall
                bomb_left1.draw(this);
                brokenwallarray.remove(d);
                emptyarray.add(new empty(bomb_left1.getX(),bomb_left1.getY(),this.loadImage("src/main/resources/empty/empty.png")));
                enemyRed eRa= bomb_left1.checkCollisionRedEnemy();
                enemyYellow eYa = bomb_left1.checkCollisionYellowEnemy();
                player ePa = bomb_left1.checkCollisionPlayer();
                if(eRa != null){
                    enemyRedarray.remove(eRa);
                }
                if(eYa != null){
                    enemyYellowarray.remove(eYa);
                }
                if(ePa != null){
                    lives--;
                    this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                    this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                    this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                    this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                    for(enemyYellow i :enemyYellowarray){
                    i.setDir("DOWN");
                    i.draw(this,imgY);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                    for(enemyRed i :enemyRedarray){
                    i.setDir("DOWN");
                    i.draw(this,imgR);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                    this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                    this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                    this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                    player.setx(player.getOriginX());
                    player.sety(player.getOriginY());
                    player.draw(this,img);  
                }
            }

            //bomb right
            explosion bomb_right1 = new explosion(bombefore.getx()+32,bombefore.gety(), imgE[5],wallarray, brokenwallarray,enemyYellowarray,enemyRedarray,playerarray);
            bomb_right1.checkCollision();
            brokenwall e= bomb_right1.checkCollisionA();
            //no wall or broken wall
            if(bomb_right1.isAlive() && e == null){
                bomb_right1.draw(this);
                // bomb right side
                explosion bomb_right2 = new explosion(bombefore.getx()+64,bombefore.gety(), imgE[3],wallarray, brokenwallarray,enemyYellowarray,enemyRedarray,playerarray);
                bomb_right2.checkCollision();
                enemyRed eRa= bomb_right1.checkCollisionRedEnemy();
                enemyYellow eYa = bomb_right1.checkCollisionYellowEnemy();
                player ePa = bomb_right1.checkCollisionPlayer();
                if(eRa != null){
                    enemyRedarray.remove(eRa);
                }
                if(eYa != null){
                    enemyYellowarray.remove(eYa);
                }
                if(ePa != null){
                    lives--;
                    this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                    this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                    this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                    this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                    for(enemyYellow i :enemyYellowarray){
                    i.setDir("DOWN");
                    i.draw(this,imgY);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                    for(enemyRed i :enemyRedarray){
                    i.setDir("DOWN");
                    i.draw(this,imgR);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                    this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                    this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                    this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                    player.setx(player.getOriginX());
                    player.sety(player.getOriginY());
                    player.draw(this,img);  
                }
                brokenwall f= bomb_right2.checkCollisionA();
                if(bomb_right2.isAlive() && f == null){
                    bomb_right2.draw(this);
                    enemyRed eRa1= bomb_right2.checkCollisionRedEnemy();
                    enemyYellow eYa1 = bomb_right2.checkCollisionYellowEnemy();
                    player ePa1 = bomb_right2.checkCollisionPlayer();
                    if(eRa1 != null){
                        enemyRedarray.remove(eRa1);
                    }
                    if(eYa1 != null){
                        enemyYellowarray.remove(eYa1);
                    }
                    if(ePa1 != null){
                        lives--;
                        this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                        this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                        this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                        this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                        for(enemyYellow i :enemyYellowarray){
                        i.setDir("DOWN");
                        i.draw(this,imgY);
                        i.setx(i.originX);
                        i.sety(i.originY);
                        }
                        this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                        this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                        this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                        this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                        for(enemyRed i :enemyRedarray){
                        i.setDir("DOWN");
                        i.draw(this,imgR);
                        i.setx(i.originX);
                        i.sety(i.originY);
                        }
                        this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                        this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                        this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                        this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                        player.setx(player.getOriginX());
                        player.sety(player.getOriginY());
                        player.draw(this,img);  
                    }
                }else if(bomb_right2.isAlive() && f!= null){   // // no wall but broken wall
                    bomb_right2.draw(this);
                    brokenwallarray.remove(f);
                    emptyarray.add(new empty(bomb_right2.getX(),bomb_right2.getY(),this.loadImage("src/main/resources/empty/empty.png")));
                }
            }else if(bomb_right1.isAlive() && e != null){   // // no wall but broken wall
                bomb_right1.draw(this);
                brokenwallarray.remove(e);
                emptyarray.add(new empty(bomb_right1.getX(),bomb_right1.getY(),this.loadImage("src/main/resources/empty/empty.png")));
                enemyRed eRa= bomb_right1.checkCollisionRedEnemy();
                enemyYellow eYa = bomb_right1.checkCollisionYellowEnemy();
                player ePa = bomb_right1.checkCollisionPlayer();
                if(eRa != null){
                    enemyRedarray.remove(eRa);
                }
                if(eYa != null){
                    enemyYellowarray.remove(eYa);
                }
                if(ePa != null){
                    lives--;
                    this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                    this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                    this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                    this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                    for(enemyYellow i :enemyYellowarray){
                    i.setDir("DOWN");
                    i.draw(this,imgY);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                    for(enemyRed i :enemyRedarray){
                    i.setDir("DOWN");
                    i.draw(this,imgR);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                    this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                    this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                    this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                    player.setx(player.getOriginX());
                    player.sety(player.getOriginY());
                    player.draw(this,img);  
                }
            }


            //bomb bottom
            explosion bomb_bottom1 = new explosion(bombefore.getx(),bombefore.gety()+32, imgE[6],wallarray, brokenwallarray,enemyYellowarray,enemyRedarray,playerarray);
            bomb_bottom1.checkCollision();
            brokenwall g= bomb_bottom1.checkCollisionA();
            
            //no wall or broken wall
            if(bomb_bottom1.isAlive() && g == null){
                bomb_bottom1.draw(this);
                // bottom region
                enemyRed eRa= bomb_bottom1.checkCollisionRedEnemy();
                enemyYellow eYa = bomb_bottom1.checkCollisionYellowEnemy();
                player ePa = bomb_bottom1.checkCollisionPlayer();
                if(eRa != null){
                    enemyRedarray.remove(eRa);
                }
                if(eYa != null){
                    enemyYellowarray.remove(eYa);
                }
                if(ePa != null){
                    lives--;
                    this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                    this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                    this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                    this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                    for(enemyYellow i :enemyYellowarray){
                    i.setDir("DOWN");
                    i.draw(this,imgY);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                    for(enemyRed i :enemyRedarray){
                    i.setDir("DOWN");
                    i.draw(this,imgR);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                    this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                    this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                    this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                    player.setx(player.getOriginX());
                    player.sety(player.getOriginY());
                    player.draw(this,img);  
                }
                explosion bomb_bottom2 = new explosion(bombefore.getx(),bombefore.gety()+64, imgE[1],wallarray, brokenwallarray,enemyYellowarray,enemyRedarray,playerarray);
                bomb_bottom2.checkCollision();
                brokenwall h= bomb_bottom2.checkCollisionA();
                if(bomb_bottom2.isAlive() && h == null){
                    bomb_bottom2.draw(this);
                    enemyRed eRa1= bomb_bottom2.checkCollisionRedEnemy();
                    enemyYellow eYa1 = bomb_bottom2.checkCollisionYellowEnemy();
                    player ePa1 = bomb_bottom2.checkCollisionPlayer();
                    if(eRa1 != null){
                        enemyRedarray.remove(eRa1);
                    }
                    if(eYa1 != null){
                        enemyYellowarray.remove(eYa1);
                    }
                    if(ePa1 != null){
                        lives--;
                        this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                        this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                        this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                        this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                        for(enemyYellow i :enemyYellowarray){
                        i.setDir("DOWN");
                        i.draw(this,imgY);
                        i.setx(i.originX);
                        i.sety(i.originY);
                        }
                        this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                        this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                        this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                        this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                        for(enemyRed i :enemyRedarray){
                        i.setDir("DOWN");
                        i.draw(this,imgR);
                        i.setx(i.originX);
                        i.sety(i.originY);
                        }
                        this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                        this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                        this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                        this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                        player.setx(player.getOriginX());
                        player.sety(player.getOriginY());
                        player.draw(this,img);  
                    }
                }else if(bomb_bottom2.isAlive() && h!= null){   // // no wall but broken wall
                    bomb_bottom2.draw(this);
                    brokenwallarray.remove(h);
                    emptyarray.add(new empty(bomb_bottom2.getX(),bomb_bottom2.getY(),this.loadImage("src/main/resources/empty/empty.png")));
                }
            }else if(bomb_bottom1.isAlive() && g != null){   // // no wall but broken wall
                bomb_bottom1.draw(this);
                brokenwallarray.remove(g);
                emptyarray.add(new empty(bomb_bottom1.getX(),bomb_bottom1.getY(),this.loadImage("src/main/resources/empty/empty.png")));
                enemyRed eRa= bomb_bottom1.checkCollisionRedEnemy();
                enemyYellow eYa = bomb_bottom1.checkCollisionYellowEnemy();
                player ePa = bomb_bottom1.checkCollisionPlayer();
                if(eRa != null){
                    enemyRedarray.remove(eRa);
                }
                if(eYa != null){
                    enemyYellowarray.remove(eYa);
                }
                if(ePa != null){
                    lives--;
                    this.imgY[0] = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                    this.imgY[1] = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                    this.imgY[2] = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                    this.imgY[3] = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
                    for(enemyYellow i :enemyYellowarray){
                    i.setDir("DOWN");
                    i.draw(this,imgY);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.imgR[0] = this.loadImage("src/main/resources/red_enemy/red_down1.png");
                    this.imgR[1] = this.loadImage("src/main/resources/red_enemy/red_down2.png");
                    this.imgR[2] = this.loadImage("src/main/resources/red_enemy/red_down3.png");
                    this.imgR[3] = this.loadImage("src/main/resources/red_enemy/red_down4.png");
                    for(enemyRed i :enemyRedarray){
                    i.setDir("DOWN");
                    i.draw(this,imgR);
                    i.setx(i.originX);
                    i.sety(i.originY);
                    }
                    this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                    this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                    this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                    this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                    player.setx(player.getOriginX());
                    player.sety(player.getOriginY());
                    player.draw(this,img);  
                }
            }

        }


        if (millis() - time >=500 && tick1 == true) {
            emptyarray.add(new empty(bombefore.getx(),bombefore.gety()+16,this.loadImage("src/main/resources/empty/empty.png"))); 
            tick1 =false;
            bombefore.setFinished(true);
            bombefore.setIsIgnite(false);
            wheatherPress = false;
        }


    }
        


    for(player player: playerarray){
       this.player.tick(frameCount);
        if(this.player.isHit() == true){
           
            player.draw(this,img);   

        }else {
            this.img[0] = this.loadImage("src/main/resources/player/player1.png");
            this.img[1] = this.loadImage("src/main/resources/player/player2.png");
            this.img[2] = this.loadImage("src/main/resources/player/player3.png");
            this.img[3] = this.loadImage("src/main/resources/player/player4.png");
            playerarray.get(0).draw(this,img);   
            lives--;
            timeCount=180;
        }    
    }
        
        //gain keyborard direction arrow;

        if(keyPressed && press == false){
            press = true;
            switch(keyCode){
                case UP: this.player.movingup(); 
                this.img[0] = this.loadImage("src/main/resources/player/player_up1.png");
                this.img[1] = this.loadImage("src/main/resources/player/player_up2.png");
                this.img[2] = this.loadImage("src/main/resources/player/player_up3.png");
                this.img[3] = this.loadImage("src/main/resources/player/player_up4.png");
                break;
                case DOWN: this.player.movingdown();
                this.img[0] = this.loadImage("src/main/resources/player/player1.png");
                this.img[1] = this.loadImage("src/main/resources/player/player2.png");
                this.img[2] = this.loadImage("src/main/resources/player/player3.png");
                this.img[3] = this.loadImage("src/main/resources/player/player4.png");
                break;
                case LEFT: this.player.movingleft();        
                this.img[0] = this.loadImage("src/main/resources/player/player_left1.png");
                this.img[1] = this.loadImage("src/main/resources/player/player_left2.png");
                this.img[2] = this.loadImage("src/main/resources/player/player_left3.png");
                this.img[3] = this.loadImage("src/main/resources/player/player_left4.png");
                break;
                case RIGHT: this.player.movingright();
                this.img[0] = this.loadImage("src/main/resources/player/player_right1.png");
                this.img[1] = this.loadImage("src/main/resources/player/player_right2.png");
                this.img[2] = this.loadImage("src/main/resources/player/player_right3.png");
                this.img[3] = this.loadImage("src/main/resources/player/player_right4.png");
                break;
                
            }

        }
        press = keyPressed;
       

    }






    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
