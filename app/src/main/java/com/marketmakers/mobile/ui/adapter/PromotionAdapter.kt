package com.marketmakers.mobile.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marketmakers.mobile.R
import com.marketmakers.mobile.model.Promotion
import kotlinx.android.synthetic.main.promotion_item.view.*

class PromotionAdapter(private val context: Context,
                       private val promotions: List<Promotion>) : RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.promotion_item, parent, false)
        return PromotionViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return promotions.size
    }

    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
        holder.bind(promotions[position])
    }

    inner class PromotionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(promotion: Promotion) {
            with(itemView) {
                promotion_title.text = promotion.title
                promotion_type.text = promotion.type
                promotion_value.text = "${promotion.value.toShort()}% -"
                promotion_dots.text = "${promotion.dots.toString()} pontos"
                promotion_company_name.text = promotion.company.name
            }
        }
    }
}