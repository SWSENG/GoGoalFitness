package com.example.gogoalfitness.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gogoalfitness.R
import com.example.gogoalfitness.list.RecipeInfo

class RecipeAdapter(private val recipeArrayList:ArrayList<RecipeInfo>, private val listener: OnItemClickListener)
    : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{

        var image: ImageView = view.findViewById(R.id.recipeThumbnail)
        var title: TextView = view.findViewById(R.id.recipeTitle)
        var calories: TextView = view.findViewById(R.id.recipeCalories)

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.itemClick(position)
            }
        }

    }

    interface OnItemClickListener{
        fun itemClick(position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeAdapter.RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe,parent,false)

        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        val currentRec = recipeArrayList[position]

        holder.title.text = currentRec.name
        holder.image.setImageURI(Uri.parse(currentRec.image))
        holder.calories.text = currentRec.calories

    }

    override fun getItemCount(): Int {
        return recipeArrayList.size
    }

}