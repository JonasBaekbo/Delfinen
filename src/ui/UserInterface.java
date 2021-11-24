// @JonasBaekbo

package ui;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    public void menu(){
        printMessage("""
                                        
                    Foretag et valg:
                    1) Registrer nyt medlem (Formand)
                    2) Se forventet indbetaling af kontingentet (Kasser)
                    3) Se oversigt over medlemmer i restance (Kasser)
                    4) Tilføj tid til medlem (Træner)
                    5) Se top 5 svømmere indenfor hver svømmedisciplin (Træner)
                    6) Registrer bedste træningsresultat for svømmer (Træner)
                    7) Registrer resultat til stævne for svømmer (Træner)
                    8) Opret kontingent opkrævning (Kasser)
                    9) Register indbetaling (Kasser)
                    0) Slut programmet""");
    }
    public String userInput(){

        return scanner.nextLine();
    }

    public void printMessage(String s) {
        System.out.println(s);
    }
}

