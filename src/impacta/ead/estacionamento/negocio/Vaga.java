package impacta.ead.estacionamento.negocio;

import impacta.ead.estacionamento.controle.EstacionamentoControle;

/**
 * Representa as informações relativas a vagas do estacionamento ou status de ocupação
 *
 * @author Vinicius Kinjo
 */

public class Vaga {
    public static int TOTAL_VAGAS = 100;
    private static int vagasOcupadas = inicializarOcupadas();

    private Vaga(){}


    /**
     * Verificar a existencia de alguma vaga livre no estacionamento
     * @return true se houver alguma vaga e false se não tiver vaga
     */
    public static boolean temVagaLivre(){
        return (vagasOcupadas < TOTAL_VAGAS);
    }

    /**
     * Buscar o status atual das vagas do estacionamneto
     */

    public static int inicializarOcupadas(){
        EstacionamentoControle controle = new EstacionamentoControle();
        return controle.inicializarOcupadas();
    }

    /**
     * Retorna o numero de vagas ocupadas
     * @return numero total de vagas ocupadas
     */

    public static int ocupadas(){
        return Vaga.vagasOcupadas;
    }

    /**
     * Retornar o numero de vagas livres
     * @return numero total de vagas livres
     */

    public static int livres(){
        return TOTAL_VAGAS - Vaga.vagasOcupadas;
    }

    /**
     * Atualiza o numero de vagas ocupadas após a entrada do veiculo
     */
    public static void entrou(){
        Vaga.vagasOcupadas++;
    }


    public static void saiu(){
        Vaga.vagasOcupadas--;
    }
}
