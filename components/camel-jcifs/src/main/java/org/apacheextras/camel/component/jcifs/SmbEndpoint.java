/**************************************************************************************
 Copyright (C) 2010 Redpill Linpro AB
 http://code.google.com/a/apache-extras.org/p/camel-extra

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
package org.apacheextras.camel.component.jcifs;

import jcifs.smb.SmbFile;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileEndpoint;
import org.apache.camel.component.file.GenericFileProducer;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;

public class SmbEndpoint extends GenericFileEndpoint<SmbFile> {

	public SmbEndpoint(String uri, SmbComponent smbComponent, SmbConfiguration configuration) {
		super(uri, smbComponent);
		this.configuration = configuration;
	}

	@Override
	public SmbConfiguration getConfiguration() {
		return (SmbConfiguration) configuration;
	}
	
	@Override
	public SmbConsumer createConsumer(Processor processor) throws Exception {
        SmbConsumer consumer = new SmbConsumer(this, processor, createSmbOperations());

        if (isDelete() && getMove() != null) {
            throw new IllegalArgumentException("You cannot set both delete=true and move options");
        }

        // if noop=true then idempotent should also be configured
        if (isNoop() && !isIdempotentSet()) {
            log.info("Endpoint is configured with noop=true so forcing endpoint to be idempotent as well");
            setIdempotent(true);
        }

        // if idempotent and no repository set then create a default one
        if (isIdempotentSet() && isIdempotent() && idempotentRepository == null) {
            log.info("Using default memory based idempotent repository with cache max size: " + DEFAULT_IDEMPOTENT_CACHE_SIZE);
            idempotentRepository = MemoryIdempotentRepository.memoryIdempotentRepository(DEFAULT_IDEMPOTENT_CACHE_SIZE);
        }

        consumer.setMaxMessagesPerPoll(getMaxMessagesPerPoll());
        configureConsumer(consumer);
        return consumer;
    }

	@Override
	public GenericFileProducer<SmbFile> createProducer() throws Exception {
		return new SmbProducer(this, createSmbOperations());
	}

	@Override
	public Exchange createExchange(GenericFile<SmbFile> file) {
		Exchange answer = new DefaultExchange(this);
		if (file != null) {
			file.bindToExchange(answer);
		}
		return answer;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SmbOperations<SmbFile> createSmbOperations(){
		SmbClient client = new SmbClient();
		if ( ((SmbConfiguration)this.configuration).getSmbApiFactory() != null ) {
			client.setSmbApiFactory(((SmbConfiguration)this.configuration).getSmbApiFactory());
		}
		SmbOperations operations = new SmbOperations(client);
		operations.setEndpoint(this);
		return operations;
	}

	@Override
	public String getScheme() {
		return "smb";
	}

	@Override
	public char getFileSeparator() {
		return '/';
	}

	@Override
	public boolean isAbsolute(String name) {
		return true;
	}
	
	@Override
	public boolean isSingleton(){
		return false;
	}

    @Override
    protected String createDoneFileName(String fileName) {
        return super.createDoneFileName(fileName);
    }

}