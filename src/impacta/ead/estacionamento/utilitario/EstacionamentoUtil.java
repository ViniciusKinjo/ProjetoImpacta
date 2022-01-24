package impacta.ead.estacionamento.utilitario;

import impacta.ead.estacionamento.negocio.Movimentacao;

/**
 * Representa uma classe de apoio às demais do sistema
 *
 * @author Vinicius Kinjo
 *
 */

public class EstacionamentoUtil {
    /**
     * Valida a placa com o padrao nacional LLL - NNNN
     * L = Letra
     * N = Numero
     * @param placa Placa do veiculo
     * @return true se atender o padrao e false se não atender o padrao
     */
    public boolean validadarPadraoPlaca(String placa){
        //TODO implementar
        return false;
    }

    /**
     * O calculo do valor da estada do veiculo baseado no tarifario e na hora
     * de entrada e saida do veiculo
     *
     * Altera a propria instancia do parametro
     *
     * @param movimentacao Instancia da movimentacao
     */

    public void calcularPagamento(Movimentacao movimentacao){
        //TODO implementar
    }
}
