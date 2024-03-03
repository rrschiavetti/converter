import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class SimpleMinutesConverterTest {

    @ParameterizedTest
    @MethodSource
    fun `should convert a valid string representation of milliseconds to minutes and seconds`(input: String, expected: String) {
        val sut = ConverterImpl(enableMinutes = true)
        sut.convert(input) shouldBe expected
    }

    companion object {
        @JvmStatic
        fun `should convert a valid string representation of milliseconds to minutes and seconds`() = listOf(
            Arguments.of("227000", "3 minutes 47 seconds"),
            Arguments.of("300000", "5 minutes 0 seconds"),
            Arguments.of("10000", "0 minutes 10 seconds"),
            Arguments.of("60000", "1 minute 0 seconds"),
            Arguments.of("60001", "1 minute 0 seconds"),
            Arguments.of("47000", "0 minutes 47 seconds"),
            Arguments.of("7200000", "120 minutes 0 seconds"),
            Arguments.of("5620889", "93 minutes 40 seconds")
        )
    }

}