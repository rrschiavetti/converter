class Converter {

    fun convert(milliseconds: String): String {
        val millisecondsInt: Int = milliseconds.toInt()

        val result = millisecondsInt / 1000

        return result.toString() + " seconds"

    }
}