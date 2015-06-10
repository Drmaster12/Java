package coreservlets;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/** JList example illustrating
 *  <UL>
 *    <LI>The creation of a JList by creating a DefaultListModel,
 *        adding the values there, then passing that to the
 *        JList constructor.
 *    <LI>Adding new values at runtime, the key thing that 
 *        DefaultListModel lets you do that you can't do with
 *        a JList where you supply values directly.
 *  </UL>
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class DefaultListModelExample extends JFrame {
  public static void main(String[] args) {
    new DefaultListModelExample();
  }

  JList<String> sampleJList;
  private DefaultListModel<String> sampleModel;
  
  public DefaultListModelExample() {
    super("Using DefaultListModel");
    WindowUtilities.setNimbusLookAndFeel();
    addWindowListener(new ExitListener());
    Container content = getContentPane();
   
    String[] entries = { "Entry 1", "Entry 2", "Entry 3",
                         "Entry 4", "Entry 5", "Entry 6" };
    sampleModel = new DefaultListModel<>();
    for(int i=0; i<entries.length; i++) {
      sampleModel.addElement(entries[i]);
    }
    sampleJList = new JList<>(sampleModel);
    sampleJList.setVisibleRowCount(4);
    Font displayFont = new Font("Serif", Font.BOLD, 18);
    sampleJList.setFont(displayFont);
    JScrollPane listPane = new JScrollPane(sampleJList);
    
    JPanel listPanel = new JPanel();
    listPanel.setBackground(Color.white);
    Border listPanelBorder =
      BorderFactory.createTitledBorder("Sample JList");
    listPanel.setBorder(listPanelBorder);
    listPanel.add(listPane);
    content.add(listPanel, BorderLayout.CENTER);
    JButton addButton =
      new JButton("Add Entry to Bottom of JList");
    addButton.setFont(displayFont);
    addButton.addActionListener(new ItemAdder());
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.white);
    Border buttonPanelBorder =
      BorderFactory.createTitledBorder("Adding Entries");
    buttonPanel.setBorder(buttonPanelBorder);
    buttonPanel.add(addButton);
    content.add(buttonPanel, BorderLayout.SOUTH);
    pack();
    setVisible(true);
  }

  private class ItemAdder implements ActionListener {
    
    /** Add an entry to the ListModel whenever the user
     *  presses the button. Note that since the new entries
     *  may be wider than the old ones (e.g., "Entry 10" vs.
     *  "Entry 9"), you need to rerun the layout manager.
     *  You need to do this <I>before</I> trying to scroll
     *  to make the index visible.
     */

    public void actionPerformed(ActionEvent event) {
      int index = sampleModel.getSize();
      sampleModel.addElement("Entry " + (index+1));
      ((JComponent)getContentPane()).revalidate();
      sampleJList.setSelectedIndex(index);
      sampleJList.ensureIndexIsVisible(index);
    }
  }
}