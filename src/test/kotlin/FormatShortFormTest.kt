import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class FormatShortFormTest {

    @ParameterizedTest
    @MethodSource
    fun `should convert a valid string representation of milliseconds to hours, minutes and seconds timestamp ignoring zero values in short form`(input: String, expected: String) {
        val sut = ConverterImpl(enableHours = true, ignoreZero = true, shortForm = true)
        sut.convert(input) shouldBe expected
    }

    @ParameterizedTest
    @MethodSource
    fun `should convert a valid string representation of milliseconds to hours, minutes and seconds timestamp in long form`(input: String, expected: String) {
        val sut = ConverterImpl(enableHours = true, shortForm = true)
        sut.convert(input) shouldBe expected

    }

    companion object {

        @JvmStatic
        fun `should convert a valid string representation of milliseconds to hours, minutes and seconds timestamp ignoring zero values in short form`() = listOf(
            Arguments.of("227000", "3m 47s"),
            Arguments.of("3600000", "1h"),
            Arguments.of("60000", "1m"),
            Arguments.of("30000", "30s"),
            Arguments.of("409802", "6m 49s"),
            Arguments.of("7200000", "2h"),
            Arguments.of("5620889", "1h 33m 40s")
        )
        @JvmStatic
        fun `should convert a valid string representation of milliseconds to hours, minutes and seconds timestamp in long form`() = listOf(
            Arguments.of("227000", "0h 3m 47s"),
            Arguments.of("3600000", "1h 0m 0s"),
            Arguments.of("60000", "0h 1m 0s"),
            Arguments.of("60001", "0h 1m 0s"),
            Arguments.of("7200000", "2h 0m 0s"),
            Arguments.of("5620889", "1h 33m 40s"),
            Arguments.of("444509802", "123h 28m 29s")
        )
    }

}