package com.jonah.vttp5_ssf_day09practice.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jonah.vttp5_ssf_day09practice.Constants.Constants;
import com.jonah.vttp5_ssf_day09practice.Model.ToDo;
import com.jonah.vttp5_ssf_day09practice.Repo.MapRepo;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class ToDoRestService {
    @Autowired
    MapRepo mapRepo;

    RestTemplate restTemplate = new RestTemplate();


    public void loadToDoJson(){
        InputStream filInputStream = null;

        try {
            filInputStream = new FileInputStream("src\\main\\resources\\todos.json");
        } catch (Exception e) {
            // TODO: handle exception
        }
        JsonReader JsonReader = Json.createReader(filInputStream);
        JsonArray toDoJsonArray = JsonReader.readArray();
        
        List<ToDo> toDoList = new ArrayList<>();

        for(int i = 0; i<toDoJsonArray.size(); i++){
            JsonObject tdJsonObject = toDoJsonArray.getJsonObject(i);
            ToDo td = new ToDo();
            mapRepo.create(Constants.todoKey, tdJsonObject.getString("id"), tdJsonObject.toString());

        }
        System.out.println("added todo file to redis as map");
    }
}
