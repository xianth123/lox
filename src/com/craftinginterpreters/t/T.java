package com.craftinginterpreters.t;

public class T {
    public static void main(String[] args){
        for (var a = 1; a < 10; a++){
            System.out.println(a);
            if (a == 2) {
                continue;
            }
        }

    }
    
}
