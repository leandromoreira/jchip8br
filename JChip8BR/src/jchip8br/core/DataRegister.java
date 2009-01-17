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
public class DataRegister {
		public short[] V = new short[0x10];
        @Override
		public String toString() {
			return "V0=" + V[0x0] + ",V1=" + V[0x1] + ",V2=" + V[0x2] + ",V3=" + V[0x3]
					+ "<br/>,V4=" + V[0x4] + ",V5=" + V[0x5] + ",V6=" + V[0x6] + "" + ",V7="
					+ V[0x7] + "<br/>,V8=" + V[0x8] + ",V9=" + V[0x9] + ",VA=" + V[0xA] + ",VB="
					+ V[0xB] + "<br/>,VC=" + V[0xC] + "" + ",VD=" + V[0xD] + ",VE=" + V[0xE]
					+ ",VF=" + V[0xF] + "}";
		}
}
