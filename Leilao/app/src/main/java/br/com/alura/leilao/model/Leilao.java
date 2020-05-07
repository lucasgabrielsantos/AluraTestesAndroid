package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;


    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public void propoe(Lance lance) {
        if (lanceNaoForValido(lance)) return;
        lances.add(lance);
        double valorLance = lance.getValor();
        if (defineMaiorEMenorLanceParaOPrimeiroLance(valorLance)) return;
        Collections.sort(lances);
        calculaMaiorLance(valorLance);
        calculaMenorLance(valorLance);
    }

    private boolean defineMaiorEMenorLanceParaOPrimeiroLance(double valorLance) {
        if (lances.size() == 1) {
            maiorLance = valorLance;
            menorLance = valorLance;
            return true;
        }
        return false;
    }

    private boolean lanceNaoForValido(Lance lance) {
        double valorLance = lance.getValor();
        if (LanceForMenorQueOUltimoLance(valorLance))
            throw new RuntimeException("Lance foi menor que maior lance");
        if (temLances()) {
            Usuario usuarioNovo = lance.getUsuario();
            if (UsuarioForOMesmoDoUltimoLance(usuarioNovo))
                throw new RuntimeException("Mesmo usuário do último lance");
            if (SeUsuarioDeuCincoLances(usuarioNovo))
                throw new RuntimeException("Usuário já deu cinco lances");
        }
        return false;
    }

    private boolean temLances() {
        return !lances.isEmpty();
    }

    private boolean SeUsuarioDeuCincoLances(Usuario usuarioNovo) {
        int lanceDoUsuario = 0;
        for (Lance l :
                lances) {
            Usuario usuarioExistente = l.getUsuario();
            if (usuarioExistente.equals(usuarioNovo)) {
                lanceDoUsuario++;
                if (lanceDoUsuario == 5) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean UsuarioForOMesmoDoUltimoLance(Usuario usuarioNovo) {
        Usuario ultimoUsuario = lances.get(0).getUsuario();
        if (usuarioNovo.equals(ultimoUsuario)) {
            return true;
        }
        return false;
    }

    private boolean LanceForMenorQueOUltimoLance(double valorLance) {
        if (maiorLance > valorLance) {
            return true;
        }
        return false;
    }

    private void calculaMenorLance(double valorLance) {
        if (valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> tresMaioresLances() {
        int quantidadeMaximaDeLances = lances.size();
        if (quantidadeMaximaDeLances > 3) {
            quantidadeMaximaDeLances = 3;
        }
        return lances.subList(0, quantidadeMaximaDeLances);
    }

    public int quantidadeLances() {
        return lances.size();
    }
}
