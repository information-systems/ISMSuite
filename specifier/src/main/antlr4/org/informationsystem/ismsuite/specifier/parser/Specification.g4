grammar Specification; 

// Initial stuff needed for writing things down. 

fragment Do_char     : [\u0020-\u0021\u0023-\u005B\u005D-\u007E] | '\\'["\\];
fragment Sq_char     : [\u0020-\u0026\u0028-\u005B\u005D-\u007E] | '\\'['\\];
fragment Sign        : [+-];
fragment Exponent    : [Ee];
fragment Non_zero_numeric : [1-9];
fragment Numeric     : [0-9];
fragment Lower_alpha : [a-z];
fragment Upper_alpha : [A-Z];
fragment Alpha_numeric : Lower_alpha | Upper_alpha | Numeric | '_';

WS                   : [ \r\t\n]+ -> skip ;
Line_comment         : '//' ~[\r\n]* -> skip;
Block_comment        : '/*' .*? '*/' -> skip;
Upper_word           : Upper_alpha Alpha_numeric*;
Lower_word           : Lower_alpha Alpha_numeric*;

specification        : ( transaction )* EOF;

transaction          : 'transaction' transaction_name '(' argument_list ')' '{' operator* '}';

argument_list        : variable_declaration (',' variable_declaration )*;
variable_declaration : variable ':' type;

operator             : (register_operator | insert_operator | remove_operator | deregister_operator ) ';' ;

register_operator    : 'register' variable ;
deregister_operator  : 'deregister' variable ;

insert_operator      : 'insert' '(' variable_list ')' 'into' relation;
remove_operator      : 'remove' '(' variable_list ')' 'from' relation;

variable_list        : variable (',' variable)*;

transaction_name     : name ('.' name )*;

name                 : Upper_word | Lower_word;
variable             : Upper_word | Lower_word;
type                 : Lower_word;
relation             : Lower_word;
