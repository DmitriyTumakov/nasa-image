package ru.netology;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;

public class Main {
    public static String URL = "https://api.nasa.gov/planetary/apod?api_key=0nj7HpSmh0I6PKgRy1Yfj3ydtHOiXZFlnHcuC0fV";

    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        downloadImage();
    }

    private static void downloadImage() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build()) {

            HttpGet request = new HttpGet(URL);
            CloseableHttpResponse response = httpClient.execute(request);
            NASA nasaObj = convertJson(response);
            response.close();

            HttpGet imageRequest = new HttpGet(nasaObj.getUrl());
            CloseableHttpResponse imageResponse = httpClient.execute(imageRequest);
            createImage(imageResponse, nasaObj.getUrl());
            imageResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createImage(CloseableHttpResponse imageResponse, String url) {
        File file = new File("file.jpg");
        String fileName = url.substring( url.lastIndexOf('/')+1, url.length() );
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(imageResponse.getEntity().getContent().readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NASA convertJson(CloseableHttpResponse response) throws IOException {
        return mapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<>() {
                });
    }
}