package com.jonah.vttp5_ssf_day09practice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jonah.vttp5_ssf_day09practice.Constants.Constants;
import com.jonah.vttp5_ssf_day09practice.Service.ToDoRestService;


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
    


}
