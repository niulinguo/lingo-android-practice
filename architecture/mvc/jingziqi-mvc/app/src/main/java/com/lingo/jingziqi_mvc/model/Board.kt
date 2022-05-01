package com.lingo.jingziqi_mvc.model

class Board {
    private val cells: Array<Array<Cell>> = arrayOf(
        arrayOf(Cell(), Cell(), Cell()),
        arrayOf(Cell(), Cell(), Cell()),
        arrayOf(Cell(), Cell(), Cell()),
    )
    private var currentPlayer: Player = Player.O
    private var state: GameState = GameState.RUNNING
    var winner: Player? = null

    fun getCell(x: Int, y: Int): Cell {
        return cells[x][y]
    }

    fun putCell(x: Int, y: Int): Boolean {
        if (state == GameState.FINISH) {
            return false;
        }
        if (cells[x][y].isEmpty()) {
            cells[x][y].put(currentPlayer)

            checkAndUpdateState()
            if (state == GameState.RUNNING) {
                switchPlayer()
            }

            return true
        }
        return false
    }

    private fun checkAndUpdateState() {
        val winner = checkWinner()
        if (winner != null) {
            state = GameState.FINISH
            this.winner = winner
        }
    }

    private fun checkWinner(): Player? {
        val array = arrayOf(
            allSamePlayer(cells[0][0], cells[0][1], cells[0][2]),
            allSamePlayer(cells[1][0], cells[1][1], cells[1][2]),
            allSamePlayer(cells[2][0], cells[2][1], cells[2][2]),
            allSamePlayer(cells[0][0], cells[1][1], cells[2][2]),
            allSamePlayer(cells[0][2], cells[1][1], cells[2][0]),
        ).filterNotNull()
        return if (array.isEmpty()) null else array[0]
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == Player.O) Player.X else Player.O
    }

    private fun allSamePlayer(cell1: Cell, cell2: Cell, cell3: Cell): Player? {
        if (!cell1.isEmpty() && cell1 == cell2 && cell1 == cell3) {
            return cell1.player
        }
        return null
    }

    fun isFinish() = state == GameState.FINISH
}