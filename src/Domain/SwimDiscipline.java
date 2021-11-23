//@Adam Brandt Lasson
package Domain;

import java.util.ArrayList;

public class SwimDiscipline {
    String swimDiscipline; //e.g. butterfly, crawl, rygcrawl, brystsvømning.
    int trainingResult; //bedste træningsresultat.
    int date;


    public SwimDiscipline(String swimDiscipline, int trainingResult, int date) {
        this.swimDiscipline = swimDiscipline;
        this.trainingResult = trainingResult;
        this.date = date;
    }

    public String getSwimDiscipline() {
        return swimDiscipline;
    }

    public void setSwimDiscipline(String swimDiscipline) {
        this.swimDiscipline = swimDiscipline;
    }

    public int getTrainingResult() {
        return trainingResult;
    }

    public void setTrainingResult(int trainingResult) {
        this.trainingResult = trainingResult;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}

