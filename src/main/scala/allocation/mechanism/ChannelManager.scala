package allocation.mechanism

import java.util.UUID

import allocation.mechanism.DataStore._
import Constants._
import scala.collection.mutable.{MutableList => MList}

/**
  * Created by asteroid-m on 1/5/17.
  */
class ChannelManager {

  def followRequest(following: Following): MList[Following] = {
    followings += following

    val allocatedPhoneNumberDetails = getPhoneNumberAllocation(following.userId)

    if (allocatedPhoneNumberDetails.nonEmpty) resolveDataCollision(allocatedPhoneNumberDetails)
    else (followings :+ following).distinct
  }

  def getPhoneNumberAllocation(userId: UUID): Map[Option[String], MList[Channel]] =
    followings.filter(_.userId == userId).map(_.channelId).flatMap(id => channels.filter(_.id == id))
      .groupBy(_.phoneNumber).filter { case (number, channel) => channel.length > 1 }

  def resolveDataCollision(collisionData: Map[Option[String], MList[Channel]]): MList[Following] = {
    val phoneNumberChannelMap: Map[Option[String], MList[Channel]] = channels.groupBy(_.phoneNumber)

    val assignedNumbers: List[String] = phoneNumberChannelMap
      .map { case (number, channel) => number }
      .map(_.fold(EMPTY_STRING)(identity)).toList

    val unassignedNumbers: MList[String] = phoneNumbers.map(_.number).diff(assignedNumbers)

    collisionData.flatMap {
      case (number, channel) =>
        val toBeChanged: Channel = getLeastUsedChannel(channel)

        channels = channels.diff(channels.filter(_.id == toBeChanged.id))

        val conflictCheck: List[Boolean] = (phoneNumberChannelMap map {
          case (k, v) => doesConflictExist(toBeChanged.id, k.fold("")(identity), v)
        }).toList

        channels += toBeChanged.copy(phoneNumber = if (conflictCheck.contains(true)) unassignedNumbers.headOption else number)
    }.toList

    followings
  }

  def doesConflictExist(channelId: UUID, num: String, channels: MList[Channel]) =
    channels flatMap (channel => followings.filter(_.channelId == channel.id).map(_.channelId)) contains channelId

  //TODO Implementation pending
  def getLeastUsedChannel(channels: MList[Channel]) = channels.head
}
