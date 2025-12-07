/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelos.interfaces;
import java.util.ArrayList;
import modelos.classes.Veiculo;

public interface IVeiculosCRUD {
  void salvar(Veiculo veiculo) throws Exception;
  ArrayList<Veiculo> listaDeVeiculos() throws Exception;
  Veiculo buscarPorId(int idVeiculo) throws Exception;
  void atualizar(Veiculo veiculo) throws Exception;
  void remover(int idVeiculo) throws Exception;
}
