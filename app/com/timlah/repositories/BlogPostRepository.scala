package com.timlah.repositories

// https://www.baeldung.com/scala/slick-intro for more info
// Note: This is brittle at the moment. Using the "live" database on heroku to test. Shouldn't be doing this.
// Note: If the connection doesn't work any more, double check variables on heroku.
// Alternatively to the above, via terminal `heroku login` then `heroku config:get DATABASE_URL -a timlah-com`

import akka.http.scaladsl.model.DateTime
import com.timlah.models.{Author, BlogPost}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json.{Json, OFormat}
import slick.ast.BaseTypedType
import slick.jdbc.{JdbcProfile, JdbcType}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Inject

class BlogPostRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(
  implicit executionContext: ExecutionContext,
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAllBlogPosts: Future[Seq[BlogPost]] = {
    val blogPosts = TableQuery[BlogPostTable]
    val query: Query[BlogPostTable, BlogPost, Seq] = blogPosts
    db.run[Seq[BlogPost]](query.result)
  }

  def getBlogEntryById(id : Int) : Future[Option[BlogPost]] = {
    val blogPosts = TableQuery[BlogPostTable]
    val query: Query[BlogPostTable, BlogPost, Seq] = blogPosts.filter(_.id === id)
    val queryResult: Future[Seq[BlogPost]] = db.run[Seq[BlogPost]](query.result)
    for {
      posts: Seq[BlogPost] <- queryResult
      post = posts.headOption
    } yield post
  }

  def getBlogEntryBySlug(slug: String) : Future[Option[BlogPost]] = {
    val blogPosts = TableQuery[BlogPostTable]
    val query: Query[BlogPostTable, BlogPost, Seq] = blogPosts.filter(_.slug === slug)
    val queryResult: Future[Seq[BlogPost]] = db.run[Seq[BlogPost]](query.result)
    for {
      posts: Seq[BlogPost] <- queryResult
      post = posts.headOption
    } yield post
  }

  def getBlogPostCount: Future[Int] = { getAllBlogPosts.map(_.size) }
  def getLatestBlogPost: Future[BlogPost] = {
    val blogPosts = TableQuery[BlogPostTable]
    val query: Query[BlogPostTable, BlogPost, Seq] = blogPosts
    val queryResult: Future[Seq[BlogPost]] = db.run[Seq[BlogPost]](query.result)
    queryResult.map(_.last)
  }
}

class BlogPostTable(tag: Tag) extends Table[BlogPost](tag, "posts") {

  implicit val authorFormat     : OFormat[Author] = Json.format[Author]
  implicit val configColumnType : JdbcType[Author] with BaseTypedType[Author] = MappedColumnType.base[Author, String](
    author => Json.stringify(Json.toJson(author)),
    column => Json.parse(column).as[Author]
  )
  implicit val dateTimeFormat     : OFormat[DateTime] = Json.format[DateTime]
  implicit val dateTimeColumnType : JdbcType[DateTime] with BaseTypedType[DateTime] = MappedColumnType.base[DateTime, String](
    dateTime => Json.stringify(Json.toJson(dateTime)),
    column => Json.parse(column).as[DateTime]
  )

  override def * = (author, coauthor, title, slug, content, date) <> (BlogPost.tupled, BlogPost.unapply)

  val id      : Rep[Int]              = column[Int           ]("id"       , O.AutoInc, O.PrimaryKey)
  val author  : Rep[Author]           = column[Author        ]("author"   )
  val coauthor: Rep[Option[Author]]   = column[Option[Author]]("coauthor" )
  val title   : Rep[String]           = column[String        ]("title"    )
  val slug    : Rep[String]           = column[String        ]("slug"     )
  val content : Rep[String]           = column[String        ]("content"  )
  val date    : Rep[DateTime]         = column[DateTime      ]("date"     )
}