// This program takes a text file and prints each unique word and the number of times it occurs in the file

import java.io.File;
import java.util.*;

public class WordCounter {
   public static void main(String[] args){
   
      // String to receive contents of text file
      String holdme = "";
   
      // pass text file into scanner and read line-by-line into string
      // try-catch errors if file does not exist
      try {
         File source = new File(/* text file */);
         Scanner s = new Scanner(source);
         while(s.hasNext()){
            holdme = holdme + "\n" + s.nextLine();
         }
         s.close();
      } catch(Exception e){
         System.out.println("Error: file not found.");
      }
   
      // replace all dashes with a space
      holdme = holdme.replaceAll("\\p{Pd}", " ");
   
      // split string into array by whitespace and parentheses
      String[] passme = holdme.split("\\s|\\(|\\)+");
      
      // for all strings in array, remove non-alphanumeric characters from front and end of strings, 
      // and convert to lower case
      cleanWords(passme);
      
      // create hash table with String key and Integer values
      // key represents unique string from text file, value represents occurrence count of string
      HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
      
      // loop through string array to identify keys and to process each key's occurrence in text file.
      for (int i = 0; i < passme.length; i++){
         
         // ignore empty strings
         if (passme[i].equals("")){
            continue;
         }
         else {         
         
            // increment occurrences of word for each time it's found
            if (hashmap.containsKey(passme[i])){
               hashmap.put(passme[i], hashmap.get(passme[i]) + 1);
            }
            
            // otherwise, generate counter for new word
            else {
               hashmap.put(passme[i], 1);
            }
         }
      }
      
      Set set = hashmap.entrySet();
      
      Iterator it = set.iterator();
      
      // create array of strings to hold hashmap keys for sorting purposes
      String[] sortedkeys = new String[set.size()];
      
      // populate string array with hashmap keys
      
      // check that hashmap and array sizes are equal
      if (sortedkeys.length != set.size()){
         System.out.println("Size of sorted keys array and size of hashmap do not match.");
      }
      else{
         int counter = 0;
         while(it.hasNext()){
            Map.Entry map = (Map.Entry)it.next();
            sortedkeys[counter] = (String)map.getKey();
            counter++;
         }
      }
      
      // sort array of keys
      Arrays.sort(sortedkeys);
      
      // referencing sorted array of keys, output each word and number of occurrences in given text file
      for (int i = 0; i < sortedkeys.length; i++){
         System.out.println(hashmap.get(sortedkeys[i]) + " " + sortedkeys[i]);
      }
      
}
   
   /* this method passes in an array of strings, alters it by cycling each string to
   *  - remove non-alphanumeric characters from the front and back of the string,
   *  - then converts string to lowercase.
   */
   public static void cleanWords(String[] array){
      
      for (int i = 0; i < array.length; i++){
         String temp = array[i];
         // remove non-alphanumeric character(s) at front of string
         while (temp.length() > 0 && !Character.isLetter(temp.charAt(0)) && !Character.isDigit(temp.charAt(0))){
            temp = temp.substring(1);
         }
      
         // remove non-alphanumeric character(s) at end of string
         while (temp.length() > 0 && !Character.isLetter(temp.charAt(temp.length()-1)) && !Character.isDigit(temp.charAt(temp.length()-1))){
            temp = temp.substring(0, temp.length()-1);
         }
         // convert string to lower case
         array[i] = temp.toLowerCase();
      }
   }
}