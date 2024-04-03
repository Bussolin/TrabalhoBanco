package entities;

import java.util.Scanner;

public class Banco {
        
    private long numeroConta;
    private float saldo;

    @Override
    public String toString() {
        return "Numero da Conta: " + numeroConta + "\nSaldo: " + saldo;
    }
    
    public Banco(long numeroConta ) {
        this.numeroConta = numeroConta;
        this.saldo = 0f;
    }
    
    //Banco
    public static void adicionaContaCliente( Clientes cliente ){
        Scanner scan = new Scanner(System.in);
        long numeroConta;
        int tipoConta;

        System.out.println("Qual o tipo de conta?");
        System.out.println("""
                           1-Poupanca
                           2-Corrente
                           3-Investimento
                           """);
        tipoConta = scan.nextInt();
        if( tipoConta > 3 || tipoConta < 1){
            System.out.println("Tipo de conta inexsistente");
            
        }else{
            System.out.println("Digite o numero da conta: ");
            numeroConta = scan.nextInt();

            Banco conta = new Banco( numeroConta );

            if( cliente.getContas( tipoConta ) == null ){
                cliente.setContas( conta, tipoConta - 1 );
                System.out.println("\nConta criada\n");
                System.out.println(cliente.getContas(tipoConta - 1));
            }
        }

         
    }
    
    //Contas
    public static int  escolherConta ( Clientes[] cliente, int indice ){
        
        if(indice == -1){
            return -1;
        }
        
        for(int i = 0; i < 3; i++){
            Banco conta = cliente[indice].getContas(i);
            if(  !(conta == null) ){
                System.out.println(i + 1 + ": ");
                System.out.println(conta );
            }
            
        }
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Escolha a conta: ");
        int indiceConta = scan.nextInt();
        
        return indiceConta - 1;
    }
    
    public static boolean removeClient(){
        //retira a conta do cliente
        return false;
    }
    //Necessario trocar regra de conta do cliente/saldo em todo e qualquer lugar
    public static boolean DepositoCliente( Clientes[] arrayCliente){
        Scanner scan = new Scanner(System.in);

        int indiceCliente = Clientes.procuraCliente(arrayCliente);
        int indiceConta = escolherConta( arrayCliente, indiceCliente );
        if( indiceCliente != -1 && indiceConta != -1){
            System.out.println("Valor a depositar:");
            arrayCliente[indiceCliente].depositar(indiceConta, scan.nextFloat());
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
            //arrayCliente[indiceCliente2].depositar( valorTransferido);
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

    public long getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(long numeroConta) {
        this.numeroConta = numeroConta;
    }
    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
}
