package com.example.tptdl.gamelogic.gameboard

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.example.tptdl.gamelogic.Score
import com.example.tptdl.gamelogic.tokens.Token
import com.example.tptdl.gamelogic.tokens.TokenRandomizer
import com.example.tptdl.gamelogic.tokens.Void

class User   : BaseObservable() {


}

class Cell(var value : Token){
    var obsValue = ObservableField<Token>()
    /*
    obsvalue = value

    @get:Bindable
    value : Token = ObservableField<Token>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstName)
        }

    value = ObservableField<Token>()
    */
    fun setCellValue(newValue : Token) {
        value = newValue
    }

    fun getCellValue() : Token {
        return this.value
    }

    fun generateRandomValue() {
        value = TokenRandomizer(5).randomToken()
    }

    fun isEmpty(): Boolean {
        return value.isEmpty()
    }

    fun emptyCell() {
        setCellValue(Void())
    }

    fun isEqual(cell : Cell) : Boolean {
        return value.isEqual(cell.getCellValue())
    }

    fun isExplosive(): Boolean {
        return value.isExplosive()
    }

    fun explode(cellCoords: Pair<Int, Int>, gameBoard: GameBoard) {
        if (!value.isExplosive())
            return
        value.explode(cellCoords, gameBoard)
    }

    fun pop(score: Score) {
        value.pop(score)
        this.emptyCell()
    }

    fun switchValues(cell: Cell) {
        val currentValue = value
        value = cell.getCellValue()
        cell.setCellValue(currentValue)
    }

    override fun toString(): String {
        return value.toString()
    }
}
