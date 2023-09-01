package br.com.alfaumuarama.aula04.models;

import br.com.alfaumuarama.aula04.Config;

public class Pokemon {

    public String nome;
    public String url;
    public int id() {
        if (url != null) {
            String codigo = url.replace(Config.linkServer, "").replace("/", "");
            return Integer.parseInt(codigo);
        }
        return 0;
    }
    public String imagem() {
        return "";
    }
}
