lexer grammar spit;
@members {
	 private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
	public List<ErrorMsg> getErrors() {
		return errors;
	}

	public String getErrorMessage(RecognitionException e, String[] tokenNames) {
		String msg = super.getErrorMessage(e,tokenNames);
		errors.add(AntlrUtil.create(msg,e,tokenNames));
		return msg;
	}
}
@header {
package org.apache.camel.spit.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;

}

T10 : 'route' ;
T11 : '{' ;
T12 : '}' ;
T13 : 'import' ;
T14 : 'global' ;
T15 : 'interceptors' ;
T16 : 'interceptor' ;
T17 : '(' ;
T18 : ')' ;
T19 : 'from' ;
T20 : 'to' ;
T21 : 'transformer' ;
T22 : 'choice' ;
T23 : 'recipients' ;
T24 : 'dynamic' ;
T25 : 'pipeline' ;
T26 : 'filter' ;
T27 : 'splitter' ;
T28 : 'resequence' ;
T29 : 'when' ;
T30 : 'otherwise' ;
T31 : 'properties' ;
T32 : '=' ;
T33 : 'fault' ;
T34 : '.' ;
T35 : 'bean' ;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 564
RULE_ID :

	 ('^')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	 
;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 570
RULE_STRING :

	 '"' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'"') )* '"' |
	 '\'' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\'') )* '\''
	 
;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 577
RULE_INT :

	 ('-')?('0'..'9')+
	 
;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 583
RULE_WS :

	 (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;}
	 
;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 589
RULE_ML_COMMENT :

	 '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	 
;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 595
RULE_SL_COMMENT :

	 '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
	 
;

