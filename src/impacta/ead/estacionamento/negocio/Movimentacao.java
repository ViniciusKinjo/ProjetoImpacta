package impacta.ead.estacionamento.negocio;

import java.time.LocalDateTime;

/**
 * Representa o fluxo do veiculo dentro da estacionamento, ou seja, ele
 * contem as informacoes de entrada e saida do veiculo e o valor pago na estada.
 *
 * @author Vinicius Kinjo
 *
 */

public class Movimentacao  {
    private Veiculo veiculo;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private double valor;

    public Movimentacao(Veiculo veiculo, LocalDateTime dataEntrada){
        this.veiculo = veiculo;
        this.dataHoraEntrada = dataEntrada;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public double getValor() {
        return valor;
    }
}
