/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #3, A simple drawing program. <br>
 * Due: 12/04/2015 <br><br>
 * 
 * This is an extension of the Item class which is stored by the model and used to 
 * draw Ovals to the screen.
 * 
 * @author Jacob Mathias, Ghislain Ndike, Andrea Deerberg, Tom Carney
 * @version 1.0
 * @since 11/23/2015
 */

import java.awt.Point;
import java.util.Iterator;

public class Oval extends Item {

    private Point corner1;
    private Point corner2;
    
    /**
     * Sets corner 1 of the oval.
     * 
     * @param point - The first corner of the oval.
     */
    public void setCorner1(Point point) {
        
        corner1 = point;
        
    } // end setCorner1
    
    
    /**
     * Sets corner 2 of the oval.
     * 
     * @param point - The second corner of the oval.
     */
    public void setCorner2(Point point) {
        
        corner2 = point;
        
    } // end setCorner1
    
    
    /**
     * this method returns the oval's first corner.
     * 
     * @return - A Point object representing the oval's first corner.
     */
    public Point getCorner1() {
        
        return corner1;
        
    } // end getCorner1
    
    
    /**
     * this method returns the oval's second corner.
     * 
     * @return - A Point object representing the oval's second corner.
     */
    public Point getCorner2() {
        
        return corner2;
        
    } // end getCorner2
    
    
    /**
     * This method is used to determine whether the oval in question should be
     * included based on the location of the mouse click. 
     * 
     * @return - Clicking within 10 pixels of ??? will return true, else false. 
     */
    @Override
    public boolean includes(Point point) {
    
        // Get center of circle
        double centerX = ((corner1.x + corner2.x) / 2);
        double centerY = ((corner1.y + corner2.y) / 2);
        
        // Get the major and minor axis (horizontal and vertical radius)
        double majorAxis = centerX - ((corner1.x < corner2.x) ? corner1.x : corner2.x);
        double minorAxis = centerY - ((corner1.y < corner2.y) ? corner1.y : corner2.y);
        
        // Convert clicked point to grid system with circle at (0,0)
        double clickedX = point.x - centerX;
        double clickedY = point.y - centerY;
        
        // Check to see if clicked area is within circle radius and return true if it is
        if ( Math.pow(clickedX / majorAxis, 2) + Math.pow(clickedY / minorAxis, 2) <= 1.2) {
            return true;
        }
        else {
            return false;
        }
    
    } // end includes
    
    
    /**
     * Displays the oval.
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
        
        corner1.translate((int)xDif, (int)yDif);
        corner2.translate((int)xDif, (int)yDif);
        
    } // end translateItem
    

} // end Oval