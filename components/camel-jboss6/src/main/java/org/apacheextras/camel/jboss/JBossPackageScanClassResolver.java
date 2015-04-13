/**************************************************************************************
 https://camel-extra.github.io

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation; either version 3
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.


 You should have received a copy of the GNU Lesser General Public
 License along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.jboss;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import org.apache.camel.impl.DefaultPackageScanClassResolver;
import org.apache.camel.spi.PackageScanFilter;
import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.jboss.vfs.VisitorAttributes;
import org.jboss.vfs.util.AbstractVirtualFileVisitor;

/**
 * JBoss specific package scan classloader to be used when Camel is running
 * inside JBoss Application Server.
 */
public class JBossPackageScanClassResolver extends DefaultPackageScanClassResolver {

    @Override
    protected void find(PackageScanFilter test, String packageName, ClassLoader loader, Set<Class<?>> classes) {
        if (log.isTraceEnabled()) {
            log.trace("Searching for: " + test + " in package: " + packageName
                    + " using classloader: " + loader.getClass().getName());
        }

        Enumeration<URL> urls;
        try {
            urls = getResources(loader, packageName);
            if (!urls.hasMoreElements()) {
                log.trace("No URLs returned by classloader");
            }
        } catch (IOException ioe) {
            log.warn("Could not read package: " + packageName, ioe);
            return;
        }

        while (urls.hasMoreElements()) {
            URL url = null;
            try {
                url = urls.nextElement();
                if (log.isTraceEnabled()) {
                    log.trace("URL from classloader: " + url);
                }
                VirtualFile packageNode = VFS.getChild(url.toURI());
                packageNode.visit(new MatchingClassVisitor(test, packageName, classes));
            } catch (IOException ioe) {
                log.warn("Could not read entries in url: " + url, ioe);
            } catch (URISyntaxException use) {
                log.warn("Could not read entries in url: " + url, use);
            }
        }
    }

    private class MatchingClassVisitor extends AbstractVirtualFileVisitor {
        private PackageScanFilter filter;
        private String packageName;
        private Set<Class<?>> classes;

        private MatchingClassVisitor(PackageScanFilter filter, String packageName, Set<Class<?>> classes) {
            super(VisitorAttributes.RECURSE_LEAVES_ONLY);
            this.filter = filter;
            this.packageName = packageName;
            this.classes = classes;
        }

        @Override
        public void visit(VirtualFile file) {
            if (file.getName().endsWith(".class")) {
                String fqn = file.getPathName();
                String qn;
                if (fqn.indexOf("jar/") != -1) {
                    qn = fqn.substring(fqn.indexOf("jar/") + 4);
                } else {
                    if (fqn.indexOf("war/WEB-INF/classes") != -1) {
                        qn = fqn.substring(fqn.indexOf("war/WEB-INF/classes/") + "war/WEB-INF/classes/".length());
                    } else {
                        qn = fqn.substring(fqn.indexOf("/") + 1);
                    }
                }

                addIfMatching(filter, qn, classes);
            }
        }
    }

}