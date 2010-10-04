/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jchip8br.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author magwed
 */
public class MultiHighlight implements ActionListener {

  private JTextComponent comp;

  private String charsToHighlight;

  public MultiHighlight(JTextComponent c, String chars) {
    comp = c;
    charsToHighlight = chars;
  }

    @Override
  public void actionPerformed(ActionEvent e) {
    // highlight all characters that appear in charsToHighlight
    Highlighter h = comp.getHighlighter();
    h.removeAllHighlights();
    String text = comp.getText().toUpperCase();

    for (int j = 0; j < text.length(); j += 1) {
      char ch = text.charAt(j);
      if (charsToHighlight.indexOf(ch) >= 0)
        try {
          h.addHighlight(j, j + 1, DefaultHighlighter.DefaultPainter);
        } catch (BadLocationException ble) {
        }
    }
  }
}

