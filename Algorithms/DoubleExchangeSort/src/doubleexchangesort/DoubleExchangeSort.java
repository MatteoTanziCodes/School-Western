/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doubleexchangesort;
/**
 *
 * @author annettetanzi
 */
public class DoubleExchangeSort {
    
    public static void main(String[] args) {
        //Initialize Variables
        double temp;
        int i,j;
        //Declare Array
        double[]num={43.2,13.6,91.4,23.6,72.1};
        
        BeforeSort(num);
        //begin Exchange sorting
        for (i=0;i<num.length-1;i++)
        {
        for (j=i+1;j<num.length;j++)
        {
        if (num[j]<num[i])
        {
            temp = num[i];
            num[i]=num[j];
            num[j]= temp;
        }
        }
        }
        AfterSort(num);
    }
     public static void BeforeSort(double[]num)      
    {
        System.out.println("Before the bubble sort:");
    for(int j=0; j<num.length;j++)
    {
        System.out.print(num[j]+ " ");
    } 
    }
    public static void AfterSort(double[]num)      
    {
        System.out.println("\nAfter the bubble sort");
    for(int j=0; j<num.length;j++)
    {
        System.out.print(num[j] + " ");
    } 
    }
    
}
