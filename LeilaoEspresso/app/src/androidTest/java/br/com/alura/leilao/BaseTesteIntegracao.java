package br.com.alura.leilao;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import java.io.IOException;

import br.com.alura.leilao.api.retrofit.client.TesteWebClient;
import br.com.alura.leilao.model.Leilao;

import static org.junit.Assert.fail;

public abstract class BaseTesteIntegracao {

    private static final String ERRO_FALHA_LIMPEZA_DE_DADOS_DA_API = "Banco de dados não foi limpo";
    private static final String LEILÃO_NÃO_FOI_SALVO = "Leilão não foi salvo: ";

    private final TesteWebClient webClient = new TesteWebClient();

    protected void limpaBancoDeDadosDaApi() throws IOException {
        boolean bancoDeDadosNãoFoiLimpo = !webClient.limpaBancoDeDados();
        if (bancoDeDadosNãoFoiLimpo) {
            fail(ERRO_FALHA_LIMPEZA_DE_DADOS_DA_API);
        }
    }

    protected void tentaSalvarLeilaoNaApi(Leilao... leiloes) throws IOException {
        for (Leilao leilao : leiloes) {
            Leilao leilaoSalvo = webClient.salva(leilao);
            if (leilaoSalvo == null) {
                fail(LEILÃO_NÃO_FOI_SALVO + leilao.getDescricao());
            }
        }
    }

    protected void limpaBancoDeDadosInterno() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        appContext.deleteDatabase(BuildConfig.BANCO_DE_DADOS);
    }
}
