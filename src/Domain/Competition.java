//@ Mikkel Sandell

package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Competition {

    private String convention;
    private String conventionPlace;
    private LocalTime conventionTime;
    private LocalDate coventionDate;
    private String coventionStringDate;

    /*public Competition(String convention, String conventionPlace, String coventiondate, LocalTime conventionTime) {
        this.convention = convention;
        this.conventionPlace = conventionPlace;
        this.conventionTime = conventionTime;
        this.coventionStringDate = coventiondate;
    }*/

    public Competition(String convention, String conventionPlace, LocalDate coventiondate, LocalTime conventionTime) {
        this.convention = convention;
        this.conventionPlace = conventionPlace;
        this.conventionTime = conventionTime;
        this.coventionDate = coventiondate;
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

    public LocalDate getCoventiondate() {
        return coventionDate;
    }

    public void setCoventiondate(LocalDate coventiondate) {
        this.coventionDate = coventiondate;
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
                "Dato for stævne: " + coventionDate + '\n' +
                "Placeringen til stævnet: " + conventionPlace + '\n' +
                "Tiden til stævnet: " + conventionTime;
    }
}
