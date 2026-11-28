package modelo;

import java.time.Year;

/**
 * @author Ícaro Santos Silva
 */
public abstract class Jogo {

    public static final double NOTA_MINIMA = 0.0;
    public static final double NOTA_MAXIMA = 10.0;
    public static final int ANO_MINIMO = 1950;

    private int id;
    private String titulo;
    private String genero;
    private double horasJogadas;
    private double notaAvaliacao;
    private int anoLancamento;

    public Jogo(int id, String titulo, String genero, double horasJogadas, double notaAvaliacao, int anoLancamento) {
        setId(id);
        setTitulo(titulo);
        setGenero(genero);
        setHorasJogadas(horasJogadas);
        setNotaAvaliacao(notaAvaliacao);
        setAnoLancamento(anoLancamento);
    }

    public int getId() { return id; }

    public final void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("O ID não pode ser negativo.");
        }
        this.id = id;
    }

    public String getTitulo() { return titulo; }

    public final void setTitulo(String titulo) {
        this.titulo = validarTexto(titulo, "título");
    }

    public String getGenero() { return genero; }

    public final void setGenero(String genero) {
        this.genero = validarTexto(genero, "gênero");
    }

    public double getHorasJogadas() { return horasJogadas; }

    public final void setHorasJogadas(double horasJogadas) {
        validarHoras(horasJogadas);
        this.horasJogadas = horasJogadas;
    }

    public double getNotaAvaliacao() { return notaAvaliacao; }

    public final void setNotaAvaliacao(double notaAvaliacao) {
        validarNota(notaAvaliacao);
        this.notaAvaliacao = notaAvaliacao;
    }

    public int getAnoLancamento() { return anoLancamento; }

    public final void setAnoLancamento(int anoLancamento) {
        int anoAtual = Year.now().getValue();
        if (anoLancamento < ANO_MINIMO || anoLancamento > anoAtual) {
            throw new IllegalArgumentException(
                    "O ano de lançamento deve estar entre " + ANO_MINIMO + " e " + anoAtual + ".");
        }
        this.anoLancamento = anoLancamento;
    }

    /**
     * Atualiza horas e nota juntas: valida as duas antes de alterar,
     * para o jogo nunca ficar com apenas um dos valores atualizado.
     */
    public final void atualizarProgresso(double novasHoras, double novaNota) {
        validarHoras(novasHoras);
        validarNota(novaNota);
        this.horasJogadas = novasHoras;
        this.notaAvaliacao = novaNota;
    }

    private static String validarTexto(String texto, String campo) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("O " + campo + " não pode ficar vazio.");
        }
        return texto.trim();
    }

    private static void validarHoras(double horas) {
        if (horas < 0) {
            throw new IllegalArgumentException("As horas jogadas não podem ser negativas.");
        }
    }

    private static void validarNota(double nota) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
    }

    public String exibirResumo() {
        return String.format("ID: #%d | Título: %s (%d) | Gênero: %s | Horas: %.1fh | Nota: %.1f/10",
                id, titulo, anoLancamento, genero, horasJogadas, notaAvaliacao);
    }

    public abstract String exibirDetalhes();

    public abstract String analisarCustoBeneficio();
}
