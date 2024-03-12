
package entities;

import java.util.Scanner;

public class Clientes {
        private int identificador;
        private String tipo;
        private String nome;
        private String documento;
        private float saldo;
        
        public boolean sacar( float valorSacar){
            if( this.saldo >= valorSacar ){
                setSaldo( this.saldo -= valorSacar );
                return true;
            }else{
                return false;
            }
        }
        public boolean depositar( float valorADepositar){
            setSaldo( getSaldo() + valorADepositar );
            return true;
        }

        @Override
        public String toString() {
            return "Cliente: \nID: " + identificador + ",   Tipo: " + tipo + "\nNome: " + nome + ",  Documento: " + documento + ", Saldo: " + saldo;
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
        public static Clientes adicionaCliente( Clientes cliente ){
            int modulo;
            Scanner scan = new Scanner( System.in );
            
            System.out.println("\n1-Cadastro cliente fisico\n2-Cadastro cliente juridico");
            modulo = scan.nextInt();
            if( modulo == 1 ){
                cliente = cadastraClienteFisico();
            }else if( modulo == 2 ){
                cliente = cadastraClienteJuridico();
            }
            return cliente;
        }
        private static Clientes cadastraClienteFisico(){
            Scanner scan = new Scanner(System.in);
            Clientes c = new Clientes();

            System.out.println("\n----CADASTRO CLIENTE FISICO----\n ");
            System.out.println("Idenficador:");
            c.setIdentificador(scan.nextInt());
            scan.nextLine();
            System.out.println("Nome: ");
            c.setNome(scan.nextLine());
            System.out.println("CPF: ");
            c.setDocumento( scan.nextLine());
            c.setSaldo(0);
            c.setTipo("Fisico");
            return c;
       
        }
        private static Clientes cadastraClienteJuridico(){
            Scanner scan = new Scanner(System.in);
            Clientes c = new Clientes();

            System.out.println("\n----CADASTRO CLIENTE JURIDICO----\n ");
            System.out.println("Identificador: ");
            c.setIdentificador(scan.nextInt());
            scan.nextLine();
            System.out.println("Nome Fantasia: ");
            c.setNome(scan.nextLine());
            System.out.println("CNPJ: ");
            c.setDocumento( scan.nextLine());
            c.setSaldo(0);
            c.setTipo("Juridico");
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
}
