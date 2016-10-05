package fuerback.checklist;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import fuerback.checklist.dao.AtividadeDAO;
import fuerback.checklist.modelo.Atividade;

/**
 * Created by Usuario on 26/09/2016.
 */

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(FormularioActivity.this);

        Intent intent = getIntent();
        Atividade atividade = (Atividade) intent.getSerializableExtra("atividade");
        if(atividade != null){
            helper.preencheFormulario(atividade);
        }

        Spinner spinner = (Spinner) findViewById(R.id.formulario_prioridade);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(FormularioActivity.this,
                R.array.Prioridade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button botao_salvar = (Button) findViewById(R.id.formulario_salvar);
        botao_salvar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Atividade atividade = helper.getAtividade();
                AtividadeDAO dao = new AtividadeDAO(FormularioActivity.this);

                dao.insere(atividade);
                Toast.makeText(FormularioActivity.this, "Atividade " + atividade.getNome() + " salva!", Toast.LENGTH_SHORT).show();

                dao.close();
                finish();
            }
        });

    }


}
