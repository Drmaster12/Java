import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;

/** Panel for selecting the format of the query text, either as
 *  name/value pairs or raw text (for example, sending a
 *  serialized object.
 *  <P>
 *  Also, provides the ability to encode a String in the
 *  application/x-www-form-urlencoded format.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  (C) 2007 Marty Hall, Larry Brown, and Yaakov Chaikin;
 *  may be freely used or adapted.
 */

public class EncodeQueryPanel extends JPanel
    implements ActionListener {
  private Font labelFont, textFont;
  private JButton okButton, cancelButton;
  private JRadioButton optionPair, optionRaw;
  private int value;
  private Window window;

  public EncodeQueryPanel(Window window) {
    this.window = window;
    labelFont = new Font("Serif", Font.BOLD, 14);
    textFont = new Font("Monospaced", Font.BOLD, 12);
    setLayout(new BorderLayout());
    add(getOptionPanel(), BorderLayout.CENTER);
    add(getButtonPanel(), BorderLayout.SOUTH);
    value = JOptionPane.CANCEL_OPTION;
  }

  private JPanel getOptionPanel() {
    JPanel optionPanel = new JPanel();
    Border border = BorderFactory.createEtchedBorder();
    optionPanel.setBorder(
      BorderFactory.createTitledBorder(border,
                                       "Encode data as ...   ",
                                       TitledBorder.LEFT,
                                       TitledBorder.CENTER,
                                       labelFont));
    optionPanel.setLayout(
      new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
    optionPair = new JRadioButton("name/value pairs");
    optionPair.setFont(labelFont);
    optionPair.setSelected(true);
    optionRaw = new JRadioButton("raw text");
    optionRaw.setFont(labelFont);
    ButtonGroup group = new ButtonGroup();
    group.add(optionPair);
    group.add(optionRaw);
    optionPanel.add(optionPair);
    optionPanel.add(optionRaw);
    return(optionPanel);
  }

  private JPanel getButtonPanel() {
    JPanel buttonPanel = new JPanel();
    okButton = new JButton("OK");
    okButton.setFont(labelFont);
    okButton.addActionListener(this);
    cancelButton = new JButton("Cancel");
    cancelButton.setFont(labelFont);
    cancelButton.addActionListener(this);
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);
    return(buttonPanel);
  }

  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == okButton) {
      value = JOptionPane.OK_OPTION;
    }
    window.dispose();
  }

  public int getValue() {
    return(value);
  }

  /** Based on option selected (name/value pairs, raw text),
   *  encode the data (assume UTF-8 charset).
   */

  public String encode(String queryData)
      throws UnsupportedEncodingException {
    if(queryData == null || queryData.length() == 0) {
      return(queryData);
    }
    if (optionRaw.isSelected()) {
      queryData = URLEncoder.encode(queryData, "UTF-8");
    } else {
      // Fit each name/value pair and rebuild with
      // the value encoded.
      StringBuffer encodedData = new StringBuffer();
      String[] pairs = queryData.split("&");
      for(int i=0; i<pairs.length; i++) {
        encodedData.append(encodePair(pairs[i]));
        if (i<pairs.length-1) {
          encodedData.append("&");
        }
      }
      queryData = encodedData.toString();
    }
    return(queryData);
  }

  // Process name/value pair, returning pair with
  // value encoded.

  private String encodePair(String nameValuePair)
      throws UnsupportedEncodingException {
    String encodedPair = "";
    String[] pair = nameValuePair.split("=");
    if (pair[0].trim().length() == 0) {
      throw new UnsupportedEncodingException("Name missing");
    }
    encodedPair = pair[0].trim() + "=";
    if (pair.length > 1) {
      encodedPair += URLEncoder.encode(pair[1], "UTF-8");
    }
    return(encodedPair);
  }
}