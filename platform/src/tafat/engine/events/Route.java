package tafat.engine.events;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class Route {

    private static final String ADDRESS = "http://maps.googleapis.com/maps/api/directions/json?";
    private final List<Double> from;

    private Route(List<Double>  from) {
        this.from = from;
    }

    public static Route from(List<Double> location) {
        return new Route(location);
    }

    public Trip to(List<Double>  location) {
        return calculateTripTo(location);
    }

    private Trip calculateTripTo(List<Double>  location) {
        String result = query(from, location);
        return new Trip(distance(result), duration(result));
    }

    private double distance(String result) {
        result = result.substring(0, result.indexOf(" km"));
        return Double.parseDouble(result.substring(result.lastIndexOf("\"") + 1).replace(",", "."));
    }

    private double duration(String result) {
        result = result.substring(0, result.indexOf(" min"));
        return Double.parseDouble(result.substring(result.lastIndexOf("\"") + 1).replace(",", "."));
    }

    private String query(List<Double> from, List<Double> location) {
        return processResponse(doRequest(buildUrl(from, location)));
    }

    private String processResponse(URLConnection urlConnection) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line = in.readLine();
            while (line != null) {
                response.append(line);
                line = in.readLine();
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private URLConnection doRequest(URL url) {
        try {
            return url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private URL buildUrl(List<Double> from, List<Double> location) {
        try {
            return new URL(ADDRESS + "origin=" + $(from) + "&destination=" + $(location));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String $(List<Double> location) {
        return location.get(0) + "," + location.get(1);
    }

}
