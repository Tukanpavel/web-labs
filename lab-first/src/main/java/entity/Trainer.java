package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Getter
public class Trainer implements Serializable {

    //TODO: add constructor according to task

    private String fName;
    private String spec;
    private Integer phoneNum;
    private Date bDate;

    public final static String FILENAME = "lab-first-web.txt";

    public Trainer(@NotNull Trainer t){
        this.fName = t.getFName();
        this.spec = t.getSpec();
        this.phoneNum = t.getPhoneNum();
        this.bDate = t.getBDate();
    }

    public static void writeFile(@NotNull Trainer trainer) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(trainer);
            oos.close();
        } catch (IOException e) {
            System.out.println("Caught exception" + e);
        }
    }

    public static Trainer readFile() {
        Trainer b = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            b = new Trainer((Trainer) ois.readObject());
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Got an exception while reading file.." + e);
        } finally {
            return b;
        }
    }
}
