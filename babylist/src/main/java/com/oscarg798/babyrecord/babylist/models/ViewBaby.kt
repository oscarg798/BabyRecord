package com.oscarg798.babyrecord.babylist.models

import com.oscarg798.babyrecord.core.models.Baby

data class ViewBaby(
    val id: String,
    val name: String,
    val age: String
) {

    constructor(baby: Baby, age: String) : this(baby.id, baby.name, age)
}
