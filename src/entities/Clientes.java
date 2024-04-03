
package entities;

import java.util.Scanner;

public class Clientes {
        private int identificador;
        private String tipo;
        private String nome;
        private String documento;
        private Banco[] contas = new Banco[3];
        private float saldo;

    public Clientes(int identificador, String tipo, String nome, String documento ) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.nome = nome;
        this.documento = documento;
        this.saldo = 0;
    }
    
    public boolean sacar( float valorSacar){
        if( this.saldo >= valorSacar ){
            setSaldo( this.saldo -= valorSacar );
            return true;    
        }
        return false;
    }
    
    public void depositar( int indice, float valorADepositar){
        setContasValor( indice, this.contas[indice].getSaldo() + valorADepositar );
    }

    public static Clientes adicionaCliente(){
        int modulo;
        Scanner scan = new Scanner( System.in );
        
        System.out.println("\n1-Cadastro cliente fisico\n2-Cadastro cliente juridico");
        modulo = scan.nextInt();
        switch( modulo ){
            case 1->{
                return cadastraClienteFisico();
            }
            
            case 2->{
                return cadastraClienteJuridico();
            }
            default->{
                System.out.println("Tipo inexsistente");
                return null;
            }
        }
    }
    
    private static Clientes cadastraClienteFisico(){
        Scanner scan = new Scanner(System.in);
        int id;
        String nome, documento;

        System.out.println("\n----CADASTRO CLIENTE FISICO----\n ");
        System.out.println("Idenficador:");
        id = scan.nextInt();
        scan.nextLine();
        System.out.println("Nome: ");
        nome = scan.nextLine();
        System.out.println("CPF: ");
        documento = scan.nextLine();
        
        Clientes c = new Clientes(id, "Fisico", nome, documento);
        
        return c;

    }
    private static Clientes cadastraClienteJuridico(){
        Scanner scan = new Scanner(System.in);
        int id;
        String nome, documento;

        System.out.println("\n----CADASTRO CLIENTE JURIDICO----\n ");
        System.out.println("Idenficador:");
        id = scan.nextInt();
        scan.nextLine();
        System.out.println("Nome Fantasia: ");
        nome = scan.nextLine();
        System.out.println("CNPJ: ");
        documento = scan.nextLine();
        
        Clientes c = new Clientes(id, "Juridico", nome, documento);
        return c;
    }
    public static int procuraCliente( Clientes[] arrayCliente ){
        int indice, id;
        Scanner scan = new Scanner(System.in);

        System.out.println("Identificador do cliente: ");
        id = scan.nextInt();

        for( indice = 0; indice < arrayCliente.length; indice++ ){
            if( arrayCliente[indice] != null){
                if( id == arrayCliente[indice].getIdentificador()){
                    return indice;
                }
            }
        }
        System.out.println("Cliente não cadastrado");
        return -1;
    }
    public static void mostraClientes( Clientes[] arrayClientes ){
        for( Clientes cliente : arrayClientes){
            System.out.println("-------------------------");
            System.out.println(cliente);
        }
    }

    @Override
    public String toString() {
        return "Cliente: \nID: " + identificador + ",   Tipo: " + tipo + "\nNome: " + nome + ",  Documento: " + documento + ", Saldo: " + saldo;
    }
    
    public Banco[] getContas() {
        return contas;
    }

    public void setContas(Banco[] contas ) {
        this.contas = contas;
    }
    //0 - Poupança
    //1 - Corrente
    //2 - Investimento
    public void setContasValor( int indice, float valor) {
        this.contas[indice].setSaldo( valor );
    }

    public int getIdentificador() {
        return identificador;
    }
    public String getTipo() {
        return tipo;
    }
    public String getNome() {
        return nome;
    }
    public String getDocumento() {
        return documento;
    }
    public float getSaldo() {
        return saldo;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public void setSaldo(float saldo) {
        if( saldo > 0){
            this.saldo = saldo;
        }
    }
}
