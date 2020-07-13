package com.naveentp.newsapp.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naveentp.newsapp.R
import com.naveentp.shared.NewsDetails
import com.squareup.picasso.Picasso

/**
 * @author Naveen T P
 * @since 02/06/19
 */
class NewsListAdapter(
    private val articles: List<NewsDetails.Article>,
    private val clickListener: (NewsDetails.Article) -> Unit
) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = articles.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener { clickListener(article) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val newsTitle: TextView = itemView.findViewById(R.id.newsTitle)
        private val newsDesc: TextView = itemView.findViewById(R.id.newsDesc)
        private val newsImage: ImageView = itemView.findViewById(R.id.newsImage)

        fun bind(article: NewsDetails.Article) {
            newsTitle.text = article.title
            newsDesc.text = article.description
            Picasso.get().load(article.urlToImage)
                .placeholder(R.drawable.news_placeholder)
                .error(R.drawable.news_placeholder)
                .into(newsImage)
        }
    }
}