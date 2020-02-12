package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Getter
public class Trainer implements Serializable {

    private String fName;
    private String spec;
    private Integer phoneNum;
    private Date bDate;

    public final static String FILENAME = "lab-first-web.txt";

    public static void writeFile(@NonNull Trainer trainer) {
        String[] data = {
                trainer.getFName(),
                trainer.getSpec(),
                Long.toString(trainer.getBDate().getTime()),
                trainer.getPhoneNum().toString()
        };
        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get(FILENAME)))) {
            Stream.of(data).forEach(pw::println);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }

    public static void readFile(Trainer b) {
        try (Stream<String> stream = Files.lines(Paths.get(FILENAME))) {
            ArrayList<String> dataArr = new ArrayList<>(stream.collect(Collectors.toList()));
            b.setFName(dataArr.get(0));
            b.setSpec(dataArr.get(1));
            Date fileDate = b.getBDate();
            fileDate.setTime(Long.parseLong(dataArr.get(2)));
            b.setBDate(fileDate);
            b.setPhoneNum(Integer.parseInt(dataArr.get(3)));
        } catch (IOException e) {
            System.out.println("Got an I/O exception while reading file.." + e);
        }
    }
}
