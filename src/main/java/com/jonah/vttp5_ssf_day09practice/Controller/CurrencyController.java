package com.jonah.vttp5_ssf_day09practice.Controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jonah.vttp5_ssf_day09practice.Service.CurrencyRestService;

@Controller
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyRestService currencyRestService;

    @GetMapping("")
    public void getListOfCurrencies(){
        Set<String> currencyKeys = currencyRestService.getCurrencyRates();
        
    }
    
}
