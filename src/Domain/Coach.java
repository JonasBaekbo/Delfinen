//@Mikkel Sandell

package Domain;

public class Coach {
    private String name;
    private String age;

    public Coach(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getStringForSaving() {
        return getName() + ";" + getAge();
    }

    @Override
    public String toString() {
        return "Navn: " + name + '\n' +
                "Alder: " + age;
    }
}
