package modelos.interfaces;

import java.util.ArrayList;
import modelos.classes.Movimentacao;

public interface IMovimentacaoCRUD {
    void salvar(Movimentacao mov) throws Exception;
    ArrayList<Movimentacao> listagemDeMovimentacoes() throws Exception;
    Movimentacao buscarPorId(int id) throws Exception;
    void atualizar(Movimentacao mov) throws Exception;
    void remover(int id) throws Exception;
}
