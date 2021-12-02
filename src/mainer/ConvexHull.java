package mainer;

import java.util.*;

class Point
{
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class ConvexHull {

    static Vector<Point> hull = new Vector<>();

    private static int orientation(Point p, Point q, Point r)
    {
        int val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0;
        return (val > 0)? 1: 2;
    }

    // Prints convex hull of a set of n points.
    public static void convexHull(Point[] points, int n)
    {
        // There must be at least 3 points
        if (n < 3) return;

        // Find the leftmost point
        int lowerLeftPt = 0;
        for (int i = 1; i < n; i++)
            if (points[i].x < points[lowerLeftPt].x)
                lowerLeftPt = i;

        // Start from leftmost point
        int pt = lowerLeftPt, current;
        do
        {
            hull.add(points[pt]);
            current = (pt + 1) % n;

            for (int i = 0; i < n; i++)
            {
                if (orientation(points[pt], points[i], points[current]) == 2)
                    current = i;
            }
            pt = current;

        } while (pt != lowerLeftPt);
    }
}