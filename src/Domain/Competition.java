//@ Mikkel Sandell

package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.lang.String.format;


public class Competition extends Training {
    private String conventionName;
    private String placementInCompetition;

    public Competition(Member member, LocalDate trainingDate, LocalTime trainingTime, String conventionName, String placementInCompetition) {
        super(member, trainingDate, trainingTime);
        this.conventionName = conventionName;
        this.placementInCompetition = placementInCompetition;
    }

    @Override
    public String toString() {
        return member.getName() + ";" + trainingDate + ";" + trainingTime + ";" + conventionName + ";" + placementInCompetition;
    }

}
