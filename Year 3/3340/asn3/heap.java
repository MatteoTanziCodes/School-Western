public class heap implements heapADT{

    private Edge[] A;
    private int[] H;
    private int max;
    private int hMax;

    /*
     * Empty Heap constructor for initializing.
     */
    public heap() {
    }


    public heap(Edge[] keys, int n) {
        // reference to the array of edges
        A = keys;
        max = n - 1;
        H = new int[2 * max];
        hMax = 2 * max - 1;
        // create the heap
        heapify();
    }


    private void heapify() {
        for (int i = max; i <= hMax; i++) {
            H[i] = i - max + 1;
        }

        for (int i = max - 1; i >= 1; i--) {
            if (A[H[2 * i]].getWeight() < A[H[2 * i + 1]].getWeight()) {
                H[i] = H[2 * i];
            } else {
                H[i] = H[2 * i + 1];
            }
        }
    }


    public boolean in_heap(Edge id) {
        // create an edge to use for comparison
        Edge check = new Edge(0, 0, 0);

        for (int i = 0; i < A.length; i++) {
            check = A[i];

            // If the endpoints and weight of check match id, return true
            if (check.getSource() == id.getSource() && check.getDestination() == id.getDestination()
                    && check.getWeight() == id.getWeight()) {
                return true;
                // If endpoints are reverse but still match, return true
            } else if (check.getSource() == id.getDestination()
                    && check.getDestination() == id.getSource() && check.getWeight() == id.getWeight()) {
                return true;
            }
        }

        // id is not in the heap
        return false;
    }

    /*
     * The minimum key of the heap.
     *
     * @return the minimum key of the heap
     */
    @Override
    public int min_key() {
        return A[H[1]].getWeight();
    }

    /*
     * The id of the element with minimum key in the heap.
     *
     * @return the minimum id of the heap
     */
    @Override
    public int min_id() {
        return H[1];
    }

    /*
     * The key of the element whose id is id in the heap.
     *
     * @param the id of the key element
     *
     * @return the key of the element whose id is id in the heap
     */
    @Override
    public int key(int id) {
        return A[id].getWeight();
    }

    /*
     * Deletes the element with minimum key from the heap.
     *
     * @return the element that was deleted
     */
    @Override
    public void delete_min() {
        // create an edge to reference removed
        Edge removed = new Edge(0, 0, Integer.MAX_VALUE);
        A[0] = removed;

        H[H[1] + max - 1] = 0;

        int i = (int) Math.floor((H[1] + max - 1) / 2);

        // reheapify
        reheapify(i);


    }

    public void reheapify(int i) {
        while (i >= 1) {
            if (A[H[2 * i]].getWeight() < A[H[2 * i + 1]].getWeight()) {
                H[i] = H[2 * i];
            } else {
                H[i] = H[2 * i + 1];
            }

            i = (int) Math.floor(i / 2);
        }
    }

    public void decrease_key(int id, int new_key) {
        A[id].setWeight(new_key);
        int i = (int) Math.floor((id + max - 1) / 2);

        // reheapfiy
        reheapify(i);
    }

    public boolean isEmpty() {
        for (int i = 0; i < hMax; i++) {
            if(H[i] != 0){
                return false;
            }
        }
        return true;
    }
    public void print_A(){
        for (Edge edge : A) {
            System.out.print(edge.getWeight() + " ");
        }
        System.out.println();
    }
    public void print_H(){
        for (int j : H) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
