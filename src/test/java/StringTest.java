import org.example.videoclub.services.StringService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StringTest {

    private static StringService stringService;

    @BeforeAll
    public static void before(){
        stringService = new StringService();
    }

    @Test
    public void testCamelCase(){

        String valor = "El señor de los anillos";
        String valorEsperado = "elSeñorDeLosAnillos";
        String valorCalculado = stringService.toCamelCase(valor);

        Assertions.assertEquals(valorEsperado, valorCalculado);
    }

    @Test
    public void testPascalCase(){

        String valor = "El señor de los anillos";
        String valorEsperado = "ElSeñorDeLosAnillos";
        String valorCalculado = stringService.toPascalCase(valor);

        Assertions.assertEquals(valorEsperado, valorCalculado);
    }

    @Test
    public void testSnakeCase(){

        String valor = "El señor de los anillos";
        String valorEsperado = "el_seor_de_los_anillos";
        String valorCalculado = stringService.toSnakeCase(valor);

        Assertions.assertEquals(valorEsperado, valorCalculado);
    }

    @Test
    public void testEliminarDiacriticos(){

        String valor = "Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ";
        String valorEsperado = "This is a funky String";
        String valorCalculado = stringService.eliminarDiacriticos(valor);

        Assertions.assertEquals(valorEsperado, valorCalculado);
    }

}
