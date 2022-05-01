package com.lingo.jingziqi_mvvm

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lingo.jingziqi_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: JingZiQiViewModel by lazy {
        defaultViewModelProviderFactory.create(JingZiQiViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = this.viewModel
    }

    private fun reset() {
        viewModel.reset()
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