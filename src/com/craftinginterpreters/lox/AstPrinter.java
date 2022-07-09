package com.craftinginterpreters.lox;

public class AstPrinter  implements Expr.Visitor<String>{
    String print(Expr expr){
        return expr.accept(this);
    }
    
}
