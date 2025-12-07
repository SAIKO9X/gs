package visao;

import controllers.MovimentacaoController;
import controllers.TipoDeDespesasController;
import controllers.VeiculoController;
import modelos.classes.Movimentacao;
import modelos.classes.TipoDeDespesa;
import modelos.classes.Veiculo;
import relatorios.PdfGerador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaDeRelatorios extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaDeRelatorios.class.getName());

    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JButton jButtonRelatorioControle;
    private javax.swing.JButton jButtonRelatorioDespesas;
    private javax.swing.JButton jButtonRelatorioMov;
    private javax.swing.JButton jButtonExportarExcel;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelSubtitulo;
    private javax.swing.JLabel jLabelIconeTopo;
    private javax.swing.JPanel jPanelFundoVerde;
    private javax.swing.JPanel jPanelFundoAzul;
    private javax.swing.JPanel jPanelBotoesGrid;

    public TelaDeRelatorios() {
        initComponents();
        this.setLocationRelativeTo(null); 
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GynLog - Central de Relatórios");
        setMinimumSize(new Dimension(800, 600));

        jPanelFundoVerde = new javax.swing.JPanel();
        jPanelFundoAzul = new javax.swing.JPanel();
        jPanelBotoesGrid = new javax.swing.JPanel();

        jPanelFundoVerde.setBackground(new java.awt.Color(31, 146, 126));
        jPanelFundoAzul.setBackground(new java.awt.Color(16, 58, 83));
        
        jPanelBotoesGrid.setOpaque(false);
        jPanelBotoesGrid.setLayout(new GridLayout(2, 2, 20, 20)); 

        jLabelTitulo = new javax.swing.JLabel("GYNLOG");
        jLabelTitulo.setFont(new java.awt.Font("Unispace", Font.BOLD, 36));
        jLabelTitulo.setForeground(Color.WHITE);

        jLabelSubtitulo = new javax.swing.JLabel("Controle de Frotas");
        jLabelSubtitulo.setFont(new java.awt.Font("Unispace", Font.BOLD, 18));
        jLabelSubtitulo.setForeground(Color.WHITE);
        
        jLabelIconeTopo = new javax.swing.JLabel("Relatórios");
        jLabelIconeTopo.setFont(new java.awt.Font("Unispace", Font.BOLD, 24));
        jLabelIconeTopo.setForeground(new Color(200, 200, 200));

        jButtonRelatorioControle = criarBotaoPrincipal("Relatório de Frota");
        jButtonRelatorioMov = criarBotaoPrincipal("Relatório Movimentação");
        jButtonRelatorioDespesas = criarBotaoPrincipal("Tipos de Despesas");
        jButtonExportarExcel = criarBotaoPrincipal("Exportar Excel (.csv)");

        jButtonRelatorioControle.addActionListener(evt -> jButtonRelatorioControleActionPerformed(evt));
        jButtonRelatorioMov.addActionListener(evt -> jButtonRelatorioMovActionPerformed(evt));
        jButtonRelatorioDespesas.addActionListener(evt -> jButtonRelatorioDespesasActionPerformed(evt));
        jButtonExportarExcel.addActionListener(evt -> jButtonExportarExcelActionPerformed(evt));

        jPanelBotoesGrid.add(jButtonRelatorioControle);
        jPanelBotoesGrid.add(jButtonRelatorioMov);
        jPanelBotoesGrid.add(jButtonRelatorioDespesas);
        jPanelBotoesGrid.add(jButtonExportarExcel);

        jButtonVoltar = new javax.swing.JButton("Voltar ao Menu");
        jButtonVoltar.setBackground(new java.awt.Color(220, 53, 69));
        jButtonVoltar.setForeground(Color.WHITE);
        jButtonVoltar.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        jButtonVoltar.setFocusPainted(false);
        jButtonVoltar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        jButtonVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonVoltar.addActionListener(evt -> jButtonVoltarActionPerformed(evt));

        // --- MONTAGEM DO LAYOUT ---
        jPanelFundoAzul.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        jPanelFundoAzul.add(jPanelBotoesGrid, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.0; 
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(0, 0, 20, 20); 
        jPanelFundoAzul.add(jButtonVoltar, gbc);


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanelFundoVerde);
        jPanelFundoVerde.setLayout(jPanel1Layout);
        
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFundoAzul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Painel Azul expande
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelIconeTopo)
                            .addComponent(jLabelSubtitulo))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelSubtitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelIconeTopo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelFundoAzul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanelFundoVerde);
        pack();
    }

    private JButton criarBotaoPrincipal(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(new Color(240, 240, 240));
        btn.setForeground(new Color(16, 58, 83));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(250, 80));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // --- AÇÕES ---
    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        dispose();
    }

    private void jButtonRelatorioControleActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String[] opcoes = {"1. Geral (Todos os Veículos)", "2. Apenas Inativos"};
            String escolhaStr = (String) JOptionPane.showInputDialog(null,
                    "Selecione o filtro de veículos:", "Relatório de Frota",
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            if (escolhaStr == null) return;

            VeiculoController veiculoController = new VeiculoController();
            ArrayList<Veiculo> listaParaRelatorio = null;
            String tituloRelatorio = "";
            String nomeArquivo = "";

            if (escolhaStr.startsWith("1")) {
                listaParaRelatorio = veiculoController.listar();
                tituloRelatorio = "Relatório Geral da Frota";
                nomeArquivo = "Relatorio_Frota_Geral.pdf";
            } else if (escolhaStr.startsWith("2")) {
                listaParaRelatorio = veiculoController.listarInativos();
                tituloRelatorio = "Relatório de Veículos Inativos";
                nomeArquivo = "Relatorio_Frota_Inativos.pdf";
                if (listaParaRelatorio.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nenhum veículo inativo encontrado.");
                    return;
                }
            }

            List<String> linhas = new ArrayList<>();
            linhas.add("----------------------------------------------------------------------------------");
            for (Veiculo v : listaParaRelatorio) {
                linhas.add("ID: " + v.getIdVeiculo() + " | Placa: " + v.getPlaca() + " | Modelo: " + v.getModelo() + " | Marca: " + v.getMarca());
                linhas.add("Ano: " + v.getAnoFabricacao() + " | Status: " + v.getStatus());
                linhas.add("----------------------------------------------------------------------------------");
            }
            linhas.add(" ");
            linhas.add("Total de veículos listados: " + listaParaRelatorio.size());

            PdfGerador.gerarRelatorio(tituloRelatorio, linhas, nomeArquivo);
            JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso: " + nomeArquivo);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void jButtonRelatorioMovActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String[] opcoes = {
                "1. Geral (Todas as Movimentações)",
                "2. Filtrar por Veículo",
                "3. Somatório por Mês (Geral)",
                "4. Somatório por Mês (Combustível)",
                "5. Somatório de IPVA por Ano",
                "6. Relatório de Multas por Ano"
            };

            String escolhaStr = (String) JOptionPane.showInputDialog(null,
                    "Selecione o tipo de relatório:", "Gerador de Relatórios",
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            if (escolhaStr == null) return;

            relatorios.RelatorioMovimentacao relatorioService = new relatorios.RelatorioMovimentacao();
            controllers.MovimentacaoController movController = new controllers.MovimentacaoController();

            if (escolhaStr.startsWith("1")) {
                relatorioService.gerar();
                JOptionPane.showMessageDialog(this, "Relatório Geral gerado!");
            } else if (escolhaStr.startsWith("2")) {
                String idStr = JOptionPane.showInputDialog("Digite o ID do Veículo:");
                if (idStr != null && !idStr.isEmpty()) {
                    int idVeiculo = Integer.parseInt(idStr);
                    ArrayList<modelos.classes.Movimentacao> lista = movController.listarPorVeiculo(idVeiculo);
                    relatorioService.gerarPdfCustomizado(lista, "Relatório do Veículo " + idVeiculo, "Relatorio_Veiculo_" + idVeiculo + ".pdf");
                    JOptionPane.showMessageDialog(this, "PDF gerado com sucesso!");
                }
            } else if (escolhaStr.startsWith("3")) {
                String mesAno = JOptionPane.showInputDialog("Digite o Mês/Ano (ex: 12/2025):");
                if (mesAno != null && !mesAno.isEmpty()) {
                    ArrayList<modelos.classes.Movimentacao> lista = movController.filtrarPorMesETipo(mesAno, 0);
                    String nomeArq = "Relatorio_Mes_" + mesAno.replace("/", "-") + "_Geral.pdf";
                    relatorioService.gerarPdfCustomizado(lista, "Somatório Geral: " + mesAno, nomeArq);
                    JOptionPane.showMessageDialog(this, "Relatório mensal gerado: " + nomeArq);
                }
            } else if (escolhaStr.startsWith("4")) {
                String mesAno = JOptionPane.showInputDialog("Digite o Mês/Ano (ex: 12/2025):");
                if (mesAno != null && !mesAno.isEmpty()) {
                    controllers.TipoDeDespesasController tipoController = new controllers.TipoDeDespesasController();
                    int idCombustivel = -1;
                    for (modelos.classes.TipoDeDespesa t : tipoController.listar()) {
                        if (t.getDescricao().equalsIgnoreCase("Combustível") || t.getDescricao().equalsIgnoreCase("Combustivel")) {
                            idCombustivel = t.getIdTipoDeDespesa();
                            break;
                        }
                    }
                    if (idCombustivel == -1) {
                        JOptionPane.showMessageDialog(this, "Erro: 'Combustível' não encontrado!");
                        return;
                    }
                    ArrayList<modelos.classes.Movimentacao> lista = movController.filtrarPorMesETipo(mesAno, idCombustivel);
                    String nomeArq = "Relatorio_Mes_" + mesAno.replace("/", "-") + "_Combustivel.pdf";
                    relatorioService.gerarPdfCustomizado(lista, "Gastos com Combustível: " + mesAno, nomeArq);
                    JOptionPane.showMessageDialog(this, "Relatório gerado: " + nomeArq);
                }
            } else if (escolhaStr.startsWith("5")) {
                String anoStr = JOptionPane.showInputDialog("Digite o Ano (ex: 2025):");
                if (anoStr != null && !anoStr.isEmpty()) {
                    int ano = Integer.parseInt(anoStr);
                    controllers.TipoDeDespesasController tipoController = new controllers.TipoDeDespesasController();
                    int idIpva = -1;
                    for (modelos.classes.TipoDeDespesa t : tipoController.listar()) {
                        if (t.getDescricao().equalsIgnoreCase("IPVA")) {
                            idIpva = t.getIdTipoDeDespesa();
                            break;
                        }
                    }
                    if (idIpva == -1) {
                        JOptionPane.showMessageDialog(this, "Erro: 'IPVA' não encontrado!");
                        return;
                    }
                    ArrayList<modelos.classes.Movimentacao> lista = movController.filtrarPorAnoETipo(ano, idIpva);
                    String nomeArq = "Relatorio_IPVA_" + ano + ".pdf";
                    relatorioService.gerarPdfCustomizado(lista, "Relatório de IPVA - Ano: " + ano, nomeArq);
                    JOptionPane.showMessageDialog(this, "PDF gerado: " + nomeArq);
                }
            } else if (escolhaStr.startsWith("6")) {
                String anoStr = JOptionPane.showInputDialog("Digite o Ano (ex: 2025):");
                if (anoStr != null && !anoStr.isEmpty()) {
                    int ano = Integer.parseInt(anoStr);
                    controllers.TipoDeDespesasController tipoController = new controllers.TipoDeDespesasController();
                    int idMulta = -1;
                    for (modelos.classes.TipoDeDespesa t : tipoController.listar()) {
                        if (t.getDescricao().equalsIgnoreCase("Multa") || t.getDescricao().equalsIgnoreCase("Multas")) {
                            idMulta = t.getIdTipoDeDespesa();
                            break;
                        }
                    }
                    if (idMulta == -1) {
                        JOptionPane.showMessageDialog(this, "Erro: 'Multas' não encontrado!");
                        return;
                    }
                    ArrayList<modelos.classes.Movimentacao> lista = movController.filtrarPorAnoETipo(ano, idMulta);
                    if (lista.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Nenhuma multa encontrada para " + ano);
                    } else {
                        String nomeArq = "Relatorio_Multas_" + ano + ".pdf";
                        relatorioService.gerarPdfCustomizado(lista, "Relatório de Multas - Ano: " + ano, nomeArq);
                        JOptionPane.showMessageDialog(this, "PDF gerado: " + nomeArq);
                    }
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro de formato: Digite apenas números válidos.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void jButtonRelatorioDespesasActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            TipoDeDespesasController tipoDeDespesasController = new TipoDeDespesasController();
            List<TipoDeDespesa> lista = tipoDeDespesasController.listar();

            List<String> linhas = new ArrayList<>();
            for (TipoDeDespesa t : lista) {
                linhas.add("ID Tipo: " + t.getIdTipoDeDespesa() + " | Descrição: " + t.getDescricao());
            }

            PdfGerador.gerarRelatorio("Relatório de Tipos de Despesas", linhas, "relatorio_tipo_despesas.pdf");
            JOptionPane.showMessageDialog(this, "Relatório gerado: relatorio_tipo_despesas.pdf");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void jButtonExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {
        relatorios.RelatorioVeiculos relatorio = new relatorios.RelatorioVeiculos();
        relatorio.exportarParaExcel();
        JOptionPane.showMessageDialog(this, "Exportação solicitada.");
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new TelaDeRelatorios().setVisible(true));
    }
}