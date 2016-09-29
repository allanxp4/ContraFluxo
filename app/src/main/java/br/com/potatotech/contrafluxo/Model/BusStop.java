package br.com.potatotech.contrafluxo.Model;

/**
 * Created by Allan on 26/09/2016.
 */

public class BusStop {
    private String CodigoParada;
    private String Nome;
    private String Endereco;
    private double Latitude;
    private double Longitude;

    public String getCodigoParada() {
        return CodigoParada;
    }

    public void setCodigoParada(String codigoParada) {
        CodigoParada = codigoParada;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
