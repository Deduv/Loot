package controle;

import java.util.ArrayList;
import modelo.Jogo;
import modelo.JogoPago;
import modelo.JogoGratuito;

/**
 * Classe responsável pelo gerenciamento da coleção de jogos da biblioteca (CRUD).
 *
 * Divisão de responsabilidade na classe:
 * - Parte 1 (N3 - Cadastro, geração de ID e listagem): Ícaro Santos Silva
 * - Parte 2 (N4 - Consulta por ID e alteração): Eduardo Santana dos Santos
 * - Parte 3 (N5 - Validação de lista vazia e exclusão): Saulo Magno Santos de Jesus
 *
 * @author Ícaro Santos Silva
 * @author Eduardo Santana dos Santos
 * @author Saulo Magno Santos de Jesus
 */
public class Biblioteca {

    private ArrayList<Jogo> listaDeJogos;
    private int proximoId = 1;

    public Biblioteca() {
        this.listaDeJogos = new ArrayList<>();
    }

    // =========================================================================
    // PARTE 1 (NÚMERO 3): Cadastro, Geração de ID e Listagem
    // @author Ícaro Santos Silva
    // =========================================================================

    /**
     * Gera e retorna o próximo identificador sequencial único (1, 2, 3...).
     *
     * @author Ícaro Santos Silva
     * @return Próximo ID disponível.
     */
    public int gerarProximoId() {
        return this.proximoId++;
    }

    /**
     * Inclui um novo jogo na coleção e atribui um identificador sequencial automático
     * caso o jogo ainda não possua um ID válido.
     *
     * @author Ícaro Santos Silva
     * @param novoJogo Objeto do jogo a ser adicionado.
     */
    public void incluirJogo(Jogo novoJogo) {
        if (novoJogo == null) {
            return;
        }
        if (novoJogo.getId() <= 0) {
            novoJogo.setId(gerarProximoId());
        }
        this.listaDeJogos.add(novoJogo);
    }

    /**
     * Imprime a lista completa de jogos cadastrados utilizando exibirResumo() e analisarCustoBeneficio().
     *
     * @author Ícaro Santos Silva
     */
    public void imprimirLista() {
        if (this.listaDeJogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado na biblioteca.");
            return;
        }

        System.out.println("\n========== LISTA DE JOGOS CADASTRADOS ==========");
        for (Jogo jogo : this.listaDeJogos) {
            imprimirJogo(jogo);
        }
    }

    /**
     * Imprime os detalhes individuais de um jogo e a sua análise polimórfica.
     *
     * @author Ícaro Santos Silva
     * @param jogo Objeto Jogo a ser exibido.
     */
    public void imprimirJogo(Jogo jogo) {
        if (jogo == null) {
            System.out.println("Jogo não encontrado.");
            return;
        }

        System.out.println(jogo.exibirResumo());

        if (jogo instanceof JogoPago pago) {
            System.out.printf("Tipo: Jogo Pago | Preço: R$ %.2f%n", pago.getPrecoPago());
        } else if (jogo instanceof JogoGratuito gratuito) {
            System.out.printf("Tipo: Jogo Gratuito | Gastos extras: R$ %.2f%n", gratuito.getGastoItensExtras());
        }

        System.out.println("Análise: " + jogo.analisarCustoBeneficio());
        System.out.println("----------------------------------------");
    }

    // =========================================================================
    // PARTE 2 (NÚMERO 4): Consulta por ID e Alteração
    // @author Eduardo Santana dos Santos
    // =========================================================================

    /**
     * Consulta um jogo na coleção a partir de seu identificador único.
     *
     * @author Eduardo Santana dos Santos
     * @param id Identificador do jogo procurado.
     * @return O objeto Jogo correspondente, ou null caso não seja encontrado.
     */
    public Jogo consultarPorId(int id) {
        for (Jogo jogo : this.listaDeJogos) {
            if (jogo.getId() == id) {
                return jogo;
            }
        }
        return null;
    }

    /**
     * Altera os dados variáveis de um jogo existente (horas jogadas e nota).
     *
     * @author Eduardo Santana dos Santos
     * @param id Identificador do jogo a ser alterado.
     * @param novasHoras Quantidade atualizada de horas jogadas.
     * @param novaNota Nota de avaliação atualizada (0.0 a 10.0).
     * @return true se o jogo foi localizado e atualizado; false caso contrário.
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

    // =========================================================================
    // PARTE 3 (NÚMERO 5): Validação e Exclusão
    // @author Saulo Magno Santos de Jesus
    // =========================================================================

    /**
     * Verifica se a biblioteca está vazia.
     *
     * @author Saulo Magno Santos de Jesus
     * @return true se não houver nenhum jogo cadastrado; false caso contrário.
     */
    public boolean estaVazia() {
        return this.listaDeJogos.isEmpty();
    }

    /**
     * Exclui um jogo da coleção a partir de seu identificador único.
     *
     * @author Saulo Magno Santos de Jesus
     * @param id Identificador do jogo a ser removido.
     * @return true se o jogo foi localizado e removido; false caso contrário.
     */
    public boolean excluirJogo(int id) {
        Jogo jogo = consultarPorId(id);
        if (jogo != null) {
            this.listaDeJogos.remove(jogo);
            return true;
        }
        return false;
    }
}
