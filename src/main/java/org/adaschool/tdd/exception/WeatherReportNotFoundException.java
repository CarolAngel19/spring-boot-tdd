package org.adaschool.tdd.exception;

public class WeatherReportNotFoundException extends RuntimeException {
    public WeatherReportNotFoundException(String message) {
        super(message);
    }
}
