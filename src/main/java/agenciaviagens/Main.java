package agenciaviagens;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Classe principal: interação com o usuário para cadastrar vendas de pacotes de viagem.
 */
public class Main {

    private static final Scanner entrada = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("      AGÊNCIA DE VIAGENS - CADASTRO");
        System.out.println("==========================================");

        boolean continuar;
        do {
            cadastrarVenda();
            System.out.print("\nDeseja cadastrar outra venda? (S/N): ");
            continuar = entrada.nextLine().trim().equalsIgnoreCase("S");
        } while (continuar);

        System.out.println("\nEncerrando o sistema. Até logo!");
    }

    private static void cadastrarVenda() {
        // ---------------- Pacote de viagem ----------------
        System.out.println("\n--- DADOS DO PACOTE DE VIAGEM (valores em dólar) ---");
        String destino = lerTexto("Destino: ");
        int dias = lerInteiroPositivo("Quantidade de dias: ");

        String tipoTransporte = lerTexto("Tipo de transporte (aéreo, rodoviário, marítimo...): ");
        double valorTransporte = lerDoubleNaoNegativo("Valor do transporte (US$): ");
        Transporte transporte = new Transporte(tipoTransporte, valorTransporte);

        String descricaoHospedagem = lerTexto("Descrição da hospedagem: ");
        double valorDiaria = lerDoubleNaoNegativo("Valor da diária (US$): ");
        Hospedagem hospedagem = new Hospedagem(descricaoHospedagem, valorDiaria);

        double margem = lerDoubleNaoNegativo("Margem de lucro (%): ");
        double taxas = lerDoubleNaoNegativo("Taxas adicionais (US$): ");

        PacoteViagem pacote = new PacoteViagem(transporte, hospedagem, destino, dias, margem, taxas);

        System.out.println("\n=========== PACOTE CRIADO ===========");
        pacote.mostrarInformacoes();

        // ---------------- Venda ----------------
        System.out.println("\n--- DADOS DA VENDA ---");
        String cliente = lerTexto("Nome do cliente: ");
        String formaPagamento = lerTexto("Forma de pagamento: ");
        LocalDate data = lerData("Data da venda (dd/MM/aaaa, Enter para hoje): ");
        double cotacao = lerDoublePositivo("Cotação do dólar no dia (R$): ");

        Venda venda = new Venda(cliente, formaPagamento, data, pacote);

        System.out.println("\n=========== VENDA CADASTRADA ===========");
        venda.mostrarInformacoes(cotacao);
        System.out.println("=========================================");
    }

    // ------------------------------------------------------------------
    // Métodos auxiliares de leitura (com validação)
    // ------------------------------------------------------------------

    private static String lerTexto(String mensagem) {
        String texto;
        do {
            System.out.print(mensagem);
            texto = entrada.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("  Campo obrigatório. Tente novamente.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    private static int lerInteiroPositivo(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(entrada.nextLine().trim());
                if (valor > 0) {
                    return valor;
                }
                System.out.println("  Informe um número maior que zero.");
            } catch (NumberFormatException e) {
                System.out.println("  Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine().trim();
            // aceita "1500,50", "1500.50" e "1.500,50"
            if (texto.contains(",")) {
                texto = texto.replace(".", "").replace(",", ".");
            }
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println("  Valor inválido. Digite um número (ex.: 1500,50).");
            }
        }
    }

    private static double lerDoubleNaoNegativo(String mensagem) {
        while (true) {
            double valor = lerDouble(mensagem);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("  O valor não pode ser negativo.");
        }
    }

    private static double lerDoublePositivo(String mensagem) {
        while (true) {
            double valor = lerDouble(mensagem);
            if (valor > 0) {
                return valor;
            }
            System.out.println("  O valor deve ser maior que zero.");
        }
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine().trim();
            if (texto.isEmpty()) {
                return LocalDate.now();
            }
            try {
                return LocalDate.parse(texto, FORMATO_DATA);
            } catch (DateTimeParseException e) {
                System.out.println("  Data inválida. Use o formato dd/MM/aaaa.");
            }
        }
    }
}
