/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.core.exceptions;

/**
 *
 * @author kmb
 */
public class RecordExistsException extends Exception{
    public RecordExistsException(String message){
        super(message);
    }
}
