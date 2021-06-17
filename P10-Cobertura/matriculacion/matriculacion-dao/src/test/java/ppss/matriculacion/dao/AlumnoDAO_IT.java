package ppss.matriculacion.dao;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.*;
import ppss.matriculacion.to.AlumnoTO;

import java.time.LocalDate;

public class AlumnoDAO_IT {

    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;
    private IAlumnoDAO alumnoDAO;

    @BeforeEach
    public void setUp() throws Exception{
        String cadena_conexionDB = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";
        databaseTester = new MiJdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                cadena_conexionDB,
                "root",
                "ppss");
        connection = databaseTester.getConnection();

        alumnoDAO = new FactoriaDAO().getAlumnoDAO();
    }

    @Tag("Integracion-fase1")
    @Test
    public void testA1() throws Exception {


        //Inicializamos la base de datos con los datos iniciales
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();


        //Añadimos al alumno a la base de datos
        AlumnoTO alumno = new AlumnoTO();

        alumno.setNif("33333333C");
        alumno.setNombre("Elena Aguirre Juarez");
        alumno.setFechaNacimiento(LocalDate.of(1985,2,22));
        Assertions.assertDoesNotThrow(() -> alumnoDAO.addAlumno(alumno));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable,actualTable);

    }

    @Tag("Integracion-fase1")
    @Test
    public void testA2() throws Exception {
        //Inicializamos la base de datos con los datos iniciales
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //Añadimos al alumno a la base de datos
        AlumnoTO alumno = new AlumnoTO();

        alumno.setNif("11111111A");
        alumno.setNombre("Alfonso Ramirez Ruiz");
        alumno.setFechaNacimiento(LocalDate.of(1982,2,22));

        DAOException exception = Assertions.assertThrows(DAOException.class,()->alumnoDAO.addAlumno(alumno));

        Assertions.assertEquals("Error al conectar con BD",exception.getMessage());
    }

    @Tag("Integracion-fase1")
    @Test
    public void testA3() throws Exception {
        //Inicializamos la base de datos con los datos iniciales
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //Añadimos al alumno a la base de datos
        AlumnoTO alumno = new AlumnoTO();

        alumno.setNif("44444444A");
        alumno.setNombre(null);
        alumno.setFechaNacimiento(LocalDate.of(1982,2,22));

        DAOException exception = Assertions.assertThrows(DAOException.class,()->alumnoDAO.addAlumno(alumno));

        Assertions.assertEquals("Error al conectar con BD",exception.getMessage());

    }

    @Tag("Integracion-fase1")
    @Test
    public void testA4() throws Exception {
        //Añadimos al alumno a la base de datos
        AlumnoTO alumno = null;

        DAOException exception = Assertions.assertThrows(DAOException.class,()->alumnoDAO.addAlumno(alumno));

        Assertions.assertEquals("Alumno nulo",exception.getMessage());

    }

    @Tag("Integracion-fase1")
    @Test
    public void testA5() throws Exception {
        //Inicializamos la base de datos con los datos iniciales
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //Añadimos al alumno a la base de datos
        AlumnoTO alumno = new AlumnoTO();

        alumno.setNif(null);
        alumno.setNombre("Pedro Garcia Lopez");
        alumno.setFechaNacimiento(LocalDate.of(1982,2,22));

        DAOException exception = Assertions.assertThrows(DAOException.class,()->alumnoDAO.addAlumno(alumno));

        Assertions.assertEquals("Error al conectar con BD",exception.getMessage());

    }

    @Tag("Integracion-fase1")
    @Test
    public void testB1() throws Exception {
        //Inicializamos la base de datos con los datos iniciales
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        Assertions.assertDoesNotThrow(()->alumnoDAO.delAlumno("11111111A"));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable,actualTable);
    }

    @Tag("Integracion-fase1")
    @Test
    public void testB2() throws Exception {
        //Inicializamos la base de datos con los datos iniciales
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exception = Assertions.assertThrows(DAOException.class,()->alumnoDAO.delAlumno("33333333C"));

        Assertions.assertEquals("No se ha borrado ningun alumno",exception.getMessage());

    }
}
