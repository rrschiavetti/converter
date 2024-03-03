/**
 * This class represents a Converter that converts milliseconds representation in String into seconds timestamp.
 * @param enableMinutes A flag to enable the conversion of milliseconds into minutes and seconds. Default is false.
 */
class ConverterImpl(
     private val enableMinutes: Boolean = false
): Converter {

    private companion object {
        /** Text for singular SECOND unit */
        const val SINGULAR_SECOND = "second"
        /** Text for plural SECOND unit */
        const val PLURAL_SECOND = "seconds"
        /** Text for singular MINUTE unit */
        const val SINGULAR_MINUTE = "minute"
        /** Text for plural MINUTE unit */
        const val PLURAL_MINUTE = "minutes"
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
        val totalSeconds = millisecondsLong / 1000

        return if (enableMinutes) {
            val minutes = totalSeconds /60
            val remainingSeconds = totalSeconds % 60
            getOutputTimestamp(remainingSeconds, minutes)
        } else {
            getOutputTimestamp(totalSeconds)
        }
    }

    /**
     * Builds the output string with appropriate units (seconds or second).
     *
     * @param seconds The number of seconds.
     * @return A string timestamp representing the seconds with appropriate units.
     * @sample 10 -> "10 seconds"
     */
    private fun getOutputTimestamp(seconds: Long): String {
        val unit = if (seconds == 1L) SINGULAR_SECOND else PLURAL_SECOND
        return "$seconds $unit"
    }

    /**
     * Builds the output string with appropriate units (seconds or second).
     *
     * @param remainSeconds The number of seconds.
     * @param minutes The number of minutes.
     * @return A string timestamp representing the seconds with appropriate units like "3 minutes 47 seconds"
     */
    private fun getOutputTimestamp(remainSeconds: Long, minutes: Long): String {
        val secondsUnit = if(remainSeconds == 1L) SINGULAR_SECOND else PLURAL_SECOND
        val minutesUnit = if(minutes == 1L) SINGULAR_MINUTE else PLURAL_MINUTE
        return "$minutes $minutesUnit $remainSeconds $secondsUnit"
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