package com.whycody.wordslife.home.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.whycody.wordslife.BR
import com.whycody.wordslife.R
import com.whycody.wordslife.data.HistoryItem

class HistoryAdapter: ListAdapter<HistoryItem, HistoryAdapter.HistoryHolder>(HistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,
            R.layout.item_history, parent, false)
        return HistoryHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.setupData(getItem(position))
    }

    inner class HistoryHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {

        fun setupData(historyItem: HistoryItem) {
            binding.setVariable(BR.historyItem, historyItem)
            binding.setVariable(BR.position, layoutPosition)
            binding.executePendingBindings()
        }
    }
}