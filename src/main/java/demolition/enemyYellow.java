package demolition;

import java.util.List;

import processing.core.*;


public class enemyYellow extends gameSetenemy {
    

    public enemyYellow(int x, int y,PImage pi,List<wall> wallarr,List<brokenwall> broarr,PImage[] a, player playarr, List<enemyYellow> enemyYellowsarr, List<enemyRed> enemyRedsarr) {

        super(x, y, pi, wallarr, broarr,playarr, a,enemyYellowsarr,enemyRedsarr);

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

            if ((rightClide == true && footClide == false && dir == "DOWN") ||( rightClide == true&&footClide == false && dir == "RIGHT") ||(footClide == true && leftClide == false && rightClide == true&& dir == "RIGHT")||(footClide == false&&rightClide == false&& headClide == false &&leftClide == false&& dir == "DOWN") ){
                this.dir = movingdown();

            }  else  if((footClide == true &&leftClide == false&& dir == "DOWN")||(footClide == true &&leftClide == false&& dir == "LEFT") ||  (footClide == true &&leftClide == true&& dir == "DOWN")||(footClide == false&&rightClide == false&& headClide == false &&leftClide == false&& dir == "LEFT")|| (leftClide == false&& dir == "LEFT")){
                this. dir =movingleft();
            }  else  if((headClide == false && leftClide == true&& dir == "LEFT") || (headClide == false && leftClide == true&& dir == "UP") ||(rightClide == true&& headClide== false&&leftClide== false&&dir == "UP") ||(headClide == true &&leftClide == true&& dir == "LEFT")||(footClide == false&&rightClide == false&& headClide == false &&leftClide == false&& dir == "UP") ){
               this.dir= movingup();
            }else if ((headClide == true && (leftClide == true || rightClide == true)&& dir == "UP")||(rightClide == false&& dir == "RIGHT") || (leftClide == true&& headClide== true&&dir == "UP")||(footClide == false&&rightClide == false&& headClide == false &&leftClide == false&& dir == "RIGHT")){
                this.dir=movingright();
            }
            }
    }



}