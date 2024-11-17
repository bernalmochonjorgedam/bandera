package com.utad.banderas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utad.banderas.R
import com.utad.banderas.databinding.AutonomiaCardviewBinding
import com.utad.banderas.model.Autonomia
import com.utad.banderas.util.BanderasProvider

class AutonomiaRecyclerAdapter(
    private val autonomiaList: MutableList<Autonomia>,
    private val onEditAutonomia: (Autonomia) -> Unit,
    private val onDeleteAutonomia: (Autonomia) -> Unit
) : RecyclerView.Adapter<AutonomiaRecyclerAdapter.AutonomiaViewHolder>() {
    private val banderaMap = BanderasProvider.getBanderas()

    inner class AutonomiaViewHolder(private val binding: AutonomiaCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(autonomia: Autonomia) {
            binding.nameAutonomia.text = autonomia.nombre

            val imageResId = banderaMap[autonomia.bandera]
            if (imageResId != null) {
                binding.imageAutonomia.setImageResource(imageResId)
            } else {
                Picasso.get()
                    .load("file:///android_res/drawable/spain.png")
                    .into(binding.imageAutonomia)
            }

            setupListeners(autonomia)
        }

        private fun setupListeners(autonomia: Autonomia) {
            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "Yo soy de ${autonomia.nombre}", Toast.LENGTH_SHORT).show()
            }
            binding.root.setOnLongClickListener {
                showContextMenu(it, autonomia)
                true
            }
        }

        private fun showContextMenu(view: View, autonomia: Autonomia) {
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.menuInflater.inflate(R.menu.delete_edit, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.eliminar -> {
                        onDeleteAutonomia(autonomia)
                        true
                    }

                    R.id.editar -> {
                        onEditAutonomia(autonomia)
                        true
                    }

                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutonomiaViewHolder {
        val binding = AutonomiaCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AutonomiaViewHolder(binding)
    }

    override fun getItemCount(): Int = autonomiaList.size

    override fun onBindViewHolder(holder: AutonomiaViewHolder, position: Int) {
        holder.bind(autonomiaList[position])
    }
}
