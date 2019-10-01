/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Meng
 */
public class Library {
    private ArrayList<Video> library;
    
    public Library(){}

    public ArrayList<Video> getLibrary() {
        return library;
    }

    public void setLibrary(ArrayList<Video> library) {
        this.library = library;
    }
    
    
}
