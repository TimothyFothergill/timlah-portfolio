Recently, I stumbled across a Scala library called [Indigo](https://indigoengine.io), which is promoted as a game engine for functional programmers.
I've wanted to practice functional development for some time, so I've decided to give it a try. Following along with the tutorials isn't
too difficult, so I'm hoping before too long, I'll have a game I can embed on this website, written in Scala.  
  
Indigo uses one import and one line of [scala.js](https://www.scala-js.org), after which the implementation is purely Scala:

```scala 
import scala.scalajs.js.annotation.JSExportTopLevel
@JSExportTopLevel("IndigoGame")
```

There's some good documentation on the site, so I'm going to have a crack at this, see if I can't make a single-level for a platformer in it.  
  
I'll report my progress here once I've gotten somewhere!
