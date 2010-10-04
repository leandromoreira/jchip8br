/*
This file is part of JChip8BR.

JChip8BR is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

JChip8BR is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with JChip8BR.  If not, see <http://www.gnu.org/licenses/>.
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

