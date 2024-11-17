package com.utad.banderas.activity


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.utad.banderas.R
import com.utad.banderas.model.Autonomia
import com.utad.banderas.util.BanderasProvider

class EditAutonomiaActivity : AppCompatActivity() {
    private lateinit var autonomia: Autonomia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_autonomia)

        autonomia = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("autonomia", Autonomia::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("autonomia")
        } ?: Autonomia(0, "", "spain")

        val banderaMap = BanderasProvider.getBanderas()
        val imageResId = banderaMap[autonomia.bandera] ?: R.drawable.spain

        val imageView = findViewById<ImageView>(R.id.imageAutonomia)
        imageView.setImageResource(imageResId)

        val editText = findViewById<EditText>(R.id.editTextAutonomiaName)
        editText.setText(autonomia.nombre)

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            autonomia.nombre = editText.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("autonomia", autonomia)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
