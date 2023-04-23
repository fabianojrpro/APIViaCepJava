import java.util.Scanner;

public class Program {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o CEP: ");
        String cep = scanner.nextLine();
        
        try {
            Endereco endereco = ViaCepClient.consultarCep(cep);
            
            System.out.println("CEP: " + endereco.cep);
            System.out.println("Logradouro: " + endereco.logradouro);
            System.out.println("Bairro: " + endereco.bairro);
            System.out.println("Localidade: " + endereco.localidade);
            System.out.println("UF: " + endereco.uf);
            System.out.println("IBGE: " + endereco.ibge);
            System.out.println("GIA: " + endereco.gia);
            System.out.println("DDD: " + endereco.ddd);
            System.out.println("SIAFI: " + endereco.siafi);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        scanner.close();
    }
}
