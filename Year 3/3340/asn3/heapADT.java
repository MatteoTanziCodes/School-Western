public interface heapADT {

    boolean in_heap(Edge id);


    int min_key();


    int min_id();


    int key(int id);


    void delete_min();


    void reheapify(int i);


    void decrease_key(int id, int new_key);


    boolean isEmpty();


    void print_A();


    void print_H();

}
