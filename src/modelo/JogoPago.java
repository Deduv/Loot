package modelo;

/**
 * Subclasse que representa um jogo pago, com cálculo de custo por hora jogada.
 *
 * @author Eduardo Luis de Oliveira Mendonça
 */

public class JogoPago extends Jogo {

    private double precoPago;

    public JogoPago(int id, String titulo, String genero, double horasJogadas,
                    double notaAvaliacao, int anoLancamento, double precoPago) {
        super(id, titulo, genero, horasJogadas, notaAvaliacao, anoLancamento);
        setPrecoPago(precoPago);
    }

    public double getPrecoPago() {
        return precoPago;
    }

    public void setPrecoPago(double precoPago) {
        if (precoPago < 0) {
            throw new IllegalArgumentException("O preço pago não pode ser negativo.");
        }
        this.precoPago = precoPago;
    }

    @Override
    public String analisarCustoBeneficio() {
        if (precoPago == 0) {
            return "Obtido sem custo (R$ 0,00)";
        }
        if (getHorasJogadas() <= 0) {
            return String.format("R$ %.2f investidos — Ainda não jogado", precoPago);
        }

        double custoPorHora = precoPago / getHorasJogadas();
        return String.format("R$ %.2f por hora jogada", custoPorHora);
    }
}
