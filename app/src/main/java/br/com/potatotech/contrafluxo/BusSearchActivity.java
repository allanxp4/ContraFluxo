package br.com.potatotech.contrafluxo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import br.com.potatotech.contrafluxo.Adapter.BusSearchResultsAdapter;
import br.com.potatotech.contrafluxo.Client.BusClient;
import br.com.potatotech.contrafluxo.Dao.BusSearchHistoryDao;
import br.com.potatotech.contrafluxo.Model.BusSearchResult;

public class BusSearchActivity extends AppCompatActivity {

    private BusClient client;
    private ListView listViewBusList;
    private EditText busSearchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_search);

        client = new BusClient(BusSearchActivity.this);

        listViewBusList = (ListView)findViewById(R.id.bus_list);

        listViewBusList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusSearchResult result = (BusSearchResult) parent.getItemAtPosition(position);
                //Toast.makeText(BusSearchActivity.this, result.getDenominacaoTPTS(), Toast.LENGTH_LONG).show();
                BusSearchHistoryDao busSearchHistoryDao= new BusSearchHistoryDao(BusSearchActivity.this);
                busSearchHistoryDao.insert(result);
                busSearchHistoryDao.close();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("BUS_LINE_CODIGO", result.getCodigoLinha());
                resultIntent.putExtra("BUS_SEARCH_RESULT", result);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        busSearchField = (EditText)findViewById(R.id.bus_search_text);
        busSearchField.addTextChangedListener(searchWatcher);
    }


    private final TextWatcher searchWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() >= 3){
                client.searchBus(busSearchField.getText().toString(), new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e == null){
                            BusSearchResult[] search = new Gson().fromJson(result, BusSearchResult[].class);
                            List<BusSearchResult> busList = Arrays.asList(search);
                            BusSearchResultsAdapter adapter = new BusSearchResultsAdapter(BusSearchActivity.this, busList);
                            listViewBusList.setAdapter(adapter);
                        }
                        else{
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    };
}
