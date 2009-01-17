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

/**
 * @author dreampeppers99
 */
public class MemoryMap {

    public static final int START_PROGRAM = 0x200;
    public static final int END_PROGRAM = 0xFFF;
    
    public static final int CHARACTER_0 = 0x000;
    public static final int CHARACTER_1 = 0x005;
    public static final int CHARACTER_2 = 0x00A;
    public static final int CHARACTER_3 = 0x00F;
    public static final int CHARACTER_4 = 0x014;
    public static final int CHARACTER_5 = 0x019;
    public static final int CHARACTER_6 = 0x01E;
    public static final int CHARACTER_7 = 0x023;
    public static final int CHARACTER_8 = 0x028;
    public static final int CHARACTER_9 = 0x02D;
    public static final int CHARACTER_A = 0x032;
    public static final int CHARACTER_B = 0x037;
    public static final int CHARACTER_C = 0x03C;
    public static final int CHARACTER_D = 0x041;
    public static final int CHARACTER_E = 0x046;
    public static final int CHARACTER_F = 0x04B;

    public static final int XCHARACTER_0 = 0x050;
    public static final int XCHARACTER_1 = 0x05A;
    public static final int XCHARACTER_2 = 0x064;
    public static final int XCHARACTER_3 = 0x06E;
    public static final int XCHARACTER_4 = 0x078;
    public static final int XCHARACTER_5 = 0x082;
    public static final int XCHARACTER_6 = 0x08C;
    public static final int XCHARACTER_7 = 0x096;
    public static final int XCHARACTER_8 = 0x0A0;
    public static final int XCHARACTER_9 = 0x0AA;
    public static final int XCHARACTER_A = 0x0B4;
    public static final int XCHARACTER_B = 0x0BE;
    public static final int XCHARACTER_C = 0x0C8;
    public static final int XCHARACTER_D = 0x0D2;
    public static final int XCHARACTER_E = 0x0DC;
    public static final int XCHARACTER_F = 0x0E6;

    private static Logger log = LoggerManager.getMemoryMapLogger();

    public static int giveAdressFor(int character) {
        log.debug("request de address from the 0x" + Integer.toHexString(character).toUpperCase() + " character");
        switch (character) {
            case 0x0:
                return CHARACTER_0;
            case 0x1:
                return CHARACTER_1;
            case 0x2:
                return CHARACTER_2;
            case 0x3:
                return CHARACTER_3;
            case 0x4:
                return CHARACTER_4;
            case 0x5:
                return CHARACTER_5;
            case 0x6:
                return CHARACTER_6;
            case 0x7:
                return CHARACTER_7;
            case 0x8:
                return CHARACTER_8;
            case 0x9:
                return CHARACTER_9;
            case 0xA:
                return CHARACTER_A;
            case 0xB:
                return CHARACTER_B;
            case 0xC:
                return CHARACTER_C;
            case 0xD:
                return CHARACTER_D;
            case 0xE:
                return CHARACTER_E;
            case 0xF:
                return CHARACTER_F;
        }
        log.error("was required an character not avaliable =0x" + Integer.toHexString(character).toUpperCase());
        return CHARACTER_0;
    }

    static int giveSAdressFor(short character) {
        log.debug("request de saddress from the 0x" + Integer.toHexString(character).toUpperCase() + " character");
        switch (character) {
            case 0x0:
                return XCHARACTER_0;
            case 0x1:
                return XCHARACTER_1;
            case 0x2:
                return XCHARACTER_2;
            case 0x3:
                return XCHARACTER_3;
            case 0x4:
                return XCHARACTER_4;
            case 0x5:
                return XCHARACTER_5;
            case 0x6:
                return XCHARACTER_6;
            case 0x7:
                return XCHARACTER_7;
            case 0x8:
                return XCHARACTER_8;
            case 0x9:
                return XCHARACTER_9;
            case 0xA:
                return XCHARACTER_A;
            case 0xB:
                return XCHARACTER_B;
            case 0xC:
                return XCHARACTER_C;
            case 0xD:
                return XCHARACTER_D;
            case 0xE:
                return XCHARACTER_E;
            case 0xF:
                return XCHARACTER_F;
        }
        log.error("was required an character not avaliable =0x" + Integer.toHexString(character).toUpperCase());
        return CHARACTER_0;
    }
}
