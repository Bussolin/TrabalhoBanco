package entities;

import java.util.Scanner;

public class Banco {
        
        private long numeroConta;
        private float saldo;
            
        public static void addClient( Clientes cliente ){
        //adiciona conta no cliente   
        }
        public static boolean removeClient(){
            //retira a conta do cliente
            return false;
        }
        public static boolean DepositoCliente( Clientes[] arrayCliente){
            int indice;
            Scanner scan = new Scanner(System.in);
            
            indice = Clientes.procuraCliente( arrayCliente );
            if( indice != -1){
                System.out.println("Valor a depositar:");
                arrayCliente[indice].depositar(scan.nextFloat());
                return true;
            }
            return false;
        }
        public static boolean SaqueCliente( Clientes[] arrayCliente){
            int indice;
            Scanner scan = new Scanner(System.in);

            indice = Clientes.procuraCliente( arrayCliente );
            if( indice != -1){
                System.out.println("Valor a sacar:");
                if( !arrayCliente[indice].sacar(scan.nextFloat())){
                     System.out.println("Saldo insuficiente");
                     return false;
                }             
                return true;
            }
            return false;
        }
        public static boolean transferenciaValor( Clientes[] arrayCliente){
            int indiceCliente1, indiceCliente2;
            float valorTransferido;
            Scanner scan = new Scanner(System.in);
            
            System.out.println("Cliente que ira transferir: ");
            indiceCliente1 = Clientes.procuraCliente( arrayCliente );
           if( indiceCliente1 == -1){
               return false;
           }
           System.out.println("Cliente que recebera valor: ");
           indiceCliente2 = Clientes.procuraCliente( arrayCliente );
           if( indiceCliente2 == -1){
               return false;
           }
            
            System.out.println("Valor a ser depositado:");
            valorTransferido = scan.nextFloat();
            
            if (arrayCliente[indiceCliente1].sacar( valorTransferido ) ) {
                arrayCliente[indiceCliente2].depositar( valorTransferido);
                return true;
            }else{
                return false;
            }
        }
        public static float getSaldoTotal( Clientes[] arrayClientes ){
            float saldoTotal = 0;
            
            for( Clientes cliente : arrayClientes){
                if( cliente != null ){
                    saldoTotal += cliente.getSaldo();
                }
            }
            return saldoTotal;
        }
}
