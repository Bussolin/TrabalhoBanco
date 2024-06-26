
package entities;

import java.util.Scanner;

public class Clientes {
        private int identificador;
        private String tipo;
        private String nome;
        private String documento;
        private Banco[] contas = new Banco[3];

    public Clientes(int identificador, String tipo, String nome, String documento ) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.nome = nome;
        this.documento = documento;
    }
    
    public void sacar( int indice, float valorSacar){
        if( this.contas[indice].getSaldo() >= valorSacar){
            setContasValor( indice, this.contas[indice].getSaldo() - valorSacar );
        }
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
        int id;
        Scanner scan = new Scanner(System.in);

        System.out.println("Identificador do cliente: ");
        id = scan.nextInt();

        for( int indice = 0; indice < arrayCliente.length; indice++ ){
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
        if( arrayClientes[0] == null){
            System.out.println("Nenhum cliente cadastrado");
            return;
        }
        
        for( Clientes cliente : arrayClientes){
            if( cliente != null){
                System.out.println("-------------------------");
                System.out.println(cliente);
                System.out.println("-----CONTAS-CLIENTE------");
                Banco.MostraContas( cliente );
            }
        }
    }

    @Override
    public String toString() {
        return "Cliente: \nID: " + identificador + ",   Tipo: " + tipo + "\nNome: " + nome + ",  Documento: " + documento;
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
}
