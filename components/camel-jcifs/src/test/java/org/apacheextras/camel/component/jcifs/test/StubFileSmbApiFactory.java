package org.apacheextras.camel.component.jcifs.test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apacheextras.camel.component.jcifs.SmbApiFactory;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;


public class StubFileSmbApiFactory implements SmbApiFactory {
	
	Map<String, SmbFile> smbFiles = new HashMap<String, SmbFile>();
	Map<String, SmbFileOutputStream> smbFileOutputStream = new HashMap<String, SmbFileOutputStream>();

	public SmbFile createSmbFile(String urlString,
			NtlmPasswordAuthentication authentication)
			throws MalformedURLException, SmbException {
		try {
			URI uri = new URI(urlString);
			return smbFiles.get(uri.getPath());
		} catch (URISyntaxException e) {
			throw new MalformedURLException(e.getLocalizedMessage());
		}
	}

	public SmbFileOutputStream createSmbFileOutputStream(SmbFile smbFile,
			boolean b) throws SmbException, MalformedURLException,
			UnknownHostException {
		return smbFileOutputStream.get(smbFile.getName());
		
	}

	public void putSmbFiles(String urlString, SmbFile file) throws URISyntaxException {
		URI uri = new URI(urlString);
		this.smbFiles.put(uri.getPath(), file);
		
	}

	public void putSmbFileOutputStream(String string,
			SmbFileOutputStream mockOutputStream) {
		this.smbFileOutputStream.put(string, mockOutputStream);
		
	}

}
