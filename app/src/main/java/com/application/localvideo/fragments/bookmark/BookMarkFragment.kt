package com.application.localvideo.fragments.bookmark

import android.view.View
import com.application.localvideo.R
import com.application.localvideo.base.BaseFragment
import kotlinx.android.synthetic.main.toolbar.*

class BookMarkFragment : BaseFragment() {

    override fun getLayout(): Int {
        return R.layout.fragment_bookmark
    }

    override fun initView(view: View) {
        initHeader()
    }

    private fun initHeader() {
        header_text.text = getString(R.string.bookmark)
    }
}