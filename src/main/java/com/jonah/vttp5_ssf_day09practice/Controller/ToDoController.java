package com.jonah.vttp5_ssf_day09practice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.jonah.vttp5_ssf_day09practice.Constants.Constants;
import com.jonah.vttp5_ssf_day09practice.Model.SessionData;
import com.jonah.vttp5_ssf_day09practice.Model.ToDo;
import com.jonah.vttp5_ssf_day09practice.Service.ToDoRestService;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("")
public class ToDoController {
    @Autowired
    ToDoRestService toDoRestService;
    
    @GetMapping("/alltodo")
    public String allToDo(HttpSession httpSession, Model model) {
        System.out.println("http session data:" + httpSession.getId() + "session getattribute" + httpSession.getAttribute("session"));

        if(httpSession.getAttribute("session") ==null){
            return "redirect:/sessions";
        }
        
        System.out.println("the dob attribute is " + httpSession.getAttribute("dob"));
        if(! toDoRestService.checkBirthday(httpSession.getAttribute("dob").toString())){

            System.out.println("DID NOT PASS THE AGE CHECK, less than 1 year old");
            return "redirect:/sessions";
        }
        System.out.println("Passed the age check!");

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

        System.out.println(" \n + \n post form received from createTODO.:" + todo.getName() + "date received is:" + todo.getDueDate() + "whole todo object: " + todo.toString());

        //turning the todo object into json object and adding it to redis.
        toDoRestService.addJsonObjectToRedis(toDoRestService.turnToDoJson(todo));

        return "redirect:/alltodo";

    }

    @GetMapping("/delete/{todo-id}")
    public String deleteToDo(@PathVariable("todo-id") String toDoId){
        System.out.println("\nto do id to delete is " + toDoId);
        toDoRestService.deleteToDoFromRedis(toDoId);

        return "redirect:/alltodo";
    }


    @GetMapping("/update/{todo-id}")
    public String updateToDo(@PathVariable("todo-id") String toDoId, Model model){
        System.out.println("update: todo id to update is" + toDoId);
        ToDo todo = toDoRestService.toDoFromRedis(toDoId);
        
        System.out.println("to do object received is " + todo.toString());
        model.addAttribute("todo",todo);
        return "todoupdate";
    }

    @PostMapping("/todoupdate")
    public String postUpdateForm(@Valid @ModelAttribute("todo") ToDo todo, BindingResult bindingResult, Model model){
        System.out.println("received from post update data");
        if(bindingResult.hasErrors()){
            System.out.println("errors in bindingresult: " + bindingResult.getErrorCount());
        }
        System.out.println("to do data to update" + todo.getId() + todo);
        toDoRestService.updateToDoInRedis(todo);
            return "redirect:/alltodo";
 

    }
}

    
    
    



