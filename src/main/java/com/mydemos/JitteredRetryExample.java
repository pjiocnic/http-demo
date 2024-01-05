package com.mydemos;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Random;

public class JitteredRetryExample {

    private static final int MAX_RETRIES = 3;
    private static final Duration BASE_RETRY_INTERVAL = Duration.ofSeconds(5);

    public static void main(String[] args) {
        String serviceUrl = "https://example.com/api";

        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            try {
                makeHttpRequest(serviceUrl);
                break; // Successful request, exit loop
            } catch (IOException | InterruptedException e) {
                // Handle exceptions or log errors
                System.err.println("Error in HTTP request attempt " + attempt + ": " + e.getMessage());

                if (attempt < MAX_RETRIES) {
                    Duration retryInterval = calculateJitteredRetryInterval(BASE_RETRY_INTERVAL);
                    System.out.println("Retrying in " + retryInterval.getSeconds() + " seconds...");
                    sleep(retryInterval);
                } else {
                    System.err.println("Max retries reached. Exiting.");
                }
            }
        }
    }

    private static void makeHttpRequest(String serviceUrl) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serviceUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check for successful status codes (e.g., 2xx)
//		if (response.statusCode() >= 200 && response.statusCode() < 300) {
		if (response.statusCode() >= HttpURLConnection.HTTP_OK && response.statusCode() < HttpURLConnection.HTTP_MULT_CHOICE) {
            // Process the successful response
            System.out.println("Successful response: " + response.body());
        } else {
            // Handle non-successful response (e.g., server errors)
            throw new IOException("HTTP request failed with status code: " + response.statusCode());
        }
    }

    private static Duration calculateJitteredRetryInterval(Duration baseInterval) {
        Random random = new Random();
        long jitter = random.nextLong() % (baseInterval.toMillis() / 4);
        return baseInterval.plusMillis(jitter);
    }

    private static void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
