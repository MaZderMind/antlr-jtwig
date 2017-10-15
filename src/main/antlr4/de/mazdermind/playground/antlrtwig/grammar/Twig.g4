grammar Twig;

document: expression EOL;

expression: expression ('*' | '/') expression # MathOp
          | expression ('+' | '-') expression # MathOp
          | '(' expression  ')' # Group
          | literalExpression #Literal;

literalExpression: INT | STRING;
INT: [0-9]+;
STRING: '"' [^"]+ '"';
EOL: '\r'?'\n';

SPACE: [ \t\r\n] -> skip;
