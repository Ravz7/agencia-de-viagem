package agenciaviagens;

import java.util.Locale;

/**
 * Pacote de viagem formado por transporte e hospedagem.
 * Todos os valores desta classe são considerados em dólar (US$).
 */
public class PacoteViagem {

    private Transporte transporte;
    private Hospedagem hospedagem;
    private String destino;
    private int quantidadeDias;
    private double margemLucro;       // porcentagem (ex.: 20 = 20%)
    private double taxasAdicionais;   // valor monetário em dólar

    public PacoteViagem(Transporte transporte, Hospedagem hospedagem, String destino,
                        int quantidadeDias, double margemLucro, double taxasAdicionais) {
        this.transporte = transporte;
        this.hospedagem = hospedagem;
        this.destino = destino;
        this.quantidadeDias = quantidadeDias;
        this.margemLucro = margemLucro;
        this.taxasAdicionais = taxasAdicionais;
    }

    // ------------------------------------------------------------------
    // Cálculos
    // ------------------------------------------------------------------

    /** Total da hospedagem = valor da diária x quantidade de dias. */
    public double calcularTotalHospedagem() {
        return hospedagem.getValorDiaria() * quantidadeDias;
    }

    /**
     * Aplica a margem de lucro (porcentagem) sobre o valor informado.
     * Retorna o valor + margem aplicada ao valor.
     */
    public double calcularLucro(double margemPercentual, double valor) {
        return valor + (valor * margemPercentual / 100.0);
    }

    /**
     * Total do pacote = (transporte + total da hospedagem) com a margem de lucro
     * aplicada + taxas adicionais.
     */
    public double calcularTotalPacote(double margemPercentual, double taxas) {
        double custoBase = transporte.getValor() + calcularTotalHospedagem();
        return calcularLucro(margemPercentual, custoBase) + taxas;
    }

    /** Total do pacote usando a margem e as taxas informadas na criação do objeto. */
    public double calcularTotalPacote() {
        return calcularTotalPacote(margemLucro, taxasAdicionais);
    }

    // ------------------------------------------------------------------
    // Exibição
    // ------------------------------------------------------------------

    public void mostrarInformacoes() {
        Locale us = Locale.US;
        System.out.println("Destino............: " + destino);
        System.out.println("Quantidade de dias.: " + quantidadeDias);
        System.out.println("Transporte.........: " + transporte.getTipo()
                + " - " + String.format(us, "US$ %,.2f", transporte.getValor()));
        System.out.println("Hospedagem.........: " + hospedagem.getDescricao()
                + " - " + String.format(us, "US$ %,.2f", hospedagem.getValorDiaria()) + " por diária");
        System.out.println("Total hospedagem...: " + String.format(us, "US$ %,.2f", calcularTotalHospedagem()));
        System.out.println("Margem de lucro....: " + String.format(us, "%.2f%%", margemLucro));
        System.out.println("Taxas adicionais...: " + String.format(us, "US$ %,.2f", taxasAdicionais));
        System.out.println("TOTAL DO PACOTE....: " + String.format(us, "US$ %,.2f", calcularTotalPacote()));
    }

    // ------------------------------------------------------------------
    // Getters e setters
    // ------------------------------------------------------------------

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public double getTaxasAdicionais() {
        return taxasAdicionais;
    }

    public void setTaxasAdicionais(double taxasAdicionais) {
        this.taxasAdicionais = taxasAdicionais;
    }
}
