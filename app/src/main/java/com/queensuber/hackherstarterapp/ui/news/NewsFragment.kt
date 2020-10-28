package com.queensuber.hackherstarterapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxrelay3.PublishRelay
import com.queensuber.hackherstarterapp.R
import com.queensuber.hackherstarterapp.adapters.NewsArticleAdapter
import com.queensuber.hackherstarterapp.data.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.view.*
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    @Inject
    lateinit var statusRelay: PublishRelay<Status>
    private val newsArticleViewModel: NewsArticleViewModel by activityViewModels()
    private lateinit var adapter: NewsArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        statusRelay.accept(Status.LOADING)
        adapter = NewsArticleAdapter()
        root.rv_news.layoutManager = LinearLayoutManager(context)
        root.rv_news.adapter = adapter
        newsArticleViewModel.response.observe(viewLifecycleOwner, Observer { resource ->
            resource.data?.articles?.let {
                adapter.submitArticles(it)
                statusRelay.accept(Status.SUCCESS)
            }
        })
        return root
    }
}