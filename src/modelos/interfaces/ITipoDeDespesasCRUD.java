/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelos.interfaces;
import java.util.ArrayList;
import modelos.classes.TipoDeDespesa;

public interface ITipoDeDespesasCRUD {
    void salvar(TipoDeDespesa tipo) throws Exception;
    ArrayList<TipoDeDespesa> listaDeTiposDeDespesas() throws Exception;
    TipoDeDespesa buscarPorId(int id) throws Exception;
    void atualizar(TipoDeDespesa tipo) throws Exception;
    void remover(int id) throws Exception;
}
