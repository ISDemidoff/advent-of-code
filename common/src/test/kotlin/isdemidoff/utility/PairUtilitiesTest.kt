package isdemidoff.utility

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PairUtilitiesTest : FreeSpec({
    "Check CharSequence#keyValue(String)" - {
        data class KeyValueTestData(
            val inputString: String,
            val delimiter: String,
            val result: Pair<String, String>?,
        ) {
            constructor(inputString: String, delimiter: String) : this(inputString, delimiter, null)
        }

        withData(
            nameFn = { (inputString, delimiter, result) ->
                "'$inputString' delimited by '$delimiter' should result into $result"
            },
            KeyValueTestData("1: 2", ": ", "1" to "2"),
            KeyValueTestData("1 => 2", " => ", "1" to "2"),
            KeyValueTestData("1,2", ",", "1" to "2"),
            KeyValueTestData(",1", ",", "" to "1"),
            KeyValueTestData("1,", ",", "1" to ""),
            KeyValueTestData("1 2", " ", "1" to "2"),
        ) { (inputString, delimiter, result) ->
            inputString.keyValue(delimiter) shouldBe result
        }

        withData(
            nameFn = { (inputString, delimiter) ->
                "'$inputString' delimited by '$delimiter' should throw an exception"
            },
            KeyValueTestData("1: 2: 3", ": "),
            KeyValueTestData("1,2,3", ","),
            KeyValueTestData("13", ","),
            KeyValueTestData("", ","),
            KeyValueTestData(" 1 2", " "),
            KeyValueTestData("1 2 ", " "),
            KeyValueTestData("12", ""),
        ) { (inputString, delimiter) ->
            shouldThrow<IllegalArgumentException> {
                inputString.keyValue(delimiter)
            } shouldHaveMessage "Invalid input: $inputString"
        }
    }

    "Check CharSequence#keyValueBy(String, (String) -> K, (String) -> V)" - {
        data class KeyValueByTestData<K, V>(
            val inputString: String,
            val delimiter: String,
            val keyTransformer: (String) -> K,
            val valueTransformer: (String) -> V,
            val result: Pair<K, V>,
        )

        withData(
            nameFn = { (inputString, delimiter, _, _, result) ->
                "'$inputString' delimited by '$delimiter' should result into $result"
            },
            KeyValueByTestData("1: 2", ": ", { it.toInt() }, { it.toInt() }, 1 to 2),
            KeyValueByTestData("str: 2", ": ", { it }, { it.toInt() }, "str" to 2),
            KeyValueByTestData("2 : str", " : ", { it.toInt() }, { it }, 2 to "str"),
        ) { (inputString, delimiter, keyTr, valTr, result) ->
            inputString.keyValueBy(delimiter, keyTr, valTr) shouldBe result
        }
    }
})