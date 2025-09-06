package startDB;

import org.junit.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MainTest {

    @Test
    public void testeAdoçãoSimples() {
        Main main = new Main();
        Resultado resultado = main.encontraPessoas(
                "RATO,BOLA", // brinquedos pessoa1
                "BOLA,RATO", // brinquedos pessoa2
                "Rex"        // animal
        );
        Map<String, String> res = resultado.getLista();
        assertEquals("pessoa 1", res.get("Rex"), "Rex deve ser adotado pela pessoa 1");
    }

    @Test
    public void testeAbrigoPorAmbosAptos() {
        Main main = new Main();
        Resultado resultado = main.encontraPessoas(
                "RATO,BOLA",
                "RATO,BOLA",
                "Bebe"  // animal que ambas podem adotar
        );
        Map<String, String> res = resultado.getLista();
        assertEquals("Abrigo", res.get("Bebe"), "Bebe deve ir para o Abrigo pois ambos aptos");
    }

    @Test
    public void testeGatoNãoDivideBrinquedos() {
        Main main = new Main();
        Resultado resultado = main.encontraPessoas(
                "BOLA,LASER",
                "RATO,BOLA",
                "Mimi,Zero"
        );
        Map<String, String> res = resultado.getLista();
        assertEquals("pessoa 1", res.get("Mimi"));
        assertEquals("pessoa 2", res.get("Zero"));
    }

    @Test
    public void testeLocoSemOrdem() {
        Main main = new Main();
        Resultado resultado = main.encontraPessoas(
                "RATO,BOLA",
                "LASER,SKATE",
                "Rex,Loco"
        );
        Map<String, String> res = resultado.getLista();
        assertEquals("pessoa 1", res.get("Rex"));
        assertEquals("pessoa 1", res.get("Loco"));
    }

    @Test
    public void testeLimiteDeAnimais() {
        Main main = new Main();
        Resultado resultado = main.encontraPessoas(
                "RATO,BOLA,SKATE,LASER",
                "BOLA,RATO,LASER,CAIXA",
                "Rex,Bebe,Loco,Zero"
        );
        Map<String, String> res = resultado.getLista();
        int countPessoa1 = (int) res.values().stream().filter(v -> v.equals("pessoa 1")).count();
        int countPessoa2 = (int) res.values().stream().filter(v -> v.equals("pessoa 2")).count();
        assertTrue(countPessoa1 <= 3, "pessoa 1 não pode adotar mais de 3 animais");
        assertTrue(countPessoa2 <= 3, "pessoa 245 não pode adotar mais de 3 animais");
    }

    @Test
    public void testeAnimalInvalido() {
        Main main = new Main();
        Resultado resultado = main.encontraPessoas(
                "RATO,BOLA",
                "BOLA,RATO",
                "Rex,Fantasma"
        );
        assertEquals("Animal inválido", resultado.getErro());
    }

    @Test
    public void testeBrinquedoDuplicado() {
        Main main = new Main();
        Resultado resultado = main.encontraPessoas(
                "RATO,RATO",
                "BOLA,RATO",
                "Rex"
        );
        assertEquals("Brinquedo inválido", resultado.getErro());
    }
}
