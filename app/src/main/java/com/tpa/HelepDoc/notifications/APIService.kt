package com.tpa.HelepDoc.notifications

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {
    @Headers(
        "Content-Type: application/json",
        "Authorization:key=AAAAF0XeDFg:APA91bEw8nFsItgBhmjePPy_wHtWE8o8apwPBnkGACm9DlRbufDJUVolq_Rwahx6QP6qR0qdTQacSBTx83x8NZ_Q8tsEtqI7VHY1ibptLqfwk38y7Arm3XpNpCw53VgxhuqJ7_1T9WT2"
    )
    @POST("fcm/send")
    fun sendNotification(@Body body: Sender?): Call<MyResponse>?
}