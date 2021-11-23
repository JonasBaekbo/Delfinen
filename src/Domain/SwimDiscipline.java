//@Adam Brandt Lasson
package Domain;

public class SwimDiscipline {
    String swimDiscipline;
    int trainingResult;
    int date;
    String tournamentName;
    String tournamentLocation;
    int tournamentTime;

    public SwimDiscipline(String swimDiscipline, int trainingResult, int date){
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
    public void SwimTournament(String tournamentLocation, String tournamentName, int tournamentTime ){
        this.tournamentLocation = tournamentLocation;
        this.tournamentName = tournamentName;
        this.tournamentTime = tournamentTime;
    }
}
