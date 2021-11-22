package Domain;


import ui.UserInterface;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controller {
    boolean isRunning = true;
    private UserInterface ui = new UserInterface();
    Scanner sc = new Scanner(System.in);
    public void start() throws FileNotFoundException {

        while(isRunning){
            ui.menu();
            switch (ui.userInput()){
                case "0" -> exit();
               case "1" -> createNewMember();
                //case "2" -> logIn();
            }
        }
    }

    public void createNewMember(){
        System.out.println("indtast brugerens navn: ");
        String name = sc.nextLine();
        System.out.println("indtast alderen på brugeren: ");
        int age = sc.nextInt();
        System.out.println("indtast activityformen på brugeren: ");
        String activityForm = sc.nextLine();
        System.out.println("indtast activitylevelet på brugeren: ");
        String activityLevel = sc.nextLine();
        Member m = new Member(name,age,activityForm,activityLevel);
    }



    public void exit(){
        isRunning = false;
    }
}
