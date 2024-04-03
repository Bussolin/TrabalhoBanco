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
            System.out.println("""
                               1- Cadastros
                               2- Depositar valor
                               3- Sacar valor
                               4- Transferencia de valores entre contas
                               5- Soma total dos valores dos clientes
                               6- Remover um cliente
                               0- Sair""");
            modulo = scan.nextInt();
            switch (modulo) {
                case 1 -> {
                    identificadorArray = Cadastros( arrayClientes, identificadorArray);
                }
                case 2 -> {
                    if(identificadorArray != 0  ){
                        if( Banco.DepositoCliente( arrayClientes ) ){
                            System.out.println("Deposito realizado com sucesso");
                        }else{
                            System.out.println("Cliente ou conta nao encontrado");
                        }
                    }else{
                        System.out.println("Cadastre um cliente antes de fazer uma acao");
                    }
                }
                case 3 -> {
                    if(identificadorArray != 0  ){
                        if( Banco.SaqueCliente( arrayClientes ) ){
                            System.out.println("Saque realizado com sucesso");
                        }
                    }else{
                        System.out.println("Cadastre um cliente antes de fazer uma acao");
                    }
                }
                case 4 -> {
                    if( Banco.transferenciaValor( arrayClientes )) {
                        System.out.println("Transferencia concluida com sucesso");
                    }else{
                        System.out.println("Falha ao transferir saldo");
                    }
                }
                case 5 -> System.out.println("Soma total dos valores dos clientes: " + Banco.getSaldoTotal( arrayClientes) );
                case 6 -> Banco.removeClient();
                default -> {
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
                identificadorArray++;
               
            }
            case 2 ->{
                int indice = Clientes.procuraCliente( clientes );
                Banco.adicionaContaCliente( clientes[indice]);
                
            }
        }
        return identificadorArray;
    }
    
}
