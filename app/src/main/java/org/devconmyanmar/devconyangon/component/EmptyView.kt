package org.devconmyanmar.devconyangon.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import org.devconmyanmar.devconyangon.R

class EmptyView: RelativeLayout {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init(){
        val view= View.inflate(context, R.layout.view_empty,this)
    }
}