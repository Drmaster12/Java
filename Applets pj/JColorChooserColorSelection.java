import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionListener;

public abstract class JColorChooserColorSelection  {
    private JColorChooser jcc = null;
    private JLabel label = null;

    public JColorChooserColorSelection() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        jcc = new JColorChooser();
        jcc.getSelectionModel().addChangeListener(new ChangeListener());
        getContentPane().add(jcc, BorderLayout.PAGE_START);

        label = new JLabel("Selected Font Color", JLabel.EAST);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(100, 100));
        getContentPane().add(label, BorderLayout.EAST);
        this.pack();
    }
    /**
     * A ChangeListener implementation for listening the color
     * selection of the JColorChooser component.
     */
    class ColorSelection implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            Color color = jcc.getColor();
            label.setForeground(color);
        }
    }

    private void pack() {
		// TODO Auto-generated method stub
		
	}

	private void setLayout(BorderLayout borderLayout) {
		// TODO Auto-generated method stub
		
	}

	private Container getContentPane() {
		// TODO Auto-generated method stub
		return null;
	}

}


	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){  
            
        
	}
	

	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

private  void JColorChooser()  {
}
	
public abstract JLabel location();

public void JColorChooser(JLabel location) {


    setLayout( new FlowLayout() );
    location = new JLabel();
    add( location);
    addMouseListener(this);       
    addMouseListener(this);
    JButton colorButton = new JButton("Choose Pen Color");
    colorButton.addActionListener((ActionListener) this);
    add(colorButton);
    add(colorButton);

  }
private void add(JLabel location2) {
	// TODO Auto-generated method stub
	
}
private void setLayout(FlowLayout flowLayout) {
	// TODO Auto-generated method stub
	
}
private void add(JButton colorButton) {
	// TODO Auto-generated method stub
	
}
private void addMouseListener(JColorChooserColorSelection jColorChooserColorSelection) {
	// TODO Auto-generated method stub
	
}
}
