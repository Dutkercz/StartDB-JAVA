package startDB;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private String nome;
    private List<String> brinquedos;
    private List<Animal> animaisAdotados;

    public Pessoa() {
    }

    public Pessoa(String nome, List<String> brinquedos) {
        this.nome = nome;
        this.brinquedos = brinquedos;
        this.animaisAdotados = new ArrayList<>();
    }

    public boolean aptaPara(Animal animal) {
        if (animal.getNome().equals("loco")) {
            return this.animaisAdotados.size() > 0;
        }

        List<String> favoritos = animal.getBrinquedosFavoritos();
        int index = 0;
        for (String brinquedo : this.brinquedos) {
            if (brinquedo.equals(favoritos.get(index))) {
                index++;
            }
            if (index == favoritos.size()) break;
        }
        return index == favoritos.size();
    }

    public boolean possuiEspacoParaAdotar() {
        return animaisAdotados.size() < 3;
    }

    public void adotarAnimal(Animal animal){
        if (possuiEspacoParaAdotar()){
            animaisAdotados.add(animal);
        }
    }
    //GETTERS AND SETTERS



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getBrinquedos() {
        return brinquedos;
    }

    public void setBrinquedos(List<String> brinquedos) {
        this.brinquedos = brinquedos;
    }

    public List<Animal> getAnimaisAdotados() {
        return animaisAdotados;
    }

    public void setAnimaisAdotados(List<Animal> animaisAdotados) {
        this.animaisAdotados = animaisAdotados;
    }
}
