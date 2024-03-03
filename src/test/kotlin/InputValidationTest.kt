import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class InputValidationTest {

    private val sut = ConverterImpl()

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
}