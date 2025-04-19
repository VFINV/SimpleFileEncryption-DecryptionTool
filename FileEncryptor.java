import java.io.*;
import java.util.Scanner;

public class FileEncryptor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 🔐 File Encryptor Tool ===");
        System.out.print("Enter file path: ");
        String inputFile = scanner.nextLine();

        System.out.print("Enter encryption key (number): ");
        int key = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Do you want to Encrypt (E) or Decrypt (D)? ");
        String mode = scanner.nextLine().trim().toUpperCase();

        String outputFile = mode.equals("E") ? "encrypted.txt" : "decrypted.txt";

        try {
            String content = readFile(inputFile);
            String result = (mode.equals("E")) ? encrypt(content, key) : decrypt(content, key);
            writeFile(outputFile, result);
            System.out.println("✅ File processed. Output saved to: " + outputFile);
        } catch (IOException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append((char)(c + key));
        }
        return result.toString();
    }

    public static String decrypt(String text, int key) {
        return encrypt(text, -key); // reuse the encrypt method by reversing the key
    }

    public static String readFile(String path) throws IOException {
        return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path)));
    }

    public static void writeFile(String path, String content) throws IOException {
        java.nio.file.Files.write(java.nio.file.Paths.get(path), content.getBytes());
    }
}
