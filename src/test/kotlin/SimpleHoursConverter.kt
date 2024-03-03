import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class SimpleHoursConverter {

    @ParameterizedTest
    @MethodSource
    fun `should convert a valid string representation of milliseconds to hours, minutes and seconds timestamp`(input: String, expected: String) {
        val sut = ConverterImpl(enableHours = true)
        sut.convert(input) shouldBe expected

    }

    companion object {
        @JvmStatic
        fun `should convert a valid string representation of milliseconds to hours, minutes and seconds timestamp`() = listOf(
            Arguments.of("227000", "0 hours 3 minutes 47 seconds"),
            Arguments.of("3600000", "1 hour 0 minutes 0 seconds"),
            Arguments.of("60000", "0 hours 1 minute 0 seconds"),
            Arguments.of("60001", "0 hours 1 minute 0 seconds"),
            Arguments.of("7200000", "2 hours 0 minutes 0 seconds"),
            Arguments.of("5620889", "1 hour 33 minutes 40 seconds"),
            Arguments.of("444509802", "123 hours 28 minutes 29 seconds")

        )
    }
}