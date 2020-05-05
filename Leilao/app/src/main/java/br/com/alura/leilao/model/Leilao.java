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
        double valorLance = lance.getValor();
        if (maiorLance > valorLance) {
            return;
        }
        if (!lances.isEmpty()) {
            Usuario usuarioNovo = lance.getUsuario();
            Usuario ultimoUsuario = lances.get(0).getUsuario();
            if (usuarioNovo.equals(ultimoUsuario)) {
                return;
            }
            int lanceDoUsuario = 0;
            for (Lance l :
                    lances) {
                Usuario usuarioExistente = l.getUsuario();
                if (usuarioExistente.equals(usuarioNovo)) {
                    lanceDoUsuario++;
                    if (lanceDoUsuario == 5) {
                        return;
                    }
                }

            }
        }
        lances.add(lance);
        if (lances.size() == 1) {
            maiorLance = valorLance;
            menorLance = valorLance;
            return;
        }
        Collections.sort(lances);
        calculaMaiorLance(valorLance);
        calculaMenorLance(valorLance);
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
