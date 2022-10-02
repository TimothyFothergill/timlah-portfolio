package com.timlah.services

import akka.http.scaladsl.model.DateTime
import com.timlah.models.{Author, BlogPost}
import com.timlah.repositories.BlogPostRepository

import javax.inject.Inject

class BlogService @Inject()(
  blogPostRepository  : BlogPostRepository,
  markupService       : MarkupService
) {

  def createBlogPost(author: Author, coauthor: Option[Author], title: String): BlogPost = {
    val dateTime = DateTime.now
    val testMarkup = markupService.markdownFileToHTML()
    BlogPost(
      author        = author,
      coauthor      = coauthor,
      title         = title,
      slug          = "banana",
      content       = testMarkup match {
        case Left(_)  => ""
        case Right(s) => s
      },
      date          = dateTime
    )
  }

  def getLatestBlogPost() = {
    blogPostRepository
  }
}
