grammar VariableDescription;

INT :	'0'..'9'+;

fragment POSITIVE     : [1-9];
fragment NUMERIC      : [0-9];
fragment LOWER_ALPHA  : [a-z];
fragment UPPER_ALPHA  : [A-Z];

fragment BRACKET_OPEN : '(';
fragment BRACKET_CLOSE: ')';
fragment AMOUNT_SIGN  : '`';
fragment SEPARATOR    : ',';

fragment ALPHA_NUMERIC : LOWER_ALPHA | UPPER_ALPHA | NUMERIC | '_';

Number               : POSITIVE NUMERIC*;
Upper_word           : UPPER_ALPHA ALPHA_NUMERIC*;
Lower_word           : LOWER_ALPHA ALPHA_NUMERIC*;

WS                   : [ \r\t\n]+ -> skip ;

sequence             : amount? ('`')? (variable | variable_list)? EOF; 

amount               : INT;

variable             : Lower_word;

variable_list        : '(' variable ( ',' variable )* ')';
