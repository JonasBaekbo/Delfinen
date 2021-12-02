//@ Mikkel Sandell

package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Competition {

    private String convention;
    private String conventionPlace;
    private LocalTime competitionTime;
    private LocalDate competitionDate;


    public Competition(String convention, String conventionPlace, LocalDate competitionDate, LocalTime competitionTime) {
        this.convention = convention;
        this.conventionPlace = conventionPlace;
        this.competitionTime = competitionTime;
        this.competitionDate = competitionDate;
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

    public LocalDate getCompetitionDate() {
        return competitionDate;
    }

    public void setCompetitionDate(LocalDate competitionDate) {
        this.competitionDate = competitionDate;
    }

    public LocalTime getCompetitionTime() {
        return competitionTime;
    }

    public void setCompetitionTime(LocalTime competitionTime) {
        this.competitionTime = competitionTime;
    }

    @Override
    public String toString() {
        return "Stævnets navn: " + convention + '\n' +
                "Dato for stævne: " + competitionDate + '\n' +
                "Placeringen til stævnet: " + conventionPlace + '\n' +
                "Tiden til stævnet: " + competitionTime;
    }
}
