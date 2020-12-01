package org.techtown.chabak;

public class AttractionData {
    private String Attraction_name;
    private String latitude;
    private String longitude;
    public String getAttraction_name(){
        return Attraction_name;
    }
    public String getLatitude(){
        return latitude;
    }
    public String getLongitude(){
        return longitude;
    }
    public void setAttraction_name(String Attraction_name){
        this.Attraction_name = Attraction_name;
    }
    public void setAttraction_latitude(String latitude){
        this.latitude = latitude;
    }
    public void setAttraction_longitude(String longitude){
        this.longitude = longitude;
    }
}
