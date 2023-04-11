package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.baza;



import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Adresa;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Glasac;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Politicar;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Stranka;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;


public class BazaPodataka {

    public static Boolean activeConnectionWithDatabase = false;

    private static BazaPodataka handler = null;

    public static synchronized Connection connectToDatabase() throws SQLException, IOException {

        Properties configuration = new Properties();
        configuration.load(new FileReader("dat/bazaPodataka.properties"));

        String databaseURL = configuration.getProperty("databaseURL");
        String databaseUsername = configuration.getProperty("databaseUsername");
        String databasePassword = configuration.getProperty("databasePassword");

        Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
        BazaPodataka.activeConnectionWithDatabase = true;
        return connection;
    }

    public static synchronized void disconnectFromDatabase(Connection connection) throws SQLException {
        connection.close();
        BazaPodataka.activeConnectionWithDatabase = false;
    }

    public static List<Glasac> getAllGlasacFromDatabase(Connection connection) throws SQLException, IOException {

        //Connection connection = connectToDatabase();

        List<Glasac> glasacList = new ArrayList<>();

        Statement sqlStatement = connection.createStatement();

        ResultSet glasacResultSet = sqlStatement.executeQuery("SELECT * FROM glasac");

        while(glasacResultSet.next()) {
            Glasac newGlasac = getGlasacFromResultSet(glasacResultSet);
            glasacList.add(newGlasac);
        }

        connection.close();

        return glasacList;
    }

    public static List<Politicar> getAllPoliticarFromDatabase(Connection connection) throws SQLException, IOException {

        //Connection connection = connectToDatabase();

        List<Politicar> politicarList = new ArrayList<>();

        Statement sqlStatement = connection.createStatement();

        ResultSet politicarResultSet = sqlStatement.executeQuery("SELECT * FROM politicar");

        while(politicarResultSet.next()) {
            Politicar newPoliticar = getPoliticarFromResultSet(politicarResultSet);
            politicarList.add(newPoliticar);
        }

        connection.close();

        return politicarList;
    }

    public static List<Stranka> getAllStrankaFromDatabase(Connection connection) throws SQLException, IOException {

        //Connection connection = connectToDatabase();

        List<Stranka> strankaList = new ArrayList<>();

        Statement sqlStatement = connection.createStatement();

        ResultSet strankaResultSet = sqlStatement.executeQuery("SELECT * FROM stranka");

        while(strankaResultSet.next()) {
            Stranka newStranka = getStrankaFromResultSet(strankaResultSet);
            strankaList.add(newStranka);
        }

        connection.close();

        return strankaList;
    }



    public static void insertNewGlasacToDatabase(Glasac glasac) throws SQLException, IOException {
        Connection connection = connectToDatabase();

        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO GLASAC ( IME, PREZIME,JMBAG, DATUM_RODJENJA,IZBORNO_MJESTO,GLASAO) VALUES(?, ?, ?, ?,?,?)");

        stmt.setString(1, glasac.getIme());
        stmt.setString(2, glasac.getPrezime());
        stmt.setString(3, glasac.getJmbag());
        stmt.setDate(4, Date.valueOf(glasac.getDatumRodenja()));
        stmt.setString(5, glasac.getIzbornoMjesto());
        stmt.setBoolean(6, glasac.getGlasao());

        stmt.executeUpdate();

        connection.close();
    }

    public static void insertNewStrankaToDatabase(Stranka stranka) throws SQLException, IOException {
        Connection connection = connectToDatabase();

        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO STRANKA ( IME, ADRESA) VALUES(?, ?)");

        stmt.setString(1, stranka.getNaziv());
        stmt.setString(2, stranka.getAdresa().toString());

        stmt.executeUpdate();

        connection.close();
    }

    public static BazaPodataka getInstance() {
        if (handler == null) {
            handler = new BazaPodataka();
        }
        return handler;
    }

    public static void insertNewPoliticarToDatabase(Politicar politicar) throws SQLException, IOException {
        Connection connection = connectToDatabase();

        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO POLITICAR ( IME,PREZIME,SUMA_GLASOVA) VALUES(?, ?, ?)");

        stmt.setString(1, politicar.getIme());
        stmt.setString(2, politicar.getPrezime());
        stmt.setInt(3,politicar.getSumaGlasova());

        stmt.executeUpdate();

        connection.close();
    }

