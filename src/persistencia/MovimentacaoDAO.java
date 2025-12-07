package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import modelos.classes.Movimentacao;
import modelos.interfaces.IMovimentacaoCRUD;

public class MovimentacaoDAO implements IMovimentacaoCRUD {

    private String nomeDoArquivo = "./src/bancodedados/Movimentacoes.txt";

    @Override
    public void salvar(Movimentacao mov) throws Exception {
        try {
            FileWriter fw = new FileWriter(nomeDoArquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String linha = mov.getIdMovimentacao() + ";" +
                           mov.getIdVeiculo() + ";" +
                           mov.getIdTipoDespesa() + ";" +
                           mov.getData() + ";" +
                           mov.getValor() + ";" +
                           mov.getDescricao() + ";" +
                           mov.getStatus();   // <-- AGORA SALVA STATUS

            bw.write(linha);
            bw.newLine();
            bw.close();

        } catch (Exception e) {
            throw new Exception("Erro ao salvar movimentação: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Movimentacao> listagemDeMovimentacoes() throws Exception {
        try {
            ArrayList<Movimentacao> lista = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivo));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idMov = Integer.parseInt(partes[0]);
                int idVeic = Integer.parseInt(partes[1]);
                int idTipo = Integer.parseInt(partes[2]);
                String data = partes[3];
                double valor = Double.parseDouble(partes[4]);
                String desc = partes[5];
                String status = partes.length > 6 ? partes[6] : "Ativa"; 
                // <-- SE NÃO TIVER STATUS, INICIA COMO 'Ativa'

                lista.add(new Movimentacao(idMov, idVeic, idTipo, desc, data, valor, status));
            }

            br.close();
            return lista;

        } catch (Exception e) {
            throw new Exception("Erro ao listar movimentações: " + e.getMessage());
        }
    }

    @Override
    public Movimentacao buscarPorId(int id) throws Exception {
        ArrayList<Movimentacao> lista = listagemDeMovimentacoes();
        for (Movimentacao m : lista) {
            if (m.getIdMovimentacao() == id) return m;
        }
        return null;
    }

    @Override
    public void atualizar(Movimentacao mov) throws Exception {
        try {
            ArrayList<Movimentacao> lista = listagemDeMovimentacoes();
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeDoArquivo));

            for (Movimentacao m : lista) {

                if (m.getIdMovimentacao() == mov.getIdMovimentacao()) {
                    // <-- ESCREVE A VERSÃO ATUALIZADA
                    String linha = mov.getIdMovimentacao() + ";" +
                                   mov.getIdVeiculo() + ";" +
                                   mov.getIdTipoDespesa() + ";" +
                                   mov.getData() + ";" +
                                   mov.getValor() + ";" +
                                   mov.getDescricao() + ";" +
                                   mov.getStatus();
                    bw.write(linha);
                } else {
                    // <-- ESCREVE A VERSÃO ANTIGA
                    String linha = m.getIdMovimentacao() + ";" +
                                   m.getIdVeiculo() + ";" +
                                   m.getIdTipoDespesa() + ";" +
                                   m.getData() + ";" +
                                   m.getValor() + ";" +
                                   m.getDescricao() + ";" +
                                   m.getStatus();
                    bw.write(linha);
                }
                bw.newLine();
            }
            bw.close();

        } catch (Exception e) {
            throw new Exception("Erro ao atualizar movimentação: " + e.getMessage());
        }
    }

    @Override
    public void remover(int id) throws Exception {
        try {
            ArrayList<Movimentacao> lista = listagemDeMovimentacoes();
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeDoArquivo));

            for (Movimentacao m : lista) {
                if (m.getIdMovimentacao() != id) {

                    String linha = m.getIdMovimentacao() + ";" +
                                   m.getIdVeiculo() + ";" +
                                   m.getIdTipoDespesa() + ";" +
                                   m.getData() + ";" +
                                   m.getValor() + ";" +
                                   m.getDescricao() + ";" +
                                   m.getStatus();

                    bw.write(linha);
                    bw.newLine();
                }
            }

            bw.close();

        } catch (Exception e) {
            throw new Exception("Erro ao remover movimentação: " + e.getMessage());
        }
    }
}
