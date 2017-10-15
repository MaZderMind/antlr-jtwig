grammar Twig;

document: expression EOL;

expression: expression ('*' | '/') expression # MathOp
          | expression ('+' | '-') expression # MathOp
          | '(' expression  ')' # Group
          | variableExpression #Variable
          | literalExpression #Literal;

literalExpression: INT | STRING;
variableExpression: VARIABLE;

INT: [0-9]+;
STRING: '"' [^"]+ '"';
VARIABLE: [a-zA-Z][a-zA-Z0-9_]*;
EOL: '\r'?'\n';

SPACE: [ \t\r\n] -> skip;
