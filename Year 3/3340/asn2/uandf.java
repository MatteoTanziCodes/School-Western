/**
 * 251011979 - Matteo Tanzi // 3340B
 */
public class uandf {

    // Define variables
    int [] p;
    int [] r;
    int finalSetComplete = 0;

    /**
     * Constructor method which constructs an disjoint-set data type with n elements, 1, 2, . . . , n
     */
    public uandf(int n){
        //disjoint-set data type with n elements, 1, 2, . . . , n
        p = new int[n];
        r = new int[n];

    }
    /**
     * creates a new set whose only member (and thus representative) is i
     */
    public void make_set(int i){
        // new set whose only member (and thus representative) is i
        p[i] = i;
    }

    /**
     * unites the dynamic sets that contains i and j, respectively, into a new
     * set that is the union of these two sets.
     */
    public void union_sets(int i, int j){

        // unites the dynamic sets that contains i and j, respectively, into a new set that is the union of these two sets.
        if (r[i = find_set(i)] >= r[j = find_set(j)] ){

            p[j] = p[i];
            if (r[find_set(i)] == r[find_set(j)]){
                r[i]++;
            }
        }
        else{
            p[i] = p[j];
        }
    }

    /**
     * returns the representative of the set containing i.
     * @return set[i]
     */
    public int find_set(int i){

        //returns the representative of the set containing i.
        if(finalSetComplete == 1){
            return p[i];
        }
        else{
            if(p[i] != i){
                return p[i] = find_set(p[i]);
            }
            else{
                return i;
            }
        }
    }

    /**
     * returns the total number of current sets and finalizes the current sets:
     * (i) make set() and union sets() will have no effect after this operation and (ii) resets
     * the representatives of the sets so that integers from 1 to f inal sets() will be used as
     * representatives.
     * @return set_count
     */
    public int final_set(){

        int totalSets = 1;

        for (int i = 1; i < p.length; i++){

            if (p[i] != 0){
                find_set(i);
            }

        }
        for (int i = 1; i < p.length; i++){

            if(p[i] == i){
                p[i] = totalSets ++;
                r[i] = 1;
            }
            else{
                r[i] = 0;
            }

        }
        for (int i = 1; i < p.length; i++){

            if (r[i] == 0 && p[i] > 0){
                p[i] = p[p[i]];
            }

        }

        finalSetComplete = 1;
        return totalSets - 1;
    }
}
