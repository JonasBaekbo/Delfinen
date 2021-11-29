package Domain;

import java.util.ArrayList;
import java.util.Objects;

public class SwimTeam {

    public ArrayList<Member> listSwimmersSplitByAge(ArrayList<Member> membersList, int splitAge, String swimmingDiscipline, boolean overSplitAge) {
        ArrayList<Member> swimmingDisciplineSplitByAge = new ArrayList<>();
        for (Member member : membersList) {
            if (Objects.equals(member.getActivityLevel(), "Aktivt")) {
                if (Objects.equals(member.getActivityForm(), "Konkurrence")) {
                    if (member.getTime() != null) {

                        if (member.getSwimmingDiscipline().equalsIgnoreCase(swimmingDiscipline)) {
                            int memberAge = Integer.parseInt(member.getAge());

                            if (overSplitAge && (memberAge >= splitAge)) {
                                swimmingDisciplineSplitByAge.add(member);

                            } else if (!overSplitAge && (memberAge < splitAge)) {
                                swimmingDisciplineSplitByAge.add(member);
                            }
                        }
                    }
                }
            }
        }
        return swimmingDisciplineSplitByAge;

    }
}
