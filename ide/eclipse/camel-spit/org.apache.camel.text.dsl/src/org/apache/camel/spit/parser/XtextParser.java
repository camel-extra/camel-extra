/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.


 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/gpl-2.0-standalone.html
 ***************************************************************************************/
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