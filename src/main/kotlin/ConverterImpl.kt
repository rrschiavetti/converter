/**
 * This class represents a Converter that converts milliseconds representation in String into seconds timestamp.
 */
class ConverterImpl(
    val enableMinutes: Boolean = false
): Converter {

    private companion object {
        /** Text for singular unit */
        const val SINGULAR = "second"
        /** Text for plural unit */
        const val PLURAL = "seconds"
    }

    /**
     * Converts the given milliseconds into seconds.
     *
     * @param milliseconds The milliseconds in string to be converted.
     * @return A string timestamp with the converted seconds with appropriate units (seconds or second).
     * @throws InputIsNotAValidNumberException If the provided milliseconds is not a valid number greater than zero and less than Long.MAX_VALUE.
     * @sample "10000" -> "10 seconds"
     */
    override fun convert(milliseconds: String): String {
        validateInput(milliseconds)

        val millisecondsLong: Long = milliseconds.toLong()

        if (!enableMinutes) {
            val seconds = millisecondsLong / 1000
            return getOutputTimestamp(seconds)
        } else {
            val seconds = millisecondsLong / 1000
            val minutes = seconds /60
            val remainingSeconds = seconds % 60
            return getOutputTimestamp(remainingSeconds, minutes)
        }
    }

    /**
     * Builds the output string with appropriate units (seconds or second).
     *
     * @param seconds The number of seconds.
     * @return A string timestamp representing the seconds with appropriate units.
     *
     */
    private fun getOutputTimestamp(seconds: Long): String {
        val unit = if (seconds == 1L) SINGULAR else PLURAL
        return "$seconds $unit"
    }

    private fun getOutputTimestamp(seconds: Long, minutes: Long): String {
        return "$minutes minutes and $seconds seconds"
    }

    /**
     * Validates the input milliseconds.
     *
     * @param milliseconds The milliseconds in String format to be validated.
     * @throws IllegalArgumentException If the provided milliseconds is not a valid number greater than zero and less than Long.MAX_VALUE.
     */
    private fun validateInput(milliseconds: String) {
        try {
            val millisecondsLong = milliseconds.toLong()
            if (millisecondsLong < 0 ) {
                throw InputIsNotAValidNumberException("Provided milliseconds must be a valid number greater than zero and less than ${Long.MAX_VALUE}")
            }
        } catch (e: NumberFormatException) {
            throw InputIsNotAValidNumberException("Provided milliseconds must be a valid number greater than zero and less than ${Long.MAX_VALUE}")
        }
    }

    /**
     * Custom exception class for indicating that the input string is not a valid number.
     */
    class InputIsNotAValidNumberException(message: String) : IllegalArgumentException(message)
}