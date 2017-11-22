/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 * 
 * @modified - 11/23/2015 by team 2 for group project 3, added the draw methods for Polygons
 *             and ovals. 
 */

import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;


/**
 * A UI that uses the swing package
 *
 */
public class NewSwingUI implements UIContext {
  private Graphics graphics;
  private static NewSwingUI swingUI;
  /**
   * For the singleton pattern
   */
  private NewSwingUI() {
  }
  /**
   * Returns the instance
   * @return the instance
   */
  public static NewSwingUI getInstance() {
    if (swingUI == null) {
      swingUI = new NewSwingUI();
    }
    return swingUI;
  }
  /**
   * The Graphics object for drawing
   * @param graphics the Graphics object
   */
  public  void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }
  /**
   * Draws a label
   * @param label the label
   */
  public void draw(Label label) {
    if (label.getStartingPoint() != null) {
      if (label.getText() != null) {
        graphics.drawString(label.getText(), (int) label.getStartingPoint().getX(), (int) label.getStartingPoint().getY());
      }
    }
    int length = graphics.getFontMetrics().stringWidth(label.getText());
    graphics.drawString("_", (int) label.getStartingPoint().getX() + length, (int) label.getStartingPoint().getY());
  }
  /**
   * Draws a line
   * @param line the line to be drawn
   */
  public void draw(Line line) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (line.getPoint1() != null) {
      i1 = Math.round((float) (line.getPoint1().getX()));
      i2 = Math.round((float) (line.getPoint1().getY()));
      if (line.getPoint2() != null) {
        i3 = Math.round((float) (line.getPoint2().getX()));
        i4 = Math.round((float) (line.getPoint2().getY()));
      } else {
        i3 = i1;
        i4 = i2;
      }
      graphics.drawLine(i1, i2, i3, i4);
    }
  }

  
  /**
   * Draws a polygon.
   * 
   * @param - The polygon to be drawn.
   */
  public void draw(Polygon polygon) {

      Iterator<Point> iterator = polygon.getPoints();
      Point firstVertex = null; // need to hold onto for the duration.
      Point vertex1 = null; 
      Point vertex2 = null;
      
      if(iterator.hasNext()) { // check for any vertices else do nothing
          
          firstVertex = iterator.next();
          vertex2 = firstVertex;
          
          // So you can see a dot where the first (possibly only) vertex is.
          graphics.drawLine((int)firstVertex.getX(), (int)firstVertex.getY(), 
                              (int)firstVertex.getX(), (int)firstVertex.getY()); 
          
          // This will cycle through the rest, drawing lines from vertex1 to 2.
          while(iterator.hasNext()) {
              
              vertex1 = vertex2;          
              vertex2 = iterator.next();
              graphics.drawLine((int)vertex1.getX(), (int)vertex1.getY(), 
                                  (int)vertex2.getX(), (int)vertex2.getY());
          
          }
          
          // This will draw a line from last vertex back to the first
          graphics.drawLine((int)vertex2.getX(), (int)vertex2.getY(), 
                              (int)firstVertex.getX(), (int)firstVertex.getY());
          
      } // end if block
      
  } // end draw(Polygon)
  
  
  /**
   * Draws an oval.
   * 
   * @param - The oval to be drawn.
   */
  public void draw(Oval oval) {

      if(oval.getCorner1() != null && oval.getCorner2() != null) {
       
          int x1 = (int)oval.getCorner1().getX();
          int y1 = (int)oval.getCorner1().getY();
          int x2 = (int)oval.getCorner2().getX();
          int y2 = (int)oval.getCorner2().getY();
          int width = Math.abs(x1 - x2);
          int height = Math.abs(y1 - y2);
          
          // Need to start in the upper left corner (the lower of the two values).
          int x = (x1 < x2 ? x1 : x2);
          int y = (y1 < y2 ? y1 : y2);
             
          graphics.drawOval(x, y, width, height);
           
      }
      
  } // end draw(Oval)
  
  
  /**
   * Captures undefined items
   * @param item the item
   */
  public void draw(Item item) {
    System.out.println( "Cant draw unknown Item \n");
  }


}