import java.io.*;
import java.util.*;


public class syntax {
    //The list of token values (comes from lexer.java)
    static ArrayList<Integer> listOfTokens = new ArrayList<>();
    static ArrayList<char[]> listOfLexemes = new ArrayList<>();
    static int currentToken;
    static int current = 0;

    final static int INT_LIT_BYTE = 10;
    final static int INT_LIT_SHORT = 11;
    final static int INT_LIT = 12;
    final static int INT_LIT_LONG = 13;
    final static int INT_WITHOUT_TYPE = 14;
    final static int IDENT = 15;
    final static int ASSIGN_OP = 20;
    final static int ADD_OP = 21;
    final static int SUB_OP = 22;
    final static int MULT_OP = 23;
    final static int DIV_OP = 24;
    final static int LEFT_PAREN = 25;
    final static int RIGHT_PAREN = 26;
    final static int MODULUS = 27;
    final static int LESS_THAN = 28;
    final static int GREATER_THAN = 29;
    final static int LESSTHAN_EQUAL = 30;
    final static int GREATERTHAN_EQUAL = 31;
    final static int NOT_EQUAL = 33;
    final static int EQUAL = 34;
    final static int EQUAL_TO = 35;
    final static int ASSIGN = 36 ;
    final static int BEGIN = 37;
    final static int END = 38;
    final static int FOR = 39;
    final static int IF = 40;
    final static int INT_DEC = 41;
    final static int BYTE_DEC = 42;
    final static int LONG_DEC = 43;
    final static int SHORT_DEC = 44;
    final static int END_OF_STATEMENT = 45;
    final static int BEGIN_CURLY = 46;
    final static int END_CURLY = 47;
    final static int TO = 48;
    final static int EOF = -1;

    //retrieves the next token in the list
    public static void getNextToken(){
        if(current < listOfTokens.size()){
            current += 1;
        }
        currentToken = listOfTokens.get(current);
    }

    //this function ends the program after an error occurs
    public static void error(){
        System.exit(0);
    }

    //this function prints out all the lexemes within the text file, until the error is reached
    public static void printTokens(){
        for(int i = 0; i< current; i++){
            System.out.println(listOfLexemes.get(i));
        }
    }

    //this function determines which type of statement needs to be executed
    public static void stmt(){
        switch(currentToken){
            case IF:
                if_stmt();
                break;
            case FOR:
                for_loop();
                break;
            case IDENT:
                assign();
                break;
            case BEGIN_CURLY:
                block();
                break;
            case INT_DEC:
                declare();
                break;
            case BYTE_DEC:
                declare();
                break;
            case LONG_DEC:
                declare();
                break;
            case SHORT_DEC:
                declare();
                break;
            default :
                error();
        }
    }

    //this function represents the <stms_list> rule in my grammar
    public static void stmt_list(){
        stmt();
        //There will be 0 or more statements after the first one
        while(currentToken == IF || currentToken == FOR || currentToken == IDENT || currentToken == BEGIN_CURLY || currentToken == INT_DEC || currentToken == LONG_DEC || currentToken == BYTE_DEC || currentToken == SHORT_DEC){
            stmt();
        }
    }

    //this function represents the <declare> rule in my grammar
    //a declaration statment must have an int declaration keyword, an identifier, and an end-of-statement character
    public static void declare() {
        if(currentToken == INT_DEC || currentToken == BYTE_DEC || currentToken == LONG_DEC || currentToken == SHORT_DEC ){
            getNextToken();
            if(currentToken == IDENT){
                getNextToken();
                if(currentToken == END_OF_STATEMENT){
                    getNextToken();
                }
                else{
                    printTokens();
                    System.out.println("Error. There is not an end-of-statement character ';' at this point. ^");
                    error();
                }

            }
            else {
                printTokens();
                System.out.println("Error. There is not an indentifier at this point for the declaration statement.^");
                error();
            }
        }
        else{
            printTokens();
            System.out.println("Error. There is not an integer literal at this point for the declaration statement.^");
            error();
        }
    }

