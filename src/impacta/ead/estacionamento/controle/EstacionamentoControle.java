package impacta.ead.estacionamento.controle;

import impacta.ead.estacionamento.negocio.Movimentacao;

import java.time.LocalDateTime;
import java.util.List;

public class EstacionamentoControle {

    public void processarEntradata(String placa, String marca, String modelo, String cor){
        //TODO implementar
    }

    public Movimentacao processarSaida(String placa){
        //TODO implementar
        return null;
    }

    public List<Movimentacao> emitirRelatorio(LocalDateTime data){
        //TODO implementar
        return null;
    }
}
