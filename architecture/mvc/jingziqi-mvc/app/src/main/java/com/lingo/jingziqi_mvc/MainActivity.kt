package com.lingo.jingziqi_mvc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lingo.jingziqi_mvc.model.Board

class MainActivity : AppCompatActivity() {
    private var model: Board = Board()

    private val cellGroup: ViewGroup by lazy {
        findViewById(R.id.gl_cell_container)
    }

    private val winnerText: TextView by lazy {
        findViewById(R.id.tv_winner)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun reset() {
        model = Board()
        updateCells()
        updateWinner()
    }

    fun putCell(view: View) {
        val tag: String = (view.tag ?: return) as String
        val points: List<Int> = tag.split("-").map { it.toInt() }
        val success: Boolean = model.putCell(points[0], points[1])
        if (!success) {
            return
        }
        updateCells()
        if (model.isFinish()) {
            updateWinner()
        }
    }

    private fun updateCells() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val tag = "$i-$j"
                val button: Button = cellGroup.findViewWithTag(tag)
                button.text = model.getCell(i, j).toString()
            }
        }
    }

    private fun updateWinner() {
        winnerText.text = model.winner?.toString() ?: ""
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_jingziqi, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_reset) {
            reset()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}