package fuerback.checklist.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fuerback.checklist.R;
import fuerback.checklist.modelo.Atividade;

import static android.graphics.Color.rgb;

/**
 * Created by Usuario on 08/10/2016.
 */

public class AtividadeAdapter extends BaseAdapter {

    private final List<Atividade> atividades;
    private final Context context;

    public AtividadeAdapter(List<Atividade> atividade, Context context){
        this.atividades = atividade;
        this.context = context;
    }

    @Override
    public int getCount() {
        return atividades.size();
    }

    @Override
    public Object getItem(int position) {
        return atividades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return atividades.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Atividade atividade = atividades.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView campoAtividade = (TextView) view.findViewById(R.id.item_atividade);
        campoAtividade.setText(atividade.getNome());

        TextView campoDecricao = (TextView) view.findViewById(R.id.item_descricao);
        campoDecricao.setText(atividade.getDescricao());

        ImageView campoPrioridade = (ImageView) view.findViewById(R.id.item_prioridade);
        int prioridade = atividade.getPrioridade();
        switch (prioridade){
            case 0/*MUITO IMPORTANTE*/:
                campoPrioridade.setBackgroundColor(rgb(255,0,0));
                break;
            case 1/*IMPORTANTE*/:
                campoPrioridade.setBackgroundColor(rgb(255,128,0));
                break;
            case 2/*NORMAL*/:
                campoPrioridade.setBackgroundColor(rgb(0,255,0));
                break;

        }

        return view;
    }
}
