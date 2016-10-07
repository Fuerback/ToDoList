package fuerback.checklist;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import fuerback.checklist.modelo.Atividade;

/**
 * Created by Usuario on 27/09/2016.
 */

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoDescricao;
    private final Spinner campoprioridade;

    private Atividade atividade;

    FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoDescricao = (EditText) activity.findViewById(R.id.formulario_descricao);
        campoprioridade = (Spinner) activity.findViewById(R.id.formulario_prioridade);

        atividade = new Atividade();
    }

    public Atividade getAtividade() {
        atividade.setNome(campoNome.getText().toString());
        atividade.setDescricao(campoDescricao.getText().toString());
        atividade.setPrioridade(campoprioridade.getSelectedItemPosition());
        return atividade;
    }

    public void preencheFormulario(Atividade atividade) {
        campoNome.setText(atividade.getNome());
        campoDescricao.setText(atividade.getDescricao());
        campoprioridade.setSelection(atividade.getPrioridade());
        this.atividade = atividade;
    }
}
