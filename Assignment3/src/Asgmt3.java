import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Asgmt3 
{
	
	public static File getInput()
	{
		while(true)
		{
			System.out.println("Please enter an input file name: ");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        String fileName = null;
			try {
				fileName = "src/" + reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("File name does not exist.");
			}
			File file = new File(fileName); 
			// if file does not exist or not a file
			
				if ((file.exists() && file.isFile())) 
				{
					return file;
				}
				else
				{
					System.err.println(fileName + " does not exist. Please enter a valid file name.");
				}
		}
	}
	
	public static void menu(BinaryTree list)
	{
		while(true)
		{
		System.out.println("\nWelcome to the search tree menu! Please enter a number for a command.\n"
				+ "1. Search for list item\n"
				+ "2. Display entire list\n"
				+ "3. Quit\n");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		int menu = Integer.parseInt(input);
		switch(menu)
			{
				case 1:
					System.out.println("Please enter a word you are looking for: ");
					String name = in.nextLine();
					if(list.findWord(name) != -1)
					{
						System.out.println("Word found! it appears " + list.findWord(name) + " times.");
					}
					else
						System.out.println("Word not found");
					
					break;
				case 2:
						System.out.println("Please enter a traversal method: IN-ORDER, PRE-ORDER, or POST-ORDER.\n");
						String traversal = in.nextLine();
						traversal = traversal.toUpperCase();
						switch(traversal)
							{
								case "IN-ORDER":
									list.inorderPrint();
									break;
								
								case "PRE-ORDER":
									list.preorderPrint();
									break;
									
								case "POST-ORDER":
									list.postorderPrint();
									break;
									
								default:
									System.out.println("Invalid traversal method. Please try again.");
									break;
							}
					break;
				case 3:
					return;				
				default:
					System.err.println("Error: invalid menu access.");
					break;
			}
		}
	} 
	
	
	public static ArrayList<String> toStringArray(File file) throws IOException
	{
		String line = null;
		String line2 = null;
		String line3= null;
		String line4 = null;
		FileReader fReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fReader);
		ArrayList<String> list = new ArrayList<String>();
		while((line = reader.readLine()) != null)
		{
			if(line.length() > 0)
			{
				line2 = line.replace('-', ' ');
				line3 =line2.replaceAll("[^a-zA-Z0-9 \n]", "");
				line4 = line3.toLowerCase();
				for(String s:line4.split("\\s+"))
				{
					if(s!="")
					{
					list.add(s);
					}
				}
			}
		}
		reader.close();
		return list;
	}
	
	public static void populateTree(ArrayList<String> list, BinaryTree words)
	{
		for(int i = 0; i < list.size(); i++)
		{
			words.add(list.get(i));
		}
	}
	
	
	
	
	public static void main(String args[]) throws IOException
	{
		
		File file = getInput();	
		ArrayList<String> list = toStringArray(file);		
		BinaryTree words = new BinaryTree();
		populateTree(list,words);

		System.out.println("The total number of words in " + file.toString() + " = " + words.countPreorder());
		System.out.println("Number of unique words in " + file.toString() + " = " + words.uniquePreorder());
		int largestFrequency = words.largestPreorder();
		System.out.println("The word(s) which occur(s) most often and the number of times that it/they occur(s) =");
		words.findLargest(largestFrequency);
		System.out.println("The maximum height of the tree = " + words.maximumDepth());
		
		menu(words);
		
	}
	
	
}

