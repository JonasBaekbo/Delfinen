//@ Mikkel Sandell

package Domain;

import java.time.LocalTime;

public class Competition {

    private String convention;
    private String conventionPlace;
    private LocalTime conventionTime;
    //TODO tilføj dato til stævne

    public Competition(String convention, String conventionPlace, LocalTime conventionTime) {
        this.convention = convention;
        this.conventionPlace = conventionPlace;
        this.conventionTime = conventionTime;
    }

    public String getConvention() {
        return convention;
    }

    public void setConvention(String convention) {
        this.convention = convention;
    }

    public String getConventionPlace() {
        return conventionPlace;
    }

    public void setConventionPlace(String conventionPlace) {
        this.conventionPlace = conventionPlace;
    }

    public LocalTime getConventionTime() {
        return conventionTime;
    }

    public void setConventionTime(LocalTime conventionTime) {
        this.conventionTime = conventionTime;
    }

    @Override
    public String toString() {
        return "Stævnets navn: " + convention + '\n' +
                "Placeringen til stævnet: " + conventionPlace + '\n' +
                "Tiden til stævnet: " + conventionTime;
    }
}
