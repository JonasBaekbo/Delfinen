//@Mikkel Sandell

package Domain;

public class Coach {
    private String name;
    private String age;
    private DisciplineEnum swimDiscipline;

    public Coach(String name, String age, DisciplineEnum swimDiscipline) {
        this.name = name;
        this.age = age;
        this.swimDiscipline = swimDiscipline;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public DisciplineEnum getSwimDiscipline() {
        return swimDiscipline;
    }

    public String stringForSaving() {
        return getName() + ";" + getAge() + ";" + getSwimDiscipline();
    }

    @Override
    public String toString() {
        return "Navn: " + name + '\n' +
                "Alder: " + age + '\n' +
                "Svømmedisciplin: " + swimDiscipline + '\n' +
                "----------------------------------------------\n";
    }
}
