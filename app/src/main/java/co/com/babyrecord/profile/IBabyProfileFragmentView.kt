package co.com.babyrecord.profile

import android.text.SpannableString
import co.com.babyrecord.IBaseView

/**
 * Created by oscarg798 on 12/22/17.
 */
interface IBabyProfileFragmentView : IBaseView {

    fun showName(name: SpannableString)

    fun showBirthday(birthDay: SpannableString)

    fun showWeight(weight: SpannableString)

    fun showHeight(height: SpannableString)

    fun hideWeight()

    fun hideHeight()

    fun showNoBabyMessage()

    fun hideNoBabyMessage()
}