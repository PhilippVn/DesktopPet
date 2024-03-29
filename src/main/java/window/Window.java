package window;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window {
    private final int width;
    private final int height;
    private JFrame jframe;
    private boolean wasClicked;

    public Window(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public JFrame getJFrame() {
        return jframe;
    }

    public boolean wasClicked() {
        return wasClicked;
    }

    public void resetClicked(){
        wasClicked = false;
    }

    public void init(){
            jframe = new JFrame("Desktop Pet");
            jframe.setUndecorated(true);
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setSize(width,height);
            jframe.setAlwaysOnTop( true );
            jframe.setBackground(new Color(0, 0, 0, 0));
            jframe.setFocusableWindowState(false);

            Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            int taskBarHeight = scrnSize.height - winSize.height;

            jframe.setLocation(0,scrnSize.height -  taskBarHeight - jframe.getHeight());

            FrameDragListener frameDragListener = new FrameDragListener(jframe);
            jframe.addMouseListener(frameDragListener);
            jframe.addMouseListener(new WindowClickedListener());
            jframe.addMouseMotionListener(frameDragListener);
            jframe.setVisible(true);
    }

    private class WindowClickedListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            Window.this.wasClicked = true;
        }
    }

    // source: https://stackoverflow.com/questions/16046824/making-a-java-swing-frame-movable-and-setundecorated
    private static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }

}
