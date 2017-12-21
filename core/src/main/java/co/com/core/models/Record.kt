package co.com.core.models

/**
 * Created by oscarg798 on 12/20/17.
 */
data class Record(val uuid: String,
                  val startTime: Long,
                  var endTime: Long?,
                  val type: String)