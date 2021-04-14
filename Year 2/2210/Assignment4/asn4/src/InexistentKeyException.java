/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matteo
 */
public class InexistentKeyException extends RuntimeException{
    /**
     * This method extends the runtime exception to call a inexistent key error
     */
    public InexistentKeyException(){
        super ("Non Existent Key Error");
    }
}
