package fr.ensim.interop.introrest.model.weather;

public class Main {
    private long temp;
    private long feels_like;
    private long temp_min;
    private long temp_max;
    private int pressure;
    private int humidity;

    Main(){}

    public long getTemp() {
        return temp;
    }

    public void setTemp(long temp) {
        this.temp = temp;
    }

    public long getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(long feels_like) {
        this.feels_like = feels_like;
    }

    public long getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(long temp_min) {
        this.temp_min = temp_min;
    }

    public long getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(long temp_max) {
        this.temp_max = temp_max;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
