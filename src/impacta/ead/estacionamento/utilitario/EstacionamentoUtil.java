package impacta.ead.estacionamento.utilitario;

import impacta.ead.estacionamento.negocio.Movimentacao;
import impacta.ead.estacionamento.negocio.Tarifario;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
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

    public static void calcularValorPago(Movimentacao movimentacao){
        LocalDateTime inicio = movimentacao.getDataHoraEntrada();
        LocalDateTime fim = movimentacao.getDataHoraSaida();
        double valor = 0;

        long diffHoras = inicio.until(fim, ChronoUnit.HOURS);

        if(diffHoras > 0){
            valor += Tarifario.VALOR_HORA;
            fim = fim.minus(1, ChronoUnit.HOURS);
        }

        long diffMinutos = inicio.until(fim, ChronoUnit.MINUTES);

        valor += (diffMinutos/Tarifario.INCREMENTO_MINUTOS) * Tarifario.VALOR_INCREMENTAL;

        movimentacao.setValor(valor);
    }

    public static String getDataAsString(LocalDateTime dataHoraEntrada){

        return dataHoraEntrada.toString();
    }

    public static LocalDateTime getDate(String rdataEntrada){
        return LocalDateTime.parse(rdataEntrada, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
    }

    public static String getDisplayData(LocalDateTime data){
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public static String gerarTextoFaturamento(LocalDateTime data, List<Movimentacao> movimentacoes){
        double totalFaturado = 0;
        String texto = "";
        for(Movimentacao movimentacao : movimentacoes){
            totalFaturado += movimentacao.getValor();
        }

        String sAno = ""+data.getYear();
        String sMes = data.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());

        texto = "Faturamento do mês de " + sMes;
        texto += " de " + sAno + " foi de R$ " + totalFaturado;

        return texto;
    }

}
