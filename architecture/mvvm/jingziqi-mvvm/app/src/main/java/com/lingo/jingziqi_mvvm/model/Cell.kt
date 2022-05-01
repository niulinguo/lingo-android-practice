package com.lingo.jingziqi_mvvm.model

class Cell(var player: Player? = null) {
    fun isEmpty() = player == null

    fun put(player: Player) {
        this.player = player
    }

    override fun hashCode(): Int {
        return player?.hashCode() ?: 0
    }

    override fun equals(other: Any?): Boolean {
        return other is Cell && player == other.player
    }

    override fun toString(): String {
        return player?.toString() ?: ""
    }
}