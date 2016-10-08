package fuerback.checklist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fuerback.checklist.modelo.Atividade;

/**
 * Created by Usuario on 27/09/2016.
 */

public class AtividadeDAO extends SQLiteOpenHelper {

    public AtividadeDAO(Context context) {
        super(context, "CheckList", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE CheckList (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, descricao TEXT, prioridade REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insere(Atividade atividade) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getDadosAluno(atividade);
        db.insert("CheckList",null, dados);
    }

    private ContentValues getDadosAluno(Atividade atividade) {
        ContentValues dados = new ContentValues();
        dados.put("nome", atividade.getNome());
        dados.put("descricao", atividade.getDescricao());
        dados.put("prioridade", atividade.getPrioridade());
        return dados;
    }

    public List<Atividade> getAtividades() {
        String sql = "SELECT * FROM CheckList;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Atividade> atividades = new ArrayList<Atividade>();
        while(cursor.moveToNext()){
            Atividade atividade = new Atividade();
            atividade.setId(cursor.getLong(cursor.getColumnIndex("id")));
            atividade.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            atividade.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            atividade.setPrioridade(cursor.getInt(cursor.getColumnIndex("prioridade")));
            atividades.add(atividade);
        }
        ordenaListAtividadePorPrioridade(atividades);
        return atividades;
    }

    private void ordenaListAtividadePorPrioridade(List<Atividade> atividades) {
        List<Atividade> atividadesMuitoImportantes = new ArrayList<Atividade>();
        List<Atividade> atividadesImportantes = new ArrayList<Atividade>();
        List<Atividade> atividadesNormais = new ArrayList<Atividade>();

        for(int i = 0; i < atividades.size(); i++){
            Atividade atividade = atividades.get(i);
            if(atividade.getPrioridade() == 0)
                atividadesMuitoImportantes.add(atividade);
            else if(atividade.getPrioridade() == 1)
                atividadesImportantes.add(atividade);
            else if(atividade.getPrioridade() == 2)
                atividadesNormais.add(atividade);
        }

        atividades.clear();


        for(int i = 0; i < atividadesMuitoImportantes.size(); i++){
            atividades.add(atividadesMuitoImportantes.get(i));
        }
        for(int i = 0; i < atividadesImportantes.size(); i++){
            atividades.add(atividadesImportantes.get(i));
        }
        for(int i = 0; i < atividadesNormais.size(); i++){
            atividades.add(atividadesNormais.get(i));
        }

    }

    public void deleta(Atividade atividade) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {atividade.getId().toString()};
        db.delete("CheckList", "id = ?", params);
    }

    public void altera(Atividade atividade) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues dados = getDadosAluno(atividade);
        String[] params = {atividade.getId().toString()};
        db.update("CheckList", dados, "id = ?", params);
    }
}
