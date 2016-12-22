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
package org.apache.camel.component.firebase.provider;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Copies a test file to a folder to test routes.
 */
public class SampleInputProvider {

    private final Path targetFolder;

    public SampleInputProvider() throws IOException {
        targetFolder = Paths.get("src/data");
        if (!Files.exists(targetFolder)) {
            Files.createDirectories(targetFolder);
        }
    }

    public void copySampleFile() throws URISyntaxException, IOException {
        final String name = "sample_message.txt";
        URL url = Thread.currentThread().getContextClassLoader().getResource(name);
        assertThat(url).isNotNull();
        final URI uri = url.toURI();
        Path sourceFile = Paths.get(uri);
        Files.copy(sourceFile, targetFolder.resolve(name), StandardCopyOption.REPLACE_EXISTING);
    }

    public Path getTargetFolder() {
        return targetFolder;
    }

    public static String createDeleteKey() {
        return "second king of Saxony";
    }
}
