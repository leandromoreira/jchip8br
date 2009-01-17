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
package jchip8br.util;

import java.nio.ByteBuffer;

/**
 * @author dreampeppers99
 */
public class Util {

    public static short readUnsignedByte(ByteBuffer readbuffer){
        return (short) (readbuffer.get() & 0xff);
    }
    public static short readUnsignedByte(byte value){
        return (short) (value & 0xff);
    }
    public static String fillIfNeedsWith(int times,String word,String value){
        times = times - value.length()+1;
        for(int i = 1 ; i < times ; i++){
            value = word + value;
        }
        return value;
    }
}
