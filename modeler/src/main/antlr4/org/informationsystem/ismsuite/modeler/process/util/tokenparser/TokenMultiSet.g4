grammar TokenMultiSet;

INT :	'0'..'9'+;

fragment NUMERIC      : [0-9];
fragment LOWER_ALPHA  : [a-z];
fragment UPPER_ALPHA  : [A-Z];

fragment ALPHA_NUMERIC : LOWER_ALPHA | UPPER_ALPHA | NUMERIC | '_';

WS                   : [ \r\t\n]+ -> skip ;

Upper_word           : UPPER_ALPHA ALPHA_NUMERIC*;
Lower_word           : LOWER_ALPHA ALPHA_NUMERIC*;

entity               : Lower_word;
entity_list          : entity (',' entity )*;

And                  : '+'+ | '&'+ | ',';

multiset             : pnidtoken (And pnidtoken)* EOF;

entities             : (entity | '(' entity_list? ')');

pnidtoken            : amount? ('`')? (entities)?;

amount               : INT;