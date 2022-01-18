package com.aa.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataRecordTest {

    @Test
    void serialize() throws Exception {
        DataRecord dataRecord1 = new DataRecord();
        dataRecord1.setAge(22);
        dataRecord1.setFirst("Zohn");
        dataRecord1.setLast("Jackson");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(dataRecord1);

        //
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);

        DataRecordTag dataRecordTag = (DataRecordTag) ois.readObject();

        assertEquals(dataRecord1.getAge(), dataRecordTag.getAge());
        assertEquals(dataRecord1.getFirst(), dataRecordTag.getFirst());
        assertEquals(dataRecord1.getLast(), dataRecordTag.getLast());
    }

    @Test
    void unmarshallFromJsonFailsWithNullForInt() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // working
        String json = "{\"first\":\"alan\",\"last\":\"smith\",\"age\":\"55\"}";
        DataRecord dataRecord = objectMapper.readValue(json, DataRecord.class);
        assertEquals("alan", dataRecord.getFirst());
        assertEquals("smith", dataRecord.getLast());
        assertEquals(55, dataRecord.getAge());
        // exception
        String jsonBad = "{\"first\":\"alan\",\"last\":\"smith\",\"age\":null}";
        DataRecord dataRecordBad = objectMapper.readValue(jsonBad, DataRecord.class);
        assertEquals("alan", dataRecordBad.getFirst());
        assertEquals("smith", dataRecordBad.getLast());
        assertEquals(0, dataRecordBad.getAge());

    }

    @Test
    void reflectionSettingNullToPrimitiveThrowsException() throws Exception {
        DataRecord dataRecord = new DataRecord();

        Method method = dataRecord.getClass().getMethod("setAge", int.class);

        method.invoke(dataRecord, new Object[]{null});
    }

}
