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
package jchip8br.control;

import jchip8br.util.Logger;
import jchip8br.util.LoggerManager;

/**
 * @author dreampeppers99
 */
public class Controller {

    private static Controller instance;
    private static Logger log = LoggerManager.getControllerLogger();
    public static final int RELEASED_CODE = 0xFF;
    public static final int BUTTON_0 = 0x0,
                            BUTTON_1 = 0x1,
                            BUTTON_2 = 0x2,
                            BUTTON_3 = 0x3,
                            BUTTON_4 = 0x4,
                            BUTTON_5 = 0x5,
                            BUTTON_6 = 0x6,
                            BUTTON_7 = 0x7,
                            BUTTON_8 = 0x8,
                            BUTTON_9 = 0x9,
                            BUTTON_A = 0xA,
                            BUTTON_B = 0xB,
                            BUTTON_C = 0xC,
                            BUTTON_D = 0xD,
                            BUTTON_E = 0xE,
                            BUTTON_F = 0xF;
    public static int KEY_1 = 49;
    public static int KEY_2 = 50;
    public static int KEY_3 = 51;
    public static int KEY_C = 52;
    public static int KEY_4 = 81;
    public static int KEY_5 = 87;
    public static int KEY_6 = 69;
    public static int KEY_D = 82;
    public static int KEY_7 = 65;
    public static int KEY_8 = 83;
    public static int KEY_9 = 68;
    public static int KEY_E = 70;
    public static int KEY_A = 90;
    public static int KEY_0 = 88;
    public static int KEY_B = 67;
    public static int KEY_F = 86;
    private static int PRESSED_BUTTON;

    public static Controller getController() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
        PRESSED_BUTTON = RELEASED_CODE;
    }

    public boolean isThisKeyPressed(int key) {

        log.debug("is this pressed? key=0x" + jchip8br.util.Util.fillIfNeedsWith(2, "0", Integer.toHexString(key).toUpperCase()));
        if (PRESSED_BUTTON == key) {
            log.debug("yes, it was pressed");
            return true;
        } else {
            log.debug("No, it wasn't pressed, the pressed key was 0x" + Integer.toHexString(PRESSED_BUTTON).toUpperCase());
            return false;
        }
    }
    

    public void pressKey(int keyCode) {
        if (keyCode == KEY_0){
            PRESSED_BUTTON = BUTTON_0;
            return;
        }
        if (keyCode == KEY_1){
            PRESSED_BUTTON = BUTTON_1;
            return;
        }
        if (keyCode == KEY_2){
            PRESSED_BUTTON = BUTTON_2;
            return;
        }
        if (keyCode == KEY_3){
            PRESSED_BUTTON = BUTTON_3;
            return;
        }
        if (keyCode == KEY_4){
            PRESSED_BUTTON = BUTTON_4;
            return;
        }
        if (keyCode == KEY_5){
            PRESSED_BUTTON = BUTTON_5;
            return;
        }
        if (keyCode == KEY_6){
            PRESSED_BUTTON = BUTTON_6;
            return;
        }
        if (keyCode == KEY_7){
            PRESSED_BUTTON = BUTTON_7;
            return;
        }
        if (keyCode == KEY_8){
            PRESSED_BUTTON = BUTTON_8;
            return;
        }
        if (keyCode == KEY_9){
            PRESSED_BUTTON = BUTTON_9;
            return;
        }
        if (keyCode == KEY_A){
            PRESSED_BUTTON = BUTTON_A;
            return;
        }
        if (keyCode == KEY_B){
            PRESSED_BUTTON = BUTTON_B;
            return;
        }
        if (keyCode == KEY_C){
            PRESSED_BUTTON = BUTTON_C;
            return;
        }
        if (keyCode == KEY_D){
            PRESSED_BUTTON = BUTTON_D;
            return;
        }
        if (keyCode == KEY_E){
            PRESSED_BUTTON = BUTTON_E;
            return;
        }
        if (keyCode == KEY_F){
            PRESSED_BUTTON = BUTTON_F;
            return;
        }
        log.debug("you pressed 0x" + Integer.toHexString(PRESSED_BUTTON).toUpperCase());
    }

    public void releaseKey(int keyCode) {
        log.debug("You are trying to release the 0x"+Integer.toHexString(keyCode).toUpperCase() + " and you will release the 0x" + Integer.toHexString(PRESSED_BUTTON).toUpperCase());
        PRESSED_BUTTON = RELEASED_CODE;
    }

    public void setControllerConfig(int[] controller) {
        log.debug("configuring the control with "+controller);
        KEY_0 = controller[0x0];
        KEY_1 = controller[0x1];
        KEY_2 = controller[0x2];
        KEY_3 = controller[0x3];
        KEY_4 = controller[0x4];
        KEY_5 = controller[0x5];
        KEY_6 = controller[0x6];
        KEY_7 = controller[0x7];
        KEY_8 = controller[0x8];
        KEY_9 = controller[0x9];
        KEY_A = controller[0xA];
        KEY_B = controller[0xB];
        KEY_C = controller[0xC];
        KEY_D = controller[0xD];
        KEY_E = controller[0xE];
        KEY_F = controller[0xF];
    }

    public int waitingKey() {
        log.debug("waiting key...");
        while (PRESSED_BUTTON == RELEASED_CODE) {
        }
        log.debug("key preesed");
        return PRESSED_BUTTON;
    }
}
