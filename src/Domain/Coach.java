package Domain;

import Files.FileWriteException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String coachNameAndAge() {
        return getName() + ";" + getAge();
    }

    @Override
    public String toString() {
        return "Navn: " + name + '\n' +
                "Alder: " + age;
    }


}
