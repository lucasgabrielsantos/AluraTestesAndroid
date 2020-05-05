package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Formas de Declarar o método de teste de unidade :
 * <p>
 * [nomedometodo][Estado do teste][resultado esperado]
 * ou
 * [deve] [resultado esperado] [estado de teste] (mais intuitivo ainda)
 */

/**
 * exemplo de teste abaixo
 * <p>
 * /** criar cenário de teste
 * Leilao console=new Leilao("Console");
 * <p>
 * /** executar ação esperada
 * String descricaoDevolvida=console.getDescricao();
 * <p>
 * /**testar resultado esperado
 * assertEquals("Console",descricaoDevolvida);
 */

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario LUCAS = new Usuario("Lucas");

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {

        String descricaoDevolvida = CONSOLE.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance() {

        CONSOLE.propoe(new Lance(LUCAS, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, DELTA);

    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {

        CONSOLE.propoe(new Lance(LUCAS, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Gabriel"), 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        CONSOLE.propoe(new Lance(LUCAS, 10000.0));
        CONSOLE.propoe(new Lance(new Usuario("Gabriel"), 9000.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(10000.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance() {

        CONSOLE.propoe(new Lance(LUCAS, 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, DELTA);

    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {

        CONSOLE.propoe(new Lance(LUCAS, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Gabriel"), 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        CONSOLE.propoe(new Lance(LUCAS, 10000.0));
        CONSOLE.propoe(new Lance(new Usuario("Gabriel"), 9000.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(9000.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeTresLancesExatos() {
        CONSOLE.propoe(new Lance(LUCAS, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Gabriel"), 300.0));
        CONSOLE.propoe(new Lance(LUCAS, 400.0));

        List<Lance> tresMaioresLancesDevolvido = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvido.size());
        assertEquals(400.0, tresMaioresLancesDevolvido.get(0).getValor(), DELTA);

        assertEquals(300.0, tresMaioresLancesDevolvido.get(1).getValor(), DELTA);

        assertEquals(200.0, tresMaioresLancesDevolvido.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances() {
        List<Lance> tresMaioresLancesDevolvido = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvido.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(LUCAS, 250.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(250.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLances() {
        CONSOLE.propoe(new Lance(LUCAS, 400.0));
        CONSOLE.propoe(new Lance(new Usuario("Gabriel"), 350.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());

        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(350.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }
}