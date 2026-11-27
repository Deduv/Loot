package controle;

import java.util.ArrayList;
import modelo.Jogo;

/**
 * Classe responsavel pelo gerenciamento da colecao de jogos da biblioteca.
 */
public class Biblioteca {

    private ArrayList<Jogo> jogos;
    private int proximoId = 1;

    public Biblioteca() {
        this.jogos = new ArrayList<>();
    }

    /**
     * Inclui um novo jogo na colecao e atribui um identificador sequencial automatico.
     *
     * @param jogo Objeto do jogo a ser adicionado.
     */
    public void incluirJogo(Jogo jogo) {
        if (jogo == null) {
            return;
        }
        jogo.setId(this.proximoId);
        this.proximoId++;
        this.jogos.add(jogo);
    }

    /**
     * Consulta um jogo na colecao a partir de seu identificador unico.
     *
     * @param id Identificador do jogo procurado.
     * @return O objeto Jogo correspondente, ou null caso nao seja encontrado.
     */
    public Jogo consultarPorId(int id) {
        for (Jogo jogo : this.jogos) {
            if (jogo.getId() == id) {
                return jogo;
            }
        }
        return null;
    }

    /**
     * Altera os dados variaveis de um jogo existente (horas jogadas e nota).
     *
     * @param id Identificador do jogo a ser alterado.
     * @param novasHoras Quantidade atualizada de horas jogadas.
     * @param novaNota Nota de avaliacao atualizada (0.0 a 10.0).
     * @return true se o jogo foi localizado e atualizado; false caso contrario.
     */
    public boolean alterarJogo(int id, double novasHoras, double novaNota) {
        Jogo jogo = consultarPorId(id);
        if (jogo != null) {
            jogo.setHorasJogadas(novasHoras);
            jogo.setNotaAvaliacao(novaNota);
            return true;
        }
        return false;
    }

    /**
     * Imprime a lista completa de jogos cadastrados com a analise polimorfica.
     */
    public void imprimirLista() {
        if (this.jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado na biblioteca.");
            return;
        }

        System.out.println("\n========== LISTA DE JOGOS CADASTRADOS ==========");
        for (Jogo jogo : this.jogos) {
            System.out.println("ID: " + jogo.getId() + " | Título: " + jogo.getTitulo() + " (" + jogo.getAnoLancamento() + ")");
            System.out.println("Gênero: " + jogo.getGenero() + " | Horas: " + jogo.getHorasJogadas() + "h | Nota: " + jogo.getNotaAvaliacao());
            System.out.println("Análise: " + jogo.analisarCustoBeneficio());
            System.out.println("--------------------------------------------------");
        }
    }
}
