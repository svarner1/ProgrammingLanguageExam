Question a)
    
    My regex representation:
        Addition                                        +
        Subtraction                                     -
        Multiplication                                  *
        Division                                        /
        Module                                          %
        Less than                                       <
        Greater than                                    >
        Less or equal to                                ~
        Greater than or equal to                        ^
        Equal to                                        @
        Not equal to                                    !
        Assignment                                      =
        Symbol for "byte" (used in declaration)         ' 
        Symbol for "long" (used in declaration)         :
        Symbol for "short" (used in declaration)        `
        Symbol for "int" (used in declaration)          "
        Left Parentheses                                (
        Right Parentheses                               )
        Begin Symbol (to symbolize start of program)    $
        End symbol (to symbolize end of program)        ?
        End of statement                                ;
        Opening curly brace                             {
        Closing curly brace                             }
        To (for my loops; Ex. from 1 to 5)              |

        Variable names:
            [a-zA-Z_][a-zA-Z_][a-zA-Z_][a-zA-Z_][a-zA-Z_][a-zA-Z_][a-zA-Z_]?[a-zA-Z_]?

        Integer Literal Types:
            1 byte  B
            2 bytes S
            4 bytes I
            8 bytes L

        Integer Literal Regex Expression:
            1 byte integer literal:     [0-9]+B
            2 byte integer literal:     [0-9]+S
            4 byte integer literal:     [0-9]+I
            8 byte integer literal:     [0-9]+L

Question b)
    
    <program> --> `$`<stmt_list>`?`
    <stmt_list> --> <stmt> `;` {<stmt>`;`}

    <stmt> --> <if_stmt> | <for_loop> | <assignment> | <block> | <declare>
    <int_declaration> → ` ’ ` | ` ” ` | ` ` ` | `:`
    <declare> --> <int_declaration> `id` `;`

    <block> --> `{` { <stmt> ;} `}`
    <if_stmt> --> `#` `(`<bool_expr> `)` <block> 
    <for_loop> --> `&` `(` for_expr> `)` <block>
    <for_expr> --> `int_lit` `to` `int_lit`
    <assignment> --> `id` `=` <expr> `;`
    <expr> --> <term> {(`*`|`/`|`%`) <term> }
    <term> -->  <factor> {(`+`|`-`) <factor> }
    <factor> --> `id`| `int_lit`| `(`<expr>`)`

    <bool_expr> --> <rel> {(`!`|`@`)} <rel>
    <rel> --> <bex> {(`<`|`>`|`~`|`^`) <bex> }
    <bex> --> <bterm> {(`*`|`/`|`%`) <bterm> }
    <bterm> --> <bfactor> {(`+`|`-`) <bfactor>}
    <bfactor> --> `id`|`int_lit`|`(`<bex>`)`

Question c)
    The grammar for my language is pairwise disjoint. This is because for every rule there doesn't exist a nonterminal that has multiple rules for one
    terminal. Further, there aren't any rules that cause left hand recursion, in which a nonterminal calls itself as the first character.

Question d)
    The grammar for my programming lanugage is not ambiguous because there does not exist a sequence of tokens that can be derived by two different
    leftmost derivations

Question g)
    
    Test files without errors: TestFile1.txt and TestFile3.txt
    
    Test file with lexical errors: TestFile2.txt
        The errors are as follows:
            Line 1 - the identifier "varva" cannot be declared as having less than 6 characters
            Line 3 - the identifier "7varvar_" cannot include a number
            Line 4 - the integer literal 5 must end with one of the chars B, S, I, or L in order to indicate its integer type
            Line 7 - the identifier "7varvar_" cannot include a number (causes an two errors because the identifier appears twice)

    Test file with syntactical errors: TestFile4.txt
        The errors are as follows: 
            Line 1 - The start character $ is not at the beginning
            Line 2 - The statemnt "varvar = 3S" is missing a semicolon
            Line 3 - The statement "' varvar_" is missing a semicolon
            Line 4 - The statement "varvar_ = 5S" is misssing a semicolon
            Line 5 - The for loop is written incorrenctly. The symbol # must precede an open parentheses character
            Line 7 - the expression "varvar_ varvar +" for the assign statement is written incorrectly. The '+' must precede "varvar".
            Line 8 - The statement "varvar = varvar - 1S" is missing a semicolon

Question h)
    - Screenshots of my LR parse table: ParserFirstTable.png and ParserLRGrammar.png
    - Screenshots of 2 samples that produced parsing errors: ParserSample1.png and ParserSample2.png
    - Screenshots of 2 samples that successfully parses: ParserSample3.png and ParserSample4.png
