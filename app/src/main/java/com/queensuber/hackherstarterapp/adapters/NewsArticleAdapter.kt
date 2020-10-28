package com.queensuber.hackherstarterapp.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.queensuber.hackherstarterapp.R
import com.queensuber.hackherstarterapp.data.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news_article_card.view.*

class NewsArticleAdapter : RecyclerView.Adapter<NewsArticleAdapter.NewsArticleViewHolder>() {

    inner class NewsArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem.content == newItem.content && oldItem.publishedAt == newItem.publishedAt

        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitArticles(list: List<Article>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        return NewsArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news_article_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.itemView.apply {
            article.title?.let { title ->
                card_title.text = title
                card_title.visibility = View.VISIBLE
            } ?: run {card_title.visibility = View.GONE }
            article.description?.let { description ->
                card_description.text = description
                card_description.visibility = View.VISIBLE
            } ?: run {card_description.visibility = View.GONE}
            article.author?.let { author ->
                card_secondary_description.text = author
                card_secondary_description.visibility = View.VISIBLE
            } ?: run {card_secondary_description.visibility = View.GONE}
            article.urlToImage?.let { urlToImage ->
                Picasso.get().load(urlToImage).into(card_image)
            }
            article.url?.let { url ->
                card.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    holder.itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

}