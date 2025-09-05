package startDB;

import java.util.List;
import java.util.Map;

public class Resultado {
    private String erro;
    private Map<String, String> lista;

    public Resultado() {
    }

    public Resultado(String erro, Map<String, String> lista) {
        this.erro = erro;
        this.lista = lista;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public Map<String, String> getLista() {
        return lista;
    }

    public void setLista(Map<String, String> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return erro != null ? "Erro " + erro : "Resultado " + lista;

    }
}
