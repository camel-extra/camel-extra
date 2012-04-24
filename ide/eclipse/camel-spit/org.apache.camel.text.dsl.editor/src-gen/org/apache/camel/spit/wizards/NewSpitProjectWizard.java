package org.apache.camel.spit.wizards;

import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.wizards.AbstractNewProjectWizard;

import org.apache.camel.spit.SpitEditorPlugin;

public class NewSpitProjectWizard extends AbstractNewProjectWizard {

	public NewSpitProjectWizard() {
		super();
		setLangName("spit");
		setGeneratorProjectName("org.apache.camel.text.dsl.generator");
		setDslProjectName("org.apache.camel.text.dsl");
		setFileExtension("spit");
		setPackageName("org/apache/camel/spit/");
	}
	
	@Override
	protected LanguageUtilities getUtilities() {
		return SpitEditorPlugin.getDefault().getUtilities();
	}
}

