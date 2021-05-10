package com.example.exampleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exampleapp.databinding.ItemPokeBinding
import com.example.exampleapp.databinding.ItemTitleBinding
import com.example.exampleapp.model.Pokemon

class PokeTitleAdapter(private val mTitle: String, private val mPokemonList: ArrayList<Pokemon>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  companion object {
    private const val TYPE_TITLE = 0
    private const val TYPE_ITEM = 1
  }

  override fun getItemViewType(position: Int): Int {
    return if (position == 0) return TYPE_TITLE else TYPE_ITEM
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return if (viewType == TYPE_TITLE) {
      TitleViewHolder(ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    } else {
      PokeViewHolder(ItemPokeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
  }

  override fun getItemCount(): Int = mPokemonList.size.plus(1)

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (getItemViewType(position) == TYPE_TITLE) {
      (holder as TitleViewHolder).bind(mTitle)
    } else {
      (holder as PokeViewHolder).bind(mPokemonList[position.minus(1)])
    }
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

  inner class TitleViewHolder(private val mBinding: ItemTitleBinding) :
      RecyclerView.ViewHolder(mBinding.root) {
    fun bind(title: String) {
      with(mBinding) {
        tvTitle.text = title
      }
    }
  }
}