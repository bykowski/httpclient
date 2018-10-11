package app;


import app.pojo.Eur;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class HttpGetCurrencyReader {

    public static void main(String[] args) throws IOException {

        HttpRequestFactory httpRequestFactory = new NetHttpTransport().createRequestFactory();

        HttpRequest currentCurrencyValue = httpRequestFactory.buildGetRequest(new GenericUrl(
                "http://api.openrates.io/latest?symbols=USD,GBP"));

        HttpRequest oldCurrencyValue = httpRequestFactory.buildGetRequest(new GenericUrl(
                "http://api.openrates.io/2013-01-10?symbols=USD,GBP"));

        Gson gson = new GsonBuilder().create();

        String currentParseAsString = currentCurrencyValue.execute().parseAsString();
        Eur eur = gson.fromJson(currentParseAsString, Eur.class);
        System.out.println("1 " + eur.getBase() + " is: " + eur.getRates().getGBP() + " GBP"   );

        String oldparseAsString = oldCurrencyValue.execute().parseAsString();
        Eur oldeur = gson.fromJson(oldparseAsString, Eur.class);

        System.out.println("5 yars ago - 1 " + oldeur.getBase() + " was: " + oldeur.getRates().getGBP() + " GBP"   );
    }
}
