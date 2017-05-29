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
package org.apacheextras.camel.component.jcifs.strategy;

import java.util.Map;

import jcifs.smb.SmbFile;
import org.apache.camel.CamelContext;
import org.apache.camel.Expression;
import org.apache.camel.LoggingLevel;
import org.apache.camel.component.file.GenericFileExclusiveReadLockStrategy;
import org.apache.camel.component.file.GenericFileProcessStrategy;
import org.apache.camel.component.file.strategy.GenericFileDeleteProcessStrategy;
import org.apache.camel.component.file.strategy.GenericFileExpressionRenamer;
import org.apache.camel.component.file.strategy.GenericFileNoOpProcessStrategy;
import org.apache.camel.component.file.strategy.GenericFileRenameExclusiveReadLockStrategy;
import org.apache.camel.component.file.strategy.GenericFileRenameProcessStrategy;
import org.apache.camel.util.ObjectHelper;

/**
 * @see org.apache.camel.component.file.remote.strategy.SftpProcessStrategyFactory
 */
public final class SmbProcessStrategyFactory {

    private SmbProcessStrategyFactory() {
    }

    public static GenericFileProcessStrategy<SmbFile> createGenericFileProcessStrategy(CamelContext context, Map<String, Object> params) {

        // We assume a value is present only if its value not null for String and 'true' for boolean
        Expression moveExpression = (Expression) params.get("move");
        Expression moveFailedExpression = (Expression) params.get("moveFailed");
        Expression preMoveExpression = (Expression) params.get("preMove");
        boolean isNoop = params.get("noop") != null;
        boolean isDelete = params.get("delete") != null;
        boolean isMove = moveExpression != null || preMoveExpression != null || moveFailedExpression != null;

        if (isDelete) {
            GenericFileDeleteProcessStrategy<SmbFile> strategy = new GenericFileDeleteProcessStrategy<SmbFile>();
            strategy.setExclusiveReadLockStrategy(getExclusiveReadLockStrategy(params));
            if (preMoveExpression != null) {
                GenericFileExpressionRenamer<SmbFile> renamer = new GenericFileExpressionRenamer<SmbFile>();
                renamer.setExpression(preMoveExpression);
                strategy.setBeginRenamer(renamer);
            }
            if (moveFailedExpression != null) {
                GenericFileExpressionRenamer<SmbFile> renamer = new GenericFileExpressionRenamer<SmbFile>();
                renamer.setExpression(moveFailedExpression);
                strategy.setFailureRenamer(renamer);
            }
            return strategy;
        } else if (isMove || isNoop) {
            GenericFileRenameProcessStrategy<SmbFile> strategy = new GenericFileRenameProcessStrategy<SmbFile>();
            strategy.setExclusiveReadLockStrategy(getExclusiveReadLockStrategy(params));
            if (!isNoop && moveExpression != null) {
                // move on commit is only possible if not noop
                GenericFileExpressionRenamer<SmbFile> renamer = new GenericFileExpressionRenamer<SmbFile>();
                renamer.setExpression(moveExpression);
                strategy.setCommitRenamer(renamer);
            }
            // both move and noop supports pre move
            if (moveFailedExpression != null) {
                GenericFileExpressionRenamer<SmbFile> renamer = new GenericFileExpressionRenamer<SmbFile>();
                renamer.setExpression(moveFailedExpression);
                strategy.setFailureRenamer(renamer);
            }
            // both move and noop supports pre move
            if (preMoveExpression != null) {
                GenericFileExpressionRenamer<SmbFile> renamer = new GenericFileExpressionRenamer<SmbFile>();
                renamer.setExpression(preMoveExpression);
                strategy.setBeginRenamer(renamer);
            }
            return strategy;
        } else {
            // default strategy will do nothing
            GenericFileNoOpProcessStrategy<SmbFile> strategy = new GenericFileNoOpProcessStrategy<SmbFile>();
            strategy.setExclusiveReadLockStrategy(getExclusiveReadLockStrategy(params));
            return strategy;
        }
    }

    @SuppressWarnings("unchecked")
    private static GenericFileExclusiveReadLockStrategy<SmbFile> getExclusiveReadLockStrategy(Map<String, Object> params) {
        GenericFileExclusiveReadLockStrategy<SmbFile> strategy = (GenericFileExclusiveReadLockStrategy<SmbFile>) params.get("exclusiveReadLockStrategy");
        if (strategy != null) {
            return strategy;
        }

        // no explicit strategy set then fallback to readLock option
        String readLock = (String) params.get("readLock");
        if (ObjectHelper.isNotEmpty(readLock)) {
            if ("none".equals(readLock) || "false".equals(readLock)) {
                return null;
            } else if ("rename".equals(readLock)) {
                GenericFileRenameExclusiveReadLockStrategy<SmbFile> readLockStrategy = new GenericFileRenameExclusiveReadLockStrategy<SmbFile>();
                Long timeout = (Long) params.get("readLockTimeout");
                if (timeout != null) {
                    readLockStrategy.setTimeout(timeout);
                }
                Long checkInterval = (Long) params.get("readLockCheckInterval");
                if (checkInterval != null) {
                    readLockStrategy.setCheckInterval(checkInterval);
                }
                LoggingLevel logLevel = (LoggingLevel) params.get("readLockLoggingLevel");
                if (null != logLevel) {
                    readLockStrategy.setReadLockLoggingLevel(logLevel);
                }
                return readLockStrategy;
            } else if ("changed".equals(readLock)) {
                SmbChangedExclusiveReadLockStrategy readLockStrategy = new SmbChangedExclusiveReadLockStrategy();
                Long timeout = (Long) params.get("readLockTimeout");
                if (timeout != null) {
                    readLockStrategy.setTimeout(timeout);
                }
                Long checkInterval = (Long) params.get("readLockCheckInterval");
                if (checkInterval != null) {
                    readLockStrategy.setCheckInterval(checkInterval);
                }
                Long minLength = (Long) params.get("readLockMinLength");
                if (minLength != null) {
                    readLockStrategy.setMinLength(minLength);
                }
                Long minAge = (Long) params.get("readLockMinAge");
                if (null != minAge) {
                    readLockStrategy.setMinAge(minAge);
                }
                LoggingLevel logLevel = (LoggingLevel) params.get("readLockLoggingLevel");
                if (null != logLevel) {
                    readLockStrategy.setReadLockLoggingLevel(logLevel);
                }
                return readLockStrategy;
            }
        }

        return null;
    }

}
