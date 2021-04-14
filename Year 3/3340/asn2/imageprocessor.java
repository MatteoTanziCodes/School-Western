/*
  251011979 - Matteo Tanzi // 3340B
 */
import java.io.*;
import java.util.Arrays;
public class imageprocessor {

    /**
     *
     * @param args - main arg
     */
    public static void main(String[] args){

        // Define variables
        int[][] img = new int[71][71];
        int[] sets;
        int[] sorted;
        int[][] chars;
        int[] elements;
        Object [] arrayobj;
        uandf dSet;

        imageprocessor ip = new imageprocessor();
        img = ip.imageProcessing(img);
        dSet = ip.createDisjointSet(img);
        arrayobj = ip.calcValues(img, dSet);

        sets = (int[]) arrayobj[0];
        chars = (int[][]) arrayobj[1];
        sorted = (int[]) arrayobj[2];
        elements = (int[]) arrayobj[3];

        ip.imagePrinting(img,sets,false);
        ip.imagePrinting(chars,sets,false);
        ip.listPrinting(sorted,elements);
        ip.imagePrinting(chars,sets,true);

    }

    /**
     *
     * @param img - image array
     * @return - image array
     */
    public int[][] imageProcessing(int[][] img){

        try {

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String string;

            for(int i = 0; input.ready(); i++){
                string = input.readLine();
                for (int j = 0; j < string.length(); j++){
                    if(string.charAt(j) == '+'){
                        img[i][j] = 1;
                    }
                }
            }

            input.close();
        }
        catch (Exception e){
            System.out.println("File Error");
        }

        return img;
    }

    /**
     *
     * @param img - image array
     * @param dset - disjoint set
     * @return - object of arrays containing useful matrices
     */
    public Object[] calcValues(int[][] img, uandf dset){

        Object[] arrayObjs = new Object[4];
        int k;
        int[] sets = new int[dset.final_set()];
        int[][] chars = new int[img.length][img.length];
        int[] sorted;
        int[] elements;


        for (int i = 0; i<img.length; i++){
            for (int j = 0; j<img.length; j++){

                k = dset.find_set(i*img.length + j + 1) + 96;
                if(k != 96){
                    sets[k - 97]++;
                }
                chars[i][j] = k;
            }
        }



        sorted = new int[sets.length];
        elements = new int[sets.length];
        if (sets.length >= 0) System.arraycopy(sets, 0, sorted, 0, sets.length);
        if (sorted.length >= 0) System.arraycopy(sorted, 0, elements, 0, sorted.length);
        Arrays.sort(sorted);
        arrayObjs[0] = sets;
        arrayObjs[1] = chars;
        arrayObjs[2]= sorted;
        arrayObjs[3] = elements;

        return arrayObjs;
    }

    /**
     *
     * @param img - image array
     * @param sets - set values
     * @param ready - boolean to determine which type of image to print
     */
    public void imagePrinting(int[][] img, int[] sets, boolean ready){
        for (int[] ints : img) {
            for (int j = 0; j < img.length; j++) {
                if(ints[j] >= 96){
                    if(ints[j] == 96){
                        System.out.print(' ');
                    }
                    else if((sets[ints[j] - 97] == 1 || sets[ints[j] - 97] == 2) && ready){
                        System.out.print(' ');
                    }
                    else{
                        System.out.print((char)ints[j]);
                    }
                }
                else{
                    System.out.print(ints[j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     *
     * @param sorted - sorted list of elements
     * @param elements - unsorted list of elements
     */
    public void listPrinting(int[] sorted, int[] elements){
        for(int i = 0; i<elements.length; i++){
            for (int j = 0; j<elements.length; j++){
                if(sorted[i] == elements[j]){
                    System.out.println((char)(j+97) + "\t" +sorted[i]);
                    elements[j] = -1;
                }
            }
        }
        System.out.println();
    }

    /**
     *
     * @param img - binary image array
     * @return - the disjoint set
     */
    public uandf createDisjointSet(int[][] img){

        uandf disjointSet = new uandf(img.length * img.length + 1);
        for(int i = 0; i < img.length; i++){
            for(int j = 0; j < img.length; j++){
                if (img[i][j] == 1){
                    disjointSet.make_set((i * img.length + j) + 1);
                    if (j > 0 && img[i][j - 1] == 1){
                        disjointSet.union_sets(i * img.length + (j - 1) + 1, i * img.length + j + 1);
                    }
                    if (i > 0 && img[i - 1][j] == 1){
                        disjointSet.union_sets((i - 1) * img.length + j + 1, i * img.length + j + 1);
                    }
                }
            }
        }

        return disjointSet;
    }


}