    public static void deleteGlasac(Glasac glasacToDelete) throws SQLException, IOException {
        Connection connection = connectToDatabase();

        PreparedStatement deleteStudent =
                connection.prepareStatement(
                        "DELETE FROM GLASAC WHERE JMBAG = ?");

        deleteStudent.setString(1, glasacToDelete.getJmbag());

        deleteStudent.executeUpdate();

        connection.close();
    }
/*
    public static void insertNewProfesorToDatabase(Profesor profesor) throws SQLException, IOException {
        Connection connection = connectToDatabase();

        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO PROFESOR (SIFRA, IME, PREZIME, TITULA) VALUES(?, ?, ?, ?)");

        stmt.setString(1, profesor.getSifra());
        stmt.setString(2, profesor.getIme());
        stmt.setString(3, profesor.getPrezime());
        stmt.setString(4, profesor.getTitula());

        stmt.executeUpdate();

        connection.close();
    }


    public static void updateStudentFirstName(Student studentToUpdate) throws SQLException, IOException {
        Connection connection = connectToDatabase();

        PreparedStatement updateStudenti =
                connection.prepareStatement(
                        "UPDATE STUDENTI SET IME = ? WHERE JMBAG = ?");

        updateStudenti.setString(1, studentToUpdate.getIme());
        updateStudenti.setString(2, studentToUpdate.getJmbag());

        updateStudenti.executeUpdate();

        connection.close();
    }

    public static void deleteStudent(Student studentToDelete) throws SQLException, IOException {
        Connection connection = connectToDatabase();

        PreparedStatement deleteStudent =
                connection.prepareStatement(
                        "DELETE FROM STUDENTI WHERE JMBAG = ?");

        deleteStudent.setString(1, studentToDelete.getJmbag());

        deleteStudent.executeUpdate();

        connection.close();
    }

    public static List<Student> filterStudentsByCriteria(Student criteria) throws SQLException, IOException  {

        Connection connection = connectToDatabase();

        List<Student> filteredStudentList = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM studenti WHERE 1=1");

        if(criteria.getJmbag().matches("[0-9]{10,20}") && criteria.getJmbag().isEmpty() == false) {
            sql.append(" AND JMBAG = " + criteria.getJmbag());
        }

        PreparedStatement statement = connection.prepareStatement(sql.toString());

        ResultSet studentsResultSet = statement.executeQuery();

        while(studentsResultSet.next()) {
            Student newStudent = getStudentFromResultSet(studentsResultSet);
            filteredStudentList.add(newStudent);
        }

        connection.close();

        return filteredStudentList;
    }
*/
    private static Glasac getGlasacFromResultSet(ResultSet glasacResultSet) throws SQLException {
        Long glasacId = glasacResultSet.getLong("ID");
        String jmbag = glasacResultSet.getString("JMBAG");
        String firstName = glasacResultSet.getString("IME");
        String lastName = glasacResultSet.getString("PREZIME");
        LocalDate dateOfBirth = glasacResultSet.getDate("DATUM_RODJENJA").toLocalDate();
        String izbornoMjesto = glasacResultSet.getString("IZBORNO_MJESTO");
        Boolean glasao = glasacResultSet.getBoolean("GLASAO");

        return new Glasac(glasacId, firstName, lastName,jmbag, dateOfBirth,izbornoMjesto,glasao);
    }

    private static Politicar getPoliticarFromResultSet(ResultSet politicarResultSet) throws SQLException {
        Long politicarId = politicarResultSet.getLong("ID");
        String ime = politicarResultSet.getString("IME");
        String prezime = politicarResultSet.getString("PREZIME");
        Integer sumaGlasova = politicarResultSet.getInt("SUMA_GLASOVA");

        return new Politicar(politicarId,ime,prezime,sumaGlasova);
    }

    private static Stranka getStrankaFromResultSet(ResultSet strankaResultSet) throws SQLException {
        Long strankaId = strankaResultSet.getLong("ID");
        String naziv = strankaResultSet.getString("IME");
        String adresaString = strankaResultSet.getString("ADRESA");

        Adresa adresa = new Adresa(adresaString);

        return new Stranka(strankaId,naziv ,adresa);
    }

}
