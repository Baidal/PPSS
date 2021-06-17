package ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMultipathExample {

    private MultipathExample multipathExample;

    @BeforeEach
    public void setUp(){
        multipathExample = new MultipathExample();
    }

    /**
     * MP = multipath
     */

    @Test
    public void testC1MP1(){
        int a,b,c,esperado, obtenido;

        a = 6; b = 6; c = 2;
        esperado = 14;

        obtenido= multipathExample.multiPath1(a,b,c);

        Assertions.assertEquals(esperado,obtenido);
    }

    @Test
    public void testC2MP1(){
        int a,b,c,esperado, obtenido;

        a = 3; b = 3; c = 2;
        esperado = 2;

        obtenido= multipathExample.multiPath1(a,b,c);

        Assertions.assertEquals(esperado,obtenido);
    }

    @Test
    public void testC3MP2(){
        int a,b,c,esperado, obtenido;

        a = 6; b = 3; c = 6;
        esperado = 15;

        obtenido= multipathExample.multiPath2(a,b,c);

        Assertions.assertEquals(esperado,obtenido);
    }

    @Test
    public void testC4MP2(){
        int a,b,c,esperado, obtenido;

        a = 3; b = 6; c = 3;
        esperado = 3;

        obtenido= multipathExample.multiPath2(a,b,c);

        Assertions.assertEquals(esperado,obtenido);
    }

    @Test
    public void testC5MP2(){
        int a,b,c,esperado, obtenido;

        a = 6; b = 6; c = 3;
        esperado = 3;

        obtenido= multipathExample.multiPath2(a,b,c);

        Assertions.assertEquals(esperado,obtenido);
    }

    @Test
    public void testC6MP3(){
        int a,b,c,esperado, obtenido;

        a = 6; b = 3; c = 6;
        esperado = 15;

        obtenido= multipathExample.multiPath3(a,b,c);

        Assertions.assertEquals(esperado,obtenido);
    }

    @Test
    public void testC7MP3(){
        int a,b,c,esperado, obtenido;

        a = 6; b = 6; c = 3;
        esperado = 3;

        obtenido= multipathExample.multiPath3(a,b,c);

        Assertions.assertEquals(esperado,obtenido);
    }

    @Test
    public void testC8MP3(){
        int a,b,c,esperado, obtenido;

        a = 3; b = 4; c = 6;
        esperado = 10;

        obtenido= multipathExample.multiPath3(a,b,c);

        Assertions.assertEquals(esperado,obtenido);
    }

    @Test
    public void testC9MP3(){
        int a,b,c,esperado, obtenido;

        a = 6; b = 6; c = 6;
        esperado = 12;

        obtenido= multipathExample.multiPath3(a,b,c);

        Assertions.assertEquals(esperado,obtenido);
    }
}
