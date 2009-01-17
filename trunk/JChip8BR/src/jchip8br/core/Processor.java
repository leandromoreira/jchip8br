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

import java.util.Random;
import jchip8br.control.Controller;
import jchip8br.util.Logger;
import jchip8br.util.LoggerManager;

/**
 * @author dreampeppers99
 */
public class Processor {

    public DataRegister dataRegister = new DataRegister();
    public int addressRegisterI = 0;
    public int delayTimerDT;
    public int soundTimerST;
    public int programCounter,  stackPointer;
    public short[] stack = new short[0x10];
    private Memory memory = new Memory();
    private final boolean dissambler = true;
    private String assembler = "";
    private final Random rnd = new Random();
    private static Logger log = LoggerManager.getCpuLogger();
    private static final boolean bigEndian = true;

    /*Experimental*/
    private short[] hp48 = new short[8];
    /*I believe that it will works*/

    public Processor() {
        init();
    }

    public void reset() {
        init();
        memory.clear();
    }

    private void fillCharacter() {
        //0 SPRITE
        memory.writeAt(0x000, (short) 0xF0);
        memory.writeAt(0x001, (short) 0x90);
        memory.writeAt(0x002, (short) 0x90);
        memory.writeAt(0x003, (short) 0x90);
        memory.writeAt(0x004, (short) 0xF0);
        //1 SPRITE
        memory.writeAt(0x005, (short) 0x20);
        memory.writeAt(0x006, (short) 0x60);
        memory.writeAt(0x007, (short) 0x20);
        memory.writeAt(0x008, (short) 0x20);
        memory.writeAt(0x009, (short) 0x70);
        //2 SPRITE add: 0x00A
        memory.writeAt(0x00A, (short) 0xF0);
        memory.writeAt(0x00B, (short) 0x10);
        memory.writeAt(0x00C, (short) 0xF0);
        memory.writeAt(0x00D, (short) 0x80);
        memory.writeAt(0x00E, (short) 0xF0);
        //3 SPRITE add: 0x00F
        memory.writeAt(0x00F, (short) 0xF0);
        memory.writeAt(0x010, (short) 0x10);
        memory.writeAt(0x011, (short) 0xF0);
        memory.writeAt(0x012, (short) 0x10);
        memory.writeAt(0x013, (short) 0xF0);
        //4 SPRITE add: 0x014
        memory.writeAt(0x014, (short) 0x90);
        memory.writeAt(0x015, (short) 0x90);
        memory.writeAt(0x016, (short) 0xF0);
        memory.writeAt(0x017, (short) 0x10);
        memory.writeAt(0x018, (short) 0x10);
        //5 SPRITE add: 0x019
        memory.writeAt(0x019, (short) 0xF0);
        memory.writeAt(0x01A, (short) 0x80);
        memory.writeAt(0x01B, (short) 0xF0);
        memory.writeAt(0x01C, (short) 0x10);
        memory.writeAt(0x01D, (short) 0xF0);
        //6 SPRITE add: 0x01E
        memory.writeAt(0x01E, (short) 0xF0);
        memory.writeAt(0x01F, (short) 0x80);
        memory.writeAt(0x020, (short) 0xF0);
        memory.writeAt(0x021, (short) 0x90);
        memory.writeAt(0x022, (short) 0xF0);
        //7 SPRITE add: 0x023
        memory.writeAt(0x023, (short) 0xF0);
        memory.writeAt(0x024, (short) 0x10);
        memory.writeAt(0x025, (short) 0x20);
        memory.writeAt(0x026, (short) 0x40);
        memory.writeAt(0x027, (short) 0x40);
        //8 SPRITE add: 0x028
        memory.writeAt(0x028, (short) 0xF0);
        memory.writeAt(0x029, (short) 0x90);
        memory.writeAt(0x02A, (short) 0xF0);
        memory.writeAt(0x02B, (short) 0x90);
        memory.writeAt(0x02C, (short) 0xF0);
        //9 SPRITE add: 0x02D
        memory.writeAt(0x02D, (short) 0xF0);
        memory.writeAt(0x02E, (short) 0x90);
        memory.writeAt(0x02F, (short) 0xF0);
        memory.writeAt(0x030, (short) 0x10);
        memory.writeAt(0x031, (short) 0xF0);
        //A SPRITE add: 0x032
        memory.writeAt(0x032, (short) 0xF0);
        memory.writeAt(0x033, (short) 0x90);
        memory.writeAt(0x034, (short) 0xF0);
        memory.writeAt(0x035, (short) 0x90);
        memory.writeAt(0x036, (short) 0x90);
        //B SPRITE add: 0x037
        memory.writeAt(0x037, (short) 0xE0);
        memory.writeAt(0x038, (short) 0x90);
        memory.writeAt(0x039, (short) 0xE0);
        memory.writeAt(0x03A, (short) 0x90);
        memory.writeAt(0x03B, (short) 0xE0);
        //C SPRITE add: 0x03C
        memory.writeAt(0x03C, (short) 0xF0);
        memory.writeAt(0x03D, (short) 0x80);
        memory.writeAt(0x03E, (short) 0x80);
        memory.writeAt(0x03F, (short) 0x80);
        memory.writeAt(0x040, (short) 0xF0);
        //D SPRITE add: 0x041
        memory.writeAt(0x041, (short) 0xE0);
        memory.writeAt(0x042, (short) 0x90);
        memory.writeAt(0x043, (short) 0x90);
        memory.writeAt(0x044, (short) 0x90);
        memory.writeAt(0x045, (short) 0xE0);
        //E SPRITE add: 0x046
        memory.writeAt(0x046, (short) 0xF0);
        memory.writeAt(0x047, (short) 0x80);
        memory.writeAt(0x048, (short) 0xF0);
        memory.writeAt(0x049, (short) 0x80);
        memory.writeAt(0x04A, (short) 0xF0);
        //F SPRITE add: 0x04B
        memory.writeAt(0x04B, (short) 0xF0);
        memory.writeAt(0x04C, (short) 0x80);
        memory.writeAt(0x04D, (short) 0xF0);
        memory.writeAt(0x04E, (short) 0x80);
        memory.writeAt(0x04F, (short) 0x80);


        int xfontChip8 = 0x050;
        /*SChip8  starts at 0x050*/
        //0 SPRITE
        memory.writeAt(xfontChip8++, (short) 0x7E);//01111110
        memory.writeAt(xfontChip8++, (short) 0xFF);//11111111
        memory.writeAt(xfontChip8++, (short) 0xC3);//11000011
        memory.writeAt(xfontChip8++, (short) 0xC3);//11000011
        memory.writeAt(xfontChip8++, (short) 0xC3);//11000011
        memory.writeAt(xfontChip8++, (short) 0xC3);//11000011
        memory.writeAt(xfontChip8++, (short) 0xC3);//11000011
        memory.writeAt(xfontChip8++, (short) 0xC3);//11000011
        memory.writeAt(xfontChip8++, (short) 0xFF);//11111111
        memory.writeAt(xfontChip8++, (short) 0x7E);//01111110
        //1 SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));//00111000
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111000", 2));//11111000
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111000", 2));//11111000
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));//00111000
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));//00111000
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));//00111000
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));//00111000
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));//00111000
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));//11111111
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));//11111111
        //2 SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00000111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00001110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00011100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01110000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        //3 SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00000111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00001111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111100", 2));
        //4 SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00011110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11000110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00001110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00001110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00001110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00001110", 2));
        //5 SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00001111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111110", 2));
        //6 SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00001111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11110000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111110", 2));
        //7 SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00001110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00011100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111000", 2));
        //8 SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11000011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11000011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111100", 2));
        //9 SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00001111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00011110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("0011110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("1111000", 2));
        //A SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("00111100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01100110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        //B SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111110", 2));
        //C SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("01111110", 2));
        //D SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111100", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100011", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111100", 2));
        //E SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100001", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100001", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        //F SPRITE
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111111", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100001", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11111110", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
        memory.writeAt(xfontChip8++, (short) Integer.parseInt("11100000", 2));
    }

    private void init() {
        programCounter = 0x200;
        stackPointer = 0x00;
        delayTimerDT = 0x00;
        soundTimerST = 0x00;
        fillCharacter();
    }
    private String actualLine = "";

