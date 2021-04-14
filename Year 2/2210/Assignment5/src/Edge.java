/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author matte
 */
public class Edge {
    
    Node firstEdgepoint;
    Node secondEdgepoint;
    int type;
    
    /**
     * 
     * @param u
     * @param v
     * @param type 
     */
    public Edge(Node u, Node v, int type){
        
        this.firstEdgepoint = u;
        this.secondEdgepoint = v;
        this.type = type;
    }
    /**
     * 
     * @return 
     */
    public Node firstEndpoint(){
        
        return this.firstEdgepoint;
    }
    /**
     * 
     * @return 
     */
    public Node secondEndpoint(){
        
        return this.secondEdgepoint;
    }
    /**
     * 
     * @return 
     */
    public int getType(){
        
        return this.type;
    }
}
