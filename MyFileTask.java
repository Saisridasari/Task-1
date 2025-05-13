//giving access to classes read ,writing files
import java.io.*;
import java.util.Scanner;

public class MyFileTask {

    static File dataFile = new File("data.txt");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** File Handling by Saisri Dasari ***");
            System.out.println("1. Save data to file");
            System.out.println("2. Display file content");
            System.out.println("3. Update existing file");
            System.out.println("4. Exit");
            System.out.println("Pick any option: ");

            String opt = sc.nextLine().trim();
            System.out.println("You entered: " + opt); // Debug line

            switch (opt) {
                case "1":
                    saveToFile(sc);
                    break;
                case "2":
                    showFileContent();
                    break;
                case "3":
                    updateFileContent();
                    break;
                case "4":
                    System.out.println("Closing program... bye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Wrong choice, try again.");
            }
        }
    }

    public static void saveToFile(Scanner sc) {
        try {
            // If the file doesn't exist, create it
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }

            // Open file in append mode
            try (PrintWriter pw = new PrintWriter(new FileWriter(dataFile, true))) {
                System.out.println("Enter lines to save (type 'EXIT' to finish):");
                while (true) {
                    String line = sc.nextLine().trim();
                    if (line.equalsIgnoreCase("EXIT")) {
                        break;
                    } 
                    if (!line.isEmpty()) {  // Avoid saving empty lines
                        pw.println(line);
                    }
                }
                System.out.println("Saved successfully!");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while saving.");
            e.printStackTrace();
        }
    }

    public static void showFileContent() {
        if (!dataFile.exists()) {
            System.out.println("No data found. File is missing.");
            return;
        }
        try (Scanner read = new Scanner(dataFile)) {
            System.out.println("--- File Content ---");
            while (read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Unable to read the file.");
            e.printStackTrace();
        }
    }

    public static void updateFileContent() {
        if (!dataFile.exists()) {
            System.out.println("File not available to update.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            StringBuilder updatedText = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                updatedText.append(line.replace("java", "ModifiedJava")).append("\n");
            }

            try (PrintWriter pw = new PrintWriter(dataFile)) {
                pw.print(updatedText.toString());
            }

            System.out.println("File updated successfully.");
        } catch (IOException e) {
            System.out.println("Error while updating file.");
            e.printStackTrace();
        }
    }
}
