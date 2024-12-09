package com.jonah.vttp5_ssf_day09practice.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.print.attribute.standard.MediaSize.ISO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jonah.vttp5_ssf_day09practice.Constants.Constants;
import com.jonah.vttp5_ssf_day09practice.Model.ToDo;
import com.jonah.vttp5_ssf_day09practice.Repo.MapRepo;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

@Service
public class ToDoRestService {
    @Autowired
    MapRepo mapRepo;

    RestTemplate restTemplate = new RestTemplate();


    public void loadToDoJson(){
        InputStream filInputStream = null;

        try {
            filInputStream = getClass().getResourceAsStream("/todos.json");
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

    public JsonObject turnToDoJson(ToDo todo){

        JsonObjectBuilder jbBuilder = Json.createObjectBuilder();
        String uuid = UUID.randomUUID().toString();

        SimpleDateFormat sdfo =  new SimpleDateFormat("EEE, MM/dd/yyyy");

        todo.setId(uuid);
        jbBuilder.add("id", todo.getId());
        jbBuilder.add("name",todo.getName());
        jbBuilder.add("description", todo.getDescription());
        
        jbBuilder.add("priority_level", todo.getPriority());
        jbBuilder.add("status", "test");

        String timeStamp = sdfo.format(Calendar.getInstance().getTime());

        jbBuilder.add("due_date",sdfo.format(todo.getDueDate()));

        jbBuilder.add("created_at", timeStamp);
        jbBuilder.add("updated_at", timeStamp);
        
        JsonObject toDoJsonObject = jbBuilder.build();
        /* JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("id", person.getId());
            jsonObjectBuilder.add("fullName", person.getFullName());
            jsonObjectBuilder.add("email", person.getEmail());
            jsonObjectBuilder.add("phone", person.getPhone());
            jsonObjectBuilder.add("dob", person.getDob().toString());
            JsonObject jsonObject = jsonObjectBuilder.build();

 */     return toDoJsonObject;
    }

    public void addJsonObjectToRedis(JsonObject jsonObject){
        mapRepo.create(Constants.todoKey, jsonObject.getString("id"), jsonObject.toString());
        System.out.println("json object added to redis: " + jsonObject.toString());
    }


    public void deleteToDoFromRedis(String toDoId){
        mapRepo.delete(Constants.todoKey, toDoId);

        System.out.println("\ndeleted this from redis:" + toDoId);
    }

    public void updateToDoInRedis(ToDo todo){
        
        mapRepo.delete(Constants.todoKey, todo.getId());
        
        mapRepo.create(Constants.todoKey, todo.getId(), turnToDoJson(todo).toString());
        System.out.println("FOR UPDATING maprepo created: " + todo.getId());


    }

    public Boolean checkBirthday(String dateString){
        Date currentDate = new Date();
        long OneYearmilliseconds = (long) 365 * 24 * 60 * 60 * 1000;
        Date oneYearEarlier = new Date(currentDate.getTime() - OneYearmilliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        Date birthday = null;
        try {
            birthday = sdf.parse(dateString);
            System.out.println("parsed date! birthday is" + birthday);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(birthday.before(oneYearEarlier )){
            return true;
        }
        return false;

    }





}
