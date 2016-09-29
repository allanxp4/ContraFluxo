package br.com.potatotech.contrafluxo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.potatotech.contrafluxo.Model.BusSearchResult;
import br.com.potatotech.contrafluxo.R;

/**
 * Created by Allan on 25/09/2016.
 * adapter for bus view
 */

public class BusSearchResultsAdapter extends BaseAdapter {

    private List<BusSearchResult> results;
    private Context context;

    public BusSearchResultsAdapter(Context context, List<BusSearchResult> results){
        this.context = context;
        this.results = results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return results.get(position).getCodigoLinha();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BusSearchResult result = results.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.list_element_bus_search, parent, false);
        }

        TextView linha = (TextView)view.findViewById(R.id.bus_numero_linha);
        linha.setText(result.getLetreiro() + "-" + result.getTipo());

        TextView desc = (TextView)view.findViewById(R.id.bus_desc_linha);
        desc.setText(result.getSentido() == 1 ? result.getDenominacaoTPTS() : result.getDenominacaoTSTP());

        return view;
    }
}
