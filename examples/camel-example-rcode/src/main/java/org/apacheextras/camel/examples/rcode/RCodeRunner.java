/**************************************************************************************
 https://camel-extra.github.io

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
package org.apacheextras.camel.examples.rcode;

import org.apacheextras.camel.examples.rcode.builder.RCodeRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Commandline tool for starting up the {@link RCodeRouteBuilder}.
 */
public class RCodeRunner {

    /** Provides some basic output. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RCodeRunner.class);
    /** Default file that points to the source directory . */
    private static File source = new File(RCodeRunner.class.getResource("data/").toString());
    /** Default file that points the user home target directory. */
    private static File target = new File(System.getProperty("user.home") + "/target");

    /** A set of OPTIONS that configures the Camel Routes */
    private static final Options OPTIONS;
    static {
        OPTIONS = new Options();
        OPTIONS.addOption("h", "help", false, "provides a list of availble command OPTIONS.");
        OPTIONS.addOption("t", "target", true, "specified the output directory where the generated graph will be stored.");
        OPTIONS.addOption("s", "source", true, "defines the source directory that contains the data directory.");
    }

    /**
     * Shows all commands in a formatted way to provide some guidance for
     * specifying the correct OPTIONS.
     */
    private static void showHelp(Options options) {
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("help", options);
    }

    /**
     * Takes the argument String from the command line and parses according to
     * the specified parameter. If any parameter is not specified correctly, the
     * parser defaults to show the help as output.
     */
    private static boolean parseCommandLine(String... args) {
        // Initialize a basic parser
        final CommandLineParser parser = new BasicParser();
        // Parse OPTIONS from command line

        CommandLine commandLine;
        try {
            commandLine = parser.parse(OPTIONS, args);
            // Catch any parse exception and show the help
        } catch (ParseException ex) {
            LOGGER.error("Could not parse the specified OPTIONS!");
            showHelp(OPTIONS);
            return false;
        }

        if (commandLine.hasOption("help")) {
            showHelp(OPTIONS);
            return false;
        }
        // If source has not been specified or is null show OPTIONS, otherwise
        // process the option
        if (!commandLine.hasOption("source") || null == commandLine.getOptionValue("source")) {
            showHelp(OPTIONS);
            return false;
        }
        if (commandLine.hasOption("source")) {
            LOGGER.debug("Command line option is: {}", commandLine.getOptionValue("source"));
            source = new File(commandLine.getOptionValue("source"));
        }
        // If target has not been specified or is null show OPTIONS, otherwise
        // process the option
        if (!commandLine.hasOption("target") || null == commandLine.getOptionValue("target")) {
            showHelp(OPTIONS);
            return false;
        }
        if (commandLine.hasOption("target")) {
            LOGGER.debug("Command line option is: {}", commandLine.getOptionValue("target"));
            target = new File(commandLine.getOptionValue("target"));
        }
        return true;
    }

    /**
     * Kicks of the main project to run an integration of Apache Camel and
     * RCode. Accepted parameters are:
     * <ul>
     * <li><code>-help</code>: provides a list of available command OPTIONS.</li>
     * <li><code>-source &lt;arg&gt;</code>: defines the source directory that
     * contains the data directory.</li>
     * <li><code>-target &lt;arg&gt;</code>: specified the output directory
     * where the generated graph will be stored.</li>
     * </ul>
     * An example for an acceptable command is:<br>
     * <code>java -jar camel-example-rcode-${VERSION}.jar -source /${PATH}/${TO}/${SOURCE} -target /${PATH}/${TO}/${TARGET}</code>
     * 
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String... args) throws Exception {
        // Parse the command line arguments
        if (!parseCommandLine(args)) {
            System.exit(1); // NOSONAR
        }
        // Start the camel context
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RCodeRouteBuilder(source, target));
        camelContext.start();
        // Give Camel some time to process the data
        LOGGER.info("Waiting to finish the route calculation.");
        TimeUnit.SECONDS.sleep(10);
        // Shutdown the camel context
        camelContext.stop();
    }
}
