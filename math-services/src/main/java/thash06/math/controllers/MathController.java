package thash06.math.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MathController {

    @GetMapping("/lucasseq")
    public Map<String, String> getSequence(@RequestParam("value") int value) {
        StringBuilder builder = new StringBuilder("");
        HashMap<String, String> response = new HashMap<>();

        for(int i = 0; i < value; i++) {
            builder.append(calculateLucasSequence(i));

            if (i != value -1) {
                builder.append(", ");
            }
        }
        
        response.put("operation", "Secuencia de Lucas");
        response.put("input", String.valueOf(value));
        response.put("output", builder.toString());

        return response;
    }

    private int calculateLucasSequence(int number) {
        if (number == 0) {
            return 2;
        }

        if (number == 1) {
            return 1;
        }

        return calculateLucasSequence(number - 1) + calculateLucasSequence(number - 2);
    }
}
