package org.apache.camel.spit.editor;

import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;

import org.apache.camel.spit.SpitEditorPlugin;

public class SpitEditor extends AbstractXtextEditor {

   public AbstractXtextEditorPlugin getPlugin() {
      return SpitEditorPlugin.getDefault();
   }
}
