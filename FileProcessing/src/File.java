import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.*;


public class File {

	static String filePath;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<ArrayList<String>> ListOfWords = new ArrayList<ArrayList<String>>();
		ReadFromFile(scan, ListOfWords);
		printRows();
		String choosen;
		while(true) {
			
			showMenu();
			choosen = scan.nextLine();
			if(choosen.equals("1")) { 
				changeRows(scan, ListOfWords); printRows();
			}
			else if(choosen.equals("2")) {
				changeWords(scan, ListOfWords); printRows();
			}
			else if(choosen.equals("3")) return;
			
			else {
				System.out.println("Wrong command! Try again");
			}
		}
		
		
	}

	public static void changeRows(Scanner scan, ArrayList<ArrayList<String>> ListOfWords) {
		while(true) {
			System.out.print("Enter first row: ");
			int firstRow = Integer.parseInt(scan.nextLine());
			System.out.print("Enter second row: ");
			int secondRow = Integer.parseInt(scan.nextLine());
			if(firstRow >= ListOfWords.size() || secondRow >= ListOfWords.size() || firstRow<0 || secondRow<0) {
				printRows();
				System.out.println("Wrong number of row! Try again");
			}
			else {
				ArrayList<String>extraRow = new ArrayList<String>();
				extraRow = ListOfWords.get(firstRow);
				ListOfWords.set(firstRow, ListOfWords.get(secondRow));
				ListOfWords.set(secondRow, extraRow);
				WriteToFile(ListOfWords);
				break;
			}
		}
		
	}
	
	public static void changeWords(Scanner scan, ArrayList<ArrayList<String>> ListOfWords) {
		while(true) {
		
			System.out.print("Enter first row: ");
			int firstRow = Integer.parseInt(scan.nextLine());
			System.out.print("Enter first word: ");
			int firstWord = Integer.parseInt(scan.nextLine());
			System.out.print("Enter second row: ");
			int secondRow = Integer.parseInt(scan.nextLine());
			System.out.print("Enter second word: ");
			int secondWord = Integer.parseInt(scan.nextLine());
			if(firstRow >= ListOfWords.size() || secondRow >= ListOfWords.size() || firstRow<0 || secondRow<0 || firstWord<0 || secondWord<0 || firstWord>=ListOfWords.get(firstRow).size() || secondWord>=ListOfWords.get(secondRow).size()) {
				printRows();
				System.out.println("Wrong number of row or word! Try again");
			}
			else {
				String extraWord;
				extraWord = ListOfWords.get(firstRow).get(firstWord);
				ListOfWords.get(firstRow).set(firstWord, ListOfWords.get(secondRow).get(secondWord));
				ListOfWords.get(secondRow).set(secondWord, extraWord);
				WriteToFile(ListOfWords);
				break;
			}
		}
	}
	
	public static void ReadFromFile(Scanner scan, ArrayList<ArrayList<String>> ListOfWords){
		System.out.println("Enter file path:");
		String[] words;
		int row = 0;
		BufferedReader br = null;
		while(true) {
		try {
			filePath = scan.nextLine();
		      String currentLine;
		      br = new BufferedReader(new FileReader(filePath));
		      while ((currentLine = br.readLine()) != null) {
		    	  words = currentLine.split(" ");
		    	  ListOfWords.add(row, new ArrayList<String>());
		    	  Collections.addAll(ListOfWords.get(row), words);
		    	  row++;
		    	  
		      }
		    } catch (IOException e) {
		    	printRows();
		    	System.out.println("No such directory! Try again!");
		    }catch (NullPointerException exception) {
		    	printRows();
		    	System.out.println("No such directory! Try again!");
		    }finally {
		        try {
		            if (br != null) {
		            	br.close();
		            	break;
		            }
		         } catch (IOException ex) {
		            ex.printStackTrace();
		     }
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
		System.out.println("Choose a number of command:");
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
