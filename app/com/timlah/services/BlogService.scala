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
    val dateTime = DateTime.fromIsoDateTimeString("2022-08-13T10:30:00.012Z")
    val testMarkup = markupService.markdownFileToHTML()
    BlogPost(
      id            = blogPostRepository.newPostID(),
      author        = author,
      coauthor      = coauthor,
      title         = title,
      content       = testMarkup match {
        case Left(_)  => ""
        case Right(s) => s
      },
      date          = dateTime.get
    )
  }

  def getLatestBlogPost() = {
    blogPostRepository
  }
}
