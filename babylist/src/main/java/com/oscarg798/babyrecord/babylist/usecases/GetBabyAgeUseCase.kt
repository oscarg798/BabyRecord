package com.oscarg798.babyrecord.babylist.usecases

import com.oscarg798.babyrecord.core.models.Baby
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class GetBabyAgeUseCase @Inject constructor(
    private val calendar: Calendar
) {

    operator fun invoke(baby: Baby): String {
        val todayDate = Date()
        calendar.time = todayDate
        val todayCalendar = calendar.clone() as Calendar
        calendar.time = Date(baby.birthDate)
        val birthDateCalendar = calendar.clone() as Calendar

        val years = todayCalendar.get(Calendar.YEAR) - birthDateCalendar.get(Calendar.YEAR)
        val months = todayCalendar.get(Calendar.MONTH) - birthDateCalendar.get(Calendar.MONTH)

        return if (years <= 0) {
            "${months + 1} Months"
        } else {
            "$years Years, ${months + 1} Months"
        }
    }
}
