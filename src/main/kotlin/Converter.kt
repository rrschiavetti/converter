class Converter {

    fun convert(milliseconds: String): String {

        try {
            val millisecondsLong = milliseconds.toLong()
            if (millisecondsLong < 0 ) {
                throw InputIsNotAValidNumberException("Provided milliseconds must be a valid number greater than zero and less than ${Long.MAX_VALUE}")
            }
        } catch (e: NumberFormatException) {
            throw InputIsNotAValidNumberException("Provided milliseconds must be a valid number greater than zero and less than ${Long.MAX_VALUE}")
        }

        val millisecondsInt: Long = milliseconds.toLong()

        val result = millisecondsInt / 1000

        return if (result == 1L) result.toString() + " second" else result.toString() + " seconds"
    }

    class InputIsNotAValidNumberException(message: String) : IllegalArgumentException(message)
}