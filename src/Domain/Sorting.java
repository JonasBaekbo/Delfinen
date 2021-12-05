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
        int result = switch (type) {
            case "age" -> o1.getAge().compareTo(o2.getAge());
            case "name" -> o1.getName().compareTo(o2.getName());
            case "discipline" -> o1.getSwimDiscipline().compareTo(o2.getSwimDiscipline());
            case "time" -> o1.getPracticeTime().compareTo(o2.getPracticeTime());
            default -> 0;
        };
        return result;
    }
}
