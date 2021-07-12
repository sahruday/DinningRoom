package com.sahu.dinningroom.ui.orders.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahu.dinningroom.R
import com.sahu.dinningroom.appUtil.CustomCountDownTimer
import com.sahu.dinningroom.appUtil.GroupToneGenerator
import com.sahu.dinningroom.dataHolders.ACCEPTED
import com.sahu.dinningroom.dataHolders.EXPIRED
import com.sahu.dinningroom.dataHolders.Order
import com.sahu.dinningroom.dataHolders.REJECTED
import com.sahu.dinningroom.databinding.OrderItemBinding
import com.sahu.dinningroom.ext.formattedTime
import com.sahu.dinningroom.ext.toTimeInMills

class OrderAdapter(private val updateOrderStatus: (Order, Int) -> Unit) :
    ListAdapter<Order, OrderAdapter.ItemVH>(DiffCallback()) {

    inner class ItemVH(val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var timer: CustomCountDownTimer? = null

        fun bind(order: Order) {
            binding.order = order

            val res = binding.orderNumber.resources

            //Items Adapter and data.
            val adapter = OrderItemsAdapter()
            binding.itemsCycle.adapter = adapter
            adapter.submitList(order.items)

            //Expired State behaviour
            if (order.status == EXPIRED) {
                binding.progress.visibility = View.GONE
                binding.timeLeft.visibility = View.GONE
                binding.indicator.setBackgroundColor(res.getColor(R.color.red))

                binding.button.text = res.getText(R.string.expired)
                binding.button.tag = REJECTED
                binding.button.setOnClickListener {
                    updateOrderStatus(order, REJECTED)
                }
                return
            }


            val endTime = order.expireAt.toTimeInMills()
            val duration = endTime - System.currentTimeMillis()
            if (duration > 0) {

                val alarmDuration = ((endTime - order.alertAt.toTimeInMills()) / 1000).toInt()
                binding.progress.isIndeterminate = false
                binding.progress.max = (duration / 1000).toInt()

                binding.indicator.setBackgroundColor(res.getColor(R.color.green))

                timer = object :
                    CustomCountDownTimer(endTime, alarmDuration, duration, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val timeLeft =
                            ((expireTimeInMills - System.currentTimeMillis()) / 1000).toInt()

                        if (timeLeft > 0) {
                            binding.progress.visibility = View.VISIBLE
                            binding.timeLeft.visibility = View.VISIBLE

                            binding.progress.progress = timeLeft
                            binding.timeLeft.text = timeLeft.formattedTime(res)
                            binding.button.text = res.getText(R.string.accept)
                            binding.button.tag = ACCEPTED
                        } else {
                            updateOrderStatus.invoke(order, EXPIRED)
                            this.cancel()
                            GroupToneGenerator.removeFromList(order.id)
                        }

                        if (timeLeft < alarmDuration) {
                            if (currentList.contains(order)) {
                                GroupToneGenerator.addToList(order.id)
                                binding.indicator.setBackgroundColor(res.getColor(R.color.yellow))
                            } else {
                                GroupToneGenerator.removeFromList(order.id)
                                this.cancel()
                            }
                        }
                    }

                    override fun onFinish() {}
                }.start() as CustomCountDownTimer
            } else { //It means Item is Expired but status is not update in DB
                updateOrderStatus(order, EXPIRED)
            }

            binding.button.setOnClickListener {
                updateOrderStatus.invoke(order, it.tag as Int)
                GroupToneGenerator.removeFromList(order.id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val itemLayoutBinding = DataBindingUtil.inflate<OrderItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.order_item, parent, false
        )
        return ItemVH(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.timer?.cancel()
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean =
            oldItem == newItem
    }
}