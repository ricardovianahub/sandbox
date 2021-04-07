package com.aa.targetendpoint;

import java.io.IOException;

public class EndPointResolver {

    public static String retrieveBaseURL() {
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec("docker port reverse");
            String[] output = (new String(process.getInputStream().readAllBytes())).replaceAll("\n", "").split(":");
            if (!output[0].startsWith("80/tcp")) {
                throw new RuntimeException("ERROR ACCESSING REVERSE PROXY PORT - Is docker compose up and running? Does your user have access to the docker group?");
            }
            return "http://localhost:" + output[output.length - 1];
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}
