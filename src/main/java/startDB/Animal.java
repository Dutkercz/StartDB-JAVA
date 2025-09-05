package startDB;

import java.util.List;

public class Animal {

    private String nome;
    private String tipo;
    private List<String> brinquedosFavoritos;

    public Animal(String nome, String tipo, List<String> brinquedosFavoritos) {
        this.nome = nome;
        this.tipo = tipo;
        this.brinquedosFavoritos = brinquedosFavoritos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getBrinquedosFavoritos() {
        return brinquedosFavoritos;
    }

    public void setBrinquedosFavoritos(List<String> brinquedosFavoritos) {
        this.brinquedosFavoritos = brinquedosFavoritos;
    }
}
