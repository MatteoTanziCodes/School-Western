/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;
import java.util.Scanner;
/**
 *
 * @author annettetanzi
 */
public class MiniProject {

    public static void main(String[] args) {
       //Initialize Scanners
       Scanner scanS = new Scanner(System.in);
       
       //Initialize Variables
       int count=0;
       
       //Declare Array
       int[]random = new int[15];
       String[]StudNames = new String[15];
       
       //Part one
       for (int i=0;i<random.length;i++)
       {
       int number = (int)(51 + Math.random()* (100-51 +1));    
       random[i] = number;
       }
       
       //Part two
       System.out.println("Please enter 15 Student names");
       for(int i=0;i<StudNames.length;i++)
       {
       StudNames[i]=scanS.nextLine();
       }
       
       //Part 3
       for (int i=0;i<15;i++)
       {
           System.out.println(StudNames[i] + " recieved a grade of " + random[i]);
       }
       
       //Part 4
       for (int i=0;i<15;i++)
       {
       if (random[i]>=80)
           count++;
       }
       System.out.println("\nThere are "+count+" grades 80 or higher");
       
       
       //Part 5
       getMean(random);
       
       System.out.println("\nThe average of all the grades is "+ getMean(random));
    }
    
    public static int getMean(int[]random)
    {
    //Declare Variables
        int sum=0;
        int Average;
        
        for (int i=0;i<15;i++)
        {
        sum+=random[i];
        }
        Average = sum/15;
        return Average;
    }
    
}
