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
package org.apacheextras.camel.examples.esper;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedSimMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedSimMain.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 3) {
          LOGGER.info("Arguments are: <number of threads> <drop probability percent> <number of seconds to run>");
          LOGGER.info("  number of threads: the number of threads sending feed events into the engine");
          LOGGER.info("  drop probability percent: a number between zero and 100 that dictates the ");
          LOGGER.info("                            probability that per second one of the feeds drops off");
          LOGGER.info("  number of seconds: the number of seconds the simulation runs");
            System.exit(-1); //NOSONAR
        }

        int numberOfThreads;
        try {
            numberOfThreads = Integer.parseInt(args[0]);
        } catch (NullPointerException e) {
            LOGGER.error("'{}' caused by '{}'", e.getMessage(), e);
            LOGGER.info("Invalid number of threads:" + args[0]);
            System.exit(-2); //NOSONAR
            return;
        }

        double dropProbability;
        try {
            dropProbability = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            LOGGER.error("'{}' caused by '{}'", e.getMessage(), e);
            LOGGER.info("Invalid drop probability:" + args[1]);
            System.exit(-2); //NOSONAR
            return;
        }

        int numberOfSeconds;
        try {
            numberOfSeconds = Integer.parseInt(args[2]);
        } catch (NullPointerException e) {
            LOGGER.error("'{}' caused by '{}'", e.getMessage(), e);
            LOGGER.info("Invalid number of seconds to run:" + args[2]);
            System.exit(-2); //NOSONAR
            return;
        }

        // Run the sample
        LOGGER.info("Using " + numberOfThreads + " threads with a drop probability of " + dropProbability + "%, for " + numberOfSeconds + " seconds");
        FeedSimMain feedSimMain = new FeedSimMain(numberOfThreads, dropProbability, numberOfSeconds, true);
        feedSimMain.run();
    }

    private int numberOfThreads;
    private double dropProbability;
    private int numSeconds;

    public FeedSimMain(int numberOfThreads, double dropProbability, int numSeconds, boolean isWaitKeypress) {
        this.numberOfThreads = numberOfThreads;
        this.dropProbability = dropProbability;
        this.numSeconds = numSeconds;
    }

    public void run() throws IOException, InterruptedException {
    	// Send events
        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);
        MarketDataSendRunnable runnables[] = new MarketDataSendRunnable[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            runnables[i] = new MarketDataSendRunnable();
            threadPool.submit(runnables[i]);
        }

        int seconds = 0;
        Random random = new Random();
        while(seconds < numSeconds) {
            seconds++;
            Thread.sleep(1000);

            FeedEnum feedToDropOff;
            if (random.nextDouble() * 100 < dropProbability) {
                feedToDropOff = FeedEnum.FEED_A;
                if (random.nextBoolean()) {
                    feedToDropOff = FeedEnum.FEED_B;
                }
                LOGGER.info("Setting drop-off for feed " + feedToDropOff);

            } else {
                feedToDropOff = null;
            }
            for (int i = 0; i < runnables.length; i++) {
                runnables[i].setRateDropOffFeed(feedToDropOff);
            }
        }

        LOGGER.info("Shutting down threadpool");
        for (int i = 0; i < runnables.length; i++) {
            runnables[i].setShutdown();
        }
        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
