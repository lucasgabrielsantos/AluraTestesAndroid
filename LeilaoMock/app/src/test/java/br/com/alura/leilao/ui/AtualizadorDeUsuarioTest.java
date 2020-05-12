package br.com.alura.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private RecyclerView recyclerView;

    @Test
    public void deve_AtualizarListaDeUsuarios_QuandoSalvarUsuario() {

        AtualizadorDeUsuario atualizador = new AtualizadorDeUsuario(
                dao,
                adapter,
                recyclerView);

        Usuario lucas = new Usuario("Lucas");
        when(dao.salva(lucas)).thenReturn(new Usuario(1, "Lucas"));
        when(adapter.getItemCount()).thenReturn(1);

        atualizador.salva(lucas);

        verify(dao).salva(new Usuario("Lucas"));
        verify(recyclerView).smoothScrollToPosition(0);

    }

}