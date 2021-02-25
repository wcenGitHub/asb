package nz.co.asb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nz.co.asb.databinding.TransactionListItemBinding
import nz.co.asb.model.Transaction

class TransactionListAdapter(var transactionList: List<Transaction>, private val listener: TransactionSelectionListener?)
    : RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    interface TransactionSelectionListener {
        fun onTransactionSelected(transaction: Transaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TransactionListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val transaction = transactionList[position]
        viewHolder.bind(transaction)
        viewHolder.itemView.setOnClickListener {
            listener?.onTransactionSelected(transaction)
        }
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    class ViewHolder(private val binding: TransactionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.data = TransactionUiBindingData(transaction, binding.root.context)
            binding.executePendingBindings()
        }
    }
}