/****************************************************************************
 * Program: 
 * Programmer: MatteoTanzi
 * Project: 
 * Date: Oct 13 2017
 * Description: 
 ****************************************************************************/

package selectionsortalogorithm;

import java.util.Scanner;
/**
 *
 * @author 386186324
 */
public class SelectionSortAlogorithm {

    public static void main(String[] args) {
        
        //Initialize Scanners   
        Scanner scanS = new Scanner(System.in);
        Scanner scanN = new Scanner(System.in);
        

        //Declare varibles
         sortObjects();

    }

    public static void sortObjects() {
        String[] array2 = new String[5];

        for (int i = 0; i < array2.length; i++) {
            array2[i] = array[i].getName();
        }

        int minIndex;
        String minValue;

        for (int startScan = 0; startScan < array2.length - 1; startScan++) {

            minIndex = startScan;
            minValue = array2[startScan];

            for (int index = startScan + 1; index < array2.length; index++) {

                if (array2[index].compareToIgnoreCase(minValue) < 0) {
                    minValue = array2[index];
                    minIndex = index;
                }

            }

            array[minIndex] = array[startScan];
            array2[startScan] = minValue;

        }

        for (int o = 0; o < array2.length; o++) {
            System.out.println(array[o] + "");
        }

    }
}
