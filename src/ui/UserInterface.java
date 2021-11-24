// @JonasBaekbo

package ui;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    public String userInput(){

        return scanner.nextLine();
    }

    public void printMessage(String s) {
        System.out.println(s);
    }

    public void Mainemenu(){
        printMessage("""
                1) Formands menu
                2) Træner menu
                3) Kasser menu
                0) Slut programmet
                """);

    }
    public void menuCEO(){
        printMessage("""            
                    Foretag et valg:
                    1) Registrer nyt medlem (Formand)
                    2) Registrer ny træner (Formand)
                    0) Tilbage til hovedmenu""");
    }
    public void menuCoach(){
        printMessage("""            
                    Foretag et valg:
                    1) Tilføj tid til medlem (Træner)
                    2) Registrer bedste træningsresultat for svømmer (Træner)
                    3) Registrer resultat til stævne for svømmer (Træner)
                    4) Se top 5 svømmere indenfor hver svømmedisciplin (Træner)
                    0) Tilbage til hovedmenu""");
    }
    public void menuTreasurer(){
        printMessage("""                
                    Foretag et valg:
                    1) Opret kontingent opkrævning (Kasser)
                    2) Register indbetaling (Kasser)
                    3) Se forventet indbetaling af kontingentet (Kasser)
                    4) Se oversigt over medlemmer i restance (Kasser)
                    0) Tilbage til hovedmenu""");
    }

}

