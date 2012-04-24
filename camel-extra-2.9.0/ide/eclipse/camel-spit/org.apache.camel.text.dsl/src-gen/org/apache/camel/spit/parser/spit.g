grammar spit;
 options{backtrack=true; memoize=true;} 

@lexer::header {
package org.apache.camel.spit.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;

}

@parser::header {
package org.apache.camel.spit.parser;

import org.eclipse.emf.ecore.EObject;

import org.openarchitectureware.xtext.parser.impl.AntlrUtil;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.impl.EcoreModelFactory;
import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.model.ParseTreeManager;
import org.openarchitectureware.xtext.parser.parsetree.Node;

import org.apache.camel.spit.MetaModelRegistration;

}
@lexer::members {
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

@parser::members {

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

}


parse returns [Node r]:
	 result=ruleFile EOF
{ptm.ruleFinished(result,end());$r = ptm.getCurrent();};

ruleFile returns [EObject result] :
			{
				$result = factory.create("", "File");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(0)),line(),start());}temp_imports=ruleImport {factory.add($result,"imports",convert(temp_imports),false); ptm.ruleFinished(temp_imports,end()); }
)*

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(1)),line(),start());}temp_interceptorDecls=ruleGlobalInterceptorDecl {factory.add($result,"interceptorDecls",convert(temp_interceptorDecls),false); ptm.ruleFinished(temp_interceptorDecls,end()); }
)*

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(2)),line(),start());}temp_routes=ruleRoute {factory.add($result,"routes",convert(temp_routes),false); ptm.ruleFinished(temp_routes,end()); }
)*

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(3)),line(),start());}temp_interceptorDefs=ruleInterceptorChainDef {factory.add($result,"interceptorDefs",convert(temp_interceptorDefs),false); ptm.ruleFinished(temp_interceptorDefs,end()); }
)*
)
;

ruleRoute returns [EObject result] :
			{
				$result = factory.create("", "Route");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}'route'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(2)),line(),start());}temp_from=ruleFrom {factory.set($result,"from",convert(temp_from),false); ptm.ruleFinished(temp_from,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(3)),line(),start());}
temp_Interceptors=ruleInterceptors {$result=temp_Interceptors;}
{ptm.ruleFinished(getLastToken(),end());})?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(4)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(5)),line(),start());}temp_process=ruleProcessExpr {factory.set($result,"process",convert(temp_process),false); ptm.ruleFinished(temp_process,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(6)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleImport returns [EObject result] :
			{
				$result = factory.create("", "Import");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(0)),line(),start());}'import'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(1)),line(),start());}temp_location= RULE_STRING
 {factory.set($result,"location",convert(temp_location),false); ptm.ruleFinished(temp_location,end()); }
)
)
;

ruleGlobalInterceptorDecl returns [EObject result] :
			{
				$result = factory.create("", "GlobalInterceptorDecl");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(0)),line(),start());}'global'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(1)),line(),start());}'interceptors'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(2)),line(),start());}temp_interceptorName=RULE_ID
 {factory.set($result,"interceptorName",convert(temp_interceptorName),true); ptm.ruleFinished(temp_interceptorName,end()); }
)
)
;

ruleInterceptorChainDef returns [EObject result] :
			{
				$result = factory.create("", "InterceptorChainDef");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(0)),line(),start());}'interceptor'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(1)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(2)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(3)),line(),start());}temp_chain=ruleBeanExpr {factory.add($result,"chain",convert(temp_chain),false); ptm.ruleFinished(temp_chain,end()); }
)+

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(4)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleInterceptors returns [EObject result] :
			{
				$result = factory.create("", "Interceptors");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(0)),line(),start());}'interceptors'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)),line(),start());}'('{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)),line(),start());}temp_interceptorName=RULE_ID
 {factory.add($result,"interceptorName",convert(temp_interceptorName),true); ptm.ruleFinished(temp_interceptorName,end()); }
)+

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(3)),line(),start());}')'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleFrom returns [EObject result] :
			{
				$result = factory.create("", "From");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(0)),line(),start());}'from'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(1)),line(),start());}temp_uri=RULE_STRING {factory.set($result,"uri",convert(temp_uri),false); ptm.ruleFinished(temp_uri,end()); }
)
)
;

