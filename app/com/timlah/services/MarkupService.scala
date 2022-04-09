package com.timlah.services

import laika.api._
import laika.format.{Markdown, HTML}
import laika.markdown.github.GitHubFlavor

class MarkupService {

  val transformer: Transformer = Transformer
    .from(Markdown)
    .to(HTML)
    .using(GitHubFlavor)
    .build

  def markdownFileToHTML(): Either[String, String] = {
    val file = scala.io.Source.fromResource("latest-blog.md")
    val lines =
      try file.getLines.toList.filterNot(_.isEmpty)
      finally file.close()
    transformer.transform(lines.take(100).mkString("\n")) match {
      case Left(_)  => Left("<Unable to render the post at this time.>")
      case Right(s) => Right(s)
    }
  }

  def markdownStringToHTML(string: String): Either[String, String] = {
    transformer.transform(string) match {
      case Left(_)  => Left("<Unable to render the post at this time.>")
      case Right(s) => Right(s)
    }
  }
}
