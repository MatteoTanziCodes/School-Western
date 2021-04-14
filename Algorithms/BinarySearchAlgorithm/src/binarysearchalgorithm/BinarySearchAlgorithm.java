/****************************************************************************
 * Program: 
 * Programmer: MatteoTanzi
 * Project: 
 * Date: Oct 13 2017
 * Description: 
 ****************************************************************************/

package binarysearchalgorithm;

import java.util.Scanner;


/**
 *
 * @author 386186324
 */
public class BinarySearchAlgorithm {

    public static void main(String[] args) {
        
        //Initialize Scanners   
        Scanner scanS = new Scanner(System.in);
        Scanner scanN = new Scanner(System.in);
        
        //Declare array
        int [] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};

        //Declare varibles
        int first = 0;
        int last = nums.length - 1;
        int position = -1;
        boolean found = false;
        int value;
        
        System.out.println("Please enter a value between 1-14");
        value = scanN.nextInt();
        
        
        //Begin binary search
        while (found == false && first<= last){
        
            int middle = (first+last)/2;
            
            if (nums[middle]== value){
            
                found = true;
                position = middle;
                
            
            }
            else if (nums[middle]>value){
            
                last = middle -1;
                
            
            }
            else{
            
                first = middle +1;
               
            }
            
        
        }

        if (position == -1){
        
            System.out.println("We were unable to find your value");
        }
        else{
        
            System.out.println("The value:"+value+" was found at postion:"+position);
        }
    }

}
