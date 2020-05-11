package br.com.alura.leilao.ui.activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoActivityTest {


    @Mock
    private LeilaoWebClient client;
    @Mock
    private ListaLeilaoAdapter adapter;

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() {
        ListaLeilaoActivity activity = new ListaLeilaoActivity();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.sucesso(new ArrayList<Leilao>(Arrays.asList(
                        new Leilao("Computador"),
                        new Leilao("Carro"),
                        new Leilao("Console")
                )));
                return null;
            }
        }).when(client).todos(any(RespostaListener.class));

        activity.buscaLeiloes(adapter, client);

        verify(client).todos(any(RespostaListener.class));

        verify(adapter).atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Computador"),
                new Leilao("Carro"),
                new Leilao("Console")
        )));
    }

}