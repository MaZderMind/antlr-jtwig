grammar Twig;

expression: expression ('*' | '/') expression # MathOp
          | expression ('+' | '-') expression # MathOp
          | '(' expression  ')' # Group
          | variableAccessExpression #Access
          | variableExpression #Variable
          | literalExpression #Literal;

variableExpression: VARIABLE;
literalExpression: literalInteger | literalFloat | literalString;

literalInteger: sign? NUMBER;
literalFloat: sign? NUMBER ('.' NUMBER)?;
literalString: STRING;

sign: MINUS | PLUS;

variableAccessExpression: variableExpression accessExpression;

accessExpression: (arrayAccessExpression | propertyAccessExpression) accessExpression*;
arrayAccessExpression: '[' expression ']';
propertyAccessExpression: '.' VARIABLE;

STRING: DOUBLE_QUOTED_STRING | SINGLE_QUOTED_STRING;
DOUBLE_QUOTED_STRING: '"'  (~('"'  | '\\') | '\\' ('"'  | '\\'))* '"';
SINGLE_QUOTED_STRING: '\'' (~('\'' | '\\') | '\\' ('\'' | '\\'))* '\'';

VARIABLE: [a-zA-Z][a-zA-Z0-9_]*;

MINUS: '-';
PLUS: '+';
NUMBER: [0-9]+;

SPACE: [ \t\r\n] -> skip;
