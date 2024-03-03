import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HideZeroUnitValuesTest {


    @ParameterizedTest
    @MethodSource
    fun `should convert a valid string representation of milliseconds to hours, minutes and seconds timestamp ignoring zero values`(input: String, expected: String) {
        val sut = ConverterImpl(enableHours = true, ignoreZero = true)
        sut.convert(input) shouldBe expected
    }

    companion object {
        @JvmStatic
        fun `should convert a valid string representation of milliseconds to hours, minutes and seconds timestamp ignoring zero values`() = listOf(
            Arguments.of("227000", "3 minutes 47 seconds"),
            Arguments.of("3600000", "1 hour"),
            Arguments.of("60000", "1 minute"),
            Arguments.of("30000", "30 seconds"),
            Arguments.of("409802", "6 minutes 49 seconds"),
            Arguments.of("7200000", "2 hours"),
            Arguments.of("5620889", "1 hour 33 minutes 40 seconds")
        )
    }
}
