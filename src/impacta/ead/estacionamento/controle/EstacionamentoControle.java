package impacta.ead.estacionamento.controle;

import impacta.ead.estacionamento.negocio.Movimentacao;
import impacta.ead.estacionamento.negocio.Vaga;
import impacta.ead.estacionamento.negocio.Veiculo;
import impacta.ead.estacionamento.persistencia.DAOEstacionamento;
import impacta.ead.estacionamento.utilitario.EstacionamentoUtil;

import java.time.LocalDateTime;
import java.util.List;

public class EstacionamentoControle {

    /**
     * A partir dos dados do veiculo informados pelo operador realiza o fluxo de entrada do veiculo
     * no estacionamento registrando a movimentacao gerada
     * @param placa Placa do veiculo
     * @param marca Marca do veiculo
     * @param modelo Modelo do veiculo
     * @param cor Cor do veiculo
     * @throws EstacionamentoException Quando o estacionamento estiver lotado
     * @throws VeiculoExeception QUando o padrao da placa for invalido
     */

    public void processarEntrada(String placa, String marca, String modelo, String cor) throws EstacionamentoException, VeiculoExeception {
        //verificar se o estacionamento está lotado
        if(!Vaga.temVagaLivre()){
            throw new EstacionamentoException("Estacionamento lotado");
        }
        //verificar o padrão de string da placa
        if(!EstacionamentoUtil.validarPadraoPlaca(placa)){
            throw new VeiculoExeception("Placa informada inválida!");
        }
        //criar uma instancia do veiculo
        Veiculo veiculo = new Veiculo(placa, marca, modelo, cor);
        //criar a movimentacao vinculado o veiculo e com a data de entrada corrente
        Movimentacao movimentacao = new Movimentacao(veiculo, LocalDateTime.now());
        //registrar na base de dados a informacao
        DAOEstacionamento dao = new DAOEstacionamento();
        dao.criar(movimentacao);
        //atualizar o numero de vagas
        Vaga.entrou();
    }

    /**
     * A partir de uma placa de veiculo informada, realiza o fluxo de saída de veículo do estacionamento
     * @param placa
     * @return
     */

    public Movimentacao processarSaida(String placa)
            throws VeiculoExeception, EstacionamentoException {

        //validar a placa
        if (!EstacionamentoUtil.validarPadraoPlaca(placa)) {
            throw new VeiculoExeception("Placa inv�lida!");
        }

        //Buscar a movimentacao aberta baseada na placa
        DAOEstacionamento dao = new DAOEstacionamento();
        Movimentacao movimentacao = dao.buscarMovimentacaoAberta(placa);

        if (movimentacao == null) {
            throw new EstacionamentoException("Ve�culo n�o encontrado!");
        }

        //Fazer o calculo do valor a ser pago
        movimentacao.setDataHoraSaida(LocalDateTime.now());
        EstacionamentoUtil.calcularValorPago(movimentacao);

        //Atualizar os dados da movimentacao
        dao.atualizar(movimentacao);

        //Atualizar o status da vaga
        Vaga.saiu();

        return movimentacao;
    }

        public List<Movimentacao> emitirRelatorio(LocalDateTime data){
            DAOEstacionamento dao = new DAOEstacionamento();
            return dao.consultarMovimentacoes(data);
        }

    public int inicializarOcupadas(){
        DAOEstacionamento dao = new DAOEstacionamento();
        return dao.getOcupadas();
    }
}
