package coreservlets;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

/** Simple JList example illustrating
 *  <ul>
 *    <li>Creating a JList, which we do by passing values 
 *        directly to the JList constructor, rather than 
 *        using a ListModel, and
 *    <li>Attaching a listener to determine when values change.
 *  </ul>
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

// Code runs only in Java 7 or later, since JList started supporting generics in 1.7.

public class JListSimpleExample extends JFrame {
  public static void main(String[] args) {
    new JListSimpleExample();
  }

  private JList<String> sampleJList;
  private JTextField valueField;
  
  public JListSimpleExample() {
    super("Creating a Simple JList");
    WindowUtilities.setNimbusLookAndFeel();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Container content = getContentPane();

    // Create the JList, set the number of visible rows, add a
    // listener, and put it in a JScrollPane.
    String[] entries = { "Entry 1", "Entry 2", "Entry 3",
                         "Entry 4", "Entry 5", "Entry 6" };
    sampleJList = new JList<>(entries); 
    sampleJList.setVisibleRowCount(4);
    sampleJList.addListSelectionListener(new ValueReporter());
    JScrollPane listPane = new JScrollPane(sampleJList);
    Font displayFont = new Font("Serif", Font.BOLD, 18);
    sampleJList.setFont(displayFont);

    JPanel listPanel = new JPanel();
    listPanel.setBackground(Color.white);
    Border listPanelBorder =
      BorderFactory.createTitledBorder("Sample JList");
    listPanel.setBorder(listPanelBorder);
    listPanel.add(listPane);
    content.add(listPanel, BorderLayout.CENTER);
    JLabel valueLabel = new JLabel("Last Selection:");
    valueLabel.setFont(displayFont);
    valueField = new JTextField("None", 7);
    valueField.setFont(displayFont);
    valueField.setEditable(false);
    JPanel valuePanel = new JPanel();
    valuePanel.setBackground(Color.white);
    Border valuePanelBorder =
      BorderFactory.createTitledBorder("JList Selection");
    valuePanel.setBorder(valuePanelBorder);
    valuePanel.add(valueLabel);
    valuePanel.add(valueField);
    content.add(valuePanel, BorderLayout.SOUTH);
    pack();
    setVisible(true);
  }

  private class ValueReporter implements ListSelectionListener {

    /** You get three events in many cases -- one for the 
     *  deselection of the originally selected entry, one 
     *  indicating the selection is moving, and one for the 
     *  selection of the new entry. In the first two cases, 
     *  getValueIsAdjusting returns true; thus, the test below
     *  when only the third case is of interest.
     */

    @Override
    public void valueChanged(ListSelectionEvent event) {
      if (!event.getValueIsAdjusting()) {
        String value = sampleJList.getSelectedValue();
        if (value != null) {
          valueField.setText(value.toString());
        }  
      }
    }
  }
}