
/**
 * This class represents a Converter that converts milliseconds representation in String into seconds timestamp.
 * @param enableMinutes A flag to enable the conversion of milliseconds into minutes and seconds. Default is false.
 * @param enableHours A flag to enable the conversion of milliseconds into hours, minutes and seconds. Default is false. Overrides `enableMinutes` parameter to true
 */
class ConverterImpl(
     private val enableMinutes: Boolean = false,
     private val enableHours: Boolean = false,
     private val ignoreZero: Boolean = false,
     private val shortForm: Boolean = false
): Converter {

    private val outputTimestamp = OutputTimestamp(shortForm, ignoreZero)

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

        return when {
            enableHours -> outputTimestamp.getOutputTimestamp(seconds = (totalSeconds % 60) % 60, minutes = (totalSeconds % 3600) / 60, hours =  totalSeconds / 3600)
            enableMinutes -> outputTimestamp.getOutputTimestamp(seconds = totalSeconds % 60, minutes = totalSeconds / 60)
            else -> outputTimestamp.getOutputTimestamp(seconds = totalSeconds)
        }
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