package com.lingo.jingziqi_mvp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lingo.jingziqi_mvp.model.Cell
import com.lingo.jingziqi_mvp.model.Player

class MainActivity : AppCompatActivity(), JingZiQiView {
    private val presenter: JingZiQiPresenter by lazy {
        JingZiQiPresenter(this)
    }

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
        presenter.reset()
    }

    fun putCell(view: View) {
        val tag: String = (view.tag ?: return) as String
        val points: List<Int> = tag.split("-").map { it.toInt() }
        presenter.putCell(points[0], points[1])
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

    override fun updateWinner(winner: Player?) {
        winnerText.text = winner?.toString() ?: ""
    }

    override fun updateCell(x: Int, y: Int, cell: Cell) {
        val tag = "$x-$y"
        val button: Button = cellGroup.findViewWithTag(tag)
        button.text = cell.toString()
    }
}