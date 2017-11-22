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
 * @modified - 11/23/2015 by team 2 for group project 3, added the draw method for Polygons
 *             and ovals.
 * 
 */
/**
 * A given technology can implement this to draw the items in 
 * its own way.
 *
 */
public interface UIContext {
    //  public abstract void drawCircle(Circle circle);
    /**
     * Draw the line
     * @param line the line
     */
    public abstract void draw(Line line);
    /**
     * Draw the label
     * @param label the label
     */
    public abstract void draw(Label label);
    
    /**
     * Draw the polygon
     * @param polygon the polygon
     */
    public abstract void draw(Polygon polygon);
    
    
    /**
     * Draw the oval
     * @param oval the oval
     */
    public abstract void draw(Oval oval);
    
    
    /**
     * Draws unspecified items
     * @param item the item
     */
    public abstract void draw(Item item);
    
}
