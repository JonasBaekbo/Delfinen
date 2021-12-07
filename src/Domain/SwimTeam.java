// @Jonas Bækbo, Johanne Riis-Weitling
package Domain;

import Files.FileHandler;

import java.util.ArrayList;

public class SwimTeam {
    private final FileHandler files = new FileHandler();

    public ArrayList<Training> getTimesForSwimmer(String name) {
        ArrayList<Training> result = new ArrayList<>();
        CompetitionSwimmer swimmer = files.findCompetitionSwimmerByName(files.getCompetitionSwimmers(), name);
        if (swimmer != null) {
            return swimmer.getTimes();
        }
        return result;
    }

    public ArrayList<Training> getDisciplineResultsSplitByAge(DisciplineEnum swimDiscipline, int splitAge, boolean overSplitAge) {
        ArrayList<CompetitionSwimmer> swimmers = files.getCompetitionSwimmers();
        ArrayList<Training> result = new ArrayList<>();

        for (CompetitionSwimmer swimmer : swimmers) {
            DisciplineEnum discipline = swimmer.getDiscipline();
            int memberAge = Integer.parseInt(swimmer.getAge());
            if (swimmer.getActive()) {
                if (discipline == swimDiscipline) {
                    Training bestTime = null;

                    // Medlemmet er ældre end "splitAge" og vi ønsker at se medlemmer over den alder
                    if (overSplitAge && (memberAge >= splitAge)) {
                        bestTime = swimmer.getBestTime();
                        if (bestTime != null) {
                            result.add(swimmer.getBestTime());
                        }
                        // Medlemmet er yngre end "splitAge" og vi ønsker at se medlemmer under den alder
                    } else if (!overSplitAge && (memberAge < splitAge)) {
                        bestTime = swimmer.getBestTime();
                        if (bestTime != null) {
                            result.add(swimmer.getBestTime());
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Training> writeTop5Times(ArrayList<Training> times) {
        ArrayList<Training> top5Times = new ArrayList<>();

        times.sort(new Sorting());

        // Sæt 'min' til antal medlemmer i 'membersList', men dog højest 5
        int min = Math.min(times.size(), 5);
        for (int i = 0; i < min; i++) {
            top5Times.add(times.get(i));
        }
        return top5Times;
    }
}
