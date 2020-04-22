package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    // [nomedometodo][Estado do teste][resultado esperado]
    @Test
    public void getDescricaoRecebeDescricaoDevolveDescricao() {
        // criar cenário de teste
        Leilao console = new Leilao("Console");

        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorLanceQuandoRecebeApenasUmLanceDevolveMaiorLance() {

        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Lucas"), 200.0));

        double maiorLanceDevolvidoDoConsole = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvidoDoConsole, 0.0001);

    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance(){

        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Lucas"), 100.0));
        computador.propoe(new Lance(new Usuario("Gabriel"), 200.0));

        double maiorLanceDevolvidoDoComputador = computador.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvidoDoComputador, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance(){

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Santos"), 10000.0));
        carro.propoe(new Lance(new Usuario("Anderson"), 9000.0));

        double maiorLanceDevolvidoDoCarro = carro.getMaiorLance();

        assertEquals(10000.0, maiorLanceDevolvidoDoCarro, 0.0001);
    }
}