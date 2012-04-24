
package org.apache.camel.spit.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.emf.ecore.EObject;
import org.openarchitectureware.check.CheckFacade;
import org.openarchitectureware.emf.EcoreUtil2;
import org.openarchitectureware.expression.ExecutionContext;
import org.openarchitectureware.workflow.issues.Issues;
import org.openarchitectureware.workflow.issues.IssuesImpl;
import org.openarchitectureware.workflow.util.ResourceLoader;
import org.openarchitectureware.workflow.util.ResourceLoaderFactory;
import org.openarchitectureware.workflow.util.ResourceLoaderImpl;
import org.openarchitectureware.xtend.XtendFacade;
import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.IXtextParser;
import org.openarchitectureware.xtext.parser.impl.AbstractXtextParser;
import org.openarchitectureware.xtext.parser.parsetree.Node;

public abstract class GenParser extends AbstractXtextParser implements IXtextParser {

	private InputStreamReader reader;
	private spitLexer lexer;
	private spitParser parser;

	public GenParser(InputStream in) {
		this.reader = new InputStreamReader(in);
	}

	public spitLexer getLexer() {
		if (lexer == null) {
			try {
				this.lexer = new spitLexer(new ANTLRReaderStream(reader));
			} catch (IOException e) {
				handleError(e);
			}
		}
		return lexer;
	}

	public spitParser getParser() {
		if (parser == null) {
			parser = new spitParser(new CommonTokenStream(getLexer()));
		}
		return parser;
	}

	public List<ErrorMsg> getParseErrors() {
		getRootNode();
		List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
		errors.addAll(getLexer().getErrors());
		errors.addAll(getParser().getErrors());
		errors.addAll(internalErrors);
		return errors;
	}

	public Node getRootNode() {
		if (result == null) {
			try {
				result = getParser().parse();
			} catch (RecognitionException e) {
				handleError(e);
			}
		}
		return result;
	}
	
	@Override
	protected void internalPostLink() {
	}

	@Override
	protected void internalPreLink() {
	}
	
	protected void internalLink() {
		ResourceLoader cl = ResourceLoaderFactory.createResourceLoader();
		try {
			ResourceLoaderFactory.setCurrentThreadResourceLoader(new ResourceLoaderImpl(GenParser.class.getClassLoader()));
			ExecutionContext ctx = getExecutionContext();
			XtendFacade facade = XtendFacade.create(ctx, "org::apache::camel::spit::Linking");
			facade.call("link", getRootNode().getModelElement());
		} catch (Exception e) {
			internalErrors.add(new ErrorMsg("Error during linking phase : "+e.getMessage(),0,1,1));
		} finally {
			ResourceLoaderFactory.setCurrentThreadResourceLoader(cl);
		}
	}

	public Issues doCheck() {
		IssuesImpl issues = new IssuesImpl();
		ResourceLoader cl = ResourceLoaderFactory.createResourceLoader();
		try {
			ResourceLoaderFactory.setCurrentThreadResourceLoader(new ResourceLoaderImpl(GenParser.class.getClassLoader()));
			ExecutionContext ctx = getExecutionContext();
			Collection<EObject> allElements = EcoreUtil2.allContents(getRootNode().getModelElement());
			allElements.add(getRootNode().getModelElement());
			String[] checkFiles = new String[]{"org::apache::camel::spit::GenChecks","org::apache::camel::spit::Checks"};
			for (String checkFile : checkFiles) {
				CheckFacade.checkAll(checkFile, allElements, ctx, issues);
			}
		} catch (Exception e) {
			issues.addError("Error during validation phase : "+e.getMessage(), getRootNode().getModelElement());
		} finally {
			ResourceLoaderFactory.setCurrentThreadResourceLoader(cl);
		}
		return issues;
	}
	
}

