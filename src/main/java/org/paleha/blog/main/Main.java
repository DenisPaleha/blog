package org.paleha.blog.main;

import org.paleha.blog.constanse.HashMap;
import org.paleha.blog.exceptions.*;
import org.paleha.blog.logger.*;
import org.paleha.blog.memory.FileOperator;
import org.paleha.blog.numbers.BinaryNumbers;
import org.paleha.blog.numbers.HexNumbers;
import org.paleha.blog.numbers.OctalNumbers;
import org.paleha.blog.numbers.RomeNumerals;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;

import static org.paleha.blog.constanse.ConstantLibrary.*;

@Component
public class Main {

    String memoryFileName = "Memory.txt"; // Name of the memory file
    String output; // Declare output string
    State state = new State();
    Core core = new Core(state);
    AbstractLogger logger = new LoggerPl();
    HashMap hashmapMain = new HashMap(8);
    String memory = loadFromMemoryFile(state, memoryFileName);

    public Main() throws LoggerException, IOException {
        hashmapMain.loadMainHashMap();
        state.loadFromPrepared(memory);
    } // Сюда пожалуй нужно еще добавить соответствующие исключения с самого низа.


    public String calculator(String userInput) throws LoggerException, IOException {


        logger.logOutput(userInput, "in"); // Copy all input data to the logger

        State copy = state.copyState();  // Copy the State class instance to insert in case of expression reading error

        Scanner lineScanner = new Scanner(userInput).useLocale(Locale.ENGLISH); // Scan the incomingData variable

        // Insert possible values supported by the calculator
        try {
            while (lineScanner.hasNext()) { // Scan the incomingData string
                // Если строка содержит что-то, то ее фрагмент сохраняется в строку операнд: +++
                String operand = lineScanner.next(); // Split the incomingData string with input data into parts in order
                operand = operand.toLowerCase(); // Convert everything to lowercase
                boolean coreNotUsed; // Method core.calculator(str) trigger switch
                try {
                    coreNotUsed = core.calculator(operand);
                } catch (ConversionException wrongNumber) {
                    logger.logOutput(wrongNumber.getMessage(), "out");
                    return wrongNumber.getMessage();
                }

                boolean keyHashMap = hashmapMain.hasKey(operand); // Check if str is a constants.HashMap key
                if (keyHashMap) { // If the string matches an existing key
                    operand = hashmapMain.get(operand); // Assign str a value by constants.HashMap key
                    try {
                        if (operand.equals(CLEAR)) { // Memory clearing function, "c" command
                            state.clear(); // Clear memory
                            output = state.getPhrase("memory_cleared");
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(INFO)) { // Screen output function, "info" command
                            String info;
                            if (state.isEnglish()) {
                                info = state.infoEng();
                            } else {
                                info = state.infoRus();
                            }
                            logger.logOutput(info, "out");
                            return info;

                        } else if (operand.equals(SWITCH_METHOD)) {  // Data structure switching function Array/List
                            boolean storageType = state.isArray();
                            storageType = !storageType; // Change the current boolean value to the opposite
                            state.setStorageType(storageType); // Change the stack data structure to the opposite and transfer the content
                            if (storageType) {
                                output = state.getPhrase("now_calculator_uses_Array");
                            } else {
                                output = state.getPhrase("now_calculator_uses_List");
                            }
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(SAVE)) {  //  "S" command Saves all data to MemoryTwo.txt
                            output = state.getPhrase("data_saved");
                            logger.logOutput(output, "out");
                            saveStateToMemoryFile(state, memoryFileName);
                            return output;

                        } else if (operand.equals(TO_ROME)) { // "ToRome" function, converts memory to Roman numeral
                            double mem = state.memoryResult.doubleValue(); // Convert BigDecimal to double
                            String result = RomeNumerals.convertDecimalToRome(mem);
                            output = String.format(state.getPhrase("roman_number_equal"), result);
                            logger.logOutput(output, "out");
                            return output; // Roman numeral

                        } else if (operand.equals(TO_OCTAL)) {
                            int num = state.memoryResult.intValue(); // Convert BigDecimal to int with rounding to the nearest integer
                            String result = OctalNumbers.convertDecimalToOctal(num); // Conversion
                            output = String.format(state.getPhrase("octal_number_equal"), result);
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(TO_HEX)) {
                            int num = state.memoryResult.intValue(); // Convert BigDecimal to int with rounding to the nearest integer
                            String result = HexNumbers.convertDecimalToHex(num); // Conversion
                            output = String.format(state.getPhrase("hexadecimal_number_equal"), result);
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(TO_BIN)) {
                            int num = state.memoryResult.intValue(); // Convert BigDecimal to int with rounding to the nearest integer
                            String result = BinaryNumbers.convertDecimalToBinary(num); // Conversion
                            output = String.format(state.getPhrase("binary_number_equal"), result);
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(OUT_DEC)) { // Automatically converts all results to
                            state.setNumberSystem(OUT_DEC);
                            output = state.getPhrase("decimal_enabled"); // Decimal number system enabled
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(OUT_BIN)) {
                            state.setNumberSystem(OUT_BIN);
                            output = state.getPhrase("binary_enabled"); // Binary number system enabled
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(OUT_OCT)) {
                            state.setNumberSystem(OUT_OCT);
                            output = state.getPhrase("octal_enabled"); // Octal number system enabled
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(OUT_HEX)) {
                            state.setNumberSystem(OUT_HEX);
                            output = state.getPhrase("hexadecimal_enabled"); // Hexadecimal number system enabled
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(OUT_ROM)) {
                            state.setNumberSystem(OUT_ROM);
                            output = state.getPhrase("roman_enabled"); // Roman number system enabled
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(OUT_RUS)) { // Language switch
                            state.setLanguage(false);
                            output = "Язык: Русский";
                            logger.logOutput(output, "out");
                            return output;

                        } else if (operand.equals(OUT_ENG)) { // Language switch
                            state.setLanguage(true);
                            output = "Language: English";
                            logger.logOutput(output, "out");
                            return output;
                        }

                    } catch (OutOfRangeException e) { // operand.equals(TO_ROME)
                        output = e.getMessage();
                        logger.logOutput(output, "out");
                        return output; // Roman numeral

                    } catch (IOException e) { // logger.CopyFilesFromLoggerToTemp()
                        logger.logOutput(e.getMessage(), "out");
                        return e.getMessage();
                    }
                } else {
                    if (coreNotUsed) {
                        output = String.format(state.getPhrase("unknown_value"), operand);
                        logger.logOutput(output, "out");
                        return output;
                    }
                }
            }

            state.memoryResult = state.peek();

            BigDecimal out = state.memoryResult.setScale(2, RoundingMode.HALF_UP); // Round the result to two decimal places for display
            String result;
            try {
                result = state.universalConverter(out);
            } catch (OutOfRangeException e) {
                result = out.toString();
                result += " " + e.getMessage();
            }
            output = String.format(state.getPhrase("result"), result);
            logger.logOutput(output, "out"); // Result
            return output;

        } catch (IOException e) {  // Нужно ли обрабатывать это исключение или же достаточно пробросить его в конвертор?
            state = copy; //  return previous State values
            output = state.getPhrase("output_error, previous State values reloaded");
            logger.logOutput(output, "out");
            return output;
        } catch (Exception e) { // Нужно ли обрабатывать здесь эти ошибки или достаточно пробросить в конвертор?
            // Тут по идее должны вываливаться сообщения вообще обо всех ошибках.
            return e.getMessage();
        }
    }

    /**
     * The function gets a string from State and writes it to memoryFileName // Out of test
     */
    public static void saveStateToMemoryFile(State state, String memoryFileName) throws IOException {  // Make the method return a String for testing purposes
        try {
            String allMemory = state.prepareForSave();
            FileOperator memoryOperator = new FileOperator();
            memoryOperator.wroteToMemoryFile(allMemory, memoryFileName);
        } catch (IOException e) {
            throw new IOException("Can't save memory to the file " + memoryFileName);
        }
    }

    /**
     * The function gets a string from memoryFileName and writes it to State // Out of test
     */
    public static String loadFromMemoryFile(State state, String memoryFileName) throws IOException {
        String fileContent;

        FileOperator memoryOperator = new FileOperator();
        if (!memoryOperator.isFileExist(memoryFileName)) {  // Check if the file exists
            saveStateToMemoryFile(state, memoryFileName); // If the file does not exist, save the state
        }
        fileContent = memoryOperator.readFromMemoryFile(memoryFileName);

        return fileContent;
    }

}

