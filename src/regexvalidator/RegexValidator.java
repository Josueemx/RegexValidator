/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexvalidator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Morales
 */
public class RegexValidator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hecho por Josue Morales y Diego Dominguez."
                    +    "\nIntroduce cualquier input para empezar o \n\"salir\" para terminar la aplicacion.");
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList results;
        do{
            System.out.print(": ");
            input = scanner.nextLine();
            Validator v = new Validator();
            results = v.validate(input);
            if((boolean) results.get(0))
                System.out.println("Se ha detectado: "+(String)results.get(1));            
            else
                if(!input.equals("salir"))
                    System.out.println("No se introdujo una cadena valida.");            
        }
        while(!input.equals("salir"));
        System.out.println("bye");
    }
    
}
