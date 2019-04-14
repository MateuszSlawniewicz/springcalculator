package pl.sda.springcalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController { //todo change Long to String

    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public String showForm(@RequestParam(required = false) String valueToCalculate, Model model) {
        Long value = calculatorService.calculate(valueToCalculate);
        model.addAttribute("result", value);
        model.addAttribute("historyList", calculatorService.useFancyCreator(valueToCalculate));
        return "calculatorForm";

    }


}





