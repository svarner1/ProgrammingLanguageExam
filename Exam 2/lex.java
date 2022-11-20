import java.io.*;
import java.util.*;
import static java.util.Map.entry;

class lex{
    //variables
    static int charClass;
    static ArrayList<char[]> listOfLexemes = new ArrayList<>();
    static ArrayList<Integer> listOfTokens = new ArrayList<>();
    static char[] lexeme = new char[100];
    static char nextChar;
    static int lexLen;
    static int token; 
    static int nextToken = 0;
    static FileReader fr = null;

    //character classes
    //These are used to determine what identifier is being read)
    final static int LETTER = 0;
    final static int DIGIT = 1;
    final static int UNKNOWN = 99;

    //token codes
    // A specific type of token is signaled by the character classes
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

        //This is a map of all the lexemes in my language and their corresponding token code values
        static Map<Integer, String> tokenMap = Map.ofEntries(
            entry(10, "INT_LIT_BYTE"),
            entry(11, "INT_LIT_SHORT"),
            entry(12, "INT_LIT"), 
            entry(13, "INT_LIT_LONG"),
            entry(14, "INT_WITHOUT_TYPE"),
            entry(15, "IDENT"),
            entry(20, "ASSIGN_OP"),
            entry(21, "ADD_OP"),
            entry(22, "SUB_OP"),
            entry(23, "MULT_OP"),
            entry(24, "DIV_OP"),
            entry(25, "LEFT_PAREN"),
            entry(26, "RIGHT_PAREN"),
            entry(27, "MODULUS" ),
            entry(28, "LESS_THAN"),
            entry(29, "GREATER_THAN"),
            entry(30, "LESSTHAN_EQUAL"),
            entry(31, "GREATERTHAN_EQUAL"),
            entry(33, "NOT_EQUAL"),
            entry(34, "EQUAL"),
            entry(35, "EQUAL_TO"),
            entry(36, "ASSIGN"),
            entry(37, "BEGIN"),
            entry(38, "END"),
            entry(39, "FOR"),
            entry(40, "IF"),
            entry(41, "INT_DEC"),
            entry(42, "BYTE_DEC"),
            entry(43, "LONG_DEC"),
            entry(44, "SHORT_DEC"),
            entry(45, "END_OF_STATEMENT"),
            entry(46, "BEGIN_CURLY"),
            entry(47, "END_CURLY"),
            entry(48, "TO")
    );

    //getChar() gets the next character and determines its class
    public static void getChar() throws IOException{
        int i;
        if ((i = fr.read()) != -1){
            if(Character.isLetter((char)i) || (char)i == '_'){
                charClass = LETTER;
            }
            else if(Character.isDigit((char)i)){
                charClass = DIGIT;
            }
            else {
                charClass = UNKNOWN;
            }
            nextChar = (char)i;
        } 
        else {
            charClass = EOF;
        }
    }

    //getnonBlank() skips every whitespace character in the input file
    public static void getNonBlank() throws IOException{
        while(Character.isWhitespace(nextChar)) {
            getChar();
        }
    }

    //getLexemeLength() returns the length of a particular lexeme that is stored in the lexeme char array
    public static int getLexemeLength(char[] charArray){
        int length = 0;
        for(char ch: charArray){
            if(ch == '\0'){
                break;
            }
            length++;
        }
        return length;
    }

    //addChar() adds a character to the lexeme char arrya
    public static void addChar() {
        if(lexLen <= 98) {
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = '\0';
        }
        else {
            System.out.println("Error. The lexeme is too long.");
            System.exit(0);
        }
        String string = new String(lexeme);
    }

