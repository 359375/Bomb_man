package demolition;

import processing.core.PImage;

import java.util.List;

import processing.core.PApplet;
public class explosion extends base implements touch{

	
    private PImage images;
    private List<enemyYellow> yellowArr;
    private List<enemyRed> redArr;
    private List<wall> wallarr;
    private List<brokenwall> broarr;
    private List<player> playerarr;
	private boolean alive;
    public explosion(int x, int y, PImage images,List<wall> wallarr, List<brokenwall> broarr,List<enemyYellow> yellowArr,List<enemyRed> redArr, List<player> playerarr) {
		//TODO Auto-generated constructor stub
        super(x, y, images);
        this.images = images;
        this. x = x;
        this.y = y+16;
        this.wallarr = wallarr;
        this.alive = true;
        this.broarr = broarr;
        this.yellowArr= yellowArr;
        this.redArr =redArr;
        this.playerarr = playerarr;
	}

    public void draw(PApplet p){
      
        p.image(images, x , y);

}

public void collide(){
    this.alive = false; 
}

public void checkCollision(){

    for(wall wall : this.wallarr){
        int wallleft = wall.getx();
        int wallBottom = wall.gety() + wall.getHeight();
        int playerBottom = this.y + 32;
        if (   (x== wallleft && playerBottom == wallBottom) ) {
            this.collide();
            break;
        }
    }

}

public brokenwall checkCollisionA(){

    for(brokenwall wall : this.broarr){
        int wallleft = wall.getx();
        int wallBottom = wall.gety() + wall.getHeight();
        int playerBottom = this.y + 32;
        if (   (x== wallleft && playerBottom == wallBottom) ) {
            return wall;
        }
    }
    return null;
}

public enemyRed checkCollisionRedEnemy(){

    for(enemyRed wall : this.redArr){
        int wallleft = wall.getx();
        int wallBottom = wall.gety() + wall.getHeight();
        int playerBottom = this.y + 32;
        if (   (x== wallleft && playerBottom == wallBottom) ) {
            this.collide();
            return wall;
        }
      
    }
    return null;
}
public enemyYellow checkCollisionYellowEnemy(){
    for(enemyYellow wall : this.yellowArr){
        int wallleft = wall.getx();
        int wallBottom = wall.gety() + wall.getHeight();
        int playerBottom = this.y + 32;
        if (   (x== wallleft && playerBottom == wallBottom) ) {
            this.collide();
            return wall;
        }
      
    }
    return null;
}

public player checkCollisionPlayer(){

    for(player wall : this.playerarr){
        int wallleft = wall.getx();
        int wallBottom = wall.gety() + wall.getHeight();
        int playerBottom = this.y + 32;
        if (   (x== wallleft && playerBottom == wallBottom) ) {
            this.collide();
            return wall;
        }
      
    }
    return null;
}
public boolean isAlive() {
    return alive;
}

public void setAlive(boolean alive) {
    this.alive = alive;
}

public int getX() {
    return x;
}

public void setX(int x) {
    this.x = x;
}

public int getY() {
    return y;
}

public void setY(int y) {
    this.y = y;
}

}