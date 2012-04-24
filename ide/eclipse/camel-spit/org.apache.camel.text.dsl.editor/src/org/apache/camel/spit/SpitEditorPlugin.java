package org.apache.camel.spit;

import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.LanguageUtilities;
import org.osgi.framework.BundleContext;

public class SpitEditorPlugin extends AbstractXtextEditorPlugin {
   private static SpitEditorPlugin plugin;
   public static SpitEditorPlugin getDefault() {
      return plugin;
   }

   private SpitUtilities utilities = new SpitUtilities();
   public LanguageUtilities getUtilities() {
      return utilities;
   }

   public SpitEditorPlugin() {
      plugin = this;
   }

   public void stop(BundleContext context) throws Exception {
      super.stop(context);
      plugin = null;
   }
}
