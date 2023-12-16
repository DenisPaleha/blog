package org.paleha.blog.constanse;

import java.util.Objects;

public final class HashMap {

    private int capacity;
    private String[][] keys;
    private String[][] values;

    public HashMap(int capacity) { // The array size is set when creating an instance
        this.capacity = capacity;
        this.keys = new String[16][capacity]; // Set the size of the table array
        this.values = new String[16][capacity]; // Set the size of the table array
    }

    public int getCapacity() {
        return this.capacity;
    }

    /**
     * This function looks for a cell to write to.
     */
    public void put(String key, String value) {
        int hash = key.hashCode(); // Get the hash code of the key
        int index = hash % this.keys.length; // Divide by the array length and get the remainder, which is the array index
        if (index < 0) { // Sometimes key.hashCode() returns a negative number,
            index = index * -1; // so check and make it positive
        }

        for (int i = 0; i < this.keys[0].length; i++) {
            if (this.keys[index][i] == null) { // If the entry does not exist, write the key and value
                this.keys[index][i] = key;
                this.values[index][i] = value;

                // Dynamic array expansion
                if (i == this.keys[0].length - 1) { // If writing to the last cell of the array, increase the array
                    this.capacity = this.capacity + 1; // Increase the bucket array
                    String[][] tmpKeys = new String[16][this.capacity]; // Set the size of the table array
                    String[][] tmpValues = new String[16][this.capacity]; // Set the size of the table array
                    for (i = 0; i < this.keys.length; i++) { // Iterate through rows
                        for (int j = 0; j < this.keys[0].length; j++) { // Iterate through columns
                            tmpKeys[i][j] = this.keys[i][j];
                            tmpValues[i][j] = this.values[i][j];
                        }
                    }
                    this.keys = tmpKeys;
                    this.values = tmpValues;
                    break;
                }
                break;
            } else if (this.keys[index][i].equals(key)) {
                this.values[index][i] = value;
                break;
            }
        }
    }

    /**
     * This function finds the value by key.
     */
    public String get(String key) {
        boolean exists = hasKey(key);
        if (exists) {
            int hash = key.hashCode(); // Find the hash code of the key
            int index = hash % this.keys.length; // Divide by the array length and get the remainder, which is the array index
            if (index < 0) {
                index = index * -1;
            }
            for (int i = 0; i < this.keys[0].length; i++) {
                if (Objects.equals(this.keys[index][i], key)) { // Compare values, handling null
                    return this.values[index][i];
                }
            }
        }
        return null; // Return null if the key is absent
    }

    /**
     * This method prints the KEYS of the table.
     */
    public void printHashMapKeys() {
        for (String[] key : this.keys) { // Iterate through rows
            for (int j = 0; j < this.keys[0].length; j++) { // Iterate through columns
                System.out.print(" " + key[j] + " "); // Print the element
            }
            System.out.println(); // Line break for visual table formatting
        }
        System.out.println();
    }

    /**
     * This method prints the VALUES of the table.
     */
    public void printHashMapValues() {
        for (String[] value : this.values) { // Iterate through rows
            for (int j = 0; j < this.values[0].length; j++) { // Iterate through columns
                System.out.print(" " + value[j] + " "); // Print the element
            }
            System.out.println(); // Line break for visual table formatting
        }
        System.out.println();
    }

