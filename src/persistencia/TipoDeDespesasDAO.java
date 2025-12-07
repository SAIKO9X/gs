/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import java.util.ArrayList;
import modelos.classes.TipoDeDespesa;
import modelos.interfaces.ITipoDeDespesasCRUD;
// Bibliotecas para IO em arquivo texto
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author ejmcc
 */
public class TipoDeDespesasDAO implements ITipoDeDespesasCRUD{
  //Atributos
  private String nomeDoArquivoNoDisco = "./src/bancodedados/TipoDeDespesas.txt";
  //Metodo Construtor
  public TipoDeDespesasDAO(){}
  @Override
  public void salvar(TipoDeDespesa tipoDeDespesa) throws Exception {
    try{
      //cria o arquivo
      FileWriter fw = new FileWriter(nomeDoArquivoNoDisco,true);
      //Criar o buffer do arquivo
      BufferedWriter bw = new BufferedWriter(fw);
      //Escreve no arquivo
      String str = tipoDeDespesa.getIdTipoDeDespesa() + ";" + tipoDeDespesa.getDescricao();
      bw.write(str);
      bw.newLine();
      //fecha o arquivo
      bw.close();		
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Salvar - "+erro.getMessage();
      throw new Exception(msg);
    }
  }

  @Override
  public TipoDeDespesa buscarPorId(int idTipoDeDespesa) throws Exception {
    try{
      //abrir um arquivo existente
      FileReader fr = new FileReader(nomeDoArquivoNoDisco);
      //Criar o buffer do arquivo
      BufferedReader br  = new BufferedReader(fr);
      String linha = "";
      while((linha=br.readLine())!=null){
        String[] vetorStr = linha.split(";");
        int idTipoDeDespesaAux = Integer.parseInt(vetorStr[0]);
        if(idTipoDeDespesaAux == idTipoDeDespesa){
          String descricao = vetorStr[1];
          TipoDeDespesa objTipoDeDespesa = null;
          objTipoDeDespesa = new TipoDeDespesa(idTipoDeDespesaAux,descricao);
          br.close();
          return objTipoDeDespesa;
        }
      }
      br.close();
      return null;
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Buscar - "+erro.getMessage();
      throw new Exception(msg);
    }   
  }

  @Override
  public void atualizar(TipoDeDespesa tipoDeDespesa) throws Exception {
    try {
        ArrayList<TipoDeDespesa> listagem = this.listaDeTiposDeDespesas();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeDoArquivoNoDisco))) {
            for (TipoDeDespesa obj : listagem) {
                String str;
                if (obj.getIdTipoDeDespesa() == tipoDeDespesa.getIdTipoDeDespesa()) {
                    str = tipoDeDespesa.getIdTipoDeDespesa() + ";" + tipoDeDespesa.getDescricao();
                } else {
                    str = obj.getIdTipoDeDespesa() + ";" + obj.getDescricao();
                }
                bw.write(str);
                bw.newLine();
            }
        }
    } catch (Exception erro) {
        throw new Exception("Persistencia - Metodo Atualizar - " + erro.getMessage(), erro);
    }
  }

  @Override
  public void remover(int idTipoDeDespesa) throws Exception {
    try {
      ArrayList<TipoDeDespesa> listagem = null;
      listagem = this.listaDeTiposDeDespesas();
      //cria o arquivo
      FileWriter fw = new FileWriter(nomeDoArquivoNoDisco);
      //Criar o buffer do arquivo
      BufferedWriter bw =new BufferedWriter(fw);
      for(TipoDeDespesa obj : listagem){
        if(obj.getIdTipoDeDespesa() != idTipoDeDespesa){
            String str = obj.getIdTipoDeDespesa() + ";" + obj.getDescricao();
            bw.write(str);
            bw.newLine();
        }
      }
      bw.close();
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Remover - "+erro.getMessage();
      throw new Exception(msg);
    } 
  }

    @Override
    public ArrayList<TipoDeDespesa> listaDeTiposDeDespesas() throws Exception {
        try{
          ArrayList<TipoDeDespesa> lista = new ArrayList<>();
          //abrir um arquivo existente
          FileReader fr = new FileReader(nomeDoArquivoNoDisco);
          //Criar o buffer do arquivo
          BufferedReader br  = new BufferedReader(fr);
          String linha = "";
          while((linha=br.readLine())!=null){
            String[] vetorStr = linha.split(";");
            int idTipoDeDespesaAux = Integer.parseInt(vetorStr[0]);
            String descricao = vetorStr[1];
            lista.add(new TipoDeDespesa(idTipoDeDespesaAux,descricao));
          }
          br.close();
          return lista;
        }catch(Exception erro){
          String msg = "Persistencia - Metodo Lista - "+erro.getMessage();
          throw new Exception(msg);
        }  
    } 
}
