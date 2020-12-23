import java.awt.Insets;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.Stroke;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.36
// 

public class TitlesPanel extends JPanel implements ActionListener
{
    private Graphics2D g2d;
    private Timer animation;
    private boolean is_done = true;
    private int start_angle = 0;
    private ShapeFactory.shape_forma shape_forma;
    private ShapeFactory.shape_prop shape_prop;
    
    public TitlesPanel(ShapeFactory.shape_forma _shape_forma, ShapeFactory.shape_prop _shape_prop) {
        this.shape_forma = _shape_forma;
        this.shape_prop = _shape_prop;
        this.animation = new Timer(50, this);
        this.animation.setInitialDelay(50);
        this.animation.start();
    }
    /**
	*@Override check if the figure is drawn
	*/ 
    @Override
    public void actionPerformed(final ActionEvent arg0) {
        if (this.is_done) {
            this.repaint();
        }
    }
    /**
	*doDrawing Method for turning shapes
	*/
    private void doDrawing(Graphics g) {
        this.is_done = false;
        this.g2d = (Graphics2D)g;
        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension size = this.getSize();
        Insets insets = this.getInsets();
        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;
        ShapeFactory shape = new ShapeFactory(this.shape_forma, this.shape_prop);
        this.g2d.setStroke(shape.stroke);
        this.g2d.setPaint(shape.paint);
        double angle = this.start_angle++;
        if (this.start_angle > 360) {
            this.start_angle = 0;
        }
		
		double dr = 90.0 / ((double)w / ((double)shape.width * 1.5));
        int j = shape.height;
		while (j < h) {
			int i = shape.width;
			while (i < w) {
				angle = angle > 360.0 ? 0.0 : angle + dr;
				AffineTransform transform = new AffineTransform();
				transform.translate(i, j);
				transform.rotate(Math.toRadians(angle));
				this.g2d.draw(transform.createTransformedShape(shape.shape));
				i = (int)((double)i + (double)shape.width * 1.5);
			}
			j = (int)((double)j + (double)shape.height * 1.5);
			}
			this.is_done = true;
		}
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.doDrawing(g);
    }
}
