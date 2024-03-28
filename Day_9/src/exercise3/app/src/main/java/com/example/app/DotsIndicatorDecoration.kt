package com.example.app

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()

class DotsIndicatorDecoration(
    @ColorInt colorInactive: Int,
    @ColorInt colorActive: Int,
) : RecyclerView.ItemDecoration() {

    private val indicatorHeight: Int = 20.dp
    private val indicatorItemPadding: Int = 4.dp
    private val radius: Int = 2.dp
    private val inactivePaint = Paint()
    private val activePaint = Paint()

    init {
        inactivePaint.style = Paint.Style.FILL
        inactivePaint.isAntiAlias = true
        inactivePaint.color = colorInactive
        activePaint.style = Paint.Style.FILL_AND_STROKE
        activePaint.strokeCap = Paint.Cap.ROUND
        activePaint.strokeWidth = radius.toFloat() * 2
        activePaint.isAntiAlias = true
        activePaint.color = colorActive
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val adapter = parent.adapter ?: return
        val itemCount = adapter.itemCount

        val totalLength = (this.radius * 2 * itemCount).toFloat()
        val paddingBetweenItems = (0.coerceAtLeast(itemCount - 1) * indicatorItemPadding).toFloat()
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX = (parent.width - indicatorTotalWidth) / 2f

        val indicatorPosY = parent.height - indicatorHeight / 2f

        val activePosition: Int = when (parent.layoutManager) {
            is LinearLayoutManager -> {
                (parent.layoutManager as LinearLayoutManager?)!!.findFirstCompletelyVisibleItemPosition()
            }

            else -> 0
        }

        for (i in 0 until itemCount) {
            val x = indicatorStartX + radius + (radius * 2 + indicatorItemPadding) * i
            val paint = if (i == activePosition) activePaint else inactivePaint
            c.drawCircle(x, indicatorPosY, radius.toFloat(), paint)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = indicatorHeight
    }
}
