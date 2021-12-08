// @Jonas Bækbo, Johanne Riis-Weitling
package Domain;

import Files.FileHandler;

import java.util.ArrayList;

public class SwimTeam {
    private FileHandler files = new FileHandler();

    public ArrayList<Training> getTimesForSwimmer(String name) {
        ArrayList<Training> result = new ArrayList<>();
        CompetitionSwimmer swimmer = files.findCompetitionSwimmerByName(files.getCompetitionSwimmers(), name);
        ArrayList<Training> times = files.getAllSavedTimes();
        if (swimmer != null) {
            return swimmer.getTimes(times);
        }
        return result;
    }

    public ArrayList<Training> getDisciplineResultsSplitByAge(ArrayList<Training> times, DisciplineEnum swimDiscipline, int splitAge, boolean overSplitAge) {
        try {
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
                            bestTime = swimmer.getBestTime(times);
                            if (bestTime != null) {
                                result.add(swimmer.getBestTime(times));
                            }
                            // Medlemmet er yngre end "splitAge" og vi ønsker at se medlemmer under den alder
                        } else if (!overSplitAge && (memberAge < splitAge)) {
                            bestTime = swimmer.getBestTime(times);
                            if (bestTime != null) {
                                result.add(swimmer.getBestTime(times));
                            }
                        }
                    }
                }
            }
            return result;
        } catch (NumberFormatException e) {
            return null;
        }
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