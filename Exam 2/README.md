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
    <declare> --> `id` <int_declaration>  `;`

    <block> --> `{`<stmt>`}`
    <if_stmt> --> `#` `(`<bool_expr> `)` <stmt> 
    <for_loop> --> `&` `(` <bool_expr> `)` <stmt>
    <assignment> --> `id` `=` <expr> `;`
    <expr> --> <term> {(`*`|`/`|`%`)} <term> 
    <term> -->  <factor> {(`+`|`-`)} <factor> 
    <factor> --> `id`| `int_lit`| `(`<expr>`)`

    <bool_expr> --> <rel> {(`!`|`@`)} <rel>
    <rel> --> <bex> {(`<`|`>`|`~`|`^`)} <bex>
    <bex> --> <bterm> {(`*`|`/`|`%`)} <bterm>
    <bterm> --> <bfactor> {(`+`|`-`)} <bfactor>
    <bfactor> --> `id`|`int_lit`|`bool_lit`|`(`<bex>`)`

Question c)
    The grammar for my language is pairwise disjoint. This is because for every rule there doesn't exist a nonterminal that has multiple rules for one terminal. Further, there aren't any rules that cause left hand recursion, in which a nonterminal calls itself as the first character.

Question d)
    The grammar for my programming lanugage is not ambiguous because there does not exist a sequence of tokens that can be derived by two different leftmost derivations

Question e)
    My lexical analyzer is the file lex.java
    
Question g)
    Test files without errors: TestFile1.txt and TestFile3.txt
    
    Test file with lexical errors: TestFile2.txt
    
    Test file with syntactical errors: TestFile4.txt

Question h)
    - Screenshots of my LR parse table are titles Screenshot1.png, Screenshot2.png, and Screenshot3.png
