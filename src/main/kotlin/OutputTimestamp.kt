
internal class OutputTimestamp(private val shortForm: Boolean, private val ignoreZero: Boolean) {

    private companion object {
        /** Text for singular SECOND unit */
        private const val SINGULAR_SECOND = "second"
        /** Text for plural SECOND unit */
        private const val PLURAL_SECOND = "seconds"
        /** Text for SHORT SECOND unit */
        private const val SHORT_SECOND = "s"
        /** Text for singular MINUTE unit */
        private const val SINGULAR_MINUTE = "minute"
        /** Text for plural MINUTE unit */
        private const val PLURAL_MINUTE = "minutes"
        /** Text for SHORT MINUTE unit */
        private const val SHORT_MINUTE = "m"
        /** Text for singular HOUR unit */
        private const val SINGULAR_HOUR = "hour"
        /** Text for plural HOUR unit */
        private const val PLURAL_HOUR = "hours"
        /** Text for SHORT HOUR unit */
        private const val SHORT_HOUR = "h"
    }

    /**
     * Builds the output string with appropriate units (seconds or second).
     *
     * @param seconds The number of seconds.
     * @param minutes The number of minutes.
     * @param hours The number of hours
     * @return A string timestamp representing the seconds with appropriate units like "2 hours 3 minutes 47 seconds"
     */

     fun getOutputTimestamp(hours: Long? = null, minutes: Long? = null, seconds: Long? = null): String {
        val parts = mutableListOf<String>()

        addPart(parts, hours, if(shortForm) SHORT_HOUR else if(hours == 1L) SINGULAR_HOUR else PLURAL_HOUR)
        addPart(parts, minutes, if(shortForm) SHORT_MINUTE else if(minutes == 1L) SINGULAR_MINUTE else PLURAL_MINUTE)
        addPart(parts, seconds, if(shortForm) SHORT_SECOND else if(seconds == 1L) SINGULAR_SECOND else PLURAL_SECOND)

        return parts.joinToString(" ").trim()
    }

    /**
     * Builds the output string with appropriate units (seconds or second).
     *
     * @param parts List of strings with the representation of the time units adn values
     * @param value value of the time unit
     * @param unit unit of the time
     * @return A string appropriate units like "2 hours"
     */
    private fun addPart(parts: MutableList<String>, value: Long?, unit: String) {
        value?.let {
            if (value > 0 || !ignoreZero) {
                val formattedValue = if (shortForm) "$value$unit" else "$value $unit"
                parts.add(formattedValue)
            }
        }
    }
}