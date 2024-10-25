package com.example.appmarte_039.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmarte_039.Model.Remote.MarsRealState
import com.example.appmarte_039.databinding.MarsItemBinding

class AdapterMars : RecyclerView.Adapter<AdapterMars.MarsVH>() {

 // listado de elementos

    private var listaMarsItem = listOf<MarsRealState>()

    // Para seleccinar tenemos una variables que tiene un listado de nuestra clase

val selectedTerrain = MutableLiveData<MarsRealState>()

    fun update(list: List<MarsRealState>){
        listaMarsItem = list
        notifyDataSetChanged()
    }





    inner class MarsVH(private val binding :MarsItemBinding):
            RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{

         fun onClick(mars:MarsRealState) {

            Glide.with(binding.imageView)
                .load(mars.img_src)
                .centerCrop()
                .into(binding.imageView)
            // activar el click en la vista
            itemView.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
        // para seleccionar
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsVH {

        return  MarsVH(MarsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }



    override fun onBindViewHolder(holder: MarsVH, position: Int) {
        val terrain = listaMarsItem[position]
        holder.onClick(terrain)
    }


    override fun getItemCount(): Int =
    listaMarsItem.size
}