    /**
     * This function returns the number of key-value pairs in the table.
     */
    public int size() {
        int total = 0;

        for (String[] key : this.keys) { // Iterate through rows
            for (int j = 0; j < this.keys[0].length; j++) { // Iterate through columns
                if (key[j] != null) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * This function checks if a pair exists in the table.
     */
    public boolean hasKey(String key) {
        int hash = key.hashCode(); // Get the hash code of the key
        int index = hash % this.keys.length; // Divide by the array length and get the remainder, which is the array index
        if (index < 0) {
            index = index * -1;
        }
        for (int i = 0; i < this.keys[0].length; i++) {
            if (Objects.equals(this.keys[index][i], key)) { // Compare objects using this form to handle null
                return true;
            }
        }
        return false;
    }

    /**
     * This method removes data by key if it exists.
     */
    public void removeFromHashmap(String key) throws Exception {
        boolean exists = hasKey(key);
        if (exists) {
            int hash = key.hashCode(); // Get the hash code of the key
            int index = hash % this.keys.length; // Divide by the array length and get the remainder, which is the array index
            if (index < 0) {
                index = index * -1;
            }
            for (int i = 0; i < this.keys[0].length; i++) {
                if (Objects.equals(this.keys[index][i], key)) { // Compare objects using this form to handle null
                    this.keys[index][i] = null;
                    this.values[index][i] = null;
                }
            }
        } else {
            throw new Exception("You can't delete what doesn't exist...");
        }
    }

    /**
     * This method writes data for org.paleha.calculator_pl.main.Main class.
     */

    public void loadMainHashMap() {
        put("e", ConstantLibrary.EXIT);
        put("exit", ConstantLibrary.EXIT);
        put("выход", ConstantLibrary.EXIT);

        put("m", ConstantLibrary.MEMORY);
        put("м", ConstantLibrary.MEMORY); // рус.
        put("п", ConstantLibrary.MEMORY);

        put("c", ConstantLibrary.CLEAR);
        put("с", ConstantLibrary.CLEAR); // рус.

        put("h", ConstantLibrary.HELP);
        put("help", ConstantLibrary.HELP);
        put("справка", ConstantLibrary.HELP);
        put("помощь", ConstantLibrary.HELP);

        put("info", ConstantLibrary.INFO);
        put("инфо", ConstantLibrary.INFO);

        put("sm", ConstantLibrary.SWITCH_METHOD);
        put("переключить", ConstantLibrary.SWITCH_METHOD);

        put("s", ConstantLibrary.SAVE);
        put("save", ConstantLibrary.SAVE);
        put("сохранить", ConstantLibrary.SAVE);

        put("torom", ConstantLibrary.TO_ROME);
        put("torome", ConstantLibrary.TO_ROME);
        put("римские", ConstantLibrary.TO_ROME);
        put("рим", ConstantLibrary.TO_ROME);

        put("tooct", ConstantLibrary.TO_OCTAL);
        put("oct", ConstantLibrary.TO_OCTAL);
        put("tooctal", ConstantLibrary.TO_OCTAL);

        put("tohex", ConstantLibrary.TO_HEX);
        put("hex", ConstantLibrary.TO_HEX);

        put("tobin", ConstantLibrary.TO_BIN);
        put("binary", ConstantLibrary.TO_BIN);
        put("tobinary", ConstantLibrary.TO_BIN);

        put("outdec", ConstantLibrary.OUT_DEC);
        put("outbin", ConstantLibrary.OUT_BIN);
        put("outoct", ConstantLibrary.OUT_OCT);
        put("outhex", ConstantLibrary.OUT_HEX);
        put("outrom", ConstantLibrary.OUT_ROM);
        put("outrome", ConstantLibrary.OUT_ROM);

        put("outrus", ConstantLibrary.OUT_RUS);
        put("rus", ConstantLibrary.OUT_RUS);
        put("рус", ConstantLibrary.OUT_RUS);
        put("outeng", ConstantLibrary.OUT_ENG);
        put("eng", ConstantLibrary.OUT_ENG);
        put("инг", ConstantLibrary.OUT_ENG);

    }

    /**
     * This method writes data for org.paleha.calculator_pl.main.Core class.
     */

    public void loadCoreHashMap() {
        put("+", ConstantLibrary.PLUS);
        put("plus", ConstantLibrary.PLUS);
        put("плюс", ConstantLibrary.PLUS);

        put("-", ConstantLibrary.MINUS);
        put("minus", ConstantLibrary.MINUS);
        put("минус", ConstantLibrary.MINUS);

        put("*", ConstantLibrary.MULTIPLY);
        put("multiply", ConstantLibrary.MULTIPLY);
        put("умножить", ConstantLibrary.MULTIPLY);

        put("/", ConstantLibrary.DIVIDE);
        put(":", ConstantLibrary.DIVIDE);
        put("divide", ConstantLibrary.DIVIDE);
        put("разделить", ConstantLibrary.DIVIDE);
        put("поделить", ConstantLibrary.DIVIDE);
        put("делить", ConstantLibrary.DIVIDE);

        put("st", ConstantLibrary.EXPONENT);
        put("exponent", ConstantLibrary.EXPONENT);
        put("ст", ConstantLibrary.EXPONENT);
        put("степень", ConstantLibrary.EXPONENT);

        put("root", ConstantLibrary.SQUARE);
        put("square", ConstantLibrary.SQUARE);
        put("sq", ConstantLibrary.SQUARE);
        put("корень", ConstantLibrary.SQUARE);

        put("%", ConstantLibrary.PERCENT);
        put("percent", ConstantLibrary.PERCENT);
        put("процент", ConstantLibrary.PERCENT);
        put("проценты", ConstantLibrary.PERCENT);

    }
}


