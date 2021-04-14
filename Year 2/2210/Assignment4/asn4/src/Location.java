/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matteo
 */
    public class Location {

        private int xcoord;
        private int ycoord;
    
    /**
     * 
     * @param x
     * @param y 
     * 
     * This constructor method sets the x and y parameters
     */
    public Location(int x, int y){
            this.xcoord = x;
            this.ycoord = y;
    }
    /**
     * 
     * @return
     * 
     * this method returns the xcoord
     */
    public int xCoord(){

        return this.xcoord;
    }
    /**
     * 
     * @return
     * 
     * this method returns the ycoord
     */
    public int yCoord(){

        return this.ycoord;
    }

    /**
     *
     * @param p
     * @return
     * 
     * this compare to method compares two locations to see which is bigger
     */
    public int compareTo (Location p){
            //bigger conditions
            if ((this.xcoord > p.xcoord) || (this.xcoord == p.xcoord && this.ycoord > p.ycoord) ){
                return 1;
            }
            // equal condition
            if(this.xcoord == p.xcoord && this.ycoord == p.ycoord){
                return 0;
            }
            //smaller conditions
            if((this.xcoord < p.xcoord) || (this.xcoord == p.xcoord && this.ycoord < p.ycoord)){
                return -1;
            }
            
            // default return
            return -1;
        }
    }

