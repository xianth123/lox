package com.craftinginterpreters.lox;

import java.text.ParseException;

import com.craftinginterpreters.lox.Expr.Assign;
import com.craftinginterpreters.lox.Expr.Call;
import com.craftinginterpreters.lox.Expr.Get;
import com.craftinginterpreters.lox.Expr.Set;
import com.craftinginterpreters.lox.Expr.This;
import com.craftinginterpreters.lox.Expr.Variable;

public class AstPrinter  implements Expr.Visitor<String>{
    String print(Expr expr){
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr){
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr){
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr){
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr){
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    private String parenthesize(String name, Expr... exprs){
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr: exprs){
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(')');
        return builder.toString();
    }

    public static void main(String[] args){
        Expr expression = new Expr.Binary(
            new Expr.Unary(
                new Token(TokenType.MINUS, "-", null, 1),
                new Expr.Literal(123)
            ),
            new Token(TokenType.STAR, "*", null, 1),
            new Expr.Grouping(new Expr.Literal(45.78)));

        System.out.println(new AstPrinter().print(expression));
    }

    @Override
    public String visitVariableExpr(Variable expr) {
        return expr.name.lexeme;
    }

    @Override
    public String visitAssignExpr(Assign expr) {
        return expr.name.lexeme;
    }

    @Override
    public String visitCallExpr(Call expr) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visitGetExpr(Get expr) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visitSetExpr(Set expr) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visitThisExpr(This expr) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
