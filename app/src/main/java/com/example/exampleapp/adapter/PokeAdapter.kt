package com.example.exampleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exampleapp.databinding.ItemPokeBinding
import com.example.exampleapp.model.Pokemon

class PokeAdapter(private val mPokemonList: ArrayList<Pokemon>) :
    RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        return PokeViewHolder(
            ItemPokeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = mPokemonList.size

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        holder.bind(mPokemonList[position])
    }

    inner class PokeViewHolder(private val mBinding: ItemPokeBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(pokemon: Pokemon) {
            with(mBinding) {
                Glide.with(root.context).load(
                    "https://www.w3schools.com/w3css/img_lights.jpg").into(ivProductImg)
                tvName.text = pokemon.name
                tvId.text = pokemon.id.toString()
                tvUrl.text = pokemon.url
            }
        }
    }
}