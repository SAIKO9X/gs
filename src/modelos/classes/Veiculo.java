package modelos.classes;

public class Veiculo {

  private int idVeiculo;
  private String placa;
  private String marca;
  private String modelo;
  private int anoFabricacao;
  private STATUS status;

  public Veiculo() {
  }

  public Veiculo(int idVeiculo, String placa, String marca, String modelo, int anoFabricacao, STATUS status) throws Exception {
    this.idVeiculo = idVeiculo;
    setPlaca(placa);
    setMarca(marca);
    setModelo(modelo);
    setAnoFabricacao(anoFabricacao);
    this.status = status;
  }

  public int getIdVeiculo() {
    return idVeiculo;
  }

  public void setIdVeiculo(int idVeiculo) {
    this.idVeiculo = idVeiculo;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) throws Exception {
    if (placa == null || placa.trim().isEmpty()) {
      throw new Exception("A Placa não pode ser vazia!");
    }
    this.placa = placa;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) throws Exception {
    if (marca == null || marca.trim().isEmpty()) {
      throw new Exception("A Marca não pode ser vazia!");
    }
    this.marca = marca;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) throws Exception {
    if (modelo == null || modelo.trim().isEmpty()) {
      throw new Exception("O Modelo não pode ser vazio!");
    }
    this.modelo = modelo;
  }

  public int getAnoFabricacao() {
    return anoFabricacao;
  }

  public void setAnoFabricacao(int anoFabricacao) throws Exception {
    if (anoFabricacao < 1900 || anoFabricacao > 2100) {
      throw new Exception("Ano de fabricação inválido! Informe um ano entre 1900 e 2100.");
    }
    this.anoFabricacao = anoFabricacao;
  }

  public STATUS getStatus() {
    return status;
  }

  public void setStatus(STATUS status) {
    this.status = status;
  }
}