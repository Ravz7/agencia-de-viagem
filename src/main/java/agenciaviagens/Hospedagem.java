package agenciaviagens;

/**
 * Hospedagem de um pacote de viagem.
 * O valor da diária é considerado em dólar (US$).
 */
public class Hospedagem {

    private String descricao;    // ex.: Hotel 4 estrelas, pousada, resort
    private double valorDiaria;  // em dólar

    public Hospedagem(String descricao, double valorDiaria) {
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @Override
    public String toString() {
        return descricao + " (US$ " + String.format("%.2f", valorDiaria) + " por diária)";
    }
}
