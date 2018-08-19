package com.marketmakers.mobile.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.marketmakers.mobile.model.Product
import com.marketmakers.mobile.R
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter(private val products: List<Product>,
                     private val context: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)

        val product = products[position]

        view.product_name.text = product.name
        view.product_qty.text = product.quantity.toString()
        view.product_price.text = product.value.toString()
        view.product_category.text = product.category

        return view
    }

    override fun getItem(position: Int): Product = products[position]

    override fun getItemId(position: Int): Long = products[position].id

    override fun getCount(): Int = products.size
}