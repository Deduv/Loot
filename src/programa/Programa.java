package programa;

import controle.Biblioteca;
import java.util.Scanner;
import modelo.Jogo;
import modelo.JogoGratuito;
import modelo.JogoPago;

/**
 * Classe principal responsável apenas por iniciar o menu interativo,
 * ler as entradas do usuário via console e acionar os métodos da Biblioteca.
 *
 * @author Saulo Magno Santos de Jesus
 */
public class Programa {

    private static Scanner leitor = new Scanner(System.in);
    private static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        iniciarMenu();
        leitor.close();
    }

    private static void iniciarMenu() {
        int opcao = -1;

        do {
            System.out.println("\n=== BIBLIOTECA DE JOGOS (LOOT) ===");
            System.out.println("1 - Cadastrar Jogo");
            System.out.println("2 - Listar Todos os Jogos");
            System.out.println("3 - Buscar Jogo por ID");
            System.out.println("4 - Atualizar Jogo (Horas / Nota)");
            System.out.println("5 - Excluir Jogo por ID");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            if (!leitor.hasNextInt()) {
                System.out.println("Entrada inválida! Digite apenas números.");
                leitor.nextLine();
                continue;
            }

            opcao = leitor.nextInt();
            leitor.nextLine();

            if (opcao >= 2 && opcao <= 5 && biblioteca.estaVazia()) {
                System.out.println("\n[Aviso] A biblioteca ainda está vazia! Cadastre um jogo na Opção 1 primeiro.");
                continue;
            }

            switch (opcao) {
                case 1:
                    lerDadosJogo();
                    break;
                case 2:
                    biblioteca.imprimirLista();
                    break;
                case 3:
                    buscarJogo();
                    break;
                case 4:
                    atualizarJogo();
                    break;
                case 5:
                    removerJogo();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha um número entre 0 e 5.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void lerDadosJogo() {
        System.out.println("\n--- CADASTRAR JOGO ---");
        System.out.print("Tipo de jogo (1-Pago | 2-Gratuito): ");
        if (!leitor.hasNextInt()) {
            System.out.println("Tipo inválido! Digite 1 ou 2.");
            leitor.nextLine();
            return;
        }
        int tipo = leitor.nextInt();
        leitor.nextLine();

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo inválido! Operação cancelada.");
            return;
        }

        System.out.print("Título: ");
        String titulo = leitor.nextLine();

        System.out.print("Gênero: ");
        String genero = leitor.nextLine();

        System.out.print("Horas jogadas: ");
        double horas = leitor.nextDouble();

        System.out.print("Nota de avaliação (0 a 10): ");
        double nota = leitor.nextDouble();

        System.out.print("Ano de lançamento: ");
        int ano = leitor.nextInt();
        leitor.nextLine();

        int novoId = biblioteca.gerarProximoId();

        if (tipo == 1) {
            System.out.print("Preço pago (R$): ");
            double precoPago = leitor.nextDouble();
            leitor.nextLine();

            biblioteca.incluirJogo(new JogoPago(novoId, titulo, genero, horas, nota, ano, precoPago));
        } else {
            System.out.print("Gasto com itens extras (R$): ");
            double gastoExtras = leitor.nextDouble();
            leitor.nextLine();

            biblioteca.incluirJogo(new JogoGratuito(novoId, titulo, genero, horas, nota, ano, gastoExtras));
        }

        System.out.println("Jogo cadastrado com sucesso!");
    }

    private static void buscarJogo() {
        System.out.println("\n--- BUSCAR JOGO ---");
        System.out.print("Digite o ID do jogo: ");
        int idBusca = leitor.nextInt();
        leitor.nextLine();

        Jogo encontrado = biblioteca.consultarPorId(idBusca);
        biblioteca.imprimirJogo(encontrado);
    }

    private static void atualizarJogo() {
        System.out.println("\n--- ATUALIZAR JOGO ---");
        System.out.print("Digite o ID do jogo para atualizar: ");
        int idAlt = leitor.nextInt();

        System.out.print("Novas horas jogadas: ");
        double novasHoras = leitor.nextDouble();

        System.out.print("Nova nota de avaliação (0 a 10): ");
        double novaNota = leitor.nextDouble();
        leitor.nextLine();

        if (biblioteca.alterarJogo(idAlt, novasHoras, novaNota)) {
            System.out.println("Dados atualizados com sucesso!");
        } else {
            System.out.println("Não foi possível atualizar. Verifique o ID informado.");
        }
    }

    private static void removerJogo() {
        System.out.println("\n--- EXCLUIR JOGO ---");
        System.out.print("Digite o ID do jogo que deseja remover: ");
        int idExclusao = leitor.nextInt();
        leitor.nextLine();

        if (biblioteca.excluirJogo(idExclusao)) {
            System.out.println("Jogo removido com sucesso!");
        } else {
            System.out.println("Jogo com ID " + idExclusao + " não encontrado.");
        }
    }
}
