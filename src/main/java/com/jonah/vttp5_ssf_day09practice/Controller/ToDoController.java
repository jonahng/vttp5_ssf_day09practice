package com.jonah.vttp5_ssf_day09practice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.jonah.vttp5_ssf_day09practice.Constants.Constants;
import com.jonah.vttp5_ssf_day09practice.Model.ToDo;
import com.jonah.vttp5_ssf_day09practice.Service.ToDoRestService;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("")
public class ToDoController {
    @Autowired
    ToDoRestService toDoRestService;
    
    @GetMapping("/alltodo")
    public String allToDo(Model model) {
        model.addAttribute("alltodo", toDoRestService.allToDoFromRedis(Constants.todoKey));
        return "alltodo";
    }


    @GetMapping("/createtodo")
    public String createToDo(Model model){
        ToDo todo = new ToDo();
        model.addAttribute("todo", todo);
        return "createtodoform";
    }


    @PostMapping("/createtodo")
    public String postCreateToDo(@Valid @ModelAttribute("todo") ToDo todo, BindingResult bindingResult, Model model) {
        //TODO: process POST request

        System.out.println(" \n + \n post form received from createTODO.:" + todo.getName() + todo.getDueDate());

        //json builder to turn the received person into json object
        //write json object into the redis database susing maprepo?

      /*  EXAMPLE:
             JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("id", person.getId());
            jsonObjectBuilder.add("fullName", person.getFullName());
            jsonObjectBuilder.add("email", person.getEmail());
            jsonObjectBuilder.add("phone", person.getPhone());
            jsonObjectBuilder.add("dob", person.getDob().toString());
            JsonObject jsonObject = jsonObjectBuilder.build();
        mapoRepo.create(Constant.personsKey, person.getId().toString(), jsonObject.toString()); */

        return "redirect:/alltodo";

    }
    
    


}
