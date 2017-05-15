package allocation.mechanism

import java.util.UUID

/**
  * Created by asteroid-m on 28/4/17.
  */

case class Channel(id: UUID, name: String, phoneNumber: Option[String]){

}

case class User(id: UUID, name: String)

case class Following(channelId: UUID, userId: UUID)

case class PhoneNumber(number: String)
