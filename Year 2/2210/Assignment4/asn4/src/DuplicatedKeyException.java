/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matteo
 */
public class DuplicatedKeyException extends RuntimeException{
    /**
     * This method extends the runtime exception to call a duplicated key error
     */
    public DuplicatedKeyException(){
        super ("Duplicated Key Error");
    }
}
