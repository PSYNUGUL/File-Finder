//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: File Finder
// Files: ShallowFileIterator.java , DeepFileIterator.java, FilteredFileIterator.java,
//////////////// P07Tester.java
// Course: CS300 Spring 2020
//
// Author: Yeon Jae Cho
// Email: ycho226@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: none
// Partner Email: none
// Partner Lecturer's Name: none
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understood the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: none
// Online Sources: just piazza
//
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class implements unit test methods to check the correctness of ShallowFileIterator,
 * DeepFileIterator and FilteredFileIterator classes defined in the CS300 Spring 2020 - P07 File
 * Finder programming assignment.
 */
public class P07Tester {

  /**
   * This method checks for the correctness of ShallowFileIterator constructor.
   * 
   * @param file - specified file in main method
   * @return true if expectedResults matches the result String
   */
  public static boolean testShallowFileIterator(File file) {
    try {
      ShallowFileIterator file1 = new ShallowFileIterator(file);
      String expectedResults =
          "assignments, exam preparation, lecture notes, " + "reading notes, todo.txt, ";
      String result = "";
      while (file1.hasNext()) {
        result = result + file1.next().getName() + ", ";
      }
      if (!result.equals(expectedResults)) {
        return false;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
        return false;
      }
    return true;
  }

  /**
   * This method checks for correctness of DeepfileIterator constructor.
   * 
   * @param file - specified file in main method
   * @return true if expectedResults matches the result String
   */
  public static boolean testDeepFileIterator(File file) {
    File folder = file;
    try {
      folder = new File(folder.getPath() + File.separator + "assignments");
      DeepFileIterator file1 = new DeepFileIterator(folder);
      String result = "";
      while (file1.hasNext()) {
        result = result + file1.next().getName() + ", ";
      }
      String expectedResults = "P01, PiggyBank.java, P02, CalendarPrinter.java, P03, "
          + "ElasticBank.java, P04, ExceptionalPiggyBank.java, P05, ExtendedVersion, "
          + "WinterCarnival.java, WinterCarnival.java, P06, AlphabetTrain.java, ";
      if (!result.equals(expectedResults)) {
        return false;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
        return false;
      }
    return true;
  }

  /**
   * This method checks for correctness of FilteredFileIterator.
   * 
   * @param file - specified file in main method
   * @return true if expectedResults matches result String
   */
  public static boolean testFilteredFileIterator(File file) {
    try {
      FilteredFileIterator file1 = new FilteredFileIterator(file, ".java");
      String result = "";
      while (file1.hasNext()) {
        result = result + file1.next().getName() + ", ";
      }
      String expectedResults = "PiggyBank.java, CalendarPrinter.java, ElasticBank.java, "
          + "ExceptionalPiggyBank.java, WinterCarnival.java, WinterCarnival.java, "
          + "AlphabetTrain.java, codeSamples.java, ";
      if (!result.equals(expectedResults)) {
        System.out.println(result);
        return false;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
        return false;
      }
    return true;
  }

  public static void main(String[] args) {
    System.out
        .println("Testing ShallowFileIterator: " + testShallowFileIterator(new File("filesystem")));
    System.out.println("Testing ShallowFileIterator with emtpy file reference: "
        + testShallowFileIterator(new File("")));
    System.out.println("Testing DeepFileIterator: " + testDeepFileIterator(new File("filesystem")));
    System.out.println("Testing DeepFileIterator with empty file reference: "
        + testDeepFileIterator(new File("")));
    System.out.println(
        "Testing FilteredFileIterator: " + testFilteredFileIterator(new File("filesystem")));
    System.out.println("Testing FilteredFileIterator with empty file reference: "
        + testFilteredFileIterator(new File("")));
  }
}
