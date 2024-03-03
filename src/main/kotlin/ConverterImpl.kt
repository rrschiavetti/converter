/**
 * This class represents a Converter that converts milliseconds representation in String into seconds timestamp.
 * @param enableMinutes A flag to enable the conversion of milliseconds into minutes and seconds. Default is false.
 * @param enableHours A flag to enable the conversion of milliseconds into hours, minutes and seconds. Default is false. Overrides `enableMinutes` parameter to true
 */
class ConverterImpl(
     private val enableMinutes: Boolean = false,
     private val enableHours: Boolean = false,
     private val ignoreZero: Boolean = false
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
        /** Text for singular HOUR unit */
        const val SINGULAR_HOUR = "hour"
        /** Text for plural HOUR unit */
        const val PLURAL_HOUR = "hours"
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

        return if (enableHours) {
            val hours = totalSeconds / 3600
            val remainingMinutes = (totalSeconds % 3600) / 60
            val remainingSeconds = (totalSeconds % 60) % 60
            getOutputTimestamp(remainingSeconds, remainingMinutes, hours)
        }else if (enableMinutes) {
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
        if (ignoreZero && seconds == 0L) {
            return ""
        } else{
            val unit = if (seconds == 1L) SINGULAR_SECOND else PLURAL_SECOND
            return "$seconds $unit"
        }

    }

    /**
     * Builds the output string with appropriate units (seconds or second).
     *
     * @param seconds The number of seconds.
     * @param minutes The number of minutes.
     * @return A string timestamp representing the seconds with appropriate units like "3 minutes 47 seconds"
     */
    private fun getOutputTimestamp(seconds: Long, minutes: Long): String {
        val parts = mutableListOf<String>()
        val secondsUnit = if(seconds == 1L) SINGULAR_SECOND else PLURAL_SECOND
        val minutesUnit = if(minutes == 1L) SINGULAR_MINUTE else PLURAL_MINUTE

        if ((ignoreZero && minutes > 0L) || !ignoreZero) {
            parts.add("$minutes $minutesUnit")
        }
        if ((ignoreZero && seconds > 0L )|| !ignoreZero) {
            parts.add("$seconds $secondsUnit")
        }
        return parts.joinToString(" ").trim()
    }

    /**
     * Builds the output string with appropriate units (seconds or second).
     *
     * @param seconds The number of seconds.
     * @param minutes The number of minutes.
     * @param hours The number of hours
     * @return A string timestamp representing the seconds with appropriate units like "2 hours 3 minutes 47 seconds"
     */
    private fun getOutputTimestamp(seconds: Long, minutes: Long, hours: Long): String {
        val parts = mutableListOf<String>()
        val secondsUnit = if(seconds == 1L) SINGULAR_SECOND else PLURAL_SECOND
        val minutesUnit = if(minutes == 1L) SINGULAR_MINUTE else PLURAL_MINUTE
        val hoursUnit = if(hours == 1L) SINGULAR_HOUR else PLURAL_HOUR

        if ((ignoreZero && hours > 0L) || !ignoreZero) {
            parts.add("$hours $hoursUnit")
        }

        if ((ignoreZero && minutes > 0L) || !ignoreZero) {
            parts.add("$minutes $minutesUnit")
        }

        if ((ignoreZero && seconds > 0L )|| !ignoreZero) {
            parts.add("$seconds $secondsUnit")
        }

        return parts.joinToString(" ").trim()
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