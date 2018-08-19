package com.marketmakers.mobile.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.marketmakers.mobile.model.Product
import com.marketmakers.mobile.R
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter(private val products: List<Product>,
                     private val context: Context) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(products[position])

    override fun getItemId(position: Int): Long = products[position].id

    inner class ProductViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        internal fun bind(product: Product) {
            itemView.product_name.text = product.name
            itemView.product_category.text = product.category
            itemView.product_qty.text = product.quantity.toString()
            itemView.product_price.text = product.value.toString()
        }
    }
}