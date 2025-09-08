package util;

import model.Evento;
import java.io.*;
import java.util.*;

public class GerenciadorArquivo {
    private static final String PASTA = "data";
    private static final String NOME_ARQUIVO = PASTA + File.separator + "eventos.data";

    public static void salvarEventos(List<Evento> novosEventos) {
        try {
            File pasta = new File(PASTA);
            if (!pasta.exists()) {
                pasta.mkdir(); // cria pasta data se não existir
            }

            // Carrega os eventos já existentes
            List<Evento> eventosExistentes = carregarEventos();

            // Adiciona os novos
            eventosExistentes.addAll(novosEventos);

            // Salva tudo de volta
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
                oos.writeObject(new ArrayList<>(eventosExistentes));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Evento> carregarEventos() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO))) {
            return (List<Evento>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar eventos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
