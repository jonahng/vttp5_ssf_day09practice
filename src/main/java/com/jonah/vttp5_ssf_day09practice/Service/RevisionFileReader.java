package com.jonah.vttp5_ssf_day09practice.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RevisionFileReader{ //implements CommandLineRunner 

    //set the variable in application properties
    @Value("${my.api.key}")
    private String apikey;


    //@Override
    public void print(){
        System.out.println("revisionfilereader.java override commandlinerunner");

        String file = "customers";

    }

    public void withLoop(String file) throws Exception{
        //List<Customer> customers = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            br.readLine(); //this discards the header first line
            String line;
            while(null != (line=br.readLine())){
                String[] fields = line.split(",");
                if(!"iceland".equals(fields[6].trim().toLowerCase())){
                    continue;
                }
        
                //create your object, like customer or smth
                //set the object to fields[1] etc on the object using object setters
                //create list of the object, customers.add(customer)
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void withStream(String file) throws Exception{
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
/* 
                br.lines()
                .skip(1) //skipping first header line
                .map(line -> line.split(","))//turn from 1 format to another using map
                .filter(fields -> "hungary".equals(fields[6].trim().toLowerCase())) //i think this will find hungary
                .map(fields -> {
                Customer customer  = new customer();
                        customer.setFirstName(fields[1]);
                        return customer;
                    }).toList(); */
          

    }catch (Exception exception){

    }
    
}
}