    //this function represent the <block> rule in my grammar
    //a block has 0 or more statements within a set of curly braces
    public static void block() {
        if(currentToken == BEGIN_CURLY){
            getNextToken();
            //checking for each type of statement
            while(currentToken == IF || currentToken == FOR || currentToken == IDENT || currentToken == BEGIN_CURLY || currentToken == INT_DEC || currentToken == LONG_DEC || currentToken == BYTE_DEC || currentToken == SHORT_DEC){
                stmt();
            }
            if(currentToken == END_CURLY){
                getNextToken();
            }
            else {
                printTokens();
                System.out.println("Error. There is not a closing curly brace at this point for the code block.^");
                error();
            }
        }
    }

    //this function represents the <if_stmt> rule in my grammar
    public static void if_stmt(){
        if(currentToken == IF){
            getNextToken();
            if(currentToken == LEFT_PAREN){
                getNextToken();
                bool_expr();
                if(currentToken == RIGHT_PAREN){
                    getNextToken();
                        if(currentToken ==  BEGIN_CURLY){
                            block();
                        }
                        else{
                            printTokens();
                            System.out.println("Error. There is not an opening curly brace at this point for a code block.^");
                            error();
                        }
                }
                else{
                    printTokens();
                    System.out.println("Error. There is not a closing parentheses at this point for the selection statement.^");
                    error();
                }
            }
            else {
                printTokens();
                System.out.println("Error. There is not an opening parentheses at this point for the selection statement.^");
                error();
            }
        }
    }

    //this function represents the <for_loop> rule in my grammar
    public static void for_loop(){
        if(currentToken == FOR){
            getNextToken();
            if(currentToken == LEFT_PAREN){
                getNextToken();
                for_expr();
                if(currentToken == RIGHT_PAREN){
                    getNextToken();
                    if(currentToken == BEGIN_CURLY){
                        block();
                    }
                }
                else{
                    printTokens();
                    System.out.println("Error. A closing parentheses is required at this point for the loop.^");
                    error();
                }
            }
            else{
                printTokens();
                System.out.println("Error. An int literal is required at this point for the loop boolean expression.^");
                error();
            }
        }
        else {
            System.out.println("Error. The keyword '&' is missing for the loop.^");
            error();
        }
    }

    //this function represents the <for_expr> rule in my grammar
    //the for expression is the expression that is evaluated for the loops in my language
    public static void for_expr(){
        if(currentToken == INT_LIT || currentToken == INT_LIT_BYTE || currentToken == INT_LIT_LONG || currentToken == INT_LIT_SHORT){
            getNextToken();
            if(currentToken == TO){
                getNextToken();
                if(currentToken == INT_LIT || currentToken == INT_LIT_BYTE || currentToken == INT_LIT_LONG || currentToken == INT_LIT_SHORT){
                    getNextToken();
                }
                else{
                    printTokens();
                    System.out.println("Error. An int literal is required at this point for the for-loop boolean expression.^");
                    error();
                }
            }
            else{
                printTokens();
                System.out.println("Error. The character '|' is required at this point for the for-loop boolean expression.^");
                error();
            }
        }
        else {
            printTokens();
            System.out.println("Error. An int literal is required at this point for the for-loop boolean expression.^");
            error();
        }
    }

    //this function represents the <assign> rule in my grammar
    public static void assign(){
        if(currentToken == IDENT){
            getNextToken();
            if(currentToken == ASSIGN){
                getNextToken();
                expr();
                if(currentToken == END_OF_STATEMENT){
                    getNextToken();
                }
                else{
                    printTokens();
                    System.out.println("Error. There is not an end of statement character ';' at this point.^");
                    error();
                }
            }
            else{
                printTokens();
                System.out.println("Error. The assign token '=' is missing at this point.^");
                error();
            }
        }
        else {
            System.out.println("Error. An identifier is missing for the assignment statement.^");
            error();
        }
    }

