import java.util.LinkedList;
/**
 *
 * @author matteo
 */
public class Dictionary implements DictionaryADT {
    private final int size;
    private final LinkedList<Record>[] dictionary;
    
    public Dictionary(int size) {
        this.size = size;
        this.dictionary = new LinkedList[size];
    }

    public int insert(Record pair) throws DictionaryException {

        int value = hashFunction(pair.getConfig());

        if (dictionary[value] == null) {
            LinkedList<Record> list = new LinkedList<Record>();
            list.add(pair);
            dictionary[value] = list;
            return 0;
        }
        
        for(int i = 0; i < dictionary[value].size(); i++) {
            Record temp = dictionary[value].get(i);
            
            if (temp.getConfig().equals(pair.getConfig())) {
                throw new DictionaryException();
            }
            
        }
        
        dictionary[value].add(pair);
        return 1;
    }
    public void remove(String config) throws DictionaryException {
        int value = hashFunction(config);
        int i = 0;
         
        if (dictionary[value] == null) {
            throw new DictionaryException();
        }
         
        while (i < dictionary[value].size()) {
            Record temp = dictionary[value].get(i);
            if (temp.getConfig().equals(config)) {
                dictionary[value].remove(i);
                return;
            } else {
                i = i + 1;
            }
        }
    }

    public int get(String config) {
        
        int value = hashFunction(config);
        
        if (dictionary[value] == null) {
            return -1;
        }
        
        for(int i = 0; i < dictionary[value].size(); i++) {
            Record temp = dictionary[value].get(i);
            if (temp.getConfig().equals(config)) {
                return temp.getScore();
            }  
        }
        
        return -1;

    }
    public int numElements() {
        int count = 0;
        
        for (LinkedList<Record> dictionary1 : dictionary) {
            if (dictionary1 != null) {
                count = dictionary1.stream().map((_item) -> 1).reduce(count, Integer::sum);
            }
        }
        
        return count;
    }

    private int hashFunction(String s) {
        int value = 31;
        int location = (int) s.charAt(0);
        
        for (int i = 0; i < s.length() - 1; i++) {
            location = (location * value + (int) s.charAt(i)) % size;
        }
        
        return location % size;
    }
}
