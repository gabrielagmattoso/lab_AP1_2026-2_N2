
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Os testes prontos servem de exemplo.
 * Complete os testes marcados com //TODO (Tarefas 4 e 5).
 */
public class AssinanteTest {

    private Assinante assinante;

    @BeforeEach
    void setUp() {
        assinante = new Assinante("Ana");
    }

    @Test
    void deveRegistrarAssistidoPorTitulo() {
        assinante.adicionar(new Episodio("Piloto", 1, 42));
        assertTrue(assinante.registrarAssistido("Piloto"));
        assertEquals(42, assinante.tempoTotalAssistido());
    }

    @Test
    void naoDeveRegistrarTituloInexistente() {
        assinante.adicionar(new Episodio("Piloto", 1, 42));
        assertFalse(assinante.registrarAssistido("Final"));
    }

    @Test
    void deveCalcularCreditoDeTempo() {
        assinante.adicionar(new Episodio("A", 1, 40));
        assinante.adicionar(new Episodio("B", 1, 50));
        assinante.registrarAssistido("A");
        assertEquals(50, assinante.creditoDeTempo());
    }

    @Test
    void resumoDeveConterNomeEClassificacao() {
        assinante.adicionar(new Episodio("Piloto", 1, 42));
        String r = assinante.resumo();
        assertTrue(r.contains("Ana"), r);
        assertTrue(r.contains("INICIANTE"), r);
    }

    @Test
    void deveClassificarEngajamento() {
        assinante.adicionar(new Episodio("A", 1, 40));
        assinante.adicionar(new Episodio("B", 1, 50));

        assinante.registrarAssistido("A");

        assertEquals(Engajamento.REGULAR, assinante.classificacaoEngajamento());

        assinante.registrarAssistido("B");

        assertEquals(Engajamento.BINGE, assinante.classificacaoEngajamento());
    }

    @Test
    void deveCalcularTarifaMensal() {
        assinante.adicionar(new Episodio("A", 1, 250)); 
        assinante.adicionar(new Episodio("B", 1, 50));

        assinante.registrarAssistido("A");

        assertEquals(assinante.TARIFA_BASE * Engajamento.BINGE.getFator() , assinante.tarifaMensal());

        assinante.adicionar(new Episodio("C", 1, 301)); 
        assinante.adicionar(new Episodio("D", 1, 50));

        assinante.registrarAssistido("B");
        assinante.registrarAssistido("C");

        assertEquals(0, assinante.tarifaMensal());



        //TODO Tarefa 5: testar tarifaMensal usando o fator da classificação
        // (ex.: BINGE sem isenção → 29,90 × 1,10) e a isenção acima de 600 minutos
    }
}
