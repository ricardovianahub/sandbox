package com.aa;

import java.io.IOException;

public class TestUtils {

    public static String retrieveBaseURL() {
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec("docker port reverse");
            String[] output = (new String(process.getInputStream().readAllBytes())).replaceAll("\n", "").split(":");
            return "http://localhost:" + output[output.length - 1];
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}
