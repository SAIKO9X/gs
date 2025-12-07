package modelos.classes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class TipoDeDespesa {
  //Atributos
  private int idTipoDeDespesa = 0;
  private String descricao = "";
  
  //Metodos
  public TipoDeDespesa() {
  }

  public TipoDeDespesa(int idTipoDeDespesa, String descricao) {
    this.idTipoDeDespesa = idTipoDeDespesa;
    this.descricao = descricao;
  }
  
  //Getters e Setters
  public int getIdTipoDeDespesa() {
    return idTipoDeDespesa;
  }
  public void setIdTipoDeDespesa(int idTipoDeDespesa) {
    this.idTipoDeDespesa = idTipoDeDespesa;
  }
  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public String toString(){
      return idTipoDeDespesa + ";" + descricao;
  }
}
