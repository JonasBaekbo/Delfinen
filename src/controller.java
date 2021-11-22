import java.util.Scanner;

public class controller {
    Scanner sc = new Scanner(System.in);
    public void createNewMamber(){
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
}
