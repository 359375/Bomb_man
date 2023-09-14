package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public class bombefore extends base {
    private PImage[] images ;
    private int imageCount;
    private boolean isFinished;
    private Boolean isIgnite;
    public bombefore(int x, int y,PImage[] images, PImage pi) {
        super(x, y, pi);
        imageCount=0;
        this.images=images;
        this.isFinished=true;
        this.isIgnite = false;
    }
    
    public void tick(int count){
            if(count %15 == 0) {
                imageCount++;
                }
                if (imageCount>(images.length-1)) {
                    imageCount  = 0;
                }

    }
    
    public void draw(PApplet p,PImage[] a, int x,int y){
            images=a;
            p.image(images[imageCount], x , y+16);
            isIgnite =true;
            isFinished =false;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }



    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Boolean getIsIgnite() {
        return isIgnite;
    }

    public void setIsIgnite(Boolean isIgnite) {
        this.isIgnite = isIgnite;
    }


    
}