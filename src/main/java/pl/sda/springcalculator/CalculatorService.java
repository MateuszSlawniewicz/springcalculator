package pl.sda.springcalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CalculatorService {
    @Autowired
    private CalculatorDAO calculatorDAO;


    public List<String> useFancyCreator(String value) {
        return calculatorDAO.fancyHistoryListCreator(value);
    }


    public Long calculate(String valueToCalculate) {
        if (calculatorDAO.checkIfExists(valueToCalculate)) {
            return calculatorDAO.giveMeAValue(valueToCalculate);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Pattern pattern = Pattern.compile("(\\d+)(\\D+)(\\d+)");
        Matcher matcher = pattern.matcher(valueToCalculate);
        Long result = matchAndCalculate(matcher);
        if (result != null) {
            calculatorDAO.addToCache(result, valueToCalculate);
            return result;
        }

        return null;
    }

    private Long matchAndCalculate(Matcher matcher) {
        matcher.matches();
        String group1 = matcher.group(1);
        String group2 = matcher.group(2).trim();
        String group3 = matcher.group(3);
        switch (group2) {
            case "+":
                return Long.valueOf(group1) + Long.valueOf(group3);
            case "-":
                return Long.valueOf(group1) - Long.valueOf(group3);
            case "*":
                return Long.valueOf(group1) * Long.valueOf(group3);
            case "/":
            case "\\":
                return Long.valueOf(group1) / Long.valueOf(group3);
        }
        return null;
    }

}