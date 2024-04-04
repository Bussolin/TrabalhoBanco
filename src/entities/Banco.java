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
    
    public static void removeCliente(Clientes[] clientes){
        if( clientes[0] == null){
            System.out.println("Nenhum cliente cadastrado");
            return;
        }
        Clientes.mostraClientes( clientes );
        
        Scanner scan = new Scanner(System.in);
        System.out.println("--------EXCLUSAO--------");
        int indiceCliente = Clientes.procuraCliente( clientes );
        if( indiceCliente != -1){
            for( int i = indiceCliente; i < clientes.length; i++){
                if( i != clientes.length - 1){
                    clientes[ i ] = clientes[ i + 1];
                }
            }
            System.out.println("Cliente excluido com sucesso");
        }else{
            System.out.println("cliente não encontrado");
        }
    }
    
    public static int  escolherConta ( Clientes cliente ){
        
        int contas = MostraContas( cliente );
        
        if ( contas == 0){
            System.out.println("Nenhuma conta cadastrada");
            return -1;
        }
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Escolha a conta: ");
        int indiceConta = scan.nextInt();
        if( indiceConta <= 0 ){
            return -1;
        }else if( cliente.getContas()[indiceConta - 1] != null ){
            return indiceConta - 1;
        }else{
            return -1;
        }
        
    }
    
    public static void depositoCliente( Clientes[] arrayCliente){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("\n------DEPOSITO------");
        int indiceCliente = Clientes.procuraCliente(arrayCliente);
        if( indiceCliente == -1){
            return;
        }
        
        int indiceConta = escolherConta( arrayCliente[ indiceCliente ] );
        if( indiceConta != -1){
            System.out.println("Valor a depositar:");
            arrayCliente[indiceCliente].depositar(indiceConta, scan.nextFloat());
            System.out.println("Deposito realizado com sucesso");
            System.out.println("\nDados atualizado");
            System.out.println( arrayCliente[indiceCliente].getContas()[ indiceConta ] + "\n");

        }
    }
    
    public static void saqueCliente( Clientes[] arrayCliente){
        
        System.out.println("\n------SAQUE------");
        int indiceCliente = Clientes.procuraCliente( arrayCliente );
        if( indiceCliente == -1){
            return;
        }
        
        Scanner scan = new Scanner(System.in);
        int indiceConta = escolherConta(arrayCliente[ indiceCliente ] );
        if (indiceConta != -1 ) {
        
            System.out.println("Valor a sacar:");
            float valorSacar = scan.nextFloat();
            arrayCliente[indiceCliente].sacar( indiceConta, valorSacar );
            float saldoAntigo = arrayCliente[indiceCliente].getContas()[ indiceConta ].getSaldo();
            if( saldoAntigo == arrayCliente[indiceCliente].getContas()[ indiceConta ].getSaldo() ){
                System.out.println("Saldo insuficiente");
            }else{
                System.out.println("Saque realizado com sucesso");
                System.out.println("\nDados atualizado");
                System.out.println( arrayCliente[indiceCliente].getContas()[ indiceConta ] + "\n");
            }
        }
    }
    
    public static void transferenciaValor( Clientes[] arrayCliente){
        int indiceCliente1, indiceCliente2, indiceContaOrigem, indiceContaDestino;

        System.out.println("\n------TRANSFERÊNCIA------");
        
        System.out.println("Cliente origem: ");
        indiceCliente1 = Clientes.procuraCliente( arrayCliente );
        if( indiceCliente1 == -1){
            return;
        }
        indiceContaOrigem = escolherConta( arrayCliente[ indiceCliente1 ] );
        
        System.out.println("Cliente destino: ");
        indiceCliente2 = Clientes.procuraCliente( arrayCliente );
        if( indiceCliente2 == -1){
            return;
        }
        indiceContaDestino = escolherConta( arrayCliente[ indiceCliente2 ]);
        
        if( indiceCliente1 == indiceCliente2 && indiceContaDestino == indiceContaOrigem){
            System.out.println("Nao e possivel a transferencia para a mesma conta");
            return;
        }
        
        float valorTransferido, saldoAntigo;
        Scanner scan = new Scanner(System.in);

        System.out.println("Valor a ser transferido:");
        valorTransferido = scan.nextFloat();
        saldoAntigo = arrayCliente[indiceCliente1].getContas()[ indiceContaOrigem ].getSaldo();
        arrayCliente[indiceCliente1].sacar( indiceContaOrigem, valorTransferido );
        
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
    
    public static String getStringTipoConta( int indice ){
        return switch (indice) {
            case 0 -> " - Poupanca";
            case 1 -> " - Corrente";
            default -> " - Investimento";
        };
    }
    
    public static int MostraContas( Clientes cliente ){
        int i =0;
        
        for( Banco conta : cliente.getContas() ){
            if( conta != null){
                System.out.println();
                System.out.println( (i + 1) + getStringTipoConta( i ) +": ");
                System.out.println( conta );
                System.out.println();
                i++;
            }
        }
        return i;
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
