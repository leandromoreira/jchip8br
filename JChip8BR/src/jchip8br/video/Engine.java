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
package jchip8br.video;

import java.awt.Color;
import java.awt.Graphics;
import jchip8br.core.Processor;
import jchip8br.util.Logger;
import jchip8br.util.LoggerManager;

/**
 * @author dreampeppers99
 */
public class Engine {

    //private int widhtSchip = 128,  heigthSchip = 64;
    private int widhtSchip = 138,  heigthSchip = 74;
    private int widhtChip = 64,  heigthChip = 32;
    private int xboundary = widhtChip,  yboundary = heigthChip;
    private int pixelsToShift = 2;
    private int[][] vram = new int[widhtChip][heigthChip];
    private final Graphics video;
    private final Processor cpu;
    private static Logger log = LoggerManager.getEngineLogger();
    public static int streetch = 0x6;
    public static Color objects = Color.GREEN,  background = Color.BLACK;
    public static String pixelShape = "Rectangle filled";
    private int bitsWide = 8;
    public static int justDrawAtFrame = 1;
    public static int callingNumber = 3;

    public Engine(Graphics place, Processor processor) {
        video = place;
        cpu = processor;
        video.setColor(Color.GREEN);
    }

    public Engine(Processor processor) {
        video = null;
        cpu = processor;
    }

    public void clearScreen() {
        for (int y = 0; y < yboundary; y++) {
            for (int x = 0; x < xboundary; x++) {
                vram[x][y] = 0x0;
            }
        }
    }

    public void draw(int[] sprite, int x, int y) {
        if (video == null) {
            drawSpriteWithoutRender(y, sprite, x);
        } else {
            drawSprite(y, sprite, x);
        }
        log.debug("It was supose to draw the sprite [0=" + sprite[0] + "," + (sprite.length - 1) + "=" + sprite[sprite.length - 1] + "] at x=" + x + ", y=" + y);
    }

    public void drawFrame() {

        for (int y = 0; y < yboundary; y++) {
            for (int x = 0; x < xboundary; x++) {
                if (vram[x][y] == 0) {
                    video.setColor(background);
                    if (pixelShape.equals("Rectangle")) {
                        video.drawRect(x * streetch, y * streetch, streetch, streetch);
                    }
                    if (pixelShape.equals("Rectangle filled")) {
                        video.drawRect(x * streetch, y * streetch, streetch, streetch);
                        video.fillRect(x * streetch, y * streetch, streetch, streetch);
                    }
                    if (pixelShape.equals("3DRectangle")) {
                        video.draw3DRect(x * streetch, y * streetch, streetch, streetch, true);
                    }
                    if (pixelShape.equals("3DRectangle filled")) {
                        video.draw3DRect(x * streetch, y * streetch, streetch, streetch, true);
                        video.fill3DRect(x * streetch, y * streetch, streetch, streetch, true);
                    }
                    if (pixelShape.equals("Oval")) {
                        video.drawOval(x * streetch, y * streetch, streetch, streetch);
                    }
                    if (pixelShape.equals("Oval filled")) {
                        video.drawOval(x * streetch, y * streetch, streetch, streetch);
                        video.fillOval(x * streetch, y * streetch, streetch, streetch);
                    }
                } else {
                    video.setColor(objects);
                    if (pixelShape.equals("Rectangle")) {
                        video.drawRect(x * streetch, y * streetch, streetch, streetch);
                    }
                    if (pixelShape.equals("Rectangle filled")) {
                        video.drawRect(x * streetch, y * streetch, streetch, streetch);
                        video.fillRect(x * streetch, y * streetch, streetch, streetch);
                    }
                    if (pixelShape.equals("3DRectangle")) {
                        video.draw3DRect(x * streetch, y * streetch, streetch, streetch, true);
                    }
                    if (pixelShape.equals("3DRectangle filled")) {
                        video.draw3DRect(x * streetch, y * streetch, streetch, streetch, true);
                        video.fill3DRect(x * streetch, y * streetch, streetch, streetch, true);
                    }
                    if (pixelShape.equals("Oval")) {
                        video.drawOval(x * streetch, y * streetch, streetch, streetch);
                    }
                    if (pixelShape.equals("Oval filled")) {
                        video.drawOval(x * streetch, y * streetch, streetch, streetch);
                        video.fillOval(x * streetch, y * streetch, streetch, streetch);
                    }
                }
            }
        }
    }

    public int[][] getFrame() {
        return vram;
    }

