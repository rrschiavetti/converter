import io.kotest.matchers.shouldBe
import kotlin.test.Test

class SimpleMinutesConverterTest {

    @Test
    fun `should convert a valid string representation of milliseconds to minutes and secodns`() {
        val sut = ConverterImpl(enableMinutes = true)
        sut.convert("227000") shouldBe "3 minutes and 47 seconds"
    }

}