package programa;

import controle.Biblioteca;
import java.util.Scanner;
import modelo.Jogo;
import modelo.JogoGratuito;
import modelo.JogoPago;

public class Programa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
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

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine();
                continue;
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao >= 2 && opcao <= 5 && biblioteca.estaVazia()) {
                System.out.println("\n[Aviso] A biblioteca ainda está vazia! Cadastre um jogo na Opção 1 primeiro.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- CADASTRAR JOGO ---");
                    System.out.print("Tipo de jogo (1-Pago | 2-Gratuito): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Tipo inválido! Digite 1 ou 2.");
                        scanner.nextLine();
                        break;
                    }
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    if (tipo != 1 && tipo != 2) {
                        System.out.println("Tipo inválido! Operação cancelada.");
                        break;
                    }

                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Gênero: ");
                    String genero = scanner.nextLine();

                    System.out.print("Horas jogadas: ");
                    double horas = scanner.nextDouble();

                    System.out.print("Nota de avaliação (0 a 10): ");
                    double nota = scanner.nextDouble();

                    System.out.print("Ano de lançamento: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();

                    if (tipo == 1) {
                        System.out.print("Preço pago (R$): ");
                        double precoPago = scanner.nextDouble();
                        scanner.nextLine();

                        Jogo novoPago = new JogoPago(0, titulo, genero, horas, nota, ano, precoPago);
                        biblioteca.incluirJogo(novoPago);
                    } else {
                        System.out.print("Gasto com itens extras (R$): ");
                        double gastoExtras = scanner.nextDouble();
                        scanner.nextLine();

                        Jogo novoGratuito = new JogoGratuito(0, titulo, genero, horas, nota, ano, gastoExtras);
                        biblioteca.incluirJogo(novoGratuito);
                    }

                    System.out.println("Jogo cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE JOGOS ---");
                    biblioteca.imprimirLista();
                    break;

                case 3:
                    System.out.println("\n--- BUSCAR JOGO ---");
                    System.out.print("Digite o ID do jogo: ");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();

                    Jogo encontrado = biblioteca.consultarPorId(idBusca);
                    if (encontrado != null) {
                    biblioteca.imprimirJogo(encontrado);
                    } else {
                    System.out.println("Jogo com ID " + idBusca + " não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- ATUALIZAR JOGO ---");
                    System.out.print("Digite o ID do jogo para atualizar: ");
                    int idAlt = scanner.nextInt();

                    System.out.print("Novas horas jogadas: ");
                    double novasHoras = scanner.nextDouble();

                    System.out.print("Nova nota de avaliação (0 a 10): ");
                    double novaNota = scanner.nextDouble();
                    scanner.nextLine();

                    boolean atualizado = biblioteca.alterarJogo(idAlt, novasHoras, novaNota);
                    if (atualizado) {
                        System.out.println("Dados atualizados com sucesso!");
                    } else {
                        System.out.println("Não foi possível atualizar. Verifique o ID informado.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- EXCLUIR JOGO ---");
                    System.out.print("Digite o ID do jogo que deseja remover: ");
                    int idExclusao = scanner.nextInt();
                    scanner.nextLine();

                    if (biblioteca.excluirJogo(idExclusao)) {
                        System.out.println("Jogo removido com sucesso!");
                    } else {
                        System.out.println("Jogo com ID " + idExclusao + " não encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha um número entre 0 e 5.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}
