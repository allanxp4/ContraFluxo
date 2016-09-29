package br.com.potatotech.contrafluxo.Model;

/**
 * Created by Allan on 28/09/2016.
 */

public class BusSearchHistory {
    private String Denominacao;
    private String Letreiro;
    private int CodigoLinha;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenominacao() {
        return Denominacao;
    }

    public void setDenominacao(String nome) {
        Denominacao = nome;
    }

    public String getLetreiro() {
        return Letreiro;
    }

    public void setLetreiro(String letreiro) {
        Letreiro = letreiro;
    }

    public int getCodigoLinha() {
        return CodigoLinha;
    }

    public void setCodigoLinha(int codigoLinha) {
        CodigoLinha = codigoLinha;
    }
}
