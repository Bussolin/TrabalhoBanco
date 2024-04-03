package application;

import entities.Clientes;
import entities.Banco;
import java.util.Scanner;

public class application {
    public static void main(String[] args) {
        Clientes[] arrayClientes = new Clientes[100];
        Scanner scan = new Scanner(System.in);
        int identificadorArray = 0,
            modulo;
        do {
            Menu();
            modulo = scan.nextInt();
            
            switch (modulo) {
                case 1 -> {
                    identificadorArray = Cadastros( arrayClientes, identificadorArray);
                }
                case 2 -> {
                    Banco.DepositoCliente( arrayClientes );
                }
                case 3 -> {
                    Banco.SaqueCliente( arrayClientes );
                }
                case 4 -> {
                    Banco.transferenciaValor( arrayClientes );
                }
                case 5 -> System.out.println("Soma total dos valores dos clientes: " + Banco.getSaldoTotal( arrayClientes) );
                case 6 -> Banco.removeClient();
                default -> {
                    System.out.println("Modulo nao implementado");;
                }
            }
        } while( modulo != 0  );
    }
    
    public static int Cadastros( Clientes[] clientes, int identificadorArray ){
        Scanner scan = new Scanner(System.in);
        
        
        System.out.println("------CADASTRO------");
        System.out.println("1- Cadastro de Cliente \n2- Abrir uma nova conta");
        int modulo = scan.nextInt();
        switch( modulo){
            case 1 ->{
                clientes[ identificadorArray ] = Clientes.adicionaCliente();
                if(clientes[ identificadorArray ] != null){
                    identificadorArray++;                    
                }
            }
            case 2 ->{
                int indice = Clientes.procuraCliente( clientes );
                Banco.adicionaContaCliente( clientes[indice]);
            }
        }
        return identificadorArray;
    }
    
    public static void Menu(){
        System.out.println("""
                               1- Cadastros
                               2- Depositar valor
                               3- Sacar valor
                               4- Transferencia de valores entre contas
                               5- Soma total dos valores dos clientes
                               6- Remover um cliente
                               0- Sair""");
    }
}
