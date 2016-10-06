package fuerback.checklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fuerback.checklist.dao.AtividadeDAO;
import fuerback.checklist.modelo.Atividade;

public class ListaAtividadesActivity extends AppCompatActivity {

    private ListView listaAtividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAtividade = (ListView) findViewById(R.id.lista_atividade);

        listaAtividade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Atividade atividade = (Atividade) listaAtividade.getItemAtPosition(position);
                Intent intentTrocaTelaFormulario = new Intent(ListaAtividadesActivity.this, FormularioActivity.class);
                intentTrocaTelaFormulario.putExtra("atividade", atividade);
                startActivity(intentTrocaTelaFormulario);
            }
        });

        Button botao_criar = (Button) findViewById(R.id.botao_criar);
        botao_criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAtividadesActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaAtividade);
    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }

    private void carregaLista() {
        AtividadeDAO dao = new AtividadeDAO(this);
        List<Atividade> atividades = dao.getAtividades();
        dao.close();

        ArrayAdapter<Atividade> adapter = new ArrayAdapter<Atividade>(this, android.R.layout.simple_list_item_1, atividades);
        listaAtividade.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Atividade atividade = (Atividade) listaAtividade.getItemAtPosition(info.position);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AtividadeDAO dao = new AtividadeDAO(ListaAtividadesActivity.this);
                dao.deleta(atividade);
                dao.close();

                carregaLista();

                Toast.makeText(ListaAtividadesActivity.this, "Atividade " + atividade.getNome() + " deletada!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
