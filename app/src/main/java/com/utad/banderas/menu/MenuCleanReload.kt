package com.utad.banderas.menu

import android.app.AlertDialog
import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.utad.banderas.R
import com.utad.banderas.adapter.AutonomiaRecyclerAdapter
import com.utad.banderas.data.AutonomiaData
import com.utad.banderas.model.Autonomia

class MenuCleanReload(
    private val context: Context,
    private val autonomiaList: MutableList<Autonomia>,
    private val adapter: AutonomiaRecyclerAdapter
) {
    fun handleOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.recargar -> {
                reloadList()
                true
            }

            R.id.limpiar -> {
                clearList()
                true
            }

            else -> false
        }
    }

    private fun reloadList() {
        autonomiaList.clear()
        autonomiaList.addAll(AutonomiaData.obtenerAutonomias())
        with(adapter) {
            notifyDataSetChanged()
        }
        Toast.makeText(context, "Lista de autonomías recargada", Toast.LENGTH_SHORT).show()
    }

    private fun clearList() {
        autonomiaList.clear()
        adapter.notifyDataSetChanged()
        Toast.makeText(context, "Lista de autonomías limpiada", Toast.LENGTH_SHORT).show()
    }

    fun isListEmpty(): Boolean {
        return autonomiaList.isEmpty()
    }
}
