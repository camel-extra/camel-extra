// $ANTLR 3.0 ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g 2008-11-11 16:35:01

package org.apache.camel.spit.parser;

import org.eclipse.emf.ecore.EObject;

import org.openarchitectureware.xtext.parser.impl.AntlrUtil;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.impl.EcoreModelFactory;
import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.model.ParseTreeManager;
import org.openarchitectureware.xtext.parser.parsetree.Node;

import org.apache.camel.spit.MetaModelRegistration;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class spitParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "'route'", "'{'", "'}'", "'import'", "'global'", "'interceptors'", "'interceptor'", "'('", "')'", "'from'", "'to'", "'convert'", "'header'", "'='", "'body'", "'with'", "'transformer'", "'choice'", "'recipients'", "'dynamic'", "'pipeline'", "'filter'", "'splitter'", "'resequence'", "'when'", "'otherwise'", "'properties'", "'fault'", "'.'", "'bean'"
    };
    public static final int RULE_ML_COMMENT=8;
    public static final int RULE_ID=4;
    public static final int RULE_WS=7;
    public static final int EOF=-1;
    public static final int RULE_INT=6;
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=9;

        public spitParser(TokenStream input) {
            super(input);
            ruleMemo = new HashMap[72+1];
         }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g"; }



    	private Token getLastToken() {
    		return input.LT(-1);
    	}
    	private Token getNextToken() {
    		return input.LT(1);
    	}

    	private int line() {
    		Token t = getNextToken();
    		if (t==null)
    			return 1;
    		return t.getLine();
    	}

    	private int start() {
    		Token t = getNextToken();
    		if (t==null)
    			return 0;
    		if (t instanceof CommonToken) {
    			return ((CommonToken)t).getStartIndex();
    		}
    		return t.getTokenIndex();
    	}

    	private int end() {
    		Token t = getLastToken();
    		if (t==null)
    			return 1;
    		if (t instanceof CommonToken) {
    			return ((CommonToken)t).getStopIndex()+1;
    		}
    		return t.getTokenIndex();
    	}

    	protected Object convert(Object arg) {
    		if (arg instanceof org.antlr.runtime.Token) {
    			Token t = (Token) arg;
    			String s = t.getText();
    			if (t.getType() == spitLexer.RULE_ID && s.startsWith("^")) {
    				return s.substring(1);
    			} else if (t.getType()==spitLexer.RULE_STRING) {
    				return s.substring(1,s.length()-1);
    			} else if (t.getType()==spitLexer.RULE_INT) {
    				return Integer.valueOf(s);
    			}
    			return s;
    		}
    		return arg;
    	}


    	private EcoreModelFactory factory = new EcoreModelFactory(MetaModelRegistration.getEPackage());
        private ParseTreeManager ptm = new ParseTreeManager();
    	private XtextFile xtextfile = MetaModelRegistration.getXtextFile();
    	
    	{
    		
    	}

    	public ParseTreeManager getResult() {
    		return ptm;
    	}

    	private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
    	public List<ErrorMsg> getErrors() {
    		return errors;
    	}

    	@Override
    		public void reportError(RecognitionException e) {
    		String msg = super.getErrorMessage(e,tokenNames);
    		errors.add(AntlrUtil.create(msg,e,tokenNames));
    			ptm.addError(msg, e);
    			ptm.ruleFinished(null, end());
    		}




    // $ANTLR start parse
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:121:1: parse returns [Node r] : result= ruleFile EOF ;
    public Node parse() throws RecognitionException {
        Node r = null;
        int parse_StartIndex = input.index();
        EObject result = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 1) ) { return r; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:122:3: (result= ruleFile EOF )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:122:3: result= ruleFile EOF
            {
            pushFollow(FOLLOW_ruleFile_in_parse67);
            result=ruleFile();
            _fsp--;
            if (failed) return r;
            match(input,EOF,FOLLOW_EOF_in_parse69); if (failed) return r;
            if ( backtracking==0 ) {
              ptm.ruleFinished(result,end());r = ptm.getCurrent();
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 1, parse_StartIndex); }
        }
        return r;
    }
    // $ANTLR end parse


    // $ANTLR start ruleFile
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:125:1: ruleFile returns [EObject result] : ( (temp_imports= ruleImport )* (temp_interceptorDecls= ruleGlobalInterceptorDecl )* (temp_routes= ruleRoute )* (temp_interceptorDefs= ruleInterceptorChainDef )* ) ;
    public EObject ruleFile() throws RecognitionException {
        EObject result = null;
        int ruleFile_StartIndex = input.index();
        EObject temp_imports = null;

        EObject temp_interceptorDecls = null;

        EObject temp_routes = null;

        EObject temp_interceptorDefs = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 2) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:126:4: ( ( (temp_imports= ruleImport )* (temp_interceptorDecls= ruleGlobalInterceptorDecl )* (temp_routes= ruleRoute )* (temp_interceptorDefs= ruleInterceptorChainDef )* ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:126:4: ( (temp_imports= ruleImport )* (temp_interceptorDecls= ruleGlobalInterceptorDecl )* (temp_routes= ruleRoute )* (temp_interceptorDefs= ruleInterceptorChainDef )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "File");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:130:1: ( (temp_imports= ruleImport )* (temp_interceptorDecls= ruleGlobalInterceptorDecl )* (temp_routes= ruleRoute )* (temp_interceptorDefs= ruleInterceptorChainDef )* )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:130:2: (temp_imports= ruleImport )* (temp_interceptorDecls= ruleGlobalInterceptorDecl )* (temp_routes= ruleRoute )* (temp_interceptorDefs= ruleInterceptorChainDef )*
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:130:2: (temp_imports= ruleImport )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==13) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:130:3: temp_imports= ruleImport
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(0)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleImport_in_ruleFile93);
            	    temp_imports=ruleImport();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"imports",convert(temp_imports),false); ptm.ruleFinished(temp_imports,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:133:1: (temp_interceptorDecls= ruleGlobalInterceptorDecl )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==14) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:133:2: temp_interceptorDecls= ruleGlobalInterceptorDecl
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleGlobalInterceptorDecl_in_ruleFile105);
            	    temp_interceptorDecls=ruleGlobalInterceptorDecl();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"interceptorDecls",convert(temp_interceptorDecls),false); ptm.ruleFinished(temp_interceptorDecls,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:136:1: (temp_routes= ruleRoute )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==10) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:136:2: temp_routes= ruleRoute
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(2)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleRoute_in_ruleFile117);
            	    temp_routes=ruleRoute();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"routes",convert(temp_routes),false); ptm.ruleFinished(temp_routes,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:139:1: (temp_interceptorDefs= ruleInterceptorChainDef )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==16) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:139:2: temp_interceptorDefs= ruleInterceptorChainDef
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(3)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleInterceptorChainDef_in_ruleFile129);
            	    temp_interceptorDefs=ruleInterceptorChainDef();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"interceptorDefs",convert(temp_interceptorDefs),false); ptm.ruleFinished(temp_interceptorDefs,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 2, ruleFile_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFile


    // $ANTLR start ruleRoute
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:144:1: ruleRoute returns [EObject result] : ( ( 'route' ) (temp_name= RULE_ID ) (temp_from= ruleFrom ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_process= ruleProcessExpr ) ( '}' ) ) ;
    public EObject ruleRoute() throws RecognitionException {
        EObject result = null;
        int ruleRoute_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_from = null;

        EObject temp_Interceptors = null;

        EObject temp_process = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 3) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:145:4: ( ( ( 'route' ) (temp_name= RULE_ID ) (temp_from= ruleFrom ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_process= ruleProcessExpr ) ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:145:4: ( ( 'route' ) (temp_name= RULE_ID ) (temp_from= ruleFrom ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_process= ruleProcessExpr ) ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Route");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:149:1: ( ( 'route' ) (temp_name= RULE_ID ) (temp_from= ruleFrom ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_process= ruleProcessExpr ) ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:149:2: ( 'route' ) (temp_name= RULE_ID ) (temp_from= ruleFrom ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_process= ruleProcessExpr ) ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:149:2: ( 'route' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:149:3: 'route'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,10,FOLLOW_10_in_ruleRoute157); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:151:1: (temp_name= RULE_ID )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:151:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRoute166); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:154:1: (temp_from= ruleFrom )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:154:2: temp_from= ruleFrom
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleFrom_in_ruleRoute177);
            temp_from=ruleFrom();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"from",convert(temp_from),false); ptm.ruleFinished(temp_from,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:157:1: (temp_Interceptors= ruleInterceptors )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==15) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:157:2: temp_Interceptors= ruleInterceptors
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(3)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleInterceptors_in_ruleRoute189);
                    temp_Interceptors=ruleInterceptors();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_Interceptors;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }
                    break;

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:161:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:161:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(4)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleRoute200); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:163:1: (temp_process= ruleProcessExpr )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:163:2: temp_process= ruleProcessExpr
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(5)),line(),start());
            }
            pushFollow(FOLLOW_ruleProcessExpr_in_ruleRoute209);
            temp_process=ruleProcessExpr();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"process",convert(temp_process),false); ptm.ruleFinished(temp_process,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:166:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:166:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(6)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleRoute218); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 3, ruleRoute_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleRoute


    // $ANTLR start ruleImport
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:170:1: ruleImport returns [EObject result] : ( ( 'import' ) (temp_location= RULE_STRING ) ) ;
    public EObject ruleImport() throws RecognitionException {
        EObject result = null;
        int ruleImport_StartIndex = input.index();
        Token temp_location=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 4) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:171:4: ( ( ( 'import' ) (temp_location= RULE_STRING ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:171:4: ( ( 'import' ) (temp_location= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Import");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:175:1: ( ( 'import' ) (temp_location= RULE_STRING ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:175:2: ( 'import' ) (temp_location= RULE_STRING )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:175:2: ( 'import' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:175:3: 'import'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,13,FOLLOW_13_in_ruleImport243); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:177:1: (temp_location= RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:177:2: temp_location= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_location=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleImport253); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"location",convert(temp_location),false); ptm.ruleFinished(temp_location,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 4, ruleImport_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleImport


    // $ANTLR start ruleGlobalInterceptorDecl
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:183:1: ruleGlobalInterceptorDecl returns [EObject result] : ( ( 'global' ) ( 'interceptors' ) (temp_interceptorName= RULE_ID ) ) ;
    public EObject ruleGlobalInterceptorDecl() throws RecognitionException {
        EObject result = null;
        int ruleGlobalInterceptorDecl_StartIndex = input.index();
        Token temp_interceptorName=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 5) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:184:4: ( ( ( 'global' ) ( 'interceptors' ) (temp_interceptorName= RULE_ID ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:184:4: ( ( 'global' ) ( 'interceptors' ) (temp_interceptorName= RULE_ID ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "GlobalInterceptorDecl");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:188:1: ( ( 'global' ) ( 'interceptors' ) (temp_interceptorName= RULE_ID ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:188:2: ( 'global' ) ( 'interceptors' ) (temp_interceptorName= RULE_ID )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:188:2: ( 'global' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:188:3: 'global'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,14,FOLLOW_14_in_ruleGlobalInterceptorDecl281); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:190:1: ( 'interceptors' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:190:2: 'interceptors'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,15,FOLLOW_15_in_ruleGlobalInterceptorDecl288); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:192:1: (temp_interceptorName= RULE_ID )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:192:2: temp_interceptorName= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            temp_interceptorName=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGlobalInterceptorDecl297); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"interceptorName",convert(temp_interceptorName),true); ptm.ruleFinished(temp_interceptorName,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 5, ruleGlobalInterceptorDecl_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleGlobalInterceptorDecl


    // $ANTLR start ruleInterceptorChainDef
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:198:1: ruleInterceptorChainDef returns [EObject result] : ( ( 'interceptor' ) (temp_name= RULE_ID ) ( '{' ) (temp_chain= ruleBeanExpr )+ ( '}' ) ) ;
    public EObject ruleInterceptorChainDef() throws RecognitionException {
        EObject result = null;
        int ruleInterceptorChainDef_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_chain = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 6) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:199:4: ( ( ( 'interceptor' ) (temp_name= RULE_ID ) ( '{' ) (temp_chain= ruleBeanExpr )+ ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:199:4: ( ( 'interceptor' ) (temp_name= RULE_ID ) ( '{' ) (temp_chain= ruleBeanExpr )+ ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "InterceptorChainDef");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:203:1: ( ( 'interceptor' ) (temp_name= RULE_ID ) ( '{' ) (temp_chain= ruleBeanExpr )+ ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:203:2: ( 'interceptor' ) (temp_name= RULE_ID ) ( '{' ) (temp_chain= ruleBeanExpr )+ ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:203:2: ( 'interceptor' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:203:3: 'interceptor'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,16,FOLLOW_16_in_ruleInterceptorChainDef325); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:205:1: (temp_name= RULE_ID )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:205:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInterceptorChainDef334); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:208:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:208:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleInterceptorChainDef343); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:210:1: (temp_chain= ruleBeanExpr )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==39) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:210:2: temp_chain= ruleBeanExpr
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(3)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleBeanExpr_in_ruleInterceptorChainDef352);
            	    temp_chain=ruleBeanExpr();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"chain",convert(temp_chain),false); ptm.ruleFinished(temp_chain,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:213:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:213:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(4)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleInterceptorChainDef362); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 6, ruleInterceptorChainDef_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleInterceptorChainDef


    // $ANTLR start ruleInterceptors
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:217:1: ruleInterceptors returns [EObject result] : ( ( 'interceptors' ) ( '(' ) (temp_interceptorName= RULE_ID )+ ( ')' ) ) ;
    public EObject ruleInterceptors() throws RecognitionException {
        EObject result = null;
        int ruleInterceptors_StartIndex = input.index();
        Token temp_interceptorName=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 7) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:218:4: ( ( ( 'interceptors' ) ( '(' ) (temp_interceptorName= RULE_ID )+ ( ')' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:218:4: ( ( 'interceptors' ) ( '(' ) (temp_interceptorName= RULE_ID )+ ( ')' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Interceptors");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:222:1: ( ( 'interceptors' ) ( '(' ) (temp_interceptorName= RULE_ID )+ ( ')' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:222:2: ( 'interceptors' ) ( '(' ) (temp_interceptorName= RULE_ID )+ ( ')' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:222:2: ( 'interceptors' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:222:3: 'interceptors'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,15,FOLLOW_15_in_ruleInterceptors387); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:224:1: ( '(' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:224:2: '('
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,17,FOLLOW_17_in_ruleInterceptors394); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:226:1: (temp_interceptorName= RULE_ID )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:226:2: temp_interceptorName= RULE_ID
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)),line(),start());
            	    }
            	    temp_interceptorName=(Token)input.LT(1);
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInterceptors403); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"interceptorName",convert(temp_interceptorName),true); ptm.ruleFinished(temp_interceptorName,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:230:1: ( ')' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:230:2: ')'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,18,FOLLOW_18_in_ruleInterceptors414); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 7, ruleInterceptors_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleInterceptors


    // $ANTLR start ruleFrom
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:234:1: ruleFrom returns [EObject result] : ( ( 'from' ) (temp_uri= RULE_STRING ) ) ;
    public EObject ruleFrom() throws RecognitionException {
        EObject result = null;
        int ruleFrom_StartIndex = input.index();
        Token temp_uri=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 8) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:235:4: ( ( ( 'from' ) (temp_uri= RULE_STRING ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:235:4: ( ( 'from' ) (temp_uri= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "From");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:239:1: ( ( 'from' ) (temp_uri= RULE_STRING ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:239:2: ( 'from' ) (temp_uri= RULE_STRING )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:239:2: ( 'from' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:239:3: 'from'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,19,FOLLOW_19_in_ruleFrom439); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:241:1: (temp_uri= RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:241:2: temp_uri= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_uri=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFrom448); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"uri",convert(temp_uri),false); ptm.ruleFinished(temp_uri,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 8, ruleFrom_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFrom


    // $ANTLR start ruleTo
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:246:1: ruleTo returns [EObject result] : ( ( 'to' ) (temp_uri= RULE_STRING ) ) ;
    public EObject ruleTo() throws RecognitionException {
        EObject result = null;
        int ruleTo_StartIndex = input.index();
        Token temp_uri=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 9) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:247:4: ( ( ( 'to' ) (temp_uri= RULE_STRING ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:247:4: ( ( 'to' ) (temp_uri= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "To");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:251:1: ( ( 'to' ) (temp_uri= RULE_STRING ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:251:2: ( 'to' ) (temp_uri= RULE_STRING )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:251:2: ( 'to' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:251:3: 'to'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,20,FOLLOW_20_in_ruleTo475); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:253:1: (temp_uri= RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:253:2: temp_uri= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_uri=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTo484); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"uri",convert(temp_uri),false); ptm.ruleFinished(temp_uri,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 9, ruleTo_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTo


    // $ANTLR start rulePatternExpr
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:258:1: rulePatternExpr returns [EObject result] : (temp_choice= ruleChoice | temp_recipients= ruleRecipients | temp_pipeline= rulePipeline | temp_filter= ruleFilter | temp_splitter= ruleSplitter | temp_resequencer= ruleResequencer | temp_transformer= ruleTransformer | temp_dynamicrecipients= ruleDynamicRecipients );
    public EObject rulePatternExpr() throws RecognitionException {
        EObject result = null;
        int rulePatternExpr_StartIndex = input.index();
        EObject temp_choice = null;

        EObject temp_recipients = null;

        EObject temp_pipeline = null;

        EObject temp_filter = null;

        EObject temp_splitter = null;

        EObject temp_resequencer = null;

        EObject temp_transformer = null;

        EObject temp_dynamicrecipients = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 10) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:259:9: (temp_choice= ruleChoice | temp_recipients= ruleRecipients | temp_pipeline= rulePipeline | temp_filter= ruleFilter | temp_splitter= ruleSplitter | temp_resequencer= ruleResequencer | temp_transformer= ruleTransformer | temp_dynamicrecipients= ruleDynamicRecipients )
            int alt8=8;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt8=1;
                }
                break;
            case 28:
                {
                alt8=2;
                }
                break;
            case 30:
                {
                alt8=3;
                }
                break;
            case 31:
                {
                alt8=4;
                }
                break;
            case 32:
                {
                alt8=5;
                }
                break;
            case 33:
                {
                alt8=6;
                }
                break;
            case 26:
                {
                alt8=7;
                }
                break;
            case 29:
                {
                alt8=8;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("258:1: rulePatternExpr returns [EObject result] : (temp_choice= ruleChoice | temp_recipients= ruleRecipients | temp_pipeline= rulePipeline | temp_filter= ruleFilter | temp_splitter= ruleSplitter | temp_resequencer= ruleResequencer | temp_transformer= ruleTransformer | temp_dynamicrecipients= ruleDynamicRecipients );", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:259:9: temp_choice= ruleChoice
                    {
                    pushFollow(FOLLOW_ruleChoice_in_rulePatternExpr513);
                    temp_choice=ruleChoice();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_choice;
                    }

                    }
                    break;
                case 2 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:259:64: temp_recipients= ruleRecipients
                    {
                    pushFollow(FOLLOW_ruleRecipients_in_rulePatternExpr528);
                    temp_recipients=ruleRecipients();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_recipients;
                    }

                    }
                    break;
                case 3 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:259:131: temp_pipeline= rulePipeline
                    {
                    pushFollow(FOLLOW_rulePipeline_in_rulePatternExpr543);
                    temp_pipeline=rulePipeline();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_pipeline;
                    }

                    }
                    break;
                case 4 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:259:192: temp_filter= ruleFilter
                    {
                    pushFollow(FOLLOW_ruleFilter_in_rulePatternExpr558);
                    temp_filter=ruleFilter();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_filter;
                    }

                    }
                    break;
                case 5 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:259:247: temp_splitter= ruleSplitter
                    {
                    pushFollow(FOLLOW_ruleSplitter_in_rulePatternExpr573);
                    temp_splitter=ruleSplitter();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_splitter;
                    }

                    }
                    break;
                case 6 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:259:308: temp_resequencer= ruleResequencer
                    {
                    pushFollow(FOLLOW_ruleResequencer_in_rulePatternExpr588);
                    temp_resequencer=ruleResequencer();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_resequencer;
                    }

                    }
                    break;
                case 7 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:259:378: temp_transformer= ruleTransformer
                    {
                    pushFollow(FOLLOW_ruleTransformer_in_rulePatternExpr603);
                    temp_transformer=ruleTransformer();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_transformer;
                    }

                    }
                    break;
                case 8 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:259:448: temp_dynamicrecipients= ruleDynamicRecipients
                    {
                    pushFollow(FOLLOW_ruleDynamicRecipients_in_rulePatternExpr618);
                    temp_dynamicrecipients=ruleDynamicRecipients();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_dynamicrecipients;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 10, rulePatternExpr_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePatternExpr


    // $ANTLR start ruleProcessExpr
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:261:1: ruleProcessExpr returns [EObject result] : temp_patternorbeanexpr= rulePatternOrBeanExpr ;
    public EObject ruleProcessExpr() throws RecognitionException {
        EObject result = null;
        int ruleProcessExpr_StartIndex = input.index();
        EObject temp_patternorbeanexpr = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 11) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:262:9: (temp_patternorbeanexpr= rulePatternOrBeanExpr )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:262:9: temp_patternorbeanexpr= rulePatternOrBeanExpr
            {
            pushFollow(FOLLOW_rulePatternOrBeanExpr_in_ruleProcessExpr643);
            temp_patternorbeanexpr=rulePatternOrBeanExpr();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_patternorbeanexpr;
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 11, ruleProcessExpr_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleProcessExpr


    // $ANTLR start rulePatternOrBeanExpr
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:264:1: rulePatternOrBeanExpr returns [EObject result] : (temp_beanexpr= ruleBeanExpr | temp_patternexpr= rulePatternExpr );
    public EObject rulePatternOrBeanExpr() throws RecognitionException {
        EObject result = null;
        int rulePatternOrBeanExpr_StartIndex = input.index();
        EObject temp_beanexpr = null;

        EObject temp_patternexpr = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 12) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:265:9: (temp_beanexpr= ruleBeanExpr | temp_patternexpr= rulePatternExpr )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==39) ) {
                alt9=1;
            }
            else if ( ((LA9_0>=26 && LA9_0<=33)) ) {
                alt9=2;
            }
            else {
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("264:1: rulePatternOrBeanExpr returns [EObject result] : (temp_beanexpr= ruleBeanExpr | temp_patternexpr= rulePatternExpr );", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:265:9: temp_beanexpr= ruleBeanExpr
                    {
                    pushFollow(FOLLOW_ruleBeanExpr_in_rulePatternOrBeanExpr668);
                    temp_beanexpr=ruleBeanExpr();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_beanexpr;
                    }

                    }
                    break;
                case 2 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:265:70: temp_patternexpr= rulePatternExpr
                    {
                    pushFollow(FOLLOW_rulePatternExpr_in_rulePatternOrBeanExpr683);
                    temp_patternexpr=rulePatternExpr();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_patternexpr;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 12, rulePatternOrBeanExpr_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePatternOrBeanExpr


    // $ANTLR start ruleBeanExpr
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:267:1: ruleBeanExpr returns [EObject result] : ( (temp_BEAN= ruleBEAN ) (temp_id= RULE_ID ) (temp_method= RULE_ID )? ) ;
    public EObject ruleBeanExpr() throws RecognitionException {
        EObject result = null;
        int ruleBeanExpr_StartIndex = input.index();
        Token temp_id=null;
        Token temp_method=null;
        EObject temp_BEAN = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 13) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:268:4: ( ( (temp_BEAN= ruleBEAN ) (temp_id= RULE_ID ) (temp_method= RULE_ID )? ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:268:4: ( (temp_BEAN= ruleBEAN ) (temp_id= RULE_ID ) (temp_method= RULE_ID )? )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "BeanExpr");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:272:1: ( (temp_BEAN= ruleBEAN ) (temp_id= RULE_ID ) (temp_method= RULE_ID )? )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:272:2: (temp_BEAN= ruleBEAN ) (temp_id= RULE_ID ) (temp_method= RULE_ID )?
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:272:2: (temp_BEAN= ruleBEAN )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:272:3: temp_BEAN= ruleBEAN
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleBEAN_in_ruleBeanExpr709);
            temp_BEAN=ruleBEAN();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_BEAN;
            }
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:276:1: (temp_id= RULE_ID )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:276:2: temp_id= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_id=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleBeanExpr721); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"id",convert(temp_id),false); ptm.ruleFinished(temp_id,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:279:1: (temp_method= RULE_ID )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ID) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:279:2: temp_method= RULE_ID
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(2)),line(),start());
                    }
                    temp_method=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleBeanExpr732); if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"method",convert(temp_method),false); ptm.ruleFinished(temp_method,end()); 
                    }

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 13, ruleBeanExpr_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleBeanExpr


    // $ANTLR start ruleSimplePattern
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:284:1: ruleSimplePattern returns [EObject result] : ( (temp_interceptors= ruleInterceptors )? ( '{' ) (temp_actions= ruleActionStatement )* (temp_targets= ruleToTarget )+ (temp_properties= rulePropertiesBlock )? ( '}' ) ) ;
    public EObject ruleSimplePattern() throws RecognitionException {
        EObject result = null;
        int ruleSimplePattern_StartIndex = input.index();
        EObject temp_interceptors = null;

        EObject temp_actions = null;

        EObject temp_targets = null;

        EObject temp_properties = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 14) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:285:4: ( ( (temp_interceptors= ruleInterceptors )? ( '{' ) (temp_actions= ruleActionStatement )* (temp_targets= ruleToTarget )+ (temp_properties= rulePropertiesBlock )? ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:285:4: ( (temp_interceptors= ruleInterceptors )? ( '{' ) (temp_actions= ruleActionStatement )* (temp_targets= ruleToTarget )+ (temp_properties= rulePropertiesBlock )? ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "SimplePattern");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:289:1: ( (temp_interceptors= ruleInterceptors )? ( '{' ) (temp_actions= ruleActionStatement )* (temp_targets= ruleToTarget )+ (temp_properties= rulePropertiesBlock )? ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:289:2: (temp_interceptors= ruleInterceptors )? ( '{' ) (temp_actions= ruleActionStatement )* (temp_targets= ruleToTarget )+ (temp_properties= rulePropertiesBlock )? ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:289:2: (temp_interceptors= ruleInterceptors )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==15) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:289:3: temp_interceptors= ruleInterceptors
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(0)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleInterceptors_in_ruleSimplePattern762);
                    temp_interceptors=ruleInterceptors();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"interceptors",convert(temp_interceptors),false); ptm.ruleFinished(temp_interceptors,end()); 
                    }

                    }
                    break;

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:292:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:292:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleSimplePattern772); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:294:1: (temp_actions= ruleActionStatement )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=21 && LA12_0<=22)||LA12_0==24) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:294:2: temp_actions= ruleActionStatement
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(2)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleActionStatement_in_ruleSimplePattern781);
            	    temp_actions=ruleActionStatement();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"actions",convert(temp_actions),false); ptm.ruleFinished(temp_actions,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:297:1: (temp_targets= ruleToTarget )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==20||(LA13_0>=26 && LA13_0<=33)||LA13_0==37||LA13_0==39) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:297:2: temp_targets= ruleToTarget
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(3)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleToTarget_in_ruleSimplePattern793);
            	    temp_targets=ruleToTarget();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"targets",convert(temp_targets),false); ptm.ruleFinished(temp_targets,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:300:1: (temp_properties= rulePropertiesBlock )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==36) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:300:2: temp_properties= rulePropertiesBlock
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(4)),line(),start());
                    }
                    pushFollow(FOLLOW_rulePropertiesBlock_in_ruleSimplePattern805);
                    temp_properties=rulePropertiesBlock();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"properties",convert(temp_properties),false); ptm.ruleFinished(temp_properties,end()); 
                    }

                    }
                    break;

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:303:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:303:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(5)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleSimplePattern815); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 14, ruleSimplePattern_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSimplePattern


    // $ANTLR start ruleActionStatement
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:307:1: ruleActionStatement returns [EObject result] : ( (temp_SetHeaderAction= ruleSetHeaderAction ) | (temp_SetBodyAction= ruleSetBodyAction ) | ( ( 'convert' ) (temp_ConvertBodyAction= ruleConvertBodyAction ) ) ) ;
    public EObject ruleActionStatement() throws RecognitionException {
        EObject result = null;
        int ruleActionStatement_StartIndex = input.index();
        EObject temp_SetHeaderAction = null;

        EObject temp_SetBodyAction = null;

        EObject temp_ConvertBodyAction = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 15) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:308:4: ( ( (temp_SetHeaderAction= ruleSetHeaderAction ) | (temp_SetBodyAction= ruleSetBodyAction ) | ( ( 'convert' ) (temp_ConvertBodyAction= ruleConvertBodyAction ) ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:308:4: ( (temp_SetHeaderAction= ruleSetHeaderAction ) | (temp_SetBodyAction= ruleSetBodyAction ) | ( ( 'convert' ) (temp_ConvertBodyAction= ruleConvertBodyAction ) ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ActionStatement");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:312:1: ( (temp_SetHeaderAction= ruleSetHeaderAction ) | (temp_SetBodyAction= ruleSetBodyAction ) | ( ( 'convert' ) (temp_ConvertBodyAction= ruleConvertBodyAction ) ) )
            int alt15=3;
            switch ( input.LA(1) ) {
            case 22:
                {
                alt15=1;
                }
                break;
            case 24:
                {
                alt15=2;
                }
                break;
            case 21:
                {
                alt15=3;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("312:1: ( (temp_SetHeaderAction= ruleSetHeaderAction ) | (temp_SetBodyAction= ruleSetBodyAction ) | ( ( 'convert' ) (temp_ConvertBodyAction= ruleConvertBodyAction ) ) )", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:312:2: (temp_SetHeaderAction= ruleSetHeaderAction )
                    {
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:312:2: (temp_SetHeaderAction= ruleSetHeaderAction )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:312:3: temp_SetHeaderAction= ruleSetHeaderAction
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(0)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleSetHeaderAction_in_ruleActionStatement843);
                    temp_SetHeaderAction=ruleSetHeaderAction();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_SetHeaderAction;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;
                case 2 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:316:1: (temp_SetBodyAction= ruleSetBodyAction )
                    {
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:316:1: (temp_SetBodyAction= ruleSetBodyAction )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:316:2: temp_SetBodyAction= ruleSetBodyAction
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleSetBodyAction_in_ruleActionStatement858);
                    temp_SetBodyAction=ruleSetBodyAction();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_SetBodyAction;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;
                case 3 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:320:1: ( ( 'convert' ) (temp_ConvertBodyAction= ruleConvertBodyAction ) )
                    {
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:320:1: ( ( 'convert' ) (temp_ConvertBodyAction= ruleConvertBodyAction ) )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:320:2: ( 'convert' ) (temp_ConvertBodyAction= ruleConvertBodyAction )
                    {
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:320:2: ( 'convert' )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:320:3: 'convert'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(2)).eContents().get(0)),line(),start());
                    }
                    match(input,21,FOLLOW_21_in_ruleActionStatement871); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }

                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:322:1: (temp_ConvertBodyAction= ruleConvertBodyAction )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:322:2: temp_ConvertBodyAction= ruleConvertBodyAction
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(2)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleConvertBodyAction_in_ruleActionStatement881);
                    temp_ConvertBodyAction=ruleConvertBodyAction();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_ConvertBodyAction;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 15, ruleActionStatement_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleActionStatement


    // $ANTLR start ruleSetHeaderAction
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:329:1: ruleSetHeaderAction returns [EObject result] : ( ( 'header' ) (temp_header= RULE_STRING ) ( '=' ) (temp_value= ruleExpression ) ) ;
    public EObject ruleSetHeaderAction() throws RecognitionException {
        EObject result = null;
        int ruleSetHeaderAction_StartIndex = input.index();
        Token temp_header=null;
        EObject temp_value = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 16) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:330:4: ( ( ( 'header' ) (temp_header= RULE_STRING ) ( '=' ) (temp_value= ruleExpression ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:330:4: ( ( 'header' ) (temp_header= RULE_STRING ) ( '=' ) (temp_value= ruleExpression ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "SetHeaderAction");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:334:1: ( ( 'header' ) (temp_header= RULE_STRING ) ( '=' ) (temp_value= ruleExpression ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:334:2: ( 'header' ) (temp_header= RULE_STRING ) ( '=' ) (temp_value= ruleExpression )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:334:2: ( 'header' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:334:3: 'header'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,22,FOLLOW_22_in_ruleSetHeaderAction911); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:336:1: (temp_header= RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:336:2: temp_header= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_header=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSetHeaderAction920); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"header",convert(temp_header),false); ptm.ruleFinished(temp_header,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:339:1: ( '=' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:339:2: '='
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,23,FOLLOW_23_in_ruleSetHeaderAction929); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:341:1: (temp_value= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:341:2: temp_value= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleSetHeaderAction938);
            temp_value=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 16, ruleSetHeaderAction_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSetHeaderAction


    // $ANTLR start ruleSetBodyAction
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:346:1: ruleSetBodyAction returns [EObject result] : ( ( 'body' ) ( '=' ) (temp_value= ruleExpression ) ) ;
    public EObject ruleSetBodyAction() throws RecognitionException {
        EObject result = null;
        int ruleSetBodyAction_StartIndex = input.index();
        EObject temp_value = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 17) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:347:4: ( ( ( 'body' ) ( '=' ) (temp_value= ruleExpression ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:347:4: ( ( 'body' ) ( '=' ) (temp_value= ruleExpression ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "SetBodyAction");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:351:1: ( ( 'body' ) ( '=' ) (temp_value= ruleExpression ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:351:2: ( 'body' ) ( '=' ) (temp_value= ruleExpression )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:351:2: ( 'body' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:351:3: 'body'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,24,FOLLOW_24_in_ruleSetBodyAction965); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:353:1: ( '=' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:353:2: '='
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,23,FOLLOW_23_in_ruleSetBodyAction972); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:355:1: (temp_value= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:355:2: temp_value= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleSetBodyAction981);
            temp_value=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 17, ruleSetBodyAction_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSetBodyAction


    // $ANTLR start ruleConvertBodyAction
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:360:1: ruleConvertBodyAction returns [EObject result] : (temp_convertbodywithaction= ruleConvertBodyWithAction | temp_convertbodytoaction= ruleConvertBodyToAction );
    public EObject ruleConvertBodyAction() throws RecognitionException {
        EObject result = null;
        int ruleConvertBodyAction_StartIndex = input.index();
        EObject temp_convertbodywithaction = null;

        EObject temp_convertbodytoaction = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 18) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:361:9: (temp_convertbodywithaction= ruleConvertBodyWithAction | temp_convertbodytoaction= ruleConvertBodyToAction )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==25) ) {
                alt16=1;
            }
            else if ( (LA16_0==20) ) {
                alt16=2;
            }
            else {
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("360:1: ruleConvertBodyAction returns [EObject result] : (temp_convertbodywithaction= ruleConvertBodyWithAction | temp_convertbodytoaction= ruleConvertBodyToAction );", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:361:9: temp_convertbodywithaction= ruleConvertBodyWithAction
                    {
                    pushFollow(FOLLOW_ruleConvertBodyWithAction_in_ruleConvertBodyAction1010);
                    temp_convertbodywithaction=ruleConvertBodyWithAction();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_convertbodywithaction;
                    }

                    }
                    break;
                case 2 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:361:109: temp_convertbodytoaction= ruleConvertBodyToAction
                    {
                    pushFollow(FOLLOW_ruleConvertBodyToAction_in_ruleConvertBodyAction1025);
                    temp_convertbodytoaction=ruleConvertBodyToAction();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_convertbodytoaction;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 18, ruleConvertBodyAction_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleConvertBodyAction


    // $ANTLR start ruleConvertBodyWithAction
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:363:1: ruleConvertBodyWithAction returns [EObject result] : ( ( 'with' ) (temp_BeanExpr= ruleBeanExpr ) ) ;
    public EObject ruleConvertBodyWithAction() throws RecognitionException {
        EObject result = null;
        int ruleConvertBodyWithAction_StartIndex = input.index();
        EObject temp_BeanExpr = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 19) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:364:4: ( ( ( 'with' ) (temp_BeanExpr= ruleBeanExpr ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:364:4: ( ( 'with' ) (temp_BeanExpr= ruleBeanExpr ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ConvertBodyWithAction");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:368:1: ( ( 'with' ) (temp_BeanExpr= ruleBeanExpr ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:368:2: ( 'with' ) (temp_BeanExpr= ruleBeanExpr )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:368:2: ( 'with' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:368:3: 'with'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,25,FOLLOW_25_in_ruleConvertBodyWithAction1048); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:370:1: (temp_BeanExpr= ruleBeanExpr )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:370:2: temp_BeanExpr= ruleBeanExpr
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleBeanExpr_in_ruleConvertBodyWithAction1058);
            temp_BeanExpr=ruleBeanExpr();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_BeanExpr;
            }
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 19, ruleConvertBodyWithAction_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleConvertBodyWithAction


    // $ANTLR start ruleConvertBodyToAction
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:376:1: ruleConvertBodyToAction returns [EObject result] : ( ( 'to' ) (temp_type= RULE_STRING ) ) ;
    public EObject ruleConvertBodyToAction() throws RecognitionException {
        EObject result = null;
        int ruleConvertBodyToAction_StartIndex = input.index();
        Token temp_type=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 20) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:377:4: ( ( ( 'to' ) (temp_type= RULE_STRING ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:377:4: ( ( 'to' ) (temp_type= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ConvertBodyToAction");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:381:1: ( ( 'to' ) (temp_type= RULE_STRING ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:381:2: ( 'to' ) (temp_type= RULE_STRING )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:381:2: ( 'to' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:381:3: 'to'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(18)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,20,FOLLOW_20_in_ruleConvertBodyToAction1086); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:383:1: (temp_type= RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:383:2: temp_type= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(18)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_type=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleConvertBodyToAction1095); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"type",convert(temp_type),false); ptm.ruleFinished(temp_type,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 20, ruleConvertBodyToAction_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleConvertBodyToAction


    // $ANTLR start ruleTransformer
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:388:1: ruleTransformer returns [EObject result] : ( ( 'transformer' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_beanref= ruleBeanExpr ) (temp_target= ruleToTarget )+ ( '}' ) ) ;
    public EObject ruleTransformer() throws RecognitionException {
        EObject result = null;
        int ruleTransformer_StartIndex = input.index();
        EObject temp_Interceptors = null;

        EObject temp_beanref = null;

        EObject temp_target = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 21) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:389:4: ( ( ( 'transformer' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_beanref= ruleBeanExpr ) (temp_target= ruleToTarget )+ ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:389:4: ( ( 'transformer' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_beanref= ruleBeanExpr ) (temp_target= ruleToTarget )+ ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Transformer");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:393:1: ( ( 'transformer' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_beanref= ruleBeanExpr ) (temp_target= ruleToTarget )+ ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:393:2: ( 'transformer' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_beanref= ruleBeanExpr ) (temp_target= ruleToTarget )+ ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:393:2: ( 'transformer' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:393:3: 'transformer'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,26,FOLLOW_26_in_ruleTransformer1122); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:395:1: (temp_Interceptors= ruleInterceptors )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==15) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:395:2: temp_Interceptors= ruleInterceptors
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleInterceptors_in_ruleTransformer1132);
                    temp_Interceptors=ruleInterceptors();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_Interceptors;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }
                    break;

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:399:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:399:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleTransformer1143); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:401:1: (temp_beanref= ruleBeanExpr )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:401:2: temp_beanref= ruleBeanExpr
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            pushFollow(FOLLOW_ruleBeanExpr_in_ruleTransformer1152);
            temp_beanref=ruleBeanExpr();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"beanref",convert(temp_beanref),false); ptm.ruleFinished(temp_beanref,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:404:1: (temp_target= ruleToTarget )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==20||(LA18_0>=26 && LA18_0<=33)||LA18_0==37||LA18_0==39) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:404:2: temp_target= ruleToTarget
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(4)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleToTarget_in_ruleTransformer1163);
            	    temp_target=ruleToTarget();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:407:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:407:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(5)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleTransformer1173); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 21, ruleTransformer_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTransformer


    // $ANTLR start ruleChoice
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:411:1: ruleChoice returns [EObject result] : ( ( 'choice' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_when= ruleWhenClause )* (temp_otherwise= ruleOtherwiseClause )? ( '}' ) ) ;
    public EObject ruleChoice() throws RecognitionException {
        EObject result = null;
        int ruleChoice_StartIndex = input.index();
        EObject temp_Interceptors = null;

        EObject temp_when = null;

        EObject temp_otherwise = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 22) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:412:4: ( ( ( 'choice' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_when= ruleWhenClause )* (temp_otherwise= ruleOtherwiseClause )? ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:412:4: ( ( 'choice' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_when= ruleWhenClause )* (temp_otherwise= ruleOtherwiseClause )? ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Choice");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:416:1: ( ( 'choice' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_when= ruleWhenClause )* (temp_otherwise= ruleOtherwiseClause )? ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:416:2: ( 'choice' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_when= ruleWhenClause )* (temp_otherwise= ruleOtherwiseClause )? ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:416:2: ( 'choice' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:416:3: 'choice'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,27,FOLLOW_27_in_ruleChoice1198); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:418:1: (temp_Interceptors= ruleInterceptors )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==15) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:418:2: temp_Interceptors= ruleInterceptors
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleInterceptors_in_ruleChoice1208);
                    temp_Interceptors=ruleInterceptors();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_Interceptors;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }
                    break;

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:422:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:422:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleChoice1219); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:424:1: (temp_when= ruleWhenClause )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==34) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:424:2: temp_when= ruleWhenClause
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(3)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleWhenClause_in_ruleChoice1228);
            	    temp_when=ruleWhenClause();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"when",convert(temp_when),false); ptm.ruleFinished(temp_when,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:427:1: (temp_otherwise= ruleOtherwiseClause )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==35) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:427:2: temp_otherwise= ruleOtherwiseClause
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(4)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleOtherwiseClause_in_ruleChoice1240);
                    temp_otherwise=ruleOtherwiseClause();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"otherwise",convert(temp_otherwise),false); ptm.ruleFinished(temp_otherwise,end()); 
                    }

                    }
                    break;

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:430:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:430:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(5)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleChoice1250); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 22, ruleChoice_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleChoice


    // $ANTLR start ruleRecipients
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:434:1: ruleRecipients returns [EObject result] : ( ( 'recipients' ) (temp_body= ruleSimplePattern ) ) ;
    public EObject ruleRecipients() throws RecognitionException {
        EObject result = null;
        int ruleRecipients_StartIndex = input.index();
        EObject temp_body = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 23) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:435:4: ( ( ( 'recipients' ) (temp_body= ruleSimplePattern ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:435:4: ( ( 'recipients' ) (temp_body= ruleSimplePattern ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Recipients");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:439:1: ( ( 'recipients' ) (temp_body= ruleSimplePattern ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:439:2: ( 'recipients' ) (temp_body= ruleSimplePattern )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:439:2: ( 'recipients' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:439:3: 'recipients'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,28,FOLLOW_28_in_ruleRecipients1275); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:441:1: (temp_body= ruleSimplePattern )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:441:2: temp_body= ruleSimplePattern
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleSimplePattern_in_ruleRecipients1284);
            temp_body=ruleSimplePattern();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 23, ruleRecipients_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleRecipients


    // $ANTLR start ruleDynamicRecipients
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:446:1: ruleDynamicRecipients returns [EObject result] : ( ( 'dynamic' ) ( 'recipients' ) (temp_body= ruleExpression ) ) ;
    public EObject ruleDynamicRecipients() throws RecognitionException {
        EObject result = null;
        int ruleDynamicRecipients_StartIndex = input.index();
        EObject temp_body = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 24) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:447:4: ( ( ( 'dynamic' ) ( 'recipients' ) (temp_body= ruleExpression ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:447:4: ( ( 'dynamic' ) ( 'recipients' ) (temp_body= ruleExpression ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "DynamicRecipients");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:451:1: ( ( 'dynamic' ) ( 'recipients' ) (temp_body= ruleExpression ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:451:2: ( 'dynamic' ) ( 'recipients' ) (temp_body= ruleExpression )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:451:2: ( 'dynamic' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:451:3: 'dynamic'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,29,FOLLOW_29_in_ruleDynamicRecipients1311); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:453:1: ( 'recipients' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:453:2: 'recipients'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,28,FOLLOW_28_in_ruleDynamicRecipients1318); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:455:1: (temp_body= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:455:2: temp_body= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleDynamicRecipients1327);
            temp_body=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 24, ruleDynamicRecipients_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleDynamicRecipients


    // $ANTLR start rulePipeline
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:460:1: rulePipeline returns [EObject result] : ( ( 'pipeline' ) (temp_body= ruleSimplePattern ) ) ;
    public EObject rulePipeline() throws RecognitionException {
        EObject result = null;
        int rulePipeline_StartIndex = input.index();
        EObject temp_body = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 25) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:461:4: ( ( ( 'pipeline' ) (temp_body= ruleSimplePattern ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:461:4: ( ( 'pipeline' ) (temp_body= ruleSimplePattern ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Pipeline");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:465:1: ( ( 'pipeline' ) (temp_body= ruleSimplePattern ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:465:2: ( 'pipeline' ) (temp_body= ruleSimplePattern )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:465:2: ( 'pipeline' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:465:3: 'pipeline'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(23)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,30,FOLLOW_30_in_rulePipeline1354); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:467:1: (temp_body= ruleSimplePattern )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:467:2: temp_body= ruleSimplePattern
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(23)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleSimplePattern_in_rulePipeline1363);
            temp_body=ruleSimplePattern();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 25, rulePipeline_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePipeline


    // $ANTLR start ruleFilter
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:472:1: ruleFilter returns [EObject result] : ( ( 'filter' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) ) ;
    public EObject ruleFilter() throws RecognitionException {
        EObject result = null;
        int ruleFilter_StartIndex = input.index();
        EObject temp_expr = null;

        EObject temp_body = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 26) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:473:4: ( ( ( 'filter' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:473:4: ( ( 'filter' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Filter");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:477:1: ( ( 'filter' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:477:2: ( 'filter' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:477:2: ( 'filter' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:477:3: 'filter'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,31,FOLLOW_31_in_ruleFilter1390); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:479:1: (temp_expr= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:479:2: temp_expr= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleFilter1399);
            temp_expr=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:482:1: (temp_body= ruleSimplePattern )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:482:2: temp_body= ruleSimplePattern
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleSimplePattern_in_ruleFilter1410);
            temp_body=ruleSimplePattern();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 26, ruleFilter_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFilter


    // $ANTLR start ruleSplitter
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:487:1: ruleSplitter returns [EObject result] : ( ( 'splitter' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) ) ;
    public EObject ruleSplitter() throws RecognitionException {
        EObject result = null;
        int ruleSplitter_StartIndex = input.index();
        EObject temp_expr = null;

        EObject temp_body = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 27) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:488:4: ( ( ( 'splitter' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:488:4: ( ( 'splitter' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Splitter");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:492:1: ( ( 'splitter' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:492:2: ( 'splitter' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:492:2: ( 'splitter' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:492:3: 'splitter'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,32,FOLLOW_32_in_ruleSplitter1437); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:494:1: (temp_expr= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:494:2: temp_expr= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleSplitter1446);
            temp_expr=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:497:1: (temp_body= ruleSimplePattern )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:497:2: temp_body= ruleSimplePattern
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleSimplePattern_in_ruleSplitter1457);
            temp_body=ruleSimplePattern();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 27, ruleSplitter_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSplitter


    // $ANTLR start ruleResequencer
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:502:1: ruleResequencer returns [EObject result] : ( ( 'resequence' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) ) ;
    public EObject ruleResequencer() throws RecognitionException {
        EObject result = null;
        int ruleResequencer_StartIndex = input.index();
        EObject temp_expr = null;

        EObject temp_body = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 28) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:503:4: ( ( ( 'resequence' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:503:4: ( ( 'resequence' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Resequencer");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:507:1: ( ( 'resequence' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:507:2: ( 'resequence' ) (temp_expr= ruleExpression ) (temp_body= ruleSimplePattern )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:507:2: ( 'resequence' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:507:3: 'resequence'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,33,FOLLOW_33_in_ruleResequencer1484); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:509:1: (temp_expr= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:509:2: temp_expr= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleResequencer1493);
            temp_expr=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:512:1: (temp_body= ruleSimplePattern )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:512:2: temp_body= ruleSimplePattern
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleSimplePattern_in_ruleResequencer1504);
            temp_body=ruleSimplePattern();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 28, ruleResequencer_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleResequencer


    // $ANTLR start ruleWhenClause
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:517:1: ruleWhenClause returns [EObject result] : ( ( 'when' ) (temp_expr= ruleExpression ) ( '{' ) (temp_target= ruleToTarget )+ ( '}' ) ) ;
    public EObject ruleWhenClause() throws RecognitionException {
        EObject result = null;
        int ruleWhenClause_StartIndex = input.index();
        EObject temp_expr = null;

        EObject temp_target = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 29) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:518:4: ( ( ( 'when' ) (temp_expr= ruleExpression ) ( '{' ) (temp_target= ruleToTarget )+ ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:518:4: ( ( 'when' ) (temp_expr= ruleExpression ) ( '{' ) (temp_target= ruleToTarget )+ ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "WhenClause");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:522:1: ( ( 'when' ) (temp_expr= ruleExpression ) ( '{' ) (temp_target= ruleToTarget )+ ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:522:2: ( 'when' ) (temp_expr= ruleExpression ) ( '{' ) (temp_target= ruleToTarget )+ ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:522:2: ( 'when' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:522:3: 'when'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(27)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,34,FOLLOW_34_in_ruleWhenClause1531); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:524:1: (temp_expr= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:524:2: temp_expr= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(27)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleWhenClause1540);
            temp_expr=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:527:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:527:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(27)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleWhenClause1549); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:529:1: (temp_target= ruleToTarget )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==20||(LA22_0>=26 && LA22_0<=33)||LA22_0==37||LA22_0==39) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:529:2: temp_target= ruleToTarget
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(27)).eContents().get(1)).eContents().get(3)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleToTarget_in_ruleWhenClause1558);
            	    temp_target=ruleToTarget();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:532:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:532:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(27)).eContents().get(1)).eContents().get(4)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleWhenClause1568); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 29, ruleWhenClause_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleWhenClause


    // $ANTLR start ruleOtherwiseClause
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:536:1: ruleOtherwiseClause returns [EObject result] : ( ( 'otherwise' ) ( '{' ) (temp_target= ruleToTarget ) ( '}' ) ) ;
    public EObject ruleOtherwiseClause() throws RecognitionException {
        EObject result = null;
        int ruleOtherwiseClause_StartIndex = input.index();
        EObject temp_target = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 30) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:537:4: ( ( ( 'otherwise' ) ( '{' ) (temp_target= ruleToTarget ) ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:537:4: ( ( 'otherwise' ) ( '{' ) (temp_target= ruleToTarget ) ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "OtherwiseClause");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:541:1: ( ( 'otherwise' ) ( '{' ) (temp_target= ruleToTarget ) ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:541:2: ( 'otherwise' ) ( '{' ) (temp_target= ruleToTarget ) ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:541:2: ( 'otherwise' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:541:3: 'otherwise'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(28)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,35,FOLLOW_35_in_ruleOtherwiseClause1593); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:543:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:543:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(28)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleOtherwiseClause1600); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:545:1: (temp_target= ruleToTarget )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:545:2: temp_target= ruleToTarget
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(28)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleToTarget_in_ruleOtherwiseClause1609);
            temp_target=ruleToTarget();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:548:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:548:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(28)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleOtherwiseClause1618); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 30, ruleOtherwiseClause_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleOtherwiseClause


    // $ANTLR start ruleExpression
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:552:1: ruleExpression returns [EObject result] : ( ( (temp_lang= RULE_ID ) ( '(' ) (temp_ExpressionValue= ruleExpressionValue ) ( ')' ) ) | ( RULE_STRING ) ) ;
    public EObject ruleExpression() throws RecognitionException {
        EObject result = null;
        int ruleExpression_StartIndex = input.index();
        Token temp_lang=null;
        EObject temp_ExpressionValue = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 31) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:553:4: ( ( ( (temp_lang= RULE_ID ) ( '(' ) (temp_ExpressionValue= ruleExpressionValue ) ( ')' ) ) | ( RULE_STRING ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:553:4: ( ( (temp_lang= RULE_ID ) ( '(' ) (temp_ExpressionValue= ruleExpressionValue ) ( ')' ) ) | ( RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Expression");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:557:1: ( ( (temp_lang= RULE_ID ) ( '(' ) (temp_ExpressionValue= ruleExpressionValue ) ( ')' ) ) | ( RULE_STRING ) )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_ID) ) {
                alt23=1;
            }
            else if ( (LA23_0==RULE_STRING) ) {
                alt23=2;
            }
            else {
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("557:1: ( ( (temp_lang= RULE_ID ) ( '(' ) (temp_ExpressionValue= ruleExpressionValue ) ( ')' ) ) | ( RULE_STRING ) )", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:557:2: ( (temp_lang= RULE_ID ) ( '(' ) (temp_ExpressionValue= ruleExpressionValue ) ( ')' ) )
                    {
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:557:2: ( (temp_lang= RULE_ID ) ( '(' ) (temp_ExpressionValue= ruleExpressionValue ) ( ')' ) )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:557:3: (temp_lang= RULE_ID ) ( '(' ) (temp_ExpressionValue= ruleExpressionValue ) ( ')' )
                    {
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:557:3: (temp_lang= RULE_ID )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:557:4: temp_lang= RULE_ID
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(0)).eContents().get(0)),line(),start());
                    }
                    temp_lang=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleExpression1646); if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"lang",convert(temp_lang),false); ptm.ruleFinished(temp_lang,end()); 
                    }

                    }

                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:560:1: ( '(' )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:560:2: '('
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(0)).eContents().get(1)),line(),start());
                    }
                    match(input,17,FOLLOW_17_in_ruleExpression1655); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }

                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:562:1: (temp_ExpressionValue= ruleExpressionValue )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:562:2: temp_ExpressionValue= ruleExpressionValue
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(0)).eContents().get(2)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleExpressionValue_in_ruleExpression1665);
                    temp_ExpressionValue=ruleExpressionValue();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_ExpressionValue;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }

                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:566:1: ( ')' )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:566:2: ')'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(0)).eContents().get(3)),line(),start());
                    }
                    match(input,18,FOLLOW_18_in_ruleExpression1675); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:569:1: ( RULE_STRING )
                    {
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:569:1: ( RULE_STRING )
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:569:2: RULE_STRING
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleExpression1686); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 31, ruleExpression_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleExpression


    // $ANTLR start ruleExpressionValue
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:573:1: ruleExpressionValue returns [EObject result] : (temp_value= RULE_STRING ) ;
    public EObject ruleExpressionValue() throws RecognitionException {
        EObject result = null;
        int ruleExpressionValue_StartIndex = input.index();
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 32) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:574:4: ( (temp_value= RULE_STRING ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:574:4: (temp_value= RULE_STRING )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ExpressionValue");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:578:1: (temp_value= RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:578:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(30)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleExpressionValue1712); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 32, ruleExpressionValue_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleExpressionValue


    // $ANTLR start rulePropertiesBlock
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:582:1: rulePropertiesBlock returns [EObject result] : ( ( 'properties' ) ( '{' ) (temp_properties= rulePropertyDef )+ ( '}' ) ) ;
    public EObject rulePropertiesBlock() throws RecognitionException {
        EObject result = null;
        int rulePropertiesBlock_StartIndex = input.index();
        EObject temp_properties = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 33) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:583:4: ( ( ( 'properties' ) ( '{' ) (temp_properties= rulePropertyDef )+ ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:583:4: ( ( 'properties' ) ( '{' ) (temp_properties= rulePropertyDef )+ ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "PropertiesBlock");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:587:1: ( ( 'properties' ) ( '{' ) (temp_properties= rulePropertyDef )+ ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:587:2: ( 'properties' ) ( '{' ) (temp_properties= rulePropertyDef )+ ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:587:2: ( 'properties' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:587:3: 'properties'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(31)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,36,FOLLOW_36_in_rulePropertiesBlock1737); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:589:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:589:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(31)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,11,FOLLOW_11_in_rulePropertiesBlock1744); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:591:1: (temp_properties= rulePropertyDef )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_ID) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:591:2: temp_properties= rulePropertyDef
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(31)).eContents().get(1)).eContents().get(2)),line(),start());
            	    }
            	    pushFollow(FOLLOW_rulePropertyDef_in_rulePropertiesBlock1753);
            	    temp_properties=rulePropertyDef();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"properties",convert(temp_properties),false); ptm.ruleFinished(temp_properties,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:594:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:594:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(31)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,12,FOLLOW_12_in_rulePropertiesBlock1763); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 33, rulePropertiesBlock_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePropertiesBlock


    // $ANTLR start rulePropertyDef
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:598:1: rulePropertyDef returns [EObject result] : ( (temp_name= ruleSCOPED_ID ) ( '=' ) (temp_value= RULE_STRING ) ) ;
    public EObject rulePropertyDef() throws RecognitionException {
        EObject result = null;
        int rulePropertyDef_StartIndex = input.index();
        Token temp_value=null;
        EObject temp_name = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 34) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:599:4: ( ( (temp_name= ruleSCOPED_ID ) ( '=' ) (temp_value= RULE_STRING ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:599:4: ( (temp_name= ruleSCOPED_ID ) ( '=' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "PropertyDef");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:603:1: ( (temp_name= ruleSCOPED_ID ) ( '=' ) (temp_value= RULE_STRING ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:603:2: (temp_name= ruleSCOPED_ID ) ( '=' ) (temp_value= RULE_STRING )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:603:2: (temp_name= ruleSCOPED_ID )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:603:3: temp_name= ruleSCOPED_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(32)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleSCOPED_ID_in_rulePropertyDef1790);
            temp_name=ruleSCOPED_ID();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:606:1: ( '=' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:606:2: '='
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(32)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,23,FOLLOW_23_in_rulePropertyDef1799); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:608:1: (temp_value= RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:608:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(32)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePropertyDef1808); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 34, rulePropertyDef_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePropertyDef


    // $ANTLR start ruleToTarget
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:613:1: ruleToTarget returns [EObject result] : (temp_to= ruleTo | temp_processexpr= ruleProcessExpr | temp_fault= ruleFault );
    public EObject ruleToTarget() throws RecognitionException {
        EObject result = null;
        int ruleToTarget_StartIndex = input.index();
        EObject temp_to = null;

        EObject temp_processexpr = null;

        EObject temp_fault = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 35) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:614:9: (temp_to= ruleTo | temp_processexpr= ruleProcessExpr | temp_fault= ruleFault )
            int alt25=3;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt25=1;
                }
                break;
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 39:
                {
                alt25=2;
                }
                break;
            case 37:
                {
                alt25=3;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("613:1: ruleToTarget returns [EObject result] : (temp_to= ruleTo | temp_processexpr= ruleProcessExpr | temp_fault= ruleFault );", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:614:9: temp_to= ruleTo
                    {
                    pushFollow(FOLLOW_ruleTo_in_ruleToTarget1837);
                    temp_to=ruleTo();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_to;
                    }

                    }
                    break;
                case 2 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:614:52: temp_processexpr= ruleProcessExpr
                    {
                    pushFollow(FOLLOW_ruleProcessExpr_in_ruleToTarget1852);
                    temp_processexpr=ruleProcessExpr();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_processexpr;
                    }

                    }
                    break;
                case 3 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:614:122: temp_fault= ruleFault
                    {
                    pushFollow(FOLLOW_ruleFault_in_ruleToTarget1867);
                    temp_fault=ruleFault();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_fault;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 35, ruleToTarget_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleToTarget


    // $ANTLR start ruleFault
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:616:1: ruleFault returns [EObject result] : ( ( 'fault' ) ( RULE_STRING ) ) ;
    public EObject ruleFault() throws RecognitionException {
        EObject result = null;
        int ruleFault_StartIndex = input.index();
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 36) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:617:4: ( ( ( 'fault' ) ( RULE_STRING ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:617:4: ( ( 'fault' ) ( RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Fault");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:621:1: ( ( 'fault' ) ( RULE_STRING ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:621:2: ( 'fault' ) ( RULE_STRING )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:621:2: ( 'fault' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:621:3: 'fault'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(34)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,37,FOLLOW_37_in_ruleFault1890); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:623:1: ( RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:623:2: RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(34)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFault1897); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 36, ruleFault_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFault


    // $ANTLR start ruleSCOPED_ID
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:627:1: ruleSCOPED_ID returns [EObject result] : ( ( RULE_ID ) ( ( '.' ) ( RULE_ID ) )* ) ;
    public EObject ruleSCOPED_ID() throws RecognitionException {
        EObject result = null;
        int ruleSCOPED_ID_StartIndex = input.index();
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 37) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:628:4: ( ( ( RULE_ID ) ( ( '.' ) ( RULE_ID ) )* ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:628:4: ( ( RULE_ID ) ( ( '.' ) ( RULE_ID ) )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "SCOPED_ID");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:632:1: ( ( RULE_ID ) ( ( '.' ) ( RULE_ID ) )* )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:632:2: ( RULE_ID ) ( ( '.' ) ( RULE_ID ) )*
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:632:2: ( RULE_ID )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:632:3: RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(35)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSCOPED_ID1922); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:634:1: ( ( '.' ) ( RULE_ID ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==38) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:634:2: ( '.' ) ( RULE_ID )
            	    {
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:634:2: ( '.' )
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:634:3: '.'
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(35)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            	    }
            	    match(input,38,FOLLOW_38_in_ruleSCOPED_ID1930); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }

            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:636:1: ( RULE_ID )
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:636:2: RULE_ID
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(35)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSCOPED_ID1937); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 37, ruleSCOPED_ID_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSCOPED_ID


    // $ANTLR start ruleBEAN
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:641:1: ruleBEAN returns [EObject result] : ( 'bean' ) ;
    public EObject ruleBEAN() throws RecognitionException {
        EObject result = null;
        int ruleBEAN_StartIndex = input.index();
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 38) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:642:4: ( ( 'bean' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:642:4: ( 'bean' )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "BEAN");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:646:1: ( 'bean' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:646:2: 'bean'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(36)).eContents().get(1)),line(),start());
            }
            match(input,39,FOLLOW_39_in_ruleBEAN1964); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 38, ruleBEAN_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleBEAN


 

    public static final BitSet FOLLOW_ruleFile_in_parse67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_ruleFile93 = new BitSet(new long[]{0x0000000000016402L});
    public static final BitSet FOLLOW_ruleGlobalInterceptorDecl_in_ruleFile105 = new BitSet(new long[]{0x0000000000014402L});
    public static final BitSet FOLLOW_ruleRoute_in_ruleFile117 = new BitSet(new long[]{0x0000000000010402L});
    public static final BitSet FOLLOW_ruleInterceptorChainDef_in_ruleFile129 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_10_in_ruleRoute157 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRoute166 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleFrom_in_ruleRoute177 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleInterceptors_in_ruleRoute189 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleRoute200 = new BitSet(new long[]{0x00000083FC000000L});
    public static final BitSet FOLLOW_ruleProcessExpr_in_ruleRoute209 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleRoute218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleImport243 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleImport253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleGlobalInterceptorDecl281 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleGlobalInterceptorDecl288 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGlobalInterceptorDecl297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleInterceptorChainDef325 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInterceptorChainDef334 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleInterceptorChainDef343 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_ruleBeanExpr_in_ruleInterceptorChainDef352 = new BitSet(new long[]{0x0000008000001000L});
    public static final BitSet FOLLOW_12_in_ruleInterceptorChainDef362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleInterceptors387 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleInterceptors394 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInterceptors403 = new BitSet(new long[]{0x0000000000040010L});
    public static final BitSet FOLLOW_18_in_ruleInterceptors414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleFrom439 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFrom448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleTo475 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTo484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChoice_in_rulePatternExpr513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRecipients_in_rulePatternExpr528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePipeline_in_rulePatternExpr543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFilter_in_rulePatternExpr558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSplitter_in_rulePatternExpr573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResequencer_in_rulePatternExpr588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTransformer_in_rulePatternExpr603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDynamicRecipients_in_rulePatternExpr618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePatternOrBeanExpr_in_ruleProcessExpr643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBeanExpr_in_rulePatternOrBeanExpr668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePatternExpr_in_rulePatternOrBeanExpr683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBEAN_in_ruleBeanExpr709 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleBeanExpr721 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleBeanExpr732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterceptors_in_ruleSimplePattern762 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleSimplePattern772 = new BitSet(new long[]{0x000000A3FD700000L});
    public static final BitSet FOLLOW_ruleActionStatement_in_ruleSimplePattern781 = new BitSet(new long[]{0x000000A3FD700000L});
    public static final BitSet FOLLOW_ruleToTarget_in_ruleSimplePattern793 = new BitSet(new long[]{0x000000B3FC101000L});
    public static final BitSet FOLLOW_rulePropertiesBlock_in_ruleSimplePattern805 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleSimplePattern815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSetHeaderAction_in_ruleActionStatement843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSetBodyAction_in_ruleActionStatement858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleActionStatement871 = new BitSet(new long[]{0x0000000002100000L});
    public static final BitSet FOLLOW_ruleConvertBodyAction_in_ruleActionStatement881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleSetHeaderAction911 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSetHeaderAction920 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleSetHeaderAction929 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSetHeaderAction938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleSetBodyAction965 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleSetBodyAction972 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSetBodyAction981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConvertBodyWithAction_in_ruleConvertBodyAction1010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConvertBodyToAction_in_ruleConvertBodyAction1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleConvertBodyWithAction1048 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_ruleBeanExpr_in_ruleConvertBodyWithAction1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleConvertBodyToAction1086 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleConvertBodyToAction1095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleTransformer1122 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleInterceptors_in_ruleTransformer1132 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleTransformer1143 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_ruleBeanExpr_in_ruleTransformer1152 = new BitSet(new long[]{0x000000A3FC100000L});
    public static final BitSet FOLLOW_ruleToTarget_in_ruleTransformer1163 = new BitSet(new long[]{0x000000A3FC101000L});
    public static final BitSet FOLLOW_12_in_ruleTransformer1173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleChoice1198 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleInterceptors_in_ruleChoice1208 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleChoice1219 = new BitSet(new long[]{0x0000000C00001000L});
    public static final BitSet FOLLOW_ruleWhenClause_in_ruleChoice1228 = new BitSet(new long[]{0x0000000C00001000L});
    public static final BitSet FOLLOW_ruleOtherwiseClause_in_ruleChoice1240 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleChoice1250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleRecipients1275 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleSimplePattern_in_ruleRecipients1284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleDynamicRecipients1311 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleDynamicRecipients1318 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleDynamicRecipients1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rulePipeline1354 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleSimplePattern_in_rulePipeline1363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleFilter1390 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleFilter1399 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleSimplePattern_in_ruleFilter1410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleSplitter1437 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSplitter1446 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleSimplePattern_in_ruleSplitter1457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleResequencer1484 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleResequencer1493 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleSimplePattern_in_ruleResequencer1504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleWhenClause1531 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleWhenClause1540 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleWhenClause1549 = new BitSet(new long[]{0x000000A3FC100000L});
    public static final BitSet FOLLOW_ruleToTarget_in_ruleWhenClause1558 = new BitSet(new long[]{0x000000A3FC101000L});
    public static final BitSet FOLLOW_12_in_ruleWhenClause1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleOtherwiseClause1593 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleOtherwiseClause1600 = new BitSet(new long[]{0x000000A3FC100000L});
    public static final BitSet FOLLOW_ruleToTarget_in_ruleOtherwiseClause1609 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleOtherwiseClause1618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleExpression1646 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleExpression1655 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleExpressionValue_in_ruleExpression1665 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleExpression1675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleExpression1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleExpressionValue1712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rulePropertiesBlock1737 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_rulePropertiesBlock1744 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePropertyDef_in_rulePropertiesBlock1753 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_12_in_rulePropertiesBlock1763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSCOPED_ID_in_rulePropertyDef1790 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_rulePropertyDef1799 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePropertyDef1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTo_in_ruleToTarget1837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProcessExpr_in_ruleToTarget1852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFault_in_ruleToTarget1867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleFault1890 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFault1897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSCOPED_ID1922 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_38_in_ruleSCOPED_ID1930 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSCOPED_ID1937 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_39_in_ruleBEAN1964 = new BitSet(new long[]{0x0000000000000002L});

}