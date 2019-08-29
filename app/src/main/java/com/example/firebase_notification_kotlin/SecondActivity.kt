package com.example.firebase_notification_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val notification = intent.extras?.get("Notificacion") as? NotificationVO
        println(notification.toString())
        notification?.let {
            cargarNotificacion(it)
        }
    }

    private fun cargarNotificacion(notificationVO: NotificationVO) {
        notification_title.append(notificationVO.title)
        if(notificationVO.image != null){
            cargarImagenUrl(notificationVO.image)
        }
        notification_description.append(notificationVO.message)
    }

    private fun cargarImagenUrl(image: String?) {
        Picasso.with(this).load(image).into(notification_image)
    }

}
