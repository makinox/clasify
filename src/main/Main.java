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

  public static void verifyState(String line) throws FileNotFoundException, UnsupportedEncodingException {
    new File("src/out").mkdirs();
    if (line.substring(0,2).equals("F1") || line.substring(0,2).equals("F2")){
      //System.out.println("--");
    } else {
      // System.out.println(line.split(";|,")[0].substring(2));
      createCity(line.split(";|,")[1], line.split(";|,")[0].substring(2), line.split(";|,")[2]  );
      createId(line.split(";|,")[1] , line.split(";|,")[2]);
    }
  }

  public static void createCity(String city, String name, String id) throws FileNotFoundException, UnsupportedEncodingException {
    new File("src/out/city").mkdirs();
    PrintWriter writer = new PrintWriter("src/out/city/"+city+".txt", "UTF-8");
    writer.println(name+", "+id);
    writer.close();

  }

  public static void createId(String city, String id) throws FileNotFoundException, UnsupportedEncodingException {
    new File("src/out/ids").mkdirs();
    PrintWriter writer = new PrintWriter("src/out/ids/"+id+".txt", "UTF-8");
    writer.println(city);
    writer.close();
  }
}
