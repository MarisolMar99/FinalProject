import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class CandyManager
{
    private ArrayList<String> availableCandy = new ArrayList<>();
    private String [] soldOutCandy = new String[10];
    private int soldOutCount = 0;
    public static Scanner kb = new Scanner(System.in);

    public void saveData(String availableFile, String soldOutFile)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(availableFile))){
            for(String candy: availableCandy)
            {
                writer.write(candy);
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error saving available candies: " + e);
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(soldOutFile))){
            for(int i=0; i<soldOutCount; i++)
            {
                writer.write(soldOutCandy[i]);
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error saving sold-out candies: " + e);
        }

    }

    public void loadData(String availableFile, String soldOutFile)
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(availableFile)))
        {
            String line;
            while((line=reader.readLine()) != null)
            {
                availableCandy.add(line);
            }
        }
        catch(IOException e)
        {
            System.out.println("Error loading available candies: " + e);
        }


        try(BufferedReader reader = new BufferedReader(new FileReader(soldOutFile)))
        {
            String line;
            while((line=reader.readLine()) != null)
            {
                soldOutCandy[soldOutCount++] = line;
            }
        }
        catch(IOException e)
        {
            System.out.println("Error loading sold-out candies: " + e);
        }
    }

    public void addCandy()
    {
        System.out.print("What is the name of the candy you want to add: ");
        String candy = kb.nextLine();
        availableCandy.add(candy);
    }

    public void sellCandy() throws IndexOutOfBoundsException
    {
        if (availableCandy.isEmpty())
        {
            System.out.println("No candy available to sell.");
            return;
        }

        viewCandy();
        int candyIndex = InputValidator.getValidInteger("Enter the index of the candy to sell: ", 0, availableCandy.size() - 1);

        if (soldOutCount == soldOutCandy.length)
        {
            System.out.println("Sold-out array is full.");
            return;
        }
        soldOutCandy[soldOutCount++] = availableCandy.remove(candyIndex);
        System.out.println(soldOutCandy[soldOutCount-1] + "Candy sold successfully");
    }

    public void viewCandy()
    {
        System.out.println("Available Candy: " + availableCandy);
        System.out.print("Sold-out Candy: ");
        for (int i = 0; i < soldOutCount; i++)
        {
            System.out.print(soldOutCandy[i] + " ");
        }
        System.out.println();
    }
}
