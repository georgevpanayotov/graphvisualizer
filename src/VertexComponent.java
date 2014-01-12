import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class VertexComponent extends JComponent implements MouseMotionListener
{
    private boolean dragging = false;
    private Point lastPoint;
    private String vertex;
    private JLabel label;

    public VertexComponent(String vertex)
    {
        this.addMouseMotionListener(this);
        this.vertex = vertex;

        this.label = new JLabel(this.vertex);
        this.add(this.label);
        Dimension size = this.label.getPreferredSize();
        this.label.setBounds(0, 0,
        size.width, size.height);

        this.revalidate();
        this.repaint();
        this.setVisible(true);
        this.setBackground(Color.RED);
        this.setLayout(null);
        this.setEnabled(true);
    }

//
// @category subclassing JComponent
//

    public Dimension getPreferredSize()
    {
        return this.label.getPreferredSize();
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
            Point delta = new Point((int)(e.getX() - this.lastPoint.getX()), (int)(e.getY() - this.lastPoint.getY()));
            Point newLocation = this.getLocation();
            newLocation.translate((int)delta.getX(), (int)delta.getY());
            this.setBounds((int)newLocation.getX(), (int)newLocation.getY(), this.getWidth(), this.getHeight());
        }
    }

    public void mouseMoved(MouseEvent e)
    {

    }

}
