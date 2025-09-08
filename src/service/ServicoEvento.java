package service;

import model.Evento;
import model.Usuario;
import util.GerenciadorArquivo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ServicoEvento {
    private final List<Evento> eventos = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void criarEvento() {
        scanner.nextLine(); // limpar buffer
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Categoria (festa/show/esporte/etc): ");
        String categoria = scanner.nextLine();
        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String horario = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Evento evento = new Evento(nome, endereco, categoria, horario, descricao);
        eventos.add(evento);
        salvarEventos();
        System.out.println("Evento cadastrado com sucesso!");
    }

    public void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        eventos.stream()
                .sorted(Comparator.comparing(e -> LocalDateTime.parse(e.getHorario(), formatter)))
                .forEach(System.out::println);
    }

    public void confirmarParticipacao(Usuario usuario) {
        listarEventos();
        System.out.print("Digite o nome do evento para confirmar participação: ");
        scanner.nextLine();
        String nomeEvento = scanner.nextLine();

        eventos.stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nomeEvento))
                .findFirst()
                .ifPresentOrElse(
                        e -> {
                            e.adicionarParticipante(usuario);
                            salvarEventos();
                            System.out.println("Participação confirmada!");
                        },
                        () -> System.out.println("Evento não encontrado.")
                );
    }

    public void cancelarParticipacao(Usuario usuario) {
        System.out.print("Digite o nome do evento para cancelar participação: ");
        scanner.nextLine();
        String nomeEvento = scanner.nextLine();

        eventos.stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nomeEvento))
                .findFirst()
                .ifPresentOrElse(
                        e -> {
                            e.removerParticipante(usuario);
                            salvarEventos();
                            System.out.println("Participação cancelada!");
                        },
                        () -> System.out.println("Evento não encontrado.")
                );
    }

    public void mostrarEventosConfirmados(Usuario usuario) {
        eventos.stream()
                .filter(e -> e.getParticipantes().contains(usuario))
                .forEach(System.out::println);
    }

    public void carregarEventos() {
        eventos.clear();
        eventos.addAll(GerenciadorArquivo.carregarEventos());
    }

    public void salvarEventos() {
        GerenciadorArquivo.salvarEventos(eventos);
    }
}
