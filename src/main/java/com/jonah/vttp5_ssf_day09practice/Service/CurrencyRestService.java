package com.jonah.vttp5_ssf_day09practice.Service;

import java.io.StringReader;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jonah.vttp5_ssf_day09practice.Constants.Constants;
import com.jonah.vttp5_ssf_day09practice.Repo.MapRepo;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class CurrencyRestService {

    @Autowired
    MapRepo mapRepo;

    RestTemplate restTemplate = new RestTemplate();

    public Set<String> getCurrencyRates(){
        ResponseEntity<String> currencyRawData = restTemplate.getForEntity(Constants.listOfCurrenciesURL, String.class);
        String currencyData = currencyRawData.getBody();
        JsonReader jsonReader = Json.createReader(new StringReader(currencyData));
        JsonObject wholeJsonObject = jsonReader.readObject();
        JsonObject resultsJsonObject = wholeJsonObject.getJsonObject("results");
        Set<String> countryKeys = resultsJsonObject.keySet();
        System.out.println(countryKeys);

        for(String countryKey: countryKeys){
            JsonObject countryJsonObject = null;
            countryJsonObject = resultsJsonObject.getJsonObject(countryKey);
            mapRepo.create(Constants.currencyKey, countryKey, countryJsonObject.toString());

        }
        return countryKeys;

    }

    public Double callAPI(String starterCurrency, String convertedCurrency){

        JsonReader jsonReader = Json.createReader(new StringReader(mapRepo.get(Constants.currencyKey, starterCurrency).toString()));
        JsonObject countryJson = jsonReader.readObject();
        String starterCurrencyId = countryJson.getString("currencyId");

        JsonReader jsonReader2 = Json.createReader(new StringReader(mapRepo.get(Constants.currencyKey, convertedCurrency).toString()));
        JsonObject countryJson2 = jsonReader2.readObject();
        String convertedCurrencyId = countryJson2.getString("currencyId");


        String plainURL = "https://free.currconv.com/api/v7/convert";
        String apiKey = "apiKey=e19d793fde867e2c6dd5";
        String finalURL = plainURL + "?q=" + starterCurrencyId + "_" + convertedCurrencyId + "&compact=ultra&" + apiKey;
        System.out.println("API URL IS: " + finalURL);
        
        ResponseEntity<String> replyRawData = restTemplate.getForEntity(finalURL, String.class);
        String replyData = replyRawData.getBody();
        System.out.println("replyData received" + replyData);

        JsonReader replyjsonReader = Json.createReader(new StringReader(replyData));
        JsonObject replyObject = replyjsonReader.readObject();
        Double conversionRate = Double.parseDouble(replyObject.get(starterCurrencyId + "_" + convertedCurrencyId).toString());

        System.out.println("RECEIVED CONVERSION RATE! IT IS " + conversionRate);
        return conversionRate;
    }


    
}
