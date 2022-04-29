package app;

import java.util.Scanner;
import java.io.*;

public class RLEconverter {
   private final static int DEFAULT_LEN = 100; // used to create arrays.

   /*
    *  This method reads in an uncompressed ascii image file that contains 
    *  2 characters. It stores each line of the file in an array.
    *  It then calls compressAllLines to get an array that stores the compressed
    *  version of each uncompressed line from the file. The compressed array
    *  is then passed to the getCompressedFileStr method which returns a String
    *  of all compressed lines (the two charcaters are written in the first line)
    *  in CSV format. This String is written to a text file with the prefix "RLE_"
    *  added to the original, uncompressed file name.
    *  Note that dataSize keeps track of the number of lines in the file. The array 
    *  that holds the lines of the file is initialized to the DEFAULT_LEN, which 
    *  is assumed to be << the number of lines in the file.
    */   
  public void compressFile(String fileName) throws IOException{
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] decompressed = new String [DEFAULT_LEN];
    int dataSize = 0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        decompressed[dataSize]=line;
        dataSize++;
    }
    scan.close();
    char[] fileChars = discoverAsciiChars(decompressed, dataSize); 
    String[] compressed = compressAllLines(decompressed, dataSize, fileChars);
    writeFile(getCompressedFileStr(compressed, fileChars), "RLE_"+fileName);
  }
  
   
/*
 * This method implements the RLE compression algorithm. It takes a line of uncompressed data
 * from an ascii file and returns the RLE encoding of that line in CSV format.
 * The two characters that make up the image file are passed in as a char array, where
 * the first cell contains the first character that occurred in the file.
 *
 * Algorithm 
 * Pick the first character from the source
 * Count subsequent occurrence of the same character in the given input
 * If a new character is found append the character and count in the result
 * Continue the process until the end of the input
 * For the fileChars, do it so the first letter in the array is the one that is read first
*/ 

public String compressLine(String line, char[] fileChars){
   //TODO: Implement this method
  if (line.length() == 0) {
    return null;
  }
  // Variables for program
  int asciiIndex = 0;
  int count = 0;
  String output = "";
  int i = 0;
  // Loops through the String line to check if they have the same
  // accsi element. IF they do, it will add a count and increment the i.
  while (i < line.length()){
    if (line.charAt(i) == fileChars[asciiIndex]) {
      count++;
      i++;
    } else {
      //Adds the count and then moves onto the next letter
      output += count + ",";
      count = 0;
      //Tests which ascii it is on and then starts counting
      if (asciiIndex != 0) {
        asciiIndex = 0;
      } else {
        asciiIndex = 1;
      }
    } 
  }
  //Returns the output in CSV format
  output += count + ",";
  return output.substring(0,output.length()-1);
}

  /*
   *  This method discovers the two ascii characters that make up the image. 
   *  It iterates through all of the lines and writes each compressed line
   *  to a String array which is returned. The method compressLine is called on 
   *  each line.
   *  The dataSize is the number of lines in the file, which is likely to be << the length of lines.
   */
  public String[] compressAllLines(String[] lines, int dataSize, char[] fileChars){
      //TODO: Implement this method
      //Creates a new String array in order to store all the various lines of code
      //for loop to iterate through the data of lines
      //calling compressline and then moving to the next line.
      String[] compressed = new String[dataSize];

      for (int i = 0; i < dataSize; i++) {
         compressed[i] = compressLine(lines[i], fileChars);
      }
      return compressed;
  }


/*
 *  This method assembles the lines of compressed data for
 *  writing to a file. The first line must be the 2 ascii characters
 *  in comma-separated format. 
 */
