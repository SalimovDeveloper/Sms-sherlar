package uz.programmer710.smssherlar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.programmer710.smssherlar.databinding.ItemSherlarBinding
import uz.programmer710.smssherlar.models.Info

class RvAdapter(val list: List<Info>, val rvClick: RvClick) : RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(val itemSherlar: ItemSherlarBinding): RecyclerView.ViewHolder(itemSherlar.root){
        fun onBind(info: Info){
//            val animation = AnimationUtils.loadAnimation(context, R.anim.my_anim)
//            itemSherlar.root.startAnimation(animation)
            itemSherlar.itemName.text = info.name
            itemSherlar.itemSher.text = info.sher
            itemSherlar.cardItemView.setOnClickListener {
                rvClick.itemClick(info)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemSherlarBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface RvClick{
        fun itemClick(info: Info)
    }

}