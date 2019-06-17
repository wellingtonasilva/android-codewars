package br.com.wsilva.codewars.sevice.rest

import br.com.wsilva.codewars.model.dto.ChallengesCompletedDTO
import br.com.wsilva.codewars.model.dto.UserDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String,
                @Header("Authorization") authorization: String): Single<UserDTO>

    @GET("users/{username}/code-challenges/completed")
    fun getCodeChallengesCompleted(@Path("username") username: String,
                                   @Query("page") page: Int,
                @Header("Authorization") authorization: String): Single<ChallengesCompletedDTO>
}