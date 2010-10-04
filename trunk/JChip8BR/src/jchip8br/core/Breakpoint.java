/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jchip8br.core;

import javax.swing.text.Highlighter.Highlight;

/**
 *
 * @author magwed
 */
public class Breakpoint {

    public Breakpoint(int address) {
        this.address = address;
    }

    private int address;
    private boolean enabled;
    private Highlight highlighter;

    public boolean isAddress(int address)
    {
        return address==this.address;
    }

    public int Address() { return address; }
    public Highlight Highlight() { return highlighter; }

    public void SetHighlight(Highlight AddHighlight) {highlighter = AddHighlight;}
}
