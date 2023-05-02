package com.example.pokemoninfo.ui

import android.R.attr.bitmap
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.pokemoninfo.R


private const val TAG = "PokemonListView"

class PokemonListView : View {

    constructor (context: Context?) : super(context) {}
    constructor (context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor (context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    private val rect = Rect()
    private val paint = Paint()
    var bm = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    val rectf = RectF(10f, 10f, 500f, 475f)
    var pokemonName: String = ""
    var pokemonPhoto: String = ""
    var pokemonType: String = "normal"


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.UNSPECIFIED)
        val height = MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.UNSPECIFIED)
        this.setMeasuredDimension(width, height / 4)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBackGround(canvas)
        drawImageFromUrl(canvas)
    }



    private fun drawBackGround(canvas: Canvas){
        paint.apply {
            isAntiAlias = true
            color = when(pokemonType){
                "normal" -> resources.getColor(R.color.beige)
                "fire" -> resources.getColor(R.color.light_red)
                "bug" -> resources.getColor(R.color.dark_green)
                "poison" -> resources.getColor(R.color.purple)
                "flying" -> resources.getColor(R.color.grey_blue)
                "dark" -> resources.getColor(R.color.dark_purple)
                "dragon" -> resources.getColor(R.color.azure)
                "ghost" -> resources.getColor(R.color.light_purple)
                "psychic" -> resources.getColor(R.color.pink)
                "electric" -> resources.getColor(R.color.yellow)
                "grass" -> resources.getColor(R.color.light_green)
                "rock" -> resources.getColor(R.color.brown)
                "steel" -> resources.getColor(R.color.light_blue)
                "ground" -> resources.getColor(R.color.dark_brown)
                "fairy" -> resources.getColor(R.color.light_pink)
                "fighting" -> resources.getColor(R.color.orange)
                "ice" -> resources.getColor(R.color.blue_white)
                "water" -> resources.getColor(R.color.blue)
                else -> {
                    resources.getColor(R.color.white)
                }
            }
            canvas.drawRoundRect(rectf, 40f, 40f, paint)
        }
    }


    private fun drawImageFromUrl(canvas: Canvas) {
        Glide.with(context)
            .asBitmap()
            .load(pokemonPhoto)
            .into(object: CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bm = Bitmap.createScaledBitmap(resource, 300, 300, false)
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })

        canvas.drawBitmap(bm, 0f, 0f, null)
    }
}
