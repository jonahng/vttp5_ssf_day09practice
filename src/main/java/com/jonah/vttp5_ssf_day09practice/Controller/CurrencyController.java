package com.jonah.vttp5_ssf_day09practice.Controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jonah.vttp5_ssf_day09practice.Model.CurrencyConversionForm;
import com.jonah.vttp5_ssf_day09practice.Service.CurrencyRestService;

@Controller
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyRestService currencyRestService;

    @GetMapping("")
    public String getListOfCurrencies(Model model){
        Set<String> currencyKeys = currencyRestService.getCurrencyRates();
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        currencyConversionForm.setCurrencyKeys(currencyKeys);
        model.addAttribute("currencyConversionForm", currencyConversionForm);
        //model.addAttribute("currencyKeys", currencyKeys);

        return "currencyform";
    }


  /*   starterCurrency;
    private String convertedCurrency;
    private Long amountToConvert;
 */
    @PostMapping("")
    public String handleCurrencyConversion(@RequestBody MultiValueMap<String, String> formEntity, Model model){
        String starterCurrency = formEntity.getFirst("starterCurrency");
        String convertedCurrency = formEntity.getFirst("convertedCurrency");
        Double amountToConvert = Double.parseDouble(formEntity.getFirst("amountToConvert"));
        //Long amountToConvert = formEntity.getFirst("amountToConvert");
        //Long amountToConvert = Long.parseLong(formEntity.getFirst("amountToConvert"));
        System.out.println("data received from form is: " + starterCurrency + convertedCurrency + amountToConvert);
        Double conversionRate = currencyRestService.callAPI(starterCurrency, convertedCurrency);
        Double amountOfFinalCurrency = amountToConvert*conversionRate;

        model.addAttribute("conversionRate", conversionRate);
        model.addAttribute("starterCurrency", starterCurrency);
        model.addAttribute("convertedCurrency", convertedCurrency);
        model.addAttribute("amountToConvert", amountToConvert);
        model.addAttribute("amountOfFinalCurrency", amountOfFinalCurrency);
        return "currencyanswer";
    }
    
}
