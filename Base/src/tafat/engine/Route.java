package tafat.engine;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static java.nio.file.StandardOpenOption.APPEND;

public class Route {

    private static final String ADDRESS = "http://maps.googleapis.com/maps/api/directions/json?";
    private static Map<String, Trip> trips = new HashMap<>();

    private static final File routeFile = new File("res/.route");
    private static final String Separator = "@";
    private static final int TripId = 0;
    private static final int Distance = 1;
    private static final int Duration = 2;

    static {
        try {
            if (!routeFile.exists()) {
                routeFile.getParentFile().mkdirs();
                routeFile.createNewFile();
            }
            Files.readAllLines(routeFile.toPath()).stream()
                    .map(s -> s.split(Separator)).forEach(s -> trips.put(s[TripId], new Trip(s[Distance], s[Duration])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final List<Double> from;

    private Route(List<Double> from) {
        this.from = from;
    }

    public static Route from(List<Double> location) {
        trips.put(location.toString() + location.toString(), new Trip(0, 0));
        return new Route(location);
    }

    public Trip to(List<Double> location) {
        String tripKey = from.toString() + location.toString();
        if (!trips.containsKey(tripKey)) addTrip(tripKey, location);
        return trips.get(tripKey);
    }

    private void addTrip(String tripKey, List<Double> location) {
        Trip trip = calculateTripTo(location);
        trips.put(tripKey, trip);
        try {
            String record = tripKey + Separator + trip.distance() + Separator + trip.duration() + "\n";
            Files.write(routeFile.toPath(), record.getBytes(), APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Trip calculateTripTo(List<Double> location) {
        String result = query(from, location);
        while (result.contains("OVER_QUERY_LIMIT")) {
            try {
                Logger.getLogger(Route.class.getName()).warning("Waiting after over query limit of Google Maps");
                Thread.sleep(2000);
                result = query(from, location);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new Trip(distance(result), duration(result));
    }

    private double distance(String result) {
        result = result.substring(0, result.indexOf(" km"));
        return Double.parseDouble(result.substring(result.lastIndexOf("\"") + 1).replace(",", "."));
    }

    private double duration(String result) {
        result = result.substring(0, result.indexOf(" min"));
        result = result.substring(result.lastIndexOf("\"") + 1).replace(",", ".");
        return result.contains(" hour ") ?
                Double.parseDouble(result.split(" hour ")[0]) * 60 + Double.parseDouble(result.split(" hour ")[1]) :
                Double.parseDouble(result);
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
