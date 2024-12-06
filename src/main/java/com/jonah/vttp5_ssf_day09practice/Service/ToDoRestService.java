package com.jonah.vttp5_ssf_day09practice.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public ToDo toDoFromRedis(String mapKey){
        JsonReader jsonReader = Json.createReader(new StringReader(mapRepo.get(Constants.todoKey, mapKey).toString()));
        JsonObject toDoJsonObject = jsonReader.readObject();
        ToDo td = new ToDo();

        //"Sun, 10/22/2024"
        SimpleDateFormat sdfo =  new SimpleDateFormat("EEE, MM/dd/yyyy");


        td.setId(toDoJsonObject.getString("id"));
        td.setName(toDoJsonObject.getString("name"));
        td.setDescription(toDoJsonObject.getString("description"));
        td.setPriority(toDoJsonObject.getString("priority_level"));
        td.setStatus(toDoJsonObject.getString("status"));
        try {
            td.setDueDate(sdfo.parse(toDoJsonObject.getString("due_date")));
            td.setCreatedAt(sdfo.parse(toDoJsonObject.getString("created_at")));
            td.setUpdatedAt(sdfo.parse(toDoJsonObject.getString("updated_at")));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("to do item created from redis:" + td);

        return td;

    }

    public List<ToDo> allToDoFromRedis(String redisKey){
        List<ToDo> wholeToDoListRedis = new ArrayList<>();
        for(Object mapKey: mapRepo.getKeys(Constants.todoKey)){
            wholeToDoListRedis.add(toDoFromRedis(mapKey.toString()));
        }
        System.out.println("completed alltodofromredis" + wholeToDoListRedis);
        return wholeToDoListRedis;

    }


}
