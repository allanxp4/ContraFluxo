package br.com.potatotech.contrafluxo.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import br.com.potatotech.contrafluxo.Model.BusSearchHistory;
import br.com.potatotech.contrafluxo.Model.BusSearchResult;
import br.com.potatotech.contrafluxo.R;

/**
 * Created by Allan on 28/09/2016.
 */

public class BusHistoryAdapter extends BaseAdapter {
    private List<BusSearchHistory> busSearchHistoryList;
    private Context context;

    public BusHistoryAdapter(Context context, List<BusSearchHistory> busSearchHistories){
        this.context = context;
        this.busSearchHistoryList = busSearchHistories;
        Collections.reverse(this.busSearchHistoryList);
    }

    @Override
    public int getCount() {
        return busSearchHistoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return busSearchHistoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return busSearchHistoryList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BusSearchHistory busSearchHistory = busSearchHistoryList.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.list_sidebar, parent, false);
        }

        TextView textViewTop = (TextView)view.findViewById(R.id.sidebar_item_text_top);
        textViewTop.setText(busSearchHistory.getDenominacao());

        TextView textViewBottom = (TextView)view.findViewById(R.id.sidebar_item_text_bottom);
        textViewBottom.setText(busSearchHistory.getLetreiro());

        return view;
    }
}
