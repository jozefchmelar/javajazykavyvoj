/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajazykavyvoj.packageWeek2;

/**
 *
 * @author jozef.chmelar.ml
 */
public class Point {
    private boolean visited;
    private boolean survior;

    public Point() {
        this.visited=false;
        this.survior=false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }

    public boolean isSurvior() {
        return survior;
    }

    public void setSurvior() {
        this.survior = true;
    }
    
    
    
}
