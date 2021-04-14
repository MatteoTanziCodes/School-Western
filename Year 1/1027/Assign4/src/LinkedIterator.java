/**
 * LinkedIterator represents an iterator for a linked list of file nodes.
 */

import java.util.*;
    
public class LinkedIterator implements Iterator<FileNode>
{
    private FileNode current;  // the current position
  
  /**
   * Sets up this iterator using the specified items.
   *
   * @param collection  the collection the iterator will move over
   */
  public LinkedIterator (FileNode collection)
  {
    current = collection;
  }
  
  /**
   * Returns true if this iterator has at least one more element
   * to deliver in the iteration.
   *
   * @return  true if this iterator has a least one more element to deliver
   *          in the iteration
   */
  public boolean hasNext()
  {
    return (current!= null);
  }
  
  /**
   * Returns the next element in the iteration. If there are no
   * more elements in this iteration, a NoSuchElementException is
   * thrown.
   *
   * @return                         the next element in the iteration
   * @throws NoSuchElementException  if a no such element exception occurs
   */
  public FileNode next()
  {
    if (! hasNext())
        throw new NoSuchElementException();
    FileNode result = current;
    current = current.getNext();
    return result;
  }
  
  /**
   * The remove operation is not supported.
   * 
   * @throws UnsupportedOperationException  if an unsupported operation
   *                                        exception occurs
   */
  public void remove() throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  }
}
