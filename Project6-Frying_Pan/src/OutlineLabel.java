import java.awt.*;
import javax.swing.*;

public class OutlineLabel extends JLabel {

    private Color outlineColor = Color.BLACK;
    private boolean isPaintingOutline = false;
    private boolean forceTransparent = false;

    public OutlineLabel() {
        super();
    }

    public OutlineLabel(String text) {
        super(text);
    }

    public OutlineLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public Color getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
        this.invalidate();
    }

    @Override
    public Color getForeground() {
        if (isPaintingOutline) {
            return outlineColor;
        } else {
            return super.getForeground();
        }
    }

    @Override
    public boolean isOpaque() {
        if (forceTransparent) {
            return false;
        } else {
            return super.isOpaque();
        }
    }

    @Override
    public void paint(Graphics g) {

        String text = getText();
        if (text == null || text.length() == 0) {
            super.paint(g);
            return;
        }

        // 1 2 3
        // 8 9 4
        // 7 6 5

        if (isOpaque())
            super.paint(g);

        forceTransparent = true;
        isPaintingOutline = true;
        g.translate(-1, -1);
        super.paint(g); // 1
        g.translate(1, 0);
        super.paint(g); // 2
        g.translate(1, 0);
        super.paint(g); // 3
        g.translate(0, 1);
        super.paint(g); // 4
        g.translate(0, 1);
        super.paint(g); // 5
        g.translate(-1, 0);
        super.paint(g); // 6
        g.translate(-1, 0);
        super.paint(g); // 7
        g.translate(0, -1);
        super.paint(g); // 8
        g.translate(1, 0); // 9
        isPaintingOutline = false;

        super.paint(g);
        forceTransparent = false;

    }

    public static void main(String[] args) {
        JFrame w = new JFrame();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OutlineLabel label = new OutlineLabel("change the outline thickness", OutlineLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 48));
        label.setOutlineColor(Color.black);
        label.setForeground(Color.white);
        label.setOpaque(true);
        w.setContentPane(new JPanel(new BorderLayout()));
        w.add(label, BorderLayout.CENTER);
        w.pack();
        w.setVisible(true);
    }
}