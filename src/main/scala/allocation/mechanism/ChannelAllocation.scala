package allocation.mechanism

import Constants._
import DataStore._

object ChannelAllocation {
  val channelManger: ChannelManager = new ChannelManager

  def main(args: Array[String]): Unit = {
    println(s"CHANNEL_ONE_ID $CHANNEL_ONE_ID \n")
    println(s"CHANNEL_TWO_ID $CHANNEL_TWO_ID \n")
    println(s"CHANNEL_THREE_ID $CHANNEL_THREE_ID \n")

    println(s"USER_ONE_ID $USER_ONE_ID \n")
    println(s"USER_TWO_ID $USER_TWO_ID \n")

    channels foreach { channel => println(s"Initial channel allocation :- $channel \n") }

    channelManger.followRequest(Following(CHANNEL_ONE_ID, USER_TWO_ID))

    channels foreach { channel => println(s"New allocation :- $channel \n") }
  }
}
