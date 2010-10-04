/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jchip8br.core;

import jchip8br.util.Util;

/**
 *
 * @author magnus.wedmark
 */


 public enum eInstruction
{                               //v1,  v2,  val8,addr12,val4
    CLS ("CLS", 0x00E0, 0xFFFF,false,false,false,false,false),
    RET ("RET","RTS", 0x00EE, 0xFFFF,false,false,false,false,false),
    JMP ("JMP", 0x1000, 0xF000,false,false,false,true,false),
    CALL ("CALL","JSR", 0x2000, 0xF000,false,false,false,true,false),
    SE_XNN  ("SE","SKEQ", 0x3000, 0xF000,true,false,true,false,false),
    SNE_XNN ("SNE","SKNE", 0x4000, 0xF000,true,false,true,false,false),
    SE_XY ("SE","SKEQ", 0x5000, 0xF000,true,true,false,false,false),
    LD  ("LD","MOV", 0x6000, 0xF000,true,false,true,false,false),
    ADD_NN2X ("ADD", 0x7000, 0xF000,true,false,true,false,false),
    LD_Y2X ("LD","MOV", 0x8000, 0xF00F,true,true,false,false,false),
    OR  ("OR", 0x8001, 0xF00F,true,true,false,false,false),
    AND ("AND", 0x8002, 0xF00F,true,true,false,false,false),
    XOR ("XOR", 0x8003, 0xF00F,true,true,false,false,false),
    ADD_Y2X("ADD", 0x8004, 0xF00F,true,true,false,false,false),
    SUB ("SUB", 0x8005, 0xF00F,true,true,false,false,false),
    SHR ("SHR", 0x8006, 0xF00F,true,false,false,false,false),
    SUBN ("SUBN","RSB", 0x8007, 0xF00F,true,true,false,false,false),
    SHL ("SHL", 0x800E, 0xF00F,true,false,false,false,false),
    SNE_XY ("SNE","SKNE", 0x9000, 0xF00F,true,true,false,false,false),
    LD_NNN2I ("LD","MOVI", 0xA000, 0xF000,false,false,false,true,false),
    JMP_V0 ("JMP","JMI", 0xB000, 0xF000,false,false,false,true,false), //V0
    RND ("RND","RAND", 0xC000, 0xF000,true,false,true,false,false),
    DRW_16 ("DRW","XSPRITE", 0xD000, 0xF00F,true,true,false,false,false), // must be before DRW, maybe a single DRW for both?
    DRW ("DRW","SPRITE", 0xD000, 0xF000,true,true,false,false,true),
    SKP ("SKP","SKPR", 0xE09E, 0xF0FF,true,false,false,false,false),
    SKNP ("SKNP","SKUP", 0xE0A1, 0xF0FF,true,false,false,false,false),
    LD_D2X ("LD","GDELAY", 0xF007, 0xF0FF,true,false,false,false,false),
    LD_K ("LD","KEY", 0xF00A, 0xF0FF,true,false,false,false,false),
    LD_D ("LD","SDELAY", 0xF015, 0xF0FF,true,false,false,false,false),
    LD_S ("LD","SSOUND", 0xF018, 0xF0FF,true,false,false,false,false),
    ADD_X2I ("ADD","ADI", 0xF01E, 0xF0FF,true,false,false,false,false),
    LD_F ("LD","FONT", 0xF029, 0xF0FF,true,false,false,false,false),
    LD_B ("LD","BCD", 0xF033, 0xF0FF,true,false,false,false,false),
    LD_X2M ("LD","STR", 0xF055, 0xF0FF,true,false,false,false,false),
    LD_M2X ("LD","LDR", 0xF065, 0xF0FF,true,false,false,false,false),
//    /*ignored*/
    Call_1802 ("C1802", 0x0000, 0xF000,false,false,false,false,false),
//    /*SChip8*/
    SCD ("SCD","SCDOWN", 0x00C0, 0xFFFF,false,false,false,false,false),
    SCR ("SCR","SCRIGHT", 0x00FB, 0xFFFF,false,false,false,false,false),
    SCL ("SCL","SCLEFT", 0x00FC, 0xFFFF,false,false,false,false,false),
    EXIT ("EXIT", 0x00FD, 0xFFFF,false,false,false,false,false),
    LOW ("LOW", 0x00FE, 0xFFFF,false,false,false,false,false),
    HIGH ("HIGH", 0x00FF, 0xFFFF,false,false,false,false,false),
   //DRW_0 ("DRW", 0xD000, 0xF00F,false,false,false,false,false), moved up
    LD_A ("LD","FONTX", 0xF030, 0xF0FF,false,false,false,false,false), //FONTX
    LD_X2HP ("LD","STR", 0xF075, 0xF0FF,true,false,false,false,false),
    LD_HP2X ("LD","LDR", 0xF085, 0xF0FF,true,false,false,false,false);

    static eAssemblerType assemblerType;

    public static eAssemblerType AssemblerType() {return assemblerType;}

    public static void SetAssemblerType(eAssemblerType newAssemblerType)
    {
        assemblerType = newAssemblerType;
    }

    @Override
    public String toString()
    {
        return Opcode()+GetParameterFormat() + GetDebugRegisterDecoding();
    }

    static eInstruction DecodeInstruction(int opCode) throws Exception
    {
        for(eInstruction inst : eInstruction.values())
        {
            if(inst.Value() == (opCode&inst.usedBitMask))
            {
                inst.instruction = opCode;
                return inst;
            }
        }
        //throw new EnumConstantNotPresentException(, "");
        throw new Exception(String.format("Opcode {0} not found", opCode));
    }

