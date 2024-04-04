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
                    LimpaTela();
                    identificadorArray = Cadastros( arrayClientes, identificadorArray);
                }
                case 2 -> {
                    LimpaTela();
                    Banco.depositoCliente( arrayClientes );
                }
                case 3 -> {
                    LimpaTela();
                    Banco.saqueCliente( arrayClientes );
                }
                case 4 -> {
                    LimpaTela();
                    Banco.transferenciaValor( arrayClientes );
                }
                case 5 -> System.out.println("Soma total dos valores dos clientes: " + Banco.getSaldoTotal( arrayClientes) );
                case 6 -> Banco.removeCliente( arrayClientes );
                case 7 -> Clientes.mostraClientes( arrayClientes );
                default -> {
                    System.out.println("Modulo nao implementado");
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
                               7- Informacoes clientes
                               0- Sair""");
    }
    
    public static void LimpaTela(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
}
