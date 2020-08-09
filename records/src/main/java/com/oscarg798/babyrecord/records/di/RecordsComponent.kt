package com.oscarg798.babyrecord.records.di

import com.oscarg798.babyrecord.core.di.RecordModuleDependencies
import com.oscarg798.babyrecord.records.RecordsFragment
import dagger.Component

@Component(dependencies = [RecordModuleDependencies::class], modules = [RecordsModule::class])
interface RecordsComponent {

    fun inject(recordsFragment: RecordsFragment)

    @Component.Factory
    interface Factory {

        fun create(recordModuleDependencies: RecordModuleDependencies): RecordsComponent
    }
}
