package isdemidoff.adventofcode.year2016.day13.entity

data class Building(
    val puzzleInput: PuzzleInput,
) {
    fun isFreeSpace(position: Pair<Int, Int>) =
        (puzzleInput.designerFavNumber + position.let { (x, y) -> x * x + 3 * x + 2 * x * y + y + y * y }).countOneBits() % 2 == 0

    fun getAvailableNextPositions(position: Pair<Int, Int>, visited: Set<Pair<Int, Int>>) = listOf(
        position.first - 1 to position.second,
        position.first to position.second - 1,
        position.first + 1 to position.second,
        position.first to position.second + 1,
    )
        .filter { (x, y) -> x >= 0 && y >= 0 }
        .filterNot { visited.contains(it) }
        .filter { isFreeSpace(it) }

    fun findSolution(): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        val queue = ArrayDeque<Pair<Pair<Int, Int>, Int>>() // point to depth

        queue.addLast((1 to 1) to 0)

        while (queue.isNotEmpty()) {
            val (curPoint, depth) = queue.removeFirst()
            visited.add(curPoint)
            if (curPoint == puzzleInput.desiredCoordinates) return depth
            getAvailableNextPositions(curPoint, visited).forEach { queue.addLast(it to depth + 1) }
        }

        return -1
    }

    fun findLocations(maxDepth: Int): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        val queue = ArrayDeque<Pair<Pair<Int, Int>, Int>>() // point to depth

        queue.addLast((1 to 1) to 0)

        while (queue.isNotEmpty()) {
            val (curPoint, depth) = queue.removeFirst()
            if (depth > maxDepth) continue
            visited.add(curPoint)
            getAvailableNextPositions(curPoint, visited).forEach { queue.addLast(it to depth + 1) }
        }

        return visited.size
    }
}