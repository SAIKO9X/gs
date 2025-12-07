/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import modelos.classes.Veiculo;
import persistencia.VeiculosDAO;

import javax.swing.*;
import java.util.ArrayList;

public class VeiculoController {

  private VeiculosDAO dao;

  public VeiculoController() {
    dao = new VeiculosDAO();
  }


  public boolean validar(Veiculo v) {

    if (v.getIdVeiculo() <= 0) {
      JOptionPane.showMessageDialog(null, "ID inválido!");
      return false;
    }

    if (v.getPlaca().trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "A placa não pode ser vazia!");
      return false;
    }

    if (v.getMarca().trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "A marca não pode ser vazia!");
      return false;
    }

    if (v.getModelo().trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "O modelo não pode ser vazio!");
      return false;
    }

    if (v.getAnoFabricacao() < 1900 || v.getAnoFabricacao() > 2100) {
      JOptionPane.showMessageDialog(null, "Ano de fabricação inválido!");
      return false;
    }

    return true;
  }

  public void cadastrar(Veiculo v) throws Exception {


    if (!validar(v)) return;


    ArrayList<Veiculo> lista = dao.listaDeVeiculos();

    for (Veiculo obj : lista) {
      if (obj.getIdVeiculo() == v.getIdVeiculo()) {
        JOptionPane.showMessageDialog(null,
          "Erro: Já existe um veículo cadastrado com esse ID!");
        return;
      }
    }

    dao.salvar(v);
    JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!");
  }


  public void atualizar(Veiculo v) throws Exception {

    if (!validar(v)) return;

    dao.atualizar(v);
    JOptionPane.showMessageDialog(null, "Veículo atualizado com sucesso!");
  }

  public void remover(int id) throws Exception {
    dao.remover(id);
    JOptionPane.showMessageDialog(null, "Veículo removido!");
  }


  public ArrayList<Veiculo> listar() throws Exception {
    return dao.listaDeVeiculos();
  }


  public Veiculo buscar(int id) throws Exception {
    return dao.buscarPorId(id);
  }


  public ArrayList<Veiculo> filtrar(String modelo, String marca, String status) throws Exception {

    ArrayList<Veiculo> todos = dao.listaDeVeiculos();
    ArrayList<Veiculo> filtrados = new ArrayList<>();

    for (Veiculo v : todos) {

      boolean combinaModelo = modelo.isEmpty() || v.getModelo().equalsIgnoreCase(modelo);
      boolean combinaMarca = marca.isEmpty() || v.getMarca().equalsIgnoreCase(marca);
      boolean combinaStatus = status.equals("Todos") ||
        v.getStatus().toString().equalsIgnoreCase(status);

      if (combinaModelo && combinaMarca && combinaStatus) {
        filtrados.add(v);
      }
    }

    return filtrados;
  }

  public ArrayList<Veiculo> listarInativos() throws Exception {
    ArrayList<Veiculo> todos = dao.listaDeVeiculos();
    ArrayList<Veiculo> inativos = new ArrayList<>();

    for (Veiculo v : todos) {
      if (v.getStatus() != null && v.getStatus().toString().equalsIgnoreCase("INATIVO")) {
        inativos.add(v);
      }
    }

    return inativos;
  }
}
