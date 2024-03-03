import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ConverterTest {

    private val sut = Converter()

    @Test
    fun `should parse a valid string representation of milliseconds to seconds `() {
        sut.convert("10000") shouldBe "10 seconds"
    }

}