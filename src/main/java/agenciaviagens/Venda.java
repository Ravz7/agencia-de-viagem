package agenciaviagens;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Venda de um pacote de viagem a um cliente.
 */
public class Venda {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private String nomeCliente;
    private String formaPagamento;
    private LocalDate data;
    private PacoteViagem pacote;

    public Venda(String nomeCliente, String formaPagamento, LocalDate data, PacoteViagem pacote) {
        this.nomeCliente = nomeCliente;
        this.formaPagamento = formaPagamento;
        this.data = data;
        this.pacote = pacote;
    }

    /** Cria a venda com a data de hoje. */
    public Venda(String nomeCliente, String formaPagamento, PacoteViagem pacote) {
        this(nomeCliente, formaPagamento, LocalDate.now(), pacote);
    }

    // ------------------------------------------------------------------
    // Conversões de moeda
    // ------------------------------------------------------------------

    /** Converte dólar para reais: valor em dólar x cotação (R$ por US$ 1). */
    public double converterParaReais(double valorDolar, double cotacao) {
        return valorDolar * cotacao;
    }

    /** Converte reais para dólar: valor em reais / cotação (R$ por US$ 1). */
    public double converterParaDolar(double valorReais, double cotacao) {
        return valorReais / cotacao;
    }

    // ------------------------------------------------------------------
    // Exibição
    // ------------------------------------------------------------------

    /** Mostra na tela o total do pacote em dólar e em reais. */
    public void mostrarTotalPacote(double cotacao) {
        double totalDolar = pacote.calcularTotalPacote();
        double totalReais = converterParaReais(totalDolar, cotacao);

        System.out.println("Cotação do dólar...: " + String.format(PT_BR, "R$ %,.4f", cotacao));
        System.out.println("Total em dólar.....: " + String.format(Locale.US, "US$ %,.2f", totalDolar));
        System.out.println("Total em reais.....: " + String.format(PT_BR, "R$ %,.2f", totalReais));
    }

    /** Mostra todas as informações da venda, incluindo os totais em dólar e reais. */
    public void mostrarInformacoes(double cotacao) {
        System.out.println("Cliente............: " + nomeCliente);
        System.out.println("Forma de pagamento.: " + formaPagamento);
        System.out.println("Data da venda......: " + data.format(FORMATO_DATA));
        System.out.println();
        System.out.println("--- Pacote vendido ---");
        pacote.mostrarInformacoes();
        System.out.println();
        System.out.println("--- Valores da venda ---");
        mostrarTotalPacote(cotacao);
    }

    // ------------------------------------------------------------------
    // Getters e setters
    // ------------------------------------------------------------------

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public PacoteViagem getPacote() {
        return pacote;
    }

    public void setPacote(PacoteViagem pacote) {
        this.pacote = pacote;
    }
}
