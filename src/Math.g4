grammar Math;

program
        : definitions block '.'
        ;

definitions
        : var_def? proc_def?
        ;

var_def
        : 'var' variable SEMICOLON (variable SEMICOLON)*
        ;

variable
        : IDENT (',' IDENT)* ':' type
        ;

proc_def
        : (( procedure | function ) SEMICOLON)*
        ;

procedure
        : 'procedure' header SEMICOLON body
        ;

function
        : 'function' header ':' type SEMICOLON body
        ;

header
        : IDENT ( LPAREN variable (SEMICOLON variable)* RPAREN )?
        ;

body
        : ( var_def )? block
        ;

type
        : 'integer' | 'array' '[' NUMBER ']' 'of' 'integer'
        ;

block
        : 'begin' stmt ( SEMICOLON stmt )* 'end'
        ;

stmt
        : for_stmt
        | if_stmt
        | while_stmt
        | repeat_stmt
        | assign_stmt
        | proc_stmt
        | null_stmt
        | block
        | read_stmt
        | write_stmt
        | writeln_stmt
        ;

null_stmt
        :
        ;

proc_stmt
        : IDENT ( proc_args )?
        ;

assign_stmt
        : IDENT ':=' expr                                           # SimpleValue
        ;

repeat_stmt
        : 'repeat' stmt ( SEMICOLON stmt )* 'until' expr
        ;

while_stmt
        : 'while' expr 'do' stmt
        ;

if_stmt
        : 'if' expr 'then' stmt ( 'else' stmt )?             # ifStmt
        ;

for_stmt
        : 'for' assign_stmt op=( TO | DOWNTO ) expr 'do' stmt       # forStmt
        ;

read_stmt
        : 'read' LPAREN IDENT ( ',' IDENT )* RPAREN
        ;

write_stmt
        : 'write' proc_args                                     #Write
        ;

writeln_stmt
        : 'writeln' proc_args                           # Writeln
        ;

expr
        : expr1 ( ( 'or' | 'and' ) expr1 )*
        ;

expr1
        : expr2 ( op=( LESS | LESS_EQUAL | MORE | MORE_EQUAL | EQUALS | NOT_EQUAL ) expr2 )*              #Comprasion
        ;

expr2
        : expr3 ( ( PLUS | MINUS ) expr3 )*                     # Addition
        ;

expr3
        : expr4 ( ( SLASH | STAR | 'mod' ) expr4 )*                   # Multiplication
        ;

expr4
        : ( PLUS | MINUS | 'not' ) expr4                                                    # Minus
        | expr5                                                                             # Value
        ;

expr5
        : IDENT                                             # Ident
        | NUMBER                                            # Number
        | LPAREN expr RPAREN                                # Parenthesis
;

index
        : '[' NUMBER ']'
        ;

proc_args
        : LPAREN expr ( ',' expr )* RPAREN
        ;

PLUS       : '+' ;
MINUS      : '-' ;
STAR       : '*' ;
SLASH      : '/' ;
EQUALS     : '=' ;
LPAREN     : '(' ;
RPAREN     : ')' ;
SEMICOLON  : ';' ;
NOT_EQUAL  : '<>';
LESS       : '<' ;
MORE       : '>' ;
LESS_EQUAL : '<=';
MORE_EQUAL : '>=';
TO         : 'to';
DOWNTO     : 'downto';

IDENT
        : [A-Za-z_] [A-Za-z0-9_]*
        ;

NUMBER
        : [0-9]+
        ;

WS
        : [ \f\t\u0014\r\n]+ -> skip
        ;