package com.timlah.repositories

import akka.http.scaladsl.model.DateTime
import com.timlah.models.{Author, BlogPost}
import com.timlah.models.admin.AdminLoginDetails
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json._
import slick.ast.BaseTypedType
import slick.jdbc.{JdbcProfile, JdbcType}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

import scala.concurrent.Await
import scala.concurrent.duration._

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Inject
import scala.concurrent.Await
import com.timlah.models.StoredBlogPost

class BlogPostRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(
  implicit executionContext: ExecutionContext,
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAllUsers: Future[Seq[AdminLoginDetails]] = {
    val userDetailsTable = TableQuery[AdminLoginTable]
    val query: Query[AdminLoginTable, AdminLoginDetails, Seq] = userDetailsTable
    db.run[Seq[AdminLoginDetails]](query.result)
  }

  def checkUserDetails(adminForm: AdminLoginDetails): Future[Boolean] = {
    val userDetailsTable = TableQuery[AdminLoginTable]
    val query: Query[AdminLoginTable, AdminLoginDetails, Seq] = userDetailsTable.filter(_.username === adminForm.username)
    val queryResult: Future[Seq[AdminLoginDetails]] = db.run[Seq[AdminLoginDetails]](query.result)
    for {
      userDetails: Seq[AdminLoginDetails] <- queryResult
      user = userDetails.head
    } 
    yield user.password == adminForm.password
  }

  def insertBlogPost(blogPost: StoredBlogPost) = {
    val blogPostsTable = TableQuery[StoredBlogPostTable]
    val insertBlogPost = blogPostsTable += blogPost
    db.run(insertBlogPost)
  }

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
    queryResult.map(_.maxBy(_.id))
  }
}

class StoredBlogPostTable(tag: Tag) extends Table[StoredBlogPost](tag, sys.env.getOrElse("ACTIVE_TABLE", "")) {
  implicit val configColumnType : JdbcType[Author] with BaseTypedType[Author] = MappedColumnType.base[Author, String](
    author => Json.stringify(Json.toJson(author)),
    column => Json.parse(column).as[Author]
  )

  override def * = (id, author, coauthor, title, slug, content, date) <> ((StoredBlogPost.apply _).tupled, StoredBlogPost.unapply)
  val id        : Rep[Int]              = column[Int             ]("id"       , O.AutoInc, O.PrimaryKey)
  val author    : Rep[Author]           = column[Author          ]("author"     )
  val coauthor  : Rep[Option[Author]]   = column[Option[Author]  ]("coauthor"   )
  val title     : Rep[String]           = column[String          ]("title"      )
  val slug      : Rep[String]           = column[String          ]("slug"       )
  val content   : Rep[String]           = column[String          ]("content"    )
  val date      : Rep[String]           = column[String          ]("date"       )
}

class BlogPostTable(tag: Tag) extends Table[BlogPost](tag, sys.env.getOrElse("ACTIVE_TABLE", "")) {

  implicit val configColumnType : JdbcType[Author] with BaseTypedType[Author] = MappedColumnType.base[Author, String](
    author => Json.stringify(Json.toJson(author)),
    column => Json.parse(column).as[Author]
  )
  implicit val dateTimeFormat     : OFormat[DateTime] = Json.format[DateTime]
  implicit val dateTimeColumnType : JdbcType[DateTime] with BaseTypedType[DateTime] = MappedColumnType.base[DateTime, String](
    dateTime => Json.stringify(Json.toJson(dateTime)),
    column => Json.parse(column).as[DateTime]
  )

  override def * = (id, author, coauthor, title, slug, content, date) <> (BlogPost.tupled, BlogPost.unapply)

  val id        : Rep[Int]              = column[Int             ]("id"       , O.AutoInc, O.PrimaryKey)
  val author    : Rep[Author]           = column[Author          ]("author"     )
  val coauthor  : Rep[Option[Author]]   = column[Option[Author]  ]("coauthor"   )
  val title     : Rep[String]           = column[String          ]("title"      )
  val slug      : Rep[String]           = column[String          ]("slug"       )
  val content   : Rep[String]           = column[String          ]("content"    )
  val date      : Rep[DateTime]         = column[DateTime        ]("date"       )
}

class AdminLoginTable(tag: Tag) extends Table[AdminLoginDetails](tag, sys.env.getOrElse("USER_TABLE", "user")) {

  override def * = (username, password) <> ((AdminLoginDetails.apply _).tupled, AdminLoginDetails.unapply)
  val username: Rep[String] = column[String]("username")
  val password: Rep[String] = column[String]("password")
}