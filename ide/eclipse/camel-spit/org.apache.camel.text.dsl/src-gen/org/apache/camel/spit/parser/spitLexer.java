// $ANTLR 3.0 ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g 2008-11-11 16:35:01

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
    public static final int T36=36;
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
    public static final int T37=37;
    public static final int T18=18;
    public static final int RULE_WS=7;
    public static final int T26=26;
    public static final int T15=15;
    public static final int RULE_INT=6;
    public static final int EOF=-1;
    public static final int T32=32;
    public static final int T17=17;
    public static final int Tokens=40;
    public static final int T31=31;
    public static final int T16=16;
    public static final int T38=38;
    public static final int T27=27;
    public static final int RULE_SL_COMMENT=9;
    public static final int T30=30;
    public static final int T24=24;
    public static final int T19=19;
    public static final int T39=39;

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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:33:7: ( 'convert' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:33:7: 'convert'
            {
            match("convert"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:34:7: ( 'header' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:34:7: 'header'
            {
            match("header"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:35:7: ( '=' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:35:7: '='
            {
            match('='); 

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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:36:7: ( 'body' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:36:7: 'body'
            {
            match("body"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:37:7: ( 'with' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:37:7: 'with'
            {
            match("with"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:38:7: ( 'transformer' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:38:7: 'transformer'
            {
            match("transformer"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:39:7: ( 'choice' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:39:7: 'choice'
            {
            match("choice"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:40:7: ( 'recipients' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:40:7: 'recipients'
            {
            match("recipients"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:41:7: ( 'dynamic' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:41:7: 'dynamic'
            {
            match("dynamic"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:42:7: ( 'pipeline' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:42:7: 'pipeline'
            {
            match("pipeline"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:43:7: ( 'filter' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:43:7: 'filter'
            {
            match("filter"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:44:7: ( 'splitter' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:44:7: 'splitter'
            {
            match("splitter"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:45:7: ( 'resequence' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:45:7: 'resequence'
            {
            match("resequence"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:46:7: ( 'when' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:46:7: 'when'
            {
            match("when"); 


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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:47:7: ( 'otherwise' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:47:7: 'otherwise'
            {
            match("otherwise"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start T36
    public void mT36() throws RecognitionException {
        try {
            int _type = T36;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:48:7: ( 'properties' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:48:7: 'properties'
            {
            match("properties"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T36

    // $ANTLR start T37
    public void mT37() throws RecognitionException {
        try {
            int _type = T37;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:49:7: ( 'fault' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:49:7: 'fault'
            {
            match("fault"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T37

    // $ANTLR start T38
    public void mT38() throws RecognitionException {
        try {
            int _type = T38;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:50:7: ( '.' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:50:7: '.'
            {
            match('.'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T38

    // $ANTLR start T39
    public void mT39() throws RecognitionException {
        try {
            int _type = T39;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:51:7: ( 'bean' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:51:7: 'bean'
            {
            match("bean"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T39

    // $ANTLR start RULE_ID
    public void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:651:3: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:651:3: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:651:3: ( '^' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:651:4: '^'
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

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:651:33: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:657:3: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\'' )
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
                    new NoViableAltException("655:1: RULE_STRING : ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\'' );", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:657:3: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )* '\"'
                    {
                    match('\"'); 
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:657:7: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )*
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
                    	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:657:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:657:53: ~ ( '\\\\' | '\"' )
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
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:658:3: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\''
                    {
                    match('\''); 
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:658:8: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )*
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
                    	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:658:10: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:658:54: ~ ( '\\\\' | '\\'' )
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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:664:3: ( ( '-' )? ( '0' .. '9' )+ )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:664:3: ( '-' )? ( '0' .. '9' )+
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:664:3: ( '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:664:4: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:664:9: ( '0' .. '9' )+
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
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:664:10: '0' .. '9'
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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:670:3: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:670:3: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:670:3: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:676:3: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:676:3: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:676:8: ( options {greedy=false; } : . )*
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
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:676:36: .
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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:682:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:682:3: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:682:8: (~ ( '\\n' | '\\r' ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFE')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:682:8: ~ ( '\\n' | '\\r' )
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

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:682:22: ( '\\r' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:682:22: '\\r'
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
        // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:10: ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT )
        int alt12=36;
        switch ( input.LA(1) ) {
        case 'r':
            {
            switch ( input.LA(2) ) {
            case 'o':
                {
                int LA12_25 = input.LA(3);

                if ( (LA12_25=='u') ) {
                    int LA12_49 = input.LA(4);

                    if ( (LA12_49=='t') ) {
                        int LA12_72 = input.LA(5);

                        if ( (LA12_72=='e') ) {
                            int LA12_94 = input.LA(6);

                            if ( ((LA12_94>='0' && LA12_94<='9')||(LA12_94>='A' && LA12_94<='Z')||LA12_94=='_'||(LA12_94>='a' && LA12_94<='z')) ) {
                                alt12=31;
                            }
                            else {
                                alt12=1;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            case 'e':
                {
                switch ( input.LA(3) ) {
                case 's':
                    {
                    int LA12_50 = input.LA(4);

                    if ( (LA12_50=='e') ) {
                        int LA12_73 = input.LA(5);

                        if ( (LA12_73=='q') ) {
                            int LA12_95 = input.LA(6);

                            if ( (LA12_95=='u') ) {
                                int LA12_117 = input.LA(7);

                                if ( (LA12_117=='e') ) {
                                    int LA12_133 = input.LA(8);

                                    if ( (LA12_133=='n') ) {
                                        int LA12_148 = input.LA(9);

                                        if ( (LA12_148=='c') ) {
                                            int LA12_158 = input.LA(10);

                                            if ( (LA12_158=='e') ) {
                                                int LA12_166 = input.LA(11);

                                                if ( ((LA12_166>='0' && LA12_166<='9')||(LA12_166>='A' && LA12_166<='Z')||LA12_166=='_'||(LA12_166>='a' && LA12_166<='z')) ) {
                                                    alt12=31;
                                                }
                                                else {
                                                    alt12=24;}
                                            }
                                            else {
                                                alt12=31;}
                                        }
                                        else {
                                            alt12=31;}
                                    }
                                    else {
                                        alt12=31;}
                                }
                                else {
                                    alt12=31;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                    }
                    break;
                case 'c':
                    {
                    int LA12_51 = input.LA(4);

                    if ( (LA12_51=='i') ) {
                        int LA12_74 = input.LA(5);

                        if ( (LA12_74=='p') ) {
                            int LA12_96 = input.LA(6);

                            if ( (LA12_96=='i') ) {
                                int LA12_118 = input.LA(7);

                                if ( (LA12_118=='e') ) {
                                    int LA12_134 = input.LA(8);

                                    if ( (LA12_134=='n') ) {
                                        int LA12_149 = input.LA(9);

                                        if ( (LA12_149=='t') ) {
                                            int LA12_159 = input.LA(10);

                                            if ( (LA12_159=='s') ) {
                                                int LA12_167 = input.LA(11);

                                                if ( ((LA12_167>='0' && LA12_167<='9')||(LA12_167>='A' && LA12_167<='Z')||LA12_167=='_'||(LA12_167>='a' && LA12_167<='z')) ) {
                                                    alt12=31;
                                                }
                                                else {
                                                    alt12=19;}
                                            }
                                            else {
                                                alt12=31;}
                                        }
                                        else {
                                            alt12=31;}
                                    }
                                    else {
                                        alt12=31;}
                                }
                                else {
                                    alt12=31;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                    }
                    break;
                default:
                    alt12=31;}

                }
                break;
            default:
                alt12=31;}

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
                int LA12_27 = input.LA(3);

                if ( (LA12_27=='t') ) {
                    int LA12_52 = input.LA(4);

                    if ( (LA12_52=='e') ) {
                        int LA12_75 = input.LA(5);

                        if ( (LA12_75=='r') ) {
                            int LA12_97 = input.LA(6);

                            if ( (LA12_97=='c') ) {
                                int LA12_119 = input.LA(7);

                                if ( (LA12_119=='e') ) {
                                    int LA12_135 = input.LA(8);

                                    if ( (LA12_135=='p') ) {
                                        int LA12_150 = input.LA(9);

                                        if ( (LA12_150=='t') ) {
                                            int LA12_160 = input.LA(10);

                                            if ( (LA12_160=='o') ) {
                                                int LA12_168 = input.LA(11);

                                                if ( (LA12_168=='r') ) {
                                                    switch ( input.LA(12) ) {
                                                    case 's':
                                                        {
                                                        int LA12_177 = input.LA(13);

                                                        if ( ((LA12_177>='0' && LA12_177<='9')||(LA12_177>='A' && LA12_177<='Z')||LA12_177=='_'||(LA12_177>='a' && LA12_177<='z')) ) {
                                                            alt12=31;
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
                                                        alt12=31;
                                                        }
                                                        break;
                                                    default:
                                                        alt12=7;}

                                                }
                                                else {
                                                    alt12=31;}
                                            }
                                            else {
                                                alt12=31;}
                                        }
                                        else {
                                            alt12=31;}
                                    }
                                    else {
                                        alt12=31;}
                                }
                                else {
                                    alt12=31;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            case 'm':
                {
                int LA12_28 = input.LA(3);

                if ( (LA12_28=='p') ) {
                    int LA12_53 = input.LA(4);

                    if ( (LA12_53=='o') ) {
                        int LA12_76 = input.LA(5);

                        if ( (LA12_76=='r') ) {
                            int LA12_98 = input.LA(6);

                            if ( (LA12_98=='t') ) {
                                int LA12_120 = input.LA(7);

                                if ( ((LA12_120>='0' && LA12_120<='9')||(LA12_120>='A' && LA12_120<='Z')||LA12_120=='_'||(LA12_120>='a' && LA12_120<='z')) ) {
                                    alt12=31;
                                }
                                else {
                                    alt12=4;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            default:
                alt12=31;}

            }
            break;
        case 'g':
            {
            int LA12_5 = input.LA(2);

            if ( (LA12_5=='l') ) {
                int LA12_29 = input.LA(3);

                if ( (LA12_29=='o') ) {
                    int LA12_54 = input.LA(4);

                    if ( (LA12_54=='b') ) {
                        int LA12_77 = input.LA(5);

                        if ( (LA12_77=='a') ) {
                            int LA12_99 = input.LA(6);

                            if ( (LA12_99=='l') ) {
                                int LA12_121 = input.LA(7);

                                if ( ((LA12_121>='0' && LA12_121<='9')||(LA12_121>='A' && LA12_121<='Z')||LA12_121=='_'||(LA12_121>='a' && LA12_121<='z')) ) {
                                    alt12=31;
                                }
                                else {
                                    alt12=5;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
            }
            else {
                alt12=31;}
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
            case 'r':
                {
                int LA12_30 = input.LA(3);

                if ( (LA12_30=='o') ) {
                    int LA12_55 = input.LA(4);

                    if ( (LA12_55=='m') ) {
                        int LA12_78 = input.LA(5);

                        if ( ((LA12_78>='0' && LA12_78<='9')||(LA12_78>='A' && LA12_78<='Z')||LA12_78=='_'||(LA12_78>='a' && LA12_78<='z')) ) {
                            alt12=31;
                        }
                        else {
                            alt12=10;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            case 'i':
                {
                int LA12_31 = input.LA(3);

                if ( (LA12_31=='l') ) {
                    int LA12_56 = input.LA(4);

                    if ( (LA12_56=='t') ) {
                        int LA12_79 = input.LA(5);

                        if ( (LA12_79=='e') ) {
                            int LA12_101 = input.LA(6);

                            if ( (LA12_101=='r') ) {
                                int LA12_122 = input.LA(7);

                                if ( ((LA12_122>='0' && LA12_122<='9')||(LA12_122>='A' && LA12_122<='Z')||LA12_122=='_'||(LA12_122>='a' && LA12_122<='z')) ) {
                                    alt12=31;
                                }
                                else {
                                    alt12=22;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            case 'a':
                {
                int LA12_32 = input.LA(3);

                if ( (LA12_32=='u') ) {
                    int LA12_57 = input.LA(4);

                    if ( (LA12_57=='l') ) {
                        int LA12_80 = input.LA(5);

                        if ( (LA12_80=='t') ) {
                            int LA12_102 = input.LA(6);

                            if ( ((LA12_102>='0' && LA12_102<='9')||(LA12_102>='A' && LA12_102<='Z')||LA12_102=='_'||(LA12_102>='a' && LA12_102<='z')) ) {
                                alt12=31;
                            }
                            else {
                                alt12=28;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            default:
                alt12=31;}

            }
            break;
        case 't':
            {
            switch ( input.LA(2) ) {
            case 'r':
                {
                int LA12_33 = input.LA(3);

                if ( (LA12_33=='a') ) {
                    int LA12_58 = input.LA(4);

                    if ( (LA12_58=='n') ) {
                        int LA12_81 = input.LA(5);

                        if ( (LA12_81=='s') ) {
                            int LA12_103 = input.LA(6);

                            if ( (LA12_103=='f') ) {
                                int LA12_124 = input.LA(7);

                                if ( (LA12_124=='o') ) {
                                    int LA12_139 = input.LA(8);

                                    if ( (LA12_139=='r') ) {
                                        int LA12_151 = input.LA(9);

                                        if ( (LA12_151=='m') ) {
                                            int LA12_161 = input.LA(10);

                                            if ( (LA12_161=='e') ) {
                                                int LA12_169 = input.LA(11);

                                                if ( (LA12_169=='r') ) {
                                                    int LA12_175 = input.LA(12);

                                                    if ( ((LA12_175>='0' && LA12_175<='9')||(LA12_175>='A' && LA12_175<='Z')||LA12_175=='_'||(LA12_175>='a' && LA12_175<='z')) ) {
                                                        alt12=31;
                                                    }
                                                    else {
                                                        alt12=17;}
                                                }
                                                else {
                                                    alt12=31;}
                                            }
                                            else {
                                                alt12=31;}
                                        }
                                        else {
                                            alt12=31;}
                                    }
                                    else {
                                        alt12=31;}
                                }
                                else {
                                    alt12=31;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            case 'o':
                {
                int LA12_34 = input.LA(3);

                if ( ((LA12_34>='0' && LA12_34<='9')||(LA12_34>='A' && LA12_34<='Z')||LA12_34=='_'||(LA12_34>='a' && LA12_34<='z')) ) {
                    alt12=31;
                }
                else {
                    alt12=11;}
                }
                break;
            default:
                alt12=31;}

            }
            break;
        case 'c':
            {
            switch ( input.LA(2) ) {
            case 'o':
                {
                int LA12_35 = input.LA(3);

                if ( (LA12_35=='n') ) {
                    int LA12_60 = input.LA(4);

                    if ( (LA12_60=='v') ) {
                        int LA12_82 = input.LA(5);

                        if ( (LA12_82=='e') ) {
                            int LA12_104 = input.LA(6);

                            if ( (LA12_104=='r') ) {
                                int LA12_125 = input.LA(7);

                                if ( (LA12_125=='t') ) {
                                    int LA12_140 = input.LA(8);

                                    if ( ((LA12_140>='0' && LA12_140<='9')||(LA12_140>='A' && LA12_140<='Z')||LA12_140=='_'||(LA12_140>='a' && LA12_140<='z')) ) {
                                        alt12=31;
                                    }
                                    else {
                                        alt12=12;}
                                }
                                else {
                                    alt12=31;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            case 'h':
                {
                int LA12_36 = input.LA(3);

                if ( (LA12_36=='o') ) {
                    int LA12_61 = input.LA(4);

                    if ( (LA12_61=='i') ) {
                        int LA12_83 = input.LA(5);

                        if ( (LA12_83=='c') ) {
                            int LA12_105 = input.LA(6);

                            if ( (LA12_105=='e') ) {
                                int LA12_126 = input.LA(7);

                                if ( ((LA12_126>='0' && LA12_126<='9')||(LA12_126>='A' && LA12_126<='Z')||LA12_126=='_'||(LA12_126>='a' && LA12_126<='z')) ) {
                                    alt12=31;
                                }
                                else {
                                    alt12=18;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            default:
                alt12=31;}

            }
            break;
        case 'h':
            {
            int LA12_11 = input.LA(2);

            if ( (LA12_11=='e') ) {
                int LA12_37 = input.LA(3);

                if ( (LA12_37=='a') ) {
                    int LA12_62 = input.LA(4);

                    if ( (LA12_62=='d') ) {
                        int LA12_84 = input.LA(5);

                        if ( (LA12_84=='e') ) {
                            int LA12_106 = input.LA(6);

                            if ( (LA12_106=='r') ) {
                                int LA12_127 = input.LA(7);

                                if ( ((LA12_127>='0' && LA12_127<='9')||(LA12_127>='A' && LA12_127<='Z')||LA12_127=='_'||(LA12_127>='a' && LA12_127<='z')) ) {
                                    alt12=31;
                                }
                                else {
                                    alt12=13;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
            }
            else {
                alt12=31;}
            }
            break;
        case '=':
            {
            alt12=14;
            }
            break;
        case 'b':
            {
            switch ( input.LA(2) ) {
            case 'e':
                {
                int LA12_38 = input.LA(3);

                if ( (LA12_38=='a') ) {
                    int LA12_63 = input.LA(4);

                    if ( (LA12_63=='n') ) {
                        int LA12_85 = input.LA(5);

                        if ( ((LA12_85>='0' && LA12_85<='9')||(LA12_85>='A' && LA12_85<='Z')||LA12_85=='_'||(LA12_85>='a' && LA12_85<='z')) ) {
                            alt12=31;
                        }
                        else {
                            alt12=30;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            case 'o':
                {
                int LA12_39 = input.LA(3);

                if ( (LA12_39=='d') ) {
                    int LA12_64 = input.LA(4);

                    if ( (LA12_64=='y') ) {
                        int LA12_86 = input.LA(5);

                        if ( ((LA12_86>='0' && LA12_86<='9')||(LA12_86>='A' && LA12_86<='Z')||LA12_86=='_'||(LA12_86>='a' && LA12_86<='z')) ) {
                            alt12=31;
                        }
                        else {
                            alt12=15;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            default:
                alt12=31;}

            }
            break;
        case 'w':
            {
            switch ( input.LA(2) ) {
            case 'i':
                {
                int LA12_40 = input.LA(3);

                if ( (LA12_40=='t') ) {
                    int LA12_65 = input.LA(4);

                    if ( (LA12_65=='h') ) {
                        int LA12_87 = input.LA(5);

                        if ( ((LA12_87>='0' && LA12_87<='9')||(LA12_87>='A' && LA12_87<='Z')||LA12_87=='_'||(LA12_87>='a' && LA12_87<='z')) ) {
                            alt12=31;
                        }
                        else {
                            alt12=16;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            case 'h':
                {
                int LA12_41 = input.LA(3);

                if ( (LA12_41=='e') ) {
                    int LA12_66 = input.LA(4);

                    if ( (LA12_66=='n') ) {
                        int LA12_88 = input.LA(5);

                        if ( ((LA12_88>='0' && LA12_88<='9')||(LA12_88>='A' && LA12_88<='Z')||LA12_88=='_'||(LA12_88>='a' && LA12_88<='z')) ) {
                            alt12=31;
                        }
                        else {
                            alt12=25;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            default:
                alt12=31;}

            }
            break;
        case 'd':
            {
            int LA12_15 = input.LA(2);

            if ( (LA12_15=='y') ) {
                int LA12_42 = input.LA(3);

                if ( (LA12_42=='n') ) {
                    int LA12_67 = input.LA(4);

                    if ( (LA12_67=='a') ) {
                        int LA12_89 = input.LA(5);

                        if ( (LA12_89=='m') ) {
                            int LA12_111 = input.LA(6);

                            if ( (LA12_111=='i') ) {
                                int LA12_128 = input.LA(7);

                                if ( (LA12_128=='c') ) {
                                    int LA12_143 = input.LA(8);

                                    if ( ((LA12_143>='0' && LA12_143<='9')||(LA12_143>='A' && LA12_143<='Z')||LA12_143=='_'||(LA12_143>='a' && LA12_143<='z')) ) {
                                        alt12=31;
                                    }
                                    else {
                                        alt12=20;}
                                }
                                else {
                                    alt12=31;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
            }
            else {
                alt12=31;}
            }
            break;
        case 'p':
            {
            switch ( input.LA(2) ) {
            case 'r':
                {
                int LA12_43 = input.LA(3);

                if ( (LA12_43=='o') ) {
                    int LA12_68 = input.LA(4);

                    if ( (LA12_68=='p') ) {
                        int LA12_90 = input.LA(5);

                        if ( (LA12_90=='e') ) {
                            int LA12_112 = input.LA(6);

                            if ( (LA12_112=='r') ) {
                                int LA12_129 = input.LA(7);

                                if ( (LA12_129=='t') ) {
                                    int LA12_144 = input.LA(8);

                                    if ( (LA12_144=='i') ) {
                                        int LA12_154 = input.LA(9);

                                        if ( (LA12_154=='e') ) {
                                            int LA12_162 = input.LA(10);

                                            if ( (LA12_162=='s') ) {
                                                int LA12_170 = input.LA(11);

                                                if ( ((LA12_170>='0' && LA12_170<='9')||(LA12_170>='A' && LA12_170<='Z')||LA12_170=='_'||(LA12_170>='a' && LA12_170<='z')) ) {
                                                    alt12=31;
                                                }
                                                else {
                                                    alt12=27;}
                                            }
                                            else {
                                                alt12=31;}
                                        }
                                        else {
                                            alt12=31;}
                                    }
                                    else {
                                        alt12=31;}
                                }
                                else {
                                    alt12=31;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            case 'i':
                {
                int LA12_44 = input.LA(3);

                if ( (LA12_44=='p') ) {
                    int LA12_69 = input.LA(4);

                    if ( (LA12_69=='e') ) {
                        int LA12_91 = input.LA(5);

                        if ( (LA12_91=='l') ) {
                            int LA12_113 = input.LA(6);

                            if ( (LA12_113=='i') ) {
                                int LA12_130 = input.LA(7);

                                if ( (LA12_130=='n') ) {
                                    int LA12_145 = input.LA(8);

                                    if ( (LA12_145=='e') ) {
                                        int LA12_155 = input.LA(9);

                                        if ( ((LA12_155>='0' && LA12_155<='9')||(LA12_155>='A' && LA12_155<='Z')||LA12_155=='_'||(LA12_155>='a' && LA12_155<='z')) ) {
                                            alt12=31;
                                        }
                                        else {
                                            alt12=21;}
                                    }
                                    else {
                                        alt12=31;}
                                }
                                else {
                                    alt12=31;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
                }
                break;
            default:
                alt12=31;}

            }
            break;
        case 's':
            {
            int LA12_17 = input.LA(2);

            if ( (LA12_17=='p') ) {
                int LA12_45 = input.LA(3);

                if ( (LA12_45=='l') ) {
                    int LA12_70 = input.LA(4);

                    if ( (LA12_70=='i') ) {
                        int LA12_92 = input.LA(5);

                        if ( (LA12_92=='t') ) {
                            int LA12_114 = input.LA(6);

                            if ( (LA12_114=='t') ) {
                                int LA12_131 = input.LA(7);

                                if ( (LA12_131=='e') ) {
                                    int LA12_146 = input.LA(8);

                                    if ( (LA12_146=='r') ) {
                                        int LA12_156 = input.LA(9);

                                        if ( ((LA12_156>='0' && LA12_156<='9')||(LA12_156>='A' && LA12_156<='Z')||LA12_156=='_'||(LA12_156>='a' && LA12_156<='z')) ) {
                                            alt12=31;
                                        }
                                        else {
                                            alt12=23;}
                                    }
                                    else {
                                        alt12=31;}
                                }
                                else {
                                    alt12=31;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
            }
            else {
                alt12=31;}
            }
            break;
        case 'o':
            {
            int LA12_18 = input.LA(2);

            if ( (LA12_18=='t') ) {
                int LA12_46 = input.LA(3);

                if ( (LA12_46=='h') ) {
                    int LA12_71 = input.LA(4);

                    if ( (LA12_71=='e') ) {
                        int LA12_93 = input.LA(5);

                        if ( (LA12_93=='r') ) {
                            int LA12_115 = input.LA(6);

                            if ( (LA12_115=='w') ) {
                                int LA12_132 = input.LA(7);

                                if ( (LA12_132=='i') ) {
                                    int LA12_147 = input.LA(8);

                                    if ( (LA12_147=='s') ) {
                                        int LA12_157 = input.LA(9);

                                        if ( (LA12_157=='e') ) {
                                            int LA12_165 = input.LA(10);

                                            if ( ((LA12_165>='0' && LA12_165<='9')||(LA12_165>='A' && LA12_165<='Z')||LA12_165=='_'||(LA12_165>='a' && LA12_165<='z')) ) {
                                                alt12=31;
                                            }
                                            else {
                                                alt12=26;}
                                        }
                                        else {
                                            alt12=31;}
                                    }
                                    else {
                                        alt12=31;}
                                }
                                else {
                                    alt12=31;}
                            }
                            else {
                                alt12=31;}
                        }
                        else {
                            alt12=31;}
                    }
                    else {
                        alt12=31;}
                }
                else {
                    alt12=31;}
            }
            else {
                alt12=31;}
            }
            break;
        case '.':
            {
            alt12=29;
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
            alt12=31;
            }
            break;
        case '\"':
        case '\'':
            {
            alt12=32;
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
            alt12=33;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt12=34;
            }
            break;
        case '/':
            {
            int LA12_24 = input.LA(2);

            if ( (LA12_24=='*') ) {
                alt12=35;
            }
            else if ( (LA12_24=='/') ) {
                alt12=36;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT );", 12, 24, input);

                throw nvae;
            }
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT );", 12, 0, input);

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
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:114: T36
                {
                mT36(); 

                }
                break;
            case 28 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:118: T37
                {
                mT37(); 

                }
                break;
            case 29 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:122: T38
                {
                mT38(); 

                }
                break;
            case 30 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:126: T39
                {
                mT39(); 

                }
                break;
            case 31 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:130: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 32 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:138: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 33 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:150: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 34 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:159: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 35 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:167: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 36 :
                // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:1:183: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;

        }

    }


 

}