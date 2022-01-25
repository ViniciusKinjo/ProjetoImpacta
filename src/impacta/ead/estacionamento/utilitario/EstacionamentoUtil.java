package impacta.ead.estacionamento.utilitario;

import impacta.ead.estacionamento.negocio.Movimentacao;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static boolean validarPadraoPlaca(String placa){
        String padrao ="[A-Z][A-Z][A-Z]-\\d\\d\\d\\d";
        Pattern p = Pattern.compile(padrao);
        Matcher m = p.matcher(placa);
        //TODO implementar
        return m.matches();
    }

    /**
     * Recuperar uma propriedade do arquivo de configuracao da aplicacao
     * configuration.txt
     * @param propriedade
     * @return valor associado a propriedade
     */

    public static String get(String propriedade) {
        Properties prop = new Properties();
        String valor = null;
        try {
            prop.load(EstacionamentoUtil.class.getResourceAsStream("/recursos/configuration.txt"));
            valor = prop.getProperty(propriedade);

        } catch (IOException e) {
            assert false : "Configuracao não carregada";
        }
        return valor;
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

    public static String getDataAsString(LocalDateTime dataHoraEntrada){

        return dataHoraEntrada.toString();
    }
}
