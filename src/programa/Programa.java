package programa;

import controle.Biblioteca;
import java.util.Scanner;
import modelo.JogoGratuito;
import modelo.JogoPago;

/**
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
                System.out.println("A biblioteca está vazia! Cadastre um jogo primeiro.");
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
                    System.out.print("ID do jogo: ");
                    biblioteca.imprimirJogo(biblioteca.consultarPorId(leitor.nextInt()));
                    break;
                case 4:
                    System.out.print("ID do jogo: ");
                    int idAlterar = leitor.nextInt();
                    System.out.print("Novas horas jogadas: ");
                    double novasHoras = leitor.nextDouble();
                    System.out.print("Nova nota (0 a 10): ");
                    double novaNota = leitor.nextDouble();
                    System.out.println(biblioteca.alterarJogo(idAlterar, novasHoras, novaNota)
                            ? "Jogo atualizado com sucesso!" : "Jogo não encontrado.");
                    break;
                case 5:
                    System.out.print("ID do jogo: ");
                    System.out.println(biblioteca.excluirJogo(leitor.nextInt())
                            ? "Jogo removido com sucesso!" : "Jogo não encontrado.");
                    break;
                case 0:
                    System.out.println("Encerrando o sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void lerDadosJogo() {
        System.out.print("Tipo (1-Pago | 2-Gratuito): ");
        int tipo = leitor.nextInt();
        leitor.nextLine();
        System.out.print("Título: ");
        String titulo = leitor.nextLine();
        System.out.print("Gênero: ");
        String genero = leitor.nextLine();
        System.out.print("Horas jogadas: ");
        double horas = leitor.nextDouble();
        System.out.print("Nota (0 a 10): ");
        double nota = leitor.nextDouble();
        System.out.print("Ano de lançamento: ");
        int ano = leitor.nextInt();

        if (tipo == 1) {
            System.out.print("Preço pago (R$): ");
            biblioteca.incluirJogo(new JogoPago(0, titulo, genero, horas, nota, ano, leitor.nextDouble()));
        } else {
            System.out.print("Gasto com itens extras (R$): ");
            biblioteca.incluirJogo(new JogoGratuito(0, titulo, genero, horas, nota, ano, leitor.nextDouble()));
        }
        System.out.println("Jogo cadastrado com sucesso!");
    }
}