    public void scroll4PixelsToLeft() {
        log.debug("scroll left " + pixelsToShift);
        int[][] newvram = new int[xboundary][yboundary];
        for (int y = 0; y < yboundary; y++) {
            for (int x = 0; x < xboundary; x++) {
                if (pixelsToShift == 4) {
                    if (x == 134 || x == 135 || x == 136 || x == 137) {
                        newvram[x][y] = 0x0;
                    } else {
                        newvram[x][y] = vram[x + 4][y];
                    }
                } else {
                    if (x == 62 || x == 63) {
                        newvram[x][y] = 0x0;
                    } else {
                        newvram[x][y] = vram[x + 2][y];
                    }
                }
            }
        }
        vram = newvram;
    }

    public void scroll4PixelsToRigth() {
        log.debug("scroll right " + pixelsToShift);
        int[][] newvram = new int[xboundary][yboundary];
        for (int y = 0; y < yboundary; y++) {
            for (int x = 0; x < xboundary; x++) {
                if (pixelsToShift == 4) {
                    if (x == 0 || x == 1 || x == 2 || x == 3) {
                        newvram[x][y] = 0x0;
                    } else {
                        newvram[x][y] = vram[x - 4][y];
                    }
                } else {
                    if (x == 0 || x == 1) {
                        newvram[x][y] = 0x0;
                    } else {
                        newvram[x][y] = vram[x - 2][y];
                    }
                }
            }
        }
        vram = newvram;
    }

    public void scrollDown(int timesToScroll) {
        log.debug("scroll down " + timesToScroll);
        --timesToScroll;
        int[][] newvram = new int[xboundary][yboundary];
        for (int y = 0; y < yboundary; y++) {
            for (int x = 0; x < xboundary; x++) {
                if (y <= timesToScroll) {
                    newvram[x][y] = 0x0;
                } else {
                    newvram[x][y] = vram[x][y - (timesToScroll + 1)];
                }
            }
        }
        vram = newvram;
    }

    public void setHighGraphics() {
        vram = new int[widhtSchip][heigthSchip];
        xboundary = widhtSchip;
        yboundary = heigthSchip;
        pixelsToShift = 0x4;
        bitsWide = 16;
        clearScreen();
    }

    public void setLowGraphics() {
        vram = new int[widhtChip][heigthChip];
        xboundary = widhtChip;
        yboundary = heigthChip;
        pixelsToShift = 0x2;
        bitsWide = 8;
        clearScreen();
    }

    private void drawSprite(int y, int[] sprite, int x) {
        int initY = y;
        int initX = x;
        cpu.dataRegister.V[0xF] = 0x0;

        for (int i = 0; i < sprite.length; i++) {
            char[] line = jchip8br.util.Util.fillIfNeedsWith(bitsWide, "0", Integer.toBinaryString(sprite[i])).toCharArray();
            for (int px = 0; px < bitsWide; px++) {
                if (line[px] == '1') {
                    if (initX + px >= xboundary || initY + i >= yboundary) {
                        log.error("Pixel[1] --> it couldn't be happen' x=" + (initX + px) + ",y=" + (initY + i));
                        break;
                    }
                    if (vram[initX + px][initY + i] == 0x1) {
                        cpu.dataRegister.V[0xF] = 0x1;
                    }
                    vram[initX + px][initY + i] ^= 0x1;
                } 
            }
        }

        if (justDrawAtFrame == callingNumber) {
            drawFrame();
            justDrawAtFrame = 0;
        }
        justDrawAtFrame++;
    }

    private void drawSpriteWithoutRender(int y, int[] sprite, int x) {
        int initY = y;
        int initX = x;
        cpu.dataRegister.V[0xF] = 0x0;

        for (int i = 0; i < sprite.length; i++) {
            char[] line = jchip8br.util.Util.fillIfNeedsWith(bitsWide, "0", Integer.toBinaryString(sprite[i])).toCharArray();
            for (int px = 0; px < bitsWide; px++) {
                if (line[px] == '1') {
                    if (initX + px >= xboundary || initY + i >= yboundary) {
                        log.error("Pixel[1] --> it couldn't be happen' x=" + (initX + px) + ",y=" + (initY + i));
                        break;
                    }
                    if (vram[initX + px][initY + i] == 0x1) {
                        cpu.dataRegister.V[0xF] = 0x1;
                    }
                    vram[initX + px][initY + i] ^= 0x1;
                } 
            }
        }
    }
}
