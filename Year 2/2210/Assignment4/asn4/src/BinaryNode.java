/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matteo
 */
public class BinaryNode {
    
    private Pixel value;
    private BinaryNode left;
    private BinaryNode right;
    private BinaryNode parent;
    
    /**
     * 
     * @param value
     * @param left
     * @param right
     * @param parent 
     * 
     * Constructor sets node values
     */
    public BinaryNode (Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent){
 
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;       
 
    }
    /**
     * default constructor makes empty node
     */
    public BinaryNode (){
        
        this.value = null;
        this.left = null;
        this.right = null;
        this.parent = null;
        
    }
    /**
     * 
     * @return - parent nodwe
     */
    public BinaryNode getParent(){
        
        return this.parent;
    }
    /**
     * 
     * @param parent 
     * 
     * sets the parent node
     */
    public void setParent(BinaryNode parent){
        
        this.parent = parent;
    }
    /**
     * 
     * @param p 
     * 
     * sets the left binaryNode
     */
    public void setLeft (BinaryNode p){
        
        this.left = p;
    }
    /**
     * 
     * @param p 
     * 
     * sets the right binaryNode
     */
    public void setRight (BinaryNode p){
        
        this.right = p;
    }
    /**
     * 
     * @param value
     * 
     * sets the data for a node
     */
    public void setData (Pixel value){
        
        this.value = value;
    }
    /**
     * 
     * @return 
     * 
     * checks if the node has any data, returns false if no data, true otherwise
     */
    public boolean isLeaf(){
        
        return this.value == null;
    }
    /**
     * 
     * @return - data of a node
     */
    public Pixel getData (){
        
        return this.value;
    }
    /**
     * 
     * @return - left child node 
     */
    public BinaryNode getLeft(){
        
        return this.left;
    }
    /**
     * 
     * @return - right child node
     */
    public BinaryNode getRight(){
        
        return this.right;
    }
}
