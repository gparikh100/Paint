import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class CanvasListener extends MouseInputAdapter {

	public static boolean isDrawing;

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
		isDrawing = true;
		if (Mode.getMode().equals("e") || Mode.getMode().equals("l") || Mode.getMode().equals("r") || Mode.getMode().equals("o") || Mode.getMode().equals("p")) {
			PointManager.getPoints().add(new Point((int) e.getPoint().getX(), (int) e.getPoint().getY()));
		}
		if (Mode.getMode().equals("pf")) {
			PointManager.getPoints().add(new Point((int) e.getPoint().getX(), (int) e.getPoint().getY()));
//			BufferedImage tempSave = new BufferedImage(PaintRunner.canvas.getWidth(), PaintRunner.canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
//			Graphics tempG = tempSave.getGraphics();
//			PaintRunner.canvas.paint(tempG);
//			try {
//				ImageIO.write(tempSave, "png", new File("./Images/tempCanvas.png"));
//			} 
//			catch (IOException e1) {
//
//			}
			Mode.setFillMode();
			PaintRunner.canvas.paintImmediately(0, 0, PaintRunner.CANVAS_SIZE_X, PaintRunner.CANVAS_SIZE_Y);
			PointManager.getPoints().clear();
			Mode.setPreFillMode();
		}
		if (Mode.getMode().equals("s")) {
			Sprayer.setRadius(Thickness.getThickness());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isDrawing = false;
		if (Mode.getMode().equals("e") || Mode.getMode().equals("l") || Mode.getMode().equals("r") || Mode.getMode().equals("o")) {
			PointManager.getPoints().add(new Point((int)e.getPoint().getX(), (int)e.getPoint().getY()));
		}
		if (Mode.getMode().equals("p")) {
			PaintTools.foundColor = false;
		}
		if (!((Mode.getMode().equals("e")) || (Mode.getMode().equals("p")) || (Mode.getMode().equals("f")) || (Mode.getMode().equals("s"))) && PointManager.getPoints().size() > 1) {
			if (!(PointManager.getPoints().get(0).equals(PointManager.getPoints().get(1)))) {
				PointManager.addToLib(PointManager.getPoints());
			}
		}
		if (Mode.getMode().equals("s")) {
			PointManager.addToLib(Sprayer.spraySet);
			Sprayer.clearSpraySet();
		}
		if (!PointManager.getPoints().isEmpty()) {
			PaintRunner.canvas.paintImmediately(0, 0, PaintRunner.CANVAS_SIZE_X, PaintRunner.CANVAS_SIZE_Y);
			PointManager.getPoints().clear();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		isDrawing = true;
		if (Mode.getMode().equals("d")) {
			PointManager.getPoints().add(new Point((int)e.getPoint().getX(), (int)e.getPoint().getY()));
			PointManager.boundsManager();
			PointManager.removeDuplicates();
			if (PointManager.getPoints().size() > 1) {
				PaintRunner.canvas.paintImmediately(0, 0, PaintRunner.CANVAS_SIZE_X, PaintRunner.CANVAS_SIZE_Y);
			}
		}
		if (Mode.getMode().equals("e") || Mode.getMode().equals("p")) {
			PointManager.getPoints().add(new Point((int)e.getPoint().getX(), (int)e.getPoint().getY()));
			if (!PointManager.getPoints().get(0).equals(PointManager.getPoints().get(1))) {
				PaintRunner.canvas.paintImmediately(0, 0, PaintRunner.CANVAS_SIZE_X, PaintRunner.CANVAS_SIZE_Y);
			}
			PointManager.getPoints().remove(0);
		}
		if (Mode.getMode().equals("l") || Mode.getMode().equals("r") || Mode.getMode().equals("o") || Mode.getMode().equals("s")) {
			PointManager.getPoints().add(new Point((int)e.getPoint().getX(), (int)e.getPoint().getY()));
			PaintRunner.canvas.paintImmediately(0, 0, PaintRunner.CANVAS_SIZE_X, PaintRunner.CANVAS_SIZE_Y);
			PointManager.getPoints().remove(PointManager.getPoints().size() - 1);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	public static boolean isDrawing() {
		return isDrawing;
	}
	
}
