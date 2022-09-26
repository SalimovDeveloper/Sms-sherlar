package uz.programmer710.smssherlar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.programmer710.smssherlar.R
import uz.programmer710.smssherlar.databinding.ItemSaqlanganlarBinding
import uz.programmer710.smssherlar.models.Info

class MvAdapter (val list: List<Info>, val mvClick: MvClick) : RecyclerView.Adapter<MvAdapter.Vh>() {
    inner class Vh(val itemSaqlanganlar: ItemSaqlanganlarBinding): RecyclerView.ViewHolder(itemSaqlanganlar.root){
        fun onBind(info: Info) {
//            val animation = AnimationUtils.loadAnimation(context, R.anim.my_anim)
//            itemSherlar.root.startAnimation(animation)
            itemSaqlanganlar.itemSaqlanganNomi.text = info.name
            itemSaqlanganlar.itemSaqlanganMatni.text = info.sher
            itemSaqlanganlar.itemSaqlanganYurak.setImageResource(R.drawable.qizil_yurak)
            itemSaqlanganlar.cardSaqlangan.setOnClickListener {
                mvClick.itemClick(info)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemSaqlanganlarBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface MvClick{
        fun itemClick(info: Info)
    }
}