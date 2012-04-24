// $ANTLR 3.0 ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g 2008-11-07 14:56:34

package org.apache.camel.spit.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class spitLexer extends Lexer {
    public static final int T21=21;
    public static final int RULE_ML_COMMENT=8;
    public static final int T14=14;
    public static final int T29=29;
    public static final int RULE_ID=4;
    public static final int T33=33;
    public static final int T22=22;
    public static final int T11=11;
    public static final int RULE_STRING=5;
    public static final int T12=12;
    public static final int T28=28;
    public static final int T23=23;
    public static final int T35=35;
    public static final int T13=13;
    public static final int T34=34;
    public static final int T20=20;
    public static final int T10=10;
    public static final int T25=25;
    public static final int T18=18;
    public static final int RULE_WS=7;
    public static final int T26=26;
    public static final int T15=15;
    public static final int RULE_INT=6;
    public static final int EOF=-1;
    public static final int T32=32;
    public static final int T17=17;
    public static final int Tokens=36;
    public static final int T31=31;
    public static final int T16=16;
    public static final int T27=27;
    public static final int RULE_SL_COMMENT=9;
    public static final int T30=30;
    public static final int T24=24;
    public static final int T19=19;

    	 private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
    	public List<ErrorMsg> getErrors() {
    		return errors;
    	}

    	public String getErrorMessage(RecognitionException e, String[] tokenNames) {
    		String msg = super.getErrorMessage(e,tokenNames);
    		errors.add(AntlrUtil.create(msg,e,tokenNames));
    		return msg;
    	}

    public spitLexer() {;} 
    public spitLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g"; }

    // $ANTLR start T10
    public void mT10() throws RecognitionException {
        try {
            int _type = T10;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:22:7: ( 'route' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:22:7: 'route'
            {
            match("route"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T10

    // $ANTLR start T11
    public void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:23:7: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:23:7: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:24:7: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:24:7: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:25:7: ( 'import' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:25:7: 'import'
            {
            match("import"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:26:7: ( 'global' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:26:7: 'global'
            {
            match("global"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:27:7: ( 'interceptors' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:27:7: 'interceptors'
            {
            match("interceptors"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:28:7: ( 'interceptor' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:28:7: 'interceptor'
            {
            match("interceptor"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:29:7: ( '(' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:29:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:30:7: ( ')' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:30:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:31:7: ( 'from' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:31:7: 'from'
            {
            match("from"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:32:7: ( 'to' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:32:7: 'to'
            {
            match("to"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:33:7: ( 'transformer' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:33:7: 'transformer'
            {
            match("transformer"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:34:7: ( 'choice' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:34:7: 'choice'
            {
            match("choice"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:35:7: ( 'recipients' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:35:7: 'recipients'
            {
            match("recipients"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:36:7: ( 'dynamic' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:36:7: 'dynamic'
            {
            match("dynamic"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:37:7: ( 'pipeline' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:37:7: 'pipeline'
            {
            match("pipeline"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:38:7: ( 'filter' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:38:7: 'filter'
            {
            match("filter"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:39:7: ( 'splitter' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:39:7: 'splitter'
            {
            match("splitter"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:40:7: ( 'resequence' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:40:7: 'resequence'
            {
            match("resequence"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:41:7: ( 'when' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:41:7: 'when'
            {
            match("when"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start T30
    public void mT30() throws RecognitionException {
        try {
            int _type = T30;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:42:7: ( 'otherwise' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:42:7: 'otherwise'
            {
            match("otherwise"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T30

    // $ANTLR start T31
    public void mT31() throws RecognitionException {
        try {
            int _type = T31;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:43:7: ( 'properties' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:43:7: 'properties'
            {
            match("properties"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T31

    // $ANTLR start T32
    public void mT32() throws RecognitionException {
        try {
            int _type = T32;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:44:7: ( '=' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:44:7: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T32

    // $ANTLR start T33
    public void mT33() throws RecognitionException {
        try {
            int _type = T33;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:45:7: ( 'fault' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:45:7: 'fault'
            {
            match("fault"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T33

    // $ANTLR start T34
    public void mT34() throws RecognitionException {
        try {
            int _type = T34;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:46:7: ( '.' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:46:7: '.'
            {
            match('.'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T34

    // $ANTLR start T35
    public void mT35() throws RecognitionException {
        try {
            int _type = T35;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:47:7: ( 'bean' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:47:7: 'bean'
            {
            match("bean"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start RULE_ID
    public void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:566:3: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:566:3: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:566:3: ( '^' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:566:4: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:566:33: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ID

    // $ANTLR start RULE_STRING
    public void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:572:3: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\'' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\"') ) {
                alt5=1;
            }
            else if ( (LA5_0=='\'') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("570:1: RULE_STRING : ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\'' );", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:572:3: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )* '\"'
                    {
                    match('\"'); 
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:572:7: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )*
                    loop3:
                    do {
                        int alt3=3;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0=='\\') ) {
                            alt3=1;
                        }
                        else if ( ((LA3_0>='\u0000' && LA3_0<='!')||(LA3_0>='#' && LA3_0<='[')||(LA3_0>=']' && LA3_0<='\uFFFE')) ) {
                            alt3=2;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:572:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:572:53: ~ ( '\\\\' | '\"' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:573:3: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\''
                    {
                    match('\''); 
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:573:8: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )*
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            alt4=1;
                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='&')||(LA4_0>='(' && LA4_0<='[')||(LA4_0>=']' && LA4_0<='\uFFFE')) ) {
                            alt4=2;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:573:10: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:573:54: ~ ( '\\\\' | '\\'' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_STRING

    // $ANTLR start RULE_INT
    public void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:579:3: ( ( '-' )? ( '0' .. '9' )+ )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:579:3: ( '-' )? ( '0' .. '9' )+
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:579:3: ( '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:579:4: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:579:9: ( '0' .. '9' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:579:10: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_INT

    // $ANTLR start RULE_WS
    public void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:585:3: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:585:3: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:585:3: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\t' && LA8_0<='\n')||LA8_0=='\r'||LA8_0==' ') ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_WS

    // $ANTLR start RULE_ML_COMMENT
    public void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:591:3: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:591:3: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:591:8: ( options {greedy=false; } : . )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='*') ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1=='/') ) {
                        alt9=2;
                    }
                    else if ( ((LA9_1>='\u0000' && LA9_1<='.')||(LA9_1>='0' && LA9_1<='\uFFFE')) ) {
                        alt9=1;
                    }


                }
                else if ( ((LA9_0>='\u0000' && LA9_0<=')')||(LA9_0>='+' && LA9_0<='\uFFFE')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:591:36: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match("*/"); 

            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ML_COMMENT

    // $ANTLR start RULE_SL_COMMENT
    public void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:597:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:597:3: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:597:8: (~ ( '\\n' | '\\r' ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFE')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:597:8: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:597:22: ( '\\r' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:597:22: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_SL_COMMENT

    public void mTokens() throws RecognitionException {
        // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:10: ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT )
        int alt12=32;
        switch ( input.LA(1) ) {
        case 'r':
            {
            switch ( input.LA(2) ) {
            case 'e':
                {
                switch ( input.LA(3) ) {
                case 's':
                    {
                    int LA12_44 = input.LA(4);

                    if ( (LA12_44=='e') ) {
                        int LA12_63 = input.LA(5);

                        if ( (LA12_63=='q') ) {
                            int LA12_81 = input.LA(6);

                            if ( (LA12_81=='u') ) {
                                int LA12_99 = input.LA(7);

                                if ( (LA12_99=='e') ) {
                                    int LA12_114 = input.LA(8);

                                    if ( (LA12_114=='n') ) {
                                        int LA12_127 = input.LA(9);

                                        if ( (LA12_127=='c') ) {
                                            int LA12_136 = input.LA(10);

                                            if ( (LA12_136=='e') ) {
                                                int LA12_144 = input.LA(11);

                                                if ( ((LA12_144>='0' && LA12_144<='9')||(LA12_144>='A' && LA12_144<='Z')||LA12_144=='_'||(LA12_144>='a' && LA12_144<='z')) ) {
                                                    alt12=27;
                                                }
                                                else {
                                                    alt12=19;}
                                            }
                                            else {
                                                alt12=27;}
                                        }
                                        else {
                                            alt12=27;}
                                    }
                                    else {
                                        alt12=27;}
                                }
                                else {
                                    alt12=27;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                    }
                    break;
                case 'c':
                    {
                    int LA12_45 = input.LA(4);

                    if ( (LA12_45=='i') ) {
                        int LA12_64 = input.LA(5);

                        if ( (LA12_64=='p') ) {
                            int LA12_82 = input.LA(6);

                            if ( (LA12_82=='i') ) {
                                int LA12_100 = input.LA(7);

                                if ( (LA12_100=='e') ) {
                                    int LA12_115 = input.LA(8);

                                    if ( (LA12_115=='n') ) {
                                        int LA12_128 = input.LA(9);

                                        if ( (LA12_128=='t') ) {
                                            int LA12_137 = input.LA(10);

                                            if ( (LA12_137=='s') ) {
                                                int LA12_145 = input.LA(11);

                                                if ( ((LA12_145>='0' && LA12_145<='9')||(LA12_145>='A' && LA12_145<='Z')||LA12_145=='_'||(LA12_145>='a' && LA12_145<='z')) ) {
                                                    alt12=27;
                                                }
                                                else {
                                                    alt12=14;}
                                            }
                                            else {
                                                alt12=27;}
                                        }
                                        else {
                                            alt12=27;}
                                    }
                                    else {
                                        alt12=27;}
                                }
                                else {
                                    alt12=27;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                    }
                    break;
                default:
                    alt12=27;}

                }
                break;
            case 'o':
                {
                int LA12_25 = input.LA(3);

                if ( (LA12_25=='u') ) {
                    int LA12_46 = input.LA(4);

                    if ( (LA12_46=='t') ) {
                        int LA12_65 = input.LA(5);

                        if ( (LA12_65=='e') ) {
                            int LA12_83 = input.LA(6);

                            if ( ((LA12_83>='0' && LA12_83<='9')||(LA12_83>='A' && LA12_83<='Z')||LA12_83=='_'||(LA12_83>='a' && LA12_83<='z')) ) {
                                alt12=27;
                            }
                            else {
                                alt12=1;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
                }
                break;
            default:
                alt12=27;}

            }
            break;
        case '{':
            {
            alt12=2;
            }
            break;
        case '}':
            {
            alt12=3;
            }
            break;
        case 'i':
            {
            switch ( input.LA(2) ) {
            case 'n':
                {
                int LA12_26 = input.LA(3);

                if ( (LA12_26=='t') ) {
                    int LA12_47 = input.LA(4);

                    if ( (LA12_47=='e') ) {
                        int LA12_66 = input.LA(5);

                        if ( (LA12_66=='r') ) {
                            int LA12_84 = input.LA(6);

                            if ( (LA12_84=='c') ) {
                                int LA12_102 = input.LA(7);

                                if ( (LA12_102=='e') ) {
                                    int LA12_116 = input.LA(8);

                                    if ( (LA12_116=='p') ) {
                                        int LA12_129 = input.LA(9);

                                        if ( (LA12_129=='t') ) {
                                            int LA12_138 = input.LA(10);

                                            if ( (LA12_138=='o') ) {
                                                int LA12_146 = input.LA(11);

                                                if ( (LA12_146=='r') ) {
                                                    switch ( input.LA(12) ) {
                                                    case 's':
                                                        {
                                                        int LA12_155 = input.LA(13);

                                                        if ( ((LA12_155>='0' && LA12_155<='9')||(LA12_155>='A' && LA12_155<='Z')||LA12_155=='_'||(LA12_155>='a' && LA12_155<='z')) ) {
                                                            alt12=27;
                                                        }
                                                        else {
                                                            alt12=6;}
                                                        }
                                                        break;
                                                    case '0':
                                                    case '1':
                                                    case '2':
                                                    case '3':
                                                    case '4':
                                                    case '5':
                                                    case '6':
                                                    case '7':
                                                    case '8':
                                                    case '9':
                                                    case 'A':
                                                    case 'B':
                                                    case 'C':
                                                    case 'D':
                                                    case 'E':
                                                    case 'F':
                                                    case 'G':
                                                    case 'H':
                                                    case 'I':
                                                    case 'J':
                                                    case 'K':
                                                    case 'L':
                                                    case 'M':
                                                    case 'N':
                                                    case 'O':
                                                    case 'P':
                                                    case 'Q':
                                                    case 'R':
                                                    case 'S':
                                                    case 'T':
                                                    case 'U':
                                                    case 'V':
                                                    case 'W':
                                                    case 'X':
                                                    case 'Y':
                                                    case 'Z':
                                                    case '_':
                                                    case 'a':
                                                    case 'b':
                                                    case 'c':
                                                    case 'd':
                                                    case 'e':
                                                    case 'f':
                                                    case 'g':
                                                    case 'h':
                                                    case 'i':
                                                    case 'j':
                                                    case 'k':
                                                    case 'l':
                                                    case 'm':
                                                    case 'n':
                                                    case 'o':
                                                    case 'p':
                                                    case 'q':
                                                    case 'r':
                                                    case 't':
                                                    case 'u':
                                                    case 'v':
                                                    case 'w':
                                                    case 'x':
                                                    case 'y':
                                                    case 'z':
                                                        {
                                                        alt12=27;
                                                        }
                                                        break;
                                                    default:
                                                        alt12=7;}

                                                }
                                                else {
                                                    alt12=27;}
                                            }
                                            else {
                                                alt12=27;}
                                        }
                                        else {
                                            alt12=27;}
                                    }
                                    else {
                                        alt12=27;}
                                }
                                else {
                                    alt12=27;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
                }
                break;
            case 'm':
                {
                int LA12_27 = input.LA(3);

                if ( (LA12_27=='p') ) {
                    int LA12_48 = input.LA(4);

                    if ( (LA12_48=='o') ) {
                        int LA12_67 = input.LA(5);

                        if ( (LA12_67=='r') ) {
                            int LA12_85 = input.LA(6);

                            if ( (LA12_85=='t') ) {
                                int LA12_103 = input.LA(7);

                                if ( ((LA12_103>='0' && LA12_103<='9')||(LA12_103>='A' && LA12_103<='Z')||LA12_103=='_'||(LA12_103>='a' && LA12_103<='z')) ) {
                                    alt12=27;
                                }
                                else {
                                    alt12=4;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
                }
                break;
            default:
                alt12=27;}

            }
            break;
        case 'g':
            {
            int LA12_5 = input.LA(2);

            if ( (LA12_5=='l') ) {
                int LA12_28 = input.LA(3);

                if ( (LA12_28=='o') ) {
                    int LA12_49 = input.LA(4);

                    if ( (LA12_49=='b') ) {
                        int LA12_68 = input.LA(5);

                        if ( (LA12_68=='a') ) {
                            int LA12_86 = input.LA(6);

                            if ( (LA12_86=='l') ) {
                                int LA12_104 = input.LA(7);

                                if ( ((LA12_104>='0' && LA12_104<='9')||(LA12_104>='A' && LA12_104<='Z')||LA12_104=='_'||(LA12_104>='a' && LA12_104<='z')) ) {
                                    alt12=27;
                                }
                                else {
                                    alt12=5;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
            }
            else {
                alt12=27;}
            }
            break;
        case '(':
            {
            alt12=8;
            }
            break;
        case ')':
            {
            alt12=9;
            }
            break;
        case 'f':
            {
            switch ( input.LA(2) ) {
            case 'i':
                {
                int LA12_29 = input.LA(3);

                if ( (LA12_29=='l') ) {
                    int LA12_50 = input.LA(4);

                    if ( (LA12_50=='t') ) {
                        int LA12_69 = input.LA(5);

                        if ( (LA12_69=='e') ) {
                            int LA12_87 = input.LA(6);

                            if ( (LA12_87=='r') ) {
                                int LA12_105 = input.LA(7);

                                if ( ((LA12_105>='0' && LA12_105<='9')||(LA12_105>='A' && LA12_105<='Z')||LA12_105=='_'||(LA12_105>='a' && LA12_105<='z')) ) {
                                    alt12=27;
                                }
                                else {
                                    alt12=17;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
                }
                break;
            case 'r':
                {
                int LA12_30 = input.LA(3);

                if ( (LA12_30=='o') ) {
                    int LA12_51 = input.LA(4);

                    if ( (LA12_51=='m') ) {
                        int LA12_70 = input.LA(5);

                        if ( ((LA12_70>='0' && LA12_70<='9')||(LA12_70>='A' && LA12_70<='Z')||LA12_70=='_'||(LA12_70>='a' && LA12_70<='z')) ) {
                            alt12=27;
                        }
                        else {
                            alt12=10;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
                }
                break;
            case 'a':
                {
                int LA12_31 = input.LA(3);

                if ( (LA12_31=='u') ) {
                    int LA12_52 = input.LA(4);

                    if ( (LA12_52=='l') ) {
                        int LA12_71 = input.LA(5);

                        if ( (LA12_71=='t') ) {
                            int LA12_89 = input.LA(6);

                            if ( ((LA12_89>='0' && LA12_89<='9')||(LA12_89>='A' && LA12_89<='Z')||LA12_89=='_'||(LA12_89>='a' && LA12_89<='z')) ) {
                                alt12=27;
                            }
                            else {
                                alt12=24;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
                }
                break;
            default:
                alt12=27;}

            }
            break;
        case 't':
            {
            switch ( input.LA(2) ) {
            case 'r':
                {
                int LA12_32 = input.LA(3);

                if ( (LA12_32=='a') ) {
                    int LA12_53 = input.LA(4);

                    if ( (LA12_53=='n') ) {
                        int LA12_72 = input.LA(5);

                        if ( (LA12_72=='s') ) {
                            int LA12_90 = input.LA(6);

                            if ( (LA12_90=='f') ) {
                                int LA12_107 = input.LA(7);

                                if ( (LA12_107=='o') ) {
                                    int LA12_120 = input.LA(8);

                                    if ( (LA12_120=='r') ) {
                                        int LA12_130 = input.LA(9);

                                        if ( (LA12_130=='m') ) {
                                            int LA12_139 = input.LA(10);

                                            if ( (LA12_139=='e') ) {
                                                int LA12_147 = input.LA(11);

                                                if ( (LA12_147=='r') ) {
                                                    int LA12_153 = input.LA(12);

                                                    if ( ((LA12_153>='0' && LA12_153<='9')||(LA12_153>='A' && LA12_153<='Z')||LA12_153=='_'||(LA12_153>='a' && LA12_153<='z')) ) {
                                                        alt12=27;
                                                    }
                                                    else {
                                                        alt12=12;}
                                                }
                                                else {
                                                    alt12=27;}
                                            }
                                            else {
                                                alt12=27;}
                                        }
                                        else {
                                            alt12=27;}
                                    }
                                    else {
                                        alt12=27;}
                                }
                                else {
                                    alt12=27;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
                }
                break;
            case 'o':
                {
                int LA12_33 = input.LA(3);

                if ( ((LA12_33>='0' && LA12_33<='9')||(LA12_33>='A' && LA12_33<='Z')||LA12_33=='_'||(LA12_33>='a' && LA12_33<='z')) ) {
                    alt12=27;
                }
                else {
                    alt12=11;}
                }
                break;
            default:
                alt12=27;}

            }
            break;
        case 'c':
            {
            int LA12_10 = input.LA(2);

            if ( (LA12_10=='h') ) {
                int LA12_34 = input.LA(3);

                if ( (LA12_34=='o') ) {
                    int LA12_55 = input.LA(4);

                    if ( (LA12_55=='i') ) {
                        int LA12_73 = input.LA(5);

                        if ( (LA12_73=='c') ) {
                            int LA12_91 = input.LA(6);

                            if ( (LA12_91=='e') ) {
                                int LA12_108 = input.LA(7);

                                if ( ((LA12_108>='0' && LA12_108<='9')||(LA12_108>='A' && LA12_108<='Z')||LA12_108=='_'||(LA12_108>='a' && LA12_108<='z')) ) {
                                    alt12=27;
                                }
                                else {
                                    alt12=13;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
            }
            else {
                alt12=27;}
            }
            break;
        case 'd':
            {
            int LA12_11 = input.LA(2);

            if ( (LA12_11=='y') ) {
                int LA12_35 = input.LA(3);

                if ( (LA12_35=='n') ) {
                    int LA12_56 = input.LA(4);

                    if ( (LA12_56=='a') ) {
                        int LA12_74 = input.LA(5);

                        if ( (LA12_74=='m') ) {
                            int LA12_92 = input.LA(6);

                            if ( (LA12_92=='i') ) {
                                int LA12_109 = input.LA(7);

                                if ( (LA12_109=='c') ) {
                                    int LA12_122 = input.LA(8);

                                    if ( ((LA12_122>='0' && LA12_122<='9')||(LA12_122>='A' && LA12_122<='Z')||LA12_122=='_'||(LA12_122>='a' && LA12_122<='z')) ) {
                                        alt12=27;
                                    }
                                    else {
                                        alt12=15;}
                                }
                                else {
                                    alt12=27;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
            }
            else {
                alt12=27;}
            }
            break;
        case 'p':
            {
            switch ( input.LA(2) ) {
            case 'r':
                {
                int LA12_36 = input.LA(3);

                if ( (LA12_36=='o') ) {
                    int LA12_57 = input.LA(4);

                    if ( (LA12_57=='p') ) {
                        int LA12_75 = input.LA(5);

                        if ( (LA12_75=='e') ) {
                            int LA12_93 = input.LA(6);

                            if ( (LA12_93=='r') ) {
                                int LA12_110 = input.LA(7);

                                if ( (LA12_110=='t') ) {
                                    int LA12_123 = input.LA(8);

                                    if ( (LA12_123=='i') ) {
                                        int LA12_132 = input.LA(9);

                                        if ( (LA12_132=='e') ) {
                                            int LA12_140 = input.LA(10);

                                            if ( (LA12_140=='s') ) {
                                                int LA12_148 = input.LA(11);

                                                if ( ((LA12_148>='0' && LA12_148<='9')||(LA12_148>='A' && LA12_148<='Z')||LA12_148=='_'||(LA12_148>='a' && LA12_148<='z')) ) {
                                                    alt12=27;
                                                }
                                                else {
                                                    alt12=22;}
                                            }
                                            else {
                                                alt12=27;}
                                        }
                                        else {
                                            alt12=27;}
                                    }
                                    else {
                                        alt12=27;}
                                }
                                else {
                                    alt12=27;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
                }
                break;
            case 'i':
                {
                int LA12_37 = input.LA(3);

                if ( (LA12_37=='p') ) {
                    int LA12_58 = input.LA(4);

                    if ( (LA12_58=='e') ) {
                        int LA12_76 = input.LA(5);

                        if ( (LA12_76=='l') ) {
                            int LA12_94 = input.LA(6);

                            if ( (LA12_94=='i') ) {
                                int LA12_111 = input.LA(7);

                                if ( (LA12_111=='n') ) {
                                    int LA12_124 = input.LA(8);

                                    if ( (LA12_124=='e') ) {
                                        int LA12_133 = input.LA(9);

                                        if ( ((LA12_133>='0' && LA12_133<='9')||(LA12_133>='A' && LA12_133<='Z')||LA12_133=='_'||(LA12_133>='a' && LA12_133<='z')) ) {
                                            alt12=27;
                                        }
                                        else {
                                            alt12=16;}
                                    }
                                    else {
                                        alt12=27;}
                                }
                                else {
                                    alt12=27;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
                }
                break;
            default:
                alt12=27;}

            }
            break;
        case 's':
            {
            int LA12_13 = input.LA(2);

            if ( (LA12_13=='p') ) {
                int LA12_38 = input.LA(3);

                if ( (LA12_38=='l') ) {
                    int LA12_59 = input.LA(4);

                    if ( (LA12_59=='i') ) {
                        int LA12_77 = input.LA(5);

                        if ( (LA12_77=='t') ) {
                            int LA12_95 = input.LA(6);

                            if ( (LA12_95=='t') ) {
                                int LA12_112 = input.LA(7);

                                if ( (LA12_112=='e') ) {
                                    int LA12_125 = input.LA(8);

                                    if ( (LA12_125=='r') ) {
                                        int LA12_134 = input.LA(9);

                                        if ( ((LA12_134>='0' && LA12_134<='9')||(LA12_134>='A' && LA12_134<='Z')||LA12_134=='_'||(LA12_134>='a' && LA12_134<='z')) ) {
                                            alt12=27;
                                        }
                                        else {
                                            alt12=18;}
                                    }
                                    else {
                                        alt12=27;}
                                }
                                else {
                                    alt12=27;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
            }
            else {
                alt12=27;}
            }
            break;
        case 'w':
            {
            int LA12_14 = input.LA(2);

            if ( (LA12_14=='h') ) {
                int LA12_39 = input.LA(3);

                if ( (LA12_39=='e') ) {
                    int LA12_60 = input.LA(4);

                    if ( (LA12_60=='n') ) {
                        int LA12_78 = input.LA(5);

                        if ( ((LA12_78>='0' && LA12_78<='9')||(LA12_78>='A' && LA12_78<='Z')||LA12_78=='_'||(LA12_78>='a' && LA12_78<='z')) ) {
                            alt12=27;
                        }
                        else {
                            alt12=20;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
            }
            else {
                alt12=27;}
            }
            break;
        case 'o':
            {
            int LA12_15 = input.LA(2);

            if ( (LA12_15=='t') ) {
                int LA12_40 = input.LA(3);

                if ( (LA12_40=='h') ) {
                    int LA12_61 = input.LA(4);

                    if ( (LA12_61=='e') ) {
                        int LA12_79 = input.LA(5);

                        if ( (LA12_79=='r') ) {
                            int LA12_97 = input.LA(6);

                            if ( (LA12_97=='w') ) {
                                int LA12_113 = input.LA(7);

                                if ( (LA12_113=='i') ) {
                                    int LA12_126 = input.LA(8);

                                    if ( (LA12_126=='s') ) {
                                        int LA12_135 = input.LA(9);

                                        if ( (LA12_135=='e') ) {
                                            int LA12_143 = input.LA(10);

                                            if ( ((LA12_143>='0' && LA12_143<='9')||(LA12_143>='A' && LA12_143<='Z')||LA12_143=='_'||(LA12_143>='a' && LA12_143<='z')) ) {
                                                alt12=27;
                                            }
                                            else {
                                                alt12=21;}
                                        }
                                        else {
                                            alt12=27;}
                                    }
                                    else {
                                        alt12=27;}
                                }
                                else {
                                    alt12=27;}
                            }
                            else {
                                alt12=27;}
                        }
                        else {
                            alt12=27;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
            }
            else {
                alt12=27;}
            }
            break;
        case '=':
            {
            alt12=23;
            }
            break;
        case '.':
            {
            alt12=25;
            }
            break;
        case 'b':
            {
            int LA12_18 = input.LA(2);

            if ( (LA12_18=='e') ) {
                int LA12_41 = input.LA(3);

                if ( (LA12_41=='a') ) {
                    int LA12_62 = input.LA(4);

                    if ( (LA12_62=='n') ) {
                        int LA12_80 = input.LA(5);

                        if ( ((LA12_80>='0' && LA12_80<='9')||(LA12_80>='A' && LA12_80<='Z')||LA12_80=='_'||(LA12_80>='a' && LA12_80<='z')) ) {
                            alt12=27;
                        }
                        else {
                            alt12=26;}
                    }
                    else {
                        alt12=27;}
                }
                else {
                    alt12=27;}
            }
            else {
                alt12=27;}
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case '^':
        case '_':
        case 'a':
        case 'e':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'q':
        case 'u':
        case 'v':
        case 'x':
        case 'y':
        case 'z':
            {
            alt12=27;
            }
            break;
        case '\"':
        case '\'':
            {
            alt12=28;
            }
            break;
        case '-':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt12=29;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt12=30;
            }
            break;
        case '/':
            {
            int LA12_23 = input.LA(2);

            if ( (LA12_23=='*') ) {
                alt12=31;
            }
            else if ( (LA12_23=='/') ) {
                alt12=32;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT );", 12, 23, input);

                throw nvae;
            }
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT );", 12, 0, input);

            throw nvae;
        }

        switch (alt12) {
            case 1 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:10: T10
                {
                mT10(); 

                }
                break;
            case 2 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:14: T11
                {
                mT11(); 

                }
                break;
            case 3 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:18: T12
                {
                mT12(); 

                }
                break;
            case 4 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:22: T13
                {
                mT13(); 

                }
                break;
            case 5 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:26: T14
                {
                mT14(); 

                }
                break;
            case 6 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:30: T15
                {
                mT15(); 

                }
                break;
            case 7 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:34: T16
                {
                mT16(); 

                }
                break;
            case 8 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:38: T17
                {
                mT17(); 

                }
                break;
            case 9 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:42: T18
                {
                mT18(); 

                }
                break;
            case 10 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:46: T19
                {
                mT19(); 

                }
                break;
            case 11 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:50: T20
                {
                mT20(); 

                }
                break;
            case 12 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:54: T21
                {
                mT21(); 

                }
                break;
            case 13 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:58: T22
                {
                mT22(); 

                }
                break;
            case 14 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:62: T23
                {
                mT23(); 

                }
                break;
            case 15 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:66: T24
                {
                mT24(); 

                }
                break;
            case 16 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:70: T25
                {
                mT25(); 

                }
                break;
            case 17 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:74: T26
                {
                mT26(); 

                }
                break;
            case 18 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:78: T27
                {
                mT27(); 

                }
                break;
            case 19 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:82: T28
                {
                mT28(); 

                }
                break;
            case 20 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:86: T29
                {
                mT29(); 

                }
                break;
            case 21 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:90: T30
                {
                mT30(); 

                }
                break;
            case 22 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:94: T31
                {
                mT31(); 

                }
                break;
            case 23 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:98: T32
                {
                mT32(); 

                }
                break;
            case 24 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:102: T33
                {
                mT33(); 

                }
                break;
            case 25 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:106: T34
                {
                mT34(); 

                }
                break;
            case 26 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:110: T35
                {
                mT35(); 

                }
                break;
            case 27 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:114: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 28 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:122: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 29 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:134: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 30 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:143: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 31 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:151: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 32 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:167: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;

        }

    }


 

}