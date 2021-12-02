package mainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class Main extends JComponent  {
    
    final String PATH = "D:\\ConvexHull\\";

    public static void main(String[] args) {
        //create an object for the contents of the window
        Main main = new Main();
        //creating a window object
        JFrame f = new JFrame("DatasetVisualization");
        f.setSize(960, 540);
        f.setVisible(true);
        f.setLocation(40, 40);
        f.add(main);
    }

    @Override
    public void paint(Graphics gr) {
        BufferedImage bImg = new BufferedImage(960, 540, BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = (Graphics2D)gr;
        Graphics2D graphImage = bImg.createGraphics();
        MyWriter writeDS = new MyWriter(PATH);

        //set background color
        graphImage.setColor(Color.WHITE);
        //creating a background
        graphImage.fillRect(0, 0, 960, 540);
        //set the color of the points
        graphImage.setColor(Color.BLACK);

        try {
            ReaderDS reader = new ReaderDS(PATH + "DS6.txt");
            Point[] points = reader.readDS();
            ConvexHull.convexHull(points, points.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //create creating a point object
        Rectangle point = new Rectangle(0, 0, 1, 1);

        for(int i = 0; i < ConvexHull.hull.size(); i++) {

            point.x = ConvexHull.hull.get(i).x;
            point.y = ConvexHull.hull.get(i).y;

            graphImage.setColor(Color.black);
            graph.draw(point);
            if(i != ConvexHull.hull.size()-1) {
                graph.setColor(Color.blue);
                graph.drawLine(
                        ConvexHull.hull.get(i).x,
                        ConvexHull.hull.get(i).y,
                        ConvexHull.hull.get(i+1).x,
                        ConvexHull.hull.get(i+1).y);
                graphImage.setColor(Color.blue);
                graphImage.drawLine(
                        ConvexHull.hull.get(i).x,
                        ConvexHull.hull.get(i).y,
                        ConvexHull.hull.get(i+1).x,
                        ConvexHull.hull.get(i+1).y);
                System.out.println(ConvexHull.hull.get(i).x + "," + ConvexHull.hull.get(i).y);
            }
        }
        for(int i = 0; i < ReaderDS.dataSet.length; i++) {
            point.x = ReaderDS.dataSet[i][0];
            point.y = ReaderDS.dataSet[i][1];
            graph.setColor(Color.black);
            graph.fill(point);
            graphImage.setColor(Color.black);
            graphImage.draw(point);
        }

        //connection of the last and first point
        graph.setColor(Color.blue);
        graph.drawLine(
                ConvexHull.hull.get(ConvexHull.hull.size()-1).x,
                ConvexHull.hull.get(ConvexHull.hull.size()-1).y,
                ConvexHull.hull.get(0).x,
                ConvexHull.hull.get(0).y);
        graph.setColor(Color.black);
        graphImage.drawLine(
                ConvexHull.hull.get(ConvexHull.hull.size()-1).x,
                ConvexHull.hull.get(ConvexHull.hull.size()-1).y,
                ConvexHull.hull.get(0).x,
                ConvexHull.hull.get(0).y);

        try {
            //save in graphic format
            System.out.println(bImg.getHeight());
            System.out.println(bImg.getWidth());
            writeDS.write(ConvexHull.hull);
            ImageIO.write(bImg, "jpeg", new File(PATH + "output_image.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}