package startDB;

import java.util.*;

public class Main {
    Map<String, Animal> animaisDisponiveis = new HashMap<>();
    {

        animaisDisponiveis.put("Rex", new Animal("Rex", "cão", Arrays.asList("RATO", "BOLA")));
        animaisDisponiveis.put("Mimi",new Animal("Mimi", "gato", Arrays.asList("BOLA", "LASER")));
        animaisDisponiveis.put("Fofo",new Animal("Fofo", "gato", Arrays.asList("BOLA", "RATO", "LASER")));
        animaisDisponiveis.put("Zero",new Animal("Zero", "gato", Arrays.asList("RATO", "BOLA")));
        animaisDisponiveis.put("Bola",new Animal("Bola", "cão", Arrays.asList("CAIXA", "NOVELO")));
        animaisDisponiveis.put("Bebe",new Animal("Bebe", "cão", Arrays.asList("LASER", "RATO", "BOLA")));
        animaisDisponiveis.put("Loco",new Animal("Loco", "jabuti", Arrays.asList("SKATE", "RATO")));
    }
    public static void main(String[] args) {
        Main app = new Main();

        // caso válido
        Resultado r1 = app.encontraPessoas("RATO,BOLA","RATO,NOVELO", "Rex,Fofo");
        System.out.println("Teste 1 => " + r1);

        // caso invalido
        Resultado r2 = app.encontraPessoas("CAIXA,RATO","RATO,BOLA", "Lulu");
        System.out.println("Teste 2 => " + r2);


    }
    public Resultado encontraPessoas(String brinquedosPessoa1, String brinquedosPessoa2, String ordemAnimais){
        List<String> listBrinquedos1 = Arrays.asList(brinquedosPessoa1.trim().toUpperCase().split(","));
        List<String> listBrinquedos2 = Arrays.asList(brinquedosPessoa2.trim().toUpperCase().split(","));
        List<String> nomesAnimais = Arrays.asList(ordemAnimais.trim().split(","));

        //Eliminar elementos repetidos, usando o HashSet, portanto se tiverem tamanos diferente
        //significa que haviam elementos duplicados
        if (new HashSet<>(listBrinquedos1).size() != listBrinquedos1.size() ||
                new HashSet<>(listBrinquedos2).size() != listBrinquedos2.size()) {
            return new Resultado("Brinquedo inválido", null);
        }

        // Aqui verificamos se o animal não existe na lista de disponíveis (animaisDisponiveis)
        // ou se já está duplicado no setAnimais
        var setAnimais = new HashSet<>();
        for (String nome: nomesAnimais){
            if (!animaisDisponiveis.containsKey(nome) || setAnimais.contains(nome)) {
                return new Resultado("Animal inválido", null);
            }
            setAnimais.add(nome);
        }
        var pessoa1 = new Pessoa("pessoa 1", listBrinquedos1);
        var pessoa2 = new Pessoa("pessoa 2", listBrinquedos2);

        Map<String, String> resultado = new HashMap<>();

        //fazemos uma lista de brinquedos que já foram usados por algum animal que a pessoa adotou
        // Regra 3 onde gatos não dividem briquedos
        Set<String> brinquedosReservadosPessoa1 = new HashSet<>();
        Set<String> brinquedosReservadosPessoa2 = new HashSet<>();

        for (String nomeAnimal : nomesAnimais){
            var animal = animaisDisponiveis.get(nomeAnimal);

            var apta1 = pessoa1.aptaPara(animal) && pessoa1.possuiEspacoParaAdotar();
            var apta2 = pessoa2.aptaPara(animal) && pessoa2.possuiEspacoParaAdotar();

            //aqui ja podemos passar para o proximo animal se as duas pessoas ja cairem na regra do gato
            //vamos ver se o brinquedos favoritos do gato ja estão na lista de usados por outro animal
            if (animal.getTipo().equalsIgnoreCase("gato")) {
                boolean conflito1 = animal.getBrinquedosFavoritos()
                        .stream()
                        .anyMatch(brinquedosReservadosPessoa1::contains);
                boolean conflito2 = animal.getBrinquedosFavoritos()
                        .stream()
                        .anyMatch(brinquedosReservadosPessoa2::contains);

                // Se houver conflito em uma pessoa, ela não pode adotar
                if (conflito1) apta1 = false;
                if (conflito2) apta2 = false;

                // Aqui vamos verificar se nenhum dos 2 OU os 2 podem adota, vai pro abrigo (tadinho)
                if ((!apta1 && !apta2) || (apta1 && apta2)) {
                    resultado.put(nomeAnimal, "Abrigo");
                    continue; // passa para o próximo animal
                }
            }

            if (apta1) {
                pessoa1.adotarAnimal(animal);
                resultado.put(nomeAnimal, pessoa1.getNome());
                brinquedosReservadosPessoa1.addAll(animal.getBrinquedosFavoritos());

            } else if (apta2) {
                pessoa2.adotarAnimal(animal);
                resultado.put(nomeAnimal, pessoa2.getNome());
                brinquedosReservadosPessoa2.addAll(animal.getBrinquedosFavoritos());

            }
        }
        return new Resultado(null, resultado);
    }
}