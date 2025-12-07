package relatorios;

import modelos.classes.Movimentacao;
import persistencia.MovimentacaoDAO;

import java.util.ArrayList;
import java.util.List;

public class RelatorioMovimentacao {

  public void gerar() {
    try {
      MovimentacaoDAO dao = new MovimentacaoDAO();
      ArrayList<Movimentacao> lista = dao.listagemDeMovimentacoes();
      gerarPdfCustomizado(lista, "Relatório Geral de Movimentações", "Relatorio_Movimentacoes_Geral.pdf");
    } catch (Exception e) {
      System.out.println("Erro ao gerar relatório geral: " + e.getMessage());
    }
  }

  public void gerarPdfCustomizado(ArrayList<Movimentacao> lista, String tituloRelatorio, String nomeArquivo) {
    List<String> linhas = new ArrayList<>();
    double totalValor = 0;

    linhas.add("--------------------------------------------------");

    for (Movimentacao m : lista) {
      linhas.add("ID Movimentação: " + m.getIdMovimentacao());
      linhas.add("Veículo ID: " + m.getIdVeiculo());
      linhas.add("Tipo de Despesa ID: " + m.getIdTipoDespesa());
      linhas.add("Descrição: " + m.getDescricao());
      linhas.add("Data: " + m.getData());
      linhas.add("Valor: R$ " + String.format("%.2f", m.getValor()));
      linhas.add("Status: " + m.getStatus());
      linhas.add("--------------------------------------------------");

      totalValor += m.getValor();
    }

    linhas.add(" ");
    linhas.add("TOTAL REGISTRADO: R$ " + String.format("%.2f", totalValor));

    PdfGerador.gerarRelatorio(tituloRelatorio, linhas, nomeArquivo);
  }
}