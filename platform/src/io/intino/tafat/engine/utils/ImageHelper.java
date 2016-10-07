package io.intino.tafat.engine.utils;

import spark.utils.IOUtils;

import java.io.IOException;
import java.net.URL;

import static com.sun.org.apache.xml.internal.security.utils.Base64.encode;

public class ImageHelper {

	public static String base64(URL image) {
		try {
			return encode(IOUtils.toByteArray(image.openStream()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
