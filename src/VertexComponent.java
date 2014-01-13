import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.lang.ref.WeakReference;
import java.awt.Graphics2D;

public class VertexComponent extends JComponent implements MouseMotionListener
{
    private boolean dragging = false;
    private Point lastPoint;
    private String vertex;
    private JLabel label;
    private WeakReference<VertexComponentDelegate> delegate;
    private static final int MARGIN = 5;
    private static final int ROUNDRADIUS = 15;
    private static final int LIGHT_BLUE = 0x72b2f7;

    public VertexComponent(String vertex)
    {
        this.addMouseMotionListener(this);
        this.vertex = vertex;

        this.label = new JLabel(this.vertex);
        this.label.setForeground(Color.WHITE);
        this.add(this.label);
        Dimension size = this.label.getPreferredSize();
        this.label.setBounds(VertexComponent.MARGIN, VertexComponent.MARGIN,
        size.width, size.height);

        this.revalidate();
        this.repaint();
        this.setVisible(true);
        this.setBackground(Color.RED);
        this.setLayout(null);
        this.setEnabled(true);
    }

//
// @category public methods
//

    public void setDelegate(VertexComponentDelegate delegateObject)
    {
        this.delegate = new WeakReference<VertexComponentDelegate>(delegateObject);
    }

    public VertexComponentDelegate getDelegate()
    {
        if(this.delegate != null)
        {
            VertexComponentDelegate delegateObject = this.delegate.get();
            if(delegateObject != null)
            {
                return delegateObject;
            }
            else
            {
                // the weak reference has been released. So forget the WeakReference object
                this.delegate = null;
            }
        }

        return null;
    }
//
// @category subclassing JComponent
//

    public Dimension getPreferredSize()
    {
        Dimension size = this.label.getPreferredSize();
        size.width += 2 * VertexComponent.MARGIN;
        size.height += 2 * VertexComponent.MARGIN;
        return size;
    }

    public void paint(Graphics g)
    {
        Graphics2D g2 =(Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(VertexComponent.LIGHT_BLUE));
        g.fillRoundRect(
            0,
            0,
            this.getWidth(),
            this.getHeight(),
            VertexComponent.ROUNDRADIUS,
            VertexComponent.ROUNDRADIUS);
        super.paint(g);
    }
//
// @category MouseListener Methods
//

    public void mouseDragged(MouseEvent e)
    {
        if(this.lastPoint == null)
        {
            this.lastPoint = e.getPoint();
        }
        else
        {
            Point delta = new Point(
                            (int)(e.getX() - this.lastPoint.getX()),
                            (int)(e.getY() - this.lastPoint.getY()));
            Point newLocation = this.getLocation();
            newLocation.translate((int)delta.getX(), (int)delta.getY());
            this.setBounds(
                (int)newLocation.getX(),
                (int)newLocation.getY(),
                this.getWidth(),
                this.getHeight());

            VertexComponentDelegate delegateObject = this.getDelegate();
            if(delegateObject != null)
            {
                delegateObject.vertexMoved(this);
            }
        }
    }

    public void mouseMoved(MouseEvent e)
    {

    }
}
