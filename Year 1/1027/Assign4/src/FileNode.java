import java.util.ArrayList;import java.util.List;/** * FileNode represents a node in the linked list of files. * Every node corresponds to a specific file that contains a specific word.
 * * 
 */public class FileNode{    private FileNode next;    private String filename;    private ArrayList<Integer> positions;        /**     * Creates a node storing the name of the file.     *
     * @param filename The name of the file corresponding to the node
     */    public FileNode (String filename)    {        next = null;        this.filename = filename;        positions = new ArrayList<Integer>();    }        /**     * Returns the node that follows this one.     *
     * @return  the node that follows the current one
     */    public FileNode getNext()    {        return next;    }        /**     * Sets the node that follows this one.     *
     * @param node  the node to be set to follow the current one
     */    public void setNext (FileNode node)    {        next = node;    }        /**     * Returns the name of the file corresponding to this node.     *
     * @return  the element stored in this node
     */    public String getFilename()    {        return filename;    }        /**     * Returns the list of the word's position in this file.     *      * @return	list of positions in this file     */    public ArrayList<Integer> getPositions() {    	return positions;    }        /**     * Adds a new position of the word in the file corresponding to     * this node.     *      * @param position	The word's position in this file     */    public void addPosition(int position) {    	positions.add(position);    }}
