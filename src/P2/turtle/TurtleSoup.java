/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     *
     * @param turtle     the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        for (int i = 0; i < 4; i++) {
            turtle.forward(sideLength);
            turtle.turn(-90);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * <p>
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     *
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        return 180 - (double) 360 / sides;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * <p>
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     *
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        return 360 / (int) (180 - angle);
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * <p>
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     *
     * @param turtle     the turtle context
     * @param sides      number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        for (int i = 0; i < sides; i++) {
            turtle.forward(sideLength);
            turtle.turn(180 - calculateRegularPolygonAngle(sides));
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * <p>
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360.
     * <p>
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     *
     * @param currentHeading current direction as clockwise from north
     * @param currentX       current location x-coordinate
     * @param currentY       current location y-coordinate
     * @param targetX        target point x-coordinate
     * @param targetY        target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     * must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        int offsetX = targetX - currentX, offsetY = targetY - currentY;
        double targetHeading;
        final double angel = 180 * (Math.atan(offsetY / (double) offsetX)) / Math.PI;
        if (offsetX > 0) {
            if (offsetY == 0) {
                targetHeading = 90;
            } else {
                targetHeading = 90 - angel;
            }
        } else if (offsetX == 0) {
            if (offsetY > 0) {
                targetHeading = 0;
            } else if (offsetY < 0) {
                targetHeading = 180;
            } else {
                return 0;
            }
        } else {
            if (offsetY == 0) {
                targetHeading = 270;
            } else {
                targetHeading = 270 - angel;
            }
        }
        return (targetHeading - currentHeading) >= 0 ? (targetHeading - currentHeading) : 360 + (targetHeading - currentHeading);
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * <p>
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     *
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     * otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> headingToPointList = new ArrayList<>();
        double tempHeading = 0.0, startHeading = 0.0;
        for (int i = 0; i < xCoords.size() - 1; i++) {
            tempHeading = calculateHeadingToPoint(startHeading, xCoords.get(i), yCoords.get(i),
                    xCoords.get(i + 1), yCoords.get(i + 1));
            headingToPointList.add(tempHeading);
            startHeading += tempHeading;
            startHeading = startHeading % 360;
        }
        return headingToPointList;
    }

    /**
     * Draw your personal, custom art.
     * <p>
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     *
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        PenColor[] colorList = {
                PenColor.RED,
                PenColor.PINK,
                PenColor.ORANGE,
                PenColor.GRAY,
                PenColor.YELLOW,
                PenColor.GREEN,
                PenColor.BLACK,
                PenColor.CYAN,
                PenColor.BLUE,
                PenColor.MAGENTA};
        turtle.turn(147);
        for (int i = 0; i < 10; i++) {
            turtle.color(colorList[9 - i]);
            for (int j = 0; j < 20; j++) {
                drawRegularPolygon(turtle, 89, i);
                turtle.turn(i % 2 == 0 ? 12 : -12);
            }
        }
        turtle.turn(-147);
        List<Integer> turnPointx = new ArrayList<>(), turnPointy = new ArrayList<>();
        List<Double> turnAngel;
        turnPointx.add(0);
        turnPointx.add(-5);
        turnPointx.add(-5);
        turnPointx.add(5);
        turnPointx.add(5);
        turnPointx.add(0);
        turnPointy.add(0);
        turnPointy.add(0);
        turnPointy.add(-300);
        turnPointy.add(-300);
        turnPointy.add(0);
        turnPointy.add(0);
        turnAngel = calculateHeadings(turnPointx, turnPointy);
        double distance;
        for (int i = 0; i < turnPointx.size()-1; i++) {
            distance = Math.sqrt(((turnPointx.get(i+1)- turnPointx.get(i))*(turnPointx.get(i+1)- turnPointx.get(i)) + (turnPointy.get(i+1)- turnPointy.get(i)) * (turnPointy.get(i+1)- turnPointy.get(i))));
            turtle.turn(turnAngel.get(i));
            turtle.forward((int) distance);
        }
    }

    /**
     * Main method.
     * <p>
     * This is the method that runs when you run "java TurtleSoup".
     *
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        drawPersonalArt(turtle);

        // draw the window
        turtle.draw();
    }

}
