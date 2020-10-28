package com.queensuber.hackherstarterapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.queensuber.hackherstarterapp.R
import com.queensuber.hackherstarterapp.adapters.NewsArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.android.synthetic.main.fragment_search.view.*

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by activityViewModels()
    private lateinit var adapter: NewsArticleAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        root.search_edit_text.setOnEditorActionListener { textView, actionId, keyEvent ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchViewModel.search(textView.text.toString())
                    true
                }
                else -> false
            }
        }
        adapter = NewsArticleAdapter()
        root.rv_search.layoutManager = LinearLayoutManager(context)
        root.rv_search.adapter = adapter

        searchViewModel.response.observe(viewLifecycleOwner, Observer { resource ->
            resource.data?.articles?.let {
                adapter.submitArticles(it)
            }
        })
        return root
    }
}