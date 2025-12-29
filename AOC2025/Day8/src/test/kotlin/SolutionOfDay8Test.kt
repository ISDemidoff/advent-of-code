import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import isdemidoff.utility.test.TestConstants.INPUT_FILE_NAME
import isdemidoff.utility.test.TestConstants.SAMPLE_FILE_NAME

class SolutionOfDay8Test : StringSpec({
    "Sample check" {
        solveForFileName(SAMPLE_FILE_NAME, 10) shouldBe 40
    }

    "My result" {
        println(solveForFileName(INPUT_FILE_NAME, 1000))
    }
})
