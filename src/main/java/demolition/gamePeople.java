package demolition;

import java.util.List;

import processing.core.PImage;

public abstract class gamePeople extends base {

    protected int xVel;
    protected int yVel;
    protected int locationBeforeY;
    protected int locationBeforeX;
    protected List<wall> wallarr;
    protected List<brokenwall> broarr;
    protected boolean alive;
    protected boolean hit;
    public gamePeople(int x, int y,List<wall> wallarr, List<brokenwall> broarr, PImage pi) {
        super(x, y, pi);
        this.xVel = 0;
        this.yVel = 0;
        this.alive =true;
        this.wallarr = wallarr;
        this.broarr = broarr;

    }
    
    public void moving(){
        this.yVel = 32;
        this.xVel = 32;
    }

    public boolean isHit() {
        return hit;
    }



    


}