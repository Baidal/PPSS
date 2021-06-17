package ppss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataArrayTest {

    int[] coleccion, esperado;
    DataArray data;


    @Test
    void F1_delete(){
        esperado = new int[3];
        coleccion = new int[4];

        coleccion[0] = 1; coleccion[1] = 3; coleccion[2] = 5; coleccion[3] = 7;
        esperado[0] = 1; esperado[1] = 3; esperado[2] = 7;

        data = new DataArray(coleccion);




        assertDoesNotThrow(()->data.delete(5));
        assertArrayEquals(esperado, data.getColeccion());
        assertEquals(esperado.length,data.size(),"Fallo en la comparación de tamaños");

    }

    @Test
    void F2_delete(){
        esperado = new int[4];
        coleccion = new int[5];

        coleccion[0] = 1; coleccion[1] = 3; coleccion[2] = 3; coleccion[3] = 5; coleccion[4] = 7;
        esperado[0] = 1; esperado[1] = 3; esperado[2] = 5; esperado[3] = 7;

        data = new DataArray(coleccion);

        assertDoesNotThrow(()->data.delete(3));

        assertArrayEquals(esperado, data.getColeccion());
        assertEquals(esperado.length,data.size(),"Fallo en la comparación de tamaños");

    }

    @Test
    void F3_delete(){
        esperado = new int[9];
        coleccion = new int[10];

        for(int i = 0; i < 10; i++){
            coleccion[i] = i+1;
            if(i < 3 && i < 9)
                esperado[i] = i+1;
            else if(i >= 3 && i < 9)
                esperado[i] = i + 2;
        }

        data = new DataArray(coleccion);

        assertDoesNotThrow(()->data.delete(4));

        assertArrayEquals(esperado, data.getColeccion());
        assertEquals(esperado.length,data.size(),"Fallo en la comparación de tamaños");

    }

    @Test
    void F4_delete(){
        coleccion = new int[0];


        data = new DataArray(coleccion);

        DataException exception = assertThrows(DataException.class,() -> data.delete(8));
        assertEquals(exception.getMessage(),"No hay elementos en la colección");
    }
    @Test
    void F5_delete(){
        coleccion = new int[4];
        coleccion[0] = 1; coleccion[1] = 3; coleccion[2] = 5; coleccion[3] = 7;

        data = new DataArray(coleccion);

        DataException exception = assertThrows(DataException.class,() -> data.delete(-5));
        assertEquals(exception.getMessage(),"El valor a borrar debe ser > cero");
    }

    @Test
    void F6_delete(){
        coleccion = new int[0];

        data = new DataArray(coleccion);

        DataException exception = assertThrows(DataException.class,() -> data.delete(0));
        assertEquals(exception.getMessage(),"Colección vacía. Y el valor a borrar debe ser > cero");
    }

    @Test
    void F7_delete(){
        coleccion = new int[4];
        coleccion[0] = 1; coleccion[1] = 3;coleccion[2] = 5; coleccion[3] = 7;

        data = new DataArray(coleccion);

        DataException exception = assertThrows(DataException.class,() -> data.delete(8));
        assertEquals(exception.getMessage(),"Elemento no encontrado");
    }


}