package Domain;

import java.util.ArrayList;
import java.util.Objects;

public class SwimTeam {
    public ArrayList<Member> listSwimmersUnderSplitAge(ArrayList<Member> membersList, int splitAge, String swimmingDiscipline) {
        ArrayList<Member> swimmingDisciplineUnderSplitAge = new ArrayList<>();
        for (Member member : membersList) {
            if (Objects.equals(member.getActivityLevel(), "Aktivt")) {
                if (Objects.equals(member.getActivityForm(), "Konkurrence")) {

                    if (member.getTime() != null) {
                        if (member.getSwimmingDiscipline().equalsIgnoreCase(swimmingDiscipline))
                            if (Integer.parseInt(member.getAge()) < splitAge) {
                                swimmingDisciplineUnderSplitAge.add(member);
                            }


                    }
                }
            }
        }
        return swimmingDisciplineUnderSplitAge;
    }

    public ArrayList<Member> listSwimmersOverSplitAge(ArrayList<Member> membersList, int splitAge, String swimmingDiscipline) {
        ArrayList<Member> swimmingDisciplineOverSplitAge = new ArrayList<>();
        for (Member member : membersList) {
            if (Objects.equals(member.getActivityLevel(), "Aktivt")) {
                if (Objects.equals(member.getActivityForm(), "Konkurrence")) {

                    if (member.getTime() != null) {
                        if (member.getSwimmingDiscipline().equalsIgnoreCase(swimmingDiscipline))
                            if (Integer.parseInt(member.getAge()) >= splitAge) {
                                swimmingDisciplineOverSplitAge.add(member);
                            }
                    }
                }
            }
        }
        return swimmingDisciplineOverSplitAge;

    }
}
