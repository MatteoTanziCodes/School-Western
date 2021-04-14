/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintstore2;
import java.util.Scanner;
import java.text.*;
/**
 *
 * @author annettetanzi
 */
public class PaintStore2 
{
    public static void main(String[] args) 
    {
        //Initialize scanners and format
        DecimalFormat decFor = new DecimalFormat ("0.00");
        Scanner scanS = new Scanner (System.in);
        Scanner scanN = new Scanner (System.in);
        
        //Initialize variables
        String paintColour;
        double redGallons = redPaint();
        double greenGallons = greenPaint();
        double totalGallons = totalGLMethod(greenGallons,redGallons);
        //Begin do while to begin user input
        do
        {
            System.out.println("Hey User! What colour paint would you "
            + "like to purchase?  \n" +
            "Red: $21.95/gallon. \n" +
            "Green: $19.95/gallon.");
            paintColour = scanS.nextLine();
            //Begin If-Else Statements to Call methods
            if (paintColour.equalsIgnoreCase("Red"))
            {
            redPaint();
            }
            else
            {
            greenPaint();
            } 
            
            
        }
        while (!((paintColour.equalsIgnoreCase("Red"))
                || (paintColour.equalsIgnoreCase("Green"))));
        
        totalGLMethod(greenGallons,redGallons);
        System.out.println("Total for Red Paint: " + redGallons);
        System.out.println("Total for Green Paint: " + greenGallons);
        System.out.println("Total with 13% tax: " + totalGallons);
        thankYouMessage();
        
        
    }//End of Main
    
    public static double redPaint()
    {
    //Initialize Scanners and Format
    Scanner scanS = new Scanner (System.in);
    Scanner scanN = new Scanner (System.in);
    
    double redGallons;
    String more;
    
    do
    {
    System.out.println("How many gallons would you like to purchase?");
    redGallons = scanN.nextDouble();
    
    System.out.println("Would you like to purchase more? (Yes/No)");
    more = scanS.nextLine();
    
    if (more.equalsIgnoreCase("Yes"))
    {
    redGallons = redGallons * 21.95;
    return redGallons;
    }
    else
    {
    redGallons = redGallons * 21.95;
    return redGallons;
    }
   
    }
    while (redGallons < 0);
            
    
    }//End of Red paint
    
    public static double greenPaint()
    {
    //Initialize Scanners and Format
    Scanner scanS = new Scanner (System.in);
    Scanner scanN = new Scanner (System.in);
    
    double greenGallons;
    String more;
    
    do
    {
    System.out.println("How many gallons would you like to purchase?");
    greenGallons = scanN.nextDouble();
    
    System.out.println("Would you like to purchase more? (Yes/No)");
    more = scanS.nextLine();
    
    if (more.equalsIgnoreCase("Yes"))
    {
    greenGallons = greenGallons * 19.95;
    return greenGallons;
    }
    else
    {
    greenGallons = greenGallons * 19.95;
    return greenGallons;
    }
   
    }
    while (greenGallons < 0);
            
    }//End of Green paint
    
    public static double totalGLMethod(double redGallons,double greenGallons)
    {
        System.out.println("***************************");
        System.out.println("* The Rainbow Paint Store *");
        System.out.println("***************************");
    double totalGallons;
    totalGallons =((redGallons + greenGallons)*1.13);
    return totalGallons;
    }
    public static void thankYouMessage()
    {
    System.out.println("Thank you for choosing The"
            + " Rainbow paint store!\nPlease come again!");
    }
}
