package agenciaviagens;

/**
 * Meio de transporte de um pacote de viagem.
 * O valor é considerado em dólar (US$).
 */
public class Transporte {

    private String tipo;   // aéreo, rodoviário, marítimo etc.
    private double valor;  // em dólar

    public Transporte(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return tipo + " (US$ " + String.format("%.2f", valor) + ")";
    }
}
