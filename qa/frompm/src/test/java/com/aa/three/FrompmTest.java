package com.aa.three;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aa.improvekataben.FromPMApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FromPMApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FrompmTest {

    @Autowired
    private TestRestTemplate testRestTemplate; // "Postman"

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void verifyThereIsAItineraryInPNR() throws Exception {
        // setup & execution = Postman
        String response = testRestTemplate.getForObject("http://ricbox.com/three", String.class); // playing "Postman" - same functionality as Postman hitting "Send"
        // assertion
        List<Map> jsonData = objectMapper.readValue(response, List.class);
        for (Map row : jsonData) {
            // pm.expect(row.itinerary).to.not.equal(null);
            assertNotNull(row.get("itinerary"));
            if (row.get("origin") == null) {
                fail("no origin");
            }
            assertNotNull(row.get("date"));
        }
    }


//    pm.test("Verify there is a itinerary in PNR", function(){
//            var jsonData = pm.response.json();
//            for(row of jsonData) {
//                pm.expect(row.itinerary).to.not.equal(null);
//                pm.expect(row.origin).to.not.equal(null);
//                if (row.origin == null){
//                    pm.expect.fail("no origin detected");
//                }
//                pm.expect(row.date).to.not.equal(null);
//            }
//        });

}
