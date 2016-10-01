package br.com.potatotech.contrafluxo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;

import java.util.Arrays;
import java.util.List;

import br.com.potatotech.contrafluxo.Adapter.BusHistoryAdapter;
import br.com.potatotech.contrafluxo.Client.BusClient;
import br.com.potatotech.contrafluxo.Dao.BusSearchHistoryDao;
import br.com.potatotech.contrafluxo.Model.BusSearchHistory;
import br.com.potatotech.contrafluxo.Model.BusSearchResult;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_BUS_SEARCH = 1;
    MapsFragment mapsFragment = new MapsFragment();
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private BusClient busClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        busClient = new BusClient(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.frame_map, mapsFragment);
        tx.commit();
    }

    @Override
    protected void onResume() {

        drawerLayout = (DrawerLayout)findViewById(R.id.activity_main);
        drawerList = (ListView)findViewById(R.id.right_drawer);

        BusHistoryAdapter busHistoryAdapter = new BusHistoryAdapter(this, new BusSearchHistoryDao(this).get());

        drawerList.setAdapter(busHistoryAdapter);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusSearchHistory bus = (BusSearchHistory)parent.getItemAtPosition(position);
                mapsFragment.updateMapBusLocations(bus);

            }
        });
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.map_search:
                startActivityForResult(new Intent(MainActivity.this, BusSearchActivity.class),  REQUEST_CODE_BUS_SEARCH);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case REQUEST_CODE_BUS_SEARCH:
                if(resultCode == RESULT_OK){
                    BusSearchResult busSearchResult = (BusSearchResult) data.getSerializableExtra("BUS_SEARCH_RESULT");
                    mapsFragment.updateMapBusLocations(busSearchResult);

                }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
