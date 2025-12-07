/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorios;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import modelos.classes.TipoDeDespesa;
import persistencia.TipoDeDespesasDAO;

public class RelatorioTipoDeDespesa {

    public void gerar() {
        try {
            TipoDeDespesasDAO dao = new TipoDeDespesasDAO();
            ArrayList<TipoDeDespesa> lista = dao.listaDeTiposDeDespesas();

            FileWriter fw = new FileWriter("Relatorio_TiposDeDespesa.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("RELATORIO DE TIPOS DE DESPESA");
            bw.newLine();
            bw.write("-----------------------------");
            bw.newLine();
            bw.newLine();

            for (TipoDeDespesa t : lista) {
                bw.write("ID: " + t.getIdTipoDeDespesa());
                bw.newLine();
                bw.write("Descrição: " + t.getDescricao());
                bw.newLine();
                bw.write("-----------------------------");
                bw.newLine();
            }

            bw.close();

        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }
}

