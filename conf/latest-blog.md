Recently I decided this website needed a more dynamic blog system. This would prove to be an interesting and useful challenge, one that I'm glad to have undertaken.

Blog posts are now stored in a database, meaning I can save them properly and thus provide a method for dynamically curating urls for each post. Scala's a rather verbose language, so here's a high-level view for how I implemented this.

## Technical malarkey for this here blog

First, a BlogPost case class:
```scala
case class BlogPost(
  author  : Author        ,
  coauthor: Option[Author],
  title   : String        ,
  slug    : String        ,
  content : String        ,
  date    : DateTime
)
```

Simple data. I like it!

Next up, I set up the database in my config file and wrote a Repository file, which handles all work around the database. I used Slick as my library of choice to connect to my database. One interesting point was setting up the proven shape of the BlogPost object to match fields in the database.

```scala
// other stuff above here
  override def * = (author, coauthor, title, slug, content, date) <> (BlogPost.tupled, BlogPost.unapply)

  val id      : Rep[Int]              = column[Int           ]("id"       , O.AutoInc, O.PrimaryKey)
  val author  : Rep[Author]           = column[Author        ]("author"   )
  val coauthor: Rep[Option[Author]]   = column[Option[Author]]("coauthor" )
  val title   : Rep[String]           = column[String        ]("title"    )
  val slug    : Rep[String]           = column[String        ]("slug"     )
  val content : Rep[String]           = column[String        ]("content"  )
  val date    : Rep[DateTime]         = column[DateTime      ]("date"     )
}
```
Next I had to make sure there was a route for the new blog pages set up. I wanted to make this dynamically set based on data in each BlogPost. Here comes `IDs` and `slugs`.
```routes
GET     /blog/id/:id                com.timlah.controllers.HomeController.blogByID(id: Int)
GET     /blog/slug/:slug            com.timlah.controllers.HomeController.blogBySlug(slug: String)
```

Lastly, now we have our routing setup, we just need to set it up in our HomeController.
```scala
//HomeController
def blogByID(id: Int) = Action.async { implicit request: Request[AnyContent] =>
  val getFutureBlogPost = repository.getBlogEntryById(id)
  getFutureBlogPost.map(i =>
    Ok(com.timlah.views.html.blog(
      i.get, markupService.markdownStringToHTML(i.get.content) match {
        case Left(_) => "<Unable to render the post at this time.>"
        case Right(s) => s
      }
    )))
}
```

My blog template accepts a `BlogPost` object, which does all the work for laying out how these blog posts look. The `markupService.markdownStringToHTML(i.get.content)` pattern match could be done much better, however it's perfectly serviceable for now. A service that takes a markdown string and transforms it into HTML. 

The above post is just a small part of the work that was undertaken to get this set up. To increase transparency, I have made the [repo public](https://github.com/TimothyFothergill/timlah-portfolio).

## What's next?
I needed to take a week or so away from my RPG project, as that's such a big project. Now that I've finished these small updates for the website, it's time for me to get back to working on the RPG. Next build coming up very soon.

Thanks for reading, much love and take care of yourselves