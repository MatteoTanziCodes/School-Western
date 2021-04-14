/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matteo
 */
public class Pixel {
    
    private Location loc;
    private int c;
    
    /**
     * 
     * @param p
     * @param color 
     * 
     * this constructor sets the pixel location and color
     */
    public Pixel(Location p, int color){
        
        this.loc = p;
        this.c = color;
        
    }
    /**
     * 
     * @return - location 
     */
    public Location getLocation(){
        
        return this.loc;
    }
    /**
     * 
     * @return - color
     */
    public int getColor(){
     
        return this.c;
    }
}
