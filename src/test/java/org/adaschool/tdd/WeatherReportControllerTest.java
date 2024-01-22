package org.adaschool.tdd;

import org.adaschool.tdd.controller.weather.WeatherReportController;
import org.adaschool.tdd.controller.weather.dto.NearByWeatherReportsQueryDto;
import org.adaschool.tdd.controller.weather.dto.WeatherReportDto;
import org.adaschool.tdd.repository.document.GeoLocation;
import org.adaschool.tdd.repository.document.WeatherReport;
import org.adaschool.tdd.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherReportControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @Test
    void createWeatherReport() {
        double lat = 4.7110;
        double lng = 74.0721;
        GeoLocation location = new GeoLocation(lat, lng);
        WeatherReportDto weatherReportDto = new WeatherReportDto(location, 35f, 22f, "tester", new Date());
        WeatherReport weatherReport = new WeatherReport(
                weatherReportDto.getGeoLocation(),
                weatherReportDto.getTemperature(),
                weatherReportDto.getHumidity(),
                weatherReportDto.getReporter(),
                new Date());

        when(weatherService.report(any(WeatherReportDto.class))).thenReturn(weatherReport);

        WeatherReport createdReport = weatherReportController.create(weatherReportDto);

        assertThat(createdReport).isEqualTo(weatherReport);
    }

    @Test
    void findById() {
        String reportId = "tester1";
        WeatherReport weatherReport = new WeatherReport(
                new GeoLocation(4.7110, 74.0721),
                35f, 22f, "tester", new Date());
        when(weatherService.findById(reportId)).thenReturn(weatherReport);

        WeatherReport foundReport = weatherReportController.findById(reportId);

        assertThat(foundReport).isEqualTo(weatherReport);
    }

    @Test
    void findNearByReports() {
        NearByWeatherReportsQueryDto queryDto = new NearByWeatherReportsQueryDto(
                new GeoLocation(4.7110, 74.0721), 100f);
        WeatherReport weatherReport = new WeatherReport(
                new GeoLocation(4.7110, 74.0721),
                35f, 22f, "tester", new Date());
        when(weatherService.findNearLocation(queryDto.getGeoLocation(), queryDto.getDistanceRangeInMeters()))
                .thenReturn(Collections.singletonList(weatherReport));

        List<WeatherReport> nearbyReports = weatherReportController.findNearByReports(queryDto);

        assertThat(nearbyReports).containsExactly(weatherReport);
    }

    @Test
    void findByReporterId() {
        String reporterId = "tester";
        WeatherReport weatherReport = new WeatherReport(
                new GeoLocation(4.7110, 74.0721),
                35f, 22f, reporterId, new Date());
        when(weatherService.findWeatherReportsByName(reporterId))
                .thenReturn(Collections.singletonList(weatherReport));

        List<WeatherReport> reportsByReporter = weatherReportController.findByReporterId(reporterId);

        assertThat(reportsByReporter).containsExactly(weatherReport);
    }
}
