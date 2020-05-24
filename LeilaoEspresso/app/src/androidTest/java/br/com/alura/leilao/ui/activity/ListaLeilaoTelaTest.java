package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import br.com.alura.leilao.R;
import br.com.alura.leilao.api.retrofit.client.TesteWebClient;
import br.com.alura.leilao.formatter.FormatadorDeMoeda;
import br.com.alura.leilao.model.Leilao;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static br.com.alura.leilao.matchers.ViewMatcher.apareceLeilaoNaPosicao;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.fail;

public class ListaLeilaoTelaTest {

    private static final String ERRO_FALHA_LIMPEZA_DE_DADOS_DA_API = "Banco de dados não foi limpo";
    private static final String LEILÃO_NÃO_FOI_SALVO = "Leilão não foi salvo: ";

    @Rule
    public ActivityTestRule<ListaLeilaoActivity> activity = new ActivityTestRule<>(ListaLeilaoActivity.class, true, false);
    private final TesteWebClient webClient = new TesteWebClient();
    private FormatadorDeMoeda formatadorDeMoeda = new FormatadorDeMoeda();

    @Before
    public void setup() throws IOException {
        limpaBancoDeDadosDaApi();
    }

    @Test
    public void deve_AparecerUmLeilao_QuandoCarregarUmLeilaoNaApi() throws IOException {

        tentaSalvarLeilaoNaApi(new Leilao("Carro"));

        activity.launchActivity(new Intent());

        onView(allOf(withText("Carro"),
                withId(R.id.item_leilao_descricao)))
                .check(matches(isDisplayed()));

        String formatoEsperado = formatadorDeMoeda.formata(0.00);
        onView(allOf(withText(formatoEsperado),
                withId(R.id.item_leilao_maior_lance)))
                .check(matches(isDisplayed()));

    }

    @Test
    public void deve_AparecerDoisLeiloes_QuandoCarregarDoisLeiloesDaApi() throws IOException {

        tentaSalvarLeilaoNaApi(new Leilao("Carro"),
                new Leilao("Computador"));

        activity.launchActivity(new Intent());

        onView(withId(R.id.lista_leilao_recyclerview))
                .check(matches(apareceLeilaoNaPosicao(0, "Carro", 0.00)));
    }

    @After
    public void tearDown() throws IOException {
        limpaBancoDeDadosDaApi();
    }

    private void tentaSalvarLeilaoNaApi(Leilao... leiloes) throws IOException {

        for (Leilao leilao : leiloes) {
            Leilao leilaoSalvo = webClient.salva(leilao);
            if (leilaoSalvo == null) {
                fail(LEILÃO_NÃO_FOI_SALVO + leilao.getDescricao());
            }
        }
    }

    private void limpaBancoDeDadosDaApi() throws IOException {
        boolean bancoDeDadosNãoFoiLimpo = !webClient.limpaBancoDeDados();
        if (bancoDeDadosNãoFoiLimpo) {
            fail(ERRO_FALHA_LIMPEZA_DE_DADOS_DA_API);
        }
    }
}