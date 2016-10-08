package fuerback.checklist.modelo;

import java.io.Serializable;

/**
 * Created by Usuario on 26/09/2016.
 */

public class Atividade implements Serializable {

    private String nome;
    private int prioridade;
    private String descricao;
    private Long id;

    //----------------------------------------------------------------------------------------------


    public String getNome() {
        return nome;
    }

    //----------------------------------------------------------------------------------------------

    public void setNome(String nome) {
        this.nome = nome;
    }

    //----------------------------------------------------------------------------------------------

    public int getPrioridade() {
        return prioridade;
    }

    //----------------------------------------------------------------------------------------------

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    //----------------------------------------------------------------------------------------------

    public String getDescricao() {
        return descricao;
    }

    //----------------------------------------------------------------------------------------------

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //----------------------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    //----------------------------------------------------------------------------------------------

    public void setId(Long id) {
        this.id = id;
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return getPrioridadeIdentificador() + " - " + getNome();
    }

    //----------------------------------------------------------------------------------------------

    private String getPrioridadeIdentificador() {
        String identificador = "";
        switch(prioridade){
            case 0:
                identificador = "MI";
                break;
            case 1:
                identificador = "I";
                break;
            case 2:
                identificador = "N";
        }
        return identificador;
    }

    //----------------------------------------------------------------------------------------------
}