ruleTo returns [EObject result] :
			{
				$result = factory.create("", "To");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(0)),line(),start());}'to'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)),line(),start());}temp_uri=RULE_STRING {factory.set($result,"uri",convert(temp_uri),false); ptm.ruleFinished(temp_uri,end()); }
)
)
;

rulePatternExpr returns [EObject result] :
        temp_choice=ruleChoice {$result=temp_choice;}	|        temp_recipients=ruleRecipients {$result=temp_recipients;}	|        temp_pipeline=rulePipeline {$result=temp_pipeline;}	|        temp_filter=ruleFilter {$result=temp_filter;}	|        temp_splitter=ruleSplitter {$result=temp_splitter;}	|        temp_resequencer=ruleResequencer {$result=temp_resequencer;}	|        temp_transformer=ruleTransformer {$result=temp_transformer;}	|        temp_dynamicrecipients=ruleDynamicRecipients {$result=temp_dynamicrecipients;}	;

ruleProcessExpr returns [EObject result] :
        temp_patternorbeanexpr=rulePatternOrBeanExpr {$result=temp_patternorbeanexpr;}	;

rulePatternOrBeanExpr returns [EObject result] :
        temp_beanexpr=ruleBeanExpr {$result=temp_beanexpr;}	|        temp_patternexpr=rulePatternExpr {$result=temp_patternexpr;}	;

ruleBeanExpr returns [EObject result] :
			{
				$result = factory.create("", "BeanExpr");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(0)),line(),start());}
temp_BEAN=ruleBEAN {$result=temp_BEAN;}
{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(1)),line(),start());}temp_id=RULE_ID {factory.set($result,"id",convert(temp_id),false); ptm.ruleFinished(temp_id,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(2)),line(),start());}temp_method=RULE_ID {factory.set($result,"method",convert(temp_method),false); ptm.ruleFinished(temp_method,end()); }
)?
)
;

ruleSimplePattern returns [EObject result] :
			{
				$result = factory.create("", "SimplePattern");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(0)),line(),start());}temp_interceptors=ruleInterceptors {factory.set($result,"interceptors",convert(temp_interceptors),false); ptm.ruleFinished(temp_interceptors,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(1)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(2)),line(),start());}temp_actions=ruleActionStatement {factory.add($result,"actions",convert(temp_actions),false); ptm.ruleFinished(temp_actions,end()); }
)*

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(3)),line(),start());}temp_targets=ruleToTarget {factory.add($result,"targets",convert(temp_targets),false); ptm.ruleFinished(temp_targets,end()); }
)+

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(4)),line(),start());}temp_properties=rulePropertiesBlock {factory.set($result,"properties",convert(temp_properties),false); ptm.ruleFinished(temp_properties,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(5)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleActionStatement returns [EObject result] :
			{
				$result = factory.create("", "ActionStatement");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(0)),line(),start());}
temp_SetHeaderAction=ruleSetHeaderAction {$result=temp_SetHeaderAction;}
{ptm.ruleFinished(getLastToken(),end());})
	|
({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(1)),line(),start());}
temp_SetBodyAction=ruleSetBodyAction {$result=temp_SetBodyAction;}
{ptm.ruleFinished(getLastToken(),end());})
	|
(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(2)).eContents().get(0)),line(),start());}'convert'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(2)).eContents().get(1)),line(),start());}
temp_ConvertBodyAction=ruleConvertBodyAction {$result=temp_ConvertBodyAction;}
{ptm.ruleFinished(getLastToken(),end());})
)
)
;

ruleSetHeaderAction returns [EObject result] :
			{
				$result = factory.create("", "SetHeaderAction");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(0)),line(),start());}'header'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(1)),line(),start());}temp_header=RULE_STRING {factory.set($result,"header",convert(temp_header),false); ptm.ruleFinished(temp_header,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(2)),line(),start());}'='{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(3)),line(),start());}temp_value=ruleExpression {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleSetBodyAction returns [EObject result] :
			{
				$result = factory.create("", "SetBodyAction");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(0)),line(),start());}'body'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(1)),line(),start());}'='{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(2)),line(),start());}temp_value=ruleExpression {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleConvertBodyAction returns [EObject result] :
        temp_convertbodywithaction=ruleConvertBodyWithAction {$result=temp_convertbodywithaction;}	|        temp_convertbodytoaction=ruleConvertBodyToAction {$result=temp_convertbodytoaction;}	;

ruleConvertBodyWithAction returns [EObject result] :
			{
				$result = factory.create("", "ConvertBodyWithAction");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(0)),line(),start());}'with'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(1)),line(),start());}
