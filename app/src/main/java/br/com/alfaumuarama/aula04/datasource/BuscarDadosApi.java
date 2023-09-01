package br.com.alfaumuarama.aula04.datasource;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import br.com.alfaumuarama.aula04.models.Pokemon;
//quando esse thread acabar de realizar ele vai retornar uma lista
public class BuscarDadosApi extends AsyncTask<String, Void, ArrayList<Pokemon>> {
    @Override
    protected ArrayList<Pokemon> doInBackground(String... strings) {
        ArrayList<Pokemon> listaDados = new ArrayList<>();

        try{
            //capturando a primeira posicao do vetor de strings
            String link = strings[0];

            //criando uma URL valida, apartir do link
            URL url = new URL(link);

            //criando uma conexão a partir da URL
            URLConnection connection = url.openConnection();

            //salvando na memoria o retorno da API
            InputStream stream = connection.getInputStream();

            //pegando os dados de memoria e colocando num reader
            InputStreamReader inputStreamReader = new InputStreamReader(stream);

            //pegando os dados da reader e carregando no buffer que pode ser lido
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String dados = "";
            String linha;

            //executando o comando pra depois fazer a comparação
            //pega a linha, le ela e guarda na linha e quando chega no final ele retorna null
            //quando tiver null ele vai encerrar a execução
            //enquanto existir dados para ler no reader, salva o valor nos dados
            while ((linha = reader.readLine()) != null){
               // dados = dados + linha;
                dados += linha;
            }

            //transforma os dados de texto em Objeto JSON
            JSONObject json = new JSONObject(dados);

            //capturando o vetor de dentro do item RESULTS do JSON
            JSONArray lista = new JSONArray(json.getString("results"));

            for (int i = 0; i < lista.length(); i++) {
                //pega o item atual da lista e salva em ITEM
                JSONObject item = (JSONObject) lista.get(i);

                //mapeando os campos do JSON para a classe Pokemon
                Pokemon pokemon = new Pokemon();
                pokemon.nome = item.getString("name");
                pokemon.url = item.getString("url");

                listaDados.add(pokemon);
            }
        }catch (Exception ex){

        }

        return listaDados;
    }
}
