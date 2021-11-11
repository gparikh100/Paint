import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.event.MouseInputAdapter;
import java.awt.*; 

public class ColorListener extends MouseInputAdapter {

    public static BufferedImage colorWheel;
    private static int x;
    private static int y;

    public static int getX(){
        return x;
    }
    
    public static int getY(){
        return y;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY() - PaintRunner.BAR_SIZE;
        Color c = CoordtoRGB();
        PointManager.setColor(c);
        ControlPanel.shiftColorPalet(c);
        ControlPanel.shiftSelectedColor(200, 50);
        PaintRunner.control.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
       
	}

	@Override
	public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY() - PaintRunner.BAR_SIZE;
        if (x >= 0 && x < ColorWheel.cWidth && y >= 0 && y < ColorWheel.cHeight) {
            Color c = CoordtoRGB();
            PointManager.setColor(c);
            ControlPanel.adjustColorPalet(c);
            ControlPanel.shiftSelectedColor(200, 50);
            PaintRunner.control.repaint();
        }
	}

	@Override
	public void mouseMoved(MouseEvent e) {
        
    }

    public Color CoordtoRGB() {
        try {
            colorWheel = ImageIO.read(getClass().getResource("colormap.png"));
        } 
        catch (IOException e) {

        }
        return new Color(colorWheel.getRGB(x,y));
    }
	
}
