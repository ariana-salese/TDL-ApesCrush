package com.example.tptdl.gamelogic.gameboard

class Movement(private val cellCoordsToMove : Pair<Int, Int>, private val direction : Direction) {

    // checkIfMoveIsValid() should be called before this to check for out of bounds movements
    fun obtainCellCoordsToSwitch() : Pair<Int, Int> {
        return direction.obtainCellCoordsOf(cellCoordsToMove)
    }

    // Checks if a movement would end up in an out of bounds scenario and returns a boolean.
    fun checkIfOutOfBounds(height : Int, width : Int): Boolean {
        return direction.isMovementOutOfBounds(height, width, cellCoordsToMove)
    }

    fun obtainCellCoords(): Pair<Int, Int> { return cellCoordsToMove }

    /*
    // Function returns the opposite of a certain movement, currently doesn't check for out of bounds moves.
    fun oppositeMovement(): Movement {
        val oppositeDirection : String
        val oppositeCoords : Pair<Int, Int>
        val (x, y) = cellToMove
        when {
            (direction == "Left") -> {
                oppositeDirection = "Right"
                oppositeCoords = Pair(x-1, y)
            }
            (direction == "Right") -> {
                oppositeDirection = "Left"
                oppositeCoords = Pair(x+1, y)
            }
            (direction == "Up") -> {
                oppositeDirection = "Down"
                oppositeCoords = Pair(x, y-1)
            }
            (direction == "Down") -> {
                oppositeDirection = "Down"
                oppositeCoords = Pair(x, y+1)
            }
            else -> throw Exception("Invalid direction")
        }
        return Movement(oppositeCoords, oppositeDirection)
    }//TODO Repensar utilidad de este metodo @Alejo

     */
}