package zio.cookbook.env

import zio.cookbook.env.Common.{ UserID, UserProfile }
import zio.test.Assertion.{ equalTo }
import zio.test._

object DBSpec extends DefaultRunnableSpec {
  def spec = suite("DBSpec")(
    testM("access to Mock DB") {
      val user0 = UserID(0)
      val prof0 = UserProfile(user0)

      for {
        _  <- TestService.setTestData(Map(user0 -> prof0))
        p0 <- TestService.lookup(user0)
      } yield assert(p0)(equalTo(prof0))
    }
  )
}
