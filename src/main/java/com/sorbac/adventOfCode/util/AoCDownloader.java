package com.sorbac.adventOfCode.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AoCDownloader {
    public static String downloadInput(int year, int day) throws Exception {
        String urlString = String.format("https://adventofcode.com/%d/day/%d/input", year, day);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Cookie", "session=" + System.getenv("AOC_SESSION_COOKIE"));

        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        }

        return result.toString();
    }
}
