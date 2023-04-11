package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;


import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String USER_FILE_NAME = "dat/user.txt";

    private static final String PROMJENE_FILE_NAME = "dat/promjene.txt";

    private static final int BROJ_GLASACA = 6;
    private static final int BROJ_STRANAKA = 3;

    private static final int BROJ_ZAPISA_DAT_STRANKA = 4;

    private static final int BROJ_ZAPISA_DAT_GLASACI = 7;

    private static final int BROJ_ZAPISA_DAT_POLITICARI = 4;

    private static final String SERIALIZED_PROMJENE_FILE_NAME="dat/serializedPromjene.dat";

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Example log from {}", Main.class.getSimpleName());
        Scanner unos = new Scanner(System.in);

        Integer broj;

        Boolean repeatInput = false;
        do {
            try {
                System.out.println("Enter number");
                broj = unos.nextInt();
                repeatInput = false;
            }catch (InputMismatchException ex){
                System.out.println(" enter numeric value");
                unos.nextLine();
                repeatInput = true;
                logger.error("Unesen krivi tip podatka -> InputMismatchException " + ex);
            }
        }while (repeatInput);

        List<Glasac> listaGlasaca = new ArrayList<>();
        List<Stranka> listaStranka = new ArrayList<>();
        List<Politicar> listaPoliticara = new ArrayList<>();
        List<User> listaUsera = new ArrayList<>();
        Map<Politicar, List<Stranka>> nositelji = new HashMap<>();

        readUserFromFile(listaUsera);



        try(BufferedReader bufReaderStranka = new BufferedReader(new FileReader(new File("dat/stranka.txt")));
        ){

            List<String> datotekaStranka = bufReaderStranka.lines().collect(Collectors.toList());

            for (int i = 0;i < datotekaStranka.size()/BROJ_ZAPISA_DAT_STRANKA;i++){
                String idStranka = datotekaStranka.get(i * BROJ_ZAPISA_DAT_STRANKA);
                String nazivStranke = datotekaStranka.get(i * BROJ_ZAPISA_DAT_STRANKA+1);
                String adresaString = datotekaStranka.get(i * BROJ_ZAPISA_DAT_STRANKA+2);
                Adresa adresa = new Adresa(adresaString);
                listaStranka.add(new Stranka(Long.parseLong(idStranka),
                        nazivStranke,
                        adresa));

            }

            datotekaStranka.forEach(System.out::println);

        } catch (FileNotFoundException e) {
            logger.error("d -> FileNotFoundException " + e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("d -> IOException " + e);
            throw new RuntimeException(e);
        }

        try(BufferedReader bufReaderGlasaci = new BufferedReader(new FileReader(new File("dat/glasaci.txt")));
        ){

            List<String> datotekaGlasaci = bufReaderGlasaci.lines().collect(Collectors.toList());

            for (int i = 0;i < datotekaGlasaci.size()/BROJ_ZAPISA_DAT_GLASACI;i++){
                String idGlasac = datotekaGlasaci.get(i * BROJ_ZAPISA_DAT_GLASACI);
                String ime = datotekaGlasaci.get(i * BROJ_ZAPISA_DAT_GLASACI+1);
                String prezime = datotekaGlasaci.get(i * BROJ_ZAPISA_DAT_GLASACI+2);
                String jmbag = datotekaGlasaci.get(i*BROJ_ZAPISA_DAT_GLASACI + 3);
                String datumRodenja = datotekaGlasaci.get(i * BROJ_ZAPISA_DAT_GLASACI + 4);
                String izbornoMjesto = datotekaGlasaci.get(i * BROJ_ZAPISA_DAT_GLASACI + 5);
                String glasao = datotekaGlasaci.get(i * BROJ_ZAPISA_DAT_GLASACI + 6);

                listaGlasaca.add(new Glasac(Long.parseLong(idGlasac),
                        ime,
                        prezime,
                        jmbag,
                        LocalDate.parse(datumRodenja),
                        izbornoMjesto,
                        Boolean.parseBoolean(glasao)));

            }

            datotekaGlasaci.forEach(System.out::println);

        } catch (FileNotFoundException e) {
            logger.error("d -> FileNotFoundException " + e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("d -> IOException " + e);
            throw new RuntimeException(e);
        }


        try(BufferedReader bufReaderPoliticari = new BufferedReader(new FileReader(new File("dat/politicari.txt")));
        ){

            List<String> datotekaPoliticari = bufReaderPoliticari.lines().collect(Collectors.toList());

            for (int i = 0;i < datotekaPoliticari.size()/BROJ_ZAPISA_DAT_POLITICARI;i++){
                String idPoliticar = datotekaPoliticari.get(i * BROJ_ZAPISA_DAT_POLITICARI);
                String ime = datotekaPoliticari.get(i * BROJ_ZAPISA_DAT_POLITICARI+1);
                String prezime = datotekaPoliticari.get(i * BROJ_ZAPISA_DAT_POLITICARI+2);
                String sumaGlasova = datotekaPoliticari.get(i*BROJ_ZAPISA_DAT_POLITICARI+3);

                listaPoliticara.add(new Politicar(Long.parseLong(idPoliticar),
                        ime,
                        prezime,
                        Integer.parseInt(sumaGlasova)));

            }


            datotekaPoliticari.forEach(System.out::println);

        } catch (FileNotFoundException e) {
            logger.error("d -> FileNotFoundException " + e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("d -> IOException " + e);
            throw new RuntimeException(e);
        }

        List<Promjene> promjeneIzFile = new ArrayList<>();

        readChangesFromFile(promjeneIzFile);

        serializePromjene(promjeneIzFile);

        deserializePromjene(SERIALIZED_PROMJENE_FILE_NAME);


    }

    private static void serializePromjene(List<Promjene> promjenes){

        File file = new File(Main.SERIALIZED_PROMJENE_FILE_NAME);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

            List<Promjene> serializedPromjene= promjenes.stream()
                    .filter(s -> s.getOsoba().isEmpty())
                    .collect(Collectors.toList());

            out.writeObject(serializedPromjene);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("d -> IOException " + e);
        }

    }

    private static void deserializePromjene(String fileName){

        File file = new File(fileName);

        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            List<Promjene> readPromjene = (List<Promjene>) in.readObject();

            System.out.println("\nDeserijalizirane promjene:");

            for (int i=0;i<readPromjene.size();i++){
                System.out.println(readPromjene.get(i).getOsoba());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("d -> IOException " + e);
        }
    }

    public static void readUserFromFile(List<User> users) {

        try (FileReader fileReader = new FileReader(Main.USER_FILE_NAME);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line;

            while ((line = reader.readLine()) != null) {
                long id = Long.parseLong(line);
                String username = reader.readLine();
                String password = reader.readLine();
                String rolaString = reader.readLine();


                Integer rolaInteger = Integer.parseInt(rolaString);
                Role rolaTipRola = Role.PRAZNO;
                switch(rolaInteger){
                    case 1:
                        rolaTipRola = Role.ADMIN;
                        break;
                    case 2:
                        rolaTipRola = Role.USER;
                        break;
                    case 3:
                        rolaTipRola = Role.MODERATOR;
                        break;
                }

                User user = new User(id,username,password,rolaTipRola);
                users.add(user);

            }
        } catch (IOException e) {
            System.out.println("File " + Main.USER_FILE_NAME + " not found.");
            logger.error(e.getMessage(), e);
        }


    }

    private static void readChangesFromFile(List<Promjene> promjene) {

        try (FileReader fileReader = new FileReader(Main.PROMJENE_FILE_NAME);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line;

            while ((line = reader.readLine()) != null) {
                long id = Long.parseLong(line);
                String staraVrijednost = reader.readLine();
                String novaVrijednost = reader.readLine();
                String rolaString = reader.readLine();
                String datum = reader.readLine();


                /*Integer rolaInteger = Integer.parseInt(rolaString);
                Role rolaTipRola = Role.PRAZNO;
                switch(rolaInteger){
                    case 1:
                        rolaTipRola = Role.ADMIN;
                        break;
                    case 2:
                        rolaTipRola = Role.USER;
                        break;
                    case 3:
                        rolaTipRola = Role.MODERATOR;
                        break;
                }*/

                Promjene promjena = new Promjene(id,staraVrijednost,novaVrijednost,rolaString,datum);
                promjene.add(promjena);

            }
        } catch (IOException e) {
            System.out.println("File " + Main.USER_FILE_NAME + " not found.");
            logger.error(e.getMessage(), e);
        }


    }

    public static Glasac unosGlasaca(Scanner unos, int i){
        System.out.println("Unesite " + (i+1) + ". glasaca:");

        System.out.println("Unesite ime glasaca:");
        String ime = unos.nextLine();

        System.out.println("Unesite prezime glasaca:");
        String prezime = unos.nextLine();

        System.out.println("Unesite jmbag glasaca");
        String jmbag = unos.nextLine();

        System.out.println("Unesite datum rodenja u formatu (dd.MM.yyyy.):");
        String privremeniDatumRodenja = unos.nextLine();

        System.out.println("Unesite izborno mjesto: ");
        String izbornoMjesto = unos.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

        LocalDate datumRodenja = LocalDate.parse(privremeniDatumRodenja,formatter);

        Glasac glasac = new Glasac(1,ime,prezime,jmbag,datumRodenja,izbornoMjesto,false);
        return glasac;

    }


}
