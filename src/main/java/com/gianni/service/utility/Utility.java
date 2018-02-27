package com.gianni.service.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

public class Utility {

	private static final Logger log = Logger.getLogger(Utility.class.getName());
	
	public static byte[] getByteArrayFromInputStream(InputStream i) {
		byte[] foto = null;
		if(null != i) {
			try {
				foto = IOUtils.toByteArray(i);
			} catch (IOException e) {
				log.severe(e.getMessage());
			} 
		}
		return foto;
	}
}
