package com.lingo.jingziqi_mvp

import com.lingo.jingziqi_mvp.model.Board

class JingZiQiPresenter(private val view: JingZiQiView) {

    private var model: Board = Board()

    fun putCell(x: Int, y: Int) {
        val success: Boolean = model.putCell(x, y)
        if (!success) {
            return
        }
        updateCells()
        if (model.isFinish()) {
            updateWinner()
        }
    }

    fun reset() {
        model = Board()
        updateCells()
        updateWinner()
    }

    private fun updateCells() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                view.updateCell(i, j, model.getCell(i, j))
            }
        }
    }

    private fun updateWinner() {
        view.updateWinner(model.winner)
    }
}