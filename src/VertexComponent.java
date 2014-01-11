import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.JPanel;

public class VertexComponent extends JComponent implements MouseListener
{
    private boolean dragging = false;
    private Point pressedPoint;
    private String vertex;
    private JLabel label;

    public VertexComponent(String vertex)
    {
        this.addMouseListener(this);
        this.vertex = vertex;
        this.setSize(200, 200);
        this.label = new JLabel(this.vertex);
        this.add(this.label);
    }

//
// @category MouseListener Methods
//

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        this.dragging = true;
        this.pressedPoint = e.getPoint();
    }

    public void mouseReleased(MouseEvent e)
    {
        if(!this.dragging)
        {
            this.dragging = false;

            Point delta = new Point((int)(e.getX() - this.pressedPoint.getX()), (int)(e.getY() - this.pressedPoint.getY()));
            Point newLocation = this.getLocation();
            newLocation.translate((int)delta.getX(), (int)delta.getY());
            this.setLocation(newLocation);
        }
    }
}
