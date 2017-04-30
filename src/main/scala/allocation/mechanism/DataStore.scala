package allocation.mechanism

import allocation.mechanism.Constants._

import scala.collection.mutable.{MutableList => MList}

/**
  * Created by asteroid-m on 28/4/17.
  */
class DataStore {

  //TODO remove var
  var channels: MList[Channel] =
  MList(Channel(CHANNEL_ONE_ID, "channel_one", Some("ph_number_one")),
    Channel(CHANNEL_TWO_ID, "channel_two", Some("ph_number_one")),
    Channel(CHANNEL_THREE_ID, "channel_three", Some("ph_number_one")))

  val users: MList[User] = MList(User(USER_ONE_ID, "user_one"), User(USER_TWO_ID, "user_two"))

  val followings: MList[Following] =
    MList(Following(CHANNEL_THREE_ID, USER_ONE_ID), Following(CHANNEL_TWO_ID, USER_TWO_ID), Following(CHANNEL_ONE_ID, USER_ONE_ID))

  val phoneNumbers: MList[PhoneNumber] =
    MList(PhoneNumber("ph_number_one"), PhoneNumber("ph_number_two"), PhoneNumber("ph_number_three"))

}

object DataStore extends DataStore