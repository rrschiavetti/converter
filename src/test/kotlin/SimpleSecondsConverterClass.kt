import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SimpleSecondsConverterClass {

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
    fun `should return singular when second representation is 1`() {
        sut.convert("1000") shouldBe "1 second"
    }

}