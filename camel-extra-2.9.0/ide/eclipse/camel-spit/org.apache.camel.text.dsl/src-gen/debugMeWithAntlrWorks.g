grammar debugMeWithAntlrWorks;
 options{backtrack=true; memoize=true;} 



parse :
	 result=ruleFile EOF
;

ruleFile  :
((temp_imports=ruleImport )*

(temp_interceptorDecls=ruleGlobalInterceptorDecl )*

(temp_routes=ruleRoute )*

(temp_interceptorDefs=ruleInterceptorChainDef )*
)
;

ruleRoute  :
(('route')

(temp_name=RULE_ID )

(temp_from=ruleFrom )

(
temp_Interceptors=ruleInterceptors {$result=temp_Interceptors;}
)?

('{')

(temp_process=ruleProcessExpr )

('}')
)
;

ruleImport  :
(('import')

(temp_location= RULE_STRING
)
)
;

ruleGlobalInterceptorDecl  :
(('global')

('interceptors')

(temp_interceptorName=RULE_ID
)
)
;

ruleInterceptorChainDef  :
(('interceptor')

(temp_name=RULE_ID )

('{')

(temp_chain=ruleBeanExpr )+

('}')
)
;

ruleInterceptors  :
(('interceptors')

('(')

(temp_interceptorName=RULE_ID
)+

(')')
)
;

ruleFrom  :
(('from')

(temp_uri=RULE_STRING )
)
;

ruleTo  :
(('to')

(temp_uri=RULE_STRING )
)
;

rulePatternExpr  :
        temp_choice=ruleChoice 	|        temp_recipients=ruleRecipients 	|        temp_pipeline=rulePipeline 	|        temp_filter=ruleFilter 	|        temp_splitter=ruleSplitter 	|        temp_resequencer=ruleResequencer 	|        temp_transformer=ruleTransformer 	|        temp_dynamicrecipients=ruleDynamicRecipients 	;

ruleProcessExpr  :
        temp_patternorbeanexpr=rulePatternOrBeanExpr 	;

rulePatternOrBeanExpr  :
        temp_beanexpr=ruleBeanExpr 	|        temp_patternexpr=rulePatternExpr 	;

ruleBeanExpr  :
((
temp_BEAN=ruleBEAN {$result=temp_BEAN;}
)

(temp_id=RULE_ID )

(temp_method=RULE_ID )?
)
;

ruleSimplePattern  :
((temp_interceptors=ruleInterceptors )?

('{')

(temp_actions=ruleActionStatement )*

(temp_targets=ruleToTarget )+

(temp_properties=rulePropertiesBlock )?

('}')
)
;

ruleActionStatement  :
((
temp_SetHeaderAction=ruleSetHeaderAction {$result=temp_SetHeaderAction;}
)
	|
(
temp_SetBodyAction=ruleSetBodyAction {$result=temp_SetBodyAction;}
)
	|
(('convert')

(
temp_ConvertBodyAction=ruleConvertBodyAction {$result=temp_ConvertBodyAction;}
)
)
)
;

ruleSetHeaderAction  :
(('header')

(temp_header=RULE_STRING )

('=')

(temp_value=ruleExpression )
)
;

ruleSetBodyAction  :
(('body')

('=')

(temp_value=ruleExpression )
)
;

ruleConvertBodyAction  :
        temp_convertbodywithaction=ruleConvertBodyWithAction 	|        temp_convertbodytoaction=ruleConvertBodyToAction 	;

ruleConvertBodyWithAction  :
(('with')

(
temp_BeanExpr=ruleBeanExpr {$result=temp_BeanExpr;}
)
)
;

ruleConvertBodyToAction  :
(('to')

(temp_type=RULE_STRING )
)
;

ruleTransformer  :
(('transformer')

(
temp_Interceptors=ruleInterceptors {$result=temp_Interceptors;}
)?

('{')

(temp_beanref=ruleBeanExpr )

(temp_target=ruleToTarget )+

('}')
)
;

ruleChoice  :
(('choice')

(
temp_Interceptors=ruleInterceptors {$result=temp_Interceptors;}
)?

('{')

(temp_when=ruleWhenClause )*

(temp_otherwise=ruleOtherwiseClause )?

('}')
)
;

ruleRecipients  :
(('recipients')

(temp_body=ruleSimplePattern )
)
;

ruleDynamicRecipients  :
(('dynamic')

('recipients')

(temp_body=ruleExpression )
)
;

rulePipeline  :
(('pipeline')

(temp_body=ruleSimplePattern )
)
;

ruleFilter  :
(('filter')

(temp_expr=ruleExpression )

(temp_body=ruleSimplePattern )
)
;

ruleSplitter  :
(('splitter')

(temp_expr=ruleExpression )

(temp_body=ruleSimplePattern )
)
;

ruleResequencer  :
(('resequence')

(temp_expr=ruleExpression )

(temp_body=ruleSimplePattern )
)
;

ruleWhenClause  :
(('when')

(temp_expr=ruleExpression )

('{')

(temp_target=ruleToTarget )+

('}')
)
;

ruleOtherwiseClause  :
(('otherwise')

('{')

(temp_target=ruleToTarget )

('}')
)
;

ruleExpression  :
(((temp_lang=RULE_ID )

('(')

(
temp_ExpressionValue=ruleExpressionValue {$result=temp_ExpressionValue;}
)

(')')
)
	|
(RULE_STRING)
)
;

ruleExpressionValue  :
(temp_value=RULE_STRING )
;

rulePropertiesBlock  :
(('properties')

('{')

(temp_properties=rulePropertyDef )+

('}')
)
;

rulePropertyDef  :
((temp_name=ruleSCOPED_ID )

('=')

(temp_value=RULE_STRING )
)
;

ruleToTarget  :
        temp_to=ruleTo 	|        temp_processexpr=ruleProcessExpr 	|        temp_fault=ruleFault 	;

ruleFault  :
(('fault')

(RULE_STRING)
)
;

ruleSCOPED_ID  :
((RULE_ID)

(('.')

(RULE_ID)
)*
)
;

ruleBEAN  :
('bean')
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

