package com.timlah.repositories

import reactivemongo.bson.BSONDocument
import reactivemongo.api.bson.collection.BSONCollection
import play.modules.reactivemongo.ReactiveMongoApi

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Inject


class BlogPostRepository @Inject()(
  implicit executionContext: ExecutionContext,
  reactiveMongoApi: ReactiveMongoApi
) {
  val collection: Future[BSONCollection] = reactiveMongoApi.database.map(db => db.collection("blog"))

  def mongoConnection() = {
    ???
  }

  def newPostID() = {
    countAllPosts + 1
  }

  def countAllPosts = {
    1
  }
}
