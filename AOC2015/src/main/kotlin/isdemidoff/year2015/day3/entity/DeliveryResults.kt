package isdemidoff.year2015.day3.entity

class DeliveryResults {
    private val visitedPoints = mutableSetOf(Point(0, 0))
    private var currentPoint: Point = Point(0, 0)

    fun traversePath(input: String) {
        input.forEach {
            currentPoint = when (it) {
                '^' -> currentPoint.copy(y = currentPoint.y + 1)
                'v' -> currentPoint.copy(y = currentPoint.y - 1)
                '>' -> currentPoint.copy(x = currentPoint.x + 1)
                '<' -> currentPoint.copy(x = currentPoint.x - 1)
                else -> error("Unexpected character: $it")
            }.also { visitedPoints.add(it) }
        }
    }

    fun getNumberOfVisitedPoints() = visitedPoints.size
}