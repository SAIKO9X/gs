package modelos.classes;

public class Movimentacao {

  private int idMovimentacao;
  private int idVeiculo;
  private int idTipoDespesa;
  private String descricao;
  private String data;
  private double valor;
  private String status;

  public Movimentacao() {
  }

  public Movimentacao(int idMovimentacao, int idVeiculo, int idTipoDespesa, String descricao, String data, double valor, String status) throws Exception {
    this.idMovimentacao = idMovimentacao;
    this.idVeiculo = idVeiculo;
    this.idTipoDespesa = idTipoDespesa;

    setDescricao(descricao);
    setData(data);
    setValor(valor);

    this.status = status;
  }

  public int getIdMovimentacao() {
    return idMovimentacao;
  }

  public void setIdMovimentacao(int idMovimentacao) {
    this.idMovimentacao = idMovimentacao;
  }

  public int getIdVeiculo() {
    return idVeiculo;
  }

  public void setIdVeiculo(int idVeiculo) {
    this.idVeiculo = idVeiculo;
  }

  public int getIdTipoDespesa() {
    return idTipoDespesa;
  }

  public void setIdTipoDespesa(int idTipoDespesa) {
    this.idTipoDespesa = idTipoDespesa;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) throws Exception {
    if (descricao == null || descricao.trim().isEmpty()) {
      throw new Exception("A descrição da despesa é obrigatória!");
    }
    this.descricao = descricao;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) throws Exception {
    if (data == null || data.trim().isEmpty()) {
      throw new Exception("A data é obrigatória!");
    }
    this.data = data;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) throws Exception {
    if (valor <= 0) {
      throw new Exception("O valor da despesa deve ser maior que zero!");
    }
    this.valor = valor;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}