package com.example.fabianoluiz

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.fabianoluiz.databinding.ActivityMainBinding
import com.example.fabianoluiz.databinding.DialogEditCarroBinding
import com.example.fabianoluiz.entity.Carro
import com.example.fabianoluiz.factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            (application as
                    CarroApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpLogo()
        setUpListeners()
        setUpRecyclerView()
    }

    private fun setUpListeners() {
        binding.buttonAddCarro.setOnClickListener {
            val marcaName = binding.editTextCarroMarca.text.toString()
            val urlImagem = binding.editLogo.text.toString()
            val modeloName =
                binding.editTextCarroModelo.text.toString()
            if (marcaName.isNotBlank() && modeloName.isNotBlank()) {
                mainViewModel.insert(
                    Carro(
                        marca = marcaName,
                        modelo = modeloName,
                        urlImage = urlImagem,
                    )
                )
                binding.editTextCarroMarca.text.clear()
                binding.editLogo.text.clear()
                binding.editTextCarroModelo.text.clear()
                binding.editTextCarroMarca.requestFocus()
            }
        }
    }

    private fun showEditDialog(carro: Carro) {
        val dialogBinding = DialogEditCarroBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogBinding.root)
        // Preenche os campos de texto com os dados do jogo atual
        dialogBinding.editTextCarroMarca.setText(carro.marca)
        dialogBinding.editTextCarroModelo.setText(carro.modelo)
        Glide.with(this)
            .load(carro.urlImage)
            .into(dialogBinding.logoCarro);

        builder.setTitle("Editar Carro")
        builder.setPositiveButton("Salvar") { _, _ ->
            val updatedCarro = carro.copy(
                marca = dialogBinding.editTextCarroMarca.text.toString(),
                modelo = dialogBinding.editTextCarroModelo.text.toString()
            )
            mainViewModel.update(updatedCarro)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun setUpRecyclerView () {
        val adapter = MainListAdapter(
            onEditClick = { carro -> showEditDialog(carro) },
            onDeleteClick = { carro -> mainViewModel .delete(carro) }
        )
        binding.recyclerViewCarros .adapter = adapter
        binding.recyclerViewCarros .layoutManager = LinearLayoutManager( this)
        // Adicionar Divider
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerViewCarros  .context,
            (binding.recyclerViewCarros  .layoutManager as
                    LinearLayoutManager ).orientation
        )
        binding.recyclerViewCarros  .addItemDecoration( dividerItemDecoration )
        mainViewModel .allCarros .observe(this) { games ->
            games?.let { adapter.setCarros( it) }
        }
    }

    private fun setUpLogo() {
        Glide
            .with(this).load("https://static.vecteezy.com/system/resources/previews/006/404/900/original/board-game-logo-free-vector.jpg")
            .into(binding.imageLogo)
    }
}