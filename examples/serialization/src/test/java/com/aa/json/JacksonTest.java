package com.aa.json;

import org.junit.jupiter.api.Test;

import com.aa.serialization.DataRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void convertToJson() throws JsonProcessingException {
        DataRecord dataRecord = new DataRecord();
        dataRecord.setFirst("Start");
        dataRecord.setLast("End");
        dataRecord.setAge(89);

        String request = objectMapper.writeValueAsString(dataRecord);

        System.out.println(request);
    }

}
