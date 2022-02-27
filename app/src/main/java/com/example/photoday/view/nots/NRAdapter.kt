package com.example.photoday.view.nots

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.photoday.R
import com.example.photoday.databinding.NoteRecyclerItemBinding

@SuppressLint("NotifyDataSetChanged")
class NRAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<Pair<Data, Boolean>>
) : RecyclerView.Adapter<CustomViewHolder>(), ItemTouchHelperAdapter {


    fun appendItem(title: String, desc: String) {
        data.add(Pair(Data(0, title, desc, "00-00-0000"), false))
        notifyItemInserted(itemCount - 1)
    }

    private fun generateItem() = Pair(Data(0, "title", "desc", "00-00-0000"), false)


    inner class NoteViewHolder(private val binding: NoteRecyclerItemBinding) :
        CustomViewHolder(binding.root), ItemTouchHelperViewHolder {
        override fun bind(data: Pair<Data, Boolean>) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.apply {
                    containerNote.setOnClickListener {
                        onListItemClickListener.onItemClick(data.first)
                    }
                    nTitle.text = data.first.title
                    nDescription.text = data.first.description
                    addItemImageView.setOnClickListener {
                        addItem()
                    }
                    removeItemImageView.setOnClickListener { removeItem() }
                    moveItemUp.setOnClickListener { moveUp() }
                    moveItemDown.setOnClickListener { moveDown() }
                    binding.nDescription.visibility = if (data.second) View.VISIBLE else View.GONE
                    binding.nTitle.setOnClickListener {
                        toggleText()
                    }
                }
            }
        }

        private fun toggleText() {
            data[layoutPosition] = data[layoutPosition].let {
                it.first to !it.second
            }
            notifyItemChanged(layoutPosition)
        }

        private fun addItem() {
            data.add(layoutPosition, generateItem())
            notifyItemInserted(layoutPosition)
        }



        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        private fun moveUp() {
            layoutPosition.takeIf { it > 1 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }


        private fun moveDown() {
            layoutPosition.takeIf { it < data.size }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition + 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            NoteRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size


    interface OnListItemClickListener {
        fun onItemClick(data: Data)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.bind(data[position])

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }


}



