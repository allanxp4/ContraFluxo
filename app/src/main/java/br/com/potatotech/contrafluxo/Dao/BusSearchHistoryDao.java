package br.com.potatotech.contrafluxo.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.potatotech.contrafluxo.Model.BusSearchHistory;
import br.com.potatotech.contrafluxo.Model.BusSearchResult;
import br.com.potatotech.contrafluxo.Model.BusStop;

/**
 * Created by Allan on 28/09/2016.
 * searchdao
 */

public class BusSearchHistoryDao extends SQLiteOpenHelper {
    public BusSearchHistoryDao(Context context) {
        super(context, "busdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "CREATE TABLE bus_search_history (id INTEGER PRIMARY_KEY, denominacao TEXT NOT NULL, " +
                "letreiro TEXT NOT NULL, codigo_linha INTEGER NOT NULL);";
        db.execSQL(createDB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(BusSearchResult busSearchResult){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("denominacao", (busSearchResult.getSentido() == 1 ? busSearchResult.getDenominacaoTPTS() : busSearchResult.getDenominacaoTSTP()));
        dados.put("letreiro", busSearchResult.getLetreiro() + "-" + busSearchResult.getTipo());
        dados.put("codigo_linha", busSearchResult.getCodigoLinha());

        db.insert("bus_search_history", null, dados);
        db.close();
    }

    public List<BusSearchHistory> get(){
        SQLiteDatabase db = getWritableDatabase();

        List<BusSearchHistory> busSearchHistories = new ArrayList<BusSearchHistory>();
        Cursor c = db.rawQuery("SELECT * FROM bus_search_history", null);

        while(c.moveToNext()){
            BusSearchHistory busSearchHistory = new BusSearchHistory();
            busSearchHistory.setCodigoLinha(c.getInt(c.getColumnIndex("codigo_linha")));
            busSearchHistory.setDenominacao(c.getString(c.getColumnIndex("denominacao")));
            busSearchHistory.setLetreiro(c.getString(c.getColumnIndex("letreiro")));
            busSearchHistory.setId(c.getInt(c.getColumnIndex("id")));
            busSearchHistories.add(busSearchHistory);
        }
        c.close();
        return busSearchHistories;
    }
}
