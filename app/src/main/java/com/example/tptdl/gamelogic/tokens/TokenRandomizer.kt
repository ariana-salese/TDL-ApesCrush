package com.example.tptdl.gamelogic.tokens

class TokenRandomizer {
    val allTokens : MutableList<Token> = mutableListOf()
    val allFruits : MutableList<Fruit> = mutableListOf()
    val numberOfTokens : Int = 5
    val numberOfFruits : Int = 4

    init {
        allTokens.add(Apple())
        allTokens.add(Banana())
        allTokens.add(Bomb())
        allTokens.add(Grapes())
        allTokens.add(Peach())
        allTokens.add(Strawberry())
        allFruits.add(Apple())
        allFruits.add(Banana())
        allFruits.add(Grapes())
        allFruits.add(Peach())
        allFruits.add(Strawberry())
    }

    fun randomToken() : Token {
        return allTokens.elementAt((0..numberOfTokens).random())
    }

    fun randomFruit() : Fruit {
        return allFruits.elementAt((0..numberOfFruits).random())
    }
}