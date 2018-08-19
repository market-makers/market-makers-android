package com.marketmakers.mobile.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.marketmakers.mobile.R
import com.marketmakers.mobile.model.Invoice
import com.marketmakers.mobile.model.Promotion
import com.marketmakers.mobile.model.Rescue
import com.marketmakers.mobile.model.User
import com.marketmakers.mobile.repository.api.UserAPI
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_promotion_details.*

class PromotionDetailsActivity : AppCompatActivity() {

    private val userApi by lazy {
        UserAPI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion_details)

        val promotion = intent.extras.get(extraPromotion) as Promotion
        val userId = intent.extras.getString(extraUser)

        promotion_details_title.text = promotion.title
        promotion_details_type.text = promotion.type
        promotion_details_value.text = "${promotion.value.toString()}%"
        promotion_details_dots.text = "${promotion.dots.toString()} pontos"
        promotion_details_company_name.text = promotion.company.name
        promotion_details_company_neighborhood.text = promotion.company.address.neighborhood
        promotion_details_company_city_state.text = "${promotion.company.address.city} - ${promotion.company.address.state}"



        btn_rescue.setOnClickListener {
            content_promotion_details.visibility = View.GONE
            content_loading.visibility = View.VISIBLE

            userApi
                    .api
                    .rescue(Rescue(userId, promotion.id))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<User> {
                        override fun onComplete() {
                            //not implemented
                        }

                        override fun onSubscribe(d: Disposable) {
                            //not implemented
                        }

                        override fun onNext(user: User) {
                            content_promotion_details.visibility = View.VISIBLE
                            content_loading.visibility = View.GONE

                            val intent = Intent(applicationContext, DotsEarnedActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.putExtra(DotsEarnedActivity.extraUserUpdated, user)
                            startActivity(intent)
                        }

                        override fun onError(e: Throwable) {
                            //not implemented
                        }

                    })
        }
    }

    companion object {
        val extraPromotion = "promotion"
        val extraUser = "user"
    }
}
