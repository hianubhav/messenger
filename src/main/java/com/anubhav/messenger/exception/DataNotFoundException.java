/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anubhav.messenger.exception;

/**
 *
 * @author Anubhav
 */
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5345345345435345L;
    public DataNotFoundException(String string) {
        super(string);
    }

    public DataNotFoundException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
