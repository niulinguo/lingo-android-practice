package com.lingo.jingziqi_mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lingo.jingziqi_mvvm.model.Board

class JingZiQiViewModel : ViewModel() {
    private val _winnerText: MutableLiveData<String> = MutableLiveData("")
    val winnerText: LiveData<String> = _winnerText

    private var model: Board = Board()

    private val _cellTexts: Array<Array<MutableLiveData<String>>> = arrayOf(
        arrayOf(MutableLiveData(""), MutableLiveData(""), MutableLiveData("")),
        arrayOf(MutableLiveData(""), MutableLiveData(""), MutableLiveData("")),
        arrayOf(MutableLiveData(""), MutableLiveData(""), MutableLiveData("")),
    )

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

    fun cellText(x: Int, y: Int): LiveData<String> {
        return _cellTexts[x][y]
    }

    private fun updateCells() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                _cellTexts[i][j].value = model.getCell(i, j).toString()
            }
        }
    }

    private fun updateWinner() {
        _winnerText.value = model.winner?.toString() ?: ""
    }
}