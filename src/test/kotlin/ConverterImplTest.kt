import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ConverterImplTest {

    private val sut = ConverterImpl()

    @Test
    fun `should convert a valid string representation of milliseconds to seconds `() {
        sut.convert("10000") shouldBe "10 seconds"
    }

    @Test
    fun `should return seconds ignoring leftover milliseconds`() {
        sut.convert("10001") shouldBe "10 seconds"
    }

    @Test
    fun `should throw InputNotValid when input is not a number`() {
        shouldThrow<ConverterImpl.InputIsNotAValidNumberException> { sut.convert("AAA") }
    }

    @Test
    fun `should return InputNotValid when input is less than zero`() {
        shouldThrow<ConverterImpl.InputIsNotAValidNumberException> {sut.convert("-1")}
    }

    @Test
    fun `should return InputNotValid when input is a decimal number`(){
        shouldThrow<ConverterImpl.InputIsNotAValidNumberException> { sut.convert("10.1") }
    }

    @Test
    fun `should return InputNotValid when input is greater than 9223372036854775807`() {
        shouldThrow<ConverterImpl.InputIsNotAValidNumberException> {sut.convert("9223372036854775808")}
    }

    @Test
    fun `should return singular when second representation is 1`() {
        sut.convert("1000") shouldBe "1 second"
    }

}