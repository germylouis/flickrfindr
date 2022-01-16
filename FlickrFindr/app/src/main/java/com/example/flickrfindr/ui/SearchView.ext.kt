package com.example.flickrfindr.ui

import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Code provided by https://blog.mindorks.com/instant-search-using-kotlin-flow-operators
 */
fun SearchView.getQueryTextChangeStateFlow(): Flow<String> {
    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })
    return query
}
