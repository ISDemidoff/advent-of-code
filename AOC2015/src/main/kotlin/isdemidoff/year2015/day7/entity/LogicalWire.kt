package isdemidoff.year2015.day7.entity

sealed class LogicalWire(
    val identifier: String
) {
    abstract val signalValue: UShort
    abstract fun assignInputs(allWires: Map<String, LogicalWire>)
}

class ValueLogicalWire(value: String) : LogicalWire(value) {
    init {
        check(value.matches("""[0-9]+""".toRegex())) { "Assumed $value is a numeric value" }
    }

    override val signalValue: UShort = value.toUShort()

    override fun assignInputs(allWires: Map<String, LogicalWire>) {
        // No operation
    }
}

abstract class SingleInputLogicalWire(
    private val inputIdentifier: String,
    selfIdentifier: String,
    private val operation: (UShort) -> UShort
) : LogicalWire(selfIdentifier) {
    private var inputWire: LogicalWire? = null

    override fun assignInputs(allWires: Map<String, LogicalWire>) {
        inputWire = allWires[inputIdentifier] ?: ValueLogicalWire(inputIdentifier)
    }

    override val signalValue: UShort by lazy {
        check(inputWire != null) { "Input wire labeled \"$inputIdentifier\" is null" }
        return@lazy operation(inputWire!!.signalValue)
    }
}

class CopyLogicalWire(
    inputIdentifier: String,
    selfIdentifier: String,
) : SingleInputLogicalWire(
    inputIdentifier = inputIdentifier,
    selfIdentifier = selfIdentifier,
    operation = { it }
)

class NotLogicalWire(
    inputIdentifier: String,
    selfIdentifier: String,
) : SingleInputLogicalWire(
    inputIdentifier = inputIdentifier,
    selfIdentifier = selfIdentifier,
    operation = { it.inv() }
)

class LeftShiftLogicalWire(
    inputIdentifier: String,
    shift: Int,
    selfIdentifier: String,
) : SingleInputLogicalWire(
    inputIdentifier = inputIdentifier,
    selfIdentifier = selfIdentifier,
    operation = { (it.toInt() shl shift).toUShort() }
)

class RightShiftLogicalWire(
    inputIdentifier: String,
    shift: Int,
    selfIdentifier: String,
) : SingleInputLogicalWire(
    inputIdentifier = inputIdentifier,
    selfIdentifier = selfIdentifier,
    operation = { (it.toInt() shr shift).toUShort() }
)

abstract class TwoInputsLogicalWire(
    private val leftIdentifier: String,
    private val rightIdentifier: String,
    selfIdentifier: String,
    private val operation: (UShort, UShort) -> UShort,
) : LogicalWire(selfIdentifier) {
    private var leftWire: LogicalWire? = null
    private var rightWire: LogicalWire? = null

    override fun assignInputs(allWires: Map<String, LogicalWire>) {
        leftWire = allWires[leftIdentifier] ?: ValueLogicalWire(leftIdentifier)
        rightWire = allWires[rightIdentifier] ?: ValueLogicalWire(rightIdentifier)
    }

    override val signalValue: UShort by lazy {
        check(leftWire != null) { "Input wire labeled \"$leftIdentifier\" is null" }
        check(rightWire != null) { "Input wire labeled \"$rightIdentifier\" is null" }
        return@lazy operation(leftWire!!.signalValue, rightWire!!.signalValue)
    }
}

class AndLogicalWire(
    leftIdentifier: String,
    rightIdentifier: String,
    selfIdentifier: String,
) : TwoInputsLogicalWire(
    leftIdentifier = leftIdentifier,
    rightIdentifier = rightIdentifier,
    selfIdentifier = selfIdentifier,
    operation = { a, b -> a and b },
)

class OrLogicalWire(
    leftIdentifier: String,
    rightIdentifier: String,
    selfIdentifier: String,
) : TwoInputsLogicalWire(
    leftIdentifier = leftIdentifier,
    rightIdentifier = rightIdentifier,
    selfIdentifier = selfIdentifier,
    operation = { a, b -> a or b },
)

fun String.createLogicalWire(): LogicalWire {
    val (input, output) = this.split(" -> ")
        .also { check(it.size == 2) { "Invalid logical wire format: $this" } }
        .let { it[0] to it[1] }

    return when {
        input.startsWith("NOT") -> NotLogicalWire(input.substring(4), output)

        input.contains(" AND ") -> input.split(" AND ")
            .also { check(it.size == 2) { "Requires two inputs for AND operator: $this" } }
            .let { AndLogicalWire(it[0], it[1], output) }

        input.contains(" OR ") -> input.split(" OR ")
            .also { check(it.size == 2) { "Requires two inputs for OR operator: $this" } }
            .let { OrLogicalWire(it[0], it[1], output) }

        input.contains(" LSHIFT ") -> input.split(" LSHIFT ")
            .also { check(it.size == 2) { "Requires two inputs for LSHIFT operator: $this" } }
            .let { LeftShiftLogicalWire(it[0], it[1].toInt(), output) }

        input.contains(" RSHIFT ") -> input.split(" RSHIFT ")
            .also { check(it.size == 2) { "Requires two inputs for RSHIFT operator: $this" } }
            .let { RightShiftLogicalWire(it[0], it[1].toInt(), output) }

        else -> CopyLogicalWire(input, output)
    }
}