package com.example.fabianoluiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fabianoluiz.databinding.CarroItemBinding
import com.example.fabianoluiz.entity.Carro

class MainListAdapter(
    private val onEditClick: (Carro) -> Unit,
    private val onDeleteClick: (Carro) -> Unit
)
    :RecyclerView.Adapter<MainListAdapter.CarroViewHolder>(){

        private var carros: List<Carro> = emptyList()

    class CarroViewHolder(val binding: CarroItemBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CarroViewHolder {
        val binding = CarroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarroViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        val currentCarro = carros[position]
        holder.binding.textViewCarroMarca.text = currentCarro.marca
        holder.binding.textViewCarroModelo.text =
            currentCarro.modelo

        Glide
            .with(holder.itemView.context)
            .load(currentCarro.urlImage)
            .into(holder.binding.logoCarro)

        holder.binding.buttonEdit.setOnClickListener {
            onEditClick(currentCarro) }
        holder.binding.buttonDelete.setOnClickListener {
            onDeleteClick(currentCarro) }
    }
    override fun getItemCount() = carros.size
    fun setCarros(boardGames: List<Carro>) {
        this.carros = boardGames
        notifyDataSetChanged()
    }
}