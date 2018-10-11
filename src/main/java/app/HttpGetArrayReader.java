package app;


import app.pojo.ToDo;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class HttpGetArrayReader {

    public static void main(String[] args) throws IOException {

        HttpRequestFactory httpRequestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest currentCurrencyValue = httpRequestFactory.buildGetRequest(new GenericUrl(
                "https://jsonplaceholder.typicode.com/todos"));

        String parseAsString = currentCurrencyValue.execute().parseAsString();
        Gson gson = new GsonBuilder().create();
        ToDo[] toDos = gson.fromJson(parseAsString, ToDo[].class);

        for (ToDo toDo : toDos) {
            System.out.println(toDo);
        }
    }
}
