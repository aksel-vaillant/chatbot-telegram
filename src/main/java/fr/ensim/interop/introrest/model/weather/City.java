package fr.ensim.interop.introrest.model.weather;

public class City {
    private String name;
    private Double lat;
    private Double lon;
    private String country;
    private String exclude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setExclude(){ this.exclude = exclude; }

    public String getExclude() { return exclude; }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