    //lookup() is used to determine what type of token a particular char symbol is
    public static int lookup(char ch) {
        switch(ch) {
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;
            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;
            case '+':
                addChar();
                nextToken = ADD_OP;
                break;
            case '-':
                addChar();
                nextToken = SUB_OP;
                break;
            case '*':
                addChar();
                nextToken = MULT_OP;
                break;
            case '/':
                addChar();
                nextToken = DIV_OP;
                break;
            case '%':
                addChar();
                nextToken = MODULUS;
                break;
            case '<':
                addChar();
                nextToken = LESS_THAN;
                break;
            case '>':
                addChar();
                nextToken = GREATER_THAN;
                break;
            case '=':
                addChar();
                nextToken = ASSIGN;
                break;
            case '!':
                addChar();
                nextToken = NOT_EQUAL;
                break;
            case '^':
                addChar();
                nextToken = GREATERTHAN_EQUAL;
                break;
            case '~':
                addChar();
                nextToken = LESSTHAN_EQUAL;
                break;
            case '@':
                addChar();
                nextToken = EQUAL_TO;
                break;
            case '$':
                addChar();
                nextToken = BEGIN;
                break;
            case '?':
                addChar();
                nextToken = END;
                break;
            case '\"':
                addChar();
                nextToken = INT_DEC;
                break;
            case '\'':
                addChar();
                nextToken = BYTE_DEC;
                break;
            case ':':
                addChar();
                nextToken = LONG_DEC;
                break;
            case '`':
                addChar();
                nextToken = SHORT_DEC;
                break;
            case ';':
                addChar();
                nextToken = END_OF_STATEMENT;
                break;
            case '#':
                addChar();
                nextToken = IF;
                break;
            case '&':
                addChar();
                nextToken = FOR;
                break;
            case '|':
                addChar();
                nextToken = TO;
                break;
            case '{':
                addChar();  
                nextToken = BEGIN_CURLY;
                break;
            case '}':
                addChar();
                nextToken = END_CURLY;
                break;
            default:
                System.out.println("Error. Unknown symbol " + nextChar + " found.");
                System.exit(0);
        }
        return nextToken;
    }

    //checkNumbersWithoutTypes() determines whether a number is input in the code without a int literal type
    public static void checkNumbersWithoutTypes(){
        //check whether the list contains 14
        if(listOfTokens.contains(14)){
            int index = listOfTokens.indexOf(14);
            String lex = new String (listOfLexemes.get(index));

            System.out.println("Error. The digit value " + lex + " has not been given a type of S (1 bytes), B (2 bytes) , I (4 bytes), or L(8 bytes)");
            System.exit(0);
        }
    }

    //checkDigitsWithinVariables() determines whether a program includes a line of code that declares a varible with a digit in its name
    public static void checkDigitsWithinVariables(){
        ArrayList<Integer> unknownNumBeforeIdent = new ArrayList<Integer>(Arrays.asList(14, 15));
        ArrayList<Integer> identmBeforeUnknownNum = new ArrayList<Integer>(Arrays.asList(15, 14));

        ArrayList<Integer> byteBeforeIdent = new ArrayList<Integer>(Arrays.asList(10, 15));
        ArrayList<Integer> identBeforeByte = new ArrayList<Integer>(Arrays.asList(15, 10));

        ArrayList<Integer> shortBeforeIdent = new ArrayList<Integer>(Arrays.asList(11, 15));
        ArrayList<Integer> identBeforeShort = new ArrayList<Integer>(Arrays.asList(15, 11));

        ArrayList<Integer> intLitBeforeIdent = new ArrayList<Integer>(Arrays.asList(12, 15));
        ArrayList<Integer> identBeforeIntlit= new ArrayList<Integer>(Arrays.asList(15, 12));

        ArrayList<Integer> longBeforeIdent = new ArrayList<Integer>(Arrays.asList(13, 15));
        ArrayList<Integer> identBeforeLong = new ArrayList<Integer>(Arrays.asList(15, 13));

        if(Collections.indexOfSubList(listOfTokens, unknownNumBeforeIdent) != -1){
            System.out.println("Error. Variables cannot contain digits. An number without a type was found to precede an indentifier.");
            System.exit(0);
        }

        if(Collections.indexOfSubList(listOfTokens, identmBeforeUnknownNum) != -1){
            System.out.println("Error. Variables cannot contain digits. An number without a type was found to precede an indentifier.");
            System.exit(0);
        }

        if(Collections.indexOfSubList(listOfTokens, byteBeforeIdent) != -1){
            System.out.println("Error. Variables cannot contain digits. A byte value was found to precede an identifier.");
            System.exit(0);
        }

        if(Collections.indexOfSubList(listOfTokens, identBeforeByte) != -1){
            System.out.println("Error. Variables cannot contain digits. An identifier was found to precede a byte value.");
            System.exit(0);
        }

        if(Collections.indexOfSubList(listOfTokens, shortBeforeIdent) != -1){
            System.out.println("Error. Variables cannot contain digits. A short value was found to precede an identifier.");
            System.exit(0);
        }

        if(Collections.indexOfSubList(listOfTokens, identBeforeShort) != -1){
            System.out.println("Error. Variables cannot contain digits. An identifier was found to precede a short value.");
            System.exit(0);
        }

        if(Collections.indexOfSubList(listOfTokens, intLitBeforeIdent) != -1){
            System.out.println("Error. Variables cannot contain digits. An int value was found to precede an identifier.");
            System.exit(0);
        }

        if(Collections.indexOfSubList(listOfTokens, identBeforeIntlit) != -1){
            System.out.println("Error. Variables cannot contain digits. An identifier was found to precede an identifier.");
            System.exit(0);
        }  

        if(Collections.indexOfSubList(listOfTokens, longBeforeIdent) != -1){
            System.out.println("Error. Variables cannot contain digits. A long value was found to precede an indentifier");
            System.exit(0);
        }

        if(Collections.indexOfSubList(listOfTokens, identBeforeLong) != -1){
            System.out.println("Error. Variables cannot contain digits. An identifier was found to precede a long value.");
            System.exit(0);
        }
    }

