/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #3, A simple drawing program. <br>
 * Due: 12/04/2015 <br><br>
 * 
 * This is a button displayed by the GUI and used to move an object about. This is a JButton
 * and also extends ActionListener as the actionPerformed method is part of the class. There is 
 * also an inside class used to process mouse clicks/motion once the button is pushed.
 * 
 * @author Jacob Mathias, Ghislain Ndike, Andrea Deerberg, Tom Carney
 * @version 1.0
 * @since 11/23/2015
 */

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MoveButton  extends JButton implements ActionListener {

    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private MouseHandler motionHandler;
    private Point startPoint;
    
    
    /**
     * Creates the button.
     * 
     * @param jFrame - Frame where the button lives.
     * 
     * @param jPanel - The panel where the items are.
     */
    public MoveButton(View jFrame, JPanel jPanel) {
        
        super("Move");
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
    
    } // end MoveButton constructor
    
    
    /**
     * Handles what happens when the button is pushed.
     * 
     * @param - Event generated when the button is pushed.
     */
    public void actionPerformed(ActionEvent event) {
        
        drawingPanel.addMouseListener(mouseHandler = new MouseHandler());
        drawingPanel.addMouseMotionListener(motionHandler = new MouseHandler());
        
    } // end actionPerformed
    
    
    /**
     * Handles mouse clicks and drags.
     */
    private class MouseHandler extends MouseAdapter {
        
        
        /**
         * This method handles what happens when the mouse button is pressed.
         */
        @Override
        public void mousePressed(MouseEvent event) {
            
            // Grab the mouse's location when initially pressed.
            startPoint = event.getPoint();
            
        } // end mousePressed
        
        
        /**
         * This method handles what happens when the mouse button is dragged while
         * in the pressed state.
         */
        @Override
        public void mouseDragged(MouseEvent event) {
            
            Controller.instance().moveItem(startPoint, event.getPoint());
            
            // Since the drag event occurs repetatively while the mouse remains pressed.
            // Each time it should move from the current/most recent, not original location. 
            startPoint = event.getPoint(); 
            
        } // end mouseDragged
        
        
        /**
         * This method handles what happens when the mouse button is released after
         * being pressed and possibly dragged.
         */
        @Override
        public void mouseReleased(MouseEvent event) {
            
            // We are done moving the item so remove the listeners to all for next action.
            drawingPanel.removeMouseMotionListener(motionHandler);
            drawingPanel.removeMouseListener(mouseHandler);
            
        } // end mouseReleased
        
    } // end MouseHandler

} // end MoveButton
