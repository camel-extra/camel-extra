package org.apache.camel.spit.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openarchitectureware.xtext.parser.ErrorMsg;

public class XtextParser extends GenParser {
	public XtextParser(InputStream in) {
		super(in);
	}

	@Override
	public List<ErrorMsg> getParseErrors() {
		List<ErrorMsg> parseErrors = super.getParseErrors();
		List<ErrorMsg> newErrors = new ArrayList<ErrorMsg>(parseErrors.size());
		for (Iterator<ErrorMsg> iterator = parseErrors.iterator(); iterator
				.hasNext();) {
			ErrorMsg errorMsg = (ErrorMsg) iterator.next();
			// Funky style replacement of parser errors...
			if (errorMsg.getMsg().indexOf("RULE_ID") >= 0) {
				newErrors.add(new ErrorMsg("Identifier expected!", errorMsg
						.getStart(), errorMsg.getLength(), errorMsg.getLine()));
				iterator.remove();
			} else {
				newErrors.add(errorMsg);
			}
		}
		return newErrors;
	}

}