    //this function represents the <expr> rule in my grammar
    //expressions are used for assignment statements
    public static void expr() {
        term();
        while(currentToken == MULT_OP || currentToken == DIV_OP || currentToken == MODULUS){
            getNextToken();
            term();
        }

    }

    //this function represents the <bfactor> rule in my grammar
    //a "bfactor" is represented as an identifier, an integer literal, or an expression
    public static void bfactor(){
        if (currentToken == IDENT || currentToken == INT_LIT || currentToken == INT_LIT_BYTE || currentToken == INT_LIT_LONG || currentToken == INT_LIT_SHORT){
		    getNextToken();
        }
		else if (currentToken == LEFT_PAREN){
			getNextToken();
			bex();
			if(currentToken == RIGHT_PAREN){
				getNextToken();
            }
			else {
                printTokens();
                System.out.println("Error. There is not a closing parentheses at this point.^");
				error();
            }
        }
		else{
            printTokens();
            System.out.println("Error. There is not an opening parentheses at this point.^");
			error();
        }

    }

    //this function represents the <bterm> rule in my grammar
    //a "bterm" is an addition or subtraction equation between two values
    public static void bterm(){
        bfactor();
        while(currentToken == ADD_OP || currentToken == SUB_OP){
            getNextToken();
            bfactor();
        }
    }

    //this function represents the <bexr> rule in my grammar
    //the "bex" is a multiplication, division, or modulus equation between two values
    public static void bex(){
        bterm();
        while(currentToken == MULT_OP || currentToken == DIV_OP || currentToken == MODULUS){
            getNextToken();
            bterm();
        }
    }

    //this function represents the <rel> rule in my grammar
    //"rel" is a conditional
    public static void rel(){
        bex();
        while(currentToken == LESS_THAN || currentToken == GREATER_THAN || currentToken == LESSTHAN_EQUAL || currentToken == GREATERTHAN_EQUAL){
            getNextToken();
            bex();
        }
    }

    //this function represents the <bool_expr> rule in my grammar
    //"bool_expr" is a conditional
    public static void bool_expr(){
        rel();
        while(currentToken == NOT_EQUAL || currentToken == EQUAL_TO){
            getNextToken();
            rel();
        }
    }

    //this function represents the <term> rule in my grammar
    //"term" is an addition or subtraction equation between two values
    public static void term() {
        factor();
        while (currentToken == ADD_OP || currentToken == SUB_OP) {
            getNextToken();
            factor();
        }

    }

    //this function represents the <bfactor> rule in my grammar
    //a "bfactor" is an identifier, an integer literal, or an equation
    public static void factor(){
        if (currentToken == IDENT || currentToken == INT_LIT || currentToken == INT_LIT_BYTE || currentToken == INT_LIT_LONG || currentToken == INT_LIT_SHORT){
		    getNextToken();
        }
		else if (currentToken == LEFT_PAREN){
			getNextToken();
			expr();
			if(currentToken == RIGHT_PAREN){
				getNextToken();
            }
			else {
                printTokens();
                System.out.println("Error. There is not a closing parentheses at this point.^");
				error();
            }
        }
		else{
            printTokens();
            System.out.println("Error. There is not an opening parentheses at this point.^");
			error();
        }

    }

    public static void main(String[] args) throws IOException{
        lexer lexClassObject = new lexer();
        lexClassObject.main(null);
        //retrieving the lists of lexemes and tokens from lexer.java
        listOfTokens = lexClassObject.listOfTokens;
        listOfLexemes = lexClassObject.listOfLexemes;

        currentToken = listOfTokens.get(current);

        System.out.println("|---------------SYNTAX ANALYZER: -----------------|");
        if(currentToken == BEGIN){
            getNextToken();
            stmt_list();

            if(currentToken == END){
                System.out.println("No syntactical errors found.^");
            }
            else{
                System.out.println("Error. The program does not contain an end character.^");
                error();
            }
        }
        else {
            System.out.println("Error. The program does not not start with a begin character.");
            error();
        }
    }
}
