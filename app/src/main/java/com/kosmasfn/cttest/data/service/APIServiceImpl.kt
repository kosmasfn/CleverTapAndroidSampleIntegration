package com.kosmasfn.cttest.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServiceImpl : APIService {
    @GET("search")
    override fun getData(
        @Query("part") part: String,
        @Query("q") q: String,
        @Query("key") key: String,
        @Query("maxResults") maxResults: Int?
    ): Response<String>
}