/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author matteo
 */
public class Graph {
    
    Node nodes[];
    Edge edges[][];
    
    /**
     * 
     * @param n 
     */
    public Graph(int n){
        
        this.nodes = new Node[n];
        this.edges = new Edge[n][n];
        
        for (int i = 0; i < n; i++) {
            this.nodes[i] = new Node(i);
        }
        
    }
    /**
     * 
     * @param name
     * @return
     * @throws GraphException 
     */
    public Node getNode(int name) throws GraphException{
        if(this.nodes.length > 0){
            for (Node node : this.nodes) {
                if (node.name == name) {
                    return node;
                }
            }
        }
        
        throw new GraphException();
    }
    /**
     * 
     * @param u
     * @param v
     * @param edgeType
     * @throws GraphException 
     */
    public void insertEdge(Node u, Node v, int edgeType) throws GraphException{
        
        try{
            if(!edgeExists(u,v)){
                edges[u.getName()][v.getName()] = new Edge(u, v, edgeType);
		edges[v.getName()][u.getName()] = new Edge(v, u, edgeType);
            }
        }catch(Exception e){
            throw new GraphException();
        } 
    }
    /**
     * 
     * @param u
     * @return
     * @throws GraphException 
     */
    public Iterator<Edge> incidentEdges(Node u) throws GraphException{
        Stack<Edge> incident = new Stack<>();
        
        try{
            for (int i = 0; i < nodes.length; i++) {  
                if(edges[u.getName()][i] != null){
                    incident.push(edges[u.getName()][i]);
                }
            }
            if(incident.isEmpty()){
                return null;
            }
            else return incident.iterator();
        }catch(Exception e){
            throw new GraphException();
        }

    }
    /**
     * 
     * @param u
     * @param v
     * @return
     * @throws GraphException 
     */
    public Edge getEdge(Node u, Node v) throws GraphException{
        
        try{
            if(edges[u.getName()][v.getName()] != null){
                return edges[u.getName()][v.getName()];
            }
        }catch(Exception e){
            throw new GraphException();
        }
        throw new GraphException();
    }
    /**
     * 
     * @param u
     * @param v
     * @return
     * @throws GraphException 
     */
    public boolean areAdjacent(Node u, Node v) throws GraphException{
         
        try{
            if(edges[u.getName()][v.getName()] != null){
                return true;
            }
        }catch(Exception e){
            throw new GraphException();
        }
        return false;
    }
    /**
     * 
     * @param u
     * @param v
     * @return
     * @throws GraphException 
     */
    private boolean edgeExists(Node u, Node v) throws GraphException{
        try{
            return !(edges[u.getName()][v.getName()] == null && edges[v.getName()][u.getName()] == null);
        }catch(Exception e){
            throw new GraphException();
        }
    }
}
