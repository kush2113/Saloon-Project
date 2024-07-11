
import java.io.FileNotFoundException;

public class Kush {
    public static void main(String[] args) {
        try {
            FileReaderDemo.readFile();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}

