package demolition;

import java.util.List;

import processing.core.PImage;

public abstract class gameSetplayer extends gamePeople implements moving {




    public gameSetplayer(int x, int y, PImage pi, List<wall> wallarr, List<brokenwall> broarr
           ) {
        super(x, y,wallarr, broarr,pi);
    }

    public abstract void  movingdown();
    public abstract void  movingup();
    public abstract void  movingleft();
    public abstract void  movingright();

}