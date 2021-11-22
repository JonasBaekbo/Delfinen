import Domain.Member;

import java.util.Comparator;

public class Sorting implements Comparator<Member> {
    private String type;

    public Sorting(String type) {
        this.type = type;
    }


    @Override
    public int compare(Member o1, Member o2) {
        int resultat = 0;
        if (type.equals("age")) {
            resultat = Integer.compare(o1.getAge(), o2.getAge());
        } else if (type.equals("name")) {
            resultat = o1.getName().compareTo(o2.getName());
        } else if (type.equals("activityForm")) {
            resultat = o1.getActivityForm().compareTo(o2.getActivityForm());
        } else if (type.equals("activityLevel")) {
            resultat = o1.getActivityLevel().compareTo(o2.getActivityLevel());
        }
        return resultat;
    }
}
