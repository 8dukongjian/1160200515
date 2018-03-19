/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.text.DecimalFormat; 

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	double degree  =(double)(sides-2)*180/sides; 
        DecimalFormat f = new DecimalFormat("#.00");  
        return Double.parseDouble(f.format(degree));
          
    }


	/**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
         double a = Math.ceil(360/(180-angle));
    	 return (int)a;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	for(int i=1;i<=sides;i++)
    	{
    		   	turtle.forward(sideLength);
    	        turtle.turn(180-calculateRegularPolygonAngle(sides));
    	}
 
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
            int targetX, int targetY) {
           DecimalFormat f = new DecimalFormat("#.0");
           currentHeading = Double.parseDouble(f.format(currentHeading));
           int a = targetX - currentX;
           int b = targetY - currentY;
           int c = currentX - targetX;
           int d = currentY - targetY;
           if(currentX<=targetX&&currentY<targetY)
           {
              if(currentX==targetX)
              {
                  return (360.0-currentHeading)%360;
              }
              double e = Math.atan(a/b)/Math.PI*180.0;
              e= Double.parseDouble(f.format(e));
              if(e>=currentHeading)
                   return e-currentHeading;
              else
                   return 360.0 - (currentHeading-e);
           }
           else if(currentX<targetX&&currentY>=targetY)
	           {
	        	   if(currentY==targetY)
	        	   {
	        		   return 90.0-currentHeading;
	        	   }
	        	   double e = Math.atan(d/a)/Math.PI*180.0;
	        	   e= Double.parseDouble(f.format(e));
	        	   return 90.0 - e + currentHeading;
	           }
           else if(currentX>=targetX&&currentY>targetY)
           {
        	   if(currentX==targetX)
        	   {
        		   return 90.0 + currentHeading;
        	   }
        	   double e = Math.atan((double)c/d)/Math.PI*180.0;
        	   e= Double.parseDouble(f.format(e));
        	   return 180.0-currentHeading + e ;

           }
           else
           {
        	   if(currentY==targetY)
        		   return 270.0-currentHeading;
        	   double e = Math.atan((double)c/b)/Math.PI*180.0;
        	   e= Double.parseDouble(f.format(e));
        	   return 360.0-(e+currentHeading);
           }
}



    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> a = new ArrayList<>();
        double temp = 0.0;
        for(int i=1;i<xCoords.size();i++)
        {
        	a.add(calculateHeadingToPoint(temp,xCoords.get(i-1),yCoords.get(i-1),xCoords.get(i),yCoords.get(i)));
        	temp = a.get(i-1);
        }
        return a;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	for(int x=1;x<180;x++)
    	{
    		turtle.color(PenColor.ORANGE);
    		turtle.forward(x)  ;      
    	    turtle.turn(90) ;
    	}

    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
       //drawSquare(turtle, 40);
        // draw the window
        //drawRegularPolygon(turtle,8,40);
        drawPersonalArt(turtle);
        turtle.draw();
       
    }

}
