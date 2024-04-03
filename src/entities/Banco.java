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
            
            Banco[] contas = cliente.getContas();
            
            if( contas[tipoConta - 1] == null ){
                contas[ tipoConta - 1 ] = new Banco( numeroConta );
                cliente.setContas( contas );
                
                System.out.println("\nConta criada\n");
                System.out.println( contas[tipoConta - 1] + "\n" );
            }
        }
    }
    
    public static boolean removeClient(){
        //retira a conta do cliente
        return false;
    }
    
    public static int  escolherConta ( Clientes[] cliente, int indiceCliente ){
        
        int contas = 0;
        Banco[] contasArray = cliente[indiceCliente].getContas();
        
        for(int i = 0; i < 3; i++){
            if(  !(contasArray[i] == null) ){
                System.out.println(i + 1 + ": ");
                System.out.println( contasArray[i] );
                contas++;
            }
        }
        
        if ( contas == 0){
            System.out.println("Nenhuma conta cadastrada");
            return -1;
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Escolha a conta: ");
        int indiceConta = scan.nextInt();
        if( indiceConta <= 0 ){
            return -1;
        }else if( contasArray[indiceConta -1] != null ){
            return indiceConta - 1;
        }else{
            return -1;
        }
        
    }
    
    public static void DepositoCliente( Clientes[] arrayCliente){
        Scanner scan = new Scanner(System.in);
        
        int indiceCliente = Clientes.procuraCliente(arrayCliente);
        if( indiceCliente == -1){
            return;
        }
        
        int indiceConta = escolherConta( arrayCliente, indiceCliente );
        if( indiceConta != -1){
            System.out.println("Valor a depositar:");
            arrayCliente[indiceCliente].depositar(indiceConta, scan.nextFloat());
            System.out.println("Deposito realizado com sucesso");
            System.out.println("\nDados atualizado");
            System.out.println( arrayCliente[indiceCliente].getContas()[ indiceConta ] + "\n");

        }
    }
    
    //Necessario trocar regra de conta do cliente/saldo em todo e qualquer lugar
    public static void SaqueCliente( Clientes[] arrayCliente){
        int indiceCliente = Clientes.procuraCliente( arrayCliente );
        if( indiceCliente == -1){
            return;
        }
        float saldoAntigo = arrayCliente[indiceCliente].getSaldo();
        
        Scanner scan = new Scanner(System.in);
        
        int indiceConta = escolherConta(arrayCliente, indiceCliente );
        if (indiceConta != -1 ) {
        
            System.out.println("Valor a sacar:");
            float valorSacar = scan.nextFloat();
            arrayCliente[indiceCliente].sacar( indiceConta, valorSacar );
        
        }
        if( saldoAntigo == arrayCliente[indiceCliente].getContas()[ indiceConta ].getSaldo() ){
            System.out.println("Saldo insuficiente");
        }else{
            System.out.println("Saque realizado com sucesso");
            System.out.println("\nDados atualizado");
            System.out.println( arrayCliente[indiceCliente].getContas()[ indiceConta ] + "\n");
        }
    }
    //Necessario trocar regra de conta do cliente/saldo em todo e qualquer lugar
    public static void transferenciaValor( Clientes[] arrayCliente){
        int indiceCliente1, indiceCliente2, indiceContaOrigem, indiceContaDestino;

        System.out.println("\n------TRANSFERÊNCIA------");
        System.out.println("Cliente origem: ");
        indiceCliente1 = Clientes.procuraCliente( arrayCliente );
        indiceContaOrigem = escolherConta(arrayCliente, indiceCliente1);
        if( indiceCliente1 == -1){
            return;
        }
        
        System.out.println("Cliente destino: ");
        indiceCliente2 = Clientes.procuraCliente( arrayCliente );
        indiceContaDestino = escolherConta(arrayCliente, indiceCliente2);
        if( indiceCliente2 == -1){
            return;
        }
        
        float valorTransferido, saldoAntigo;
        Scanner scan = new Scanner(System.in);

        System.out.println("Valor a ser transferido:");
        valorTransferido = scan.nextFloat();
        saldoAntigo = arrayCliente[indiceCliente1].getContas()[ indiceContaOrigem ].getSaldo();
        //arrayCliente[indiceCliente1].sacar( valorTransferido );
        
        if ( !(saldoAntigo == arrayCliente[indiceCliente1].getContas()[ indiceContaOrigem ].getSaldo() ) ) {
            arrayCliente[indiceCliente2].depositar( indiceContaDestino, valorTransferido );
            System.out.println("Transferencia concluida com sucesso");
            System.out.println("\nDados atualizado");
            System.out.println( arrayCliente[indiceCliente2].getContas()[ indiceContaOrigem ] + "\n");
            
            System.out.println("\nDados atualizado");
            System.out.println( arrayCliente[indiceCliente1].getContas()[ indiceContaDestino ] + "\n");
        }else{
            System.out.println("Saldo insuficiente");
        }
    }
    public static float getSaldoTotal( Clientes[] arrayClientes ){
        float saldoTotal = 0;

        for( Clientes cliente : arrayClientes){
            if( cliente == null ){
                return saldoTotal;
            }
            Banco[] contas = cliente.getContas();
            for( int i = 0; i < 3; i++ ){
                    if( contas[i]  == null ){
                        continue;
                    }
                    saldoTotal += contas[i].getSaldo();
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
