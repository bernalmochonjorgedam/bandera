package com.utad.banderas

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utad.banderas.activity.EditAutonomiaActivity
import com.utad.banderas.adapter.AutonomiaRecyclerAdapter
import com.utad.banderas.data.AutonomiaData
import com.utad.banderas.menu.MenuCleanReload
import com.utad.banderas.model.Autonomia

class MainActivity : AppCompatActivity() {
    private lateinit var autonomiaList: MutableList<Autonomia>
    private lateinit var adapter: AutonomiaRecyclerAdapter
    private lateinit var menuCleanReload: MenuCleanReload

    private val editAutonomiaLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val editedAutonomia: Autonomia? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra("autonomia", Autonomia::class.java)
            } else {
                @Suppress("DEPRECATION")
                result.data?.getParcelableExtra("autonomia")
            }

            editedAutonomia?.let {
                val index = autonomiaList.indexOfFirst { it.id == editedAutonomia.id }
                if (index != -1) {
                    autonomiaList[index] = editedAutonomia
                    adapter.notifyItemChanged(index)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Banderas"
        setSupportActionBar(toolbar)

        autonomiaList = AutonomiaData.obtenerAutonomias().toMutableList()

        adapter = AutonomiaRecyclerAdapter(
            autonomiaList,
            onEditAutonomia = { autonomia ->
                val intent = Intent(this, EditAutonomiaActivity::class.java)
                intent.putExtra("autonomia", autonomia)
                editAutonomiaLauncher.launch(intent)
            },
            onDeleteAutonomia = { autonomia ->
                val position = autonomiaList.indexOf(autonomia)
                if (position != -1) {
                    autonomiaList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }
                Toast.makeText(this, "${autonomia.nombre} eliminada", Toast.LENGTH_SHORT).show()
                invalidateOptionsMenu()
            }
        )

        menuCleanReload = MenuCleanReload(this, autonomiaList, adapter)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.reload_clean, menu)
        menu?.findItem(R.id.eliminar)?.isEnabled = autonomiaList.isNotEmpty()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val handled = menuCleanReload.handleOptionsItemSelected(item)
        if (handled) {
            invalidateOptionsMenu()
        }
        return handled || super.onOptionsItemSelected(item)
    }
}
