package com.example.simonfxx.ui.chart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

class ChartFragment : Fragment() {
    private var webView: WebView? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        val wv = WebView(requireContext())
        webView = wv

        wv.settings.javaScriptEnabled = true
        wv.settings.domStorageEnabled = true
        wv.settings.cacheMode = WebSettings.LOAD_DEFAULT
        wv.webViewClient = WebViewClient()

        // Simple EURUSD chart; we can deep-link symbols later.
        wv.loadUrl("https://s.tradingview.com/widgetembed/?symbol=FX:EURUSD&interval=60&hide_top_toolbar=1&hide_legend=1")

        return wv
    }

    override fun onDestroyView() {
        webView?.apply {
            stopLoading()
            webViewClient = null
            destroy()
        }
        webView = null
        super.onDestroyView()
    }
}