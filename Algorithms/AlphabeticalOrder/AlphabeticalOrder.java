/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabeticalorder;

/**
 *
 * @author annettetanzi
 */
public class AlphabeticalOrder {

    public static void main(String[] args) {
        //Initialize Variables
        String temp;
        int i, j;
        //Declare Array
        String[] letters = {"a", "g", "k", "j", "t", "i", "f", "s", "w", "b"};

        //Method to print before
        BeforeSort(letters);

        //Begin Sort
        for (i = 0; i < letters.length - 1; i++) {
            for (j = i + 1; j < letters.length; j++) {
                if (letters[i].compareToIgnoreCase(letters[j]) > 0) {
                    temp = letters[i];
                    letters[i] = letters[j];
                    letters[j] = temp;

                }

            }

        }

        //Method to print after sort
        AfterSort(letters);
    }

    public static void BeforeSort(String[] letters) {
        System.out.println("Before the sort:");
        for (int j = 0; j < letters.length; j++) {
            System.out.print(letters[j] + " ");
        }
    }

    public static void AfterSort(String[] letters) {
        System.out.println("\nAfter the sort:");
        for (int j = 0; j < letters.length; j++) {
            System.out.print(letters[j] + " ");
        }
    }

}