public String stack() {
        StringBuilder value = new StringBuilder();

        for (int i = 0; i <= 15; i++) {
            value.append(jchip8br.util.Util.fillIfNeedsWith(2, "0", String.valueOf(i + 1)) + ":0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(stack[i])).toUpperCase() + "\n");
        }

        return value.toString();
    }

    public void step() {
        int oldPc = programCounter;
        int opCode = 0;

        if (bigEndian) {
            opCode = getMemory().readFrom(programCounter++) << 8 | getMemory().readFrom(programCounter++);
        } else {
            opCode = getMemory().readFrom(programCounter++) | getMemory().readFrom(programCounter++) << 8;
        }

        if (opCode == Instruction.CLS) {
            if (dissambler) {
                actualLine = "cls";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            Emulator.getVideo().clearScreen();
            return;
        }

        if (opCode == Instruction.RET) {
            if (dissambler) {
                actualLine = "ret";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            programCounter = stack[--stackPointer];
            stack[stackPointer] = 0x0;
            return;
        }

        if (opCode >= Instruction.JMP & opCode <= (Instruction.JMP + 0xFFF)) {
            int place = opCode & 0x0FFF;
            if (dissambler) {
                actualLine = "jmp 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            programCounter = place;
            return;
        }


        if (opCode >= Instruction.CALL & opCode <= (Instruction.CALL + 0xFFF)) {
            int place = opCode & 0x0FFF;
            if (dissambler) {
                actualLine = "call 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            stack[stackPointer] = (short) (programCounter);
            ++stackPointer;
            programCounter = place;
            return;
        }

        if (opCode >= Instruction.SE & opCode <= (Instruction.SE + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            if (dissambler) {
                actualLine = "se V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            if (dataRegister.V[register] == value) {
                programCounter += 2;
            }
            return;
        }


        if (opCode >= Instruction.SNE & opCode <= (Instruction.SNE + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            if (dissambler) {
                actualLine = "sne V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            if (dataRegister.V[register] != value) {
                programCounter += 2;
            }
            return;
        }

        if (opCode >= Instruction.SE_0 & opCode <= (Instruction.SE_0 + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "se V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            if (dataRegister.V[registerX] == dataRegister.V[registerY]) {
                programCounter += 2;
            }
            return;
        }

        if (opCode >= Instruction.LD & opCode <= (Instruction.LD + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            if (dissambler) {
                actualLine = "ld V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            dataRegister.V[register] = (short) value;
            return;
        }

        if (opCode >= Instruction.ADD & opCode <= (Instruction.ADD + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            if (dissambler) {
                actualLine = "add V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            dataRegister.V[register] = jchip8br.util.Util.readUnsignedByte((byte) ((byte) value + (byte) dataRegister.V[register])); //this makes most of games works including pong
            //dataRegister.V[register] += (short) value; this makes the pong works
            return;
        }

        if (opCode >= Instruction.LD_0 & opCode <= (Instruction.LD_0 + 0xFFF) & ((opCode & 0x000F) == 0x0)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "ld V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            dataRegister.V[registerX] = dataRegister.V[registerY];
            return;
        }


        if (opCode >= Instruction.OR & opCode <= (Instruction.OR + 0xFFE) & ((opCode & 0x000F) == 0x1)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "or V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            dataRegister.V[registerX] = (short) (dataRegister.V[registerX] | dataRegister.V[registerY]);
            return;
        }


        if (opCode >= Instruction.AND & opCode <= (Instruction.AND + 0xFFD) & ((opCode & 0x000F) == 0x2)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "and V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            dataRegister.V[registerX] = (short) (dataRegister.V[registerX] & dataRegister.V[registerY]);
            return;
        }


        if (opCode >= Instruction.XOR & opCode <= (Instruction.XOR + 0xFFC) & ((opCode & 0x000F) == 0x3)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "xor V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            dataRegister.V[registerX] = (short) (dataRegister.V[registerX] ^ dataRegister.V[registerY]);
            return;
        }

        if (opCode >= Instruction.ADD_0 & opCode <= (Instruction.ADD_0 + 0xFFB) & ((opCode & 0x000F) == 0x4)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "add V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            if ((dataRegister.V[registerX] + dataRegister.V[registerY]) > 0xFF) {
                dataRegister.V[0xF] = 0x1;
            } else {
                dataRegister.V[0xF] = 0x0;
            }
            dataRegister.V[registerX] = (short) (jchip8br.util.Util.readUnsignedByte((byte) ((byte) dataRegister.V[registerX] + (byte) dataRegister.V[registerY])) & 0xFF);
            //dataRegister.V[registerX] = (short) ((dataRegister.V[registerX] + dataRegister.V[registerY]) & 0xFF);
            return;
        }

        if (opCode >= Instruction.SUB & opCode <= (Instruction.SUB + 0xFFA) & ((opCode & 0x000F) == 0x5)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "sub V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            if (dataRegister.V[registerX] >= dataRegister.V[registerY]) { // i used to use just >
                dataRegister.V[0xF] = 0x1;
            } else {
                dataRegister.V[0xF] = 0x0;
            }

            dataRegister.V[registerX] = (short) jchip8br.util.Util.readUnsignedByte((byte) ((byte) dataRegister.V[registerX] - (byte) dataRegister.V[registerY]));
            //dataRegister.V[registerX] = (short) (dataRegister.V[registerX] - dataRegister.V[registerY]);
            return;
        }

        if (opCode >= Instruction.SHR & opCode <= (Instruction.SHR + 0xFF9) & ((opCode & 0x000F) == 0x6)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "shr V" + Integer.toHexString(registerX).toUpperCase() + " {, V" + Integer.toHexString(registerY).toUpperCase() + "}";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            if ((dataRegister.V[registerX] & 0x1) == 0x1) {
                dataRegister.V[0xF] = 0x1;
            } else {
                dataRegister.V[0xF] = 0x0;
            }
            dataRegister.V[registerX] = (short) (dataRegister.V[registerX] >> 0x1);
            return;
        }

        if (opCode >= Instruction.SUBN & opCode <= (Instruction.SUBN + 0xFF8) & ((opCode & 0x000F) == 0x7)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "subn V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            if (dataRegister.V[registerX] < dataRegister.V[registerY]) {
                dataRegister.V[0xF] = 0x1;
            } else {
                dataRegister.V[0xF] = 0x0;
            }
            dataRegister.V[registerX] = (short) jchip8br.util.Util.readUnsignedByte((byte) ((byte) dataRegister.V[registerY] - (byte) dataRegister.V[registerX]));
            //dataRegister.V[registerX] = (short) (dataRegister.V[registerY] - dataRegister.V[registerX]);
            return;
        }

        if (opCode >= Instruction.SHL & opCode <= (Instruction.SHL + 0xFF1) & ((opCode & 0x000F) == 0xE)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "shl V" + Integer.toHexString(registerX).toUpperCase() + " {, V" + Integer.toHexString(registerY).toUpperCase() + "}";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            if (dataRegister.V[registerX] >> 0x7 == 0x1) {
                dataRegister.V[0xF] = 0x1;
            } else {
                dataRegister.V[0xF] = 0x0;
            }
            dataRegister.V[registerX] = (short) (dataRegister.V[registerX] << 0x1);
            return;
        }

        if (opCode >= Instruction.SNE_0 & opCode <= (Instruction.SNE_0 + 0xFFF) & ((opCode & 0x000F) == 0x0)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;

            if (dissambler) {
                actualLine = "sne V" + Integer.toHexString(registerX).toUpperCase() + " ,V" + Integer.toHexString(registerY).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            if (dataRegister.V[registerX] != dataRegister.V[registerY]) {
                programCounter += 0x2;
            }
            return;
        }

        if (opCode >= Instruction.LD_1 & opCode <= (Instruction.LD_1 + 0xFFF)) {
            int place = opCode & 0x0FFF;
            if (dissambler) {
                actualLine = "ld I, 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            addressRegisterI = place;
            return;
        }


        if (opCode >= Instruction.JMP_0 & opCode <= (Instruction.JMP_0 + 0xFFF)) {
            int place = opCode & 0x0FFF;
            if (dissambler) {
                actualLine = "jmp V0, 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            programCounter = dataRegister.V[0x0] + place;
            return;
        }

        if (opCode >= Instruction.RND & opCode <= (Instruction.RND + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            if (dissambler) {
                actualLine = "rnd V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            dataRegister.V[register] = (short) (rnd.nextInt(0x100) & value);
            return;
        }

        if (opCode >= Instruction.DRW & opCode <= (Instruction.DRW + 0x0FFF) & ((opCode & 0x000F) != 0x0)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            int y = place >> 4 & 0x0F;
            int n = opCode & 0x0F;
            if (dissambler) {
                actualLine = "drw V" + Integer.toHexString(x).toUpperCase() + ", V" + Integer.toHexString(y).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(n).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            int[] sprite = new int[n];
            int count = 0;
            for (int i = addressRegisterI; i < (addressRegisterI + n); i++) {
                sprite[count++] = memory.readFrom(i);
            }
            Emulator.getVideo().draw(sprite, dataRegister.V[x], dataRegister.V[y]);
            return;
        }


        if (opCode >= Instruction.SKP & opCode <= (Instruction.SKP + 0xFFF) & ((opCode & 0x00FF) == 0x9E)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "skp V" + Integer.toHexString(x).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            if (Controller.getController().isThisKeyPressed(dataRegister.V[x])) {
                programCounter += 0x02;
            }
            return;
        }


        if (opCode >= Instruction.SKNP & opCode <= (Instruction.SKNP + 0xFFF) & ((opCode & 0x00FF) == 0xA1)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "sknp V" + Integer.toHexString(x).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            if (!Controller.getController().isThisKeyPressed(dataRegister.V[x])) {
                programCounter += 0x02;
            }
            return;
        }


        if (opCode >= Instruction.LD_2 & opCode <= (Instruction.LD_2 + 0xFFF) & ((opCode & 0x00FF) == 0x07)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld V" + Integer.toHexString(x).toUpperCase() + " ,DT";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            dataRegister.V[x] = (short) delayTimerDT;
            return;
        }

        if (opCode >= Instruction.LD_3 & opCode <= (Instruction.LD_3 + 0xFFF) & ((opCode & 0x00FF) == 0x0A)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld V" + Integer.toHexString(x).toUpperCase() + " ,K";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            dataRegister.V[x] = (short) Controller.getController().waitingKey();
            return;
        }


        if (opCode >= Instruction.LD_4 & opCode <= (Instruction.LD_4 + 0xFFF) & ((opCode & 0x00FF) == 0x15)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld DT, V" + Integer.toHexString(x).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            delayTimerDT = dataRegister.V[x];
            return;
        }


        if (opCode >= Instruction.LD_5 & opCode <= (Instruction.LD_5 + 0xFFF) & ((opCode & 0x00FF) == 0x18)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld ST, V" + Integer.toHexString(x).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            soundTimerST = dataRegister.V[x];
            return;
        }

        if (opCode >= Instruction.ADD_1 & opCode <= (Instruction.ADD_1 + 0xFFF) & ((opCode & 0x00FF) == 0x1E)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "add I, V" + Integer.toHexString(x).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }

            addressRegisterI += dataRegister.V[x];
            return;
        }

        if (opCode >= Instruction.LD_6 & opCode <= (Instruction.LD_6 + 0xFFF) & ((opCode & 0x00FF) == 0x29)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld f, V" + Integer.toHexString(x).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            addressRegisterI = MemoryMap.giveAdressFor(dataRegister.V[x]);
            return;
        }

        if (opCode >= Instruction.LD_7 & opCode <= (Instruction.LD_7 + 0xFFF) & ((opCode & 0x00FF) == 0x33)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld b, V" + Integer.toHexString(x).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            int cent = 0, tenth = 0, unit = 0;

            if (dataRegister.V[x] >= 100) {
                cent = Integer.decode(String.valueOf(dataRegister.V[x]).substring(0, 1));
            }

            if (dataRegister.V[x] >= 100) {
                tenth = Integer.decode(String.valueOf(dataRegister.V[x]).substring(1, 2));
            } else if (dataRegister.V[x] >= 10) {
                tenth = Integer.decode(String.valueOf(dataRegister.V[x]).substring(0, 1));
            }

            if (dataRegister.V[x] >= 100) {
                unit = Integer.decode(String.valueOf(dataRegister.V[x]).substring(2, 3));
            } else if (dataRegister.V[x] >= 10) {
                unit = Integer.decode(String.valueOf(dataRegister.V[x]).substring(1, 2));
            } else {
                unit = Integer.decode(String.valueOf(dataRegister.V[x]).substring(0, 1));
            }

            memory.writeAt(addressRegisterI, (short) cent);
            memory.writeAt(addressRegisterI + 1, (short) tenth);
            memory.writeAt(addressRegisterI + 2, (short) unit);
            return;
        }

        if (opCode >= Instruction.LD_8 & opCode <= (Instruction.LD_8 + 0xFFF) & ((opCode & 0x00FF) == 0x55)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld [I], V" + Integer.toHexString(x).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            for (int i = 0; i <= x; i++) {
                memory.writeAt(addressRegisterI + i, dataRegister.V[i]);
            }
            return;
        }

        if (opCode >= Instruction.LD_9 & opCode <= (Instruction.LD_9 + 0xFFF) & ((opCode & 0x00FF) == 0x65)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld V" + Integer.toHexString(x).toUpperCase() + " ,[I]";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            for (int i = 0; i <= x; i++) {
                dataRegister.V[i] = memory.readFrom(addressRegisterI + i);
            }
            return;
        }

        /*schip8*/
        if (opCode >= Instruction.SCD & opCode <= (Instruction.SCD + 0xF)) {
            int lastByte = opCode & 0x00F;
            if (dissambler) {
                actualLine = "scd  " + Integer.toHexString(lastByte).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            Emulator.getVideo().scrollDown(lastByte);
            return;
        }
        if (opCode == Instruction.SCR) {
            if (dissambler) {
                actualLine = "scr  ";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            Emulator.getVideo().scroll4PixelsToRigth();
            return;
        }
        if (opCode == Instruction.SCL) {
            if (dissambler) {
                actualLine = "scl  ";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            Emulator.getVideo().scroll4PixelsToLeft();
            return;
        }

        if (opCode == Instruction.EXIT) {
            if (dissambler) {
                actualLine = "exit  ";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            System.exit(0);
            return;
        }

        if (opCode == Instruction.LOW) {
            if (dissambler) {
                actualLine = "low  ";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            Emulator.getVideo().setLowGraphics();
            return;
        }
        if (opCode == Instruction.HIGH) {
            if (dissambler) {
                actualLine = "high  ";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            Emulator.getVideo().setHighGraphics();
            return;
        }

        if (opCode >= Instruction.DRW_0 & opCode <= (Instruction.DRW_0 + 0x0FFF) & ((opCode & 0x000F) == 0x0)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            int y = place >> 4 & 0x0F;
            int n = 32; //general case are 16 x 16

            if (addressRegisterI < 0x200) {
                n = 10; //font case are 8 x 10
            }

            if (dissambler) {
                actualLine = "drw V" + Integer.toHexString(x).toUpperCase() + ", V" + Integer.toHexString(y).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(n).toUpperCase());
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            int[] sprite = new int[n];
            int count = 0;
            for (int i = addressRegisterI; i < (addressRegisterI + n);) {
                sprite[count++] = memory.readFrom(i++) << 8 | memory.readFrom(i++);
            }
            Emulator.getVideo().draw(sprite, dataRegister.V[x], dataRegister.V[y]);
            return;
        }

        if (opCode >= Instruction.LD_A & (opCode & 0x00FF) == 0x30) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld hf, V" + Integer.toHexString(x).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            addressRegisterI = MemoryMap.giveSAdressFor(dataRegister.V[x]);
            return;
        }

        if (opCode >= Instruction.LD_B & (opCode & 0x00FF) == 0x75) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld r,V" + Integer.toHexString(x).toUpperCase();
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            for (int i = 0; i <= x; i++) {
                hp48[i] = dataRegister.V[i];
            }
            return;
        }

        if (opCode >= Instruction.LD_C & (opCode & 0x00FF) == 0x85) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;

            if (dissambler) {
                actualLine = "ld V" + Integer.toHexString(x).toUpperCase() + " ,R";
                actualLine = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(oldPc).toUpperCase()) + ":\t " + actualLine;
            }
            for (int i = 0; i <= x; i++) {
                dataRegister.V[i] = hp48[i];
            }
            return;
        }


        actualLine = "";
        log.debug("unimplemented opcode = 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(opCode).toUpperCase()));
    }

    public String showCurrentLineAssembler() {
        return actualLine;
    }

    public String dataRegisters() {
        return dataRegister.toString();
    }

    public String addressRegister() {
        return "I=" + String.valueOf(addressRegisterI);
    }

    public String memoryViewer() {
        return getMemory().toString();
    }

    public String memoryProgramViewer() {
        return getMemory().getProgram();
    }

    public String showAssembler() {
        return assembler;
    }

    public Memory getMemory() {
        return memory;
    }
}
