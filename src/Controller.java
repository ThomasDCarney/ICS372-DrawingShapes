/**
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 * 
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
 * @modified - 11/23/2015 by team 2 for group project 3, added instance variable polygon and
 *             oval. Added methods makePolygon, makeOval, addPolygonVertex setOvalPoint, 
 *             getItem and moveItem. 
 */
import java.awt.Point;
import java.util.*;
/*
 * The controller orchestrates the drawing program. It receives
 * requests from the user via the view and then transmits them appropriately
 * to the model.
 * 
 */
public class Controller {
  private static Model model;
  private Line line;
  private Label label;
  private Polygon polygon;
  private Oval oval;
  private static Controller controller;
  private int pointCount;
  
  /**
   * For singleton
   */
  private Controller() {
  }
  /**
   * Returns the instance of the controller
   * @return the instance
   */
  public static Controller instance() {
    if (controller == null) {
      controller = new Controller();
    }
    return controller;
  }
  /**
   * Sets the reference to the model
   * @param model the model
   */
  public static void setModel(Model model) {
    Controller.model = model;
  }
  /**
   * Constructs a line and sends the info to the model.
   * 
   */
  public void makeLine() {
    line = new Line();
    pointCount = 0;
    model.addItem(line);
  }
  
  
    /**
     * Constructs a new Polygon for polygon and adds a reference to the model. 
     */
    public void makePolygon() {
      
        polygon = new Polygon();
        model.addItem(polygon);
  
    } // end makePolygon
    
    
    /**
     * Constructs a new Oval for polygon and adds a reference to the model. 
     */
    public void makeOval() {
        
        oval = new Oval();
        pointCount = 0;
        model.addItem(oval);
        
    } // end makeOval
  
  
  /**
   * Stores one of the line endpoints.
   * @param point one of the two points
   */
  public void setLinePoint(Point point) {
    if (++pointCount == 1) {
      line.setPoint1(point);
    } else  {
      line.setPoint2(point);
    }
    model.updateView();
  }
  
  
  /** 
   * Stores the next vertex for polygon.
   * 
   * @param point - Another vertex in a series of 3+ vertices.
   */
  public void addPolygonVertex(Point point) {
      
      polygon.addVertex(point);
      model.updateView();
      
  } // end setPolygonPoint
  
  
    /**
     * Used to store two corners of the Oval's bounding box.
     * 
     * @param point one of the two corners
     */
    public void setOvalPoint(Point point) {
      
        if (++pointCount == 1) {
            
            oval.setCorner1(point);
        
        } else  {
            
            oval.setCorner2(point);
            
        }
        
        model.updateView();
      
    } // end setOvalPoint
  
  
  /**
   * Creates a label and informs the model.
   * @param point the start point
   */
  public void makeLabel(Point point) {
    label = new Label(point);
    model.addItem(label);
  }
  /**
   * Receives a character and accumulates it.
   * The model is asked to update the view.
   * @param character the typed in character
   */
  public void addCharacter(char character) {
    label.addCharacter(character);
    model.updateView();
  }
  /**
   * A command to remove a character. The model
   * will then update the view.
   * 
   */
  public void removeCharacter() {
    label.removeCharacter();
    model.updateView();
  }
  /**
   * Given a point, see if any of the items contains it.
   * @param point the point
   */
  public void selectItem(Point point) {
    Enumeration enumeration = model.getItems();
    while (enumeration.hasMoreElements()) {
      Item item = (Item)(enumeration.nextElement());
      if (item.includes(point)) {
        model.markSelected(item);
        break;
      }
    }
  }
  
  
    /**
     * This method is used to retrieve an item from the model.
     * 
     * @param point - The point which the found item must include.
     * 
     * @return - The item that includes that point.
     */
    public Item getItem(Point point) {
        
        Enumeration enumeration = model.getItems();
        while (enumeration.hasMoreElements()) {
        
            Item item = (Item)(enumeration.nextElement());
            if (item.includes(point)) {
            
            return item;
          
            }
        }
      
        return null;
      
    } // end getItem
  
    
    /**
     * This method is used to move an item from one location to another.
     * The item, if found, will be moved the distance between the two provided
     * points.
     * 
     * @param fromPoint - A starting point which must be part of the item.
     * 
     * @param toPoint - Another point where the item is to be moved.
     */
    public void moveItem(Point fromPoint, Point toPoint) {
      
        Item item = getItem(fromPoint);
        double xDif, yDif;
      
        if(item != null) {
          
            xDif = toPoint.getX() - fromPoint.getX();
            yDif = toPoint.getY() - fromPoint.getY();
            item.translateItem(xDif, yDif);
            model.updateView();
          
        }
      
    } // end moveItem
  
  
  /**
   * Processes the command to delete the selected items.
   */
  public  void deleteItems() {
    model.deleteSelectedItems();
  }
  /**
   * Processes the command to open a file
   * @param fileName the name of the file
   */
  public void openFile(String fileName) {
    model.retrieve(fileName);
  }
  /**
   * 
   * Processes the command to close a file
   * @param fileName the name of the file
   */
  public void saveFile(String fileName) {
    model.save(fileName);
  }


}
