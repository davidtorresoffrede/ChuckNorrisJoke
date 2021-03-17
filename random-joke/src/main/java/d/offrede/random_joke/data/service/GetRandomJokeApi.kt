package d.offrede.random_joke.data.service

import d.offrede.random_joke.data.model.JokeData
import retrofit2.Response
import retrofit2.http.GET

interface GetRandomJokeApi {
    @GET("jokes/random")
    suspend fun getRandomJoke(): Response<JokeData>
}