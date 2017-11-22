/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #3, A simple drawing program. <br>
 * Due: 12/04/2015 <br><br>
 * 
 * This is an extension of the Item class which is stored by the model and used to 
 * draw polygons to the screen.
 * 
 * @author Jacob Mathias, Ghislain Ndike, Andrea Deerberg, Tom Carney
 * @version 1.0
 * @since 11/23/2015
 */

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

public class Polygon extends Item {
    
    // Will hold the vertices
    private LinkedList<Point> points;
    
    
    /**
     * This will create a new Polygon with no vertices.
     */
    public Polygon() {
        
        points = new LinkedList<Point>();
        
    } // end Polygon constructor
    
    
    /**
     * This will add a vertex to the Polygon.
     * 
     * @param point - The vertex to be added.
     */
    public void addVertex(Point point) {

        points.add(point);
        
    } // end setPoint
    
    
    /**
     * This method will return an iterator of type java.awt.Point in order to view all 
     * stored vertices.
     * 
     * @return - The iterator for the polygon
     */
    public Iterator<Point> getPoints() {
        
        return points.iterator();
        
    } // end getPoints
    
    
    /**
     * This method is used to determine whether the polygon in question should be
     * included based on the location of the mouse click. 
     * 
     * @return - Clicking within 10 pixels of any vertex will return true, else false. 
     */
    @Override
    public boolean includes(Point point) {
        
        Iterator<Point> iterator = points.iterator();
        Point first = iterator.next();
        Point currentPoint;
        Point nextPoint = first;
        
        while(iterator.hasNext()) {
            currentPoint = nextPoint;
            nextPoint = iterator.next();
            
            if(distance(currentPoint, point) + distance(nextPoint, point) <= (distance(currentPoint, nextPoint) + 10)) {
                
                return true;
                
            }
        }
        
        if(distance(first, point) + distance(nextPoint, point) <= (distance(first, nextPoint) + 10)) {
            
            return true;
            
        }
        
        return false;
        
    } // end includes
    
    
    /**
     * Displays the polygon.
     */
    public void render() {
        
        uiContext.draw(this);
    
    } // end Render


    /**
     * This method is used to shift the item by the provided values.
     * 
     * @param xDif - The value to shift in the x direction.
     * 
     * @param yDif - The value to shift in the y direction.
     */
    @Override
    public void translateItem(double xDif, double yDif) {
        
        Iterator<Point> iterator = points.iterator();
        
        while(iterator.hasNext()) {
            
            iterator.next().translate((int)xDif, (int)yDif);
            
        }
        
    } // end translateItem

    
} // end Polygon
