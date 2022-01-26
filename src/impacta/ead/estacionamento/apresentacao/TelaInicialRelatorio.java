package impacta.ead.estacionamento.apresentacao;

import impacta.ead.estacionamento.controle.EstacionamentoControle;
import impacta.ead.estacionamento.negocio.Movimentacao;
import impacta.ead.estacionamento.utilitario.EstacionamentoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class TelaInicialRelatorio extends JFrame implements ActionListener {
    private JComboBox cboAno;
    private JComboBox cbomes;

    public TelaInicialRelatorio(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600,140));
        setResizable(false);
        setTitle("Filtro do Relat\u00F3rio");
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 15, 40));

        JLabel lblAno = new JLabel("Ano:");
        lblAno.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lblAno);

        cboAno = new JComboBox();
        cboAno.setModel(new DefaultComboBoxModel(new String[] {"2016", "2015", "2014", "2013", "2012"}));
        cboAno.setFont(new Font("Tahoma", Font.PLAIN, 14));
        getContentPane().add(cboAno);

        JLabel lblMes = new JLabel("M\u00EAs:");
        lblMes.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lblMes);

        cbomes = new JComboBox();
        cbomes.setModel(new DefaultComboBoxModel(new String[] {"Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
        cbomes.setFont(new Font("Tahoma", Font.PLAIN, 14));
        getContentPane().add(cbomes);

        JButton btnGerar = new JButton("Gerar");
        btnGerar.addActionListener(this);
        btnGerar.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(btnGerar);

        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //recupera do bom o ano e mes escolhidos
        int ano = Integer.parseInt( (String) cboAno.getSelectedItem());
        int mes = (Integer) cbomes.getSelectedIndex()+1;

        //buscar as movimentacoes do mes e ano informado
        EstacionamentoControle controle = new EstacionamentoControle();
        LocalDateTime data = LocalDateTime.of(ano, mes,1,0,0);
        List<Movimentacao> movimentacaoList = controle.emitirRelatorio(data);

        //Exibi a tela de conteudo e faturamento
        TelaResultadoRelatorio relatorio = new TelaResultadoRelatorio(this, movimentacaoList, data);

        dispose();
    }


}
