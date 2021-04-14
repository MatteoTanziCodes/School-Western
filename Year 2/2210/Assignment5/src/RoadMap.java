/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;

/**
 *
 * @author matte
 */
public class RoadMap {
    
    Graph map;
    Node startingNode,destinationNode;
    int initialMoney,width,length,toll,gain; 
 
    /**
     * 
     * @param inputFile
     * @throws MapException 
     */
    public RoadMap(String inputFile)throws MapException{
        
        try{
            Stack<Node> stack = new Stack<>();
            BufferedReader input = new BufferedReader(new FileReader(inputFile));
            String line = input.readLine();
            
            line = input.readLine();
            startingNode.name = Integer.parseInt(line);
            
            line = input.readLine();
            destinationNode.name = Integer.parseInt(line);
            
            line = input.readLine();
            width = Integer.parseInt(line);
            
            line = input.readLine();
            length = Integer.parseInt(line);
            
            line = input.readLine();
            initialMoney = Integer.parseInt(line);
            
            line = input.readLine();
            toll = Integer.parseInt(line);
            
            line = input.readLine();
            gain = Integer.parseInt(line);
            
            map = new Graph(width*length);
             
        }catch(Exception e){
            throw new MapException();
        }
    }
    /**
     * 
     * @return
     * @throws MapException 
     */
    public Graph getGraph() throws MapException{
        if(this.map != null){
            return this.map;
        }
        else{
            throw new MapException();
        }
    }
    /**
     * 
     * @return 
     */
    public int getStartingNode(){
        return startingNode.getName();
    }
    /**
     * 
     * @return 
     */
    public int getDestinationNode(){
        return destinationNode.getName();
    }
    /**
     * 
     * @return 
     */
    public int getInitialMoney(){
        return initialMoney;
    }
    /**
     * 
     * @param start
     * @param destination
     * @param initialMoney
     * @return 
     */
    public Iterator<Node> findPath(int start, int destination, int initialMoney){
        return null;
    }
}
