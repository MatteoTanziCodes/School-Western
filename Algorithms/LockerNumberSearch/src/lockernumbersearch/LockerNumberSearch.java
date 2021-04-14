/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lockernumbersearch;
import java.util.Scanner;
/**
 *
 * @author annettetanzi
 */
public class LockerNumberSearch {

    public static void main(String[] args) {
        //Initialize Scanners
       Scanner scanN = new Scanner(System.in);
       
       //Initialize Variables
       int key;
       
       //Declare Array's
       String[]names= {"Alice","Bob","Carol","Dan","Eve","Frank","Gail","Henry","Irene","John"};      
       int [] lockNum ={167,265,143,238,90,368,62,278,193,281};

       //Part two
       System.out.println("Hey user please enter a locker number");
       key=scanN.nextInt();
       
       //Method call
       int index = LockerSearch(names,lockNum,key);
       
       //Output
        if (index != -1)
        {
            System.out.println(names[index]+ " has locker number " +key);
        }
        else
        {
            System.out.println("Lucker number "+key+" has not been assigned to a student");
        }
        
        
    }
    
    public static int LockerSearch(String[]names,int[]lockNum,int key)
    {
    //Declare Variables
     int count=0;
     boolean found = false;
    for (int i=0;i<lockNum.length;i++)
    {  
    if (lockNum[i]==key)
    {
    found = true;    
    return (count);
    }
    ++count;
    }
    
    if (!found)
    {
    count = -1;
    }
    return (count);
    
    }
    
    
}
