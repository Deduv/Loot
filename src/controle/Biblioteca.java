package controle;

import java.util.ArrayList;
import modelo.Jogo;
import modelo.JogoGratuito;
import modelo.JogoPago;

/**
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

    public int gerarProximoId() {
        return this.proximoId++;
    }

    public void incluirJogo(Jogo novoJogo) {
        if (novoJogo == null) {
            return;
        }
        if (novoJogo.getId() <= 0) {
            novoJogo.setId(gerarProximoId());
        }
        this.listaDeJogos.add(novoJogo);
    }

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

    public Jogo consultarPorId(int id) {
        for (Jogo jogo : this.listaDeJogos) {
            if (jogo.getId() == id) {
                return jogo;
            }
        }
        return null;
    }

    public boolean alterarJogo(int id, double novasHoras, double novaNota) {
        Jogo jogo = consultarPorId(id);
        if (jogo != null) {
            jogo.setHorasJogadas(novasHoras);
            jogo.setNotaAvaliacao(novaNota);
            return true;
        }
        return false;
    }

    public boolean estaVazia() {
        return this.listaDeJogos.isEmpty();
    }

    public boolean excluirJogo(int id) {
        Jogo jogo = consultarPorId(id);
        if (jogo != null) {
            this.listaDeJogos.remove(jogo);
            return true;
        }
        return false;
    }
}
