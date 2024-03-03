import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class SimpleSecondsConverterClass {

    private val sut = ConverterImpl()

    @ParameterizedTest
    @MethodSource
    fun `should convert a valid string representation of milliseconds to seconds`(input: String, expected: String) {
        sut.convert(input) shouldBe expected
    }

    @Test
    fun `should return seconds ignoring leftover milliseconds`() {
        sut.convert("10001") shouldBe "10 seconds"
    }

    @Test
    fun `should return singular when second representation is 1`() {
        sut.convert("1000") shouldBe "1 second"
    }

    companion object {
        @JvmStatic
        fun `should convert a valid string representation of milliseconds to seconds`() = listOf(
            Arguments.of("10000", "10 seconds"),
            Arguments.of("227000", "227 seconds"),
            Arguments.of("300000", "300 seconds"),
            Arguments.of("60000", "60 seconds"),
            Arguments.of("60001", "60 seconds"),
            Arguments.of("47000", "47 seconds")
        )
    }

}