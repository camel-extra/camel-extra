
package org.apache.camel.spit.wizards;

import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.wizards.AbstractNewFileWizard;

import org.apache.camel.spit.SpitEditorPlugin;

public class NewSpitFileWizard extends AbstractNewFileWizard {

	@Override
	protected LanguageUtilities getUtilities() {
		return SpitEditorPlugin.getDefault().getUtilities();
	}
}
