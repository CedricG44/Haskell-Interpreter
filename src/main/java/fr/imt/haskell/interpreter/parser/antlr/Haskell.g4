grammar Haskell;

program: function+;

function : variable variable+ '=' expression;

expression : variable                                            #Var
           | constant                                            #Const

           | '(' expression expression+ ')'                      #Application

           | '\\' variable+ '->' expression                      #Lambda

           | 'if' expression 'then' expression 'else' expression #ConditionalExpression

           | (MINUS | NOT) expression                            #UnaryExpression

           | expression PLUS expression                          #BinaryExpression
           | expression TIMES expression                         #BinaryExpression
           | expression DIVIDE expression                        #BinaryExpression
           | expression AND expression                           #BinaryExpression
           | expression OR expression                            #BinaryExpression
           | expression EQUAL expression                         #BinaryExpression
           | expression GREATERTHAN expression                   #BinaryExpression
           | expression LESSTHAN expression                      #BinaryExpression
           | expression GREATERTHANOREQUAL expression            #BinaryExpression
           | expression LESSTHANOREQUAL expression               #BinaryExpression;

/* Constants */
constant : number
         | bool;


variable : ID;

/* Integers */
number : INT;

/* Booleans */
bool : 'True'
        | 'False';

/* Unary operators */
MINUS : '-';
NOT : 'not';

/* Binary operators */
PLUS : '+';
TIMES : '*';
DIVIDE : '/';
AND : '&&';
OR : '||';
EQUAL : '==';
GREATERTHAN : '>';
LESSTHAN : '<';
GREATERTHANOREQUAL : '>=';
LESSTHANOREQUAL : '<=';

fragment LOWER_CASE : 'a'..'z';
fragment UPPER_CASE : 'A'..'Z';
fragment DIGIT : '0'..'9';

ID : LOWER_CASE (LOWER_CASE | UPPER_CASE)*;
INT : ('+'|'-')?DIGIT+;

WS: [ \t\r\n]+ -> skip;
