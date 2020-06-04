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
import java.util.NoSuchElementException;

/**
 * This class implements Iterator<T> with type of File for using the classes given in Iterator with
 * changed Type value.
 * 
 * This class is used to modify constructor and initialize the field values provided.
 */
public class FilteredFileIterator implements java.util.Iterator<File> {

  /**
   * DeepFileIterator that steps through all files within the initial directory/sub directories
   */
  private DeepFileIterator fileIterator;

  /**
   * Part of a file’s name in order for that file to be returned from this iterator’s next method
   */
  private String searchPattern;

  /**
   * File reference to the next file that this iterator will return
   */
  private File nextMatchingFile;

  /**
   * Initializes searchPattern to provided String, fileIterator to DeepFileIterator passed with
   * provided file, and nextMatchingFile to nextFile found from findNextFile() method.
   * 
   * @param file          - a folder that includes list of files inside
   * @param searchPattern - specified string of file extension
   * @throws FileNotFoundException - if file provided does not exist
   */
  public FilteredFileIterator(File file, String searchPattern) throws FileNotFoundException {
    if (!file.exists()) {
      throw new FileNotFoundException("File does not exist");
    }
    this.searchPattern = searchPattern;
    this.fileIterator = new DeepFileIterator(file);
    this.nextMatchingFile = findNextFile();
  }

  /**
   * Finds the nextFile according to conditions set.
   * 
   * @return nextFile - a file that is updated based on the value of fileIterator's next file
   *         reference
   */
  private File findNextFile() {
    File nextFile = null;
    while (fileIterator.hasNext()) {
      nextFile = fileIterator.next();
      if (nextFile.getName().contains(searchPattern)) {
        return nextFile;
      }
    }
    if (!fileIterator.hasNext()) {
      nextFile = null;
    }
    return nextFile;
  }

  /**
   * Overrides hasNext() method from Iterator<T> implementation.
   * 
   * @return true if file reference of nextMatchingFile is not null
   */
  @Override
  public boolean hasNext() {
    if (nextMatchingFile != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Overrides next() method from Iterator<T> implementation.
   * 
   * Updates currentFile according to conditions and nextMatchingFile.
   * 
   * @return currentFile - a file reference updated if hasNext() is true
   * @throws NoSuchElementException if fileIterator does not have next referenceF
   */
  @Override
  public File next() {
    if (hasNext()) {
      File currentFile = this.nextMatchingFile;
      this.nextMatchingFile = findNextFile();
      return currentFile;
    } else {
      throw new NoSuchElementException("fileIterator does not have next reference.");
    }
  }
}


