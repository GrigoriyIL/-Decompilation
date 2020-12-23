import java.awt.geom.Point2D;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.Point;
import java.awt.Paint;
import java.awt.BasicStroke;
import java.awt.Shape;

// 
// Decompiled by Procyon v0.5.36
// 

public class ShapeFactory
{
    public Shape shape;
    public BasicStroke stroke = new BasicStroke(3.0f);
    public Paint paint;
    public int width = 25;
    public int height = 25;
	
		enum shape_forma {
        hexahedron,
        star,
        quadrate,
        triangle,
        pakman
    }
	
		enum shape_prop {
        three_pixels,
        zero,
        seven_pixels,
        gradient,
        red
    }

	/**
	*ShapeFactory Shape creation function
	*/
    public ShapeFactory( final shape_forma shape_type, final shape_prop shape_param) {
        switch (shape_type) {
            case hexahedron: {
                this.shape = createStar(3, new Point(0, 0), this.width / 2.0, this.width / 2.0);
                break;
            }
            case star: {
                this.shape = createStar(5, new Point(0, 0), this.width / 2.0, this.width / 4.0);
                break;
            }
            case quadrate: {
                this.shape = new Rectangle2D.Double(-this.width / 2.0, -this.height / 2.0, this.width, this.height);
                break;
            }
            case triangle: {
                final GeneralPath path = new GeneralPath();
                final double tmp_height = Math.sqrt(2.0) / 2.0 * this.height;
                path.moveTo(-this.width / 2, -tmp_height);
                path.lineTo(0.0, -tmp_height);
                path.lineTo(this.width / 2, tmp_height);
                path.closePath();
                this.shape = path;
                break;
            }
            case pakman: {
                this.shape = new Arc2D.Double(-this.width / 2.0, -this.height / 2.0, this.width, this.height, 30.0, 300.0, 2);
                break;
            }
            default: {
                throw new Error("type is nusupported");
            }
        }
        switch (shape_param) {
            case three_pixels: {
                this.stroke = new BasicStroke(3.0f);
                break;
            }
            case zero: {
                break;
            }
            case seven_pixels: {
                this.stroke = new BasicStroke(7.0f);
                break;
            }
            case gradient: {
                this.paint = new GradientPaint((float)(-this.width), (float)(-this.height), Color.white, (float)this.width, (float)this.height, Color.gray, true);
                break;
            }
			/**
			*case 8 This parameter describes the color of the shapes
			*/
            case red: {
                this.paint = Color.red;
                break;
            }
            default: {
                throw new Error("type is nusupported");
            }
        }
    }
    
    private static Shape createStar(final int arms, final Point center, final double rOuter, final double rInner) {
        final double angle = 3.141592653589793 / arms;
        final GeneralPath path = new GeneralPath();
        for (int i = 0; i < 2 * arms; ++i) {
            final double r = ((i & 0x1) == 0x0) ? rOuter : rInner;
            final Point2D.Double p = new Point2D.Double(center.x + Math.cos(i * angle) * r, center.y + Math.sin(i * angle) * r);
            if (i == 0) {
                path.moveTo(p.getX(), p.getY());
            }
            else {
                path.lineTo(p.getX(), p.getY());
            }
        }
        path.closePath();
        return path;
    }
}
