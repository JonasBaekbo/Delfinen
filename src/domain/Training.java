//@Johanne Riis-Weitling, Mikkel Sandell
package domain;

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

    public String stringForSaving() {
        return member.getName() + ";" + trainingDate + ";" + trainingTime;
    }

    @Override
    public String toString() {
        return "Medlemsnavn: " + member.getName() + '\n' +
                "Dato: " + trainingDate + '\n' +
                "Træningstid: " + trainingTime + '\n' +
                "----------------------------------------------\n";
    }

    public String getMemberName() {
        return member.getName();
    }

    public String informationToTable() {
        String firstPartOfTable = member.informationToTable();
        String secondPartOfTable = format("%-20s", trainingTime);

        return firstPartOfTable + secondPartOfTable;
    }
}