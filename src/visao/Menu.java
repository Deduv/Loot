package visao;

import controle.Biblioteca;
import java.util.NoSuchElementException;
import java.util.Scanner;
import modelo.Jogo;
import modelo.JogoGratuito;
import modelo.JogoPago;

/**
 * @author Eduardo Santana dos Santos
 */

public class Menu {

    private final Scanner leitor = new Scanner(System.in);
    private final Biblioteca biblioteca = new Biblioteca();

    public void iniciarMenu() {
        int opcao = -1;

        try {
            do {
                exibirOpcoes();
                opcao = lerInteiro("Escolha uma opção: ");

                if (opcao >= 2 && opcao <= 5 && biblioteca.estaVazia()) {
                    System.out.println("A biblioteca está vazia! Cadastre um jogo primeiro.");
                    continue;
                }

                executarOpcao(opcao);
            } while (opcao != 0);
        } catch (NoSuchElementException e) {
            System.out.println("\nEntrada encerrada. Saindo do sistema...");
        }
    }

    private void exibirOpcoes() {
        System.out.println("\n=== BIBLIOTECA DE JOGOS (LOOT) ===");
        System.out.println("1 - Cadastrar Jogo");
        System.out.println("2 - Listar Todos os Jogos");
        System.out.println("3 - Buscar Jogo por ID");
        System.out.println("4 - Atualizar Jogo (Horas / Nota)");
        System.out.println("5 - Excluir Jogo por ID");
        System.out.println("0 - Sair");
    }

    private void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarJogo();
                break;
            case 2:
                listarJogos();
                break;
            case 3:
                buscarJogo();
                break;
            case 4:
                alterarJogo();
                break;
            case 5:
                excluirJogo();
                break;
            case 0:
                System.out.println("Encerrando o sistema... Até logo!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarJogo() {
        int tipo = lerTipo();
        String titulo = lerTexto("Título: ");
        String genero = lerTexto("Gênero: ");
        double horas = lerDecimal("Horas jogadas: ");
        double nota = lerDecimal("Nota (0 a 10): ");
        int ano = lerInteiro("Ano de lançamento: ");

        try {
            Jogo novoJogo;
            if (tipo == 1) {
                double preco = lerDecimal("Preço pago (R$): ");
                novoJogo = new JogoPago(0, titulo, genero, horas, nota, ano, preco);
            } else {
                double gasto = lerDecimal("Gasto com itens extras (R$): ");
                novoJogo = new JogoGratuito(0, titulo, genero, horas, nota, ano, gasto);
            }
            biblioteca.incluirJogo(novoJogo);
            System.out.println("Jogo cadastrado com sucesso! ID: #" + novoJogo.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        }
    }

    private void listarJogos() {
        System.out.println("\n========== LISTA DE JOGOS CADASTRADOS ==========");
        for (Jogo jogo : biblioteca.listarJogos()) {
            exibirJogo(jogo);
        }
    }

    private void buscarJogo() {
        Jogo jogo = biblioteca.consultarPorId(lerInteiro("ID do jogo: "));
        if (jogo == null) {
            System.out.println("Jogo não encontrado.");
        } else {
            exibirJogo(jogo);
        }
    }

    private void alterarJogo() {
        int id = lerInteiro("ID do jogo: ");
        if (biblioteca.consultarPorId(id) == null) {
            System.out.println("Jogo não encontrado.");
            return;
        }

        double novasHoras = lerDecimal("Novas horas jogadas: ");
        double novaNota = lerDecimal("Nova nota (0 a 10): ");
        try {
            biblioteca.alterarJogo(id, novasHoras, novaNota);
            System.out.println("Jogo atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível atualizar: " + e.getMessage());
        }
    }

    private void excluirJogo() {
        System.out.println(biblioteca.excluirJogo(lerInteiro("ID do jogo: "))
                ? "Jogo removido com sucesso!" : "Jogo não encontrado.");
    }

    private void exibirJogo(Jogo jogo) {
        System.out.println(jogo.exibirResumo());
        System.out.println(jogo.exibirDetalhes());
        System.out.println("Análise: " + jogo.analisarCustoBeneficio());
        System.out.println("----------------------------------------");
    }

    private int lerTipo() {
        while (true) {
            int tipo = lerInteiro("Tipo (1-Pago | 2-Gratuito): ");
            if (tipo == 1 || tipo == 2) {
                return tipo;
            }
            System.out.println("Tipo inválido! Digite 1 ou 2.");
        }
    }

    private String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = leitor.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("Este campo não pode ficar vazio.");
        }
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = leitor.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
            }
        }
    }

    private double lerDecimal(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = leitor.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(entrada);
                if (Double.isFinite(valor)) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // tratado pela mensagem abaixo
            }
            System.out.println("Entrada inválida! Digite um número (ex.: 9,5 ou 9.5).");
        }
    }

    public void fechar() {
        leitor.close();
    }
}
