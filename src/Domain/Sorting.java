//@Mikkel Sandell, @Jonas Bækbo

package Domain;

import java.util.Comparator;

public class Sorting implements Comparator<CompetitionSwimmer> {
    private String type;

    public Sorting(String type) {
        this.type = type;
    }


    @Override
    public int compare(CompetitionSwimmer o1, CompetitionSwimmer o2) {
        int resultat = 0;
        if (type.equals("age")) {
            resultat = o1.getAge().compareTo(o2.getAge());
        } else if (type.equals("name")) {
            resultat = o1.getName().compareTo(o2.getName());
        } /*else if (type.equals("activityForm")) {
            resultat = o1.getActivityForm().compareTo(o2.getActivityForm());
         else if (type.equals("activityLevel")) {
            resultat = o1.getActive().compareTo(o2.getActive());*/ else if (type.equals("diciplin")) {
            resultat = o1.getSwimDisciplin().compareTo(o2.getSwimDisciplin());
        } else if (type.equals("time")) {
            resultat = o1.getSwimTime().compareTo(o2.getSwimTime());
        }
        return resultat;
    }
}
