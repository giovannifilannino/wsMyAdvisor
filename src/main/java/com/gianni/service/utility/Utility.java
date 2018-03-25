package com.gianni.service.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

public class Utility {

	private static final Logger log = Logger.getLogger(Utility.class.getName());

	public static byte[] getByteArrayFromInputStream(InputStream i) {
		byte[] foto = null;
		if (null != i) {
			try {
				foto = IOUtils.toByteArray(i);
			} catch (IOException e) {
				log.severe(e.getMessage());
			}
		}
		return foto;
	}

	public static String uploadImmagine(String filename, String folder, InputStream uploadedInputStream) {
		String location = null;
		try {
			String os = System.getProperty("os.name").toLowerCase();

			String locationLinux = "foodlover/" + folder;

			String path = "";
			if (os.indexOf("win") >= 0) {
				path = "D:\\TEMP";
			} else {
				path = "/var/www/html/" + locationLinux;
			}

			String uploadedFileLocation = path + File.separator + filename;

			writeToFile(uploadedInputStream, uploadedFileLocation);

			location = locationLinux + File.separator + filename;
		} catch (IOException e) {
			log.severe(e.getMessage());
		}
		return location;
	}

	private static void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {

		File f = new File(uploadedFileLocation);
		f.setReadable(true, false);
		f.setExecutable(false, true);
		f.setWritable(false, true);
		try (OutputStream out = new FileOutputStream(f);) {
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
		}
	}
}