    //lex() determines the type of lexeme for a set of characters
    public static void lex() throws IOException {
        lexLen = 0;
        getNonBlank();
        switch(charClass){
            //identifiers
            case LETTER:
                addChar();
                getChar();
                while (charClass == LETTER) {
                    addChar();
                    getChar();
                }
                int lexemeLength = getLexemeLength(lexeme);

                if(lexemeLength  < 6 ){
                    String lexemeString = new String(lexeme);
                    System.out.println("Error. The length of the variable " + lexemeString + " is too short.");
                    System.exit(0);
                }
                else if(lexemeLength > 8){
                    String lexemeString = new String(lexeme);
                    System.out.println("Error. The length of the variable " + lexemeString + " is too long.");
                    System.exit(0);
                }
                nextToken = IDENT;
                break;
            //integer literals 
            case DIGIT:
                addChar();
                getChar();
                while (charClass == DIGIT){
                    addChar();
                    getChar();
                }
                if(charClass == LETTER){
                    if(nextChar == 'B'){
                        nextToken = INT_LIT_BYTE;
                    }
                    else if(nextChar == 'S'){
                        nextToken = INT_LIT_SHORT;
                    }
                    else if(nextChar == 'I'){
                        nextToken = INT_LIT;
                    }
                    else if(nextChar == 'L'){
                        nextToken = INT_LIT_LONG;
                    }
                    else {
                        nextToken = INT_WITHOUT_TYPE;
                        break;
                    }
                    addChar();
                    getChar();
                }
                else {
                    nextToken = INT_WITHOUT_TYPE;
                }
                break;
            //parentheses and operators
            case UNKNOWN:
                lookup(nextChar);
                getChar();
                break;
            //end of file
            case EOF:
                nextToken = EOF;
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'F';
                lexeme[3] = '\0';
                break;
        }
    }

    //main() drives the lexical analyzer program. It returns a list of lexemes and a list of their corresponding token values from an input file
    public static void main(String[] args) throws IOException{
        File f = new File("TestFile2.txt");
        fr = new FileReader(f);
        
        //determines each char's class
        getChar();
        do {
            lex();
            listOfLexemes.add(lexeme);
            listOfTokens.add(nextToken);
            lexeme = new char[100];
        } while (nextToken != END);
        
        checkNumbersWithoutTypes();
        checkDigitsWithinVariables();

        System.out.println("List of lexemes:");
        for(char[] charArray: listOfLexemes){
            System.out.println(charArray);
        }

        System.out.println("List of tokens:");
        for(Integer token: listOfTokens){
            System.out.println(token + ": " + tokenMap.get(token));
        }
    }
}