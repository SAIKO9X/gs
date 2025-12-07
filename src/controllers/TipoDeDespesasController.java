package controllers;

import java.util.List;
import java.util.ArrayList;

import modelos.classes.TipoDeDespesa;
import modelos.interfaces.ITipoDeDespesasCRUD;
import persistencia.TipoDeDespesasDAO;

public class TipoDeDespesasController {

    private ITipoDeDespesasCRUD dao = new TipoDeDespesasDAO();

    // Cadastrar novo tipo de despesa
    public String cadastrarTipo(String descricao) {
        try {
            if (descricao == null || descricao.trim().isEmpty()) {
                return "Descrição não pode estar vazia.";
            }

            List<TipoDeDespesa> lista = dao.listaDeTiposDeDespesas();

            // Gerar ID corretamente (último + 1)
            int novoId = lista.size() == 0 ? 1 : lista.get(lista.size() - 1).getIdTipoDeDespesa() + 1;

            TipoDeDespesa novo = new TipoDeDespesa(novoId, descricao);

            dao.salvar(novo);

            return "Tipo de despesa cadastrado com sucesso!";

        } catch (Exception e) {
            return "Erro ao cadastrar: " + e.getMessage();
        }
    }

    // Listar todos
    public List<TipoDeDespesa> listar() {
        try {
            return dao.listaDeTiposDeDespesas(); // <-- corresponde à interface/DAO
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Atualizar registro existente
    public String atualizarTipo(int id, String novaDescricao) {
        try {
            if (novaDescricao == null || novaDescricao.trim().isEmpty()) {
                return "Descrição não pode estar vazia.";
            }

            TipoDeDespesa atualizado = new TipoDeDespesa(id, novaDescricao);

            dao.atualizar(atualizado);

            return "Registro atualizado com sucesso!";

        } catch (Exception e) {
            return "Erro ao atualizar: " + e.getMessage();
        }
    }

    // Remover pelo ID
    public String removerTipo(int idTipoDeDespesa) {
        try {
            dao.remover(idTipoDeDespesa);
            return "Tipo de despesa removido.";
        } catch (Exception e) {
            return "Erro ao remover: " + e.getMessage();
        }
    }
}
