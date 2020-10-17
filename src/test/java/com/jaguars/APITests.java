package com.jaguars;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class APITests {


    @Test
    public void testMe() {
        RestAssured.baseURI = "https://api.covid19india.org";

        Response response = get("/data.json");

        List<Map<String, ?>> teamsData = response.path("statewise");

        teamsData.forEach(m -> {
            String state = (String) m.get("state");
            if (StringUtils.equals("Maharashtra", state)) {
                String active = (String) m.get("active");
                String confirmed = (String) m.get("confirmed");
                String recovered = (String) m.get("recovered");

                System.out.println("active" + active + "confirmed" + confirmed + "recovered" + recovered);
            }
        });

        teamsData.forEach(m -> {
            String state = (String) m.get("state");
            if (StringUtils.equals("Tamil Nadu", state)) {
                String active = (String) m.get("active");
                String confirmed = (String) m.get("confirmed");
                String recovered = (String) m.get("recovered");

                System.out.println("active" + active + "confirmed" + confirmed + "recovered" + recovered);
            }
        });


        teamsData.forEach(m -> {
            String state = (String) m.get("state");
            if (StringUtils.equals("Karnataka", state)) {
                String active = (String) m.get("active");
                String confirmed = (String) m.get("confirmed");
                String recovered = (String) m.get("recovered");

                System.out.println("active" + active + "confirmed" + confirmed + "recovered" + recovered);
            }
        });

    }
}
