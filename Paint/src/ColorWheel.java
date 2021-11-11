import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ColorWheel extends JPanel {

    private static final long serialVersionUID = 1L;

    public static BufferedImage colorWheel;
    public static int cWidth = 0;
    public static int cHeight = 0;

    public ColorWheel() {
        super();
        try {
            colorWheel = ImageIO.read(getClass().getResource("colormap.png"));
            cWidth = colorWheel.getWidth();
            cHeight = colorWheel.getHeight();
        } 
        catch (IOException e) {

        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D paint = (Graphics2D) g;
        paint.drawImage(colorWheel, 0, 0, this);
    }





}
