/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anubhav.messenger.model;

/**
 *
 * @author Anubhav
 */
public class ErrorMessage {
    private String errorMessge;
    private int errorcode;
    private String documentation;

    public ErrorMessage(String errorMessge, int errorcode, String documentation) {
        this.errorMessge = errorMessge;
        this.errorcode = errorcode;
        this.documentation = documentation;
    }

    public ErrorMessage() {
    }

    public String getErrorMessge() {
        return errorMessge;
    }

    public void setErrorMessge(String errorMessge) {
        this.errorMessge = errorMessge;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
    
}
