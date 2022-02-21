package com.timlah.repositories

class BlogPostRepository {

  def mongoConnection() = {

  }

  def newPostID() = {
    countAllPosts + 1
  }

  def countAllPosts = {
    1
  }
}
