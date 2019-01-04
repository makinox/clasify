package main;

import java.io.*;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException {
    FileInputStream inputStream = null;
    Scanner sc = null;
    try {
      // System.out.println(System.getProperty("user.dir"));
      inputStream = new FileInputStream("src/data/data.txt");
      sc = new Scanner(inputStream, "UTF-8");
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        verifyState(line);
      }
      if (sc.ioException() != null) {
        throw sc.ioException();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        inputStream.close();
      }
      if (sc != null) {
        sc.close();
      }
    }

  }

  private static void verifyState(String line) throws IOException {
    new File("src/out").mkdirs();
    if (line.substring(0,2).equals("F1") || line.substring(0,2).equals("F2")){
      //System.out.println("--");
    } else {
      System.out.println(line);
      createCity(line.split(";|,")[1], line.split(";|,")[0].substring(2), line.split(";|,")[2]  );
      createId(line.split(";|,")[1] , line.split(";|,")[2]);
    }
  }

  private static void createCity(String city, String name, String id) throws IOException {
    new File("src/out/city").mkdirs();
    Writer writer = new BufferedWriter(new FileWriter("src/out/city/"+city+".txt", true));
    writer.append(name+", "+id);
    ((BufferedWriter) writer).newLine();
    writer.close();

  }

  private static void createId(String city, String id) throws IOException {
    new File("src/out/ids").mkdirs();
    Writer writer = new BufferedWriter(new FileWriter("src/out/ids/"+id+".txt", true));
    writer.append(city);
    ((BufferedWriter) writer).newLine();
    writer.close();
  }
}
