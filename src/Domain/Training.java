//@Johanne Riis-Weitling, Mikkel Sandell

package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.lang.String.format;

public class Training {
    protected Member member;
    protected LocalTime trainingTime;
    protected LocalDate trainingDate;

    public Training(Member member, LocalDate trainingDate, LocalTime trainingTime) {
        this.member = member;
        this.trainingTime = trainingTime;
        this.trainingDate = trainingDate;
    }


    public LocalTime getTrainingTime() {
        return trainingTime;
    }

    @Override
    public String toString() {
        return member.getName() + ";" + trainingDate + ";" + trainingTime;
    }

    public String getMemberName() {
        return member.getName();
    }


    public String informationToTable() {
       String firstPartOfTable= member.informationToTable();
        String secondPartOfTable=format("%-20s",trainingTime);

        return firstPartOfTable+secondPartOfTable;
    }
}


