package application;

import java.util.Scanner;

public class negocios {
      
    public static boolean confirmaCadastro(){
        char confirma;
        Scanner scan = new Scanner(System.in);

        System.out.println("Confirma o cadastro? S/N - Sim/Nao");
        confirma = scan.next().charAt(0);
        if( confirma == 'S' || confirma == 's'){
            return true;
        }else{
            return false;
        }
    }
    
}
