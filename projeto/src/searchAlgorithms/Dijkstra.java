package searchAlgorithms;

import java.security.InvalidParameterException;
import java.util.*;

public class Dijkstra {

    private int vertices[][];

    public Dijkstra(final int numVertices) {
        vertices = new int[numVertices][numVertices];
    }

    public void criarAresta(final int noOrigem, final int noDestino, final int peso) {
        // Dijkstra só funciona com pesos positivos
        if (peso >= 1) {
            vertices[noOrigem][noDestino] = peso;
            vertices[noDestino][noOrigem] = peso;
        } else {
            throw new InvalidParameterException(
                "O peso do nó origem [" + noOrigem + "] para o nó destino [" + noDestino + "] não pode ser negativo"
            );
        }
    }

    public int getMaisProximo(final int listaCustos[], final Set<Integer> naoVisitados) {
        int minDistancia = Integer.MAX_VALUE;
        int noProximo = -1;

        for (Integer i : naoVisitados) {
            if (listaCustos[i] < minDistancia) {
                minDistancia = listaCustos[i];
                noProximo = i;
            }
        }

        return noProximo;
    }

    public List<Integer> getVizinhos(final int no) {
        List<Integer> vizinhos = new ArrayList<>();
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[no][i] > 0) {
                vizinhos.add(i);
            }
        }
        return vizinhos;
    }

    public int getCusto(final int noOrigem, final int noDestino) {
        return vertices[noOrigem][noDestino];
    }

    public List<Integer> caminhoMinimo(final int noOrigem, final int noDestino) {
        int custo[] = new int[vertices.length];
        int antecessor[] = new int[vertices.length];
        Set<Integer> naoVisitados = new HashSet<>();

        Arrays.fill(custo, Integer.MAX_VALUE);
        custo[noOrigem] = 0;
        
        Arrays.fill(antecessor, -1);

        for (int v = 0; v < vertices.length; v++) {
            naoVisitados.add(v);
        }

        while (!naoVisitados.isEmpty()) {
            int noMaisProximo = getMaisProximo(custo, naoVisitados);
            if (noMaisProximo == -1) break; // Evita loop infinito

            naoVisitados.remove(noMaisProximo);

            for (Integer vizinho : getVizinhos(noMaisProximo)) {
                int custoTotal = custo[noMaisProximo] + getCusto(noMaisProximo, vizinho);

                if (custoTotal < custo[vizinho]) {
                    custo[vizinho] = custoTotal;
                    antecessor[vizinho] = noMaisProximo;
                }
            }

            if (noMaisProximo == noDestino) {
                return caminhoMaisProximo(antecessor, noDestino);
            }
        }
        
        return Collections.emptyList(); // Retorna lista vazia caso não haja caminho
    }

    public List<Integer> caminhoMaisProximo(final int antecessor[], int noMaisProximo) {
        List<Integer> caminho = new ArrayList<>();
        while (noMaisProximo != -1) {
            caminho.add(noMaisProximo);
            noMaisProximo = antecessor[noMaisProximo];
        }
        Collections.reverse(caminho);
        return caminho;
    }

    public static void main(String[] args) {
        Dijkstra grafo = new Dijkstra(6);
        grafo.criarAresta(0, 1, 4);
        grafo.criarAresta(0, 2, 2);
        grafo.criarAresta(1, 2, 5);
        grafo.criarAresta(1, 3, 10);
        grafo.criarAresta(2, 4, 3);
        grafo.criarAresta(3, 5, 6);
        grafo.criarAresta(4, 3, 2);
        grafo.criarAresta(4, 5, 8);

        System.out.println("Caminho mínimo de 0 para 5: " + grafo.caminhoMinimo(0, 5));
    }
}