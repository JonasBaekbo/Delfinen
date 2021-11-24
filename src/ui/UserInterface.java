// @JonasBaekbo

package ui;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    public void menu(){
        printMessage("""
                                        
                    Foretag et valg:
                    1) Registrer nyt medlem (Formand)
                    2) Registrer ny træner (Formand)
                    3) Tilføj tid til medlem (Træner)
                    4) Registrer bedste træningsresultat for svømmer (Træner)
                    5) Registrer resultat til stævne for svømmer (Træner)
                    6) Se top 5 svømmere indenfor hver svømmedisciplin (Træner)
                    7) Opret kontingent opkrævning (Kasser)
                    8) Register indbetaling (Kasser)
                    9) Se forventet indbetaling af kontingentet (Kasser)
                    10) Se oversigt over medlemmer i restance (Kasser)
                    0) Slut programmet""");
    }
    public String userInput(){

        return scanner.nextLine();
    }

    public void printMessage(String s) {
        System.out.println(s);
    }
}

