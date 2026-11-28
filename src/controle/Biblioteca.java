package controle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.Jogo;

/**
 * 
 * @author Eduardo Santana dos Santos
 * 
 */
public class Biblioteca {

    private final ArrayList<Jogo> listaDeJogos;
    private int proximoId = 1;

    public Biblioteca() {
        this.listaDeJogos = new ArrayList<>();
    }

    private int gerarProximoId() {
        return this.proximoId++;
    }

    public void incluirJogo(Jogo novoJogo) {
        if (novoJogo == null) {
            throw new IllegalArgumentException("O jogo não pode ser nulo.");
        }

        int id = novoJogo.getId();
        if (id == 0) {
            novoJogo.setId(gerarProximoId());
        } else if (consultarPorId(id) != null) {
            throw new IllegalArgumentException("Já existe um jogo com o ID #" + id + ".");
        } else if (id >= this.proximoId) {
            this.proximoId = id + 1;
        }

        this.listaDeJogos.add(novoJogo);
    }

    public List<Jogo> listarJogos() {
        return Collections.unmodifiableList(this.listaDeJogos);
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
            jogo.atualizarProgresso(novasHoras, novaNota);
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
