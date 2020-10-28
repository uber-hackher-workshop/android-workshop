package com.queensuber.hackherstarterapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxrelay3.PublishRelay
import com.queensuber.hackherstarterapp.R
import com.queensuber.hackherstarterapp.adapters.NewsArticleAdapter
import com.queensuber.hackherstarterapp.data.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.view.*
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject
    lateinit var statusRelay: PublishRelay<Status>

    private val searchViewModel: SearchViewModel by activityViewModels()
    private lateinit var adapter: NewsArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        root.search_edit_text.setOnEditorActionListener { textView, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    statusRelay.accept(Status.LOADING)
                    searchViewModel.search(textView.text.toString())
                    true
                }
                else -> false
            }
        }
        adapter = NewsArticleAdapter()
        root.rv_search.layoutManager = LinearLayoutManager(context)
        root.rv_search.adapter = adapter

        searchViewModel.response.observe(viewLifecycleOwner, { resource ->
            resource.data?.articles?.let {
                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(root.windowToken, 0)
                adapter.submitArticles(it)
                statusRelay.accept(Status.SUCCESS)
            }
        })
        return root
    }
}