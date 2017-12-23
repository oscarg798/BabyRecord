package co.com.babyrecord.size_record

import co.com.babyrecord.IBaseView
import co.com.core.models.SizeRecord

/**
 * Created by oscarg798 on 12/22/17.
 */
interface ISizeRecordFragmentView : IBaseView {


    fun showSizeRecords(sizeRecords: ArrayList<SizeRecord>)

    fun removeSizeRecord(sizeRecordUuid: String)

}