package test;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dimaf on 27.02.2018.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(" Hello world ");
        try {
//            Properties properties = new Properties();
//            properties.load(Files.newBufferedReader(Paths.get("conf.properties")));
//
//            String pathToTxt = properties.getProperty("pathToTxt");
//            String pathToImage = properties.getProperty("pathToImage");
//            System.out.println(pathToTxt);
//
//            CloseableHttpClient aDefault = HttpClients.createDefault();
//            CloseableHttpResponse execute = aDefault.execute(new HttpGet("http://google.com"));
//            try (BufferedReader x = new BufferedReader(new InputStreamReader(execute.getEntity().getContent()))) {
//                String line;
//                Path path = Paths.get(pathToImage);
//                path.toFile().mkdirs();
//                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path.resolve("google.html"))) {
//                    while ((line = x.readLine()) != null) {
//                        bufferedWriter.write(line);
//                    }
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        generatePolygon(6, new Point2D(1000, 1000), false);
    }

    public static List<Point2D> generatePolygon(int polygonSize, Point2D max, boolean equilateral) {
        Random random = new Random();
        Point2D start = new Point2D(random.nextInt((int) max.getX()), random.nextInt((int) max.getY()));
        int startAngle = 0;

        ArrayList<Point2D> polygon = new ArrayList<>();
        for (int i = 0; i < polygonSize; i++) {
            startAngle += getOuterAngle(polygonSize, equilateral);
            Point2D newPoint = newPoint(start, startAngle, getMagnitude(equilateral, max));
            polygon.add(checkAndModify(newPoint, max));
            start = newPoint;
        }
        return polygon;
    }

    private static int getMagnitude(boolean equilateral, Point2D max) {
        Random random = new Random();
        int magnitude = random.nextInt((int) Math.min(max.getX(), max.getY()) / 2);
        return equilateral ? magnitude : (int) (magnitude * 0.1) + random.nextInt((int) (magnitude * 0.9));
    }

    private static Point2D newPoint(Point2D start, int angle, int magnitude) {
        return start.add(new Point2D(Math.cos(Math.toRadians(angle)) * magnitude, Math.sin(Math.toRadians(angle)) * magnitude));
    }

    private static Point2D checkAndModify(Point2D point, Point2D max) {
        double x = point.getX() < 0.0 ? 0.0 : point.getX() > max.getX() ? max.getX() : point.getX();
        double y = point.getY() < 0.0 ? 0.0 : point.getY() > max.getY() ? max.getY() : point.getY();
        return new Point2D(x, y);
    }

    private static int getOuterAngle(int size, boolean equilateral) {
        return equilateral ? 360 / size : 180 / size + new Random().nextInt(180 / size);
    }
}
