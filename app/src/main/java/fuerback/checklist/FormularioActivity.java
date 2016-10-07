package fuerback.checklist;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import fuerback.checklist.dao.AtividadeDAO;
import fuerback.checklist.modelo.Atividade;

import static android.graphics.Color.rgb;

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

        final Spinner spinner = (Spinner) findViewById(R.id.formulario_prioridade);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(FormularioActivity.this,
                R.array.Prioridade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0/*MUITO IMPORTANTE*/:
                        spinner.setBackgroundColor(rgb(255,0,0));
                        break;
                    case 1/*IMPORTANTE*/:
                        spinner.setBackgroundColor(rgb(255,128,0));
                        break;
                    case 2/*NORMAL*/:
                        spinner.setBackgroundColor(rgb(0,255,0));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button botao_salvar = (Button) findViewById(R.id.formulario_salvar);
        botao_salvar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Atividade atividade = helper.getAtividade();
                AtividadeDAO dao = new AtividadeDAO(FormularioActivity.this);

                if(atividade.getId() != null){
                    dao.altera(atividade);
                    Toast.makeText(FormularioActivity.this, "Atividade " + atividade.getNome() + " editada!", Toast.LENGTH_SHORT).show();
                } else {
                    dao.insere(atividade);
                    Toast.makeText(FormularioActivity.this, "Atividade " + atividade.getNome() + " salva!", Toast.LENGTH_SHORT).show();
                }

                dao.close();
                finish();
            }
        });

    }
}
