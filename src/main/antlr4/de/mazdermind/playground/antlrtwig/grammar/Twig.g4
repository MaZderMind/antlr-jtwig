grammar Twig;

expression: expression ('*' | '/') expression # MathOp
          | expression ('+' | '-') expression # MathOp
          | '(' expression  ')' # Group
          | variableExpression #Variable
          | literalExpression #Literal;

variableExpression: VARIABLE;
literalExpression: literalInteger | literalFloat | literalString;

literalInteger: sign? NUMBER;
literalFloat: sign? NUMBER ('.' NUMBER)?;
literalString: STRING;

sign: MINUS | PLUS;

STRING: DOUBLE_QUOTED_STRING | SINGLE_QUOTED_STRING;
DOUBLE_QUOTED_STRING: '"'  (~('"'  | '\\') | '\\' ('"'  | '\\'))* '"';
SINGLE_QUOTED_STRING: '\'' (~('\'' | '\\') | '\\' ('\'' | '\\'))* '\'';

VARIABLE: [a-zA-Z][a-zA-Z0-9_]*;

MINUS: '-';
PLUS: '+';
NUMBER: [0-9]+;

SPACE: [ \t\r\n] -> skip;
