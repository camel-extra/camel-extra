/**************************************************************************************
 * jcifs Camel component
 * Copyright (C) 2010 Redpill Linpro AB
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 ***************************************************************************************/
package org.apacheextras.camel.component.jcifs;

import java.net.URI;

import org.apache.camel.component.file.GenericFileConfiguration;
import org.apache.camel.util.ObjectHelper;

public class SmbConfiguration extends GenericFileConfiguration {

	private static final String DOMAIN_SEPARATOR = ";";
	private static final String USER_PASS_SEPARATOR = ":";

	private String domain = null;
	private String username = null;
	private String password = null;
	private String host = null;
	private String path = null;
	private SmbApiFactory smbApiFactory = null;

	public SmbConfiguration(URI uri, SmbApiFactory smbApiFactory) {
		configure(uri);
		this.smbApiFactory = smbApiFactory;
	}

	@Override
	public void configure(URI uri) {
		super.configure(uri);
		String userInfo = uri.getUserInfo();

		if (userInfo.contains(DOMAIN_SEPARATOR)) {
			setDomain(ObjectHelper.before(userInfo, DOMAIN_SEPARATOR));
			userInfo = ObjectHelper.after(userInfo, DOMAIN_SEPARATOR);
		}
		if (userInfo.contains(USER_PASS_SEPARATOR)) {
			setUsername(ObjectHelper.before(userInfo, USER_PASS_SEPARATOR));
			setPassword(ObjectHelper.after(userInfo, USER_PASS_SEPARATOR));
		}
		else {
			setUsername(userInfo);
		}

		setHost(uri.getHost());
		
		setPath(uri.getPath());
	}
	
	public String getSmbPath() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("smb://");
		buffer.append(getHost());
		buffer.append(getPath());
		return buffer.toString();
	}
	
	public String getSmbHostPath() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("smb://");
		buffer.append(getHost());
		buffer.append("/");
		return buffer.toString();
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	//TODO: give this dirty handling some thinking.
	public String getDirectory() {
		String s = super.getDirectory();
		s = s.replace('\\', '/');
		//we always need /
		//this is a bit dirty

		return s;
	}
	
	public void setSmbApiFactory(SmbApiFactory smbApiFactory) {
		this.smbApiFactory = smbApiFactory;
	}
	
	public SmbApiFactory getSmbApiFactory() {
		return smbApiFactory;
	}

}
