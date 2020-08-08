package com.oscarg798.babyrecord.core

import com.oscarg798.babyrecord.core.models.BabyRecordType
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class BabyRecordTest  {

    @Test
    fun `given medicine name when getTypeFromName is executed then it should return medicine type`(){
        BabyRecordType.getTypeFromName("medicine") shouldBeInstanceOf BabyRecordType.Medicine::class
    }

    @Test
    fun `given diaper name when getTypeFromName is executed then it should return diaper type`(){
        BabyRecordType.getTypeFromName("diaper") shouldBeInstanceOf BabyRecordType.Diaper::class
    }

    @Test
    fun `given sleep name when getTypeFromName is executed then it should return sleep type`(){
        BabyRecordType.getTypeFromName("sleep") shouldBeInstanceOf BabyRecordType.Sleep::class
    }

    @Test
    fun `given feed name when getTypeFromName is executed then it should return feed type`(){
        BabyRecordType.getTypeFromName("feed") shouldBeInstanceOf BabyRecordType.Feed::class
    }

    @Test
    fun `size feed name when getTypeFromName is executed then it should return size type`(){
        BabyRecordType.getTypeFromName("size") shouldBeInstanceOf BabyRecordType.Size::class
    }
}
