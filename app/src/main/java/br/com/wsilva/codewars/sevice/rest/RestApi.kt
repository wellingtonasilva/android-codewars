package br.com.wsilva.codewars.sevice.rest

import br.com.wsilva.codewars.model.dto.UserDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RestApi {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String,
                @Header("Authorization") authorization: String): Single<UserDTO>
}