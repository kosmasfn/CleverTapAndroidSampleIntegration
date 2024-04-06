package com.kosmasfn.cttest.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    fun getData(
        @Query("part") part : String,
        @Query("q") q: String,
        @Query("key") key : String,
        @Query("maxResults") maxResults: Int ?= 20
    ) : Response<String>
}