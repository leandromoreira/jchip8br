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
public class Logger {

    private String name;
    private boolean levelDebugIsActive,  levelWarnIsActive,  levelErrorIsActive;


     public static Logger getMockLogger() {
        Logger debugger = new Logger("Mock");
        debugger.desactiveDebugLevel();
        debugger.desactiveWarnLevel();
        debugger.desactiveErrorLevel();
        return debugger;
    }

    public static Logger getDebugLogger(String name) {
        Logger debugger = new Logger(name);
        debugger.activeDebugLevel();
        return debugger;
    }

    public static Logger getWarnLogger(String name) {
        Logger debugger = new Logger(name);
        debugger.activeWarnLevel();
        return debugger;
    }

    public static Logger getErrorLogger(String name) {
        Logger debugger = new Logger(name);
        debugger.activeErrorLevel();
        return debugger;
    }

    public Logger(String name) {
        this.name = name;
        levelDebugIsActive = levelWarnIsActive = false;
        levelErrorIsActive = true;
    }

    public void activeDebugLevel() {
        levelDebugIsActive = true;
    }

    public void activeWarnLevel() {
        levelWarnIsActive = true;
    }

    public void activeErrorLevel() {
        levelErrorIsActive = true;
    }

    public void desactiveDebugLevel() {
        levelDebugIsActive = false;
    }

    public void desactiveWarnLevel() {
        levelWarnIsActive = false;
    }

    public void desactiveErrorLevel() {
        levelErrorIsActive = false;
    }

    public void debug(String msg) {
        if (levelDebugIsActive) {
            System.out.println("[" + name + "]\t DEBUG:\t" + msg);
        }
    }

    public void warn(String msg) {
        if (levelWarnIsActive || levelDebugIsActive) {
            System.out.println("[" + name + "]\t WARNI:\t" + msg);
        }
    }

    public void error(String msg) {
        if (levelErrorIsActive || levelWarnIsActive || levelDebugIsActive) {
            System.err.println("[" + name + "]\t ERROR:\t" + msg);
        }
    }
}
