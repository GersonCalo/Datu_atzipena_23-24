package dambi;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int aukera = 0;
        do {
            System.out.println();
            System.out.println("MENUAREN IZENBURUA");
            System.out.println("====================================");
            System.out.println("1.- Mendien zerrenda ikusi (taula formatuan)");
            System.out.println("2.- Mendirik altuena bistaratu");
            System.out.println("3.- Mendiak esportatu (Araba.csv, Bizkaia.csv, Gipuzkoa.csv)");
            System.out.println("4.- ...");
            System.out.println("5.- Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");
            aukera = in.nextInt();
            switch (aukera) {
                case 1:
                    met1(args);
                    break;
                case 2:
                    met2(args);
                    break;
                case 3:
                    met3(args);
                    break;

                case 5:
                    System.out.println("Eskerrik asko programa hau erabiltzeagatik.");
                    break;
                default:
                    System.out.println("Aukera okerra. Saiatu berriz.");
            }
        } while (aukera != 5);
        in.next();
    }

    public static void met1(String[] args) throws IOException {
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("Mendiak.csv"));
            String l;
            while ((l = inputStream.readLine()) != null) {
                String[] lerroa = l.split(";");
                for (int i = 0; i < lerroa.length; i++) {
                    System.out.printf("%15s", lerroa[i]);
                    if (i >= 2) {
                        System.out.println();
                    }
                }

            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    public static void met2(String[] args) throws IOException {
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("Mendiak.csv"));

            String altuenaIzena = "";
            int altuenaZenbakia = 0;

            String line;
            while ((line = inputStream.readLine()) != null) {
                String[] montes = line.split(";");

                try {
                    if (Integer.parseInt(montes[1]) > altuenaZenbakia) {
                        altuenaIzena = montes[0];
                        altuenaZenbakia = Integer.parseInt(montes[1]);
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            System.out.println("mendi altuna da " + altuenaIzena);

        } catch (FileNotFoundException e) {
            System.out.println("fitxategia ez da urkitu");

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    public static void met3(String[] args) throws IOException{
        BufferedReader inputStream = null;
        PrintWriter outputStream1 = null;
        PrintWriter outputStream2 = null;
        PrintWriter outputStream3= null;
        PrintWriter outputStream4= null;

        try{
            inputStream = new BufferedReader(new FileReader("Mendiak.csv"));
            outputStream1 = new PrintWriter(new FileWriter("Araba.csv"));
            outputStream2 = new PrintWriter(new FileWriter("Bizkaia.csv"));
            outputStream3 = new PrintWriter(new FileWriter("Nafarroa.csv"));
            outputStream4 = new PrintWriter(new FileWriter("Gipuzkoa.csv"));

            String l;
            while((l = inputStream.readLine())!=null){
                String[] mendiak = l.split(";");
                switch(mendiak[2]){
                    case "Gipuzkoa":
                        outputStream4.println(l);
                        break;
                    case "Araba":
                        outputStream1.println(l);
                        break;
                    case "Bizkaia":
                        outputStream2.println(l);
                        break;
                    case "Nafarroa":
                        outputStream3.println(l);
                        break;
                }
            }
        }catch(FileNotFoundException o){
            System.out.println("ez da fitxategia aurkitu.");
        }finally{
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream1 != null) {
                outputStream1.close();
            }
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (outputStream3 != null) {
                outputStream3.close();
            }
            if (outputStream4 != null) {
                outputStream4.close();
            }
        }

    }
}
