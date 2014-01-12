import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

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
        if(this.dragging)
        {
            this.dragging = false;

            Point delta = new Point((int)(e.getX() - this.pressedPoint.getX()), (int)(e.getY() - this.pressedPoint.getY()));
            Point newLocation = this.getLocation();
            newLocation.translate((int)delta.getX(), (int)delta.getY());
            this.setBounds((int)newLocation.getX(), (int)newLocation.getY(), this.getWidth(), this.getHeight());
        }
    }
}
