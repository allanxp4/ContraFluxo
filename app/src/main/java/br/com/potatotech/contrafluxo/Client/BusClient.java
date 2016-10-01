package br.com.potatotech.contrafluxo.Client;

import android.content.Context;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.net.URLEncoder;


/**
 * Created by Allan on 25/09/2016.
 *
 */

public class BusClient {
    private final String url = "http://api.olhovivo.sptrans.com.br/v0";
    Context context;

    public BusClient(final Context context){
        this.context = context;
        Ion.with(context)
                .load(url + "/Login/Autenticar?token=cb0029fdf8bbaf74d3980551ca5d6d7ecdb8e9219d36105f749fa01bf337d359")
                .setBodyParameter("0", "0")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e == null){

                            //Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(context, "Erro ao se conectar", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void searchBus(String searchTerms, FutureCallback<Response<String>> callback){
        Ion.with(context)
                .load("GET", (url + "/Linha/Buscar?termosBusca=" + URLEncoder.encode(searchTerms)))
                .asString()
                .withResponse()
                .setCallback(callback);
    }

    public void getStops(int codigoLinha, FutureCallback<Response<String>> callback){
        Ion.with(context)
                .load("GET", url + "/Parada/BuscarParadasPorLinha?codigoLinha=" + codigoLinha)
                .asString()
                .withResponse()
                .setCallback(callback);
    }

    public void getBusPositions(int codigoLinha, FutureCallback<Response<String>> callback){
        Ion.with(context)
                .load("GET", url + "/Posicao?codigoLinha=" + codigoLinha)
                .asString()
                .withResponse()
                .setCallback(callback);

    }

    public void getBus(int codigoLinha, FutureCallback<String> callback){
        Ion.with(context)
                .load("GET", url + "/Linha/CarregarDetalhes?codigoLinha=" + codigoLinha)
                .asString()
                .setCallback(callback);
    }
}
