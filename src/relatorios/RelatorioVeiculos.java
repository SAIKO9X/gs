/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorios;

import modelos.classes.Veiculo;
import persistencia.VeiculosDAO;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RelatorioVeiculos {

  public void gerar() {
    try {
      VeiculosDAO dao = new VeiculosDAO();
      ArrayList<Veiculo> lista = dao.listaDeVeiculos();

      FileWriter fw = new FileWriter("Relatorio_Veiculos.txt");
      BufferedWriter bw = new BufferedWriter(fw);

      bw.write("RELATORIO DE VEICULOS");
      bw.newLine();
      bw.write("-----------------------------");
      bw.newLine();
      bw.newLine();

      for (Veiculo v : lista) {
        bw.write("ID: " + v.getIdVeiculo());
        bw.newLine();
        bw.write("Placa: " + v.getPlaca());
        bw.newLine();
        bw.write("Marca: " + v.getMarca());
        bw.newLine();
        bw.write("Modelo: " + v.getModelo());
        bw.newLine();
        bw.write("Ano: " + v.getAnoFabricacao());
        bw.newLine();
        bw.write("Status: " + v.getStatus());
        bw.newLine();
        bw.write("-----------------------------");
        bw.newLine();
      }

      bw.close();

    } catch (Exception e) {
      System.out.println("Erro ao gerar relatório: " + e.getMessage());
    }
  }

  public void exportarParaExcel() {
    String nomeArquivo = "Relatorio_Frota.csv";

    try (BufferedWriter writer = new BufferedWriter(
      new OutputStreamWriter(new FileOutputStream(nomeArquivo), StandardCharsets.UTF_8))) {
      writer.write('\ufeff');
      writer.write("ID;Placa;Marca;Modelo;Ano;Status");
      writer.newLine();

      VeiculosDAO dao = new VeiculosDAO();
      for (Veiculo v : dao.listaDeVeiculos()) {
        writer.write(v.getIdVeiculo() + ";" +
          v.getPlaca() + ";" +
          v.getMarca() + ";" +
          v.getModelo() + ";" +
          v.getAnoFabricacao() + ";" +
          v.getStatus());
        writer.newLine();
      }

      JOptionPane.showMessageDialog(null, "Planilha gerada com sucesso na pasta do projeto!\nArquivo: " + nomeArquivo);

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Erro ao gerar planilha: " + e.getMessage());
    }
  }
}

