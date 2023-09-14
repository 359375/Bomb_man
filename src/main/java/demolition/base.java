package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public class base {
    protected int x;
    protected int y;
    protected PImage pi;

    public base(int x, int y, PImage pi) {
        this.x = x;
        this.y = y;
        this.pi = pi;
    }

    public void draw(PApplet p){
        p.image(this.pi, this.x, this.y);
    }

    protected int getx() {
        return this.x;
    }

    protected int gety() {
        return this.y;
    }

    public int getWidth() {
        return this.pi.width;
    }

    public int getHeight() {
        return this.pi.height;
    }

    protected void setx(int x) {
        this.x = x;
    }

    protected void sety(int y) {
        this.y = y;
    }
    
}