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

/**
 * @author dreampeppers99
 */
public class Instruction {
    /*Chip8*/
    public final static int CLS = 0x00E0;
    public final static int RET = 0x00EE;
    public final static int JMP = 0x1000;
    public final static int CALL = 0x2000;
    public final static int SE = 0x3000;
    public final static int SNE = 0x4000;
    public final static int SE_0 = 0x5000;
    public final static int LD = 0x6000;
    public final static int ADD = 0x7000;
    public final static int LD_0 = 0x8000;
    public final static int OR = 0x8001;
    public final static int AND = 0x8002;
    public final static int XOR = 0x8003;
    public final static int ADD_0 = 0x8004;
    public final static int SUB = 0x8005;
    public final static int SHR = 0x8006;
    public final static int SUBN = 0x8007;
    public final static int SHL = 0x800E;
    public final static int SNE_0 = 0x9000;
    public final static int LD_1 = 0xA000;
    public final static int JMP_0 = 0xB000;
    public final static int RND = 0xC000;
    public final static int DRW = 0xD000;
    public final static int SKP = 0xE000;
    public final static int SKNP = 0xE000;
    public final static int LD_2 = 0xF000;
    public final static int LD_3 = 0xF000;
    public final static int LD_4 = 0xF000;
    public final static int LD_5 = 0xF000;
    public final static int ADD_1 = 0xF000;
    public final static int LD_6 = 0xF000;
    public final static int LD_7 = 0xF000;
    public final static int LD_8 = 0xF000;
    public final static int LD_9 = 0xF000;
    /*ignored*/
    public final static int Call_1802 = 0x0000;
    /*SChip8*/
    public final static int SCD = 0x00C0;
    public final static int SCR = 0x00FB;
    public final static int SCL = 0x00FC;
    public final static int EXIT = 0x00FD;
    public final static int LOW = 0x00FE;
    public final static int HIGH = 0x00FF;
    public final static int DRW_0 = 0xD000;
    public final static int LD_A = 0xF030;
    public final static int LD_B = 0xF075;
    public final static int LD_C = 0xF085;
}
