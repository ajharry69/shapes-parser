package com.xently.technobrain

object TechnoBrain {

    /**
     * "p,j$i" should return "i,j$p" and "k,j$pi" should return "i,j$pk"
     */
    fun reversePreservingSpecialCharacters(input: String): String {
        // Any character not in this list will maintain its position in the output position.
        val alphaNumerics = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val outputCharacters = input.toCharArray()

        var indexToSwitchFromTheEnd = input.length - 1

        for (index in input.indices) {
            if (indexToSwitchFromTheEnd <= index) {
                // Don't go all the way to the end of the list since we are switching from both ends
                break
            }

            if (input[index] !in alphaNumerics) {
                indexToSwitchFromTheEnd--
                continue
            }

            if (input[indexToSwitchFromTheEnd] !in alphaNumerics) {
                indexToSwitchFromTheEnd--
                continue
            }

            outputCharacters[index] = input[indexToSwitchFromTheEnd]
            outputCharacters[indexToSwitchFromTheEnd] = input[index]
            indexToSwitchFromTheEnd--
        }

        return outputCharacters.joinToString("")
    }
}