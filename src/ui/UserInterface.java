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

    public void MaineMenu(){
        printMessage("""
                
                1) Formands menu
                2) Træner menu
                3) Kassér menu
                0) Slut programmet
                """);

    }
    public void menuCEO(){
        printMessage("""
                    
                    Formand
                    Foretag et valg:
                    1) Registrer nyt medlem
                    2) Registrer ny træner
                    0) Tilbage til hovedmenu""");
    }
    public void menuCoach(){
        printMessage("""
                    
                    Træner
                    Foretag et valg:
                    1) Tilføj tid til medlem
                    2) Registrer bedste træningsresultat for svømmer
                    3) Registrer resultat til stævne for svømmer
                    4) Se top 5 svømmere indenfor hver svømmedisciplin
                    0) Tilbage til hovedmenu""");
    }
    public void menuTreasurer(){
        printMessage(""" 
                      
                    Kassér
                    1) Opret kontingent opkrævning
                    2) Register indbetaling
                    3) Se forventet indbetaling af kontingentet
                    4) Se oversigt over medlemmer i restance
                    0) Tilbage til hovedmenu""");
    }

}

