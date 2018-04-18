package me.ssiddh.pictureapp.api

import me.ssiddh.pictureapp.models.PhotoList
import retrofit2.Call
import retrofit2.http.GET

interface PhotoApi {
    @GET("?key=8727305-46dc7e4a438617f2a0d094f2d&q=motorbike&image_type=photo")
    fun getPhotos() : Call<PhotoList>
}