temp_BeanExpr=ruleBeanExpr {$result=temp_BeanExpr;}
{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleConvertBodyToAction returns [EObject result] :
			{
				$result = factory.create("", "ConvertBodyToAction");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(18)).eContents().get(1)).eContents().get(0)),line(),start());}'to'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(18)).eContents().get(1)).eContents().get(1)),line(),start());}temp_type=RULE_STRING {factory.set($result,"type",convert(temp_type),false); ptm.ruleFinished(temp_type,end()); }
)
)
;

ruleTransformer returns [EObject result] :
			{
				$result = factory.create("", "Transformer");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(0)),line(),start());}'transformer'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(1)),line(),start());}
temp_Interceptors=ruleInterceptors {$result=temp_Interceptors;}
{ptm.ruleFinished(getLastToken(),end());})?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(2)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(3)),line(),start());}temp_beanref=ruleBeanExpr {factory.set($result,"beanref",convert(temp_beanref),false); ptm.ruleFinished(temp_beanref,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(4)),line(),start());}temp_target=ruleToTarget {factory.add($result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); }
)+

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(5)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleChoice returns [EObject result] :
			{
				$result = factory.create("", "Choice");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(0)),line(),start());}'choice'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(1)),line(),start());}
temp_Interceptors=ruleInterceptors {$result=temp_Interceptors;}
{ptm.ruleFinished(getLastToken(),end());})?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(2)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(3)),line(),start());}temp_when=ruleWhenClause {factory.add($result,"when",convert(temp_when),false); ptm.ruleFinished(temp_when,end()); }
)*

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(4)),line(),start());}temp_otherwise=ruleOtherwiseClause {factory.set($result,"otherwise",convert(temp_otherwise),false); ptm.ruleFinished(temp_otherwise,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(20)).eContents().get(1)).eContents().get(5)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleRecipients returns [EObject result] :
			{
				$result = factory.create("", "Recipients");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(0)),line(),start());}'recipients'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(1)),line(),start());}temp_body=ruleSimplePattern {factory.set($result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); }
)
)
;

ruleDynamicRecipients returns [EObject result] :
			{
				$result = factory.create("", "DynamicRecipients");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(0)),line(),start());}'dynamic'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(1)),line(),start());}'recipients'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(22)).eContents().get(1)).eContents().get(2)),line(),start());}temp_body=ruleExpression {factory.set($result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); }
)
)
;

rulePipeline returns [EObject result] :
			{
				$result = factory.create("", "Pipeline");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(23)).eContents().get(1)).eContents().get(0)),line(),start());}'pipeline'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(23)).eContents().get(1)).eContents().get(1)),line(),start());}temp_body=ruleSimplePattern {factory.set($result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); }
)
)
;

ruleFilter returns [EObject result] :
			{
				$result = factory.create("", "Filter");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(0)),line(),start());}'filter'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(1)),line(),start());}temp_expr=ruleExpression {factory.set($result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(2)),line(),start());}temp_body=ruleSimplePattern {factory.set($result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); }
)
)
;

ruleSplitter returns [EObject result] :
			{
				$result = factory.create("", "Splitter");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(0)),line(),start());}'splitter'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(1)),line(),start());}temp_expr=ruleExpression {factory.set($result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(2)),line(),start());}temp_body=ruleSimplePattern {factory.set($result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); }
)
)
;

ruleResequencer returns [EObject result] :
			{
				$result = factory.create("", "Resequencer");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(0)),line(),start());}'resequence'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(1)),line(),start());}temp_expr=ruleExpression {factory.set($result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(2)),line(),start());}temp_body=ruleSimplePattern {factory.set($result,"body",convert(temp_body),false); ptm.ruleFinished(temp_body,end()); }
)
)
;