    // make public if needed
    private static boolean IsSameInstructionOpcode(eInstruction inst1, eInstruction inst2)
    {
        return (inst1 == inst2);
    }

    boolean IsSameInstructionOpcode(eInstruction compareInstruction)
    {
        return compareInstruction == this;
    }

    String GetDebugRegisterDecoding()
    {
      StringBuilder sb = new StringBuilder();

      if( (v1|v2|addr12|val4) == true)
        sb.append("{ ");

      if(v1) sb.append(String.format("vx=%1$s ", Util.FormatHexNumber(1,Pvx,false) ) );
      if(v2) sb.append(String.format("vy=%1$s ",  Util.FormatHexNumber(1,Pvy,false)) );
      if(addr12) sb.append(String.format("addr=%1$s ", Util.FormatHexAdress(Paddr12, false)) );
      if(val4) sb.append(String.format("val4=%1$s ", Util.FormatHexNumber(1,Pval4,false)) );
      if(val8) sb.append(String.format("val8=%1$s ", Util.FormatHexNumber(2,Pval8,false)) );

      if( (v1|v2|addr12|val4) == true)
          sb.append(" }");
      
      return sb.toString();
    }

     //extract and format values v1,v2,val8,addr12,val4
    String GetParameterFormat()
    {
        StringBuilder sb = new StringBuilder();

        // only render these prefix ValueFormats for DavidWinterWay assembler
        if(assemblerType == eAssemblerType.DavidWinterWay)
        {
            // if LD_F (font load) is used print F as first par
            if(IsSameInstructionOpcode(eInstruction.LD_F))
                sb.append("F");

            // if LD_D (font load) is used print D as first par
            if(IsSameInstructionOpcode(eInstruction.LD_D))
                sb.append("D");

            // if LD_D (font load) is used print D as first par
            if(IsSameInstructionOpcode(eInstruction.LD_NNN2I) |
               IsSameInstructionOpcode(eInstruction.ADD_X2I))
                sb.append("I");
        }
        else // MichaelToren
        {
            if(IsSameInstructionOpcode(eInstruction.LD_X2HP))
                sb.append("R");

            if(IsSameInstructionOpcode(eInstruction.LD_M2X) |
                IsSameInstructionOpcode(eInstruction.LD_X2M))
                sb.append("V0");
        }

        if(v1)
        {
            Pvx = (instruction&0x0F00)>>8;
            sb.append(CommaIfNotEmpty(sb.length()))
              .append("V")
              .append(Integer.toHexString(Pvx).toUpperCase());
        }
        if(v2)
        {
            Pvy = (instruction&0x00F0)>>4;
            sb.append(CommaIfNotEmpty(sb.length()))
              .append("V")
              .append(Integer.toHexString(Pvy).toUpperCase());
        }
        if(val8)
        {
            Pval8 = instruction&0x00FF;
            sb.append(CommaIfNotEmpty(sb.length()))
              .append(Util.FormatHexNumber(2,Pval8,true));
        }
        if(addr12)
        {
            Paddr12 = instruction&0x0FFF;
            sb.append(CommaIfNotEmpty(sb.length()))
              .append(Util.FormatHexNumber(3,Paddr12,false));
        }
        if(val4)
        {
            Pval4 = instruction&0x000F;
            sb.append(CommaIfNotEmpty(sb.length()))
              .append(Util.FormatHexNumber(1,Pval4,false));
        }

         // only render these prefix ValueFormats for DavidWinterWay assembler
        if(assemblerType == eAssemblerType.DavidWinterWay)
        {
            if(IsSameInstructionOpcode(eInstruction.LD_M2X))
            {
                sb.append(CommaIfNotEmpty(sb.length()))
                  .append("[I]");
            }
        }
        else
        {
            if(IsSameInstructionOpcode(eInstruction.LD_HP2X))
            {
                sb.append(CommaIfNotEmpty(sb.length()))
                .append("R");
            }
        }

        
        if(sb.length() != 0)
            sb.insert(0, " ");
        
        return sb.toString();
    }

    private static String CommaIfNotEmpty(int size)
    {
        return size!=0?", ":"";
    }

    private final String dwOpcode;
    private final String mtOpcode;
    private final int value;
    private final int usedBitMask;
    private final boolean v1;
    private final boolean v2;
    private final boolean val8;
    private final boolean addr12;
    private final boolean val4;
    private int instruction;
    private int Pvx;
    private int Pvy;
    private int Pval8;
    private int Paddr12;
    private int Pval4;

    private static int opcodeLength = assemblerType==eAssemblerType.DavidWinterWay?5:8;

    eInstruction(String dwOpcode, String mtOpcode, int value, int usedBitMask,
            boolean v1, boolean v2, boolean val8, boolean addr12, boolean val4)
    {
        this.dwOpcode = dwOpcode;
        this.mtOpcode = mtOpcode;
        this.value = value;
        this.usedBitMask = usedBitMask;
        this.v1 = v1;
        this.v2 = v2;
        this.val8 = val8;
        this.addr12 = addr12;
        this.val4 = val4;
    }

    eInstruction(String opcode, int value, int usedBitMask,
            boolean v1, boolean v2, boolean val8, boolean addr12, boolean val4)
     {
        this(opcode,opcode,value,usedBitMask,v1,v2,val8,addr12,val4);
     }

    public String Opcode()  { return Util.appendSpacesToEnd(assemblerType==eAssemblerType.DavidWinterWay?dwOpcode:mtOpcode,opcodeLength); }
    public int Value()      { return value; }
    public int Instruction(){ return instruction; }
}
