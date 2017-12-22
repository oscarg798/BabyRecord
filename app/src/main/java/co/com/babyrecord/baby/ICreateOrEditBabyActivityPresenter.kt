package co.com.babyrecord.baby

import android.app.DatePickerDialog
import android.os.Bundle
import co.com.babyrecord.IBasePresenter

/**
 * Created by oscarg798 on 12/21/17.
 */
interface ICreateOrEditBabyActivityPresenter : IBasePresenter<ICreateOrEditBabyActivityView>,
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener{


    fun bindArguments(arguments: Bundle?)

    fun saveBaby(name: String)



}