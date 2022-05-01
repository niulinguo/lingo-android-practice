package com.lingo.jingziqi_mvp

import com.lingo.jingziqi_mvp.model.Cell
import com.lingo.jingziqi_mvp.model.Player

interface JingZiQiView {

    fun updateWinner(winner: Player?)

    fun updateCell(x: Int, y: Int, cell: Cell)
}