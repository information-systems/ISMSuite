// Adapted from https://github.com/TobiasGleissner/TPTP-ANTLR4-Grammar/blob/master/tptp_v7_0_0_0.g4
grammar TFF;

// Initial stuff needed for writing things down. 

fragment Do_char : [\u0020-\u0021\u0023-\u005B\u005D-\u007E] | '\\'["\\];
fragment Sq_char : [\u0020-\u0026\u0028-\u005B\u005D-\u007E] | '\\'['\\];
fragment Sign : [+-];
fragment Exponent : [Ee];
fragment Non_zero_numeric : [1-9];
fragment Numeric : [0-9];
fragment Lower_alpha : [a-z];
fragment Upper_alpha : [A-Z];
fragment Alpha_numeric : Lower_alpha | Upper_alpha | Numeric | '_';

Or                   : '|';
And                  : '&';
Iff                  : '<=>';
Impl                 : '=>';
If                   : '<=';
Niff                 : '<~>';
Nor                  : '~|';
Nand                 : '~&';
Not                  : '~';
ForallComb           : '!!';
TyForall             : '!>';
Infix_inequality     : '!=';
Infix_equality       : '=';
Forall               : '!';
ExistsComb           : '??';
TyExists             : '?*';
Exists               : '?';
Lambda               : '^';
ChoiceComb           : '@@+';
Choice               : '@+';
DescriptionComb      : '@@-';
Description          : '@-';
EqComb               : '@=';
App                  : '@';
Assignment           : ':=';
Arrow                : '>';
Star                 : '*';
Plus                 : '+';
Subtype_sign         : '<<';
Gentzen_arrow        : '-->';
Real                 : Signed_real | Unsigned_real;
Signed_real          : Sign Unsigned_real;
Unsigned_real        : Decimal_fraction|Decimal_exponent;
Rational             : Signed_rational | Unsigned_rational;
Signed_rational      : Sign Unsigned_rational;
Unsigned_rational    : Decimal '/' Positive_decimal;
Integer              : Signed_integer | Unsigned_integer;
Signed_integer       : Sign Unsigned_integer;
Unsigned_integer     : Decimal;
Decimal              : '0' | Positive_decimal;
Positive_decimal     : Non_zero_numeric Numeric*;
Decimal_exponent     : (Decimal|Decimal_fraction) Exponent Exp_integer;
Decimal_fraction     : Decimal Dot_decimal;
Dot_decimal          : '.' Numeric Numeric*;
Exp_integer          : Signed_exp_integer|Unsigned_exp_integer;
Signed_exp_integer   : Sign Unsigned_exp_integer;
Unsigned_exp_integer : Numeric Numeric*;
Dollar_word          : '$' Lower_word;
Dollar_dollar_word   : '$$' Lower_word;
Upper_word           : Upper_alpha Alpha_numeric*;
Lower_word           : Lower_alpha Alpha_numeric*;
Single_quoted        : '\'' Sq_char+ '\'';
Distinct_object      : '"' Do_char+ '"';
WS                   : [ \r\t\n]+ -> skip ;
Line_comment         : '%' ~[\r\n]* -> skip;
Block_comment        : '/*' .*? '*/' -> skip;

fof_quantifier       : Forall | Exists;
binary_connective    : Iff | Impl | If ; // | Niff | Nor | Nand;
assoc_connective     : Or | And;
unary_connective     : Not;


// Initial rule. As we only parse simple TFF formulae, 
// we stick to this, and keep the grammar as simple as possible.
// For example, we do not allow for annotations.
tff_file                : tff_line* EOF;
tff_line                : 'tff(' name ',' formula_role ',' tff_formula ').';
formula_role            : Lower_word; // #RES no restrictions

tff_formula             : tff_logic_formula | tff_typed_atom;
tff_logic_formula       : tff_binary_formula | tff_unitary_formula;
tff_binary_formula      : tff_binary_nonassoc | tff_binary_assoc;
tff_binary_nonassoc     : tff_unitary_formula binary_connective tff_unitary_formula;
tff_binary_assoc        : tff_or_formula | tff_and_formula;
tff_or_formula          : tff_unitary_formula Or tff_unitary_formula
                        | tff_or_formula Or tff_unitary_formula;
tff_and_formula         : tff_unitary_formula And tff_unitary_formula
                        | tff_and_formula And tff_unitary_formula;
tff_unitary_formula     : tff_quantified_formula 
						| tff_unary_formula
                        | tff_atomic_formula;
tff_quantified_formula  : fof_quantifier '[' variable_list ']' ':' tff_unitary_formula ;
tff_unary_formula       : unary_connective tff_unitary_formula
                        | fof_infix_unary
                        | '(' tff_logic_formula ')'; 
tff_atomic_formula      : atomic_word ( '(' argument_list ')' )?;

fof_infix_unary         : fof_term Infix_inequality fof_term
                        | fof_term Infix_equality fof_term;
fof_term                : atomic_word ( '(' argument_list ')' )? | variable ;

argument_list           : argument (',' argument)*;
argument                : variable | atomic_word;

tff_typed_atom          : atomic_word ':' atomic_word
                        | '(' tff_typed_atom ')';

variable                : Upper_word (':' atomic_word)?;
variable_list           : variable (',' variable)*;

name                    : atomic_word | Integer;
atomic_word_list        : atomic_word (',' atomic_word_list)*;
atomic_word             : Lower_word | Single_quoted;
atomic_defined_word     : Dollar_word;
atomic_system_word      : Dollar_dollar_word;
number                  : Integer | Rational | Real;
file_name               : Single_quoted;