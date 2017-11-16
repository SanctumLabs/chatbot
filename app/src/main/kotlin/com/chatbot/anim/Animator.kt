package com.chatbot.anim

import android.view.View
import com.facebook.rebound.*

/**
 * @author lusinabrian on 16/11/17.
 * @Notes Animator
 * Constructor for with Animation Type + Spring config + animation Values
 * @param springConfig config class for the spring
 * @param  type SpringyAnimationType instance for animation type
 * @param  tension Spring tension for animation type
 * @param  fraction Spring fraction value for animation
 * @param  startValue where animation start from
 * @param  endValue where animation ends to
*/
class Animator(var type: AnimType, var startValue: Double, var endValue: Double) {
    private val defaultTension = 40f
    private val defaultFraction = 7f
    private var tension: Double = 0.toDouble()
    private var fraction: Double = 0.toDouble()

    val springSystem: SpringSystem by lazy { SpringSystem.create() }
    private var animationType: AnimType? = null
    lateinit var animListener: AnimListener
    var delay = 0L

    /**
     * Constructor for with Animation Type + default config for spring + animation Values
     * * @param springConfig config class for the spring
     * @param  type SpringyAnimationType instance for animation type
     * @param  startValue where animation start from
     * @param  endValue where animation ends to
     */
    constructor(type: AnimType, startValue: Double, endValue: Double, tension: Double, fraction: Double)
            : this(type, startValue, endValue){
        this.tension = defaultTension.toDouble()
        this.fraction = defaultFraction.toDouble()
        animationType = type
    }

    fun startSpring(view: View) {
        setInitValue(view)
        val startAnimation = Runnable {
            val spring = springSystem.createSpring()
            spring.springConfig = SpringConfig.fromOrigamiTensionAndFriction(tension, fraction)
            spring.addListener(object : SimpleSpringListener() {
                override fun onSpringUpdate(spring: Spring) {
                    view.visibility = View.VISIBLE
                    val value = SpringUtil.mapValueFromRangeToRange(spring.currentValue, 0.0, 1.0, startValue, endValue).toFloat()
                    when (animationType) {
                        AnimType.TRANSLATEY -> view.translationY = value
                        AnimType.TRANSLATEX -> view.translationX = value
                        AnimType.ALPHA -> view.alpha = value
                        AnimType.SCALEY -> view.scaleY = value
                        AnimType.SCALEX -> view.scaleX = value
                        AnimType.SCALEXY -> {
                            view.scaleY = value
                            view.scaleX = value
                        }
                        AnimType.ROTATEY -> view.rotationY = value
                        AnimType.ROTATEX -> view.rotationX = value
                        AnimType.ROTATION -> view.rotation = value
                    }
                }

                override fun onSpringAtRest(spring: Spring) {
                    //animListener.onSpringStop()
                }

                override fun onSpringActivate(spring: Spring) {
                    //animListener.onSpringStart()
                }

            })
            spring.endValue = 1.0
        }
        view.postDelayed(startAnimation, delay)

    }

    /**
     * @param  view  instance for  set pre animation value
     */
    private fun setInitValue(view: View) {
        when (animationType) {
            AnimType.TRANSLATEY -> view.translationY = startValue.toFloat()
            AnimType.TRANSLATEX -> view.translationX = startValue.toFloat()
            AnimType.ALPHA -> view.alpha = startValue.toFloat()
            AnimType.SCALEY -> view.scaleY = startValue.toFloat()
            AnimType.SCALEX -> view.scaleX = startValue.toFloat()
            AnimType.SCALEXY -> {
                view.scaleY = startValue.toFloat()
                view.scaleX = startValue.toFloat()
            }
            AnimType.ROTATEY -> view.rotationY = startValue.toFloat()
            AnimType.ROTATEX -> view.rotationX = startValue.toFloat()
            AnimType.ROTATION -> view.rotation = startValue.toFloat()
        }
    }
}