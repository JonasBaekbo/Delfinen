//@Mikkel Sandell, @Jonas Bækbo
package Domain;

import java.util.Comparator;

public class Sorting implements Comparator<Training> {
    @Override
        public int compare(Training o1, Training o2) {
        if ((o1 != null) && (o2 != null)) {
            return o1.getTrainingTime().compareTo(o2.getTrainingTime());
        } else {
            return 0;
        }
    }
}