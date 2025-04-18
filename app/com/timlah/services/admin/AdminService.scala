package com.timlah.services.admin

import com.timlah.models.admin.AdminLoginDetails
import com.timlah.repositories.BlogPostRepository
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import org.mindrot.jbcrypt.BCrypt

import scala.concurrent.Await
import scala.concurrent.duration._
import java.security.SecureRandom
import com.timlah.models.admin.NewBlogPostForm
import com.timlah.models.{BlogPost, StoredBlogPost, Author}

class AdminService @Inject()(
    blogService: BlogPostRepository
)(implicit val ec: ExecutionContext) {
    def checkUserDetails(adminLogin: AdminLoginDetails): Boolean = {
        val users: Future[Seq[AdminLoginDetails]] = blogService.getAllUsers
        val futureUsers = Await.result(users, 3.seconds)
        if(futureUsers.head.username == adminLogin.username) {
            BCrypt.checkpw(adminLogin.password, futureUsers.head.password)
        } else {
            false
        }
    }

    def addNewBlogPostToDatabase(data: NewBlogPostForm) = {
        val latestBlogPost = blogService.getLatestBlogPost
        val latestBlogPostID = Await.result(latestBlogPost.map(i => i.id), 2.seconds)

        val blogPost: StoredBlogPost = StoredBlogPost(latestBlogPostID + 1,Author.authorDefault,None,data.title,data.slug,data.content,data.date)
        blogService.insertBlogPost(blogPost)
    }

    def dropBlogPostInDatabase(id: Int) = {
        blogService.dropBlogPost(id)
    }

    def editBlogPostInDatabase(data: NewBlogPostForm, id: Int) = {
        val currentBlogPost = blogService.getBlogEntryById(id)
        currentBlogPost.map { blogPost =>
            val storedBlogPost: StoredBlogPost = StoredBlogPost(
                    id,
                    Author.authorDefault,
                    None,
                    data.title,
                    data.slug,
                    data.content,
                    "" // we're not updating dates for now
                )
                blogService.updateBlogPost(storedBlogPost)
            }
    }
}
