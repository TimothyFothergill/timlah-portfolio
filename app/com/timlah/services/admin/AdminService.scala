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

class AdminService @Inject()(
    blogService: BlogPostRepository
)(implicit val ec: ExecutionContext) {
    def checkUserDetails(adminLogin: AdminLoginDetails): Boolean = {
        val users: Future[Seq[AdminLoginDetails]] = blogService.getAllUsers
        val futureUsers = Await.result(users, 3.seconds)
        BCrypt.checkpw(adminLogin.password, futureUsers.head.password)
    }

    def addNewBlogPostToDatabase(blogPost: NewBlogPostForm) = {
        ??? // TODO
    }
}
