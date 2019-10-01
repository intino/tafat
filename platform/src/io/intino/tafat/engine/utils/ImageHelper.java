package io.intino.tafat.engine.utils;

import spark.utils.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;

public class ImageHelper {

	public static String base64(URL image) {
		try {
			return Arrays.toString(Base64.getEncoder().encode(IOUtils.toByteArray(image.openStream())));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
