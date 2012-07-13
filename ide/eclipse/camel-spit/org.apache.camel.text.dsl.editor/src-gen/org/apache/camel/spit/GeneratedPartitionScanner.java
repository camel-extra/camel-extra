/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra/

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
package org.apache.camel.spit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.openarchitectureware.xtext.editor.scanning.AbstractPartitionScanner;

public class GeneratedPartitionScanner extends AbstractPartitionScanner {

	@Override
	public List<IPredicateRule> getRules() {
		List<IPredicateRule> rules = new ArrayList<IPredicateRule>();

		rules.add(new MultiLineRule("/*","*/", comment));
		rules.add(new SingleLineRule("//", "", comment));
		rules.add(new MultiLineRule("\"","\"", string));
		rules.add(new MultiLineRule("'","'", string));
		return rules;
	}

	protected IToken getSingleLineCommentToken(String string) {
		return comment;
	}

	protected IToken getStringToken(String start, String end) {
		return string;
	}

}

