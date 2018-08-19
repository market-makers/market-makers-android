package com.marketmakers.mobile.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.marketmakers.mobile.R
import com.marketmakers.mobile.model.Invoice
import com.marketmakers.mobile.model.UserInvoice
import com.marketmakers.mobile.repository.api.UserAPI
import com.marketmakers.mobile.ui.adapter.ProductAdapter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.app_bar_promotions.*
import kotlinx.android.synthetic.main.content_invoice_confirmation.*

class InvoiceConfirmationActivity : AppCompatActivity() {

    private val userApi by lazy {
        UserAPI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_confirmation)
        setSupportActionBar(toolbar)

        content_company.visibility = View.GONE
        content_loading.visibility = View.VISIBLE

        val invoiceId = intent.extras.getString(extraInvoiceId)
        val userId = intent.extras.getString(extraCurrentUser)

        userApi
                .api
                .createInvoice(userId, UserInvoice(invoiceId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Invoice> {
                    override fun onComplete() {
                        Log.d("Users", "Completed")
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(invoice: Invoice) {
                        atualizaView(invoice)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Users", "Error - ${e.message}")
                    }

                });

    }

    private fun atualizaView(invoice: Invoice) {
        company_name.text = invoice.company.name
        company_cnpj.text = invoice.company.cnpj
        company_address_street.text = invoice.company.address.street
        company_address_neighborhood.text = invoice.company.address.neighborhood
        company_address_city_state.text = "${invoice.company.address.city} - ${invoice.company.address.state}"

        with(product_reyclerview) {
            layoutManager = LinearLayoutManager(this@InvoiceConfirmationActivity)
                    itemAnimator = DefaultItemAnimator()
            adapter = ProductAdapter(invoice.products, this@InvoiceConfirmationActivity)
        }

        content_company.visibility = View.VISIBLE
        content_loading.visibility = View.GONE

    }

    companion object {
        val extraInvoiceId = "invoiceId"
        val extraCurrentUser = "currentUser"
    }
}
