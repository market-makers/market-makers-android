package com.marketmakers.mobile.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.marketmakers.mobile.R
import com.marketmakers.mobile.model.Invoice
import com.marketmakers.mobile.model.Promotion
import com.marketmakers.mobile.repository.api.PromotionAPI
import com.marketmakers.mobile.ui.adapter.ProductAdapter
import com.marketmakers.mobile.ui.adapter.PromotionAdapter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_promotions.*
import kotlinx.android.synthetic.main.app_bar_promotions.*
import kotlinx.android.synthetic.main.content_promotions.*

class PromotionsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, PromotionAdapter.PromotionClickListener {

    private val promotionApi by lazy {
        PromotionAPI()
    }

    private val userId by lazy {
        intent.extras.getString(EXTRA_CURRENT_USER)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotions)
        setSupportActionBar(toolbar)

        content_loading.visibility = View.VISIBLE
        content_promotion.visibility = View.GONE


        fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, ScannerActivity::class.java)
            intent.putExtra(ScannerActivity.extraCurrentUser, userId)
            startActivity(intent)
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val context = this

        promotionApi
                .api
                .getPromotions()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Promotion>> {
                    override fun onComplete() {
                        //not implemented
                    }

                    override fun onSubscribe(d: Disposable) {
                        //not implemented
                    }

                    override fun onNext(promotions: List<Promotion>) {
                        with(promotions_recyclerview) {
                            layoutManager = LinearLayoutManager(context)
                            itemAnimator = DefaultItemAnimator()
                            adapter = PromotionAdapter( context, promotions, this@PromotionsActivity)

                            content_loading.visibility = View.GONE
                            content_promotion.visibility = View.VISIBLE
                        }
                    }

                    override fun onError(e: Throwable) {
                        //not implemented
                    }
                })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.promotions, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(promotion: Promotion) {
        content_loading.visibility = View.VISIBLE
        content_promotion.visibility = View.GONE

        val intent = Intent(this, PromotionDetailsActivity::class.java)
        intent.putExtra(PromotionDetailsActivity.extraPromotion, promotion)
        intent.putExtra(PromotionDetailsActivity.extraUser, userId)
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                // Handle the camera action
            }
            R.id.nav_points -> {

            }
            R.id.nav_getcoupon -> {

            }
            R.id.nav_promotions -> {

            }
            R.id.nav_history -> {

            }
            R.id.nav_logout -> {
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener { t ->
                            startActivity(Intent(this, SignInActivity::class.java))
                            finish()
                        }
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        val EXTRA_CURRENT_USER = "currentUser"
    }
}
