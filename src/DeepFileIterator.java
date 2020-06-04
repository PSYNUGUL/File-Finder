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
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This class implements Iterator<T> with type of File for using the classes given in Iterator with
 * changed Type value.
 * 
 * This class is used to modify constructor and initialize the field values provided.
 */
public class DeepFileIterator implements java.util.Iterator<File> {

  /**
   * Sorted array of File references which this iterator steps through
   */
  private File[] folderContents;

  /**
   * int index specifying the next File within folderContents that this iterator’s next() method
   * will return
   */
  private int nextIndex;

  /**
   * DeepFileIterator reference that is used to step through the contents within any directory that
   * is contained within this folder
   */
  private DeepFileIterator contentsIterator;

  /**
   * Initializes contentsIterator to null and nextIndex to 0.
   * 
   * If folder exists, the files inside are sorted.
   * 
   * @param folderContents - a folder that includes list of files inside
   * @throws FileNotFoundException - if file provided does not exist
   */
  public DeepFileIterator(File folderContents) throws FileNotFoundException {
    contentsIterator = null;
    nextIndex = 0;
    if (!folderContents.exists()) {
      throw new FileNotFoundException("File is not found.");
    } else {
      this.folderContents = folderContents.listFiles();
      Arrays.sort(this.folderContents);
    }
  }

  /**
   * Overrides hasNext() method from Iterator<T> implementation.
   * 
   * Returns true if next() would return a file reference rather than throwing an exception.
   * 
   * @return true if contentsIterator is not null, has next reference value, and does not go beyond
   *         the number of files inside
   */
  @Override
  public boolean hasNext() {
    if (contentsIterator != null && contentsIterator.hasNext()) {
      return true;
    } else if (nextIndex < folderContents.length) {
      return true;
    }
    return false;

  }

  /**
   * Overrides next() method from Iterator<T> implementation.
   * 
   * Returns the next file reference in the iteration.
   * 
   * @return the next file reference in the iteration
   * @throws NoSuchElementException - if the iteration has no more file references
   */
  @Override
  public File next() {
    if (hasNext()) {
      if (contentsIterator != null && contentsIterator.hasNext()) {
        return contentsIterator.next();
      } else {
        if (folderContents[nextIndex].isDirectory()) {
          try {
            contentsIterator = new DeepFileIterator(folderContents[nextIndex]);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        }
        File nextFile = folderContents[nextIndex];
        nextIndex++;
        return nextFile;
      }
    } else {
      throw new NoSuchElementException("contentsIterator has no next reference");
    }
  }
}


