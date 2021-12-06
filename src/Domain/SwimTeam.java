// @Jonas Bækbo, Johanne Riis-Weitling
package Domain;

import java.util.ArrayList;

public class SwimTeam {

    public ArrayList<CompetitionSwimmer> listSwimmersSplitByAge(ArrayList<Member> membersList, int splitAge, DisciplineEnum swimDiscipline, boolean overSplitAge) {
        ArrayList<CompetitionSwimmer> swimDisciplineSplitByAge = new ArrayList<>();
        for (Member member : membersList) {
            if (member.getActive()) {
                CompetitionSwimmer competitionSwimmer = (CompetitionSwimmer) member;
                if (competitionSwimmer.getSwimDiscipline() != null) {
                    if (competitionSwimmer.getPracticeTime() != null) {
                        if (competitionSwimmer.getSwimDiscipline() == swimDiscipline) {
                            int memberAge = Integer.parseInt(member.getAge());
                            // Medlemmet er ældre end "splitAge" og vi ønsker at se medlemmer over den alder
                            if (overSplitAge && (memberAge >= splitAge)) {
                                swimDisciplineSplitByAge.add((CompetitionSwimmer) member);
                            // Medlemmet er yngre end "splitAge" og vi ønsker at se medlemmer under den alder
                            } else if (!overSplitAge && (memberAge < splitAge)) {
                                swimDisciplineSplitByAge.add((CompetitionSwimmer) member);
                            }
                        }
                    }
                }
            }
        }
        return swimDisciplineSplitByAge;
    }

    public ArrayList<CompetitionSwimmer> writeTop5Swimmers(ArrayList<CompetitionSwimmer> membersList) {
        ArrayList<CompetitionSwimmer> top5Swimmers = new ArrayList<>();
        membersList.sort(new Sorting("time"));
        // Sæt 'min' til antal medlemmer i 'membersList', men dog højest 5
        int min = Math.min(membersList.size(), 5);
        for (int i = 0; i < min; i++) {
            top5Swimmers.add(membersList.get(i));
        }
        return top5Swimmers;
    }
}
