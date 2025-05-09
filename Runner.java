import java.util.Scanner;

public class Runner
{
    public static CandyManager manager;

    public static void main(String [] args)
    {
        clearConsole();
        manager = new CandyManager();
        manager.loadData("availableCandy.txt", "soldOutCandy.txt");

        makeChoice();

    }

    public static void makeChoice()
    {
        Scanner kb = new Scanner(System.in);
        int choice;
        do
        {
            mainMenu();
            choice = InputValidator.getValidInteger("Enter your choice (1-4): ", 1, 4);
            System.out.println("You chose option " + choice);
            menuChoice(choice);
            System.out.println("\u001B[32m" + "Press eneter to continue..." + "\u001B[0m");
            kb.nextLine();
            clearConsole();
        }while(choice != 4);
    }

    public static void clearConsole()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void menuChoice(int choice)
    {
        switch(choice) 
        {
            case 1:
                manager.addCandy();
                break;
            case 2:
                manager.sellCandy();
                break;
            case 3:
                manager.viewCandy();
                break;
            case 4:
                manager.saveData("availableCandy.txt", "soldOutCandy.txt");
                System.out.println("Data saved. Goodbye!");
                return;
        }
    }

    public static void mainMenu()
    {
        System.out.println("Choose an option: ");
        System.out.println("1. Add Candy");
        System.out.println("2. Sell Candy");
        System.out.println("3. View Candy");
        System.out.println("4. Exit");
    }
}
