grammar CPNToken;

INT :	'0'..'9'+;

WS                   : [ \r\t\n]+ -> skip ;
Line_comment         : '//' ~[\r\n]* -> skip;
Block_comment        : '/*' .*? '*/' -> skip;

token_list  : token ( '++' token )* EOF;
token       : count '`' token_value;
count       : INT;
token_value : id | id_list;
id          : INT;
id_list     : '(' id (',' id  )* ')';