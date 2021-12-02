package Domain;

import java.util.ArrayList;

//TODO omdøbe til memberList?
public class SwimTeam {

    public ArrayList<CompetitionSwimmer> listSwimmersSplitByAge(ArrayList<Member> membersList, int splitAge, DisciplineEnum swimmingDiscipline, boolean overSplitAge) {
        ArrayList<CompetitionSwimmer> swimmingDisciplineSplitByAge = new ArrayList<>();
        for (Member member : membersList) {
            if (member.getActive() == true) {
                CompetitionSwimmer competitionSwimmer = (CompetitionSwimmer) member;
                if (competitionSwimmer.getSwimDisciplin() != null) {
                    if (competitionSwimmer.getPracticeTime() != null) {
                        if (competitionSwimmer.getSwimDisciplin()==swimmingDiscipline) {
                            int memberAge = Integer.parseInt(member.getAge());

                            if (overSplitAge && (memberAge >= splitAge)) {
                                swimmingDisciplineSplitByAge.add((CompetitionSwimmer) member);

                            } else if (!overSplitAge && (memberAge < splitAge)) {
                                swimmingDisciplineSplitByAge.add((CompetitionSwimmer) member);
                            }
                        }
                    }
                }
            }
        }
        return swimmingDisciplineSplitByAge;

    }

    public ArrayList<CompetitionSwimmer> writeTop5Swimmers(ArrayList<CompetitionSwimmer> membersList) {
        ArrayList<CompetitionSwimmer> top5Swimmers = new ArrayList<>();
        membersList.sort(new Sorting("time"));
        int min = Math.min(membersList.size(), 5);
        for (int i = 0; i < min; i++) {
            top5Swimmers.add(membersList.get(i));
        }
        return top5Swimmers;
    }
}
