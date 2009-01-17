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
public class Disassembler {
    public String assemblerFromDavidWinter(short[] memory) {
        StringBuilder sb = new StringBuilder();
        jchip8br.core.Disassembler dis = new jchip8br.core.Disassembler();

        for (int i = 0x200; i < 0xFFF;) {
            int addr = i;
            int opCode = memory[i++] << 8 | memory[i++];
            String tmp = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(addr).toUpperCase()) + ": " + dis.disassemblerDavidWinterWay(opCode);
            if (!dis.disassemblerDavidWinterWay(opCode).equals("")) {
                if (!tmp.equals("")) {
                    sb.append(tmp + "\n");
                }

            }
        }

        return sb.toString();
    }
    public String disassemblerDavidWinterWay(int opCode) {
        String actualLine = "";
        if (opCode == Instruction.CLS) {
            actualLine = "cls";
            return actualLine;
        }

        if (opCode == Instruction.RET) {
            actualLine = "ret";
            return actualLine;
        }

        if (opCode >= Instruction.JMP & opCode <= (Instruction.JMP + 0xFFF)) {
            int place = opCode & 0x0FFF;
            actualLine = "jmp 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.CALL & opCode <= (Instruction.CALL + 0xFFF)) {
            int place = opCode & 0x0FFF;
            actualLine = "call 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.SE & opCode <= (Instruction.SE + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            actualLine = "se V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.SNE & opCode <= (Instruction.SNE + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            actualLine = "sne V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.SE_0 & opCode <= (Instruction.SE_0 + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "se V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD & opCode <= (Instruction.LD + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            actualLine = "ld V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.ADD & opCode <= (Instruction.ADD + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            actualLine = "add V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.LD_0 & opCode <= (Instruction.LD_0 + 0xFFF) & ((opCode & 0x000F) == 0x0)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "ld V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.OR & opCode <= (Instruction.OR + 0xFFE) & ((opCode & 0x000F) == 0x1)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "or V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.AND & opCode <= (Instruction.AND + 0xFFD) & ((opCode & 0x000F) == 0x2)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "and V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.XOR & opCode <= (Instruction.XOR + 0xFFC) & ((opCode & 0x000F) == 0x3)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "xor V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.ADD_0 & opCode <= (Instruction.ADD_0 + 0xFFB) & ((opCode & 0x000F) == 0x4)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "add V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.SUB & opCode <= (Instruction.SUB + 0xFFA) & ((opCode & 0x000F) == 0x5)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "sub V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.SHR & opCode <= (Instruction.SHR + 0xFF9) & ((opCode & 0x000F) == 0x6)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "shr V" + Integer.toHexString(registerX).toUpperCase() + " {, V" + Integer.toHexString(registerY).toUpperCase() + "}";
            return actualLine;
        }

        if (opCode >= Instruction.SUBN & opCode <= (Instruction.SUBN + 0xFF8) & ((opCode & 0x000F) == 0x7)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "subn V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.SHL & opCode <= (Instruction.SHL + 0xFF1) & ((opCode & 0x000F) == 0xE)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "shl V" + Integer.toHexString(registerX).toUpperCase() + " {, V" + Integer.toHexString(registerY).toUpperCase() + "}";
            return actualLine;
        }

        if (opCode >= Instruction.SNE_0 & opCode <= (Instruction.SNE_0 + 0xFFF) & ((opCode & 0x000F) == 0x0)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "sne V" + Integer.toHexString(registerX).toUpperCase() + " ,V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_1 & opCode <= (Instruction.LD_1 + 0xFFF)) {
            int place = opCode & 0x0FFF;
            actualLine = "ld I, 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
            return actualLine;
        }


        if (opCode >= Instruction.JMP_0 & opCode <= (Instruction.JMP_0 + 0xFFF)) {
            int place = opCode & 0x0FFF;
            actualLine = "jmp V0, 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.RND & opCode <= (Instruction.RND + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            actualLine = "rnd V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.DRW & opCode <= (Instruction.DRW + 0x0FFF) & ((opCode & 0x000F) != 0x0)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            int y = place >> 4 & 0x0F;
            int n = opCode & 0x0F;
            actualLine = "drw V" + Integer.toHexString(x).toUpperCase() + ", V" + Integer.toHexString(y).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(n).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.SKP & opCode <= (Instruction.SKP + 0xFFF) & ((opCode & 0x00FF) == 0x9E)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "skp V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.SKNP & opCode <= (Instruction.SKNP + 0xFFF) & ((opCode & 0x00FF) == 0xA1)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "sknp V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }


        if (opCode >= Instruction.LD_2 & opCode <= (Instruction.LD_2 + 0xFFF) & ((opCode & 0x00FF) == 0x07)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld V" + Integer.toHexString(x).toUpperCase() + " ,DT";
            return actualLine;
        }

        if (opCode >= Instruction.LD_3 & opCode <= (Instruction.LD_3 + 0xFFF) & ((opCode & 0x00FF) == 0x0A)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld V" + Integer.toHexString(x).toUpperCase() + " ,K";
            return actualLine;
        }

        if (opCode >= Instruction.LD_4 & opCode <= (Instruction.LD_4 + 0xFFF) & ((opCode & 0x00FF) == 0x15)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld DT, V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_5 & opCode <= (Instruction.LD_5 + 0xFFF) & ((opCode & 0x00FF) == 0x18)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld ST, V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.ADD_1 & opCode <= (Instruction.ADD_1 + 0xFFF) & ((opCode & 0x00FF) == 0x1E)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "add I, V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_6 & opCode <= (Instruction.LD_6 + 0xFFF) & ((opCode & 0x00FF) == 0x29)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld f, V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_7 & opCode <= (Instruction.LD_7 + 0xFFF) & ((opCode & 0x00FF) == 0x33)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld b, V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_8 & opCode <= (Instruction.LD_8 + 0xFFF) & ((opCode & 0x00FF) == 0x55)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld [I], V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_9 & opCode <= (Instruction.LD_9 + 0xFFF) & ((opCode & 0x00FF) == 0x65)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld V" + Integer.toHexString(x).toUpperCase() + " ,[I]";
            return actualLine;
        }
        /*schip8*/
        if (opCode >= Instruction.SCD & opCode <= (Instruction.SCD + 0xF)) {
            int lastByte = opCode & 0x00F;
            actualLine = "scd  " + Integer.toHexString(lastByte).toUpperCase();
            return actualLine;
        }

        if (opCode == Instruction.SCR) {
            actualLine = "scr  ";
            return actualLine;
        }

        if (opCode == Instruction.SCL) {
            actualLine = "scl  ";
            return actualLine;
        }

        if (opCode == Instruction.EXIT) {
            actualLine = "exit  ";
            return actualLine;
        }

        if (opCode == Instruction.LOW) {
            actualLine = "low  ";
            return actualLine;
        }
        if (opCode == Instruction.HIGH) {
            actualLine = "high  ";
            return actualLine;
        }

        if (opCode >= Instruction.DRW_0 & opCode <= (Instruction.DRW_0 + 0x0FFF) & ((opCode & 0x000F) == 0x0)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            int y = place >> 4 & 0x0F;
            actualLine = "drw V" + Integer.toHexString(x).toUpperCase() + ", V" + Integer.toHexString(y).toUpperCase() + ", 0";
            return actualLine;
        }

        if (opCode >= Instruction.LD_A & (opCode & 0x00FF) == 0x30) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld hf, V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_B & (opCode & 0x00FF) == 0x75) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld r,V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_C & (opCode & 0x00FF) == 0x85) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ld V" + Integer.toHexString(x).toUpperCase() + " ,r";
            return actualLine;
        }

        return "";
    }
    public String assemblerFromMichaelToren(short[] memory) {
        StringBuilder sb = new StringBuilder();
        jchip8br.core.Disassembler dis = new jchip8br.core.Disassembler();

        for (int i = 0x200; i < 0xFFF;) {
            int addr = i;
            int opCode = memory[i++] << 8 | memory[i++];
            String tmp = "0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(addr).toUpperCase()) + ": " + dis.disassemmblerMichaelTorenWay(opCode);
            if (!dis.disassemblerDavidWinterWay(opCode).equals("")) {
                if (!tmp.equals("")) {
                    sb.append(tmp + "\n");
                }
            }
        }

        return sb.toString();
    }
    public String disassemmblerMichaelTorenWay(int opCode) {
        String actualLine = "";
        if (opCode == Instruction.CLS) {
            actualLine = "cls";
            return actualLine;
        }

        if (opCode == Instruction.RET) {
            actualLine = "rts";
            return actualLine;
        }

        if (opCode >= Instruction.JMP & opCode <= (Instruction.JMP + 0xFFF)) {
            int place = opCode & 0x0FFF;
            actualLine = "jmp 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.CALL & opCode <= (Instruction.CALL + 0xFFF)) {
            int place = opCode & 0x0FFF;
            actualLine = "jsr 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.SE & opCode <= (Instruction.SE + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            actualLine = "skeq V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.SNE & opCode <= (Instruction.SNE + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            actualLine = "skne V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.SE_0 & opCode <= (Instruction.SE_0 + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "skeq V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD & opCode <= (Instruction.LD + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            actualLine = "mov V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.ADD & opCode <= (Instruction.ADD + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            actualLine = "add V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.LD_0 & opCode <= (Instruction.LD_0 + 0xFFF) & ((opCode & 0x000F) == 0x0)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "mov V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.OR & opCode <= (Instruction.OR + 0xFFE) & ((opCode & 0x000F) == 0x1)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "or V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.AND & opCode <= (Instruction.AND + 0xFFD) & ((opCode & 0x000F) == 0x2)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "and V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.XOR & opCode <= (Instruction.XOR + 0xFFC) & ((opCode & 0x000F) == 0x3)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "xor V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.ADD_0 & opCode <= (Instruction.ADD_0 + 0xFFB) & ((opCode & 0x000F) == 0x4)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "add V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.SUB & opCode <= (Instruction.SUB + 0xFFA) & ((opCode & 0x000F) == 0x5)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "sub V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.SHR & opCode <= (Instruction.SHR + 0xFF9) & ((opCode & 0x000F) == 0x6)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "shr V" + Integer.toHexString(registerX).toUpperCase() + " {, V" + Integer.toHexString(registerY).toUpperCase() + "}";
            return actualLine;
        }

        if (opCode >= Instruction.SUBN & opCode <= (Instruction.SUBN + 0xFF8) & ((opCode & 0x000F) == 0x7)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "rsb V" + Integer.toHexString(registerX).toUpperCase() + ", V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.SHL & opCode <= (Instruction.SHL + 0xFF1) & ((opCode & 0x000F) == 0xE)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "shl V" + Integer.toHexString(registerX).toUpperCase() + " {, V" + Integer.toHexString(registerY).toUpperCase() + "}";
            return actualLine;
        }

        if (opCode >= Instruction.SNE_0 & opCode <= (Instruction.SNE_0 + 0xFFF) & ((opCode & 0x000F) == 0x0)) {
            int place = opCode & 0x0FFF;
            int registerX = place >> 8;
            int registerY = place >> 4 & 0x0F;
            actualLine = "skne V" + Integer.toHexString(registerX).toUpperCase() + " ,V" + Integer.toHexString(registerY).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_1 & opCode <= (Instruction.LD_1 + 0xFFF)) {
            int place = opCode & 0x0FFF;
            actualLine = "mvi 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
            return actualLine;
        }


        if (opCode >= Instruction.JMP_0 & opCode <= (Instruction.JMP_0 + 0xFFF)) {
            int place = opCode & 0x0FFF;
            actualLine = "jmi 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(place).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.RND & opCode <= (Instruction.RND + 0xFFF)) {
            int place = opCode & 0x0FFF;
            int register = place >> 8;
            int value = place & 0x0FF;
            actualLine = "rand V" + Integer.toHexString(register).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(value).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.DRW & opCode <= (Instruction.DRW + 0x0FFF) & ((opCode & 0x000F) != 0x0)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            int y = place >> 4 & 0x0F;
            int n = opCode & 0x0F;
            actualLine = "sprite V" + Integer.toHexString(x).toUpperCase() + ", V" + Integer.toHexString(y).toUpperCase() + ", 0x" + jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(n).toUpperCase());
            return actualLine;
        }

        if (opCode >= Instruction.SKP & opCode <= (Instruction.SKP + 0xFFF) & ((opCode & 0x00FF) == 0x9E)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "skpr V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.SKNP & opCode <= (Instruction.SKNP + 0xFFF) & ((opCode & 0x00FF) == 0xA1)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "skup V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }


        if (opCode >= Instruction.LD_2 & opCode <= (Instruction.LD_2 + 0xFFF) & ((opCode & 0x00FF) == 0x07)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "gdelay V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_3 & opCode <= (Instruction.LD_3 + 0xFFF) & ((opCode & 0x00FF) == 0x0A)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "key V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_4 & opCode <= (Instruction.LD_4 + 0xFFF) & ((opCode & 0x00FF) == 0x15)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "sdelay V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_5 & opCode <= (Instruction.LD_5 + 0xFFF) & ((opCode & 0x00FF) == 0x18)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ssound V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.ADD_1 & opCode <= (Instruction.ADD_1 + 0xFFF) & ((opCode & 0x00FF) == 0x1E)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "adi V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_6 & opCode <= (Instruction.LD_6 + 0xFFF) & ((opCode & 0x00FF) == 0x29)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "font V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_7 & opCode <= (Instruction.LD_7 + 0xFFF) & ((opCode & 0x00FF) == 0x33)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "bcd V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_8 & opCode <= (Instruction.LD_8 + 0xFFF) & ((opCode & 0x00FF) == 0x55)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "str V0, V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_9 & opCode <= (Instruction.LD_9 + 0xFFF) & ((opCode & 0x00FF) == 0x65)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ldr V0, V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.SCD & opCode <= (Instruction.SCD + 0xF)) {
            int lastByte = opCode & 0x00F;
            actualLine = "scdown  " + Integer.toHexString(lastByte).toUpperCase();
            return actualLine;
        }

        if (opCode == Instruction.SCR) {
            actualLine = "scright  ";
            return actualLine;
        }

        if (opCode == Instruction.SCL) {
            actualLine = "scleft ";
            return actualLine;
        }

        if (opCode == Instruction.EXIT) {
            actualLine = "exit  ";
            return actualLine;
        }
        if (opCode == Instruction.LOW) {
            actualLine = "low  ";
            return actualLine;
        }
        if (opCode == Instruction.HIGH) {
            actualLine = "high  ";
            return actualLine;
        }

        if (opCode >= Instruction.DRW_0 & opCode <= (Instruction.DRW_0 + 0x0FFF) & ((opCode & 0x000F) == 0x0)) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            int y = place >> 4 & 0x0F;
            actualLine = "xsprite V" + Integer.toHexString(x).toUpperCase() + ", V" + Integer.toHexString(y).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_A & (opCode & 0x00FF) == 0x30) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "xfont V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_B & (opCode & 0x00FF) == 0x75) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "str r,V" + Integer.toHexString(x).toUpperCase();
            return actualLine;
        }

        if (opCode >= Instruction.LD_C & (opCode & 0x00FF) == 0x85) {
            int place = opCode & 0x0FFF;
            int x = place >> 8;
            actualLine = "ldr V" + Integer.toHexString(x).toUpperCase() + " ,r";
            return actualLine;
        }

        return "";
    }
}
