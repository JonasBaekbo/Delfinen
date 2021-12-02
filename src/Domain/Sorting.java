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
        int resultat = switch (type) {
            case "age" -> o1.getAge().compareTo(o2.getAge());
            case "name" -> o1.getName().compareTo(o2.getName());
            case "disciplin" -> o1.getSwimDisciplin().compareTo(o2.getSwimDisciplin());
            case "time" -> o1.getPracticeTime().compareTo(o2.getPracticeTime());
            default -> 0;
        };
        return resultat;
    }
}
