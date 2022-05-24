package com.example.finalproject;

public class Result {
    String posX;
    String posY;
    Result(String posX, String posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public String toString(){
        return this.posX + " " + this.posY;
    }
}