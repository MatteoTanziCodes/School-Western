/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matteo
 */
public class Node {
    
    int name;
    boolean mark;
    
    /**
     * 
     * @param name 
     */
    public Node(int name){
        
        this.name = name;
    }
    /**
     * 
     * @param mark 
     */
    public void setMark(boolean mark){
        
        this.mark = mark;
    }
    /**
     * 
     * @return 
     */
    public boolean getMark(){
        
        return this.mark;
    }
    /**
     * 
     * @return 
     */
    public int getName(){
        
        return this.name;
    }
    
}
