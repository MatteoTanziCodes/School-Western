/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arraysearching;
import java.util.Scanner;
/**
 *
 * @author annettetanzi
 */
public class ArraySearching {

    public static void main(String[] args) {
       //Initialize Scanners
       Scanner scanS = new Scanner(System.in);
       Scanner scanN = new Scanner(System.in);
       
       //Initialize Variables
       int Key=0;
       boolean found = false;
       //Declare Array
       int [] random = new int [100];
       
       //Part one
       for (int i=0;i<random.length;i++)
       {
       int number = (int)(1 + Math.random()* (100-1 +1));    
       random[i] = number;
       }
       System.out.println("Hey user please enter your favourite number!");
       Key = scanN.nextInt();
       
       
       for (int i=0;i<random.length;i++)
       {
        if (random[i]==Key)
        {
        System.out.println("Found number "+Key+" at index "+i);
        found = true;
        }
        
       }
       if (!found)
       {
           System.out.println("Number "+Key+" was not found in this array");
       } 
       
    }
    
}
