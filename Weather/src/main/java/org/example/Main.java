package org.example;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import java.util.*;

/**
 * Приложение для выбора города с наиболее тёплой погодой.
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String[] citiesName = {"Красноярск", "Новосибирск", "Адлер", "Лондон", "Париж", "Минск", "Киев",
                "Владивосток", "Токио", "Иерусалим"};

        int[] citiesId = {1502026, 1496747, 584243, 2643743,  2988507, 625144, 703448, 2013348, 1850147, 281184};

        var cities = new LinkedHashMap<String, Double>();

        //Arrays.stream(citiesId).forEach(city -> {
        Arrays.stream(citiesName).forEach(city -> {
            try {
                var weather_thread = new Weather(city);
                weather_thread.start();
                weather_thread.join();
                var parts = weather_thread.weather.toString().split(",");
                cities.put(parts[0], Double.parseDouble(parts[1]));
            } catch (InterruptedException interruptedException) {
                System.out.println(interruptedException.getMessage());
            }
        });


        cities.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }

    static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
                new Comparator<Map.Entry<K, V>>() {
                    @Override
                    public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1; // Special fix to preserve items with equal values
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }


}

class Weather extends Thread {

    final static String APP_ID = "cd8a934eda8ef9934515dc86ee9048fc";
    final static String URL_SEARCH_BY_ID = "https://api.openweathermap.org/data/2.5/weather?id=%d";
    final static String URL_SEARCH_BY_NAME = "https://api.openweathermap.org/data/2.5/weather?q=%s";

    public Weather(String cityName) {
        this.url = String.format(URL_SEARCH_BY_NAME, cityName);
        ;
    }

    public Weather(int cityId) {
        this.url = String.format(URL_SEARCH_BY_ID, cityId);
    }

    String url;

    Response weather;

    @Override
    public void run() {
        try {
            URL weather_url = new URL(url
                    + "&units=" + Units.METRIC
                    + "&appid=" + APP_ID
                    + "&lang=ru");
            InputStream stream = (InputStream) weather_url.getContent();
            Gson gson = new Gson();
            weather = gson.fromJson(new InputStreamReader(stream), Response.class);
        } catch (IOException e) {
        }
    }
}