ruleWhenClause returns [EObject result] :
			{
				$result = factory.create("", "WhenClause");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(27)).eContents().get(1)).eContents().get(0)),line(),start());}'when'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(27)).eContents().get(1)).eContents().get(1)),line(),start());}temp_expr=ruleExpression {factory.set($result,"expr",convert(temp_expr),false); ptm.ruleFinished(temp_expr,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(27)).eContents().get(1)).eContents().get(2)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(27)).eContents().get(1)).eContents().get(3)),line(),start());}temp_target=ruleToTarget {factory.add($result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); }
)+

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(27)).eContents().get(1)).eContents().get(4)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleOtherwiseClause returns [EObject result] :
			{
				$result = factory.create("", "OtherwiseClause");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(28)).eContents().get(1)).eContents().get(0)),line(),start());}'otherwise'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(28)).eContents().get(1)).eContents().get(1)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(28)).eContents().get(1)).eContents().get(2)),line(),start());}temp_target=ruleToTarget {factory.set($result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(28)).eContents().get(1)).eContents().get(3)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleExpression returns [EObject result] :
			{
				$result = factory.create("", "Expression");
				ptm.setModelElement($result);
			 }
((({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(0)).eContents().get(0)),line(),start());}temp_lang=RULE_ID {factory.set($result,"lang",convert(temp_lang),false); ptm.ruleFinished(temp_lang,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(0)).eContents().get(1)),line(),start());}'('{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(0)).eContents().get(2)),line(),start());}
temp_ExpressionValue=ruleExpressionValue {$result=temp_ExpressionValue;}
{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(0)).eContents().get(3)),line(),start());}')'{ptm.ruleFinished(getLastToken(),end());})
)
	|
({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(29)).eContents().get(1)).eContents().get(1)),line(),start());}RULE_STRING{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleExpressionValue returns [EObject result] :
			{
				$result = factory.create("", "ExpressionValue");
				ptm.setModelElement($result);
			 }
({ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(30)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
;

rulePropertiesBlock returns [EObject result] :
			{
				$result = factory.create("", "PropertiesBlock");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(31)).eContents().get(1)).eContents().get(0)),line(),start());}'properties'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(31)).eContents().get(1)).eContents().get(1)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(31)).eContents().get(1)).eContents().get(2)),line(),start());}temp_properties=rulePropertyDef {factory.add($result,"properties",convert(temp_properties),false); ptm.ruleFinished(temp_properties,end()); }
)+

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(31)).eContents().get(1)).eContents().get(3)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

rulePropertyDef returns [EObject result] :
			{
				$result = factory.create("", "PropertyDef");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(32)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name=ruleSCOPED_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(32)).eContents().get(1)).eContents().get(1)),line(),start());}'='{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(32)).eContents().get(1)).eContents().get(2)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleToTarget returns [EObject result] :
        temp_to=ruleTo {$result=temp_to;}	|        temp_processexpr=ruleProcessExpr {$result=temp_processexpr;}	|        temp_fault=ruleFault {$result=temp_fault;}	;

ruleFault returns [EObject result] :
			{
				$result = factory.create("", "Fault");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(34)).eContents().get(1)).eContents().get(0)),line(),start());}'fault'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(34)).eContents().get(1)).eContents().get(1)),line(),start());}RULE_STRING{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleSCOPED_ID returns [EObject result] :
			{
				$result = factory.create("", "SCOPED_ID");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(35)).eContents().get(1)).eContents().get(0)),line(),start());}RULE_ID{ptm.ruleFinished(getLastToken(),end());})

(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(35)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}'.'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(35)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}RULE_ID{ptm.ruleFinished(getLastToken(),end());})
)*
)
;

ruleBEAN returns [EObject result] :
			{
				$result = factory.create("", "BEAN");
				ptm.setModelElement($result);
			 }
({ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(36)).eContents().get(1)),line(),start());}'bean'{ptm.ruleFinished(getLastToken(),end());})
;

RULE_ID :

	 ('^')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	 
;

RULE_STRING :

	 '"' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'"') )* '"' |
	 '\'' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\'') )* '\''
	 
;

RULE_INT :

	 ('-')?('0'..'9')+
	 
;

RULE_WS :

	 (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;}
	 
;

RULE_ML_COMMENT :

	 '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	 
;

RULE_SL_COMMENT :

	 '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
	 
;

