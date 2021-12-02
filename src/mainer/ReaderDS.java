package mainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ReaderDS {

    static int[][] dataSet;

    ReaderDS(String file) throws IOException {
        Path path = Paths.get(file);
        int lineCount = (int) Files.lines(path).count();
        dataSet = new int[lineCount][2];

    }

    public Point[] readDS() {
        String line;
        String[] string;
        Point[] point = new Point[dataSet.length];
        int i = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("DS6.txt"));
            while ((line = reader.readLine()) != null) {
                string = line.split(" ");
                dataSet[i][0] = Integer.parseInt(string[1]);
                dataSet[i][1] = Integer.parseInt(string[0]);
                point[i] = new Point(Integer.parseInt(string[1]), Integer.parseInt(string[0]));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return point;
    }
}