/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblesortarray1;
import java.util.Scanner;
/**
 *
 * @author annettetanzi
 */
public class BubbleSortArray1 {

    public static void main(String[] args) {
        //Initialize Scanners
        Scanner scanN = new Scanner(System.in);
        
        //Initialize Variables
        int temp;
        boolean flag = true;
        
        //Declare Array
        int[]nums= {65,45,34,12,99,54,39,2,88,18};
       
        //Method to print before swap
        BeforeSort(nums);
        
        //Begin bubble sorting
        while (flag)
        {
        flag=false; //set flag to false awaiting a possible swap
        for (int j=0;j<nums.length-1;j++)
        {
        if (nums[j]<nums[j+1]) //change to > for ascending sort
        {
            //Begin Swap
        temp = nums[j];
        nums[j]=nums[j+1];
        nums[j+1]=temp;
        flag = true;
        }
        }
        }
        
        //Method to print after swap
        AfterSort(nums);
    }
    
    public static void BeforeSort(int[]nums)      
    {
        System.out.println("Before the bubble sort");
    for(int j=0; j<10;j++)
    {
        System.out.print(nums[j]+ " ");
    } 
    }
    public static void AfterSort(int[]nums)      
    {
        System.out.println("\nAfter the bubble sort");
    for(int j=0; j<10;j++)
    {
        System.out.print(nums[j] + " ");
    } 
    }
    
}
