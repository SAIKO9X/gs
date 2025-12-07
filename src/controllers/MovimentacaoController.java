package controllers;

import modelos.classes.Movimentacao;
import modelos.interfaces.IMovimentacaoCRUD;
import persistencia.MovimentacaoDAO;

import java.util.ArrayList;

public class MovimentacaoController {

  private IMovimentacaoCRUD MovimentacaoDAO;

  public MovimentacaoController() {
    MovimentacaoDAO = new MovimentacaoDAO();
  }

  // Cadastrar movimentação
  public void cadastrar(Movimentacao mov) throws Exception {
    if (mov.getIdMovimentacao() <= 0) {
      throw new Exception("ID da movimentação inválido!");
    }
    if (mov.getIdVeiculo() <= 0) {
      throw new Exception("ID do veículo inválido!");
    }
    if (mov.getIdTipoDespesa() <= 0) {
      throw new Exception("ID do tipo de despesa inválido!");
    }
    if (mov.getData().isEmpty()) {
      throw new Exception("Data não pode estar vazia!");
    }
    if (mov.getValor() <= 0) {
      throw new Exception("Valor inválido!");
    }
    MovimentacaoDAO.salvar(mov);
  }

  public ArrayList<Movimentacao> listarPorVeiculo(int idVeiculo) throws Exception {
    ArrayList<Movimentacao> todas = MovimentacaoDAO.listagemDeMovimentacoes();
    ArrayList<Movimentacao> filtradas = new ArrayList<>();

    for (Movimentacao m : todas) {
      if (m.getIdVeiculo() == idVeiculo) {
        filtradas.add(m);
      }
    }

    if (filtradas.isEmpty()) {
      throw new Exception("Nenhuma movimentação encontrada para o veículo ID: " + idVeiculo);
    }

    return filtradas;
  }

  public ArrayList<Movimentacao> filtrarPorMesETipo(String mesAno, int idTipoDespesa) throws Exception {
    ArrayList<Movimentacao> todas = MovimentacaoDAO.listagemDeMovimentacoes();
    ArrayList<Movimentacao> filtradas = new ArrayList<>();

    for (Movimentacao m : todas) {
      boolean dataConfere = m.getData().contains(mesAno);

      boolean tipoConfere = (idTipoDespesa == 0) || (m.getIdTipoDespesa() == idTipoDespesa);

      if (dataConfere && tipoConfere) {
        filtradas.add(m);
      }
    }

    if (filtradas.isEmpty()) throw new Exception("Nenhum registro encontrado para " + mesAno);

    return filtradas;
  }

  public ArrayList<Movimentacao> filtrarPorAnoETipo(int ano, int idTipoDespesa) throws Exception {
    ArrayList<Movimentacao> todas = MovimentacaoDAO.listagemDeMovimentacoes();
    ArrayList<Movimentacao> filtradas = new ArrayList<>();

    for (Movimentacao m : todas) {
      try {
        String[] partesData = m.getData().split("/");

        if (partesData.length == 3) {
          int anoMovimentacao = Integer.parseInt(partesData[2]);

          if (anoMovimentacao == ano && m.getIdTipoDespesa() == idTipoDespesa) {
            filtradas.add(m);
          }
        }
      } catch (Exception e) {
        System.out.println("Data inválida ignorada: " + m.getData());
      }
    }

    return filtradas;
  }

  public ArrayList<Movimentacao> listar() throws Exception {
    return MovimentacaoDAO.listagemDeMovimentacoes();
  }


  public Movimentacao buscar(int id) throws Exception {
    return MovimentacaoDAO.buscarPorId(id);
  }


  public void atualizar(Movimentacao mov) throws Exception {
    MovimentacaoDAO.atualizar(mov);
  }


  public void remover(int id) throws Exception {
    MovimentacaoDAO.remover(id);
  }
}
