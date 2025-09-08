import service.ServicoEvento;
import service.ServicoUsuario;
import model.Usuario;

import java.util.Scanner;

/**
 * Classe principal do sistema de eventos.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ServicoEvento servicoEvento = new ServicoEvento();
    private static final ServicoUsuario servicoUsuario = new ServicoUsuario();
    private static Usuario usuarioLogado;

    public static void main(String[] args) {
        servicoEvento.carregarEventos();

        boolean rodando = true;
        while (rodando) {
            exibirMenu();
            int escolha = lerInteiro("Escolha uma opção: ");
            switch (escolha) {
                case 1 -> cadastrarUsuario();
                case 2 -> servicoEvento.criarEvento();
                case 3 -> servicoEvento.listarEventos();
                case 4 -> confirmarParticipacao();
                case 5 -> cancelarParticipacao();
                case 6 -> servicoEvento.mostrarEventosConfirmados(usuarioLogado);
                case 7 -> rodando = false;
                default -> System.out.println("Opção inválida.");
            }
        }

        servicoEvento.salvarEventos();
        System.out.println("Sistema encerrado.");
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Cadastrar Evento");
        System.out.println("3. Listar Eventos");
        System.out.println("4. Confirmar Participação em Evento");
        System.out.println("5. Cancelar Participação em Evento");
        System.out.println("6. Mostrar Meus Eventos Confirmados");
        System.out.println("7. Sair");
    }

    private static int lerInteiro(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void cadastrarUsuario() {
        scanner.nextLine(); // limpar buffer
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        usuarioLogado = servicoUsuario.cadastrarUsuario(nome, email, telefone);
        System.out.println("Usuário cadastrado e logado com sucesso!");
    }

    private static void confirmarParticipacao() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa cadastrar/login antes de participar.");
            return;
        }
        servicoEvento.confirmarParticipacao(usuarioLogado);
    }

    private static void cancelarParticipacao() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa cadastrar/login antes de cancelar participação.");
            return;
        }
        servicoEvento.cancelarParticipacao(usuarioLogado);
    }
}
