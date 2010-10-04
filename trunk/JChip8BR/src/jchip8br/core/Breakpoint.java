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
