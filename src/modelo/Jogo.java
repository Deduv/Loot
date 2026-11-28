package modelo;

/**
 * @author Ícaro Santos Silva
 */
public abstract class Jogo {
    protected int id;
    protected String titulo;
    protected String genero;
    protected double horasJogadas;
    protected double notaAvaliacao;
    protected int anoLancamento;

    public Jogo(int id, String titulo, String genero, double horasJogadas, double notaAvaliacao, int anoLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.horasJogadas = horasJogadas;
        setNotaAvaliacao(notaAvaliacao);
        this.anoLancamento = anoLancamento;
    }

    public Jogo() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public double getHorasJogadas() { return horasJogadas; }
    public void setHorasJogadas(double horasJogadas) { this.horasJogadas = horasJogadas; }

    public double getNotaAvaliacao() { return notaAvaliacao; }
    public void setNotaAvaliacao(double notaAvaliacao) {
        if (notaAvaliacao >= 0.0 && notaAvaliacao <= 10.0) {
            this.notaAvaliacao = notaAvaliacao;
        } else {
            System.out.println("[Aviso] Nota inválida! Atribuído valor 0.0 por segurança.");
            this.notaAvaliacao = 0.0;
        }
    }

    public int getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }

    public String exibirResumo() {
        return String.format("ID: #%d | Título: %s (%d) | Gênero: %s | Horas: %.1fh | Nota: %.1f/10",
                id, titulo, anoLancamento, genero, horasJogadas, notaAvaliacao);
    }

    public abstract String analisarCustoBeneficio();
}
