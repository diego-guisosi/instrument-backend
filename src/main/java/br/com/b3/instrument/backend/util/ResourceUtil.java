package br.com.b3.instrument.backend.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceUtil {

    public static String getContentAsString(String resourceName) throws IOException, URISyntaxException {
        URI uri = ResourceUtil.class.getResource("/" + resourceName).toURI();
        Path path = Paths.get(uri);

        byte[] carsData = Files.readAllBytes(path);
        return new String(carsData, Charset.forName("UTF-8"));
    }

}