public String getCompressedFileStr(String[] compressed, char[] fileChars) {
    //TODO: Implement this method
    //Creates an empty string and then making the first line must be the 2 ascii characters
   //in comma-separated format. 
      String compressedData = "";
      compressedData = fileChars[0] + "," + fileChars[1];
      //For loop to itereate through the compressed array and then adding a new line to each element.
      for (int i = 0; i < compressed.length; i++) {
        compressedData += "\n" + compressed[i];
      }
      return compressedData;
}
   /*
    *  This method reads in an RLE compressed ascii image file that contains 
    *  2 characters. It stores each line of the file in an array.
    *  It then calls decompressAllLines to get an array that stores the decompressed
    *  version of each compressed line from the file. The first row contains the two 
    *  ascii charcaters used in the original image file. The decompressed array
    *  is then passed to the getDecompressedFileStr method which returns a String
    *  of all decompressed lines, thus restoring the original, uncompressed image.
    *  This String is written to a text file with the prefix "DECOMP_"
    *  added to the original, compressed file name.
    *  Note that dataSize keeps track of the number of lines in the file. The array 
    *  that holds the lines of the file is initialized to the DEFAULT_LEN, which 
    *  is assumed to be << the number of lines in the file.
    */   
  public void decompressFile(String fileName) throws IOException{
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] compressed = new String [DEFAULT_LEN];
    int dataSize =0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        compressed[dataSize]=line;
        dataSize++;
    }
    scan.close();
    String[] decompressed = decompressAllLines(compressed, dataSize);
    writeFile(getDecompressedFileStr(decompressed), "DECOMP_"+fileName);
  }
 
   /*
   * This method decodes lines that were encoded by the RLE compression algorithm. 
   * It takes a line of compressed data and returns the decompressed, or original version
   * of that line. The two characters that make up the image file are passed in as a char array, 
   * where the first cell contains the first character that occurred in the file.
   * 
   * Get the first character of the input
   * Get the subsequent number until we find non digit character
   * Run loop that number of times and append the character to the result
   * Repeat the process until we have completed reading input
   */
   public String decompressLine(String line, char[] fileChars){
      //TODO: Implement this method
      int asciiIndex = 0;
      String output = "";
      int i = 0;
      int j = 0;
      //Creates multiple new strings by splitting the string where there are commas
      String[] arrOfline = line.split(",");
      //iterates through the array of the String line and ends at the length of it
      for (i = 0; i < arrOfline.length; i++){
        //Reads the array element and turns it into an Integer that can be used to increment fileChars
        for (j = 0; j < Integer.parseInt(arrOfline[i]); j++) {
          output += fileChars[asciiIndex];
       }
       //Switchs the ascii value to the other one so you can use both of them
       asciiIndex = 1 - asciiIndex;
    }
    return output;
  }
    /*
   *  This method iterates through all of the compressed lines and writes 
   *  each decompressed line to a String array which is returned. 
   *  The method decompressLine is called on each line. The first line in
   *  the compressed array passed in are the 2 ascii characters used to make
   *  up the image. 
   *  The dataSize is the number of lines in the file, which is likely to be << the length of lines.
   *  The array returned contains only the decompressed lines to be written to the decompressed file.
   */
  public String[] decompressAllLines(String[] lines, int dataSize){
     //TODO: Implement this method
     //Creates new string array that is the length of lines - 1
     String[] decompressed = new String[dataSize];
     //creating fileChar characaters in order to pass them into the decompress method
     char[] fileChars = new char[2];
     fileChars[0] = lines[0].charAt(0);
     fileChars[1] = lines[0].charAt(2);
    //for loop to iterate across lines and up to the size of dataSize
        for (int i = 0; i < dataSize; i++) {
            //decompresses each line 
            decompressed[i] = decompressLine(lines[i], fileChars);
        } 
     return decompressed;
  }
  
  /*
   *  This method assembles the lines of decompressed data for
   *  writing to a file. 
   */
  public String getDecompressedFileStr(String[] decompressed){
    //TODO: Implement this method
    //create a new string to get the decompressed file 
    String data = "";
    //adding decompressed[0] to data
     data += decompressed[0];
     //checks across the String decompressed and adds lines based
     for (int i = 0; i < decompressed.length; i++) {
        data += decompressed[i-1] + "\n";
  }
      return data.substring(0,data.length()-1);
  }


  // assume the file contains only 2 different ascii characters.
  public char[] discoverAsciiChars(String[] decompressed, int dataSize){
//TODO: Implement this method
//Char Array and set size to 2
char[] fileChars = new char[2];
// FileChars[0] to decompressed[0] at the first element
fileChars[0] = decompressed[0].charAt(0);
//nested for loop to iterate through the dataSize and decompressd
for (int i = 0; i < dataSize; i++) {
  for (int j = 0; j < decompressed[i].length(); j++) {
    if (fileChars[0] != decompressed[i].charAt(j)) {
      //sets fileChars[1] decompressed[i] at the letter j
      fileChars[1] = decompressed[i].charAt(j);
    }
  }
}
  return fileChars;
}



   
   public void writeFile(String data, String fileName) throws IOException{
		PrintWriter pw = new PrintWriter(fileName);
      pw.print(data);
      pw.close();
   }
}