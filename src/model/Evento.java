package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Evento implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String endereco;
    private String categoria;
    private String horario;
    private String descricao;
    private List<Usuario> participantes = new ArrayList<>();

    // Novo campo
    private LocalDateTime eventExecutionDateTime;

    public Evento(String nome, String endereco, String categoria, String horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.eventExecutionDateTime = LocalDateTime.now(); // marca o momento da criação
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCategoria() { return categoria; }
    public String getHorario() { return horario; }
    public String getDescricao() { return descricao; }
    public List<Usuario> getParticipantes() { return participantes; }
    public LocalDateTime getEventExecutionDateTime() { return eventExecutionDateTime; }

    public void adicionarParticipante(Usuario usuario) {
        if (!participantes.contains(usuario)) {
            participantes.add(usuario);
        }
    }

    public void removerParticipante(Usuario usuario) {
        participantes.remove(usuario);
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Evento: " + nome +
               " | Endereço: " + endereco +
               " | Categoria: " + categoria +
               " | Horário: " + horario +
               " | Descrição: " + descricao +
               " | Criado em: " + eventExecutionDateTime.format(fmt);
    }
}
