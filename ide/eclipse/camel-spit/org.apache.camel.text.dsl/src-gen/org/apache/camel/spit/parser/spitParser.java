// $ANTLR 3.0 ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g 2008-11-03 15:43:27

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "'route'", "'{'", "'}'", "'import'", "'global'", "'interceptors'", "'interceptor'", "'('", "')'", "'from'", "'to'", "'transformer'", "'choice'", "'recipients'", "'dynamic'", "'pipeline'", "'filter'", "'splitter'", "'resequence'", "'when'", "'otherwise'", "'properties'", "'='", "'fault'", "'.'", "'bean'"
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
            ruleMemo = new HashMap[61+1];
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

                if ( (LA6_0==35) ) {
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
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:234:1: ruleFrom returns [EObject result] : ( ( 'from' ) (temp_Endpoint= ruleEndpoint ) ) ;
    public EObject ruleFrom() throws RecognitionException {
        EObject result = null;
        int ruleFrom_StartIndex = input.index();
        EObject temp_Endpoint = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 8) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:235:4: ( ( ( 'from' ) (temp_Endpoint= ruleEndpoint ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:235:4: ( ( 'from' ) (temp_Endpoint= ruleEndpoint ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "From");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:239:1: ( ( 'from' ) (temp_Endpoint= ruleEndpoint ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:239:2: ( 'from' ) (temp_Endpoint= ruleEndpoint )
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

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:241:1: (temp_Endpoint= ruleEndpoint )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:241:2: temp_Endpoint= ruleEndpoint
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleEndpoint_in_ruleFrom449);
            temp_Endpoint=ruleEndpoint();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_Endpoint;
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
            if ( backtracking>0 ) { memoize(input, 8, ruleFrom_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFrom


    // $ANTLR start ruleTo
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:247:1: ruleTo returns [EObject result] : ( ( 'to' ) (temp_Endpoint= ruleEndpoint ) ) ;
    public EObject ruleTo() throws RecognitionException {
        EObject result = null;
        int ruleTo_StartIndex = input.index();
        EObject temp_Endpoint = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 9) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:248:4: ( ( ( 'to' ) (temp_Endpoint= ruleEndpoint ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:248:4: ( ( 'to' ) (temp_Endpoint= ruleEndpoint ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "To");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:252:1: ( ( 'to' ) (temp_Endpoint= ruleEndpoint ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:252:2: ( 'to' ) (temp_Endpoint= ruleEndpoint )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:252:2: ( 'to' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:252:3: 'to'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,20,FOLLOW_20_in_ruleTo477); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:254:1: (temp_Endpoint= ruleEndpoint )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:254:2: temp_Endpoint= ruleEndpoint
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleEndpoint_in_ruleTo487);
            temp_Endpoint=ruleEndpoint();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_Endpoint;
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
            if ( backtracking>0 ) { memoize(input, 9, ruleTo_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTo


    // $ANTLR start rulePatternExpr
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:260:1: rulePatternExpr returns [EObject result] : (temp_choice= ruleChoice | temp_recipients= ruleRecipients | temp_pipeline= rulePipeline | temp_filter= ruleFilter | temp_splitter= ruleSplitter | temp_resequencer= ruleResequencer | temp_transformer= ruleTransformer | temp_dynamicrecipients= ruleDynamicRecipients );
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
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:261:9: (temp_choice= ruleChoice | temp_recipients= ruleRecipients | temp_pipeline= rulePipeline | temp_filter= ruleFilter | temp_splitter= ruleSplitter | temp_resequencer= ruleResequencer | temp_transformer= ruleTransformer | temp_dynamicrecipients= ruleDynamicRecipients )
            int alt8=8;
            switch ( input.LA(1) ) {
            case 22:
                {
                alt8=1;
                }
                break;
            case 23:
                {
                alt8=2;
                }
                break;
            case 25:
                {
                alt8=3;
                }
                break;
            case 26:
                {
                alt8=4;
                }
                break;
            case 27:
                {
                alt8=5;
                }
                break;
            case 28:
                {
                alt8=6;
                }
                break;
            case 21:
                {
                alt8=7;
                }
                break;
            case 24:
                {
                alt8=8;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("260:1: rulePatternExpr returns [EObject result] : (temp_choice= ruleChoice | temp_recipients= ruleRecipients | temp_pipeline= rulePipeline | temp_filter= ruleFilter | temp_splitter= ruleSplitter | temp_resequencer= ruleResequencer | temp_transformer= ruleTransformer | temp_dynamicrecipients= ruleDynamicRecipients );", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:261:9: temp_choice= ruleChoice
                    {
                    pushFollow(FOLLOW_ruleChoice_in_rulePatternExpr517);
                    temp_choice=ruleChoice();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_choice;
                    }

                    }
                    break;
                case 2 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:261:64: temp_recipients= ruleRecipients
                    {
                    pushFollow(FOLLOW_ruleRecipients_in_rulePatternExpr532);
                    temp_recipients=ruleRecipients();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_recipients;
                    }

                    }
                    break;
                case 3 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:261:131: temp_pipeline= rulePipeline
                    {
                    pushFollow(FOLLOW_rulePipeline_in_rulePatternExpr547);
                    temp_pipeline=rulePipeline();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_pipeline;
                    }

                    }
                    break;
                case 4 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:261:192: temp_filter= ruleFilter
                    {
                    pushFollow(FOLLOW_ruleFilter_in_rulePatternExpr562);
                    temp_filter=ruleFilter();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_filter;
                    }

                    }
                    break;
                case 5 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:261:247: temp_splitter= ruleSplitter
                    {
                    pushFollow(FOLLOW_ruleSplitter_in_rulePatternExpr577);
                    temp_splitter=ruleSplitter();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_splitter;
                    }

                    }
                    break;
                case 6 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:261:308: temp_resequencer= ruleResequencer
                    {
                    pushFollow(FOLLOW_ruleResequencer_in_rulePatternExpr592);
                    temp_resequencer=ruleResequencer();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_resequencer;
                    }

                    }
                    break;
                case 7 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:261:378: temp_transformer= ruleTransformer
                    {
                    pushFollow(FOLLOW_ruleTransformer_in_rulePatternExpr607);
                    temp_transformer=ruleTransformer();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_transformer;
                    }

                    }
                    break;
                case 8 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:261:448: temp_dynamicrecipients= ruleDynamicRecipients
                    {
                    pushFollow(FOLLOW_ruleDynamicRecipients_in_rulePatternExpr622);
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


    // $ANTLR start ruleEndpoint
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:263:1: ruleEndpoint returns [EObject result] : (temp_uri= RULE_STRING ) ;
    public EObject ruleEndpoint() throws RecognitionException {
        EObject result = null;
        int ruleEndpoint_StartIndex = input.index();
        Token temp_uri=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 11) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:264:4: ( (temp_uri= RULE_STRING ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:264:4: (temp_uri= RULE_STRING )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Endpoint");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:268:1: (temp_uri= RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:268:2: temp_uri= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)),line(),start());
            }
            temp_uri=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleEndpoint646); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"uri",convert(temp_uri),false); ptm.ruleFinished(temp_uri,end()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 11, ruleEndpoint_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleEndpoint


    // $ANTLR start ruleProcessExpr
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:272:1: ruleProcessExpr returns [EObject result] : temp_patternorbeanexpr= rulePatternOrBeanExpr ;
    public EObject ruleProcessExpr() throws RecognitionException {
        EObject result = null;
        int ruleProcessExpr_StartIndex = input.index();
        EObject temp_patternorbeanexpr = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 12) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:273:9: (temp_patternorbeanexpr= rulePatternOrBeanExpr )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:273:9: temp_patternorbeanexpr= rulePatternOrBeanExpr
            {
            pushFollow(FOLLOW_rulePatternOrBeanExpr_in_ruleProcessExpr673);
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
            if ( backtracking>0 ) { memoize(input, 12, ruleProcessExpr_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleProcessExpr


    // $ANTLR start rulePatternOrBeanExpr
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:275:1: rulePatternOrBeanExpr returns [EObject result] : (temp_beanexpr= ruleBeanExpr | temp_patternexpr= rulePatternExpr );
    public EObject rulePatternOrBeanExpr() throws RecognitionException {
        EObject result = null;
        int rulePatternOrBeanExpr_StartIndex = input.index();
        EObject temp_beanexpr = null;

        EObject temp_patternexpr = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 13) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:276:9: (temp_beanexpr= ruleBeanExpr | temp_patternexpr= rulePatternExpr )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==35) ) {
                alt9=1;
            }
            else if ( ((LA9_0>=21 && LA9_0<=28)) ) {
                alt9=2;
            }
            else {
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("275:1: rulePatternOrBeanExpr returns [EObject result] : (temp_beanexpr= ruleBeanExpr | temp_patternexpr= rulePatternExpr );", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:276:9: temp_beanexpr= ruleBeanExpr
                    {
                    pushFollow(FOLLOW_ruleBeanExpr_in_rulePatternOrBeanExpr698);
                    temp_beanexpr=ruleBeanExpr();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_beanexpr;
                    }

                    }
                    break;
                case 2 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:276:70: temp_patternexpr= rulePatternExpr
                    {
                    pushFollow(FOLLOW_rulePatternExpr_in_rulePatternOrBeanExpr713);
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
            if ( backtracking>0 ) { memoize(input, 13, rulePatternOrBeanExpr_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePatternOrBeanExpr


    // $ANTLR start ruleBeanExpr
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:278:1: ruleBeanExpr returns [EObject result] : ( (temp_BEAN= ruleBEAN ) (temp_id= RULE_ID ) (temp_method= RULE_ID )? ) ;
    public EObject ruleBeanExpr() throws RecognitionException {
        EObject result = null;
        int ruleBeanExpr_StartIndex = input.index();
        Token temp_id=null;
        Token temp_method=null;
        EObject temp_BEAN = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 14) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:279:4: ( ( (temp_BEAN= ruleBEAN ) (temp_id= RULE_ID ) (temp_method= RULE_ID )? ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:279:4: ( (temp_BEAN= ruleBEAN ) (temp_id= RULE_ID ) (temp_method= RULE_ID )? )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "BeanExpr");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:283:1: ( (temp_BEAN= ruleBEAN ) (temp_id= RULE_ID ) (temp_method= RULE_ID )? )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:283:2: (temp_BEAN= ruleBEAN ) (temp_id= RULE_ID ) (temp_method= RULE_ID )?
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:283:2: (temp_BEAN= ruleBEAN )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:283:3: temp_BEAN= ruleBEAN
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleBEAN_in_ruleBeanExpr739);
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

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:287:1: (temp_id= RULE_ID )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:287:2: temp_id= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_id=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleBeanExpr751); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"id",convert(temp_id),false); ptm.ruleFinished(temp_id,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:290:1: (temp_method= RULE_ID )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ID) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:290:2: temp_method= RULE_ID
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(2)),line(),start());
                    }
                    temp_method=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleBeanExpr762); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 14, ruleBeanExpr_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleBeanExpr


    // $ANTLR start ruleSimplePattern
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:295:1: ruleSimplePattern returns [EObject result] : ( (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_targets= ruleToTarget )+ (temp_PropertiesBlock= rulePropertiesBlock )? ( '}' ) ) ;
    public EObject ruleSimplePattern() throws RecognitionException {
        EObject result = null;
        int ruleSimplePattern_StartIndex = input.index();
        EObject temp_Interceptors = null;

        EObject temp_targets = null;

        EObject temp_PropertiesBlock = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 15) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:296:4: ( ( (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_targets= ruleToTarget )+ (temp_PropertiesBlock= rulePropertiesBlock )? ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:296:4: ( (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_targets= ruleToTarget )+ (temp_PropertiesBlock= rulePropertiesBlock )? ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "SimplePattern");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:300:1: ( (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_targets= ruleToTarget )+ (temp_PropertiesBlock= rulePropertiesBlock )? ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:300:2: (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_targets= ruleToTarget )+ (temp_PropertiesBlock= rulePropertiesBlock )? ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:300:2: (temp_Interceptors= ruleInterceptors )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==15) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:300:3: temp_Interceptors= ruleInterceptors
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(0)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleInterceptors_in_ruleSimplePattern793);
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

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:304:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:304:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleSimplePattern804); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:306:1: (temp_targets= ruleToTarget )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=20 && LA12_0<=28)||LA12_0==33||LA12_0==35) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:306:2: temp_targets= ruleToTarget
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(2)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleToTarget_in_ruleSimplePattern813);
            	    temp_targets=ruleToTarget();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"targets",convert(temp_targets),false); ptm.ruleFinished(temp_targets,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:309:1: (temp_PropertiesBlock= rulePropertiesBlock )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==31) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:309:2: temp_PropertiesBlock= rulePropertiesBlock
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(3)),line(),start());
                    }
                    pushFollow(FOLLOW_rulePropertiesBlock_in_ruleSimplePattern826);
                    temp_PropertiesBlock=rulePropertiesBlock();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_PropertiesBlock;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }
                    break;

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:313:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:313:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(4)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleSimplePattern837); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 15, ruleSimplePattern_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSimplePattern


    // $ANTLR start ruleTransformer
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:317:1: ruleTransformer returns [EObject result] : ( ( 'transformer' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_beanref= ruleBeanExpr ) (temp_target= ruleToTarget )+ ( '}' ) ) ;
    public EObject ruleTransformer() throws RecognitionException {
        EObject result = null;
        int ruleTransformer_StartIndex = input.index();
        EObject temp_Interceptors = null;

        EObject temp_beanref = null;

        EObject temp_target = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 16) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:318:4: ( ( ( 'transformer' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_beanref= ruleBeanExpr ) (temp_target= ruleToTarget )+ ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:318:4: ( ( 'transformer' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_beanref= ruleBeanExpr ) (temp_target= ruleToTarget )+ ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Transformer");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:322:1: ( ( 'transformer' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_beanref= ruleBeanExpr ) (temp_target= ruleToTarget )+ ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:322:2: ( 'transformer' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_beanref= ruleBeanExpr ) (temp_target= ruleToTarget )+ ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:322:2: ( 'transformer' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:322:3: 'transformer'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,21,FOLLOW_21_in_ruleTransformer862); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:324:1: (temp_Interceptors= ruleInterceptors )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==15) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:324:2: temp_Interceptors= ruleInterceptors
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleInterceptors_in_ruleTransformer872);
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

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:328:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:328:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleTransformer883); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:330:1: (temp_beanref= ruleBeanExpr )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:330:2: temp_beanref= ruleBeanExpr
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            pushFollow(FOLLOW_ruleBeanExpr_in_ruleTransformer892);
            temp_beanref=ruleBeanExpr();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"beanref",convert(temp_beanref),false); ptm.ruleFinished(temp_beanref,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:333:1: (temp_target= ruleToTarget )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=20 && LA15_0<=28)||LA15_0==33||LA15_0==35) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:333:2: temp_target= ruleToTarget
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(4)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleToTarget_in_ruleTransformer903);
            	    temp_target=ruleToTarget();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:336:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:336:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(5)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleTransformer913); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 16, ruleTransformer_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTransformer


    // $ANTLR start ruleChoice
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:340:1: ruleChoice returns [EObject result] : ( ( 'choice' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_when= ruleWhenClause )* (temp_otherwise= ruleOtherwiseClause )? ( '}' ) ) ;
    public EObject ruleChoice() throws RecognitionException {
        EObject result = null;
        int ruleChoice_StartIndex = input.index();
        EObject temp_Interceptors = null;

        EObject temp_when = null;

        EObject temp_otherwise = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 17) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:341:4: ( ( ( 'choice' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_when= ruleWhenClause )* (temp_otherwise= ruleOtherwiseClause )? ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:341:4: ( ( 'choice' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_when= ruleWhenClause )* (temp_otherwise= ruleOtherwiseClause )? ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Choice");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:345:1: ( ( 'choice' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_when= ruleWhenClause )* (temp_otherwise= ruleOtherwiseClause )? ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:345:2: ( 'choice' ) (temp_Interceptors= ruleInterceptors )? ( '{' ) (temp_when= ruleWhenClause )* (temp_otherwise= ruleOtherwiseClause )? ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:345:2: ( 'choice' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:345:3: 'choice'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,22,FOLLOW_22_in_ruleChoice938); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:347:1: (temp_Interceptors= ruleInterceptors )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==15) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:347:2: temp_Interceptors= ruleInterceptors
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleInterceptors_in_ruleChoice948);
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

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:351:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:351:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleChoice959); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:353:1: (temp_when= ruleWhenClause )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==29) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:353:2: temp_when= ruleWhenClause
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(3)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleWhenClause_in_ruleChoice968);
            	    temp_when=ruleWhenClause();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"when",convert(temp_when),false); ptm.ruleFinished(temp_when,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:356:1: (temp_otherwise= ruleOtherwiseClause )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==30) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:356:2: temp_otherwise= ruleOtherwiseClause
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(4)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleOtherwiseClause_in_ruleChoice980);
                    temp_otherwise=ruleOtherwiseClause();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"otherwise",convert(temp_otherwise),false); ptm.ruleFinished(temp_otherwise,end()); 
                    }

                    }
                    break;

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:359:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:359:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(5)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleChoice990); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 17, ruleChoice_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleChoice


    // $ANTLR start ruleRecipients
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:363:1: ruleRecipients returns [EObject result] : ( ( 'recipients' ) (temp_SimplePattern= ruleSimplePattern ) ) ;
    public EObject ruleRecipients() throws RecognitionException {
        EObject result = null;
        int ruleRecipients_StartIndex = input.index();
        EObject temp_SimplePattern = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 18) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:364:4: ( ( ( 'recipients' ) (temp_SimplePattern= ruleSimplePattern ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:364:4: ( ( 'recipients' ) (temp_SimplePattern= ruleSimplePattern ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Recipients");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:368:1: ( ( 'recipients' ) (temp_SimplePattern= ruleSimplePattern ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:368:2: ( 'recipients' ) (temp_SimplePattern= ruleSimplePattern )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:368:2: ( 'recipients' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:368:3: 'recipients'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(16)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,23,FOLLOW_23_in_ruleRecipients1015); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:370:1: (temp_SimplePattern= ruleSimplePattern )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:370:2: temp_SimplePattern= ruleSimplePattern
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(16)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleSimplePattern_in_ruleRecipients1025);
            temp_SimplePattern=ruleSimplePattern();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_SimplePattern;
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
            if ( backtracking>0 ) { memoize(input, 18, ruleRecipients_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleRecipients


    // $ANTLR start ruleDynamicRecipients
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:376:1: ruleDynamicRecipients returns [EObject result] : ( ( 'dynamic' ) ( 'recipients' ) (temp_Expression= ruleExpression ) ) ;
    public EObject ruleDynamicRecipients() throws RecognitionException {
        EObject result = null;
        int ruleDynamicRecipients_StartIndex = input.index();
        EObject temp_Expression = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 19) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:377:4: ( ( ( 'dynamic' ) ( 'recipients' ) (temp_Expression= ruleExpression ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:377:4: ( ( 'dynamic' ) ( 'recipients' ) (temp_Expression= ruleExpression ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "DynamicRecipients");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:381:1: ( ( 'dynamic' ) ( 'recipients' ) (temp_Expression= ruleExpression ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:381:2: ( 'dynamic' ) ( 'recipients' ) (temp_Expression= ruleExpression )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:381:2: ( 'dynamic' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:381:3: 'dynamic'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,24,FOLLOW_24_in_ruleDynamicRecipients1053); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:383:1: ( 'recipients' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:383:2: 'recipients'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,23,FOLLOW_23_in_ruleDynamicRecipients1060); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:385:1: (temp_Expression= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:385:2: temp_Expression= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleDynamicRecipients1070);
            temp_Expression=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_Expression;
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
            if ( backtracking>0 ) { memoize(input, 19, ruleDynamicRecipients_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleDynamicRecipients


    // $ANTLR start rulePipeline
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:391:1: rulePipeline returns [EObject result] : ( ( 'pipeline' ) (temp_SimplePattern= ruleSimplePattern ) ) ;
    public EObject rulePipeline() throws RecognitionException {
        EObject result = null;
        int rulePipeline_StartIndex = input.index();
        EObject temp_SimplePattern = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 20) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:392:4: ( ( ( 'pipeline' ) (temp_SimplePattern= ruleSimplePattern ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:392:4: ( ( 'pipeline' ) (temp_SimplePattern= ruleSimplePattern ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Pipeline");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:396:1: ( ( 'pipeline' ) (temp_SimplePattern= ruleSimplePattern ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:396:2: ( 'pipeline' ) (temp_SimplePattern= ruleSimplePattern )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:396:2: ( 'pipeline' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:396:3: 'pipeline'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(18)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,25,FOLLOW_25_in_rulePipeline1098); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:398:1: (temp_SimplePattern= ruleSimplePattern )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:398:2: temp_SimplePattern= ruleSimplePattern
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(18)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleSimplePattern_in_rulePipeline1108);
            temp_SimplePattern=ruleSimplePattern();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_SimplePattern;
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
            if ( backtracking>0 ) { memoize(input, 20, rulePipeline_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePipeline


    // $ANTLR start ruleFilter
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:404:1: ruleFilter returns [EObject result] : ( ( 'filter' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) ) ;
    public EObject ruleFilter() throws RecognitionException {
        EObject result = null;
        int ruleFilter_StartIndex = input.index();
        EObject temp_expr = null;

        EObject temp_SimplePattern = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 21) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:405:4: ( ( ( 'filter' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:405:4: ( ( 'filter' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Filter");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:409:1: ( ( 'filter' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:409:2: ( 'filter' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:409:2: ( 'filter' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:409:3: 'filter'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,26,FOLLOW_26_in_ruleFilter1136); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:411:1: (temp_expr= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:411:2: temp_expr= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleFilter1145);
            temp_expr=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:414:1: (temp_SimplePattern= ruleSimplePattern )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:414:2: temp_SimplePattern= ruleSimplePattern
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleSimplePattern_in_ruleFilter1157);
            temp_SimplePattern=ruleSimplePattern();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_SimplePattern;
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
            if ( backtracking>0 ) { memoize(input, 21, ruleFilter_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFilter


    // $ANTLR start ruleSplitter
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:420:1: ruleSplitter returns [EObject result] : ( ( 'splitter' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) ) ;
    public EObject ruleSplitter() throws RecognitionException {
        EObject result = null;
        int ruleSplitter_StartIndex = input.index();
        EObject temp_expr = null;

        EObject temp_SimplePattern = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 22) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:421:4: ( ( ( 'splitter' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:421:4: ( ( 'splitter' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Splitter");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:425:1: ( ( 'splitter' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:425:2: ( 'splitter' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:425:2: ( 'splitter' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:425:3: 'splitter'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,27,FOLLOW_27_in_ruleSplitter1185); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:427:1: (temp_expr= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:427:2: temp_expr= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleSplitter1194);
            temp_expr=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:430:1: (temp_SimplePattern= ruleSimplePattern )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:430:2: temp_SimplePattern= ruleSimplePattern
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleSimplePattern_in_ruleSplitter1206);
            temp_SimplePattern=ruleSimplePattern();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_SimplePattern;
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
            if ( backtracking>0 ) { memoize(input, 22, ruleSplitter_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSplitter


    // $ANTLR start ruleResequencer
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:436:1: ruleResequencer returns [EObject result] : ( ( 'resequence' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) ) ;
    public EObject ruleResequencer() throws RecognitionException {
        EObject result = null;
        int ruleResequencer_StartIndex = input.index();
        EObject temp_expr = null;

        EObject temp_SimplePattern = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 23) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:437:4: ( ( ( 'resequence' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:437:4: ( ( 'resequence' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Resequencer");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:441:1: ( ( 'resequence' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:441:2: ( 'resequence' ) (temp_expr= ruleExpression ) (temp_SimplePattern= ruleSimplePattern )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:441:2: ( 'resequence' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:441:3: 'resequence'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,28,FOLLOW_28_in_ruleResequencer1234); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:443:1: (temp_expr= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:443:2: temp_expr= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleResequencer1243);
            temp_expr=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:446:1: (temp_SimplePattern= ruleSimplePattern )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:446:2: temp_SimplePattern= ruleSimplePattern
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleSimplePattern_in_ruleResequencer1255);
            temp_SimplePattern=ruleSimplePattern();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_SimplePattern;
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
            if ( backtracking>0 ) { memoize(input, 23, ruleResequencer_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleResequencer


    // $ANTLR start ruleWhenClause
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:452:1: ruleWhenClause returns [EObject result] : ( ( 'when' ) (temp_expr= ruleExpression ) ( '{' ) (temp_target= ruleToTarget )+ ( '}' ) ) ;
    public EObject ruleWhenClause() throws RecognitionException {
        EObject result = null;
        int ruleWhenClause_StartIndex = input.index();
        EObject temp_expr = null;

        EObject temp_target = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 24) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:453:4: ( ( ( 'when' ) (temp_expr= ruleExpression ) ( '{' ) (temp_target= ruleToTarget )+ ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:453:4: ( ( 'when' ) (temp_expr= ruleExpression ) ( '{' ) (temp_target= ruleToTarget )+ ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "WhenClause");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:457:1: ( ( 'when' ) (temp_expr= ruleExpression ) ( '{' ) (temp_target= ruleToTarget )+ ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:457:2: ( 'when' ) (temp_expr= ruleExpression ) ( '{' ) (temp_target= ruleToTarget )+ ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:457:2: ( 'when' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:457:3: 'when'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,29,FOLLOW_29_in_ruleWhenClause1283); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:459:1: (temp_expr= ruleExpression )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:459:2: temp_expr= ruleExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleWhenClause1292);
            temp_expr=ruleExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:462:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:462:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleWhenClause1301); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:464:1: (temp_target= ruleToTarget )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=20 && LA19_0<=28)||LA19_0==33||LA19_0==35) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:464:2: temp_target= ruleToTarget
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(3)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleToTarget_in_ruleWhenClause1310);
            	    temp_target=ruleToTarget();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:467:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:467:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(4)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleWhenClause1320); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 24, ruleWhenClause_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleWhenClause


    // $ANTLR start ruleOtherwiseClause
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:471:1: ruleOtherwiseClause returns [EObject result] : ( ( 'otherwise' ) ( '{' ) (temp_target= ruleToTarget ) ( '}' ) ) ;
    public EObject ruleOtherwiseClause() throws RecognitionException {
        EObject result = null;
        int ruleOtherwiseClause_StartIndex = input.index();
        EObject temp_target = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 25) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:472:4: ( ( ( 'otherwise' ) ( '{' ) (temp_target= ruleToTarget ) ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:472:4: ( ( 'otherwise' ) ( '{' ) (temp_target= ruleToTarget ) ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "OtherwiseClause");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:476:1: ( ( 'otherwise' ) ( '{' ) (temp_target= ruleToTarget ) ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:476:2: ( 'otherwise' ) ( '{' ) (temp_target= ruleToTarget ) ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:476:2: ( 'otherwise' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:476:3: 'otherwise'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(23)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,30,FOLLOW_30_in_ruleOtherwiseClause1345); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:478:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:478:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(23)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleOtherwiseClause1352); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:480:1: (temp_target= ruleToTarget )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:480:2: temp_target= ruleToTarget
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(23)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleToTarget_in_ruleOtherwiseClause1361);
            temp_target=ruleToTarget();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:483:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:483:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(23)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleOtherwiseClause1370); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 25, ruleOtherwiseClause_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleOtherwiseClause


    // $ANTLR start ruleExpression
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:487:1: ruleExpression returns [EObject result] : ( (temp_lang= RULE_ID ) ( '(' ) (temp_value= RULE_STRING ) ( ')' ) ) ;
    public EObject ruleExpression() throws RecognitionException {
        EObject result = null;
        int ruleExpression_StartIndex = input.index();
        Token temp_lang=null;
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 26) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:488:4: ( ( (temp_lang= RULE_ID ) ( '(' ) (temp_value= RULE_STRING ) ( ')' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:488:4: ( (temp_lang= RULE_ID ) ( '(' ) (temp_value= RULE_STRING ) ( ')' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Expression");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:492:1: ( (temp_lang= RULE_ID ) ( '(' ) (temp_value= RULE_STRING ) ( ')' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:492:2: (temp_lang= RULE_ID ) ( '(' ) (temp_value= RULE_STRING ) ( ')' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:492:2: (temp_lang= RULE_ID )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:492:3: temp_lang= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_lang=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleExpression1397); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"lang",convert(temp_lang),false); ptm.ruleFinished(temp_lang,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:495:1: ( '(' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:495:2: '('
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,17,FOLLOW_17_in_ruleExpression1406); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:497:1: (temp_value= RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:497:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleExpression1415); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:500:1: ( ')' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:500:2: ')'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,18,FOLLOW_18_in_ruleExpression1424); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 26, ruleExpression_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleExpression


    // $ANTLR start rulePropertiesBlock
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:504:1: rulePropertiesBlock returns [EObject result] : ( ( 'properties' ) ( '{' ) (temp_properties= rulePropertyDef )+ ( '}' ) ) ;
    public EObject rulePropertiesBlock() throws RecognitionException {
        EObject result = null;
        int rulePropertiesBlock_StartIndex = input.index();
        EObject temp_properties = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 27) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:505:4: ( ( ( 'properties' ) ( '{' ) (temp_properties= rulePropertyDef )+ ( '}' ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:505:4: ( ( 'properties' ) ( '{' ) (temp_properties= rulePropertyDef )+ ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "PropertiesBlock");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:509:1: ( ( 'properties' ) ( '{' ) (temp_properties= rulePropertyDef )+ ( '}' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:509:2: ( 'properties' ) ( '{' ) (temp_properties= rulePropertyDef )+ ( '}' )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:509:2: ( 'properties' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:509:3: 'properties'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,31,FOLLOW_31_in_rulePropertiesBlock1449); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:511:1: ( '{' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:511:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,11,FOLLOW_11_in_rulePropertiesBlock1456); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:513:1: (temp_properties= rulePropertyDef )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:513:2: temp_properties= rulePropertyDef
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(2)),line(),start());
            	    }
            	    pushFollow(FOLLOW_rulePropertyDef_in_rulePropertiesBlock1465);
            	    temp_properties=rulePropertyDef();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"properties",convert(temp_properties),false); ptm.ruleFinished(temp_properties,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:516:1: ( '}' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:516:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,12,FOLLOW_12_in_rulePropertiesBlock1475); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 27, rulePropertiesBlock_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePropertiesBlock


    // $ANTLR start rulePropertyDef
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:520:1: rulePropertyDef returns [EObject result] : ( (temp_name= ruleSCOPED_ID ) ( '=' ) (temp_value= RULE_STRING ) ) ;
    public EObject rulePropertyDef() throws RecognitionException {
        EObject result = null;
        int rulePropertyDef_StartIndex = input.index();
        Token temp_value=null;
        EObject temp_name = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 28) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:521:4: ( ( (temp_name= ruleSCOPED_ID ) ( '=' ) (temp_value= RULE_STRING ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:521:4: ( (temp_name= ruleSCOPED_ID ) ( '=' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "PropertyDef");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:525:1: ( (temp_name= ruleSCOPED_ID ) ( '=' ) (temp_value= RULE_STRING ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:525:2: (temp_name= ruleSCOPED_ID ) ( '=' ) (temp_value= RULE_STRING )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:525:2: (temp_name= ruleSCOPED_ID )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:525:3: temp_name= ruleSCOPED_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleSCOPED_ID_in_rulePropertyDef1502);
            temp_name=ruleSCOPED_ID();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:528:1: ( '=' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:528:2: '='
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,32,FOLLOW_32_in_rulePropertyDef1511); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:530:1: (temp_value= RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:530:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePropertyDef1520); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 28, rulePropertyDef_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePropertyDef


    // $ANTLR start ruleToTarget
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:535:1: ruleToTarget returns [EObject result] : (temp_to= ruleTo | temp_processexpr= ruleProcessExpr | temp_fault= ruleFault );
    public EObject ruleToTarget() throws RecognitionException {
        EObject result = null;
        int ruleToTarget_StartIndex = input.index();
        EObject temp_to = null;

        EObject temp_processexpr = null;

        EObject temp_fault = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 29) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:536:9: (temp_to= ruleTo | temp_processexpr= ruleProcessExpr | temp_fault= ruleFault )
            int alt21=3;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt21=1;
                }
                break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 35:
                {
                alt21=2;
                }
                break;
            case 33:
                {
                alt21=3;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("535:1: ruleToTarget returns [EObject result] : (temp_to= ruleTo | temp_processexpr= ruleProcessExpr | temp_fault= ruleFault );", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:536:9: temp_to= ruleTo
                    {
                    pushFollow(FOLLOW_ruleTo_in_ruleToTarget1549);
                    temp_to=ruleTo();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_to;
                    }

                    }
                    break;
                case 2 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:536:52: temp_processexpr= ruleProcessExpr
                    {
                    pushFollow(FOLLOW_ruleProcessExpr_in_ruleToTarget1564);
                    temp_processexpr=ruleProcessExpr();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_processexpr;
                    }

                    }
                    break;
                case 3 :
                    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:536:122: temp_fault= ruleFault
                    {
                    pushFollow(FOLLOW_ruleFault_in_ruleToTarget1579);
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
            if ( backtracking>0 ) { memoize(input, 29, ruleToTarget_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleToTarget


    // $ANTLR start ruleFault
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:538:1: ruleFault returns [EObject result] : ( ( 'fault' ) ( RULE_STRING ) ) ;
    public EObject ruleFault() throws RecognitionException {
        EObject result = null;
        int ruleFault_StartIndex = input.index();
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 30) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:539:4: ( ( ( 'fault' ) ( RULE_STRING ) ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:539:4: ( ( 'fault' ) ( RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Fault");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:543:1: ( ( 'fault' ) ( RULE_STRING ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:543:2: ( 'fault' ) ( RULE_STRING )
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:543:2: ( 'fault' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:543:3: 'fault'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(28)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,33,FOLLOW_33_in_ruleFault1602); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:545:1: ( RULE_STRING )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:545:2: RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(28)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFault1609); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 30, ruleFault_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFault


    // $ANTLR start ruleSCOPED_ID
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:549:1: ruleSCOPED_ID returns [EObject result] : ( ( RULE_ID ) ( ( '.' ) ( RULE_ID ) )* ) ;
    public EObject ruleSCOPED_ID() throws RecognitionException {
        EObject result = null;
        int ruleSCOPED_ID_StartIndex = input.index();
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 31) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:550:4: ( ( ( RULE_ID ) ( ( '.' ) ( RULE_ID ) )* ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:550:4: ( ( RULE_ID ) ( ( '.' ) ( RULE_ID ) )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "SCOPED_ID");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:554:1: ( ( RULE_ID ) ( ( '.' ) ( RULE_ID ) )* )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:554:2: ( RULE_ID ) ( ( '.' ) ( RULE_ID ) )*
            {
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:554:2: ( RULE_ID )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:554:3: RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSCOPED_ID1634); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:556:1: ( ( '.' ) ( RULE_ID ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==34) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:556:2: ( '.' ) ( RULE_ID )
            	    {
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:556:2: ( '.' )
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:556:3: '.'
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            	    }
            	    match(input,34,FOLLOW_34_in_ruleSCOPED_ID1642); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }

            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:558:1: ( RULE_ID )
            	    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:558:2: RULE_ID
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSCOPED_ID1649); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
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
            if ( backtracking>0 ) { memoize(input, 31, ruleSCOPED_ID_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSCOPED_ID


    // $ANTLR start ruleBEAN
    // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:563:1: ruleBEAN returns [EObject result] : ( 'bean' ) ;
    public EObject ruleBEAN() throws RecognitionException {
        EObject result = null;
        int ruleBEAN_StartIndex = input.index();
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 32) ) { return result; }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:564:4: ( ( 'bean' ) )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:564:4: ( 'bean' )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "BEAN");
              				ptm.setModelElement(result);
              			 
            }
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:568:1: ( 'bean' )
            // ..//org.apache.camel.text.dsl/src-gen//org/apache/camel/spit/parser/spit.g:568:2: 'bean'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(30)).eContents().get(1)),line(),start());
            }
            match(input,35,FOLLOW_35_in_ruleBEAN1676); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 32, ruleBEAN_StartIndex); }
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
    public static final BitSet FOLLOW_11_in_ruleRoute200 = new BitSet(new long[]{0x000000081FE00000L});
    public static final BitSet FOLLOW_ruleProcessExpr_in_ruleRoute209 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleRoute218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleImport243 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleImport253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleGlobalInterceptorDecl281 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleGlobalInterceptorDecl288 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGlobalInterceptorDecl297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleInterceptorChainDef325 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInterceptorChainDef334 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleInterceptorChainDef343 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_ruleBeanExpr_in_ruleInterceptorChainDef352 = new BitSet(new long[]{0x0000000800001000L});
    public static final BitSet FOLLOW_12_in_ruleInterceptorChainDef362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleInterceptors387 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleInterceptors394 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInterceptors403 = new BitSet(new long[]{0x0000000000040010L});
    public static final BitSet FOLLOW_18_in_ruleInterceptors414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleFrom439 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleEndpoint_in_ruleFrom449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleTo477 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleEndpoint_in_ruleTo487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChoice_in_rulePatternExpr517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRecipients_in_rulePatternExpr532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePipeline_in_rulePatternExpr547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFilter_in_rulePatternExpr562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSplitter_in_rulePatternExpr577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResequencer_in_rulePatternExpr592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTransformer_in_rulePatternExpr607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDynamicRecipients_in_rulePatternExpr622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleEndpoint646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePatternOrBeanExpr_in_ruleProcessExpr673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBeanExpr_in_rulePatternOrBeanExpr698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePatternExpr_in_rulePatternOrBeanExpr713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBEAN_in_ruleBeanExpr739 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleBeanExpr751 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleBeanExpr762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterceptors_in_ruleSimplePattern793 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleSimplePattern804 = new BitSet(new long[]{0x0000000A1FF00000L});
    public static final BitSet FOLLOW_ruleToTarget_in_ruleSimplePattern813 = new BitSet(new long[]{0x0000000A9FF01000L});
    public static final BitSet FOLLOW_rulePropertiesBlock_in_ruleSimplePattern826 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleSimplePattern837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleTransformer862 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleInterceptors_in_ruleTransformer872 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleTransformer883 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_ruleBeanExpr_in_ruleTransformer892 = new BitSet(new long[]{0x0000000A1FF00000L});
    public static final BitSet FOLLOW_ruleToTarget_in_ruleTransformer903 = new BitSet(new long[]{0x0000000A1FF01000L});
    public static final BitSet FOLLOW_12_in_ruleTransformer913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleChoice938 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleInterceptors_in_ruleChoice948 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleChoice959 = new BitSet(new long[]{0x0000000060001000L});
    public static final BitSet FOLLOW_ruleWhenClause_in_ruleChoice968 = new BitSet(new long[]{0x0000000060001000L});
    public static final BitSet FOLLOW_ruleOtherwiseClause_in_ruleChoice980 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleChoice990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleRecipients1015 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleSimplePattern_in_ruleRecipients1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleDynamicRecipients1053 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleDynamicRecipients1060 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleDynamicRecipients1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rulePipeline1098 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleSimplePattern_in_rulePipeline1108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleFilter1136 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleFilter1145 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleSimplePattern_in_ruleFilter1157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleSplitter1185 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSplitter1194 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleSimplePattern_in_ruleSplitter1206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleResequencer1234 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleResequencer1243 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_ruleSimplePattern_in_ruleResequencer1255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleWhenClause1283 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleWhenClause1292 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleWhenClause1301 = new BitSet(new long[]{0x0000000A1FF00000L});
    public static final BitSet FOLLOW_ruleToTarget_in_ruleWhenClause1310 = new BitSet(new long[]{0x0000000A1FF01000L});
    public static final BitSet FOLLOW_12_in_ruleWhenClause1320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleOtherwiseClause1345 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleOtherwiseClause1352 = new BitSet(new long[]{0x0000000A1FF00000L});
    public static final BitSet FOLLOW_ruleToTarget_in_ruleOtherwiseClause1361 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleOtherwiseClause1370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleExpression1397 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleExpression1406 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleExpression1415 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleExpression1424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rulePropertiesBlock1449 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_rulePropertiesBlock1456 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePropertyDef_in_rulePropertiesBlock1465 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_12_in_rulePropertiesBlock1475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSCOPED_ID_in_rulePropertyDef1502 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_rulePropertyDef1511 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePropertyDef1520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTo_in_ruleToTarget1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProcessExpr_in_ruleToTarget1564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFault_in_ruleToTarget1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleFault1602 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFault1609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSCOPED_ID1634 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_ruleSCOPED_ID1642 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSCOPED_ID1649 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_35_in_ruleBEAN1676 = new BitSet(new long[]{0x0000000000000002L});

}