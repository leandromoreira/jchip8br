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

import jchip8br.util.Logger;
import jchip8br.util.LoggerManager;
import jchip8br.util.Util;

/**
 * @author dreampeppers99
 */
public class Memory {

    private static Logger log = LoggerManager.getMemoryLogger();
    private short[] memory = new short[0x1000];

    public Memory() {
    }

    public short readFrom(int add) {
        if (add > 0xFFF) {
            log.error("Read Address:0x" + Integer.toHexString(add) + " unknow.");
            throw new IllegalArgumentException("["+Integer.toHexString(add).toUpperCase() +  "] The adress must be between the 0x000 and 0xFFF.");
        }
        return memory[add];
    }

    public void writeAt(int add, short value) {
        if (add > 0xFFF) {
            log.error("Write at Address:0x" + Integer.toHexString(add) + " unknow.");
            throw new IllegalArgumentException("["+Integer.toHexString(add).toUpperCase() +  "] The adress must be between the 0x000 and 0xFFF.");
        }
        memory[add] = value;
    }

    @Override
    public String toString() {
        return memoryView(0x0);
    }

    public String getProgram() {
        return memoryView(0x200);
    }

    public void clear() {
        for (int i = 0x200; i < 0xFFF; i++) {
            memory[i] = 0x0;
        }
    }

    short[] getACopy() {
        return java.util.Arrays.copyOf(memory, memory.length);
    }

    private String memoryView(int init) {
        int valuesPerRow = 16;
        StringBuilder sb = new StringBuilder();
        for (int i = init; i <= 0xFFF; i++) {
        
            if( (i%valuesPerRow) == 0)
                sb.append((Util.FormatHexAdress(i,true) + "  "));

            sb.append(Util.FormatHexValue(memory[i],false)+ " ");

            if( (i%valuesPerRow) == (valuesPerRow-1))
                sb.append("\n");
        }
        return sb.toString();
    }

   
}
