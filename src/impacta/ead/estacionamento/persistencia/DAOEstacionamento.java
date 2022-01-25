package impacta.ead.estacionamento.persistencia;

import impacta.ead.estacionamento.controle.EstacionamentoException;
import impacta.ead.estacionamento.negocio.Movimentacao;
import impacta.ead.estacionamento.negocio.Vaga;
import impacta.ead.estacionamento.negocio.Veiculo;
import impacta.ead.estacionamento.utilitario.EstacionamentoUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class DAOEstacionamento {

    /**
     * Armazena os dados da movimentacao
     * @param movimentacao
     * @throws EstacionamentoException Se ouver erro de registro
     */

    public void criar(Movimentacao movimentacao) throws EstacionamentoException{
        String cmd1 = EstacionamentoUtil.get("insertMov");
        String cmd2 = EstacionamentoUtil.get("atualizaVaga");

        Connection conexao = null;
        try {
            conexao = getConnection();
            conexao.setAutoCommit(false);

            PreparedStatement stmt = conexao.prepareStatement(cmd1);
            stmt.setString(1, movimentacao.getVeiculo().getPlaca());
            stmt.setString(2, movimentacao.getVeiculo().getMarca());
            stmt.setString(3, movimentacao.getVeiculo().getModelo());
            stmt.setString(4, movimentacao.getVeiculo().getCor());
            stmt.setString(5, EstacionamentoUtil.getDataAsString(movimentacao.getDataHoraEntrada()));
            stmt.execute();

            stmt = conexao.prepareStatement(cmd2);

            stmt.setInt(1, Vaga.ocupadas() + 1);

            stmt.execute();
            conexao.commit();
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                conexao.rollback();
                throw new EstacionamentoException("Erro ao registrar o veiculo");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void atualizar(Movimentacao movimentacao){
        //TODO implementar
    }

    public Movimentacao buscarMovimentacaoAberta(String placa){
        //TODO implementar
        return null;
    }

    public List<Movimentacao> consultarMovimentacoes(LocalDateTime data){
        //TODO implementar
        return null;
    }

    private static Connection getConnection() throws SQLException {
        String url = EstacionamentoUtil.get("url");
        String usuario = EstacionamentoUtil.get("usuario");
        String senha = EstacionamentoUtil.get("senha");

        Connection conexao = DriverManager.getConnection(url, usuario, senha);
        return conexao;
    }

    public static void closeConection(Connection conexao){
        if(conexao != null){
            try{
               conexao.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public int getOcupadas(){
        int ocupadas = 0;
        Connection conexao = null;
        String cmd = EstacionamentoUtil.get("consultaOcupadas");

        try {
            conexao = getConnection();
            PreparedStatement ps = conexao.prepareStatement(cmd);
            ResultSet resultado = ps.executeQuery();
            if(resultado.next()){
                ocupadas = resultado.getInt("ocupadas");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConection(conexao);
        }
        return ocupadas;
    }
}
