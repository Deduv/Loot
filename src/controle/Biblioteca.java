package controle;

import java.util.ArrayList;
import modelo.Jogo;
import modelo.JogoPago;
import modelo.JogoGratuito;

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
     * Exclui um jogo da colecao a partir de seu identificador unico.
     *
     * @param id Identificador do jogo a ser removido.
     * @return true se o jogo foi localizado e removido; false caso contrario.
     */
    public boolean excluirJogo(int id) {
        for (int i = 0; i < this.jogos.size(); i++) {
            if (this.jogos.get(i).getId() == id) {
                this.jogos.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se a biblioteca esta vazia.
     *
     * @return true se nao houver nenhum jogo cadastrado; false caso contrario.
     */
    public boolean estaVazia() {
        return this.jogos.isEmpty();
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
            imprimirJogo(jogo);
        }
    }

    /**
     * Imprime os detalhes individuais de um jogo e a sua analise polimorfica.
     *
     * @param jogo Objeto Jogo a ser exibido.
     */
    public void imprimirJogo(Jogo jogo) {
        if (jogo == null) {
            System.out.println("Jogo não encontrado.");
            return;
        }

        System.out.println("ID: #" + jogo.getId() + " | Título: " + jogo.getTitulo() + " (" + jogo.getAnoLancamento() + ")");
        System.out.println("Gênero: " + jogo.getGenero() + " | Horas: " + jogo.getHorasJogadas() + "h | Nota: " + jogo.getNotaAvaliacao() + "/10");

        if (jogo instanceof JogoPago pago) {
            System.out.println("Tipo: Jogo Pago | Preço: R$ " + pago.getPrecoPago());
        } else if (jogo instanceof JogoGratuito gratuito) {
            System.out.println("Tipo: Jogo Gratuito | Gastos extras: R$ " + gratuito.getGastoItensExtras());
        }

        System.out.println("Análise: " + jogo.analisarCustoBeneficio());
        System.out.println("----------------------------------------");
    }
}
