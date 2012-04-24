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
T21 : 'convert' ;
T22 : 'header' ;
T23 : '=' ;
T24 : 'body' ;
T25 : 'with' ;
T26 : 'transformer' ;
T27 : 'choice' ;
T28 : 'recipients' ;
T29 : 'dynamic' ;
T30 : 'pipeline' ;
T31 : 'filter' ;
T32 : 'splitter' ;
T33 : 'resequence' ;
T34 : 'when' ;
T35 : 'otherwise' ;
T36 : 'properties' ;
T37 : 'fault' ;
T38 : '.' ;
T39 : 'bean' ;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 649
RULE_ID :

	 ('^')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	 
;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 655
RULE_STRING :

	 '"' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'"') )* '"' |
	 '\'' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\'') )* '\''
	 
;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 662
RULE_INT :

	 ('-')?('0'..'9')+
	 
;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 668
RULE_WS :

	 (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;}
	 
;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 674
RULE_ML_COMMENT :

	 '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	 
;

// $ANTLR src "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g" 680
RULE_SL_COMMENT :

	 '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
	 
;

