//@ Mikkel Sandell

package Domain;

import java.time.LocalTime;

public class Competitions{

    private String convention;
    private String place;
    private LocalTime time;
    public Competitions(String convention, String place, LocalTime time){
        this.convention = convention;
        this.place = place;
        this.time = time;
    }

    public String getConvention() {
        return convention;
    }

    public void setConvention(String convention) {
        this.convention = convention;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return  "Stævets navn: " + convention + '\n' +
                "Placeringen til stævnet: " + place + '\n' +
                "Tiden til stævnet: " + time;
    }
}
