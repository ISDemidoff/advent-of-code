import TestConstants.INPUT_FILE_NAME
import TestConstants.SAMPLE_FILE_NAME
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SolutionOfDay1Test : StringSpec({
    "Sample check" {
        solveForFileName(SAMPLE_FILE_NAME) shouldBe 3
    }

    "My result" {
        println(solveForFileName(INPUT_FILE_NAME))
    }
})
