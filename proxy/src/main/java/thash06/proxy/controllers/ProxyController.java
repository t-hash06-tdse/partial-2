package thash06.proxy.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    private int serviceInstance = 0;

    @GetMapping("/lucasseq")
    public String getSequenceProxy(@RequestParam("value") int value) {

        try {
            String GET_URL = this.getServiceInstanceURL() + "/lucasseq?value=" + value;
            System.out.println(GET_URL);
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                return response.toString();
            } else {
                return "Server error";
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error", e);
        }
    }

    private String getServiceInstanceURL() {
        String firstInstance = "http://localhost:8080";
        String secondInstance = "http://localhost:8081";

        if (firstInstance == null || secondInstance == null) {
            System.out.println("Instances are not configured in the ENV variables!");
            return null;
        }

        if (this.serviceInstance == 0) {
            this.serviceInstance = 1;
            return firstInstance;
        }

        this.serviceInstance = 0;
        return secondInstance;
    }
}
