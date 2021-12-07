//@ Mikkel Sandell

package Domain;

import java.time.LocalDate;
import java.time.LocalTime;


public class Competition extends Training {
    private String competitionName;
    private String placementInCompetition;

    public Competition(Member member, LocalDate trainingDate, LocalTime trainingTime, String competitionName, String placementInCompetition) {
        super(member, trainingDate, trainingTime);
        this.competitionName = competitionName;
        this.placementInCompetition = placementInCompetition;
    }

    //TODO: tilføj til diagram
    public String StringForSavning() {
        return member.getName() + ";" + trainingDate + ";" + trainingTime + ";" + competitionName + ";" + placementInCompetition;
    }

    @Override
    public String toString() {
        return "Medlemsnavn: " + member.getName() + '\n' +
                "Stævne: " + competitionName + '\n' +
                "Placering: " + placementInCompetition + '\n' +
                "Dato: " + trainingDate + '\n' +
                "Stævnetid: " + trainingTime + '\n' +
                "----------------------------------------------\n";
    }
}
