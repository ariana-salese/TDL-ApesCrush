package com.example.tptdl.gamelogic.gameboard

class Direction(private val xVariation: Int, private val yVariation: Int) {

    fun isMovementOutOfBounds(height: Int, width: Int, cellCords: Pair<Int, Int>): Boolean {
        val (x, y) = cellCords

        val xFinal = x + xVariation
        val yFinal = y + yVariation

        return xFinal > width || yFinal > height || xFinal < 0 || yFinal < 0
    }

    fun obtainCellCoordsOf(cellCords: Pair<Int, Int>): Pair<Int, Int> {
        val (x, y) = cellCords
        return Pair(x + xVariation, y + yVariation)
    }

}