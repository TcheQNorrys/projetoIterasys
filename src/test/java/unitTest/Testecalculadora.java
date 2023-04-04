package unitTest;

import br.com.iterasys.Calculadora;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testecalculadora {

    @Test
    public void testearearetangulo(){
        double base = 7;
        double altura = 5;
        double resultadoEsperado = 35;

        Double resultadoAtual = Calculadora.areaRetangulo(base, altura);

        assertEquals(resultadoEsperado, resultadoAtual);
    }
    // este é um teste de unidade data driven - direcionado por dados
    @ParameterizedTest
    @CsvSource(value = {
            "7,5,35",
            "10,5,50",
            "75,7, 525",
            "7,8,56",
            "-9,5,-45"
    }, delimiter = ',')
    public void testearearetanguloLendoLista(String txtNum1, String txtNum2, String resultadoEsperado){

        Double resultadoAtual = Calculadora.areaRetangulo(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));

        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "src/test/resources/csv/massaretangulo.csv")
    public void testearearetanguloLendoArquivo(String txtNum1, String txtNum2, String resultadoEsperado){

        Double resultadoAtual = Calculadora.areaRetangulo(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));

        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }

}
