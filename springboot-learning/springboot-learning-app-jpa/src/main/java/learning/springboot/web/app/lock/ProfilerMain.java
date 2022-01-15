package learning.springboot.web.app.lock;

import java.io.File;
import java.io.FileReader;

public class ProfilerMain {

    public static void main(String[] args) {
        printFile();
        printArray();
    }

    public static void printFile() {
        try (FileReader fileReader = new FileReader(new File("F:\\蒋伟帅＆吴书琴2018.5.1短片.mp4"))) {
            int c;
            while (-1 != (c = fileReader.read())) {

            }
        } catch (Exception e) {
        }
    }

    public static void printArray() {
        try (FileReader fileReader = new FileReader(new File("F:\\Wireshark-win64-3.6.0.exe"))) {
            int c;
            while (-1 != (c = fileReader.read())) {

            }
        } catch (Exception e) {
        }
    }

}
