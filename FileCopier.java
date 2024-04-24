import java.io.*;

public class FileCopier {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the source file name:");
            String sourceFileName = reader.readLine(); // Get source file name from user input

            System.out.println("Enter the destination file name:");
            String destinationFileName = reader.readLine(); // Get destination file name from user input

            File sourceFile = new File(sourceFileName);
            File destinationFile = new File(destinationFileName);

            if (!sourceFile.exists()) {
                System.err.println("Source file does not exist.");
                return;
            }

            if (destinationFile.exists()) {
                System.err.println("Destination file already exists. Overwrite? (Y/N)");
                String overwriteChoice = reader.readLine().trim().toUpperCase();
                if (!overwriteChoice.equals("Y")) {
                    System.out.println("File copy operation aborted.");
                    return;
                }
            }

            long startTime = System.currentTimeMillis();

            try (InputStream inputStream = new FileInputStream(sourceFile);
                 OutputStream outputStream = new FileOutputStream(destinationFile)) {

                byte[] buffer = new byte[8192]; // 8KB buffer size
                int bytesRead;
                long totalBytesCopied = 0;
                long fileSize = sourceFile.length();

                System.out.println("Copying file...");
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    totalBytesCopied += bytesRead;

                    // Calculate and display progress
                    int progress = (int) ((totalBytesCopied * 100) / fileSize);
                    System.out.print("\rProgress: " + progress + "%");
                }

                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;

                System.out.println("\nFile copied successfully.");
                System.out.println("Time taken: " + elapsedTime + " milliseconds");
            } catch (IOException e) {
                System.err.println("Error copying file: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println("Error reading user input: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                reader.close(); // Close the reader
            } catch (IOException e) {
                System.err.println("Error closing reader: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
