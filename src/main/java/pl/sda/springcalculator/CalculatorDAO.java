package pl.sda.springcalculator;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CalculatorDAO {
    private Map<String, Long> resultsCache = new LinkedHashMap<>();

    public List<String> fancyHistoryListCreator(String currentQuerry) {
        return resultsCache.entrySet().stream()
                .filter(e -> !e.getKey().equals(currentQuerry))
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.toList());

    }


    public boolean checkIfExists(String valueToCalculate) {
        return resultsCache.containsKey(valueToCalculate);
    }

    public Long giveMeAValue(String valueToCalculate) {
        return resultsCache.get(valueToCalculate);
    }

    public void addToCache(Long sum, String valueToCalculate) {
        resultsCache.put(valueToCalculate, sum);
    }
}
