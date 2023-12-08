package org.paleha.blog.decoders;

import org.paleha.blog.numbers.BinaryNumbers;
import org.paleha.blog.numbers.HexNumbers;
import org.paleha.blog.numbers.OctalNumbers;
import org.paleha.blog.numbers.RomeNumerals;

public class DecoderToDecimal {


    public static String decoder(String operand) throws Exception {
        boolean isDecimal = isDouble(operand);
        boolean isRome = RomeNumerals.isRome(operand); // Check the content of the Roman numeral string
        boolean isOctal = OctalNumbers.isOctalNumber(operand); // Check the content of the octal number string
        boolean isHex = HexNumbers.isHexNumber(operand); // Check the content of the hexadecimal number string
        boolean isBinary = BinaryNumbers.isBinaryNumber(operand); // Check the content of the binary number string
//        boolean keyHashMap = coreHashMap.hasKey(operand); // Check if str is a constants.HashMap key

        if (isDecimal) {    // Put the string in a BigDecimal and add it to the stack
            return operand + "Это десятичное число";
        } else if (isRome) {
            return String.valueOf(RomeNumerals.convertRomeToPush(operand));
        } else if (isOctal) {  // Пробрасываем все исключения наверх!!!
            return String.valueOf(OctalNumbers.convertOctalToPush(operand));
        } else if (isHex) {
            return String.valueOf(HexNumbers.hexNumbersToPush(operand));
        } else if (isBinary) {
            return String.valueOf(BinaryNumbers.binaryToPush(operand));
        }else{
            return operand;
        }

    }

        public static boolean isDouble(String str) {
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
}
