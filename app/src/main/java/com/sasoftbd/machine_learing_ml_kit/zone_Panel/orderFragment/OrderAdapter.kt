package com.sasoftbd.machine_learing_ml_kit.zone_Panel.orderFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sasoftbd.machine_learing_ml_kit.R

class OrderAdapter(private val mList: List<OrderModel>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_fragment, parent, false)



        return ViewHolder(view, mListener)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.voutcher_no.text = ItemsViewModel.strCustomerName
        holder.ledger_name.text = ItemsViewModel.straddress
        holder.area_name.text = ItemsViewModel.strPhone
        holder.ledger_name.text = ItemsViewModel.straddress
        holder.total_amount.text = "5050 Tk"

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(ItemView) {

        val voutcher_no: TextView = itemView.findViewById(R.id.order_fragment_voucher_no)
        val ledger_name: TextView = itemView.findViewById(R.id.order_fragment_ledger_name)
        val area_name: TextView = itemView.findViewById(R.id.order_fragment_area_name)
        val division_name: TextView = itemView.findViewById(R.id.order_fragment_division_id)
        val order_date: TextView = itemView.findViewById(R.id.order_fragment_date_id)
        val total_amount: TextView = itemView.findViewById(R.id.order_fragment_total_amount)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }


}