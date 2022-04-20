package uz.micro.star.mixlib

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.AttrRes

/**
 * Created by Microstar on 10.03.2022.
 */
class NextBtnView : FrameLayout {
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private var buttonClickListener: (() -> Unit)? = null

    fun setButtonClickListener(f: () -> Unit) {
        buttonClickListener = f
    }

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init()
    }

    constructor(context: Context, attrs: AttributeSet, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init()
    }

    fun dismissLoader() {
        progressBar.visibility= View.GONE
        textView.visibility= View.VISIBLE
        textView.isClickable = true
    }

    fun init() {
        val view = inflate(context, R.layout.next_btn_layout, this)
        textView = view.findViewById(R.id.textView)
        progressBar = view.findViewById(R.id.progressbar)
        textView.setOnClickListener {
            textView.isClickable = false
            textView.visibility= View.GONE
            progressBar.visibility= View.VISIBLE
            buttonClickListener?.invoke()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val k = widthMeasureSpec.coerceAtMost(heightMeasureSpec)
        super.onMeasure(2 * k, k)
    }

}