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
    
}
