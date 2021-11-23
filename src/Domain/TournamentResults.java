//@Adam Brandt Lasson

package Domain;

public class TournamentResults {
    String tournamentName;
    String tournamentLocation;
    int tournamentTime;

    public TournamentResults(String tournamentLocation, String tournamentName, int tournamentTime) {
        this.tournamentLocation = tournamentLocation;
        this.tournamentName = tournamentName;
        this.tournamentTime = tournamentTime;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getTournamentLocation() {
        return tournamentLocation;
    }

    public void setTournamentLocation(String tournamentLocation) {
        this.tournamentLocation = tournamentLocation;
    }

    public int getTournamentTime() {
        return tournamentTime;
    }

    public void setTournamentTime(int tournamentTime) {
        this.tournamentTime = tournamentTime;
    }
}
