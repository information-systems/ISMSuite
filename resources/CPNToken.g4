grammar CPNToken;

fragment Numeric     : [0-9];

WS                   : [ \r\t\n]+ -> skip ;
Line_comment         : '//' ~[\r\n]* -> skip;
Block_comment        : '/*' .*? '*/' -> skip;

token_list  : token ( '++' token )* EOF;
token       : count '`' token_value;
count       : Numeric+;
token_value : id | id_list;
id          : Numeric+;
id_list     : '(' id (',' id  )* ')';