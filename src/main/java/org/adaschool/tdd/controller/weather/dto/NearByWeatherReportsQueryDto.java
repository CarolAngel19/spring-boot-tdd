package org.adaschool.tdd.controller.weather.dto;

import org.adaschool.tdd.repository.document.GeoLocation;

public class NearByWeatherReportsQueryDto
{

    private final GeoLocation geoLocation;

    private final float distanceRangeInMeters;

    public NearByWeatherReportsQueryDto( GeoLocation geoLocation, float distanceRangeInMeters )
    {
        this.geoLocation = geoLocation;
        this.distanceRangeInMeters = distanceRangeInMeters;
    }

    public GeoLocation getGeoLocation()
    {
        return geoLocation;
    }

    public float getDistanceRangeInMeters()
    {
        return distanceRangeInMeters;
    }
}