/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.dataformat.edi;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.util.ObjectHelper;
import org.apache.camel.spi.DataFormat;
import org.milyn.Smooks;
import org.w3c.dom.Document;

/**
 * EDI DataFormat.
 *
 * This data format supports currently only <b>one</b> operation:
 * <ul>
 *   <li>unmarshal = from EDI (as a InputStream) to XML (can be used when receiving streamed data from EDI files etc.).
 *   The input object should be convertable to InputStream. The result object is a javax.xml.dom.Document object. </li>
 * </ul>
 * <p/>
 * Uses the <a href="http://milyn.codehaus.org/Smooks">Smooks</a> for EDI parsing.
 * <p/>
 * Supports these options:
 * <ul>
 *   <li>mappingModel - filename of the smooks edimap mapping file to use</li>
 *   <li>smooksConfig - filename of the smooks configuration file</li>
 * </ul>
 * Either the mappingModel or the smooksConfig options should be set. Setting the mapping model will use a default
 * smooks configuration file.
 */
public class EDIDataFormat implements DataFormat {

    private String mappingModel;
    private String smooksConfig;
    private String defaultSmooksConfig;

    public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
        // Message / XML to EDI
        // TODO: Not possbile in smooks (http://jira.codehaus.org/browse/MILYN-142)
        throw new UnsupportedOperationException("Not implemented");
    }

    public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
        // EDI to XML
        Smooks smooks;
        if (smooksConfig != null) {
            smooks = new Smooks(smooksConfig);
        } else if (mappingModel != null) {
            // default config
            if (defaultSmooksConfig == null) {
                // default config not loaded so load it
                InputStream is = ObjectHelper.loadResourceAsStream("org/apache/camel/dataformat/edi/default-smooks-config.xml");
                defaultSmooksConfig = exchange.getContext().getTypeConverter().convertTo(String.class, is);
            }

            // set out mapping model to use
            String data = defaultSmooksConfig.replaceFirst("MAPPING-MODEL-PARAM", mappingModel);
            InputStream config = exchange.getContext().getTypeConverter().convertTo(InputStream.class, data);
            smooks = new Smooks(config);
        } else {
            throw new IllegalArgumentException("Either mappingModel or smooksConfig should be set");
        }

        Source source = new StreamSource(stream);
        DOMResult result = new DOMResult();
        smooks.filter(source, result, smooks.createExecutionContext());

        Document root = (Document) result.getNode();
        return root;
    }

    public String getMappingModel() {
        return mappingModel;
    }

    public void setMappingModel(String mappingModel) {
        this.mappingModel = mappingModel;
    }

    public String getSmooksConfig() {
        return smooksConfig;
    }

    public void setSmooksConfig(String smooksConfig) {
        this.smooksConfig = smooksConfig;
    }
}
