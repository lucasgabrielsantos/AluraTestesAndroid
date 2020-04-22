package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    /** Formas de Declarar o método de teste de unidade :
     *
     * [nomedometodo][Estado do teste][resultado esperado]
     * ou
     * [deve] [resultado esperado] [estado de teste] (mais intuitivo ainda)
     */

    /**
     * exemplo de teste abaixo
     */
    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {
        /** criar cenário de teste */
        Leilao console = new Leilao("Console");

        /** executar ação esperada */
        String descricaoDevolvida = console.getDescricao();

        /**testar resultado esperado */
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance() {

        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Lucas"), 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, 0.0001);

    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {

        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Lucas"), 100.0));
        computador.propoe(new Lance(new Usuario("Gabriel"), 200.0));

        double maiorLanceDevolvido = computador.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Santos"), 10000.0));
        carro.propoe(new Lance(new Usuario("Anderson"), 9000.0));

        double maiorLanceDevolvido = carro.getMaiorLance();

        assertEquals(10000.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance() {

        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Lucas"), 200.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, 0.0001);

    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {

        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Lucas"), 100.0));
        computador.propoe(new Lance(new Usuario("Gabriel"), 200.0));

        double menorLanceDevolvido = computador.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Santos"), 10000.0));
        carro.propoe(new Lance(new Usuario("Anderson"), 9000.0));

        double menorLanceDevolvido = carro.getMenorLance();

        assertEquals(9000.0, menorLanceDevolvido, 0.0001);
    }

}