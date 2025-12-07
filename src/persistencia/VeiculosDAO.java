package persistencia;

import modelos.classes.STATUS;
import modelos.classes.Veiculo;
import modelos.interfaces.IVeiculosCRUD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class VeiculosDAO implements IVeiculosCRUD {

  private String nomeDoArquivoNoDisco = "./src/bancodedados/Veiculos.txt";


  public boolean idExiste(int id) throws Exception {
    for (Veiculo v : listaDeVeiculos()) {
      if (v.getIdVeiculo() == id) {
        return true;
      }
    }
    return false;
  }


  public boolean placaExiste(String placa) throws Exception {
    for (Veiculo v : listaDeVeiculos()) {
      if (v.getPlaca().equalsIgnoreCase(placa)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void salvar(Veiculo veiculo) throws Exception {
    try {
      FileWriter fw = new FileWriter(nomeDoArquivoNoDisco, true);
      BufferedWriter bw = new BufferedWriter(fw);

      String str = veiculo.getIdVeiculo() + ";" +
        veiculo.getPlaca() + ";" +
        veiculo.getMarca() + ";" +
        veiculo.getModelo() + ";" +
        veiculo.getAnoFabricacao() + ";" +
        veiculo.getStatus() + "\n";

      bw.write(str);
      bw.close();

    } catch (Exception erro) {
      throw new Exception("Persistencia - Salvar - " + erro.getMessage());
    }
  }

  @Override
  public ArrayList<Veiculo> listaDeVeiculos() throws Exception {
    try {
      ArrayList<Veiculo> lista = new ArrayList<>();

      FileReader fr = new FileReader(nomeDoArquivoNoDisco);
      BufferedReader br = new BufferedReader(fr);

      String linha;
      while ((linha = br.readLine()) != null) {
        String dados[] = linha.split(";");

        int id = Integer.parseInt(dados[0]);
        String placa = dados[1];
        String marca = dados[2];
        String modelo = dados[3];
        int ano = Integer.parseInt(dados[4]);

        String statusStr = dados[5];
        STATUS statusEnum;
        try {
          statusEnum = STATUS.valueOf(statusStr.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
          statusEnum = STATUS.ATIVO;
        }

        Veiculo obj = new Veiculo(id, placa, marca, modelo, ano, statusEnum);
        lista.add(obj);
      }
      br.close();
      return lista;

    } catch (Exception erro) {
      throw new Exception("Persistencia - Lista - " + erro.getMessage());
    }
  }

  @Override
  public Veiculo buscarPorId(int idProcurado) throws Exception {
    try {
      FileReader fr = new FileReader(nomeDoArquivoNoDisco);
      BufferedReader br = new BufferedReader(fr);

      String linha;
      while ((linha = br.readLine()) != null) {
        String dados[] = linha.split(";");
        int id = Integer.parseInt(dados[0]);

        if (id == idProcurado) {
          String placa = dados[1];
          String marca = dados[2];
          String modelo = dados[3];
          int ano = Integer.parseInt(dados[4]);

          String statusStr = dados[5];
          STATUS statusEnum;
          try {
            statusEnum = STATUS.valueOf(statusStr.toUpperCase().trim());
          } catch (IllegalArgumentException e) {
            statusEnum = STATUS.ATIVO;
          }

          br.close();
          return new Veiculo(id, placa, marca, modelo, ano, statusEnum);
        }
      }
      br.close();
      return null;

    } catch (Exception erro) {
      throw new Exception("Persistencia - BuscarPorId - " + erro.getMessage());
    }
  }

  @Override
  public void atualizar(Veiculo veiculoAtualizado) throws Exception {
    try {
      ArrayList<Veiculo> lista = listaDeVeiculos();

      FileWriter fw = new FileWriter(nomeDoArquivoNoDisco);
      BufferedWriter bw = new BufferedWriter(fw);

      for (Veiculo obj : lista) {
        if (obj.getIdVeiculo() == veiculoAtualizado.getIdVeiculo()) {
          bw.write(
            veiculoAtualizado.getIdVeiculo() + ";" +
              veiculoAtualizado.getPlaca() + ";" +
              veiculoAtualizado.getMarca() + ";" +
              veiculoAtualizado.getModelo() + ";" +
              veiculoAtualizado.getAnoFabricacao() + ";" +
              veiculoAtualizado.getStatus() + "\n"
          );
        } else {
          bw.write(
            obj.getIdVeiculo() + ";" +
              obj.getPlaca() + ";" +
              obj.getMarca() + ";" +
              obj.getModelo() + ";" +
              obj.getAnoFabricacao() + ";" +
              obj.getStatus() + "\n"
          );
        }
      }
      bw.close();

    } catch (Exception erro) {
      throw new Exception("Persistencia - Atualizar - " + erro.getMessage());
    }
  }


  @Override
  public void remover(int idRemover) throws Exception {
    try {
      ArrayList<Veiculo> lista = listaDeVeiculos();
      FileWriter fw = new FileWriter(nomeDoArquivoNoDisco);
      BufferedWriter bw = new BufferedWriter(fw);

      for (Veiculo obj : lista) {
        if (obj.getIdVeiculo() != idRemover) {
          bw.write(obj.getIdVeiculo() + ";" + obj.getPlaca() + ";" + obj.getMarca() + ";" +
            obj.getModelo() + ";" + obj.getAnoFabricacao() + ";" + obj.getStatus() + "\n");
        }
      }
      bw.close();
    } catch (Exception erro) {
      throw new Exception("Persistencia - Remover - " + erro.getMessage());
    }
  }
}
