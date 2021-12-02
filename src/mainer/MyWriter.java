package mainer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class MyWriter {
    String path;

    MyWriter(String path) {
        this.path = path;
    }
    public void write(Vector<Point> str) throws IOException {

        BufferedWriter output = new BufferedWriter(new FileWriter(path + "DS_Convex_Hull.txt"));

        for(Point pt: str) {
            output.write(pt.x + " " + pt.y);
            output.newLine();
        }
        output.close();
    }
}