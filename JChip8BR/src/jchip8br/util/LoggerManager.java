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

/**
 * @author dreampeppers99
 */
public class LoggerManager {

    public static Logger getEngineLogger() {
        return Logger.getMockLogger();
    //return Logger.getErrorLogger("VideoEngine");
    }

    public static Logger getMemoryLogger() {
        return Logger.getMockLogger();
    //return Logger.getErrorLogger("Memory");
    }

    public static Logger getControllerLogger() {
        return Logger.getMockLogger();
    //return Logger.getErrorLogger("Controller");
    }

    public static Logger getEmulatorLogger() {
        return Logger.getMockLogger();
    //return Logger.getErrorLogger("Emulator");
    }

    public static Logger getMemoryMapLogger() {
        //return Logger.getDebugLogger("MemoryMap");
        return Logger.getMockLogger();
    }

    public static Logger getCpuLogger() {
        return Logger.getMockLogger();
    // return Logger.getDebugLogger("CPU");
    }
}
