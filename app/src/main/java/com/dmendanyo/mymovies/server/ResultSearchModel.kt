package com.dmendanyo.mymovies.server

import com.google.gson.annotations.SerializedName

data class ResultSearchModel(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var results: List<SearchResults> = listOf(),
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null,
)

data class SearchResults(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
)