package br.com.potatotech.contrafluxo.Model;

import java.io.Serializable;

/**
 * Created by Allan on 25/09/2016.
 *
 */

public class BusSearchResult implements Serializable {
    private int CodigoLinha;
    private boolean Circular;
    private String Letreiro;
    private int Tipo;
    private int Sentido;
    private String DenominacaoTPTS;
    private String DenominacaoTSTP;
    private String Informacoes;

    public int getCodigoLinha() {
        return CodigoLinha;
    }

    public void setCodigoLinha(int codigoLinha) {
        CodigoLinha = codigoLinha;
    }

    public boolean isCircular() {
        return Circular;
    }

    public void setCircular(boolean circular) {
        Circular = circular;
    }

    public String getLetreiro() {
        return Letreiro;
    }

    public void setLetreiro(String letreiro) {
        Letreiro = letreiro;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public int getSentido() {
        return Sentido;
    }

    public void setSentido(int sentido) {
        Sentido = sentido;
    }

    public String getDenominacaoTPTS() {
        return DenominacaoTPTS;
    }

    public void setDenominacaoTPTS(String denominacaoTPTS) {
        DenominacaoTPTS = denominacaoTPTS;
    }

    public String getDenominacaoTSTP() {
        return DenominacaoTSTP;
    }

    public void setDenominacaoTSTP(String denominacaoTSTP) {
        DenominacaoTSTP = denominacaoTSTP;
    }

    public String getInformacoes() {
        return Informacoes;
    }

    public void setInformacoes(String informacoes) {
        Informacoes = informacoes;
    }

    @Override
    public String toString() {
        return getLetreiro() + getTipo() + getDenominacaoTPTS();
    }
}

