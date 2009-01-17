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

import java.awt.Graphics;
import java.nio.ByteBuffer;
import jchip8br.util.Logger;
import jchip8br.util.LoggerManager;
import jchip8br.video.Engine;

/**
 * @author dreampeppers99
 */
public class Emulator implements Runnable {

    private Processor cpu;
    private static boolean paused = false;
    private static boolean stopped = false;
    private static Engine video;
    private static Logger log = LoggerManager.getEmulatorLogger();
    private static int numberOfInstructions = 750;

    public static void setSpeed(int newSpeed) {
        numberOfInstructions = newSpeed * 5;
    }

    public Emulator(Graphics graphics) {
        log.debug("initializing Chip8");
        cpu = new Processor();
        paused = stopped = false;
        video = new Engine(graphics, cpu);
    }

    public short[] getMemory() {
        return cpu.getMemory().getACopy();
    }

    public void reset() {
        log.debug("reseting Chip8");
        cpu.reset();
    }

    public void fillMemory(ByteBuffer rom, long size) {
        Memory memory = cpu.getMemory();
        memory.clear();
        for (long i = 0; i < size; i++) {
            memory.writeAt((int) i + 0x200, jchip8br.util.Util.readUnsignedByte(rom));
        }
    }

    public String showMemory() {
        return cpu.getMemory().toString();
    }

    public String showProgramMemory() {
        return cpu.getMemory().getProgram();
    }

    public void step() {
        cpu.step();
        if (cpu.delayTimerDT != 0x00) {
            cpu.delayTimerDT -= 0x01;
        }
        if (cpu.soundTimerST != 0x00) {
            playSound();
            cpu.soundTimerST -= 0x01;
        }
    }

    public static void pause() {
        paused = !paused;
        log.debug(paused ? "Chip8 was paused" : "Chip8 was unpaused");
    }

    public static void stop() {
        paused = true;
        stopped = true;
        log.debug("Chip8 was stopped");
    }

    public static void init() {
        stopped = false;
        paused = false;
        log.debug("Chip8 was put to init");
    }

    @Override
    public void run() {
        while (!stopped) {
            while (!paused) {
                for (int i = 0; i < numberOfInstructions; i++) {
                    cpu.step();
                }
                if (cpu.delayTimerDT != 0x00) {
                    cpu.delayTimerDT -= 0x01;
                }
                if (cpu.soundTimerST != 0x00) {
                    playSound();
                    cpu.soundTimerST -= 0x01;
                }
            }
        }
    }

    public String showCurrentAssembler() {
        return cpu.showCurrentLineAssembler();
    }

    public String stack() {
        return cpu.stack();
    }

    public String DT() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.delayTimerDT).toUpperCase());
    }

    public String ST() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.soundTimerST).toUpperCase());
    }

    public String I() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.addressRegisterI).toUpperCase());
    }

    public String PC() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.programCounter).toUpperCase());
    }

    public String SP() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.stackPointer).toUpperCase());
    }

    public String V0() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0x0]).toUpperCase());
    }

    public String V1() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0x1]).toUpperCase());
    }

    public String V2() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0x2]).toUpperCase());
    }

    public String V3() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0x3]).toUpperCase());
    }

    public String V4() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0x4]).toUpperCase());
    }

    public String V5() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0x5]).toUpperCase());
    }

    public String V6() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0x6]).toUpperCase());
    }

    public String V7() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0x7]).toUpperCase());
    }

    public String V8() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0x8]).toUpperCase());
    }

    public String V9() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0x9]).toUpperCase());
    }

    public String VA() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0xA]).toUpperCase());
    }

    public String VB() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0xB]).toUpperCase());
    }

    public String VC() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0xC]).toUpperCase());
    }

    public String VD() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0xD]).toUpperCase());
    }

    public String VE() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0xE]).toUpperCase());
    }

    public String VF() {
        return jchip8br.util.Util.fillIfNeedsWith(4, "0", Integer.toHexString(cpu.dataRegister.V[0xF]).toUpperCase());
    }

    public static void setVideo(Engine vi) {
        video = vi;
    }

    public static Engine getVideo() {
        return video;
    }

    private void playSound() {
        log.debug("playsound still not implemented");
    }
}
