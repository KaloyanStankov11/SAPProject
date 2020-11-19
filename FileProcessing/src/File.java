import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class File {

	static String filePath;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<ArrayList<String>> ListOfWords = new ArrayList<ArrayList<String>>();
		ReadFromFile(scan, ListOfWords);
		printRows();
		int choosen = 0;
		while(true) {
			
			showMenu();
			choosen = Integer.parseInt(scan.nextLine());
			if(choosen == 1) { 
				changeRows(scan, ListOfWords); printRows();
			}
			else if(choosen == 2) {
				changeWords(scan, ListOfWords); printRows();
			}
			else if(choosen == 3) return;
			
			else {
				System.out.println("Wrong command! Try again");
			}
		}
		
		
	}

	public static void changeRows(Scanner scan, ArrayList<ArrayList<String>> ListOfWords) {
		System.out.print("Enter first row: ");
		int firstRow = Integer.parseInt(scan.nextLine());
		System.out.print("Enter second row: ");
		int secondRow = Integer.parseInt(scan.nextLine());
		ArrayList<String>extraRow = new ArrayList<String>();
		extraRow = ListOfWords.get(firstRow);
		ListOfWords.set(firstRow, ListOfWords.get(secondRow));
		ListOfWords.set(secondRow, extraRow);
		WriteToFile(ListOfWords);
		System.out.println("Rows changed");
		
	}
	
	public static void changeWords(Scanner scan, ArrayList<ArrayList<String>> ListOfWords) {
		System.out.print("Enter first row: ");
		int firstRow = Integer.parseInt(scan.nextLine());
		System.out.print("Enter first word: ");
		int firstWord = Integer.parseInt(scan.nextLine());
		System.out.print("Enter second row: ");
		int secondRow = Integer.parseInt(scan.nextLine());
		System.out.print("Enter second word: ");
		int secondWord = Integer.parseInt(scan.nextLine());
		String extraWord;
		extraWord = ListOfWords.get(firstRow).get(firstWord);
		ListOfWords.get(firstRow).set(firstWord, ListOfWords.get(secondRow).get(secondWord));
		ListOfWords.get(secondRow).set(secondWord, extraWord);
		WriteToFile(ListOfWords);
	}
	
	public static void ReadFromFile(Scanner scan, ArrayList<ArrayList<String>> ListOfWords){
		System.out.println("Enter file path:");
		filePath = scan.nextLine();
		String[] words;
		int row = 0;
		BufferedReader br = null;
		   try {

		      String currentLine;

		      br = new BufferedReader(new FileReader(filePath));

		      while ((currentLine = br.readLine()) != null) {
		    	  words = currentLine.split(" ");
		    	  ListOfWords.add(row, new ArrayList<String>());
		    	  Collections.addAll(ListOfWords.get(row), words);
		    	  row++;
		      }

		    } catch (IOException e) {
		    	System.out.println("No such file or directory!");
		         e.printStackTrace();
		    } finally {
		        try {
		            if (br != null)br.close();
		            
		         } catch (IOException ex) {
		            ex.printStackTrace();
		     }
		   }
		   
	}
	
	public static void WriteToFile(ArrayList<ArrayList<String>> ListOfWords) {
	      FileWriter fileWriter = null;
	      try {

	         fileWriter = new FileWriter(filePath);
	         for (int i = 0; i < ListOfWords.size(); i++) {
	        	 for (int j = 0; j < ListOfWords.get(i).size(); j++) {
	        		fileWriter.write(ListOfWords.get(i).get(j) + " ");
	             }
	             	fileWriter.write(System.lineSeparator());
	            }
	      } catch (IOException e) {
	           e.printStackTrace();
	      }finally{
	        
	        if(fileWriter != null){
	            try {
	               fileWriter.close();
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	         }
	       }
	}
	
	public static void showMenu() {
		System.out.println("Choose a command:");
		System.out.println("1. Change rows");
		System.out.println("2. Change words");
		System.out.println("3. Exit");
		System.out.print("Command: ");
	}
	
	public static void printRows() {
		for (int i = 0; i < 50; ++i) System.out.println();
	}
	
	public static void print(ArrayList<ArrayList<String>> ListOfWords) {
		for(int i = 0; i<ListOfWords.size(); i++) {
			for(int j = 0; j<ListOfWords.get(i).size(); j++) {
				System.out.print(ListOfWords.get(i).get(j) + " ");
			}
			System.out.println("");
		}
	}
}
