/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matte
 */
public interface DictionaryADT 
{
    public int insert (Record pair) throws DictionaryException;

    public void remove (String config) throws DictionaryException;

    public int get (String config);

    public int numElements();
}
