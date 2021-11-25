package com.example.tptdl.gamelogic.gameboard

class Movement(private val cellToMove : Pair<Int, Int>, private val direction : String) {
    // checkIfMoveIsValid() should be called before this to check for out of bounds movements
    fun obtainCellToSwitch() : Pair<Int, Int> {
        val (x,y) = cellToMove
        val cellToSwitch : Pair<Int, Int>
        when {
            (direction == "Left") -> { cellToSwitch = Pair(x-1, y) }
            (direction == "Right") -> { cellToSwitch = Pair(x+1, y) }
            (direction == "Up") -> { cellToSwitch = Pair(x, y-1) }
            (direction == "Down") -> { cellToSwitch = Pair(x, y+1) }
            else -> throw Exception("Invalid direction")
        }
        return cellToSwitch
    }

    // Checks if a movement would end up in an out of bounds scenario and returns a boolean.
    fun checkIfOutOfBounds(height : Int, width : Int): Boolean {
        val (x, y) = cellToMove
        return !((x == 0 && (direction == "Left")) || (x == width-1 && (direction == "Right")) || (y == 0 && (direction == "Up")) || (y == height-1 && (direction == "Down"))) // checks for all out of bound possibilities
    }

    fun obtainCellCoords(): Pair<Int, Int> {
        return cellToMove
    }

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
}