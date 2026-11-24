package modelo;

/**
 *
 * @author Eduardo Luis
 */

public class JogoGratuito extends Jogo {

    private double gastoItensExtras;

    public JogoGratuito(int id, String titulo, String genero, double horasJogadas,
                        double notaAvaliacao, int anoLancamento, double gastoItensExtras) {
        super(id, titulo, genero, horasJogadas, notaAvaliacao, anoLancamento);
        setGastoItensExtras(gastoItensExtras);
    }

    public double getGastoItensExtras() {
        return gastoItensExtras;
    }

    public void setGastoItensExtras(double gastoItensExtras) {
        if (gastoItensExtras < 0) {
            throw new IllegalArgumentException("O gasto com itens extras não pode ser negativo.");
        }
        this.gastoItensExtras = gastoItensExtras;
    }

    @Override
    public String analisarCustoBeneficio() {
        if (gastoItensExtras == 0) {
            return String.format("100%% Grátis (%.1fh aproveitadas)", getHorasJogadas());
        }
        if (getHorasJogadas() <= 0) {
            return String.format("F2P com R$ %.2f em itens extras (Ainda não jogado)", gastoItensExtras);
        }

        double custoPorHora = gastoItensExtras / getHorasJogadas();
        return String.format("F2P com R$ %.2f em extras (R$ %.2f/h)", gastoItensExtras, custoPorHora);
    }
}
